
	$(function(){
		$("#nurseSchedule-table-list").datagrid({
			title:message("yly.nurseSchedule.list"),
			fitColumns:true,
			url:'../nurseSchedule/list.jhtml',  
			pagination:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:message("yly.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addNurseSchedule").dialog({    
					    title:message("yly.nurseSchedule.add"),   
					    width: 350,    
					    height: 300,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addNurseSchedule_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nurseSchedule/add.jhtml",
										type:"post",
										data:$("#addNurseSchedule_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addNurseSchedule_form').form('reset');
											$('#addNurseSchedule').dialog("close");
											$("#nurseSchedule-table-list").datagrid('reload');
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
								 $('#addNurseSchedule').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addNurseSchedule_form').show();
						    	$("#addNurseSchedule_form_dutyType").combobox({    
						    	    url:'../nurseSchedule/getAllDutyType.jhtml',  
						    	    method:"get",
						    	    valueField:'id',    
						    	    textField:'text'   
						    	});  
						    	$('#addNurseSchedule_form_dutyStaff_btn').linkbutton({    
						    	    iconCls: 'icon-search',
						    	    onClick:function(){
						    	    	searchTenantUser("addNurseSchedule_form_dutyStaff");
						    	    }
						    	}); 
						},
						onClose:function(){
						    	$('#addNurseSchedule_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("yly.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#nurseSchedule-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
						return false;
					}
					$("#editNurseSchedule").dialog({
						width:350,
						height:350,
						iconCls:'icon-mini-edit',
						title:message("yly.nurseSchedule.edit"),
						href:'../nurseSchedule/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editNurseSchedule_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nurseSchedule/update.jhtml",
										type:"post",
										data:$("#editNurseSchedule_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editNurseSchedule_form').form('reset');
											$('#editNurseSchedule').dialog("close");
											$("#nurseSchedule-table-list").datagrid('reload');
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
								 $('#editNurseSchedule').dialog("close");
							}
						}],
						onLoad:function(){
							$("#editNurseSchedule_form_dutyType").combobox({    
					    	    url:'../nurseSchedule/getAllDutyType.jhtml',  
					    	    method:"get",
					    	    valueField:'id',    
					    	    textField:'text'   
					    	});
							$("#editNurseSchedule_form_dutyType").combobox("setValue",$("#editNurseSchedule_form_dutyType").attr("data-value"));
					    	$('#editNurseSchedule_form_dutyStaff_btn').linkbutton({    
					    	    iconCls: 'icon-search',
					    	    onClick:function(){
					    	    	searchTenantUser("editNurseSchedule_form_dutyStaff");
					    	    }
					    	}); 
						}
					})
				}
			},'-',{
				text:message("yly.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#nurseSchedule-table-list").treegrid('getSelections');
					if (_rows.length == 0) {
						$.messager.alert(message("yly.common.prompt"), message("yly.common.select.deleteRow"),'warning');
					} else {
						var _ids = [];
						for (var i = 0; i < _rows.length; i++) {
							_ids.push(_rows[i].id);
						}
						if (_ids.length > 0) {
							$.messager.confirm(message("yly.common.confirm"), message("yly.common.delete.confirm"), function(r) {
								if (r) {
									$.ajax({
										url : "../nurseSchedule/delete.jhtml",
										type : "post",
										traditional : true,
										data : {
											"ids" : _ids
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
												$("#nurseSchedule-table-list").treegrid('reload');
											} else {
												alertErrorMsg();
											}
										}
									});
								}
							})
						}

					}
				}
			}],
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("yly.nurseSchedule.dutyStartTime"),field:"dutyStartTime",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.nurseSchedule.dutyEndTime"),field:"dutyEndTime",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.nurseSchedule.dutyType"),field:"dutyType",width:100,sortable:true,formatter: function(value,row,index){
						if(value &&value.dutyName){
							return value.dutyName;
						}
					}},
			      {title:message("yly.nurseSchedule.dutyStaff"),field:"dutyStaff",width:100,sortable:true},
			      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			]
	
		});
})
	