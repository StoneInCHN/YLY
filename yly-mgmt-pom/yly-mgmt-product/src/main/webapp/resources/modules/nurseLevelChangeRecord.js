var nurseChange_manager_tool = {
			add:function(){
				$('#add_nurseChange').dialog({    
				    title: message("yly.nurseChange.addNurseChange"),    
				    width: 650,    
				    height: 400,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addNurseChange_form').form('validate');
							console.info($("#addNurseChange_form").serialize());
							if(validate && checkNurseLevel("add")){								
								$.ajax({
									url:"../nurseLevelChangeRecord/add.jhtml",
									type:"post",
									data:$("#addNurseChange_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#add_nurseChange').dialog("close");
										$("#nurseChange-table-list").datagrid('reload');
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
							 $('#add_nurseChange').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addNurseChange_form').show();
				     	$("#addNurseChange_oldNurseLevel").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    }
						});
				     	$("#addNurseChange_newNurseLevel").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    }
						});
				    },
				    onClose:function(){
				    	 $('#addNurseChange_form').form('reset');
				    }
				});
			},
			remove:function(){
				listRemove('nurseChange-table-list','../nurseLevelChangeRecord/delete.jhtml');
			},
			edit:function(){
				var _edit_row = $('#nurseChange-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#edit_nurseChange').dialog({    
				    title: message("yly.common.edit"),     
				    width: 650,    
				    height: 500, 
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../nurseLevelChangeRecord/detail.jhtml?id='+_edit_row.id+'&handle=edit',
				    onLoad:function(){
				    	$("#editNurseChange_oldNurseLevel").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    },
						    value:$("#editNurseChange_oldNurseLevel").val()
						});
				    	$("#editNurseChange_newNurseLevel").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    },
						    value:$("#editNurseChange_newNurseLevel").val()
						});
				    	$('#editNurseChange_newNurseLevel').combobox({
				    		onChange:function(value){
				    			checkNurseLevel("edit");
				    		}
				    	});
				    },
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editNurseChange_form').form('validate');
							if(validate && checkNurseLevel("edit")){
								$.ajax({
									url:"../nurseLevelChangeRecord/update.jhtml",
									type:"post",
									data:$("#editNurseChange_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#edit_nurseChange').dialog("close");
										$("#nurseChange-table-list").datagrid('reload');
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
							 $('#edit_nurseChange').dialog("close");
						}
				    }]
				});  
			}
	}
