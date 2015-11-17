var physicalExaminationItemConfig_manager_tool = {

		add:function(){
			$('#addPhysicalExaminationItemConfig').dialog({
			    title: message("yly.physicalExaminationItemConfig.add"),    
			    width: 250,    
			    height: 270,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addPhysicalExaminationItemConfig_form').form('validate');
						if(validate){
							$.ajax({
								url:"../physicalExaminationItemConfig/add.jhtml",
								type:"post",
								data:$("#addPhysicalExaminationItemConfig_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addPhysicalExaminationItemConfig').dialog("close");
										$('#addPhysicalExaminationItemConfig_form').form('reset');
										$("#physicalExaminationItemConfig-table-list").datagrid('reload');
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
						 $('#addPhysicalExaminationItemConfig').dialog("close");
						 $('#addPhysicalExaminationItemConfig_form').form('reset');
					}
			    }],
			    onOpen:function(){
			    	$('#addPhysicalExaminationItemConfig_form').show();
			    }
			});  
			
		},
		edit:function(){
			var _edit_row = $('#physicalExaminationItemConfig-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("yly.common.select.editRow"));
				return false;
			}
			var _dialog = $('#editPhysicalExaminationItemConfig').dialog({    
				title: message("yly.common.edit"),   
			    width: 250,    
			    height: 270,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../physicalExaminationItemConfig/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editPhysicalExaminationItemConfig_form').form('validate');
						if(validate){
							$.ajax({
								url:"../physicalExaminationItemConfig/update.jhtml",
								type:"post",
								data:$("#editPhysicalExamination_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editPhysicalExaminationItemConfig').dialog("close");
										$('#editPhysicalExaminationItemConfig_form').form('reset');
										$("#physicalExaminationItemConfig-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editPhysicalExaminationItemConfig').dialog("close");
						 $('#editPhysicalExaminationItemConfig_form').form('reset');
					}
			    }],onLoad:function(){
			    	$('#editPhysicalExaminationItemConfig_form').show();
			    },
			});
			$('#editPhysicalExaminationItemConfig_form').show();
		},
		remove:function(){
			listRemove('physicalExaminationItemConfig-table-list','../physicalExaminationItemConfig/delete.jhtml');
		}

	};
$(function(){
	$("#physicalExaminationItemConfig-table-list").datagrid({
		title:message("yly.physicalExaminationItemConfig.list"),
		fitColumns:true,
		toolbar:"#physicalExaminationItemConfig_manager_tool",
		url:'../physicalExaminationItemConfig/list.jhtml',
//		saveUrl: '../physicalExaminationItemConfig/add.jhtml',    
//	    updateUrl: '../physicalExaminationItemConfig/update.jhtml',    
//	    destroyUrl: '../physicalExaminationItemConfig/delete.jhtml',
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#physicalExaminationItemConfigDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 250,    
	    		height: 270,
			    cache: false,
			    modal: true,
			    href:'../physicalExaminationItemConfig/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#physicalExaminationItemConfigDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.physicalExaminationItemConfig.name"),field:"configValue",width:100,sortable:true},
		      {title:message("yly.physicalExaminationItemConfig.isEnable"),field:"isEnable",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return message("yly.common.yes");
		    	  } else{
		    		  return message("yly.common.no");
		    	  }
		      }},
		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		],onClickCell:function(index, field, value){
//			console.log(index);
//			$(this).datagrid('beginEdit', index);
//			var ed = $(this).datagrid('getEditor', {index:index,field:field});
//			$(ed.target).focus();

		}

	});

	$("#physicalExaminationItemConfig-search-btn").click(function(){
	  var _queryParams = $("#physicalExaminationItemConfig-search-form").serializeJSON();
	  $('#physicalExaminationItemConfig-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#physicalExaminationItemConfig-table-list").datagrid('reload');
	})
	
	 
	 
})
