
$(function(){
	$("#assetsDepartment-search").combobox({    
	    valueField:'id',    
	    textField:'name',
	    cache: true,
	    url:'../department/findAllDepartments.jhtml'
	});
	$("#fixedAssets-table-list").datagrid({
		title:message("yly.fixedAssets.list"),
		fitColumns:true,
		toolbar:"#fixedAssets_manager_tool",
		url:'../fixedAssets/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#fixedAssetsDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../fixedAssets/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#fixedAssetsDetail').dialog("close");
					}
			    }],onOpen:function(){
			    	$('#editFixedAssets_form').show();
			    	$("#department").combobox({    
					    valueField:'id',    
					    textField:'name',
					    cache: true,
					    readonly: true,
					    url:'../department/findAllDepartments.jhtml'
					});
			    },
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.fixedAssets.assetNo"),field:"assetNo",width:100,sortable:true},
		      {title:message("yly.fixedAssets.assetName"),field:"assetName",width:100,sortable:true},
		      {title:message("yly.fixedAssets.department"),field:"department",width:100,sortable:true,
	    	  formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:message("yly.fixedAssets.assetCount"),field:"assetCount",width:100,sortable:true},
		      
		      {title:message("yly.fixedAssets.assetTime"),field:"assetTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	fixedAssets_manager_tool = {
			add:function(){
				$('#addFixedAssets').dialog({
				    title: message("yly.fixedAssets.add"),    
				    width: 700,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addFixedAssets_form').form('validate');
							if(validate){
								$.ajax({
									url:"../fixedAssets/add.jhtml",
									type:"post",
									data:$("#addFixedAssets_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addFixedAssets').dialog("close").form("reset");
											$("#fixedAssets-table-list").datagrid('reload');
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
							 $('#addFixedAssets').dialog("close").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#addFixedAssets_form').show();
				    	$("#assetsDepartment-add").combobox({    
						    valueField:'id',    
						    textField:'name',
						    cache: true,
						    url:'../department/findAllDepartments.jhtml'
						});
				    },
				
				});  
				 $('#addFixedAssets_form').show();
			},
			edit:function(){
				var _edit_row = $('#fixedAssets-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editFixedAssets').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../fixedAssets/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editFixedAssets_form').form('validate');
							if(validate){
								$.ajax({
									url:"../fixedAssets/update.jhtml",
									type:"post",
									data:$("#editFixedAssets_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editFixedAssets').dialog("close");
											$("#fixedAssets-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editFixedAssets').dialog("close").form("reset");
						}
				    }],onLoad:function(){
				    	$('#editFixedAssets_form').show();
				    	$("#assetsDepartment-edit").combobox({    
						    valueField:'id',    
						    textField:'name',
						    cache: true,
						    url:'../department/findAllDepartments.jhtml',
						    	onLoadSuccess:function(){
							    	$("#assetsDepartment-edit").combobox("setValue",$("#assetsDepartment-edit").attr("data-value"))
							    }
						});
				    },
				});
				$('#editFixedAssets_form').show();
			},
			remove:function(){
				listRemove('fixedAssets-table-list','../fixedAssets/delete.jhtml');
			}
	};
	$("#fixedAssets-search-btn").click(function(){
	  var _queryParams = $("#fixedAssets-search-form").serializeJSON();
	  $('#fixedAssets-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#fixedAssets-table-list").datagrid('reload');
	})
	
	 
	 
})
