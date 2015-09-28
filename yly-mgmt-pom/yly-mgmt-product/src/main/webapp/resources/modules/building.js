 var building_manager_tool = {
			add:function(){		
				$('#addBulid').dialog({    
				    title: '添加楼宇',    
				    width: 380,    
				    height: 270,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addBulid_form').form('validate');
							if(validate){
								$.ajax({
									url:"../building/save.jhtml",
									type:"post",
									data:$("#addBulid_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在添加中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addBulid').dialog("close");
										$("#building-table-list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addBulid').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addBulid_form').show();
				    },
				    onClose:function(){
				    	 $('#addBulid_form').form('reset');
				    }
				});
			},
			edit:function(){
				var _edit_row = $('#building-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editBuild').dialog({    
				    title: '楼宇编辑',     
				    width: 400,    
				    height: 270,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../building/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editBuild_form').form('validate');
							if(validate){
								$.ajax({
									url:"../building/update.jhtml",
									type:"post",
									data:$("#editBuild_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在保存中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editBuild').dialog("close");
										$("#building-table-list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editBuild').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('building-table-list','../building/delete.jhtml');
			}
	}
	
	
	$(function(){
		$("#building-table-list").datagrid({
			title:"楼宇列表",
			fitColumns:true,
			toolbar:"#building_manager_tool",
			url:'../building/list.jhtml',  
			pagination:true,
			loadMsg:"加载中......",
			striped:true,
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:"楼宇名称",field:"buildingName",width:100,sortable:true},
			      {title:"描述",field:"description",width:100,sortable:true},
			      {title:"创建时间",field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:"修改时间",field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			]
	
		});
})
	