$(function(){
	
	$("#role-table-list").datagrid({
		title:message("yly.role.record"),
		fitColumns:true,
		toolbar:"#role_manager_tool",
		url:'../role/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#roleDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 500,    
			    height: 510, 
			    cache: false,   
			    href:'../role/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#roleDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.role.name"),field:"name",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.name;
		      }},
		      {title:message("yly.role.description"),field:"description",width:80,align:'center',formatter:function(value,row,index){
		    	  return row.description;
		      }}
		   ]
		]

	});
	
	role_manager_tool = {
			auth:function(){		
				//授权树形列表
				var _edit_row = $('#role-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				$('#role-dialog-auth').dialog({    
				    title: message("yly.role.auth.manange"),    
				    width: 550,    
				    height: 500,    
				    closed: false,    
				    cache: false,    
				    modal: true ,
				    onOpen:function(){
				    	$('#role-table-auth').treegrid({    
						    url:'../role/listAuth.jhtml?id='+_edit_row.id,  
						    idField:'id',    
						    treeField:'text', 
						    columns:[[    
						      {title:message("yly.role.auth.name"),field:"text",width:280,align:'center',formatter:function(value,row,index){
						    	  return row.text;
						      }},
						      {title:message("yly.role.auth.status"),field:"checked",width:200,align:'center',formatter:function(value,row,index){
			                      if(row.checked){
			                    	  return "<input type='checkbox' name='authId' value='"+row.id+"' onclick='' checked='checked' />" ; 
			                      }else{
			                    	  return "<input type='checkbox' name='authId' value='"+row.id+"' onclick='' />" ; 
			                      }
						    	   
						      }}
						    ]],
						    onClickCell:function(rowIndex, field, value){
						    	console.log(field);
						    	//父节点
						    	/*if(field.parentId==null && field.checked==true){
						    		List childList=field.children;
						    	}else if(field.parentId==null && field.checked==false){
						    		
						    	}*/
						    	
						    	//
						    	/*$("input:checkbox[name=authId]").on("click",function(){
						    		var selectRow = $('#role-table-auth').treegrid("getSelected");
						    		console.log(selectRow);
						    	});*/
						    }
						});
				    },
					buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							//console.log($('input:checkbox[name=auth_id]:checked'));
								$.ajax({
									url:"../role/addAuth.jhtml?id="+_edit_row.id,
									type:"post",
									
									data:$("input:checkbox[name=authId]:checked").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#role-dialog-auth').dialog("close");
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#role-dialog-auth').dialog("close");
						}
				    }]
				}); 
				
			},
			add:function(){		
				$('#addrole').dialog({    
				    title: message("yly.role.add"),    
				    width: 370,    
				    height: 370,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addrole_form').form('validate');
							if(validate){
								$.ajax({
									url:"../role/add.jhtml",
									type:"post",
									data:$("#addrole_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addrole_form').form('reset');
										$('#addrole').dialog("close");
										$("#role-table-list").datagrid('reload');
										
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
							 $('#addrole').dialog("close");
						}
				    }]
				});  
				 $('#addrole_form').show();
			},
			edit:function(){
				var _edit_row = $('#role-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editrole').dialog({    
				    title: message("yly.common.edit"),     
				    width: 370,    
				    height: 370,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../role/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editrole_form').form('validate');
							if(validate){
								$.ajax({
									url:"../role/update.jhtml",
									type:"post",
									data:$("#editrole_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editrole').dialog("close");
										$("#role-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editrole').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				var _edit_row = $('#role-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.deleteRow"));  
					return false;
				}
				listRemove('role-table-list','../role/delete.jhtml');
			}
	}
	$("#role_search_btn").click(function(){
	  var _queryParams = $("#role_search_form").serializeJSON();
	  $('#role-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#role-table-list").datagrid('reload');
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

