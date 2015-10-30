
$(function(){
	
	$("#tenantUser-table-list").datagrid({
		title:"药品列表",
		fitColumns:true,
		toolbar:"#tenantUser_manager_tool",
		url:'../tenantUser/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#tenantUserDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../tenantUser/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#fixedAssetsDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"姓名",field:"realName",width:100,sortable:true},
		      {title:"性别",field:"gender",width:100,sortable:true},
		      {title:"年龄",field:"age",width:100,sortable:true},
		      {title:"员工编号",field:"staffID",width:100,sortable:true},
		      {title:"员工状态",field:"staffStatus",width:100,sortable:true},
	    	  
		      {title:"所在部门",field:"department",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:"担任职务",field:"position",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:"入职时间",field:"hireDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	tenantUser_manager_tool = {
			add:function(){
				$('#addTenantUser').dialog({
				    title: message("yly.drugsInfo.add"),    
				    width: 700,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addTenantUser_form').form('validate');
							if(validate){
								$.ajax({
									url:"../tenantUser/add.jhtml",
									type:"post",
									data:$("#addTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addTenantUser').dialog("close").form("reset");
											$("#tenantUser_table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addTenantUser').dialog("close").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#addTenantUser_form').show();
				    	$("#tenantUserDepartment").combobox({    
						    valueField:'id',    
						    textField:'name',
						    cache: true,
						    editable : false,
						    url:'../department/findAllDepartments.jhtml'
						});
				    },
				
				});  
			},
			edit:function(){
				var _edit_row = $('#tenantUser-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editTenantUser').dialog({    
				    title: '药品编辑',     
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../tenantUser/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editTenantUser_form').form('validate');
							if(validate){
								$.ajax({
									url:"../tenantUser/update.jhtml",
									type:"post",
									data:$("#editTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在保存中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$.messager.show({
												title:'提示',
												msg:'保存成功',
												timeout:3000,
												showType:'slide'
											});
											$('#editTenantUser').dialog("close");
											$("#tenantUser_table-list").datagrid('reload');
										}else{
											$.messager.alert('保存失败','未知错误','warning');
										}
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editTenantUser').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				var _rows = $('#tenantUser-table-list').datagrid('getSelections');
				if(_rows == null){
					$.messager.alert('警告','请选择要删除的内容');  
				}else{
					var _ids =[];
					for(var i=0; i<_rows.length; i++){
							_ids.push(_rows[i].id);
						}
					if(_ids.length >0){
						$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
							if(r){
								$.ajax({
									url:"../tenantUser/delete.jhtml",
									type:"post",
									traditional: true,
									data:{"ids":_ids},
									beforeSend:function(){
										$.messager.progress({
											text:"正在删除中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$.messager.show({
												title:'提示',
												msg:'操作成功',
												timeout:3000,
												showType:'slide'
											});
											$("#tenantUser-table-list").datagrid('reload');
										}else{
											$.messager.alert('保存失败','未知错误','warning');
										}
									}
								});
							}
						})
					}
					
				}
			}
	};
	$("#search-btn").click(function(){
	  var _queryParams = {
			  drugName:$("#drugName").val(),
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#tenantUser-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantUser-table-list").datagrid('reload');
	})
	
	 
	 
})
