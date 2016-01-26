var nurseArrangement_manager_tool = {
			add:function(){
				$('#add_nurseArrangement').dialog({    
				    title: message("yly.nurseArrangement.addNurse"),    
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
							 $('#add_nurseArrangement').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#add_nurseArrangement_form').show();
				    },
				    onClose:function(){
				    	 $('#add_nurseArrangement_form').form('reset');
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
//护理员安排	
$(function(){
	$("#nurseArrangement-table-list").datagrid({
		title:message("yly.nurseArrangement.list"),
		fitColumns:true,
		toolbar:"#nurseArrangement_manager_tool",
		url:'../nurseArrangement/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseArrangement').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../nurseArrangement/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#view_nurseArrangement').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:"10%",align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      {title:message("yly.nurseArrangement.nurseName"),field:"nurseName",width:"10%",align:'center',formatter:function(value,row,index){
		    	  return  formatLongString(value,20);
		      }},		 
		      {title:message("yly.nurseArrangement.nursingLevel"),field:"nursingLevel",width:"10%",align:'center',formatter:function(value,row,index){
		    	  return  formatLongString(value,20);
		      }},	
		      {title:message("yly.elderlyinfo.bed"),field:"bedLocation",width:"15%",align:'center',formatter:function(value,row,index){
		    	  return  formatLongString(value,20);
		      }},			      
		      {title:message("yly.nurseArrangement.nurseStartDate"),field:"nurseStartDate",width:"14%",align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
			  }},
			  {title:message("yly.nurseArrangement.nurseEndDate"),field:"nurseEndDate",width:"14%",align:'center',sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
			   }},
		      {title:message("yly.nurseArrangement.nurseAssistant"),field:"nurseAssistantName",width:"10%",align:'center',formatter:function(value,row,index){
		    	  return row.nurseAssistant.realName;
		      }},
			  {title:message("yly.common.remark"),field:"remark",width:"15%",align:'center',formatter:function(value,row,index){
					return  formatLongString(value,50);
			  }},
		   ]
		]
	});
	$("#nurseArrangement_search_btn").click(function(){
		  var _queryParams = $("#nurseArrangement_search_form").serializeJSON();
		  $('#nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#nurseArrangement-table-list").datagrid('reload');
			
		})
});
//护理员安排明细
$(function(){
	$("#nurseArrangementRecord-table-list").datagrid({
		title:message("yly.nurseArrangementRecord.list"),
		fitColumns:true,
		toolbar:"#nurseArrangementRecord_manager_tool",
		url:'../nurseArrangement/recordList.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseArrangementRecord').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../nurseArrangement/recordDetail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#view_nurseArrangementRecord').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyName",width:"15%",align:'center',formatter:function(value,row,index){
		    	  return formatLongString(value,20);
		      }},
		      {title:message("yly.nurseArrangement.nurseName"),field:"nurseName",width:"15%",align:'center',formatter:function(value,row,index){
		    	  return  formatLongString(value,20);
		      }},		 			      
		      {title:message("yly.nurseArrangement.nurseServiceTime"),field:"nurseServiceTime",width:"20%",align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
			  }},
		      {title:message("yly.nurseArrangement.nurseAssistant"),field:"nurseOperator",width:"15%",align:'center',formatter:function(value,row,index){
		    	  return formatLongString(value,20);
		      }},			  
			  {title:message("yly.common.remark"),field:"remark",width:"33%",align:'center',formatter:function(value,row,index){
					return  formatLongString(value,50);
			  }},
		   ]
		]
	});
	$("#nurseArrangementRecord_search_btn").click(function(){
		  var _queryParams = $("#nurseArrangementRecord_search_form").serializeJSON();
		  $('#nurseArrangementRecord-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#nurseArrangementRecord-table-list").datagrid('reload');
			
		})
});
//老人查询
$(function(){
	$("#elderlyInfoSearch-table-list").datagrid({
		url:'../elderlyInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_elderlyInfo').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../elderlyInfo/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#view_elderlyInfo').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
			{field : 'ck',checkbox : true},
			{title : message("yly.elderlyinfo.identifier"),field : "identifier",width :"30%",align : 'center',sortable : true},
			{title : message("yly.common.elderlyname"),field : "name",width :"30%",align : 'center',sortable : true},		
			{title : message("yly.elderly.status"),field : "elderlyStatus",width :"30%",align : 'center',sortable : true,formatter : function(value, row,index) {
					if (value == "IN_NURSING_HOME") {
						return message("yly.elderly.status.in_nursing_home");
					}
					if (value == "OUT_NURSING_HOME") {
						return message("yly.elderly.status.out_nursing_home");
					}
					if (value == "IN_PROGRESS_CHECKIN") {
						return message("yly.elderly.status.in_progress_checkin");
					}
					if (value == "IN_PROGRESS_CHECKOUT") {
						return message("yly.elderly.status.in_progress_checkout");
					}
					if (value == "DEAD") {
						return message("yly.elderly.status.dead");
					}
				}
			} 
		]
	]
});
	$("#elderlyInfo_search_btn").click(function(){
		  var _queryParams = $("#elderlyInfo_search_form").serializeJSON();
		  $('#elderlyInfoSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#elderlyInfoSearch-table-list").datagrid('reload');			
		});
	$('#elderlyInfoSearch-table-list').pagination({
		layout:['prev','links','next']
	});
});
//护理员查询
$(function(){
	$("#nurseAssistantSearch-table-list").datagrid({
		url:'../tenantUser/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseAssistant').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../tenantUser/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#view_nurseAssistant').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
			{field : 'ck',checkbox : true},
			{title : message("yly.nurseArrangement.nurseAssistant.staffID"),field : "staffID",width :"30%",align : 'center',sortable : true},
			{title : message("yly.nurseArrangement.nurseAssistant"),field : "realName",width :"30%",align : 'center',sortable : true},		
			{title : message("yly.nurseArrangement.nurseAssistant.staffStatus"),field : "staffStatus",width :"30%",align : 'center',sortable : true,formatter : function(value, row,index) {
					if (value == "INSERVICE") {
						return message("yly.tenantUser.staffStatus.inService");
					}
					if (value == "OUTSERVICE") {
						return message("yly.tenantUser.staffStatus.outService");
					}
				}
			} 
		]
	]
});
	$("#nurseAssistant_search_btn").click(function(){
		  var _queryParams = $("#nurseAssistant_search_form").serializeJSON();
		  $('#nurseAssistantSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#nurseAssistantSearch-table-list").datagrid('reload');
		});
});


