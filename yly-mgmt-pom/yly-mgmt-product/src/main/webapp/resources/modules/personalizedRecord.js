
	$(function(){
		$("#personalizedRecord-table-list").datagrid({
			title:message("yly.personalizedRecord.list"),
			fitColumns:true,
			fit:true,
			url:'../personalizedRecord/list.jhtml',  
			pagination:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:message("yly.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addPersonalizedRecord").dialog({    
					    title:message("yly.personalizedRecord.add"),   
					    width: 350,    
					    height: 450,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addPersonalizedRecord_form').form('validate');
								if(validate){
									$.ajax({
										url:"../personalizedRecord/add.jhtml",
										type:"post",
										data:$("#addPersonalizedRecord_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addPersonalizedRecord_form').form('reset');
											$('#addPersonalizedRecord').dialog("close");
											$("#personalizedRecord-table-list").datagrid('reload');
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
								 $('#addPersonalizedRecord').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addPersonalizedRecord_form').show();
						    	$("#addPersonalizedRecord_form_personalized").combobox({    
								    valueField:'id',    
								    textField:'text',
								    cache: true,
								    method:"GET",
								    prompt:message("yly.common.please.select"),
								    url:'../personalizedChargeConfig/findAll.jhtml'
								});
						},
						onClose:function(){
						    	$('#addPersonalizedRecord_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("yly.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#personalizedRecord-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
						return false;
					}
					$("#editPersonalizedRecord").dialog({
						width:350,
						height:350,
						iconCls:'icon-mini-edit',
						title:message("yly.nursePlan.edit"),
						href:'../personalizedRecord/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editPersonalizedRecord_form').form('validate');
								if(validate){
									$.ajax({
										url:"../personalizedRecord/update.jhtml",
										type:"post",
										data:$("#editPersonalizedRecord_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											debugger;
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editPersonalizedRecord').dialog("close");
											$("#personalizedRecord-table-list").datagrid('reload');
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
								 $('#editPersonalizedRecord').dialog("close");
							}
						}],
						onLoad:function(){
							$("#editPersonalizedRecord_form_personalized").combobox({    
							    valueField:'id',    
							    textField:'text',
							    cache: true,
							    method:"GET",
							    prompt:message("yly.common.please.select"),
							    url:'../personalizedChargeConfig/findAll.jhtml'
							});
							$('#editPersonalizedRecord_form_personalized').combobox('setValue', $("#editPersonalizedRecord_form_personalizedID").val());
						},
					})
				}
			},'-',{
				text:message("yly.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#personalizedRecord-table-list").treegrid('getSelections');
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
										url : "../personalizedRecord/delete.jhtml",
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
												$("#personalizedRecord-table-list").treegrid('reload');
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
			      {title:message("yly.personalizedRecord.elderlyInfo"),field:"elderlyInfo",width:100,sortable:true,formatter: function(value,row,index){
						if(value && value.name){
							return value.name;
						}else{
							
						}
			      	}},
			       {title:message("yly.personalizedRecord.personalizedNurse"),field:"personalizedNurse",width:100,sortable:true,formatter: function(value,row,index){
			    	   return value;
			      	}},
			      {title:message("yly.personalizedRecord.serviceTime"),field:"serviceTime",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.personalizedRecord.operator"),field:"operator",width:100,sortable:true},
			      {title:message("yly.personalizedRecord.nurseContent"),field:"nurseContent",width:100,sortable:true},
			      {title:message("yly.personalizedRecord.remark"),field:"remark",width:100,sortable:true},
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
	