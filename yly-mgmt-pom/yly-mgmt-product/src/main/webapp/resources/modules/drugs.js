
$(function(){
	
	$("#drugs-table-list").datagrid({
		title:message("yly.drugsInfo.list"),
		fitColumns:true,
		toolbar:"#drugs_manager_tool",
		url:'../drugs/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#drugsDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 680, 
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
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.drugsInfo.name"),field:"name",width:100,sortable:true},
		      {title:message("yly.drugsInfo.alias"),field:"alias",width:100,sortable:true},
		      {title:message("yly.drugsInfo.drugCategory"),field:"drugCategory",width:100,sortable:true,
	    	  formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.configValue;
		    	  }else{
		    		  return  value;
		    	  }
					
		      	}},
		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
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
				    height: 680,
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
						text:message("yly.common.cancel"),
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
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editDrugs').dialog({    
					title: message("yly.common.edit"),     
				    width: 700,    
				    height: 680,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../drugs/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
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
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editDrugs').dialog("close");
										$("#drugs-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editDrugs').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('drugs-table-list','../drugs/delete.jhtml');
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
