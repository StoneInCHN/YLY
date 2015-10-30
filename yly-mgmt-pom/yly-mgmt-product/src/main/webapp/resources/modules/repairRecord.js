$(function(){
	
	$("#repairRecord-table-list").datagrid({
		title:message("yly.repairRecord.record"),
		fitColumns:true,
		toolbar:"#repairRecord_manager_tool",
		url:'../repairRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#repairRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 380, 
			    cache: false,   
			    href:'../repairRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#repairRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.repairRecord.repairContent"),field:"repairContent",width:40,sortable:true},
		      {title:message("yly.common.insertDate"),field:"createDate",width:40,sortable:true, formatter: function(value,row,index){
		    	  return new Date(value).Format("yyyy-MM-dd");
		      }},
		      {title:message("yly.repairRecord.repairPlace"),field:"repairPlace",width:80,align:'center',sortable:true},
		      {title:message("yly.repairRecord.reportOperator"),field:"reportOperator",width:20,align:'center',sortable:true},
		      {title:message("yly.repairRecord.repairOperator"),field:"repairOperator",width:80,sortable:true}
		   ]
		]

	});
	 
	 

	repairRecord_manager_tool = {
			add:function(){		
				$('#addRepairRecord').dialog({    
				    title: message("yly.repairRecord.add"),    
				    width: 400,    
				    height: 380,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addRepairRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../repairRecord/add.jhtml",
									type:"post",
									data:$("#addRepairRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addRepairRecord_form').form('reset');
										$('#addRepairRecord').dialog("close");
										$("#repairRecord-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							}
							else{
								alert("validate fail");
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addRepairRecord').dialog("close");
						}
				    }]
				});  
				 $('#addRepairRecord_form').show();
			},
			edit:function(){
				var _edit_row = $('#repairRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editRepairRecord').dialog({    
				    title: message("yly.common.edit"),     
				    width: 400,    
				    height: 380,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../repairRecord/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editRepairRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../repairRecord/update.jhtml",
									type:"post",
									data:$("#editRepairRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editRepairRecord').dialog("close");
										$("#repairRecord-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editRepairRecord').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				var _edit_row = $('#repairRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.deleteRow"));  
					return false;
				}
				listRemove('repairRecord-table-list','../repairRecord/delete.jhtml');
			}
	}
	$("#repairRecord_search_btn").click(function(){
	  var _queryParams = $("#repairRecord_search_form").serializeJSON();
	  $('#repairRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#repairRecord-table-list").datagrid('reload');
	})
	
	$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
	})
	 
})

