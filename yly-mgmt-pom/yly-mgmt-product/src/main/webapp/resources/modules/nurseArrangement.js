var nurseArrangement_manager_tool = {
			add:function(){
				$('#add_nurseArrangement').dialog({    
				    title: message("yly.nurseArrangement.addNurse"),    
				    width: 650,    
				    height: 500,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addNurseArrangement_form').form('validate');
							if(validate){								
								$.ajax({
									url:"../nurseArrangement/add.jhtml",
									type:"post",
									data:$("#addNurseArrangement_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#add_nurseArrangement').dialog("close");
										$("#nurseArrangement-table-list").datagrid('reload');
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
				    	$('#addNurseArrangement_form').show();
				    },
				    onClose:function(){
				    	 $('#addNurseArrangement_form').form('reset');
				    }
				});
			},
			remove:function(){
				listRemove('nurseArrangement-table-list','../nurseArrangement/delete.jhtml');
			},
			edit:function(){
				var _edit_row = $('#nurseArrangement-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#edit_nurseArrangement').dialog({    
				    title: message("yly.common.edit"),     
				    width: 650,    
				    height: 500, 
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../nurseArrangement/detail.jhtml?id='+_edit_row.id+'&handle=edit',
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editNurseArrangement_form').form('validate');
							if(validate){
								$.ajax({
									url:"../nurseArrangement/update.jhtml",
									type:"post",
									data:$("#editNurseArrangement_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#edit_nurseArrangement').dialog("close");
										$("#nurseArrangement-table-list").datagrid('reload');
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
							 $('#edit_nurseArrangement').dialog("close");
						}
				    }]
				});  
			}
	}
var nurseArrangementRecord_manager_tool = {
		add:function(){
			$('#add_nurseArrangementRecord').dialog({    
			    title: message("yly.nurseArrangement.addNurseRecord"),    
			    width: 650,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    modal:true,
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
			    	handler:function(){
						var validate = $('#addNurseArrangementRecord_form').form('validate');
						if(validate){								
							$.ajax({
								url:"../nurseArrangement/addRecord.jhtml",
								type:"post",
								data:$("#addNurseArrangementRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#add_nurseArrangementRecord').dialog("close");
									$("#nurseArrangementRecord-table-list").datagrid('reload');
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
						 $('#add_nurseArrangementRecord').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#addNurseArrangementRecord_form').show();
			    },
			    onClose:function(){
			    	 $('#addNurseArrangementRecord_form').form('reset');
			    }
			});
		},
		remove:function(){
			listRemove('nurseArrangementRecord-table-list','../nurseArrangement/deleteRecord.jhtml');
		},
		edit:function(){
			var _edit_row = $('#nurseArrangementRecord-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
				return false;
			}
			var _dialog = $('#edit_nurseArrangementRecord').dialog({    
			    title: "编辑",     
			    width: 650,    
			    height: 550, 
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../nurseArrangement/detailRecord.jhtml?id='+_edit_row.id+'&handle=editRecord',
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editNurseArrangementRecord_form').form('validate');
						if(validate){
							$.ajax({
								url:"../nurseArrangement/updateRecord.jhtml",
								type:"post",
								data:$("#editNurseArrangementRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#edit_nurseArrangementRecord').dialog("close");
									$("#nurseArrangementRecord-table-list").datagrid('reload');
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
						 $('#edit_nurseArrangementRecord').dialog("close");
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
		checkOnSelect:true,
		onSelect:function(rowIndex,rowData){
			var selected_rows = $('#nurseArrangement-table-list').datagrid('getSelections');
			if(selected_rows.length > 1){//取消其他行的选择，模拟singleSelect属性，因为singleSelect属性和checkOnSelect属性冲突，不能直接引用
				for(var i=0;i<selected_rows.length;i++){
					if(rowData.id != selected_rows[i].id){
						var rowIndex=$('#nurseArrangement-table-list').datagrid('getRowIndex',selected_rows[i]);
						$("#nurseArrangement-table-list").datagrid("unselectRow", rowIndex);
					}
				}
			}
			//选中一个老人，右侧的护理员安排根据老人过滤
			$('#nurseArrangemenIDSearch').val(rowData.id);
			  var _queryParams = $("#nurseArrangementRecord_search_form").serializeJSON();
			  $('#nurseArrangementRecord-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#nurseArrangementRecord-table-list").datagrid('reload');	
		},
		onUnselect:function(rowIndex,rowData){
			var selected_rows = $('#nurseArrangement-table-list').datagrid('getSelections');
			if(selected_rows.length == 0){//取消选中当前行
				//取消选中一个老人，还原右侧的护理员安排根据老人过滤
				  $('#nurseArrangemenIDSearch').val(null);
				  var _queryParams = $("#nurseArrangementRecord_search_form").serializeJSON();
				  $('#nurseArrangementRecord-table-list').datagrid('options').queryParams = _queryParams;  
				  $("#nurseArrangementRecord-table-list").datagrid('reload');
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseArrangement').dialog({    
			    title: message("yly.common.detail"),    
			    width: 650,    
			    height: 500,
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
			  }}
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
		url:'../nurseArrangement/listRecord.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseArrangementRecord').dialog({    
			    title: message("yly.common.detail"),    
			    width: 650,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../nurseArrangement/detailRecord.jhtml?id='+rowData.id+'&handle=viewRecord',
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
		checkOnSelect:true,
		onSelect:function(rowIndex,rowData){
			var selected_rows = $('#elderlyInfoSearch-table-list').datagrid('getSelections');
			if(selected_rows.length > 1){//取消其他行的选择，模拟singleSelect属性，因为singleSelect属性和checkOnSelect属性冲突，不能直接引用
				for(var i=0;i<selected_rows.length;i++){
					if(rowData.id != selected_rows[i].id){
						var rowIndex=$('#elderlyInfoSearch-table-list').datagrid('getRowIndex',selected_rows[i]);
						$("#elderlyInfoSearch-table-list").datagrid("unselectRow", rowIndex);
					}
				}
			}
			//选中一个老人，右侧的护理员安排根据老人过滤
			$('#elderlyIDSearch').val(rowData.id);
			  var _queryParams = $("#nurseArrangement_search_form").serializeJSON();
			  $('#nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#nurseArrangement-table-list").datagrid('reload');
		},
		onUnselect:function(rowIndex,rowData){
			var selected_rows = $('#elderlyInfoSearch-table-list').datagrid('getSelections');
			if(selected_rows.length == 0){//取消选中当前行
				//取消选中一个老人，还原右侧的护理员安排根据老人过滤
				$('#elderlyIDSearch').val(null);
				  var _queryParams = $("#nurseArrangement_search_form").serializeJSON();
				  $('#nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;  
				  $("#nurseArrangement-table-list").datagrid('reload');
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#view_elderlyInfo').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,
			    modal: true,
			    href:'../elderlyInfo/details.jhtml?id='+rowData.id,
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
		url:'../tenantUser/list.jhtml?isJoinNurseSearch=true',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		checkOnSelect:true,
		onSelect:function(rowIndex,rowData){
			var selected_rows = $('#nurseAssistantSearch-table-list').datagrid('getSelections');
			if(selected_rows.length > 1){//取消其他行的选择，模拟singleSelect属性，因为singleSelect属性和checkOnSelect属性冲突，不能直接引用
				for(var i=0;i<selected_rows.length;i++){
					if(rowData.id != selected_rows[i].id){
						var rowIndex=$('#nurseAssistantSearch-table-list').datagrid('getRowIndex',selected_rows[i]);
						$("#nurseAssistantSearch-table-list").datagrid("unselectRow", rowIndex);
					}
				}
			}
			//选中一个老人，右侧的护理员安排根据老人过滤
			$('#nurseAssistantIDSearch').val(rowData.id);
			  var _queryParams = $("#nurseArrangement_search_form").serializeJSON();
			  $('#nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#nurseArrangement-table-list").datagrid('reload');			
		},
		onUnselect:function(rowIndex,rowData){
			var selected_rows = $('#nurseAssistantSearch-table-list').datagrid('getSelections');
			if(selected_rows.length == 0){//取消选中当前行
				//取消选中一个老人，还原右侧的护理员安排根据老人过滤
				  $('#nurseAssistantIDSearch').val(null);
				  var _queryParams = $("#nurseArrangement_search_form").serializeJSON();
				  $('#nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;  
				  $("#nurseArrangement-table-list").datagrid('reload');
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseAssistant').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../tenantUser/details.jhtml?id='+rowData.id,
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
/**
 * 老人基本信息填充
 * dataMap.id为老人id
 */
function populateElderlyInfo(id,dataMap){
	 //clearAddCheckoutChargeText();	
		if(id.indexOf("add")==0){//以add开头
			 $("#addNurseArrangement_elderlyInfoID").val(dataMap.id); // 隐藏域 老人id
			 $("#addNurseArrangement_elderlyName").textbox('setValue',dataMap.name); // 老人姓名
			 $("#addNurseArrangement_bedLocation").textbox('setValue',dataMap.bedLocation); // 床位
			 $("#addNurseArrangement_nursingLevel").textbox('setValue',dataMap.nursingLevel); // 护理级别
		}
		if(id.indexOf("edit")==0){//以edit开头
			 $("#editNurseArrangement_elderlyInfoID").val(dataMap.id); // 隐藏域 老人id
			 $("#editNurseArrangement_elderlyName").textbox('setValue',dataMap.name); // 老人姓名
			 $("#editNurseArrangement_bedLocation").textbox('setValue',dataMap.bedLocation); // 床位
			 $("#editNurseArrangement_nursingLevel").textbox('setValue',dataMap.nursingLevel); // 护理级别
		}
}
function populateNurseArrangement(id, dataMap){
		if(id.indexOf("add")==0){//以add开头
			$("#addNurseArrangementRecord_ID").val(dataMap.id); //护理员安排 id
			$("#addNurseArrangementRecord_nurseName").textbox('setValue',dataMap.nurseName); // 护理名称
			$("#addNurseArrangementRecord_nurseStartDate").val(new Date(dataMap.nurseStartDate).Format("yyyy-MM-dd")); //护理开始日期
			$("#addNurseArrangementRecord_nurseEndDate").val(new Date(dataMap.nurseEndDate).Format("yyyy-MM-dd")); //护理结束日期
			$("#addNurseArrangementRecord_elderlyName").textbox('setValue',dataMap.elderlyInfoName); // 老人名字
			$("#addNurseArrangementRecord_nurseAssistantName").textbox('setValue',dataMap.nurseAssistantName); // 护理员名字
			$("#addNurseArrangementRecord_bedLocation").textbox('setValue',dataMap.bedLocation); //床位位置
			$("#addNurseArrangementRecord_nursingLevel").textbox('setValue',dataMap.nursingLevel); //护理级别
		}
		if(id.indexOf("edit")==0){//以edit开头
			$("#editNurseArrangementRecord_ID").val(dataMap.id); //护理员安排 id
			$("#editNurseArrangementRecord_nurseName").textbox('setValue',dataMap.nurseName); // 护理名称
			$("#editNurseArrangementRecord_nurseStartDate").val(new Date(dataMap.nurseStartDate).Format("yyyy-MM-dd")); //护理开始日期
			$("#editNurseArrangementRecord_nurseEndDate").val(new Date(dataMap.nurseEndDate).Format("yyyy-MM-dd")); //护理结束日期
			$("#editNurseArrangementRecord_elderlyName").textbox('setValue',dataMap.elderlyInfoName); // 老人名字
			$("#editNurseArrangementRecord_nurseAssistantName").textbox('setValue',dataMap.nurseAssistantName); // 护理员名字
			$("#editNurseArrangementRecord_bedLocation").textbox('setValue',dataMap.bedLocation); //床位位置
			$("#editNurseArrangementRecord_nursingLevel").textbox('setValue',dataMap.nursingLevel); //护理级别
		}
}
