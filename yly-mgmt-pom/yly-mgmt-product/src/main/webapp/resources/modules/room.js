var room_manager_tool = {
			add:function(){		
				$('#addRoom').dialog({    
				    title: message("yly.room.add"),    
				    width: 380,    
				    height: 460,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addRoom_form').form('validate');
							if(validate){
								$.ajax({
									url:"../room/save.jhtml",
									type:"post",
									data:$("#addRoom_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addRoom').dialog("close");
											$("#room_table_list").datagrid('reload');
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
							 $('#addRoom').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addRoom_form').show();
				    	$("#addRoom_form_buildingId").combobox({    
						    valueField:'id',    
						    textField:'buildingName',
						    cache: true,
						    url:'../building/findAll.jhtml'
						});
				    	$("#addRoom_form_roomType").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'ROOMTYPE';// 参数
						    }
						});
				    },
				    onClose:function(){
				    	 $('#addRoom_form').form('reset');
				    }
				});  
			},
			edit:function(){
				var _edit_row = $('#room_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');      
					return false;
				}
				var _dialog = $('#editRoom').dialog({    
				    title: message("yly.room.edit"),     
				    width: 400,    
				    height: 500,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../room/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editRoom_form').form('validate');
							if(validate){
								$.ajax({
									url:"../room/update.jhtml",
									type:"post",
									data:$("#editRoom_form").serialize(),
									beforeSend:function(){
										$.messager.progress({text:message("yly.common.saving")});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editRoom').dialog("close");
											$("#room_table_list").datagrid('reload');
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
							 $('#editRoom').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('room_table_list','../room/delete.jhtml');
			}
	}
$(function(){
	$("#room_table_list").datagrid({
		title:message("yly.room.list"),
		fitColumns:true,
		toolbar:"#room_manager_tool",
		url:'../room/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.room.roomName"),field:"roomName",width:100,align:'center',sortable:true},
		      {title:message("yly.room.roomNumber"),field:"roomNumber",width:50,align:'center',sortable:true},
		      {title:message("yly.room.floor"),field:"floor",width:50,align:'center',sortable:true},
		      {title:message("yly.room.roomType"),field:"roomType",width:100,align:'center',sortable:true},
		      {title:message("yly.room.roomStatus"),field:"roomStatus",width:50,align:'center',sortable:true,formatter: function(value,row,index){
		    		if(value == "ENABLE"){
		    	  		return  message("yly.common.enable");
		    	  	}
		    	  	if(value == "DISABLE"){
		    	  		return  message("yly.common.disable");
		    	  	}
		      	}
		      },
		      {title:message("yly.room.building"),field:"building",width:100,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.buildingName;
		    	  }else{
		    		  return  value;
		    	  }
					
		      	}
		      },
		      {title:message("yly.room.description"),field:"description",width:200,align:'center',sortable:true,formatter: function(value,row,index){
					if(value && value.length >22){
						var abValue =  value.substring(0,19) +"...";
						var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
						return content;
					}else{
						return value
					}
		      	}
		      },
		      {title:message("yly.common.createDate"),field:"createDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
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
	$("#room_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#room_search_form #beginDate").val(),
			  endDate:$("#room_search_form #endDate").val()
	  }
	  $('#room_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#room_table_list").datagrid('reload');
	})
	
	
	$("#room_search_form_buildingId").combobox({    
	    url:'../building/findAll.jhtml',    
	    valueField:'id',    
	    textField:'buildingName'
	});  
})