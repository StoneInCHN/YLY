[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="YLY养老院管理系统">
    <meta name="author" content="YLY">
    <link rel="icon" href="${base}/resources/images/favicon.ico">
    <title>管理中心</title>
    <link href="${base}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/resources/css/font-awesome.min.css" rel="stylesheet">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/easyui/themes/icon.css">
 	<link rel="stylesheet" type="text/css" href="${base}/resources/css/common.css">
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/main.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/webuploader.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/upload-style.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/uploadPhotos_style.css" >
 	<link rel="stylesheet" type="text/css"href="${base}/resources/css/newAlbum_style.css" >
 	<link rel="stylesheet" type="text/css" href="${base}/resources/jcarousel/_shared/css/style.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/jcarousel/connected-carousels/jcarousel.connected-carousels.css">
    <link rel="stylesheet" type="text/css" href="${base}/resources/css/evaluting.css">
	<!--[if lt IE 9]>
    <script type="text/javascript" src="${base}/resources/js/respond.1.4.2.min.js"></script>
    <![endif]-->
  </head>

  <body class="easyui-layout" >   
	<div class="header" data-options="region:'north',split:true,noheader:true,collapse:'west'">
		<div class="logo">后台管理中心</div>
		<div id="nav-wrap" class="nav-wrap"  style="width:730px">
			<ul class="nav nav-pills">
				<li><a href="#main"><i class="fa fa-home fa-1x"></i>首页</a></li>
				[@shiro.hasPermission name="residential"]
				<li><a href="#residential"><i class="fa fa-bed fa-1x"></i>${message("yly.residential.residential")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="visitorRegistration"]
				<li><a href="#visitor"><i class="fa fa-pencil-square-o fa-1x"></i>${message("yly.visitelderly.visitorRegistration")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="admissionAndLeave"]
				<li><a href="#admissionAndLeave"><i class="fa fa-eye fa-1x"></i>${message("yly.elderlyInfo.admissionAndLeave")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="chargeManage"]
				<li><a href="#chargeManage"><i class="fa fa-jpy fa-1x"></i>${message("yly.charge.manage")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="nurseManage"]
				<li><a href="#nurseManage"><i class="fa fa-hand-paper-o fa-1x"></i>${message("yly.nurseManage.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="volunteerMain"]
				<li><a href="#volunteerMain"><i class="fa fa-gift fa-1x"></i>${message("yly.volunteer.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="chargeConfig"]
				<li><a href="#chargeConfig"><i class="fa fa-jpy fa-1x"></i>${message("yly.charge.congfig")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="logistics"]
				<li><a href="#logistics"><i class="fa fa-get-pocket fa-1x"></i>${message("yly.logistics.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="blackList"]
				<li><a href="#blacklist"><i class="fa fa-black-tie fa-1x"></i>${message("yly.blacklist.config")}</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="elderlyMange"]
				<li><a href="#seniorCitizens"><i class="fa fa-pencil fa-1x"></i>老人管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="healthManage"]
				<li><a href="#health"><i class="fa fa-users fa-1x"></i>健康管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="affairs"]
				<li><a href="#affairs"><i class="fa fa-users fa-1x"></i>院内事务</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="systemManage"]
				<li><a href="#system"><i class="fa fa-users fa-1x"></i>系统管理</a></li>
				[/@shiro.hasPermission]
				[@shiro.hasPermission name="statisticalReports"]
				<li><a href="#statisticalReports"><i class="fa fa-users fa-1x"></i>统计报表</a></li>
				[/@shiro.hasPermission]
				
				[@shiro.hasPermission name="personnelManage"]
				<li><a href="#personnel"><i class="fa fa-users fa-1x"></i>${message("yly.personnel.config")}</a></li>
				[/@shiro.hasPermission]
				<a href="#" id="nav-switcher" class="nav-switcher">更多<i class="fa fa-angle-down fa-1x"></i></a>
				<a id="nav-switcherset" href="#" class="router nav-switcherset off"><span class="middlehelper">设置</span><span><i class="fa fa-cog"></i></span></a>
			</ul>
		</div>
		<div class="welcome pull-right">
		<marquee scrollamount='2'>欢迎 ${tenantAccount.userName}登录！</marquee></div>
		<ul class="user-profile">
		    <li  class="dropdown" >
				  <a class="btn  dropdiown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    <i class="fa fa-cog"></i>
				   
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				     <li><a href="#" id="changePasswordHref">修改密码</a></li>
				    <li role="separator" class="divider"></li>
				    <li><a href="${base}/console/common/logout.jhtml">退出</a></li>
				  </ul>
		    </li>
		</ul>
	</div>   
    <div class="footer" data-options="region:'south',split:true,noheader:true" ></div>   
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true,width:115" >
		    	<ul title="${message("yly.visitelderly.visitorRegistration")}" id="visitor">
		    		[@shiro.hasPermission name="consultation"]
		    		<li><a href="#" data-url="${base}/console/consultation/consultation.jhtml">${message("yly.consultation.record")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="visitElderly"]
		    		<li><a href="#" data-url="${base}/console/visitElderly/visitElderly.jhtml">${message("yly.visitelderly.visitRegistration")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="bookingRegistration"]
		    		<li><a href="#" data-url="${base}/console/bookingRegistration/bookingRegistration.jhtml">${message("yly.bookingRegistration")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="checkedInElderly"]
		    		<li><a href="#" data-url="${base}/console/elderlyInfo/checkedInElderly.jhtml">${message("yly.elderlyInfo.checkedInElderly")}</a></li>
		    		[/@shiro.hasPermission]
		    		<li><a href="#" data-url="${base}/console/selectRoom/selectRoom.jhtml">床位状态</a></li>
		    		<li><a href="#">科室信息</a></li>
		    	</ul>
		        <ul title="${message("yly.elderlyInfo.AdmissionAndLeave")}" id="admissionAndLeave">
		    		[@shiro.hasPermission name="admission"]
		    		<li><a href="#" data-url="${base}/console/admission/admission.jhtml">${message("yly.elderlyInfo.admission")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="checkoutCharge"]
		    		<li><a href="#" data-url="${base}/console/checkout/checkoutCharge.jhtml">${message("yly.charge.checkoutCharge")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="checkinCharge"]
		    		<li><a href="#" data-url="${base}/console/billing/checkinCharge.jhtml">${message("yly.charge.checkin")}</a></li>
		    		[/@shiro.hasPermission]

		    		[@shiro.hasPermission name="elderlyEvaluatingRecord"]
		    		<li><a href="#" data-url="${base}/console/elderlyEvaluatingRecord/elderlyEvaluatingRecord.jhtml">${message("yly.elderlyInfo.evaluating")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul> 
		        <ul title="老人管理" id="seniorCitizens">
		    		<li><a href="#">老人档案</a></li>
		    		<li><a href="#">合同附件</a></li>
		    		<li><a href="#">评估报告</a></li>
		    		[@shiro.hasPermission name="elderlyPhotoAlbum"]
		    		<li><a href="#" data-url="${base}/console/elderlyPhotoAlbum/elderlyPhotoAlbum.jhtml">${message("yly.elderlyInfo.photoAlbum")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="elderlyEventRecord"]
					<li><a href="#" data-url="${base}/console/elderlyEventRecord/elderlyEventRecord.jhtml">${message("yly.elderlyInfo.event.record")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="elderlyStuffDeposit"]
		    		<li><a href="#" data-url="${base}/console/elderlyStuffDeposit/elderlyStuffDeposit.jhtml">${message("yly.elderlyInfo.stuff.deposit")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>    
		        <ul class="active" id="residential" title="${message("yly.residential.residential")}">
		    		[@shiro.hasPermission name="building"]
		    		<li><a href="#" data-url="${base}/console/building/building.jhtml">${message("yly.residential.building")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="room"]
		    		<li><a href="#" data-url="${base}/console/room/room.jhtml">${message("yly.residential.room")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="bed"]
		    		<li><a href="#" data-url="${base}/console/bed/bed.jhtml">${message("yly.residential.bed")}</a></li>
		    		[/@shiro.hasPermission]
		    		<li><a href="#" data-url="${base}/console/changeRoom/changeRoom.jhtml">${message("yly.residential.changeRoom")}</a></li>
		    	</ul>
		    	 <ul title="健康管理" id="health">
		    		<li><a href="#" data-url="${base}/console/medicalRecord/medicalRecord.jhtml">病历档案</a></li>
		    		[@shiro.hasPermission name="prescription"]
		    		<li><a href="#" data-url="${base}/console/prescription/prescription.jhtml">老人药方</a></li>
		    		[/@shiro.hasPermission ]
		    		[@shiro.hasPermission name="physicalExamination"]
		    		<li><a href="#" data-url="${base}/console/physicalExamination/physicalExamination.jhtml">体检记录</a></li>
		    		[/@shiro.hasPermission ]
		    		[@shiro.hasPermission name="drugsInfo"]
		    		<li><a href="#" data-url="${base}/console/drugs/drugsInfo.jhtml">药品管理</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>
		    	<ul title="院内事务" id="affairs">
		    		[@shiro.hasPermission name="fixedAssets"]
		    		<li><a href="#" data-url="${base}/console/fixedAssets/fixedAssets.jhtml">固定资产</a></li>
		    		[/@shiro.hasPermission]
		    		<li><a href="#" >库存管理</a></li>
		    		[@shiro.hasPermission name="donateRecord"]
		    		<li><a href="#" data-url="${base}/console/donateRecord/donateRecord.jhtml">捐赠管理</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="notification"]
		    		<li><a href="#" data-url="${base}/console/notification/notification.jhtml" >通知公告</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="industryInformation"]
		    		<li><a href="#" data-url="${base}/console/industryInformation/industryInformation.jhtml" >业内资讯</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>
		    	<ul title="系统管理" id="system">
		    		[@shiro.hasPermission name="tenantUser"]
		    		<li><a href="#" data-url="${base}/console/tenantAccount/tenantAccount.jhtml">用户管理</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="role"]
		    		<li><a href="#" data-url="${base}/console/role/role.jhtml">权限角色管理</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="systemConfig"]
		    		<li><a href="#" data-url="${base}/console/systemConfig/systemConfig.jhtml">数据字典</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="operationLog"]
		    		<li><a href="#" data-url="${base}/console/operationLog/operationLog.jhtml">系统日志</a></li>
		    		[/@shiro.hasPermission]
					<li><a href="#" data-url="${base}/console/importData/importData.jhtml">导入数据</a></li>
		    	</ul>
		    	<ul title="统计报表" id="statisticalReports">
		    		<!--[@shiro.hasPermission name="reportElderlyStatus"]
		    		<li><a href="#" data-url="${base}/console/tenantAccount/tenantAccount.jhtml">用户管理</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportSexualProportion"]
		    		<li><a href="#" data-url="${base}/console/role/role.jhtml">男女比例</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportDonate"]
		    		<li><a href="#" data-url="${base}/console/role/role.jhtml">捐赠统计</a></li>
		    		[/@shiro.hasPermission]
		    		-->
		    		[@shiro.hasPermission name="reportEvluatingResult"]
		    		<li><a href="#" data-url="${base}/console/reportEvaluatingResult/reportEvaluatingResult.jhtml">评估结果统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportLiveStatitics"]
		    		<li><a href="#" data-url="${base}/console/reportElderlyLiveStatitics/reportElderlyLiveStatitics.jhtml">居住统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportElderlyMedicalRecord"]
		    		<li><a href="#" data-url="${base}/console/reportElderlyMedicalRecord/reportElderlyMedicalRecord.jhtml">老人看病统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportElderlyEvent"]
		    		<li><a href="#" data-url="${base}/console/reportElderlyEvent/reportElderlyEvent.jhtml">老人事件统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportWaterElectricityRecord"]
		    		<li><a href="#" data-url="${base}/console/reportWaterElectricityRecord/reportWaterElectricityRecord.jhtml">水电统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportNurseLevelStatistics"]
		    		<li><a href="#" data-url="${base}/console/reportNurseLevelStatistics/reportNurseLevelStatistics.jhtml">护理级别统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportDonateStatistics"]
		    		<li><a href="#" data-url="${base}/console/reportDonateStatistics/reportDonateStatistics.jhtml">捐赠统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportRepairRecord"]
		    		<li><a href="#" data-url="${base}/console/reportRepairRecord/reportRepairRecord.jhtml">维修记录统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportBookingRegistration"]
		    		<li><a href="#" data-url="${base}/console/reportBookingRegistration/reportBookingRegistration.jhtml">预约登记统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportChargeStatistics"]
		    		<li><a href="#" data-url="${base}/console/reportChargeStatistics/reportChargeStatistics.jhtml">缴费统计</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="reportAdvanceCharge"]
		    		<li><a href="#" data-url="${base}/console/reportAdvanceCharge/reportAdvanceCharge.jhtml">缴费统计</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>
		    	<ul title="${message("yly.charge.congfig")}" id="chargeConfig">
		    		[@shiro.hasPermission name="bedChargeConfig"]
		    		<li><a href="#" data-url="${base}/console/bedChargeConfig/bedChargeConfig.jhtml">${message("yly.charge.bed")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="nurseChargeConfig"]
		    		<li><a href="#" data-url="${base}/console/nurseChargeConfig/nurseChargeConfig.jhtml">${message("yly.charge.nurse")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="mealChargeConfig"]
		    		<li><a href="#" data-url="${base}/console/mealChargeConfig/mealChargeConfig.jhtml">${message("yly.charge.meal")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="waterElectricityChargeConfig"]
		    		<li><a href="#" data-url="${base}/console/waterElectricityChargeConfig/waterElectricityChargeConfig.jhtml">${message("yly.charge.water.electricity")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="personalizedChargeConfig"]
		    		<li><a href="#" data-url="${base}/console/personalizedChargeConfig/personalizedChargeConfig.jhtml">${message("yly.charge.personalized.service")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>     
		    	<ul title="${message("yly.nurseManage.config")}" id="nurseManage">
		    		[@shiro.hasPermission name="nurseDutyType"]
		    		<li><a href="#" data-url="${base}/console/nurseDutyType/nurseDutyType.jhtml">${message("yly.nurse.nurseDutyType")}</a></li>
					[/@shiro.hasPermission]
					[@shiro.hasPermission name="nurseArrangement"]
					<li><a href="#" data-url="${base}/console/nurseArrangement/nurseArrangement.jhtml">${message("yly.nurse.arrange")}</a></li>
		    		[/@shiro.hasPermission]
					[@shiro.hasPermission name="nurseSchedule"]
					<li><a href="#" data-url="${base}/console/nurseSchedule/nurseSchedule.jhtml">${message("yly.nurse.schedule")}</a></li>
		    		[/@shiro.hasPermission]
					[@shiro.hasPermission name="nursePlan"]
		    		<li><a href="#" data-url="${base}/console/nursePlan/nursePlan.jhtml">${message("yly.nurse.plan")}</a></li>
		    		[/@shiro.hasPermission]
					[@shiro.hasPermission name="nurseLevelChangeRecord"]
					<li><a href="#"data-url="${base}/console/nurseLevelChangeRecord/nurseLevelChangeRecord.jhtml">${message("yly.nurse.modify")}</a></li>
		    		[/@shiro.hasPermission]
					[@shiro.hasPermission name="personalizedNurse"]
		    		<li><a href="#" data-url="${base}/console/personalizedNurse/personalizedNurse.jhtml">${message("yly.nurse.personal")}</a></li>
		    		[/@shiro.hasPermission]
				</ul>
		    	
		    	<ul title="${message("yly.volunteer.config")}" id="volunteerMain">
		    		[@shiro.hasPermission name="volunteer"]  
		    		<li><a href="#" data-url="${base}/console/volunteer/volunteer.jhtml">${message("yly.volunteer.manage")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>   
		    	
		    	<ul title="${message("yly.charge.manage")}" id="chargeManage">
		    		[@shiro.hasPermission name="checkinPay"]
		    	    <li><a href="#" data-url="${base}/console/billing/checkinPay.jhtml">${message("yly.pay.checkin")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="billing"]
		    		<li><a href="#" data-url="${base}/console/billing/dailyBill.jhtml">${message("yly.charge.billing")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="advanceCharge"]
		    		<li><a href="#" data-url="${base}/console/advanceCharge/advanceCharge.jhtml">${message("yly.charge.advance.manage")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="deposit"]
		    		<li><a href="#" data-url="${base}/console/deposit/deposit.jhtml">${message("yly.charge.deposit.manage")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="bedNurseChargeRecord"]
		    		<li><a href="#" data-url="${base}/console/bedNurseChargeRecord/bedNurseChargeRecord.jhtml">${message("yly.charge.bedNurse.record")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="mealChargeRecord"]
		    		<li><a href="#" data-url="${base}/console/mealChargeRecord/mealChargeRecord.jhtml">${message("yly.charge.meal.reocrd")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="waterElectricityChargeRecord"]
		    		<li><a href="#" data-url="${base}/console/waterElectricityChargeRecord/waterElectricityChargeRecord.jhtml">${message("yly.charge.water.electricity.reocrd")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="personalizedChargeRecord"]
		    		<li><a href="#" data-url="${base}/console/personalizedChargeRecord/personalizedChargeRecord.jhtml">${message("yly.charge.personalized.service.record")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="checkoutPay"]
		    		<li><a href="#" data-url="${base}/console/checkout/checkoutPay.jhtml">${message("yly.charge.checkoutPay")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>       
		    	[@shiro.hasPermission name="blackList"]   
		    	<ul title="${message("yly.blacklist.config")}" id="blacklist">
		    		<li><a href="#"  data-url="${base}/console/blackList/blacklist.jhtml">${message("yly.blacklist.list")}</a></li>
		    	</ul>
		    	[/@shiro.hasPermission]
		    	<ul title="${message("yly.logistics.config")}" id="logistics">
		    		[@shiro.hasPermission name="repairRecord"]
		    		<li><a href="#" data-url="${base}/console/repairRecord/repairRecord.jhtml">${message("yly.logistics.maintainRecord")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="waterElectricityRecord"]
		    		<li><a href="#" data-url="${base}/console/waterElectricityRecord/waterElectricityRecord.jhtml">${message("yly.logistics.waterfeeRecord")}</a></li>
		    		[/@shiro.hasPermission]
		    	</ul>
		    	<ul title="${message("yly.personnel.config")}" id="personnel">
		    		[@shiro.hasPermission name="department"]
		    		<li><a href="#" data-url="${base}/console/department/department.jhtml">${message("yly.personnel.department")}</a></li>
		    		[/@shiro.hasPermission]
		    		[@shiro.hasPermission name="position"]
		    		<li><a href="#" data-url="${base}/console/position/position.jhtml">${message("yly.personnel.position")}</a></li>
		    		[/@shiro.hasPermission]
		    		<li><a href="#" data-url="${base}/console/tenantUser/tenantUser.jhtml">${message("yly.personnel.tenantuser")}</a></li>
		    		<li><a href="#" >${message("yly.personnel.vacate")}</a></li>
		    	</ul>    
    </div>
  
    <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页">
					<div class="main-content-left">
				        <div class="shortcutNavigation">
				        	<a href ="#"><img onclick="shortcutNavigation('办理入院','${base}/console/admission/admission.jhtml')" src="${base}/resources/images/admission.png"/></a><a href ="#"><img onclick="shortcutNavigation('入院评估','${base}/console/elderlyEvaluatingRecord/elderlyEvaluatingRecord.jhtml')" src="${base}/resources/images/evaluating.png"/></a><a href ="#"><img onclick="shortcutNavigation('入院缴费','${base}/console/billing/checkinCharge.jhtml')" src="${base}/resources/images/checkinCharge.png"/></a><a href ="#"><img onclick="shortcutNavigation('日常缴费','${base}/console/billing/dailyBill.jhtml')" src="${base}/resources/images/normalCharge.png"/></a><a href ="#"><img onclick="shortcutNavigation('退住结算','${base}/console/admission/admission.jhtml')" src="${base}/resources/images/checkoutCharge.png"/></a>
				        </div>
						<div class="row index-report">
								<div class="col-md-4 index-report-item">
									<div id="elderlyStatusReportId"></div>
								</div>
								 <div class="col-md-4 index-report-item">
									<div id="elderlyAgeReportId"></div>
								</div>
								 <div class="col-md-4 index-report-item">
									<div id="elderlyLivingMainReportId"></div>
								</div>
						</div>
						<div class="row index-report">
								<div class="col-md-8 index-report-item">
						    		<div id="elderlyStatusInReportId" style="height:300px;width: 524px;"></div>
						    	</div>
						    	<div class="col-md-4">
				    				<div class="mini-widget" style="height:80px;width: 260px;margin-top:30px;">
						                <div class="mini-widget-heading clearfix">本月老人</div>
						                <div class="mini-widget-body clearfix">
						                  <div id="elderlyNewComming"class="pull-left number"></div>
						                  <div class="pull-right">
						                  	环比：<div id="increasePercent"></div>
						                  </div>
						                </div>
             				 	</div>
	             				 <div class="mini-widget" style="height:80px;width: 260px;">
					                <div class="mini-widget-heading ">男女比例</div>
					                <div class="mini-widget-body">
					                	<ul>
					                		<li>
					                		  <span class="fa fa-male  male"></span>
							                  <span id="elderlyGenderMale" class="pull-right number"></span>
					                		</li>
					                		<li >
					                		  <span class="fa fa-female female"></span>
							                  <span id="elderlyGenderFemale" class="pull-right number"></span>
					                		</li>
					                	</ul>
					                </div>
	             				 </div>
				    	</div><!--end col-->
						
						</div>
					</div>
					<div class="main-content-right">
						<div id="calendar-panel" class="easyui-panel" title="日历" 
							style="width:200px;height:220px;padding:10px;background:#fafafa;"data-options="collapsible:true">
							<div id="cc" class="easyui-calendar" style="width:100%;height:100%;"></div>  
						</div>
						<div id="notify" class="easyui-panel" title="通知"     
						        style="width:200px;height:180px;padding:10px;pbackground:#fafafa;" data-options="collapsible:true">
					          <ul id="notify-content" class="notify" style="overflow-y: hidden; height: 160px;">
							  </ul>
					    </div>
					    <div id="industryInformation" class="easyui-panel" title="业内资讯"     
						        style="width:200px;height:180px;padding:1px;background:#fafafa;" data-options="collapsible:true">
					          <ul id="industryInformation-content" class="industryInformation" style="overflow-y: hidden; height: 120px;">
							</ul>
					    </div> 
					</div>
		    </div>    
		</div>  
    </div>    
    
    <div id="searchElderlyInfo"></div>
    <div id="selectRoom"></div>
    <div id="searchAlbum"></div>
    <div id = "searchRoles"></div>
    <div id = "searchTenantUser"></div>
    <div id = "searchNurseArrangement"></div>
    <div id = "searchBed"></div>
    <div id = "changePassword">
    </div>
    <!-- JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resources/js/validator.js"></script>
	<script type="text/javascript" src="${base}/resources/js/highcharts.js"></script>
	<script type="text/javascript" src="${base}/resources/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/js/message.js"></script>
	<script type="text/javascript" src="${base}/resources/js/main.js"></script>
	<script type="text/javascript" src="${base}/resources/js/dropdown.js"></script>
	<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${base}/resources/js/webuploader.min.js"></script>
	<script type="text/javascript" src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
	<script src="${base}/resources/js/jquery.bootstrap.newsbox.min.js" type="text/javascript"></script>
	<script src="${base}/resources/js/fileUploadCommon.js"></script>
    <script src="${base}/resources/js/multiplefileUpload.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.easing.1.3.js"></script>
	<script>
		var BASE_URL = '${base}/resources' ;
	</script>
  </body>
</html>
