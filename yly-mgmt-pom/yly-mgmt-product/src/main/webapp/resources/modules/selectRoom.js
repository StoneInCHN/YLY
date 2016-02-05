$(function() {
	$('#roomTreeForSelectRoom').tree({    
	    url:'../room/findAll.jhtml', 
	    queryParams:{
	    	isSelect:false
		},
	    animate:true,
	    lines:true,
	    onBeforeSelect : function(node) {  
	        var tree = $(this).tree;  
	        var isLeaf = tree('isLeaf', node.target); 
	        if (node.attributes && node.attributes.isBuilding) {  
				if(node.id){
					$('#bedImgShowPanel').panel('open').panel('refresh',{buildingId:node.id});
					$('#bedImgShowPanel').panel({
						queryParams:{buildingId:node.id}
					})
	        	}else{
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{});
	        		$('#bedImgShowPanel').panel({
						queryParams:{}
					})
	        	}
	        }else if(node.attributes && node.attributes.rootNode){
	        	$('#bedImgShowPanel').panel('open').panel('refresh',{});
	        	$('#bedImgShowPanel').panel({
					queryParams:{}
				})
	        }else if(node.attributes && node.attributes.roomNode){
	        	if(node.id){
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{roomId:node.id});
	        		$('#bedImgShowPanel').panel({
						queryParams:{roomId:node.id}
					})
	        	}else{
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{});
	        		$('#bedImgShowPanel').panel({
						queryParams:{}
					})
	        	}
	        }else{
	        	return false;
	        }
	    } 
	}); 

	
	$('#bedImgShowPanel').panel({    
		  fit:true,
		  border:false,
		  title: '床位图展示',
		  href:'../selectRoom/bedImgShow.jhtml',
		  loadingMessage:message("yly.common.loading"),
		  onLoad:function(){    
			  $('.wminimize').click(
						function(e) {
							e.preventDefault();
							var $wcontent = $(this).parent().parent().parent().next(
									'.widget-content');
							if ($wcontent.is(':visible')) {
								$(this).children('i').removeClass('fa-chevron-up');
								$(this).children('i').addClass('fa-chevron-down');
							} else {
								$(this).children('i').removeClass('fa-chevron-down');
								$(this).children('i').addClass('fa-chevron-up');
							}
							$wcontent.toggle(500);
				});
			  $(".bed-item-somebody").on("click",function(){
				 // alert("somebody");
			  })
			   $(".bed-item-nobody").on("click",function(){
				   var $this = $(this);
				   var type =  $("#selectRoom").attr("data-type");
				   var elderlyId = $("#selectRoom").attr("data-elderly-id");
				   var elderlyName = $("#selectRoom").attr("data-elderly-name");
				   var bedNumber = $("#selectRoom").attr("data-elderly-bed-number");
				   var originalBedId =$("#selectRoom").attr("data-original-bed-id");
				   var bedNumberCur = $this.attr("data-bed-number");
				   var bedIdCur =$this.attr("data-bed-id");
				   var bedDescription =$(this).attr("data-bedDescription");
				  if(!type){
					  return ;
				  }else{
					  switch(type)
					  {
					  	// 选房 
						  case "1":
							  var alertMsg = "确认要选择["+bedNumberCur+"] 号床吗?"
							  $.messager.confirm('选房确认', alertMsg, function(r){
									if (r){
										debugger
										 var inputId = $("#selectRoom").attr("data-bed-input-id");
										  $("#"+inputId+"_ID").val(bedIdCur);
										  $("#"+inputId+"_text").textbox("setValue",bedDescription);
										  $('#selectRoom').dialog("close");
									}
								});
						    break;
						 //换房
						  case "2":
							  var alertMsg = "确认要将 ["+elderlyName+"] 从 ["+bedNumber+"] 号床换到 ["+bedNumberCur+"] 号床吗?"
							  $.messager.confirm('换房确认', alertMsg, function(r){
									if (r){
										$.ajax({
											url:"../changeRoom/changeToNewRoom.jhtml",
											type:"post",
											data:{
												"elderlyInfoId":elderlyId,
												"originalBedId":originalBedId,
												"newBedId":bedIdCur
											},
											beforeSend:function(){
												$.messager.progress({
													text:message("yly.common.saving")
												});
											},
											success:function(result,response,status){
													$.messager.progress('close');
													showSuccessMsg(result.content);
													$("#selectRoom").attr("data-elderly-id",elderlyId);
									    			$("#selectRoom").attr("data-elderly-name",elderlyName);
									    			$("#selectRoom").attr("data-elderly-bed-number",bedNumberCur);
									    			$("#selectRoom").attr("data-original-bed-id",bedIdCur);
													$('#bedImgShowPanel').panel('refresh');
											},
											error:function (XMLHttpRequest, textStatus, errorThrown) {
												$.messager.progress('close');
												alertErrorMsg();
											}
										});
									}
								});

						    break;
						  default:
						   return ;
					  }
				  }
			  })
		 }  
	});  
})
