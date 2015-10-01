$(function() {

	$("#mealChargeConfig_table_list").datagrid({
		title : message("yly.meal.charge.config"),
		fitColumns : true,
		toolbar : "#mealChargeConfig_manager_tool",
		url : '../mealChargeConfig/list.jhtml',
		pagination : true,
		loadMsg : message("yly.common.loading"),
		striped : true,
		columns : [ [
		// 选择列checkbox
		{
			field : 'ck',
			checkbox : true
		},
		// 伙食类型
		{
			title : message("yly.mealCharge.mealType"),
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

var mealChargeConfig_manager_tool = {
	add : function() {
		$('#addMealChargeConfig')
				.dialog(
						{
							title : message("yly.mealCharge.add"),
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
													'#addMealChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../mealChargeConfig/add.jhtml",
															type : "post",
															data : $(
																	"#addMealChargeConfig_form")
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
																	'#addMealChargeConfig')
																	.dialog("close");
																	$('#addMealChargeConfig_form').form('reset');		
																	$("#mealChargeConfig_table_list")
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
											$('#addMealChargeConfig')
													.dialog("close");
											$('#addMealChargeConfig_form').form('reset');
										}
									} ],
									
									onOpen:function(){
								    	$('#addMealChargeConfig_form').show();
								    	$("#mealType").combobox({    
										    valueField:'id',    
										    textField:'configValue',
										    cache: true,
										    url:'../systemConfig/findByConfigKey.jhtml',
										    onBeforeLoad : function(param) {
										        param.configKey = 'MEALTYPE';// 参数
										    }
										});
								    },
								    
						});
	},
	edit : function() {
		var _edit_row = $('#mealChargeConfig_table_list')
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
		var _dialog = $('#editMealChargeConfig')
				.dialog(
						{
							title : message("yly.mealCharge.edit"),
							width : 400,
							height : 270,
							modal : true,
							iconCls : 'icon-mini-edit',
							href : '../mealChargeConfig/edit.jhtml?id='
									+ _edit_row[0].id,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#editMealChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../mealChargeConfig/update.jhtml",
															type : "post",
															data : $(
																	"#editMealChargeConfig_form")
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
																			'#editMealChargeConfig')
																			.dialog(
																					"close");
																	$(
																			"#mealChargeConfig_table_list")
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
											$('#editMealChargeConfig').dialog(
													"close");
										}
									} ]
						});
	},

	remove : function() {
		listRemove('mealChargeConfig_table_list',
				'../mealChargeConfig/delete.jhtml');
	}
}