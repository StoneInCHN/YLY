
$(function(){
	
	$("#medicalRecord-table-list").datagrid({
		title:message("yly.medicalRecord.list"),
		fitColumns:true,
		toolbar:"#medicalRecord_manager_tool",
		url:'../medicalRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#medicalRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 580, 
			    cache: false,
			    modal: true,
			    href:'../medicalRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#medicalRecordDetail').dialog("close");
					}
			    }],onOpen:function(){
			    	$('#editMedicalRecord_form').show();
			    },
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.name"),field:"elderlyInfo",width:100,sortable:true,formatter:function(value,row,index){
			    	if(value != null) { 
			    	  return value.name;
			      	}else{
			      		return value;
			      	}
			      }
		      },
		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	medicalRecord_manager_tool = {
			add:function(){
				$('#addMedicalRecord').dialog({
				    title: message("yly.medicalRecord.add"),    
				    width: 800,    
				    height: 580,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addMedicalRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../medicalRecord/add.jhtml",
									type:"post",
									data:$("#addMedicalRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addMedicalRecord').dialog("close");
											$('#addMedicalRecord_form').form('reset');
											$("#medicalRecord-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addMedicalRecord').dialog("close");
							 $('#addMedicalRecord_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addMedicalRecord_form').show();
				    }
				});  
				
			},
			edit:function(){
				var _edit_row = $('#medicalRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editMedicalRecord').dialog({    
					title: message("yly.common.edit"),   
				    width: 800,    
				    height: 580,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../medicalRecord/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editMedicalRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../medicalRecord/update.jhtml",
									type:"post",
									data:$("#editMedicalRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editMedicalRecord').dialog("close");
											$('#editMedicalRecord_form').form('reset');
											$("#medicalRecord-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editMedicalRecord').dialog("close");
							 $('#editMedicalRecord_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editMedicalRecord_form').show();
				    },
				});
				$('#editMedicalRecord_form').show();
			},
			remove:function(){
				listRemove('medicalRecord-table-list','../medicalRecord/delete.jhtml');
			}
	};
	$("#medicalRecord-search-btn").click(function(){
	  var _queryParams = $("#medicalRecord-search-form").serializeJSON();
	  $('#medicalRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#medicalRecord-table-list").datagrid('reload');
	})
	
	 
	 
})
