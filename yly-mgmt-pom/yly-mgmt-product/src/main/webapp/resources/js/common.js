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

var messages = {
	/**
	 * common
	 */
	"yly.common.createDate" : "创建时间",
	"yly.common.modifyDate" : "修改时间",
	"yly.common.loading" : "加载中...",
	"yly.common.save" : "保存",
	"yly.common.saving" : "保存中...",
	"yly.common.edit" : "编辑",
	"yly.common.cancel" : "取消",
	"yly.common.progress" : "正在处理中...",
	"yly.common.prompt" : "操作提示",
	"yly.common.success" : "操作成功",
	"yly.common.fail" : "操作失败",
	"yly.common.unknow.error" : "未知错误",
	"yly.common.select.editRow" : "请选择要编辑的记录",
	"yly.common.select.editRow.unique" : "只能选择一条记录修改",
	"yly.common.select.deleteRow" : "请选择要删除的内容",
	"yly.common.confirm" : "确认",
	"yly.common.delete.confirm" : "您确认想要删除记录吗？",
	"yly.common.gender": "性别",
	"yly.common.relation":"与老人关系",
	"yly.common.infoChannel":"信息来源",
	"yly.common.remark":"备注",
	"yly.common.phonenumber":"电话号码",
	"yly.common.male":"男",
	"yly.common.female":"女",
	"yly.common.other":"其它",
	"yly.common.yes":"是",
	"yly.common.no":"否",
	"yly.common.detail":"详情",
	"yly.common.relation.self":"本人",
	"yly.common.relation.children":"子女",
	"yly.common.relation.marriage_relationship":"夫妻",
	"yly.common.relation.grandparents_and_grandchildren":"祖孙",
	"yly.common.relation.brothers_or_sisters":"兄弟或姐妹",
	"yly.common.relation.daughterinlaw_or_soninlaw":"儿媳或女婿",
	"yly.common.relation.friend":"朋友",
	"yly.common.relation.infochannel.network":"网络",
	"yly.common.relation.infochannel.community":"社区",
	"yly.common.relation.infochannel.ohter_introduct":"他人介绍",
	"yly.common.relation.infochannel.advertisement":"广告",
	"yly.common.idcard":"身份证号码",
	
	
	/**
	 * charge config
	 */
	"yly.nurse.charge.config" : "护理费费配置",
	"yly.meal.charge.config" : "伙食费配置",
	"yly.waterElectrity.charge.config" : "水电费配置",
	"yly.bed.charge.config" : "床位费配置",
	"yly.service.charge.config" : "服务费配置",
	"yly.charge.amount.day" : "金额(元)/天",
	"yly.charge.amount.month" : "金额(元)/月",
	// bedChargeConfig
	"yly.bedCharge.bedType" : "床位类型",
	"yly.bedCharge.add" : "添加床位费配置",
	"yly.bedCharge.edit" : "编辑床位费配置",
	// nurseChargeConfig
	"yly.nurseCharge.nurseLevel" : "护理级别",
	"yly.nurseCharge.add" : "添加护理费配置",
	"yly.nurseCharge.edit" : "编辑护理费配置",
	//mealCharegeConfig
	"yly.mealCharge.mealType" : "伙食类型",
	"yly.mealCharge.add" : "添加伙食费配置",
	"yly.mealCharge.edit" : "编辑伙食费配置",
	//consultation
	"yly.consultation.record":"咨询记录",
	"yly.consultation.vistor":"咨询人",
	"yly.consultation.elderlyname":"老人姓名",
	"yly.consultation.checkinintention":"入住意向",
	"yly.consultation.returnVisit":"是否回访",
	"yly.consultation.returnVisitDate":"回访时间",
	"yly.consultation.elderlyHealth":"健康状况",
	"yly.consultation.add":"添加咨询",
	"yly.consultation.checkinIntention.confirmed":"确认入住",
	"yly.consultation.checkinIntention.will_to_checkin_strongly":"入住意愿强",
	"yly.consultation.checkinIntention.will_to_checkin_not_strongly":"入住意愿不强",
	"yly.consultation.checkinIntention.will_not_checkin":"不入住",
	
	//visitelderly
	"yly.visitelderly.add":"添加探望",
	"yly.visitelderly.record":"探望记录",
	"yly.visitelderly.elderlyInfo":"被探望老人",
	"yly.visitelderly.visitor":"来访者",
	"yly.visitelderly.visitPersonnelNumber":"来访人数",
	"yly.visitelderly.visitDate":"来访时间",
	"yly.visitelderly.dueLeaveDate":"预计离开时间",
	"yly.visitelderly.reasonForVisit":"来访原因",
	"yly.visitelderly.search":"老人查询",

	// volunteer
	"yly.volunteer.record":"志愿者记录",
	"yly.volunteer.name":"志愿者姓名",
	"yly.volunteer.type":"类型",
	"yly.volunteer.idCard":"证件号",
	"yly.volunteer.email":"邮件",
	"yly.volunteer.address":"地址",
	"yly.volunteer.zipCode":"邮编",
	"yly.volunteer.telephone":"电话",
	"yly.volunteer.mobile":"手机",
	"yly.volunteer.add":"添加志愿者",
	"yly.volunteer.personal":"个人",
	"yly.volunteer.organization":"组织机构",
	
	//elderlyinfo
	"yly.elderlyinfo":"老人信息"


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

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(
							new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1),
						function(i, n) {
							content = content.replace(new RegExp("\\{" + i
									+ "\\}", "g"), n);
						});
				return content;
			}
		}
	}
}

