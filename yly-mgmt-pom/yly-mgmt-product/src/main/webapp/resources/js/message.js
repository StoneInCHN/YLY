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
	"yly.waterElectricity.charge.config" : "水电费配置",
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
	//waterElectricityChargeConfig
	"yly.charge.water.amount" : "水(元/吨)",
	"yly.charge.electricity.amount" : "电(元/度)",
	"yly.waterElectricityCharge.add" : "添加水电费配置",
	"yly.waterElectricityCharge.edit" : "编辑水电费配置",
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
//多语言
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