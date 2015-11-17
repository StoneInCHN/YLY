physicalExamItemIndex = 0;
var physicalExamination_manager_tool = {
		searchPhysicalExaminationItem:function(id){
			$('#physicalExaminationItemsList').dialog({    
			    title: message("yly.physicalExaminationItemConfig.search"),    
			    width: 1000,
			    height: 500,
			    modal:true,
			    cache: false,   
			    href:'../physicalExaminationItemConfig/physicalExaminationItemConfig.jhtml',
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#physicalExaminationItemsList').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$("#physicalExaminationItemConfig-table-list").datagrid({
						title:message("yly.physicalExaminationItemConfig.list"),
						fitColumns:true,
						toolbar:"#physicalExaminationItemConfig_manager_tool",
						url:'../physicalExaminationItemConfig/list.jhtml',
						pagination:true,
						loadMsg:message("yly.common.loading"),
						striped:true,
						onDblClickRow : function (rowIndex, rowData){
							 $("#"+id+"ID").val(rowData.id);
							 console.log( $("#"+id));
							 console.log(rowData.configValue);
				    		 $("#"+id).val(rowData.configValue);
				    		 
				    		 $('#physicalExaminationItemsList').dialog("close");   
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
						]

					});
			    }
			});
			
		},
			add:function(){
				$('#addPhysicalExamination').dialog({
				    title: message("yly.physicalExamination.add"),    
				    width: 800,    
				    height: 580,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addPhysicalExamination_form').form('validate');
							if(validate){
								$.ajax({
									url:"../physicalExamination/add.jhtml",
									type:"post",
									data:$("#addPhysicalExamination_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addPhysicalExamination').dialog("close");
											$('#addPhysicalExamination_form').form('reset');
											$("#physicalExamination-table-list").datagrid('reload');
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
							 $('#addPhysicalExamination').dialog("close");
							 $('#addPhysicalExamination_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addPhysicalExamination_form').show();
				    }
				});  
				
			},
			
			edit:function(){
				var _edit_row = $('#physicalExamination-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editPhysicalExamination').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../physicalExamination/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editPhysicalExamination_form').form('validate');
							if(validate){
								$.ajax({
									url:"../physicalExamination/update.jhtml",
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
											$('#editPhysicalExamination').dialog("close");
											$('#editPhysicalExamination_form').form('reset');
											$("#physicalExamination-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editPhysicalExamination').dialog("close");
							 $('#editPhysicalExamination_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editPhysicalExamination_form').show();
				    },
				});
				$('#editPhysicalExamination_form').show();
			},
			addExamItemHtml:function(){
				
			var examItemMapHtml='<tr id="physicalExamItem'+physicalExamItemIndex+'">\
				<th>体检项名称:<\/th>\
				<td>\
					<input class="easyui-textbox input_text_line " id="physicalExamItemsConfig'+physicalExamItemIndex+'" type="text" validtype="length[0,15]" style="width:60px;"\/>\
					<input type="hidden" id="physicalExamItemsConfig'+physicalExamItemIndex+'ID" name="physicalExaminationItems['+physicalExamItemIndex+'].physicalExaminationItem.id" \/>\
					<a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="physicalExamination_manager_tool.searchPhysicalExaminationItem(\'physicalExamItemsConfig'+physicalExamItemIndex+'\')" iconCls="icon-search" plain=true">test</a>\
				<\/td>\
				<th>体检值:<\/th>\
				<td>\
					<input class="easyui-textbox input_text_line " type="text" name="physicalExaminationItems['+physicalExamItemIndex+'].physicalExaminationItemValue" style="width:15px;"\/>\
				<\/td>\
				<\/tr>';
				
				$("#addPhysicalExamination-table-list").append(examItemMapHtml);
				physicalExamItemIndex++;
			},
			remove:function(){
				listRemove('physicalExamination-table-list','../physicalExamination/delete.jhtml');
			}
	};
$(function(){
	$("#physicalExamination-table-list").datagrid({
		title:message("yly.physicalExamination.list"),
		fitColumns:true,
		toolbar:"#physicalExamination_manager_tool",
		url:'../physicalExamination/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#physicalExaminationDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 580, 
			    cache: false,
			    modal: true,
			    href:'../physicalExamination/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#physicalExaminationDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.name"),field:"elderlyInfo",width:100,sortable:true,formatter:function(value,row,index){
			    	if(value != null) { 
			    	  return value.name;
			      	}else{
			      		return value;
			      	}
			      }
		      },
		      {title:message("yly.physicalExamination.operator"),field:"operator",width:100,sortable:true,formatter:function(value,row,index){
			    	if(value != null) { 
			    	  return value.realName;
			      	}else{
			      		return value;
			      	}
			      }
		      },
		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	$("#physicalExamination-search-btn").click(function(){
	  var _queryParams = $("#physicalExamination-search-form").serializeJSON();
	  $('#physicalExamination-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#physicalExamination-table-list").datagrid('reload');
	})
	
	 
	 
})