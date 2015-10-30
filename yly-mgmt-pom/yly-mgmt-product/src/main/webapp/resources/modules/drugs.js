
$(function(){
	
	$("#drugs-table-list").datagrid({
		title:"药品列表",
		fitColumns:true,
		toolbar:"#drugs_manager_tool",
		url:'../drugs/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#drugsDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../drugs/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#drugsDetail').dialog("close");
					}
			    }]
			});   
		},
		onClickRow : function (rowIndex, rowData){
			alert('test');
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"药品名称",field:"name",width:100,sortable:true},
		      {title:"别名",field:"alias",width:100,sortable:true},
		      {title:"药品分类",field:"drugCategory",width:100,sortable:true,
	    	  formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.configValue;
		    	  }else{
		    		  return  value;
		    	  }
					
		      	}},
		      {title:"创建时间",field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:"修改时间",field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		]

	});

	drugs_manager_tool = {
			add:function(){
				$('#addDrugs').dialog({
				    title: message("yly.drugsInfo.add"),    
				    width: 700,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addDrugs_form').form('validate');
							if(validate){
								$.ajax({
									url:"../drugs/add.jhtml",
									type:"post",
									data:$("#addDrugs_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addDrugs').dialog("close").form("reset");
											$("#drugs-table-list").datagrid('reload');
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
							 $('#addDrugs').dialog("close").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#addDrugs_form').show();
				    	$("#conventionalUnit").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'UNITS';// 参数
						    }
						});	
				    	$("#minUnit").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'UNITS';// 参数
						    }
						});
				    	$("#drugCategory").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'DRUGSCATEGORY';// 参数
						    }
						});
				    	$("#drugUseMethod").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'DRUGSMETHOD';// 参数
						    }
						});
				    },
				
				});  
				 $('#addDrugs_form').show();
			},
			edit:function(){
				var _edit_row = $('#drugs-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editDrugs').dialog({    
				    title: '药品编辑',     
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../drugs/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editDrugs_form').form('validate');
							if(validate){
								$.ajax({
									url:"../drugs/update.jhtml",
									type:"post",
									data:$("#editDrugs_form").serialize(),
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
											$('#editDrugs').dialog("close");
											$("#drugs-table-list").datagrid('reload');
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
							 $('#editDrugs').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				var _rows = $('#drugs-table-list').datagrid('getSelections');
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
									url:"../drugs/delete.jhtml",
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
											$("#drugs-table-list").datagrid('reload');
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
	  $('#drugs-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#drugs-table-list").datagrid('reload');
	})
	
	 
	 
})
