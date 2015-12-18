var elderlyevent_manager_tool = {
			add:function(){
				$('#addElderlyEvent').dialog({    
				    title: message("yly.elderly.event.add"),    
				    width: 500,    
				    height: 350,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addElderlyEvent_form').form('validate');
							if(validate){								
								$.ajax({
									url:"../elderlyEventRecord/add.jhtml",
									type:"post",
									data:$("#addElderlyEvent_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addElderlyEvent').dialog("close");
										$("#event-table-list").datagrid('reload');
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
							 $('#addElderlyEvent').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addElderlyEvent_form').show();
				    },
				    onClose:function(){
				    	 $('#addElderlyEvent_form').form('reset');
				    }
				});
			},
			remove:function(){
				listRemove('event-table-list','../elderlyEventRecord/delete.jhtml');
			},
			edit:function(){
				var _edit_row = $('#event-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#editEvent').dialog({    
				    title: message("yly.elderly.event.edit"),     
				    width: 500,    
				    height: 350,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../elderlyEventRecord/detail.jhtml?id='+_edit_row.id+'&handle=edit',
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editEvent_form').form('validate');
							if(validate){
								$.ajax({
									url:"../elderlyEventRecord/update.jhtml",
									type:"post",
									data:$("#editEvent_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editEvent').dialog("close");
										$("#event-table-list").datagrid('reload');
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
							 $('#editEvent').dialog("close");
						}
				    }]
				});  
			}
	}
	
$(function(){
	$("#event-table-list").datagrid({
		title:message("yly.elderlyInfo.event.list"),
		fitColumns:true,
		toolbar:"#building_manager_tool",
		url:'../elderlyEventRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#editEvent').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../elderlyEventRecord/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editEvent').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      {title:message("yly.elderlyInfo.common.operator"),field:"operator",width:30,align:'center',formatter:function(value,row,index){
		    	 return formatLongString(value,8);
		      }},
		      {title:"事件类型",field:"elderlyEventType",width:30,align:'center',formatter:function(value,row,index){
		    		if(value == "NEGATIVE"){
		    	  		return  "恶劣事件";
		    	  	}
		    	  	if(value == "NORMAL"){
		    	  		return  "普通事件";
		    	  	}
		    	  	if(value == "ACTIVE"){
		    	  		return  "表扬事件"
		    	  	}
			      }},
		      {title:message("yly.elderlyInfo.event.eventDate"),field:"eventDate",width:30,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
				{title:message("yly.elderlyInfo.event.content"),field:"eventContent",width:100,align:'center',formatter:function(value,row,index){
					return  formatLongString(value,30);
			  }},
		   ]
		]
	});
	$("#elderlyEvent_search_btn").click(function(){
		  var _queryParams = $("#elderlyEvent_search_form").serializeJSON();
		  $('#event-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#event-table-list").datagrid('reload');
			
		})
})
