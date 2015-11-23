var physicalExamItemIndex = 0;
var physicalExamination_manager_tool = {
		searchPhysicalExaminationItem:function(id){
			$('#physicalExaminationItemsList').dialog({    
			    title: message("yly.physicalExaminationItemConfig.search"),    
			    width: 1000,
			    height: 500,
			    modal:true,
			    cache: false,   
			    href:'../physicalExaminationItemConfig/searchItems.jhtml',
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#physicalExaminationItemsList').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$("#physicalExaminationItemsList-table-list").datagrid({
						title:message("yly.physicalExaminationItemConfig.list"),
						fitColumns:true,
						toolbar:"#physicalExaminationItemConfig_manager_tool",
						url:'../physicalExaminationItemConfig/searchByConfigKey.jhtml',
						pagination:true,
						loadMsg:message("yly.common.loading"),
						striped:true,
						onDblClickRow : function (rowIndex, rowData){
							 $("#"+id+"ID").val(rowData.id);
							 console.log( $("#"+id));
							 console.log(rowData.configValue);
							 $("#"+id).textbox("setValue",rowData.configValue);
				    		 
				    		 $('#physicalExaminationItemsList').dialog("close");   
						},
						onBeforeLoad : function(param) {
					        param.configKey = 'PHYSICALEXAMITEM';// 参数
					    },
						columns:[
						   [
						      {field:'ck',checkbox:true},
						      {title:message("yly.physicalExaminationItemConfig.name"),field:"configValue",width:100,sortable:true},
						      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
									return new Date(value).Format("yyyy-MM-dd");
								}
						      },
						   ]
						]
					    
					});
			    	$("#physicalExaminationItemList-search-btn").click(function(){
					  	  var _queryParams = $("#physicalExaminationItemsList-search-form").serializeJSON();
					  	  $('#physicalExaminationItemsList-table-list').datagrid('options').queryParams = _queryParams;  
					  	  $("#physicalExaminationItemsList-table-list").datagrid('reload');
					  	})
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
											$('#addPhysicalExamination_form').form('clear');
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
							 $('#addPhysicalExamination_form').form('clear');
							 
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
											$('#editPhysicalExamination_form').form('clear');
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
							 $('#editPhysicalExamination_form').form('clear');
						}
				    }],onLoad:function(){
				    	$('#editPhysicalExamination_form').show();
				    },
				});
				$('#editPhysicalExamination_form').show();
			},
			addExamItemHtml : function(operate) {
				if ($("#physicalExamItemSize").val()) {
					physicalExamItemIndex = $("#physicalExamItemSize").val();
				}
				console.log('index='+physicalExamItemIndex);
				var examItemMapHtml = '<tr id="physicalExamItem'
						+ physicalExamItemIndex
						+ '">\
						<th>体检项名称:<\/th>\
						<td>\
							<input class="easyui-textbox input_text_line " id="physicalExamItemsConfig'
						+ physicalExamItemIndex
						+ '" type="text" validtype="length[0,15]" style="width:60px;"\/>\
							<input type="hidden" id="physicalExamItemsConfig'
						+ physicalExamItemIndex
						+ 'ID" name="physicalExaminationItems['
						+ physicalExamItemIndex
						+ '].physicalExaminationItem.id" \/>\
							<a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="physicalExamination_manager_tool.searchPhysicalExaminationItem(\'physicalExamItemsConfig'
						+ physicalExamItemIndex
						+ '\')" iconCls="icon-search" plain=true"></a>\
						<\/td>\
						<th>体检值:<\/th>\
						<td>\
							<input class="easyui-textbox input_text_line " type="text" name="physicalExaminationItems['
						+ physicalExamItemIndex
						+ '].physicalExaminationItemValue" style="width:150px;"\/>\
						<\/td>\
						<\/tr>';
				if(operate == "add"){
					  var targetObj =$("#addPhysicalExaminationItem-table-list").append(examItemMapHtml);
					  $.parser.parse(targetObj);
				}else if (operate == "edit"){
					 var targetObj = $("#editPhysicalExaminationItem-table-list").append(examItemMapHtml);
					$.parser.parse(targetObj);
				}
				physicalExamItemIndex++;
			},
			remove:function(){
				listRemove('physicalExamination-table-list','../physicalExamination/delete.jhtml');
			},
			editRemove:function(id){
				$.messager.confirm(message("yly.common.confirm"), message("yly.common.delete.confirm"), function(r) {
					if (r) {
						$.ajax({
							url : "../physicalExaminationItems/delete.jhtml",
							type : "post",
							traditional : true,
							data : {
								"id" : id
							},
							beforeSend : function() {
								$.messager.progress({
									text : message("yly.common.progress")
								});
							},
							success : function(result, response, status) {
								$.messager.progress('close');
								var resultMsg = result.content;
								if (response == "success") {
									showSuccessMsg(resultMsg);
									$("#physicalExaminationItem"+id).remove();
								} else {
									alertErrorMsg();
								}
							}
						});
					}
				})
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
