
$(function(){
	
	donateDetail_manager_tool = {
			
			add:function(){
				$('#addDonateDetail').dialog({
				    title: message("yly.donateDetail.add"),    
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
											$("#donateDetail-table-list").datagrid('reload');
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
							 $('#addDonateDetail').dialog("close").form("reset");
						}
				    }],
				    onLoad:function(){
				    	$('#addDonateDetail_form').show();
				    	$('#recordId').val(recordId);
				    	$("#donateItemType").combobox({    
						    valueField:'id',    
						    textField:'itemName',
						    cache: true,
						    readyonly:true,
						    url:'../donateItemType/findAllDonateItemTypes.jhtml'
						});
				    },
				});  
				 
			},
			edit:function(){
				var _edit_row = $('#donateDetail-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editDonateDetail').dialog({    
				    title: message("yly.common.edit"), 
				    width: 700,    
				    height: 400,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../donateDetail/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
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
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editDonateDetail').dialog("close");
											$("#donateDetail_table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
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
				listRemove('donateDetail-table-list','../donateDetail/delete.jhtml');
			}
	};

	
	 
	 
})