(function($) {

	var zIndex = 100;

	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string"
				&& typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}

		if (message.type == null || message.content == null) {
			return false;
		}

		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message'
					+ message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}

		$message.children("div").removeClass(
				"messagewarnIcon messageerrorIcon messagesuccessIcon")
				.addClass("message" + message.type + "Icon").html(
						message.content);
		$message.css({
			"margin-left" : -parseInt($message.outerWidth() / 2),
			"z-index" : zIndex++
		}).show();

		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 令牌
	$(document).ajaxSend(
			function(event, request, settings) {
				if (!settings.crossDomain && settings.type != null
						&& settings.type.toLowerCase() == "post") {
					var token = getCookie("token");
					if (token != null) {
						request.setRequestHeader("token", token);
					}
				}
			});

	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");

		if (loginStatus == "accessDenied") {
			$.message("warn", "登录超时，请重新登录");
			setTimeout(function() {
				location.reload(true);
			}, 2000);
		} else if (loginStatus == "unauthorized") {
			$.message("warn", "对不起，您无此操作权限！");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global : false,
					headers : {
						token : token
					}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$()
		.ready(
				function() {

					$("form")
							.submit(
									function() {
										var $this = $(this);
										if ($this.attr("method") != null
												&& $this.attr("method")
														.toLowerCase() == "post"
												&& $this.find(
														"input[name='token']")
														.size() == 0) {
											var token = getCookie("token");
											if (token != null) {
												$this
														.append('<input type="hidden" name="token" value="'
																+ token
																+ '" \/>');
											}
										}
									});

				});

/**
 * 删除公用方法 id table_list id url 删除是向后台发送的链接
 */
function listRemove(id, url) {
	var _id = id;
	var _url = url
	var _rows = $("#" + _id).datagrid('getSelections');
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
//								$.messager.show({
//									title : '提示',
//									msg : resultMsg,
//									timeout : 3000,
//									showType : 'slide'
//								});
								$("#" + _id).datagrid('reload');
							} else {
								alertErrorMsg();
								//$.messager.alert('删除失败', '未知错误', 'warning');
							}
						}
					});
				}
			})
		}

	}
}

function searchElderlyInfo() {
	$('#searchElderlyInfo').dialog({    
	    title: message("yly.visitelderly.search"),    
	    width: 1000,
	    height: 500,
	    modal:true,
	    cache: false,   
	    href:'../visitElderly/elderlyInfoSearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchElderlyInfo').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	/**
	    	 * 此datagrid 用户展示老人数据,并且提供查询功能
	    	 */
	    	$("#elderlyInfoSearch-table-list").datagrid({
	    	 title:message("yly.elderlyinfo"),
	    	 fitColumns:true,
	    	 url:'../visitElderly/elderlyInfoList.jhtml',  
	    	 pagination:true,
	    	 loadMsg:message("yly.common.loading"),
	    	 striped:true,
	    	 onDblClickRow : function (rowIndex, rowData){
	    	 },
	    	 columns:[
	    	    [
	    	       {field:'ck',checkbox:true},
	    	       {title:message("yly.consultation.vistor"),field:"vistor",width:100,sortable:true},
	    	       {title:message("yly.common.phonenumber"),field:"phoneNumber",width:100,sortable:true},
	    	    ]
	    	 ]

	    	});
	    	
	    }
	});  
}



