var bed_manager_tool = {
			add:function(){		
				$('#addBed').dialog({    
				    title: message("yly.bed.add"),    
				    width: 350,    
				    height: 360,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addBed_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bed/add.jhtml",
									type:"post",
									data:$("#addBed_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addBed_form').form('reset');
											$('#addBed').dialog("close");
											$("#bed_table_list").datagrid('reload');
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
							 $('#addBed').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addBed_form').show();
				    	$("#addBed_form_roomId").combotree({    
				    	    animate:true,
				    	    lines:true,
				    	    url:'../room/findAll.jhtml',
				    	    //选择树节点触发事件  
				    	    onBeforeSelect : function(node) {  
				    	        //返回树对象  
				    	        var tree = $(this).tree;  
				    	        //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
				    	        var isLeaf = tree('isLeaf', node.target);  
				    	        if (!isLeaf) {  
				    	        	$.messager.alert("警告","请选择子节点","warning");
				    				// 返回false表示取消本次选择操作
				    				return false;
				    	        }  
				    	    } 
				    	})
				    }
				});  
			},
			edit:function(){
				var _edit_row = $('#bed_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
					return false;
				}
				$('#editBed').dialog({    
				    title: message("yly.bed.edit"),     
				    width: 400,    
				    height: 380,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../bed/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editBed_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bed/update.jhtml",
									type:"post",
									data:$("#editBed_form").serialize(),
									beforeSend:function(){
										$.messager.progress({text:message("yly.common.saving")});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editBed').dialog("close");
											$("#bed_table_list").datagrid('reload');
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
							 $('#editBed').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#editBed_form').show();
				    }
				});  
			},
			remove:function(){
				listRemove('bed_table_list','../bed/delete.jhtml');
			}
	}
$(function(){
	$("#bed_table_list").datagrid({
		title:message("yly.bed.list"),
		fitColumns:true,
		toolbar:"#bed_manager_tool",
		url:'../bed/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.bed.bedNumber"),field:"bedNumber",width:50,sortable:true},
		      {title:message("yly.bed.room"),field:"room",width:100,formatter: function(value,row,index){
		    	  	if(value){
		    	  		return value.roomNumber;
		    	  	}
		      	}
		      },
		      {title:message("yly.bed.status"),field:"status",width:50,sortable:true,formatter: function(value,row,index){
		    	  	if(value == "ENABLE"){
		    	  		return  message("yly.common.enable");
		    	  	}
		    	  	if(value == "DISABLE"){
		    	  		return  message("yly.common.disable");
		    	  	}
		      	}
		      },
		      {title:message("yly.room.description"),field:"description",width:200,sortable:true,formatter: function(value,row,index){
					if(value && value.length >22){
						var abValue =  value.substring(0,19) +"...";
						var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
						return content;
					}else{
						return value
					}
		      	}
		      },
		      {title:message("yly.common.createDate"),field:"createDate",width:60,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:60,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
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
	/*$("#bed_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#bed_search_form #beginDate").val(),
			  endDate:$("#bed_search_form #endDate").val()
	  }
	  $('#bed_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#bed_table_list").datagrid('reload');
	})*/

	
})