$(function() {
	$('#roomTreeForChangeRoom').tree({    
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
	        		//$('#bed_table_list').datagrid('load',{buildingId:node.id});
					$('#bedImgShowPanel').panel('open').panel('refresh',{buildingId:node.id});
					
					
	        	}else{
	        		//$('#bed_table_list').datagrid('reload',{});
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{});
	        	}
	        }else if(node.attributes && node.attributes.rootNode){
	        	//$('#bed_table_list').datagrid('reload',{});
	        	$('#bedImgShowPanel').panel('open').panel('refresh',{});
	        }else if(node.attributes && node.attributes.roomNode){
	        	if(node.id){
	        		//$('#bed_table_list').datagrid('load',{roomId:node.id});
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{roomId:node.id});
	        	}else{
	        		//$('#bed_table_list').datagrid('reload',{});
	        		$('#bedImgShowPanel').panel('open').panel('refresh',{});
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
		  href:'../bed/bedImgShow.jhtml',
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
		 }  
	});  
})