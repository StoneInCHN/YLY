$(function() {

	$("#bedChargeConfig_table_list").datagrid({
		title : message("yly.bed.charge.config"),
		fitColumns : true,
		toolbar : "#bedChargeConfig_manager_tool",
		url : '../bedChargeConfig/list.jhtml',
		pagination : true,
		loadMsg : message("yly.common.loading"),
		striped : true,
		columns : [ [
		// 选择列checkbox
		{
			field : 'ck',
			checkbox : true
		},
		// 床位类型
		{
			title : message("yly.bedCharge.bedType"),
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
			align:'center',
			width : 60,
			sortable : true,
			formatter : function(value, row, index) {
				return new Date(value).Format("yyyy-MM-dd");
			}
		}, ] ]

	});

})

var bedChargeConfig_manager_tool = {
	add : function() {
		$('#addBedChargeConfig')
				.dialog(
						{
							title : message("yly.bedCharge.add"),
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
													'#addBedChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../bedChargeConfig/add.jhtml",
															type : "post",
															data : $(
																	"#addBedChargeConfig_form")
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
																	'#addBedChargeConfig')
																	.dialog("close");
																	$('#addBedChargeConfig_form').form('reset');		
																	$("#bedChargeConfig_table_list")
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
											$('#addBedChargeConfig')
													.dialog("close");
											$('#addBedChargeConfig_form').form('reset');
										}
									} ],
									
									onOpen:function(){
								    	$('#addBedChargeConfig_form').show();
								    	$("#bedType").combobox({    
										    valueField:'id',    
										    textField:'configValue',
										    cache: true,
										    url:'../systemConfig/findByConfigKey.jhtml',
										    onBeforeLoad : function(param) {
										        param.configKey = 'ROOMTYPE';// 参数
										    }
										});
								    },
								    
						});
	},
	edit : function() {
		var _edit_row = $('#bedChargeConfig_table_list')
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
		var _dialog = $('#editBedChargeConfig')
				.dialog(
						{
							title : message("yly.bedCharge.edit"),
							width : 400,
							height : 270,
							modal : true,
							iconCls : 'icon-mini-edit',
							href : '../bedChargeConfig/edit.jhtml?id='
									+ _edit_row[0].id,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#editBedChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../bedChargeConfig/update.jhtml",
															type : "post",
															data : $(
																	"#editBedChargeConfig_form")
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
																			'#editBedChargeConfig')
																			.dialog(
																					"close");
																	$(
																			"#bedChargeConfig_table_list")
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
											$('#editBedChargeConfig').dialog(
													"close");
										}
									} ]
						});
	},

	remove : function() {
		listRemove('bedChargeConfig_table_list',
				'../bedChargeConfig/delete.jhtml');
	}
}