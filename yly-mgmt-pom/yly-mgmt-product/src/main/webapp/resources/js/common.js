// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

var setting = {
	priceScale : "2",
	priceRoundType : "roundHalfUp",
	uploadImageExtension : "jpg,jpeg,bmp,gif,png",
	uploadFlashExtension : "swf,flv",
	uploadMediaExtension : "swf,flv,mp3,wav,avi,rm,rmvb",
	uploadFileExtension : "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};

/**
 * 公共提示信息 response==success
 * 
 * @param msgStr
 */
function showSuccessMsg(msgStr) {
	$.messager.show({
		title : message("yly.common.prompt"),
		msg : msgStr,
		timeout : 3000,
		showType : 'slide'
	});
}

/**
 * 公共提示信息 error
 */
function alertErrorMsg() {
	$.messager.alert(message("yly.common.fail"),
			message("yly.common.unknow.error"), 'error');
}

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""),
				(options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

/**
 * 删除公用方法 id table_list id url 删除是向后台发送的链接
 */
function listRemove(id, url) {
	var _id = id;
	var _url = url
	var _rows = $("#" + _id).datagrid('getSelections');
	if (_rows.length == 0) {
		$.messager.alert(message("yly.common.prompt"),
				message("yly.common.select.deleteRow"), 'warning');
	} else {
		var _ids = [];
		for (var i = 0; i < _rows.length; i++) {
			_ids.push(_rows[i].id);
		}
		if (_ids.length > 0) {
			$.messager.confirm(message("yly.common.confirm"),
					message("yly.common.delete.confirm"), function(r) {
						if (r) {
							$.ajax({
								url : _url,
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
										$("#" + _id).datagrid('reload');
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

/**
 * 老人查询功能
 */
function searchElderlyInfo(id) {
	if (id == "checkoutCharge") {// 办理出院
		var checkoutFlag = false;
		if (!$("#addCheckoutCharge_checkoutNow").is(":checked")
				&& $("#addCheckoutCharge_checkoutDate").val() == "") {
			$.messager.alert(message("yly.common.prompt"),
					message("yly.checkout.keyin_checkout_date"), 'warning');
			checkoutFlag = true;
		}
		if (checkoutFlag) {
			return;
		}
	}
	$('#searchElderlyInfo')
			.dialog(
					{
						title : message("yly.visitelderly.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../elderlyInfo/commonElderlySearch.jhtml',
						buttons : [ {
							text : message("yly.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#searchElderlyInfo').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common_elderlyInfoSearch-table-list")
									.datagrid(
											{
												title : message("yly.elderlyinfo"),
												fitColumns : true,
												url : '../elderlyInfo/list.jhtml',
												pagination : true,
												loadMsg : message("yly.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													if(id.indexOf("NurseChange") != -1){//护理变更
														if(id.indexOf("add")==0){//以add开头
															 $("#addNurseChange_elderlyInfoID").val(rowData.id); // 隐藏域 老人id
															 $("#addNurseChange_elderlyName").textbox('setValue',rowData.name); // 老人姓名
															 $("#addNurseChange_elderlyName_Temp").textbox('setValue',rowData.name); // 老人姓名
															 $("#addNurseChange_oldNurseLevel").combobox('select',rowData.nursingLevel.id); // 护理级别
														}
														if(id.indexOf("edit")==0){//以add开头
															 $("#editNurseChange_elderlyInfoID").val(rowData.id); // 隐藏域 老人id
															 $("#editNurseChange_elderlyName").textbox('setValue',rowData.name); // 老人姓名
															 $("#editNurseChange_elderlyName_Temp").textbox('setValue',rowData.name); // 老人姓名
															 $("#editNurseChange_oldNurseLevel").combobox('select',rowData.nursingLevel.id); // 护理级别
														}
														$('#searchElderlyInfo').dialog("close");
														return false;
													}
													if(id.indexOf("NurseArrangement") != -1){//护理员安排
														var dataMap = {};
														dataMap.id = rowData.id; // 老人id
														dataMap.name = rowData.name; // 老人姓名
														dataMap.bedLocation = rowData.bedLocation; // 床位
														if (rowData.nursingLevel != null) {
															dataMap.nursingLevel = rowData.nursingLevel.configValue; // 护理级别
														}
														populateElderlyInfo(id, dataMap);// 将老人基本信息加载到添加护理员安排页面
														$('#searchElderlyInfo').dialog("close");
														return false;
													}
													if (id == "checkoutCharge") {// 办理出院
														if (rowData.elderlyStatus == "OUT_NURSING_HOME"
																|| rowData.elderlyStatus == "IN_PROGRESS_CHECKOUT") {
															$.messager
																	.alert(
																			message("yly.common.prompt"),
																			message(
																					"yly.checkout.elderlyStatus.invalid",
																					rowData.name),
																			'warning');
															return false;
														}

														var dataMap = {};
														dataMap.id = rowData.id; // 老人id
														dataMap.name = rowData.name; // 老人姓名
														dataMap.identifier = rowData.identifier; // 老人编号
														dataMap.bedLocation = rowData.bedLocation; // 床位
														if (rowData.nursingLevel != null) {
															dataMap.nursingLevel = rowData.nursingLevel.configValue; // 护理级别
														}
														dataMap.advanceChargeAmount = rowData.advanceChargeAmount; // 预存款
														detectBillingUnderElderly(dataMap);// 查找该老人下所有账单,并将账单明细加载到添加办理出院页面
														$('#searchElderlyInfo').dialog("close");
														return false;
													}
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.name);
													if ($("#identifier")) {
														$("#identifier")
																.val(
																		rowData.identifier);
													}
													if ($("#" + id + "_age")) {
														$("#" + id + "_age")
																.val(
																		rowData.age);
													}
													if ($("#" + id + "_gender")) {

														if (rowData.gender == "MALE") {
															$(
																	"#"
																			+ id
																			+ "_gender")
																	.val(
																			message("yly.common.male"));
														}
														if (rowData.gender == "FEMALE") {
															$(
																	"#"
																			+ id
																			+ "_gender")
																	.val(
																			message("yly.common.female"));
														}
													}
													console.log(id);
													$('#searchElderlyInfo')
															.dialog("close");
												},
												columns : [ [
														{
															field : 'ck',
															checkbox : true
														},
														{
															title : message("yly.elderlyinfo.identifier"),
															field : "identifier",
															width : 12,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.common.elderlyname"),
															field : "name",
															width : 20,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.common.age"),
															field : "age",
															width : 10,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.elderlyInfo.elderlyPhoneNumber"),
															field : "elderlyPhoneNumber",
															width : 20,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.elderlyInfo.beHospitalizedDate"),
															field : "beHospitalizedDate",
															width : 25,
															align : 'center',
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value != null) {
																	return new Date(
																			value)
																			.Format("yyyy-MM-dd");
																}
															}
														},
														{
															title : message("yly.common.gender"),
															field : "gender",
															width : 10,
															align : 'center',
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value == "MALE") {
																	return message("yly.common.male");
																}
																if (value == "FEMALE") {
																	return message("yly.common.female");
																}
															}
														},
														{
															title : message("yly.common.idcard"),
															field : "idcard",
															width : 40,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.common.bedRoom"),
															field : "bedLocation",
															width : 30,
															align : 'center',
															sortable : true
														},
														{
															title : message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber"),
															field : "consignerPhoneNumber",
															width : 20,
															align : 'center',
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (row != null
																		&& row.elderlyConsigner != null) {
																	return row.elderlyConsigner.consignerPhoneNumber;
																}
															}
														},
														{
															title : message("yly.elderly.status"),
															field : "elderlyStatus",
															width : 20,
															align : 'center',
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value == "IN_NURSING_HOME") {
																	return message("yly.elderly.status.in_nursing_home");
																}
																if (value == "OUT_NURSING_HOME") {
																	return message("yly.elderly.status.out_nursing_home");
																}
																if (value == "IN_PROGRESS_CHECKIN") {
																	return message("yly.elderly.status.in_progress_checkin");
																}
																if (value == "IN_PROGRESS_CHECKOUT") {
																	return message("yly.elderly.status.in_progress_checkout");
																}
																if (value == "DEAD") {
																	return message("yly.elderly.status.dead");
																}
															}
														} ] ]

											});

							$("#common_elderlyinfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_elderlyinfo_search_form")
														.serializeJSON();
												$(
														'#common_elderlyInfoSearch-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common_elderlyInfoSearch-table-list")
														.datagrid('reload');
											})
						}
					});
}
// 查询用户
function searchTenantUser(id) {
	$('#searchTenantUser')
			.dialog(
					{
						title : message("yly.tenantUser.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../tenantUser/commonTenantUserSearch.jhtml',
						buttons : [ {
							text : message("yly.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#searchTenantUser').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-tenantUser-table-list")
									.datagrid(
											{
												title : message("yly.elderlyinfo"),
												fitColumns : true,
												url : '../tenantUser/list.jhtml',
												pagination : true,
												loadMsg : message("yly.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													if(id.indexOf("NurseArrangement") != -1){//护理员安排
														if(id.indexOf("add")==0){//以add开头
															$("#addNurseArrangement_nurseAssistantID").val(rowData.id); // 隐藏域 护理员id
															$("#addNurseArrangement_nurseAssistantName").textbox('setValue',rowData.realName); 
														}
														if(id.indexOf("edit")==0){//以edit开头
															$("#editNurseArrangement_nurseAssistantID").val(rowData.id); // 隐藏域 护理员id
															$("#editNurseArrangement_nurseAssistantName").textbox('setValue',rowData.realName); 
														}
														$('#searchTenantUser').dialog("close");
														return false;
													}
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.realName);
													if ($("#identifier").length >0) {
														$("#identifier")
																.val(
																		rowData.identifier);
													}
													$('#searchTenantUser')
															.dialog("close");
												},
												columns : [ [
														{
															title : message("yly.common.name"),
															field : "realName",
															width : 100,
															sortable : true
														},
														{
															title : message("yly.common.age"),
															field : "age",
															width : 100,
															sortable : true
														},
														{
															title : message("yly.tenantUser.staffID"),
															field : "staffID",
															width : 100,
															sortable : true
														},
														{
															title : message("yly.tenantUser.staffStatus"),
															field : "staffStatus",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value == "INSERVICE") {
																	return message("yly.tenantUser.staffStatus.inService");
																} else if (value = "OUTSERVICE") {
																	return message("yly.tenantUser.staffStatus.outService");
																}
															}
														},

														{
															title : message("yly.tenantUser.department"),
															field : "department",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value) {
																	return value.name;
																} else {
																	return value;
																}
															}
														},
														{
															title : message("yly.tenantUser.position"),
															field : "position",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value) {
																	return value.name;
																} else {
																	return value;
																}
															}
														},
														{
															title : message("yly.tenantUser.hireDate"),
															field : "hireDate",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																return new Date(
																		value)
																		.Format("yyyy-MM-dd");
															}
														}, ] ]

											});

							$("#common_elderlyinfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_elderlyinfo_search_form")
														.serializeJSON();
												$(
														'#common_elderlyInfoSearch-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common_elderlyInfoSearch-table-list")
														.datagrid('reload');
											})
						}
					});

}
//查询护理员安排
function searchNurseArrangement(id){
	$('#searchNurseArrangement').dialog({
						title : message("yly.nurseArrangement.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../nurseArrangement/nurseArrangementSearch.jhtml',
						buttons : [ {
							text : message("yly.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#searchNurseArrangement').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示护理员管理数据,并且提供查询功能
							 */
							$("#common-nurseArrangement-table-list").datagrid({
												title : message("yly.elderlyinfo"),
												fitColumns : true,
												url : '../nurseArrangement/list.jhtml',
												pagination : true,
												loadMsg : message("yly.common.loading"),
												striped : true,
												onDblClickRow : function(rowIndex, rowData) {
													if(id.indexOf("NurseArrangementRecord") != -1){
														var dataMap = {};
														dataMap.id = rowData.id; //护理员安排 id
														dataMap.nurseName = rowData.nurseName; // 护理名称
														dataMap.nurseStartDate = rowData.nurseStartDate;//护理开始日期
														dataMap.nurseEndDate = rowData.nurseEndDate;//护理结束日期
														dataMap.nursingLevel = rowData.nursingLevel;//护理级别
														dataMap.bedLocation = rowData.bedLocation;//床位位置
														if (rowData.nurseAssistant != null) {
															dataMap.nurseAssistantName = rowData.nurseAssistant.realName; // 护理员名字
														}
														if (rowData.elderlyInfo != null) {
															dataMap.elderlyInfoName = rowData.elderlyInfo.name; // 老人名字
														}														  
														populateNurseArrangement(id, dataMap);// 将护理员安排信息加载到添加护理员安排明细页面
														$('#searchNurseArrangement').dialog("close");
														return false;
													}
												},
												columns : [ [
														      {field:'ck',checkbox:true},
														      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:"10%",align:'center',formatter:function(value,row,index){
														    	  return row.elderlyInfo.name;
														      }},
														      {title:message("yly.nurseArrangement.nurseName"),field:"nurseName",width:"10%",align:'center',formatter:function(value,row,index){
														    	  return  formatLongString(value,20);
														      }},		 
														      {title:message("yly.nurseArrangement.nursingLevel"),field:"nursingLevel",width:"10%",align:'center',formatter:function(value,row,index){
														    	  return  formatLongString(value,20);
														      }},	
														      {title:message("yly.elderlyinfo.bed"),field:"bedLocation",width:"15%",align:'center',formatter:function(value,row,index){
														    	  return  formatLongString(value,20);
														      }},			      
														      {title:message("yly.nurseArrangement.nurseStartDate"),field:"nurseStartDate",width:"14%",align:'center',sortable:true,formatter: function(value,row,index){
																	return new Date(value).Format("yyyy-MM-dd");
															  }},
															  {title:message("yly.nurseArrangement.nurseEndDate"),field:"nurseEndDate",width:"14%",align:'center',sortable:true,formatter: function(value,row,index){
																		return new Date(value).Format("yyyy-MM-dd");
															   }},
														      {title:message("yly.nurseArrangement.nurseAssistant"),field:"nurseAssistantName",width:"10%",align:'center',formatter:function(value,row,index){
														    	  return row.nurseAssistant.realName;
														      }},
															  {title:message("yly.common.remark"),field:"remark",width:"15%",align:'center',formatter:function(value,row,index){
																	return  formatLongString(value,50);
															  }}
												             ] ]
											});

							$("#common_nurseArrangement_search_btn").click(
											function() {
												var _queryParams = $("#common_nurseArrangement_search_form").serializeJSON();
												$('#common-nurseArrangement-table-list').datagrid('options').queryParams = _queryParams;
												$("#common-nurseArrangement-table-list").datagrid('reload');
											})
						}
					});


}
function searchRoles(id) {
	alert(id);
	$('#searchRoles')
			.dialog(
					{
						title : message("yly.role.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../role/commonRolesSearch.jhtml',
						buttons : [ {
							text : message("yly.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#searchRoles').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-roles-table-list")
									.datagrid(
											{
												title : message("yly.elderlyinfo"),
												fitColumns : true,
												url : '../role/list.jhtml',
												pagination : true,
												loadMsg : message("yly.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.name);
													if ($("#identifier")) {
														$("#identifier")
																.val(
																		rowData.identifier);
													}
													$('#searchRoles').dialog(
															"close");
												},
												columns : [ [
														{
															title : message("yly.role.name"),
															field : "name",
															width : 20,
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																return row.name;
															}
														},
														{
															title : message("yly.role.description"),
															field : "description",
															width : 80,
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																return row.description;
															}
														} ] ]

											});

							$("#common_elderlyinfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_elderlyinfo_search_form")
														.serializeJSON();
												$(
														'#common_elderlyInfoSearch-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common_elderlyInfoSearch-table-list")
														.datagrid('reload');
											})
						}
					});

}
function formReset(formId, tableId) {
	$('#' + formId)[0].reset();
	var _queryParams = {}
	$('#' + tableId).datagrid('options').queryParams = _queryParams;
}

// 返回指定长度字符串截取,超出部分不显示,以...作为后缀显示
function formatLongString(str, len) {
	if (str != null && str != "" && len > 0) {
		if (str.length > len) {
			return '<span title="' + str + '">' + str.substring(0, len) + "..."
					+ '<span>'
		} else {
			return str;
		}
	}
	return "";
}
// 导出excel数据
function exportData(control, form) {
	// 建议一次导出excel数据的最大值为500
	var maxSize = 500;
	$
			.ajax({
				url : "../" + control + "/count.jhtml",
				type : "post",
				data : $("#" + form).serialize(),
				success : function(result, response, status) {
					if (result.count != null) {
						var text = "";
						if (result.count == 0) {
							// "当前条件无可导出的数据。"
							text = message("yly.common.notice.current_condition_no_export_data");
							$.messager.alert(message("yly.common.notice"),
									text, 'warning');
						} else if (result.count <= maxSize) {
							// "确定导出 {0}条记录？"
							text = message(
									"yly.common.notice.comfirm_export_data",
									result.count);
							$.messager
									.confirm(
											message("yly.common.confirm"),
											text,
											function(r) {
												if (r) {
													$("#" + form)
															.attr(
																	"action",
																	"../"
																			+ control
																			+ "/exportData.jhtml");
													$("#" + form).attr(
															"target", "_blank");
													$("#" + form).submit();
												}

											});
						} else {
							// "导出数据超过 "+maxSize+" 条数据，建议搜索查询条件以缩小查询范围，再导出。";
							text = message(
									"yly.common.notice.export_data_too_much_advice_use_filter",
									maxSize);
							$.messager
									.confirm(
											message("yly.common.notice"),
											text,
											function(r) {
												if (!r) {
													// "导出共有"+ result.count
													// +"条数据，导出超过 "+maxSize+"
													// 条数据可能需要您耐心等待，仍需操作请确定继续。";
													text = message(
															"yly.common.notice.need_wait_export_too_much_data",
															result.count,
															maxSize);
													$.messager
															.confirm(
																	message("yly.common.confirm"),
																	text,
																	function(
																			yes) {
																		if (yes) {
																			$(
																					"#"
																							+ form)
																					.attr(
																							"action",
																							"../"
																									+ control
																									+ "/exportData.jhtml");
																			$(
																					"#"
																							+ form)
																					.attr(
																							"target",
																							"_blank");
																			$(
																					"#"
																							+ form)
																					.submit();
																		}
																	});
												}
											})
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
}

function loadDataPie(id, url, args, nameArray, dataArray, nameField, valueField) {
	id.series[0].data = [];
	$.ajax({
		url : url,
		type : "post",
		data : args,
		cache : false,
		success : function(data) {
			id.series[0].data = [];
			if (data.length == 1 && nameArray != null && dataArray != null) {
				if (id.chart.renderTo == 'elderlyLiveStatiticsReportId') {
					var value = [ nameArray[0], data[0]['inUsingZoomCount'] ];
					id.series[0].data.push(value);
					var free = [
							nameArray[1],
							data[0]['totalZoomCount']
									- data[0]['inUsingZoomCount'] ];
					id.series[0].data.push(free);
				} else {
					for (var i = 0; i < nameArray.length; i++) {
						var value = [ nameArray[i], data[0][dataArray[i]] ];
						id.series[0].data.push(value);
					}
				}
			} else {
				for (var i = 0; i < data.length; i++) {
					var value = [ data[i][nameField], data[i][valueField] ];
					id.series[0].data.push(value);
				}

			}
			var chart = new Highcharts.Chart(id);
		}

	});

};
function loadDataLine(id, url, args, categoryName, valueName, viewName) {
	$.ajax({
		url : url,
		type : "post",
		cache : false,
		data : args,
		success : function(data) {
			if (data.length > 0) {
				id.xAxis.categories = [];
				if (valueName instanceof Array) {
					for (var j = 0; j < valueName.length; j++) {
						var value = new Object();
						value.name = viewName[j];
						value.data = [];
						id.series.push(value);
					}
				}
				for (var i = 0; i < data.length; i++) {
					id.xAxis.categories.push(new Date(data[i][categoryName])
							.Format("yyyy年MM月"));
					if (valueName instanceof Array) {
						for (var j = 0; j < valueName.length; j++) {
							id.series[j].data.push(data[i][valueName[j]]);
						}
					} else {
						id.series[0].data.push(data[i][valueName]);
					}
				}
			}
			var chart = new Highcharts.Chart(id);
		}

	});

}
function loadDataColumn(id, url, categoryName, valueName, seriesName) {
	$.ajax({
				url : url,
				type : "post",
				cache : false,
				success : function(data) {

					if (data.length > 0) {
						var viewName = [];
						var categoryValue = [];

						if (id.chart.renderTo == 'donateStatisticsReportId') {
							for (var k = 0; k < data.length; k++) {
								var category = new Date(data[k][categoryName])
										.Format("yyyy年MM月");
								if (categoryValue.indexOf(category) == -1) {
									categoryValue.push(category);
								}
							}
							var index = 0;
							for (var j = 0; j < categoryValue.length; j++) {
								var total = 0;
								var moneyValue = 0;
								for (var i = 0; i < data.length; i++) {
									var category = new Date(
											data[i][categoryName])
											.Format("yyyy年MM月");
									var type = data[i]['donateType'];
									if (type == 'MONEY'
											&& categoryValue[j] == category) {
										moneyValue = data[i].donateCount;
									} else if (type == 'ITEM'
											&& categoryValue[j] == category) {
										total = total + data[i].donateCount;
									}
								}
								id.series[0].data.push(moneyValue);
								id.series[1].data[index] = total;
								index++;
							}
						} 
						id.xAxis.categories = categoryValue;
					}
					var chart = new Highcharts.Chart(id);
				}

			});
}
function refreshLine(id, data, categoryName, valueName, viewName) {
	if (data.length > 0) {
		id.xAxis.categories = [];
		if (valueName instanceof Array) {
			for (var j = 0; j < valueName.length; j++) {
				var value = new Object();
				value.name = viewName[j];
				value.data = [];
				id.series.push(value);
			}
		}
		for (var i = 0; i < data.length; i++) {
			id.xAxis.categories.push(new Date(data[i][categoryName])
					.Format("yyyy年MM月"));
			if (valueName instanceof Array) {
				for (var j = 0; j < valueName.length; j++) {
					id.series[j].data.push(data[i][valueName[j]]);
				}
			} else {
				id.series[0].data.push(data[i][valueName]);
			}
		}
	}
	var chart = new Highcharts.Chart(id);
}
function refreshPie(id, data, nameArray, dataArray, nameField, valueField) {
	if (data.length == 1 && nameArray != null && dataArray != null) {
		if (id.chart.renderTo == 'elderlyLiveStatiticsReportId') {
			var value = [ nameArray[0], data[0]['inUsingZoomCount'] ];
			id.series[0].data.push(value);
			var free = [ nameArray[1],
					data[0]['totalZoomCount'] - data[0]['inUsingZoomCount'] ];
			id.series[0].data.push(free);
		} else {
			for (var i = 0; i < nameArray.length; i++) {
				var value = [ nameArray[i], data[0][dataArray[i]] ];
				id.series[0].data.push(value);
			}
		}
	} else {
		for (var i = 0; i < data.length; i++) {
			var value = [ data[i][nameField], data[i][valueField] ];
			id.series[0].data.push(value);
		}

	}
	var chart = new Highcharts.Chart(id);
}

function refreshColumn(id, data, categoryName, valueName, seriesName) {
		if (data.length > 0) {
			var viewName = [];
			var categoryValue = [];

			if (id.chart.renderTo == 'donateStatisticsReportId') {
				for (var k = 0; k < data.length; k++) {
					var category = new Date(data[k][categoryName])
							.Format("yyyy年MM月");
					if (categoryValue.indexOf(category) == -1) {
						categoryValue.push(category);
					}
				}
				var index = 0;
				for (var j = 0; j < categoryValue.length; j++) {
					var total = 0;
					var moneyValue = 0;
					for (var i = 0; i < data.length; i++) {
						var category = new Date(
								data[i][categoryName])
								.Format("yyyy年MM月");
						var type = data[i]['donateType'];
						if (type == 'MONEY'
								&& categoryValue[j] == category) {
							moneyValue = data[i].donateCount;
						} else if (type == 'ITEM'
								&& categoryValue[j] == category) {
							total = total + data[i].donateCount;
						}
					}
					id.series[0].data.push(moneyValue);
					id.series[1].data[index] = total;
					index++;
				}
			} else {
				for (var k = 0; k < data.length; k++) {
					var name = data[k][seriesName].configValue;
					var category = new Date(data[k][categoryName])
							.Format("yyyy年MM月");
					if (categoryValue.indexOf(category) == -1) {
						categoryValue.push(category);
					}
					if (viewName.indexOf(name) == -1) {
						viewName.push(name);
						var value = new Object();
						value.name = name;
						value.data = [];
						id.series.push(value);
					}
				}
				for (var k = 0; k < categoryValue.length; k++) {
					for (var j = 0; j < viewName.length; j++) {
						// 如果该分类没数据，用0填充
						var viewValue = 0;
						for (var i = 0; i < data.length; i++) {
							var date = new Date(
									data[i][categoryName])
									.Format("yyyy年MM月");
							if (viewName[j] == data[i][seriesName].configValue
									&& date == categoryValue[k]) {
								viewValue = data[i][valueName]
							}
						}
						id.series[j].data.push(viewValue);
					}
				}

			}
			id.xAxis.categories = categoryValue;
		}
		var chart = new Highcharts.Chart(id);
	}
/**
 * 
 * @param option{
 *  type 调用类型 1:选房 2:换房
 *  elderlyId 老人ID
 *  elderlyName 老人姓名
 *  bedNumber 床位编号
 *  originalBedId 床位id
 *  tableId 关闭panel是需要刷新gridlist时的tableId  格式 #tableId
 *  bedInputId 选房时用于保存选房结果的ID
 * }
 */
function selectRoom(option){
	$("#selectRoom").dialog({    
	    title: '选床',    
	    width: 1000,    
	    height: 500,    
	    closed: false,    
	    cache: true,    
	    href: '../selectRoom/selectRoom.jhtml',    
	    modal: true,
	    onLoad:function(){
	    	if(option.type){
	    		$("#selectRoom").attr("data-type",option.type);
	    		if(option.type == 1){
	    			//第一次选房
	    			$("#selectRoom").attr("data-bed-input-id",option.bedInputId);
	    			//$("#"+option.bedInputId+"_text").textbox("setValue","你好");
	    			
	    		}else if(option.type == 2){
	    			$("#selectRoom").attr("data-elderly-id",option.elderlyId);
	    			$("#selectRoom").attr("data-elderly-name",option.elderlyName);
	    			$("#selectRoom").attr("data-elderly-bed-number",option.bedNumber);
	    			$("#selectRoom").attr("data-original-bed-id",option.originalBedId);
	    		}
	    	}
	    },
	    onClose:function(){
	    	$('#selectRoom').empty;  
	    	$("#selectRoom").attr("data-type","");
	    	
	    	if(option.type){
	    		$("#selectRoom").attr("data-type",option.type);
	    		if(option.tableId){
	    			$(option.tableId).datagrid('reload');
	    		}
	    	}
	    	
	    }
	}); 
}
