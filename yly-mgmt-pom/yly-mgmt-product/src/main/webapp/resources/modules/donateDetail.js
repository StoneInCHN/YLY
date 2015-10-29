
$(function(){
	
	donateDetail_manager_tool = {
			
			add:function(){
				$('#addDonateDetail').dialog({
				    title: "添加捐赠详情",    
				    width: 700,    
				    height: 400,
				    href:'../donateDetail/add.jhtml',
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addDonateDetail_form').form('validate');
							if(validate){
								$.ajax({
									url:"../donateDetail/add.jhtml",
									type:"post",
									data:$("#addDonateDetail_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addDonateDetail').dialog("close").form("reset");
											$("#donateDetail_table_list").datagrid('reload');
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
							 $('#addDonateDetail').dialog("close").form("reset");
						}
				    }],
				    onLoad:function(){
				    	$('#addDonateDetail_form').show();
				    	$('#recordId').val(recordId);
						console.log($('#recordId').val());
				    	$("#donateItemType").combobox({    
						    valueField:'id',    
						    textField:'itemName',
						    cache: true,
						    url:'../donateItemType/findAllDonateItemTypes.jhtml'
						});
				    },
				});  
				 
			},
			edit:function(){
				var _edit_row = $('#donateDetail_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editDonateDetail').dialog({    
				    title: '药品编辑',     
				    width: 700,    
				    height: 400,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../donateDetail/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editDonateDetail_form').form('validate');
							if(validate){
								$.ajax({
									url:"../donateDetail/update.jhtml",
									type:"post",
									data:$("#editDonateDetail_form").serialize(),
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
											$('#editDonateDetail').dialog("close");
											$("#donateDetail_table-list").datagrid('reload');
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
							 $('#editDonateDetail').dialog("close").form("reset");
						}
				    }],
				    onLoad:function(){
				    	$('#addDonateDetail_form').show();
				    	$('#recordId').val(recordId);
						console.log($('#recordId').val());
				    	$("#donateItemType").combobox({    
						    valueField:'id',    
						    textField:'itemName',
						    cache: true,
						    url:'../donateItemType/findAllDonateItemTypes.jhtml'
						});
				    },
				});  
			},
			remove:function(){
				var _rows = $('#donateDetail-table-list').datagrid('getSelections');
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
									url:"../donateDetail/delete.jhtml",
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
											$("#donateDetail-table-list").datagrid('reload');
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

	
	 
	 
})
