$(function() {

	$("#personalizedChargeConfig_table_list").datagrid({
		title : message("yly.service.charge.config"),
		fitColumns : true,
		toolbar : "#personalizedChargeConfig_manager_tool",
		url : '../personalizedChargeConfig/list.jhtml',
		pagination : true,
		loadMsg : message("yly.common.loading"),
		striped : true,
		columns : [ [
		// 选择列checkbox
		{
			field : 'ck',
			checkbox : true
		},
		//个性化服务项目
		{
			title : message("yly.personalizedCharge.item"),
			field : "chargeItem",
			width : 50,
			align:'center',
			sortable : true
		},
		// 金额(元/次)
		{
			title : message("yly.charge.amount.times"),
			field : "amountPerTime",
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

var personalizedChargeConfig_manager_tool = {
	add : function() {
		$('#addPersonalizedChargeConfig')
				.dialog(
						{
							title : message("yly.personalizedCharge.add"),
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
													'#addPersonalizedChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../personalizedChargeConfig/add.jhtml",
															type : "post",
															data : $(
																	"#addPersonalizedChargeConfig_form")
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
																	'#addPersonalizedChargeConfig')
																	.dialog("close");
																	$('#addPersonalizedChargeConfig_form').form('reset');		
																	$("#personalizedChargeConfig_table_list")
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
											$('#addPersonalizedChargeConfig')
													.dialog("close");
											$('#addPersonalizedChargeConfig_form').form('reset');
										}
									} ],
									
									onOpen:function(){
								    	$('#addPersonalizedChargeConfig_form').show();
								    },
								    
						});
	},
	edit : function() {
		var _edit_row = $('#personalizedChargeConfig_table_list')
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
		var _dialog = $('#editPersonalizedChargeConfig')
				.dialog(
						{
							title : message("yly.personalizedCharge.edit"),
							width : 400,
							height : 200,
							modal : true,
							iconCls : 'icon-mini-edit',
							href : '../personalizedChargeConfig/edit.jhtml?id='
									+ _edit_row[0].id,
							buttons : [
									{
										text : message("yly.common.save"),
										iconCls : 'icon-save',
										handler : function() {
											var validate = $(
													'#editPersonalizedChargeConfig_form')
													.form('validate');
											if (validate) {
												$
														.ajax({
															url : "../personalizedChargeConfig/update.jhtml",
															type : "post",
															data : $(
																	"#editPersonalizedChargeConfig_form")
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
																			'#editPersonalizedChargeConfig')
																			.dialog(
																					"close");
																	$(
																			"#personalizedChargeConfig_table_list")
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
											$('#editPersonalizedChargeConfig').dialog(
													"close");
										}
									} ]
						});
	},

	remove : function() {
		listRemove('personalizedChargeConfig_table_list',
				'../personalizedChargeConfig/delete.jhtml');
	}
}