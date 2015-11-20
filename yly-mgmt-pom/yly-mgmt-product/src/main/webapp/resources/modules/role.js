var role_manager_tool = {
		auth:function(){		
			//授权树形列表
			var _edit_row = $('#role-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
				return false;
			}
			$('#role-dialog-auth').dialog({    
			    title: message("yly.role.auth.manange"),    
			    width: 450,    
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
					      {title:message("yly.role.auth.name"),field:"text",width:180,align:'left',formatter:function(value,row,index){
					    	  return row.text;
					      }},
					      {title:message("yly.role.auth.status"),field:"checked",width:180,align:'center',formatter:function(value,row,index){
					    	  if(row.checked){
		                    	  return "<input type='checkbox' id='role-table-auth-treeId-"+row.id+"' name='authId' data-partentId='"+row._parentId+"' value='"+row.id+"'  checked/>" ; 
		                      }else{
		                    	  return "<input type='checkbox' id='role-table-auth-treeId-"+row.id+"' name='authId' data-partentId='"+row._parentId+"' value='"+row.id+"'  />" ; 
		                      }
					    	   
					      }}
					    ]],
					    onLoadSuccess:function(){
					    	$(":input[name='authId']").click(function(){
					    		var _this = $(this);
					    		var value = _this.val();
					    		var partentId = _this.attr("data-partentId");
//					    		var flagCheck = $("#role-table-auth-treeId-"+value).attr('checked');
					    		var flagCheck = _this.attr('checked');
					    		//console.log(value);
					    		//console.log(partentId);
//					    		console.log(flagCheck);
//					    		console.log($("#role-table-auth-treeId-"+partentId).attr("checked"));
//					    		console.log($('#role-table-auth').treegrid("getChildren",partentId));
					    	//	console.log($("#role-table-auth-treeId-"+value).attr("checked"));
					    		//console.log($('#role-table-auth').treegrid("find",value));
					    		
					    		//方案2
					    		if(partentId == "undefined"){
					    			if(flagCheck){
					    				$("#role-table-auth-treeId-"+value).attr("checked",false);
					    				var list =$('#role-table-auth').treegrid("getChildren",value);
					    				for(var obj in list){
					    					console.log(list[obj].id);
					    					$("#role-table-auth-treeId-"+list[obj].id).prop("checked",false);
					    				}
					    			}else{
					    				$("#role-table-auth-treeId-"+value).attr("checked",true);
					    				var list =$('#role-table-auth').treegrid("getChildren",value);
					    				for(var obj in list){
					    					console.log(list[obj].id);
					    					$("#role-table-auth-treeId-"+list[obj].id).prop("checked",true);
					    				}
					    			}
					    		}else{
					    			if(flagCheck){
					    				console.log("1111111111111");
					    				$("#role-table-auth-treeId-"+value).attr("checked",false);
					    				var list =$('#role-table-auth').treegrid("getChildren",partentId);
					    				for(var obj in list){
//					    					console.log(list[obj].checked);
					    					if(list[obj].checked=="true"){
					    						console.log(list[obj].checked);
					    						$("#role-table-auth-treeId-"+partentId).prop("checked",true);
					    						break;
					    					}else{
					    						console.log(list[obj].checked);
					    						$("#role-table-auth-treeId-"+partentId).prop("checked",false);
					    					}
					    				}
					    			}else{
					    				console.log("22222");
					    				$("#role-table-auth-treeId-"+value).attr("checked",true);
					    				$("#role-table-auth-treeId-"+partentId).prop("checked",true);
					    			}
					    		}
					    		
					    	})
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
			console.log("tttttttttt");
			var _dialog = $('#editRole').dialog({
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
						var validate = $('#editRole_form').form('validate');
						if(validate){
							$.ajax({
								url:"../role/update.jhtml",
								type:"post",
								data:$("#editRole_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#editRole').dialog("close");
									$("#role-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editRole').dialog("close");
					}
			    }]
			});  
			$('#editRole_form').show();
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

