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
			    width: 700,    
			    height: 500, 
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
		      {title:message("yly.blacklist.name"),field:"name",width:40,sortable:true},
		      {title:message("yly.common.idcard"),field:"idCard",width:80,align:'center',sortable:true},
		      {title:message("yly.common.phonenumber"),field:"contactPhone",width:70,align:'center',sortable:true},
		      {title:message("yly.common.age"),field:"age",width:20,align:'center',sortable:true},
		      {title:message("yly.blacklist.geracomium"),field:"geracomium",width:80,sortable:true},
		      {title:message("yly.common.gender"),field:"gender",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
			  {title:message("yly.elderlyInfo.residentialAddress"),field:"residentialAddress",width:40,align:'center',sortable:true},
		      {title:message("yly.blacklist.casue"),field:"cause",width:40,align:'center',sortable:true}
		   ]
		]

	});
	 
	 

	blacklist_manager_tool = {
			add:function(){		
				$('#addBlacklist').dialog({    
				    title: message("yly.blacklist.add"),    
				    width: 700,    
				    height: 500,
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
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editBlacklist').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 500,    
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
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				listRemove('blacklist-table-list','../blackList/delete.jhtml');
			}
	}
	$("#blacklist_search_btn").click(function(){
	  var _queryParams = {
			  blackListName:$("#blackListName").val(),
			  beginDate:$("#blacklist_search_btn #beginDate").val(),
			  endDate:$("#blacklist_search_btn #endDate").val()
	  }
	  $('#blacklist-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#blacklist-table-list").datagrid('reload');
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

