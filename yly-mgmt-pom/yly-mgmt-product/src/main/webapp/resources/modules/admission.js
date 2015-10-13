$(function(){
	
	$("#admission-table-list").datagrid({
		title:message("yly.elderlyinfo.record"),
		fitColumns:true,
		toolbar:"#admission_manager_tool",
		url:'../admission/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#admissionDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 500, 
			    cache: false,   
			    href:'../admission/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#admissionDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {title:message("yly.elderlyinfo.identifier"),field:"identifier",width:40,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"name",width:40,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.birthday"),field:"birthday",width:40,align:'center',sortable:true},
		      {title:message("yly.common.gender"),field:"gender",width:30,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}}
		   ]
		]

	});
	
	admission_manager_tool = {
			add:function(){		
				$('#addAdmission').dialog({    
				    title: message("yly.admission.add"),    
				    width: 1200,    
				    height: 700,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addAdmissionn_form').form('validate');
							if(validate){
								$.ajax({
									url:"../admission/add.jhtml",
									type:"post",
									data:$("#addAdmission_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addAdmission_form').form('reset');
										$('#addAdmission').dialog("close");
										$("#admission-table-list").datagrid('reload');
										
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
							 $('#addAdmission').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addAdmission_form').show();
				    	$("#personnelCategoryId").combobox({    
				    		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'PERSONNELCATEGORY';
						    },
				    		
						});
				     	$("#evaluatingResultId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'EVALUATINGRESULT';
						    }
						});
				     	$("#nursingLevelId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    }
						});
				    },
				});  
				 $('#addAdmission_form').show();
			},
			edit:function(){
				var _edit_row = $('#admission-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editAdmission').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 500,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../admission/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editAdmission_form').form('validate');
							if(validate){
								$.ajax({
									url:"../admission/update.jhtml",
									type:"post",
									data:$("#editAdmission_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editAdmission').dialog("close");
										$("#admission-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editAdmission').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('admission-table-list','../admission/delete.jhtml');
			}
	}
	$("#admission_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#admission-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#admission-table-list").datagrid('reload');
	})
	
	$("#generateIdentifier_btn").click(function(){
		$.ajax({
			url:"../identifier/generateId.jhtml",
			type:"get",
			data:{
			"idType":"ELDERLYINFO_IDENTIFIER"
			},
			success:function(result,response,status){
				$("#identifier_input").textbox('setValue',result.id);
			}
		});
	})
	 
})