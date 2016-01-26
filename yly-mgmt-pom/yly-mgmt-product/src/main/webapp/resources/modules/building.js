 var building_manager_tool = {
			add:function(){		
				$('#addBulid').dialog({    
				    title: message("yly.bulding.add"),    
				    width: 380,    
				    height: 270,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
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
											text:message("yly.common.saving")
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
						text:message("yly.common.cancel"),
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
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#editBuild').dialog({    
				    title: message("yly.bulding.edit"),     
				    width: 400,    
				    height: 270,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../building/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
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
											text:message("yly.common.saving")
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
						text:message("yly.common.cancel"),
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
			title:message("yly.bulding.list"),
			fitColumns:true,
			toolbar:"#building_manager_tool",
			url:'../building/list.jhtml',  
			pagination:true,
			fit:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("yly.bulding.buildingName"),field:"buildingName",width:100,sortable:true},
			      {title:message("yly.bulding.description"),field:"description",width:100,sortable:true},
			      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			]
	
		});
})
	