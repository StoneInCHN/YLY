$(function() {

	$("#nurseChargeConfig_table_list").datagrid({
		title : message("yly.bed.charge.config"),
		fitColumns : true,
		toolbar : "#nurseChargeConfig_manager_tool",
		url : '../nurseChargeConfig/list.jhtml',
		pagination : true,
		loadMsg : message("yly.common.loading"),
		striped : true,
		columns : [ [
		// 选择列checkbox
		{
			field : 'ck',
			checkbox : true
		},
		// 护理级别
		{
			title : message("yly.nurseCharge.nurseLevel"),
			field : "chargeItem",
			width : 80,
			align:'center',
			sortable : true,
			formatter : function(value, row, index) {
				if (value) {
					return value.configValue;
				}
			}
		},
		// 金额(元)/天
		{
			title : message("yly.charge.amount.day"),
			field : "amountPerDay",
			width : 50,
			align:'center',
			sortable : true
		},
		// 金额(元)/月
		{
			title : message("yly.charge.amount.month"),
			field : "amountPerMonth",
			width : 50,
			align:'center',
			sortable : true
		},
		// 创建时间
		{
			title : message("yly.common.createDate"),
			field : "createDate",
			width : 60,
			align:'center',
			sortable : true,
			formatter : function(value, row, index) {
				return new Date(value).Format("yyyy-MM-dd");
			}
		}, ] ]

	});

})

var nurseChargeConfig_manager_tool = {
	add : function() {
		$('#addNurseChargeConfig')
				.dialog(
						{
							title : message("yly.nurseCharge.add"),
							width : 380,
							height : 270,
							modal : true,
							iconCls : 'icon-mini-add',
							cache : false,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#addNurseChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../nurseChargeConfig/add.jhtml",
															type : "post",
															data : $(
																	"#addNurseChargeConfig_form")
																	.serialize(),
															beforeSend : function() {
																$.messager
																		.progress({
																			text : message("yly.common.progress")
																		});
															},
															success : function(
																	result,
																	response,
																	status) {
																$.messager
																		.progress('close');
																showSuccessMsg(result.content);
																
																if(result.type == "success"){
																	$(
																	'#addNurseChargeConfig')
																	.dialog("close");
																	$('#addNurseChargeConfig_form').form('reset');		
																	$("#nurseChargeConfig_table_list")
																			.datagrid(
																					'reload');
																}
																
															},
															error : function(
																	XMLHttpRequest,
																	textStatus,
																	errorThrown) {
																$.messager
																		.progress('close');
																alertErrorMsg();
															}

														});
											}
											;
										}
									},
									{
										text : message("yly.common.cancel"),
										iconCls : 'icon-cancel',
										handler : function() {
											$('#addNurseChargeConfig')
													.dialog("close");
											$('#addNurseChargeConfig_form').form('reset');
										}
									} ],
									
									onOpen:function(){
								    	$('#addNurseChargeConfig_form').show();
								    	$("#nurseLevel").combobox({    
										    valueField:'id',    
										    textField:'configValue',
										    cache: true,
										    url:'../systemConfig/findByConfigKey.jhtml',
										    onBeforeLoad : function(param) {
										        param.configKey = 'NURSELEVEL';// 参数
										    }
										});
								    },
								    
						});
	},
	edit : function() {
		var _edit_row = $('#nurseChargeConfig_table_list')
				.datagrid('getSelections');
		if (_edit_row.length == 0) {
			$.messager.alert(message("yly.common.prompt"),
					message("yly.common.select.editRow"), 'warning');
			return false;
		}
		if (_edit_row.length>1) {
			$.messager.alert(message("yly.common.prompt"),
					message("yly.common.select.editRow.unique"), 'warning');
			return false;
		}
		var _dialog = $('#editNurseChargeConfig')
				.dialog(
						{
							title : message("yly.nurseCharge.edit"),
							width : 400,
							height : 270,
							modal : true,
							iconCls : 'icon-mini-edit',
							href : '../nurseChargeConfig/edit.jhtml?id='
									+ _edit_row[0].id,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#editNurseChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../nurseChargeConfig/update.jhtml",
															type : "post",
															data : $(
																	"#editNurseChargeConfig_form")
																	.serialize(),
															beforeSend : function() {
																$.messager
																		.progress({
																			text : message("yly.common.progress")
																		});
															},
															success : function(
																	result,
																	response,
																	status) {
																$.messager
																		.progress('close');
																if (response == "success") {
																	showSuccessMsg(result.content);
																	$(
																			'#editNurseChargeConfig')
																			.dialog(
																					"close");
																	$(
																			"#nurseChargeConfig_table_list")
																			.datagrid(
																					'reload');
																} else {
																	alertErrorMsg();
																}
															}
														});
											}
											;
										}
									},
									{
										text : message("yly.common.cancel"),
										iconCls : 'icon-cancel',
										handler : function() {
											$('#editNurseChargeConfig').dialog(
													"close");
										}
									} ]
						});
	},

	remove : function() {
		listRemove('nurseChargeConfig_table_list',
				'../nurseChargeConfig/delete.jhtml');
	}
}