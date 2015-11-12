var physicalExamination_manager_tool = {
			add:function(){
				$('#addPhysicalExamination').dialog({
				    title: message("yly.physicalExamination.add"),    
				    width: 800,    
				    height: 580,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addPhysicalExamination_form').form('validate');
							if(validate){
								$.ajax({
									url:"../physicalExamination/add.jhtml",
									type:"post",
									data:$("#addPhysicalExamination_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addPhysicalExamination').dialog("close");
											$('#addPhysicalExamination_form').form('reset');
											$("#physicalExamination-table-list").datagrid('reload');
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
							 $('#addPhysicalExamination').dialog("close");
							 $('#addPhysicalExamination_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addPhysicalExamination_form').show();
				    }
				});  
				
			},
			edit:function(){
				var _edit_row = $('#physicalExamination-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editPhysicalExamination').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../physicalExamination/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editPhysicalExamination_form').form('validate');
							if(validate){
								$.ajax({
									url:"../physicalExamination/update.jhtml",
									type:"post",
									data:$("#editPhysicalExamination_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editPhysicalExamination').dialog("close");
											$('#editPhysicalExamination_form').form('reset');
											$("#physicalExamination-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editPhysicalExamination').dialog("close");
							 $('#editPhysicalExamination_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editPhysicalExamination_form').show();
				    },
				});
				$('#editPhysicalExamination_form').show();
			},
			remove:function(){
				listRemove('physicalExamination-table-list','../physicalExamination/delete.jhtml');
			}
	};
$(function(){
	$("#physicalExamination-table-list").datagrid({
		title:message("yly.medicalRecord.list"),
		fitColumns:true,
		toolbar:"#physicalExamination_manager_tool",
		url:'../physicalExamination/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#physicalExaminationDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 580, 
			    cache: false,
			    modal: true,
			    href:'../physicalExamination/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#physicalExaminationDetail').dialog("close");
					}
			    }]
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

	$("#physicalExamination-search-btn").click(function(){
	  var _queryParams = $("#physicalExamination-search-form").serializeJSON();
	  $('#physicalExamination-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#physicalExamination-table-list").datagrid('reload');
	})
	
	 
	 
})
