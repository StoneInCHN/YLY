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
			},
			exportData:function(){
				$.ajax({
					url:"../elderlyEventRecord/count.jhtml",
					type:"post",
					data:$("#elderlyEvent_search_form").serialize(),
					success:function(result,response,status){
						if(result.count != null){
							var text = "";
							if(result.count == 0){
								text = "当前条件无可导出的数据。";
								$.messager.alert(message("yly.common.notice"), text,'warning');
							}else if(result.count <= 500){
								text = "确定导出 "+result.count+" 条记录？";
								$.messager.confirm(message("yly.common.confirm"), text, function(r) {
									if(r){
										$("#elderlyEvent_search_form").attr("action","../elderlyEventRecord/exportData.jhtml");
										$("#elderlyEvent_search_form").attr("target","_blank");
										$("#elderlyEvent_search_form").submit();
									}

								});
							}else{
								text = "导出数据超过500条数据，建议搜索查询条件以缩小查询范围，再导出。";
								$.messager.confirm(message("yly.common.notice"), text, function(r) {
									if (!r) {
										text = "导出共有"+ result.count +"条数据，导出超过500条数据可能需要您耐心等待，仍需操作请确定继续。";
										$.messager.confirm(message("yly.common.confirm"), text, function(yes) {
											if(yes){
												$("#elderlyEvent_search_form").attr("action","../elderlyEventRecord/exportData.jhtml");
												$("#elderlyEvent_search_form").attr("target","_blank");
												$("#elderlyEvent_search_form").submit();
											}
										});
									}
								})
							}
						}
						$("#event-table-list").datagrid('reload');
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
						alert("error");
						$.messager.progress('close');
						alertErrorMsg();
					}
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
			//隐藏域用于标记上次使用过的查询条件 
			$("#keysOfElderlyNameHidden").val($("#elderlyName").val());
			$("#beginDateHidden").val($("#beginDate").val());
			$("#endDateHidden").val($("#endDate").val());
		})
})
//function formatLongString(str,len){
//	if(str.length > len){
//		return '<span title="'+str+'">'+str.substring(0,len)+"..."+'<span>'
//	}else{
//		return str;
//	}	
//}
