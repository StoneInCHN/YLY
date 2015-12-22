$(function(){
	
	$("#blacklist-table-list").datagrid({
		title:message("yly.blacklist.record"),
		fitColumns:true,
		toolbar:"#blacklist_manager_tool",
		url:'../blackList/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#blacklistDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 500,    
			    height: 510, 
			    cache: false,   
			    href:'../blackList/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#blacklistDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.blacklist.name"),field:"name",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      {title:message("yly.common.insertDate"),field:"blackDate",width:20,align:'center',formatter:function(value,row,index){
		    	  return new Date(value).Format("yyyy-MM-dd");
		      }},
		      {title:message("yly.common.phonenumber"),field:"elderlyPhoneNumber",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.elderlyPhoneNumber;
		      }},
		      {title:message("yly.common.age"),field:"elderlyInfoAge",width:8,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.age;
		      }},
		      {title:message("yly.blacklist.casue"),field:"cause",width:80,align:'center',formatter:function(value,row,index){
		    	  return row.cause;
		      }}
		   ]
		]

	});
	 
	 

	blacklist_manager_tool = {
			add:function(){		
				$('#addBlacklist').dialog({    
				    title: message("yly.blacklist.add"),    
				    width: 370,    
				    height: 370,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addBlacklist_form').form('validate');
							if(validate){
								$.ajax({
									url:"../blackList/add.jhtml",
									type:"post",
									data:$("#addBlacklist_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addBlacklist_form').form('reset');
										$('#addBlacklist').dialog("close");
										$("#blacklist-table-list").datagrid('reload');
										
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
							 $('#addBlacklist').dialog("close");
						}
				    }]
				});  
				 $('#addBlacklist_form').show();
			},
			edit:function(){
				var _edit_row = $('#blacklist-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editBlacklist').dialog({    
				    title: message("yly.common.edit"),     
				    width: 370,    
				    height: 370,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../blackList/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editBlacklist_form').form('validate');
							if(validate){
								$.ajax({
									url:"../blackList/update.jhtml",
									type:"post",
									data:$("#editBlacklist_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editBlacklist').dialog("close");
										$("#blacklist-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editBlacklist').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				var _edit_row = $('#blacklist-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.deleteRow"));  
					return false;
				}
				listRemove('blacklist-table-list','../blackList/delete.jhtml');
			}
	}
	$("#blacklist_search_btn").click(function(){
	  var _queryParams = $("#blacklist_search_form").serializeJSON();
	  $('#blacklist-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#blacklist-table-list").datagrid('reload');
	//隐藏域用于标记上次使用过的查询条件 
	  $("#nameHidden").val($("#name").val());
	  $("#beginDateHidden").val($("#beginDate").val());		
	  $("#endDateHidden").val($("#endDate").val());
	})
	
	$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
	})
	 
})

