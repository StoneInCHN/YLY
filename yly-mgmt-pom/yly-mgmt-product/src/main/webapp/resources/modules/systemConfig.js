var systemConfig_manager_tool = {
		add:function(){		
			$('#addSystemConfig').dialog({    
			    title: message("yly.systemConfig.add"),    
			    width: 300,    
			    height: 180,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addSystemConfig_form').form('validate');
						if(validate){
							$.ajax({
								url:"../systemConfig/add.jhtml",
								type:"post",
								data:$("#addSystemConfig_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#addSystemConfig_form').form('reset');
									$('#addSystemConfig').dialog("close");
									$("#systemConfig_table_list").datagrid('reload');
							
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
						 $('#addSystemConfig').dialog("close");
					}
			    }]
			});  
			 $('#addSystemConfig_form').show();
		},
			edit:function(){
				var _edit_row = $('#systemConfig_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');      
					return false;
				}
				var _dialog = $('#editSystemConfig').dialog({    
				    title: message("yly.systemConfig.edit"),     
				    width: 300,    
				    height: 180,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../systemConfig/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editSystemConfig_form').form('validate');
							if(validate){
								$.ajax({
									url:"../systemConfig/update.jhtml",
									type:"post",
									data:$("#editSystemConfig_form").serialize(),
									beforeSend:function(){
										$.messager.progress({text:message("yly.common.saving")});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editSystemConfig').dialog("close");
											$("#systemConfig_table_list").datagrid('reload');
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
							 $('#editSystemConfig').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('systemConfig_table_list','../systemConfig/delete.jhtml');
			}
	}
$(function(){
	$("#systemConfig_table_list").datagrid({
		title:message("yly.systemConfig.list"),
		fitColumns:true,
		fit:true,
		toolbar:"#systemConfig_manager_tool",
		url:'../systemConfig/getListConfigValueByKey.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.systemConfig.configValue"),field:"configValue",width:100,align:'center',sortable:true},
		      {title:message("yly.systemConfig.isEnabled"),field:"isEnabled",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == true){
		    	  		return  message("yly.common.yes");
		    	  	}else{
		    	  		return  message("yly.common.no");
		    	  	}
		      }},
		      {title:message("yly.common.createDate"),field:"createDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		],
		onLoadSuccess:function(data){
           $(".tips-span").tooltip({
        	   position: 'top',
               onShow: function(){
                   $(this).tooltip('tip').css({ 
                       width:'300'
                   });
               }
           });
        }

	});

	$('#configKey_Tree').tree({    
	    url:'../systemConfig/getAllConfigKey.jhtml', 
	    queryParams:{
	    	isSelect:false
		},
	    animate:true,
	    lines:true,
		onClick : function(node){
			$('#systemConfig_table_list').datagrid('load',{configKey:node.id});
			$("#addSystemConfig_form_configKey").val(node.id);
		}
	}); 
	
})