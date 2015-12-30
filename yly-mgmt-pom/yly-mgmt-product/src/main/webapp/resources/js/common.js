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
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.realName);
													if ($("#identifier")) {
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

//返回指定长度字符串截取,超出部分不显示,以...作为后缀显示
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
//导出excel数据
function exportData(control, form) {
	//建议一次导出excel数据的最大值为500
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
							//"当前条件无可导出的数据。"
							text = message("yly.common.notice.current_condition_no_export_data");
							$.messager.alert(message("yly.common.notice"),
									text, 'warning');
						} else if (result.count <= maxSize) {
							//"确定导出 {0}条记录？"
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
							//"导出数据超过 "+maxSize+" 条数据，建议搜索查询条件以缩小查询范围，再导出。";
							text = message(
									"yly.common.notice.export_data_too_much_advice_use_filter",
									maxSize);
							$.messager
									.confirm(
											message("yly.common.notice"),
											text,
											function(r) {
												if (!r) {
													//"导出共有"+ result.count +"条数据，导出超过 "+maxSize+" 条数据可能需要您耐心等待，仍需操作请确定继续。";
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

function loadDataPie(id, url, nameArray, dataArray) {
	$.ajax({
		url : url,
		type : "post",
		cache : false,
		success : function(data) {

			if (data.length == 1) {
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
			} else if (data.length > 1) {
				for (var i = 0; i < data.length; i++) {
					var value = [ data[i].evaluatingResultName,
							data[i].elderlyCount ];
					id.series[0].data.push(value);
				}

			}
			var chart = new Highcharts.Chart(id);
		}

	});

};
function loadDataLine(id, url, categoryName, valueName, viewName) {
	$.ajax({
		url : url,
		type : "post",
		cache : false,
		success : function(data) {

			if (data.length > 0) {
				if( valueName instanceof Array){
					for(var j = 0; j< valueName.length;j++){
						var value = new Object();
						value.name = viewName[j];
						value.data = [];
						id.series.push(value);
					}
				}
				for (var i = 0; i < data.length; i++) {
					id.xAxis.categories.push(new Date(data[i][categoryName])
							.Format("yyyy年MM月"));
					if( valueName instanceof Array){
						for(var j = 0; j< valueName.length;j++){
							id.series[j].data.push(data[i][valueName[j]]);
						}
					}else{
						id.series[0].data.push(data[i][valueName]);
					}
				}
			}
			var chart = new Highcharts.Chart(id);
		}

	});

}
function loadDataColumn(id, url,categoryName, valueName, seriesName) {
	$.ajax({
		url : url,
		type : "post",
		cache : false,
		success : function(data) {

			if (data.length > 0) {
				var viewName = [];
				var categoryValue = [];
				
				if(id.chart.renderTo == 'donateStatisticsReportId'){
					for(var k = 0; k < data.length; k++){
						var category = new Date(data[k][categoryName]).Format("yyyy年MM月");
						if(categoryValue.indexOf(category) == -1){
							categoryValue.push(category);
						}
					}
					var index = 0;
					for(var j = 0; j<categoryValue.length; j++){
						var total = 0 ;
						for(var i = 0; i < data.length; i++){
							var category = new Date(data[i][categoryName]).Format("yyyy年MM月");
							var type = data[i]['donateType'];
							if(type == 'MONEY' && categoryValue[j] == category){
								id.series[0].data.push(data[i].donateCount);
							}else if(type == 'ITEM' && categoryValue[j] == category){
								total = total+data[i].donateCount;
							}
						}
						id.series[1].data[index] = total;
						index++;
					}
				}else{
					for(var k = 0; k < data.length; k++){
						var name = data[k][seriesName].configValue;
						var category = new Date(data[k][categoryName]).Format("yyyy年MM月");
						if(categoryValue.indexOf(category) == -1){
							categoryValue.push(category);
						}
						if(viewName.indexOf(name) == -1){
							viewName.push(name);
							var value = new Object();
							value.name = name;
							value.data = [];
							id.series.push(value);
						}
					}
					
					for (var i = 0; i < data.length; i++) {
						for(var j = 0; j<viewName.length; j++){
							if(viewName[j] == data[i][seriesName].configValue){
								id.series[j].data.push(data[i][valueName]);
							}
						}
					}
					
				}
				id.xAxis.categories = categoryValue;
			}
			var chart = new Highcharts.Chart(id);
		}

	});

}