//护理变更
$(function(){
	$("#nurseChange-table-list").datagrid({
		title:message("yly.nurseChange.list"),
		fitColumns:true,
		toolbar:"#nurseChange_manager_tool",
		url:'../nurseLevelChangeRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		checkOnSelect:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#view_nurseChange').dialog({    
			    title: message("yly.common.detail"),    
			    width: 650,    
			    height: 400,
			    cache: false,
			    modal: true,
			    href:'../nurseLevelChangeRecord/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#view_nurseChange').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:"15%",align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }}, 
		      {title:message("yly.nurseChange.newNurseLevel"),field:"newNurseLevel",width:"15%",align:'center',formatter:function(value,row,index){
		    	  if(value != null && row.nurseChangeStatus == "ENABLE"){
		    		  return "<strong><font color=#7CDD7C>"+value.configValue+"</font></strong>";
		    	  }else{
		    		  return "<strong><font color=#999999>"+value.configValue+"</font></strong>";
		    	  }
		      }},
		      {title:message("yly.nurseChange.changeDate"),field:"changeDate",width:"15%",align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
			  }},
		      {title:message("yly.nurseChange.nurseChangeStatus"),field:"nurseChangeStatus",width:"15%",align:'center',formatter:function(value,row,index){
		    		if(value == "ENABLE"){
		    	  		return  "启用";
		    	  	}
		    	  	if(value == "DISABLE"){
		    	  		return  "已废弃";
		    	  	}
		      }},
			  {title:message("yly.common.remark"),field:"remark",width:"20%",align:'center',formatter:function(value,row,index){
					return  formatLongString(value,50);
			  }},
		      {title:message("yly.nurseChange.oldNurseLevel"),field:"oldNurseLevel",width:"15%",align:'center',formatter:function(value,row,index){
		    	  console.info(value);
		    	  if(value != null){
		    		  return "<font color=#bbbbbb>"+value.configValue+"</font>";
		    	  }
		      }}
		   ]
		]
	});
	$("#nurseChange_search_btn").click(function(){
		  var _queryParams = $("#nurseChange_search_form").serializeJSON();
		  $('#nurseChange-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#nurseChange-table-list").datagrid('reload');
		})
});
//老人查询
$(function(){
	$("#nurseChange_elderlySearch-table-list").datagrid({
		url:'../elderlyInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		checkOnSelect:true,
		onSelect:function(rowIndex,rowData){
			var selected_rows = $('#nurseChange_elderlySearch-table-list').datagrid('getSelections');
			if(selected_rows.length > 1){//取消其他行的选择，模拟singleSelect属性，因为singleSelect属性和checkOnSelect属性冲突，不能直接引用
				for(var i=0;i<selected_rows.length;i++){
					if(rowData.id != selected_rows[i].id){
						var rowIndex=$('#nurseChange_elderlySearch-table-list').datagrid('getRowIndex',selected_rows[i]);
						$("#nurseChange_elderlySearch-table-list").datagrid("unselectRow", rowIndex);
					}
				}
			}
			//选中一个老人，右侧的护理员安排根据老人过滤
			$('#nurseChange_elderlyIDSearch').val(rowData.id);
			  var _queryParams = $("#nurseChange_search_form").serializeJSON();
			  $('#nurseChange-table-list').datagrid('options').queryParams = _queryParams;  
			  $("#nurseChange-table-list").datagrid('reload');
		},
		onUnselect:function(rowIndex,rowData){
			var selected_rows = $('#nurseChange_elderlySearch-table-list').datagrid('getSelections');
			if(selected_rows.length == 0){//取消选中当前行
				//取消选中一个老人，还原右侧的护理员安排根据老人过滤
				$('#nurseChange_elderlyIDSearch').val(null);
				  var _queryParams = $("#nurseChange_search_form").serializeJSON();
				  $('#nurseChange-table-list').datagrid('options').queryParams = _queryParams;  
				  $("#nurseChange-table-list").datagrid('reload');
			}
		},
		onDblClickRow : function (rowIndex, rowData){
			$('#nurseChange_view_elderlyInfo').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,
			    modal: true,
			    href:'../elderlyInfo/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#nurseChange_view_elderlyInfo').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
			{field : 'ck',checkbox : true},
			{title : message("yly.elderlyinfo.identifier"),field : "identifier",width :"30%",align : 'center',sortable : true},
			{title : message("yly.common.elderlyname"),field : "name",width :"30%",align : 'center',sortable : true},		
			{title : message("yly.elderly.status"),field : "elderlyStatus",width :"30%",align : 'center',sortable : true,formatter : function(value, row,index) {
					if (value == "IN_NURSING_HOME") {
						return message("yly.elderly.status.in_nursing_home");
					}
					if (value == "OUT_NURSING_HOME") {
						return message("yly.elderly.status.out_nursing_home");
					}
					if (value == "IN_PROGRESS_CHECKIN") {
						return message("yly.elderly.status.in_progress_checkin");
					}
					if (value == "IN_PROGRESS_CHECKOUT") {
						return message("yly.elderly.status.in_progress_checkout");
					}
					if (value == "DEAD") {
						return message("yly.elderly.status.dead");
					}
				}
			} 
		]
	]
});
	$("#nurseChange_elderlyInfo_search_btn").click(function(){
		  var _queryParams = $("#nurseChange_elderlyInfo_search_form").serializeJSON();
		  $('#nurseChange_elderlySearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#nurseChange_elderlySearch-table-list").datagrid('reload');			
		});
});
$(function(){
	$('#addNurseChange_newNurseLevel').combobox({
		onChange:function(value){
			checkNurseLevel("add");
		}
	});
});
function checkNurseLevel(type){
	var oldNurseLevelValue = $('#'+type+'NurseChange_oldNurseLevel').combobox("getValue");
	var newNurseLevelValue = $('#'+type+'NurseChange_newNurseLevel').combobox("getValue");
	if(oldNurseLevelValue == newNurseLevelValue){
		$.messager.alert(message("yly.common.prompt"),message("变更前等级不能等于变更后等级"), 'warning');
		$('#'+type+'NurseChange_newNurseLevel').combobox("setValue",null);
		return false;
	}else{
		return true;
	}
	
}