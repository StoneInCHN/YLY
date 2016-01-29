
	$(function(){
		$("#personalizedNurse-table-list").datagrid({
			title:message("yly.personalizedNurse.list"),
			fitColumns:true,
			//fit:true,
			url:'../personalizedNurse/list.jhtml',  
			pagination:true,
			singleSelect:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:message("yly.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addPersonalizedNurse").dialog({    
					    title:message("yly.personalizedNurse.add"),   
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
								var validate = $('#addPersonalizedNurse_form').form('validate');
								if(validate){
									$.ajax({
										url:"../personalizedNurse/add.jhtml",
										type:"post",
										data:$("#addPersonalizedNurse_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addPersonalizedNurse_form').form('reset');
											$('#addPersonalizedNurse').dialog("close");
											$("#personalizedNurse-table-list").datagrid('reload');
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
								 $('#addPersonalizedNurse').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addPersonalizedNurse_form').show();
						    	$("#addPersonalizedNurse_form_personalized").combobox({    
								    valueField:'id',    
								    textField:'text',
								    cache: true,
								    method:"GET",
								    prompt:message("yly.common.please.select"),
								    url:'../personalizedChargeConfig/findAll.jhtml'
								});
						},
						onClose:function(){
						    	$('#addPersonalizedNurse_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("yly.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#personalizedNurse-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
						return false;
					}
					$("#editPersonalizedNurse").dialog({
						width:350,
						height:350,
						iconCls:'icon-mini-edit',
						title:message("yly.nursePlan.edit"),
						href:'../personalizedNurse/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editPersonalizedNurse_form').form('validate');
								if(validate){
									$.ajax({
										url:"../personalizedNurse/update.jhtml",
										type:"post",
										data:$("#editPersonalizedNurse_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											debugger;
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editPersonalizedNurse').dialog("close");
											$("#personalizedNurse-table-list").datagrid('reload');
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
								 $('#editPersonalizedNurse').dialog("close");
							}
						}],
						onLoad:function(){
							$("#editPersonalizedNurse_form_personalized").combobox({    
							    valueField:'id',    
							    textField:'text',
							    cache: true,
							    method:"GET",
							    prompt:message("yly.common.please.select"),
							    url:'../personalizedChargeConfig/findAll.jhtml'
							});
							$('#editPersonalizedNurse_form_personalized').combobox('setValue', $("#editPersonalizedNurse_form_personalizedID").val());
						},
					})
				}
			},'-',{
				text:message("yly.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#personalizedNurse-table-list").treegrid('getSelections');
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
										url : "../personalizedNurse/delete.jhtml",
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
												$("#personalizedNurse-table-list").treegrid('reload');
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
			      {title:message("yly.personalizedNurse.elderlyInfo"),field:"elderlyInfo",width:100,sortable:true,formatter: function(value,row,index){
						if(value && value.name){
							return value.name;
						}else{
							
						}
			      	}},
			       {title:message("yly.personalizedNurse.personalized"),field:"personalized",width:100,sortable:true,formatter: function(value,row,index){
						if(value && value.chargeItem){
							return value.chargeItem;
						}else{
							
						}
			      	}},
			      {title:message("yly.personalizedNurse.applyTime"),field:"applyTime",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.personalizedNurse.sumCount"),field:"sumCount",width:100,sortable:true},
			      {title:message("yly.personalizedNurse.usedCount"),field:"usedCount",width:100,sortable:true},
			      {title:message("yly.personalizedNurse.operator"),field:"operator",width:100,sortable:true},
			      {title:message("yly.personalizedNurse.nurseContent"),field:"nurseContent",width:100,sortable:true},
			      {title:message("yly.personalizedNurse.servicePrice"),field:"servicePrice",width:100,sortable:true},
			      {title:message("yly.personalizedNurse.remark"),field:"remark",width:100,sortable:true},
			      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			],
			onDblClickRow:function(rowIndex, rowData){
				var nurseId = rowData.id;
				$('#incluedPersonalizedRecord').panel('refresh','../personalizedRecord/personalizedRecord.jhtml?nurseId='+nurseId);
			}
		});
})
	