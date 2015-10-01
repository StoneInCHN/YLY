$(function() {

	$("#waterElectricityChargeConfig_table_list").datagrid({
		title : message("yly.waterElectricity.charge.config"),
		fitColumns : true,
		toolbar : "#waterElectricityChargeConfig_manager_tool",
		url : '../waterElectricityChargeConfig/list.jhtml',
		pagination : true,
		loadMsg : message("yly.common.loading"),
		striped : true,
		columns : [ [
		// 选择列checkbox
		{
			field : 'ck',
			checkbox : true
		},
		// 水(元/吨)
		{
			title : message("yly.charge.water.amount"),
			field : "waterUnitPrice",
			width : 50,
			align:'center',
			sortable : true
		},
		// 电(元/度)
		{
			title : message("yly.charge.electricity.amount"),
			field : "electricityUnitPrice",
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

var waterElectricityChargeConfig_manager_tool = {
	add : function() {
		$('#addWaterElectricityChargeConfig')
				.dialog(
						{
							title : message("yly.waterElectricityCharge.add"),
							width : 380,
							height : 200,
							modal : true,
							iconCls : 'icon-mini-add',
							cache : false,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#addWaterElectricityChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../waterElectricityChargeConfig/add.jhtml",
															type : "post",
															data : $(
																	"#addWaterElectricityChargeConfig_form")
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
																	'#addWaterElectricityChargeConfig')
																	.dialog("close");
																	$('#addWaterElectricityChargeConfig_form').form('reset');		
																	$("#waterElectricityChargeConfig_table_list")
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
											$('#addWaterElectricityChargeConfig')
													.dialog("close");
											$('#addWaterElectricityChargeConfig_form').form('reset');
										}
									} ],
									
									onOpen:function(){
								    	$('#addWaterElectricityChargeConfig_form').show();
								    },
								    
						});
	},
	edit : function() {
		var _edit_row = $('#waterElectricityChargeConfig_table_list')
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
		var _dialog = $('#editWaterElectricityChargeConfig')
				.dialog(
						{
							title : message("yly.waterElectricityCharge.edit"),
							width : 400,
							height : 200,
							modal : true,
							iconCls : 'icon-mini-edit',
							href : '../waterElectricityChargeConfig/edit.jhtml?id='
									+ _edit_row[0].id,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#editWaterElectricityChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../waterElectricityChargeConfig/update.jhtml",
															type : "post",
															data : $(
																	"#editWaterElectricityChargeConfig_form")
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
																			'#editWaterElectricityChargeConfig')
																			.dialog(
																					"close");
																	$(
																			"#waterElectricityChargeConfig_table_list")
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
											$('#editWaterElectricityChargeConfig').dialog(
													"close");
										}
									} ]
						});
	},

	remove : function() {
		listRemove('waterElectricityChargeConfig_table_list',
				'../waterElectricityChargeConfig/delete.jhtml');
	}
}