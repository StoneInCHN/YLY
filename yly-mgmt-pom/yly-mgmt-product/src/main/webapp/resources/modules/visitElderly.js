$(function(){
	
	$("#visitElderly-table-list").datagrid({
		title:message("yly.visitelderly.record"),
		fitColumns:true,
		toolbar:"#visitElderly_manager_tool",
		url:'../visitElderly/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.visitelderly.elderlyInfo"),field:"elderlyInfo",width:60,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
					
		      	}},
		      {title:message("yly.visitelderly.visitor"),field:"visitor",width:60,align:'center',sortable:true},
		      {title:message("yly.common.idcard"),field:"idcard",width:110,align:'center',sortable:true},
		      {title:message("yly.common.phonenumber"),field:"phoneNumber",width:75,align:'center',sortable:true},
		      {title:message("yly.visitelderly.visitPersonnelNumber"),field:"visitPersonnelNumber",width:50,align:'center',sortable:true},
		      {title:message("yly.common.relation"),field:"relation",width:75,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "SELF"){
		    	  		return  message("yly.common.relation.self");
		    	  	}
		    	  	if(value == "CHILDREN"){
		    	  		return  message("yly.common.relation.children");
		    	  	}
		    		if(value == "MARRIAGE_RELATIONSHIP"){
		    	  		return  message("yly.common.relation.marriage_relationship");
		    	  	}
		    		if(value == "GRANDPARENTS_AND_GRANDCHILDREN"){
		    	  		return  message("yly.common.relation.grandparents_and_grandchildren");
		    	  	}
		    		if(value == "BROTHERS_OR_SISTERS"){
		    	  		return  message("yly.common.relation.brothers_or_sisters");
		    	  	}
		    		if(value == "DAUGHTERINLAW_OR_SONINLAW"){
		    	  		return  message("yly.common.relation.daughterinlaw_or_soninlaw");
		    	  	}
		    		if(value == "FRIEND"){
		    	  		return  message("yly.common.relation.friend");
		    	  	}
		    		if(value == "OTHER"){
		    	  		return  message("yly.common.other");
		    	  	}
		      	}},
		   
		      {title:message("yly.visitelderly.visitDate"),field:"visitDate",width:100,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}},
			  {title:message("yly.visitelderly.dueLeaveDate"),field:"dueLeaveDate",width:110,align:'center',sortable:true,formatter: function(value,row,index){
			    	  	if(value != null){
			    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
			      	}
				}},
			{title:message("yly.visitelderly.reasonForVisit"),field:"reasonForVisit",width:120,align:'center',sortable:true,formatter: function(value,row,index){
				if(value && value.length >15){
					var abValue =  value.substring(0,10) +"...";
					var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
					return content;
				}else{
					return value
				}
	      	}},
			{title:message("yly.common.remark"),field:"remark",width:100,align:'center',sortable:true,formatter: function(value,row,index){
				if(value && value.length >15){
					var abValue =  value.substring(0,10) +"...";
					var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
					return content;
				}else{
					return value
				}
	      	}}
		   ]
		],
		onLoadSuccess:function(data){
	           $(".tips-span").tooltip({
	        	   position: 'top',
	               onShow: function(){
	                   $(this).tooltip('tip').css({ 
	                       width:'300'
	                   });
	               }
	           });
	        }

	});
	 

	visitElderly_manager_tool = {
			add:function(){		
				$('#addVisitElderly').dialog({    
				    title: message("yly.visitelderly.add"),    
				    width: 700,    
				    height: 500,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addVisitElderly_form').form('validate');
							if(validate){
								$.ajax({
									url:"../visitElderly/add.jhtml",
									type:"post",
									data:$("#addVisitElderly_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addVisitElderly_form').form('reset');
										$('#addVisitElderly').dialog("close");
										$("#visitElderly-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addVisitElderly').dialog("close");
						}
				    }]
				});  
				 $('#addVisitElderly_form').show();
			},
			edit:function(){
				var _edit_row = $('#visitElderly-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editVisitElderly').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 500,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../visitElderly/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editVisitElderly_form').form('validate');
							if(validate){
								$.ajax({
									url:"../visitElderly/update.jhtml",
									type:"post",
									data:$("#editVisitElderly_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVisitElderly').dialog("close");
										$("#visitElderly-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editVisitElderly').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('visitElderly-table-list','../visitElderly/delete.jhtml');
			}
	}
	  $("#visitElderly_search_btn").click(function(){
	  var _queryParams = $("#visitElderly_search_form").serializeJSON();
	  $('#visitElderly-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#visitElderly-table-list").datagrid('reload');
	  	//隐藏域用于标记上次使用过的查询条件 
		$("#elderlyNameHidden").val($("#elderlyName").val());
		$("#vistorHidden").val($("#vistor").val());
		$("#visitDateBeginDateHidden").val($("#visitDateBeginDate").val());
		$("#visitDateEndDateHidden").val($("#visitDateEndDate").val());
	  
	})
	

})