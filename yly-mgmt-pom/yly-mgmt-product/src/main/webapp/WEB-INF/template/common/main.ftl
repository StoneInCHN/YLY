<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
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
  </head>

  <body class="easyui-layout">   
	<div class="header" data-options="region:'north',split:true,noheader:true">
		<div class="logo">后台管理中心</div>
		<div id="nav-wrap" class="nav-wrap"  style="width:730px">
			<ul class="nav nav-pills">
				<li><a href="#main"><i class="fa fa-home fa-1x"></i>首页</a></li>
				<li><a href="#residential"><i class="fa fa-bed fa-1x"></i>${message("yly.residential.residential")}</a></li>
				<li><a href="#visitor"><i class="fa fa-pencil-square-o fa-1x"></i>${message("yly.visitelderly.visitorRegistration")}</a></li>
				<li><a href="#admissionAndLeave"><i class="fa fa-eye fa-1x"></i>${message("yly.elderlyInfo.admissionAndLeave")}</a></li>
				<li><a href="#chargeManage"><i class="fa fa-jpy fa-1x"></i>${message("yly.charge.manage")}</a></li>
				<li><a href="#nurseManage"><i class="fa fa-hand-paper-o fa-1x"></i>${message("yly.nurseManage.config")}</a></li>
				<li><a href="#volunteer"><i class="fa fa-gift fa-1x"></i>${message("yly.volunteer.config")}</a></li>
				<li><a href="#chargeConfig"><i class="fa fa-jpy fa-1x"></i>${message("yly.charge.congfig")}</a></li>
				<li><a href="#logistics"><i class="fa fa-get-pocket fa-1x"></i>${message("yly.logistics.config")}</a></li>
				<li><a href="#blacklist"><i class="fa fa-black-tie fa-1x"></i>${message("yly.blacklist.config")}</a></li>
				<li><a href="#seniorCitizens"><i class="fa fa-pencil fa-1x"></i>老人管理</a></li>
				<li><a href="#health"><i class="fa fa-users fa-1x"></i>健康管理</a></li>
				<li><a href="#affairs"><i class="fa fa-users fa-1x"></i>院内事务</a></li>
				<li><a href="#system"><i class="fa fa-users fa-1x"></i>系统管理</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<li><a href="#"><i class="fa fa-users fa-1x"></i>在院老人</a></li>
				<a href="#" id="nav-switcher" class="nav-switcher">更多<i class="fa fa-angle-down fa-1x"></i></a>
				<a id="nav-switcherset" href="#" class="router nav-switcherset off"><span class="middlehelper">设置</span><span><i class="fa fa-cog"></i></span></a>
			</ul>
		</div>
		<ul class="user-profile">
		    <li  class="dropdown" >
				  <a class="btn  dropdiown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    <i class="fa fa-cog"></i>
				   
				  </a>
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="#">个人信息</a></li>
				     <li><a href="#">修改密码</a></li>
				    <li role="separator" class="divider"></li>
				    <li><a href="${base}/console/common/logout.jhtml">退出</a></li>
				  </ul>
		    </li>
		</ul>
	</div>   
    <div class="footer" data-options="region:'south',split:true,noheader:true" ></div>   
    <div class="left-content" data-options="region:'west',title:'导航菜单',split:true" >
		    	<ul title="${message("yly.visitelderly.visitorRegistration")}" id="visitor">
		    		<li><a href="#" data-url="${base}/console/consultation/consultation.jhtml">${message("yly.consultation.record")}</a></li>
		    		<li><a href="#" data-url="${base}/console/visitElderly/visitElderly.jhtml">${message("yly.visitelderly.visitRegistration")}</a></li>
		    		<li><a href="#" data-url="${base}/console/bookingRegistration/bookingRegistration.jhtml">${message("yly.bookingRegistration")}</a></li>
		    		<li><a href="#">在院老人</a></li>
		    		<li><a href="#">床位状态</a></li>
		    		<li><a href="#">科室信息</a></li>
		    	</ul>
		        <ul title="${message("yly.elderlyInfo.AdmissionAndLeave")}" id="admissionAndLeave">
		    		<li><a href="#" data-url="${base}/console/admission/admission.jhtml">${message("yly.elderlyInfo.admission")}</a></li>
		    		<li><a href="#">办理出院</a></li>
		    		<li><a href="#">入院评估</a></li>
		    	</ul> 
		        <ul title="老人管理" id="seniorCitizens">
		    		<li><a href="#">老人档案</a></li>
		    		<li><a href="#">合同附件</a></li>
		    		<li><a href="#">评估报告</a></li>
		    		<li><a href="#">老人相册</a></li>
					<li><a href="#">老人事件</a></li>
		    		<li><a href="#">物品寄存</a></li>
		    	</ul>    
		        <ul class="active" id="residential" title="${message("yly.residential.residential")}">
		    		<li><a href="#" data-url="${base}/console/building/building.jhtml">${message("yly.residential.building")}</a></li>
		    		<li><a href="#" data-url="${base}/console/room/room.jhtml">${message("yly.residential.room")}</a></li>
		    		<li><a href="#" data-url="${base}/console/bed/bed.jhtml">${message("yly.residential.bed")}</a></li>
		    		<li><a href="#">${message("yly.residential.changeRoom")}</a></li>
		    	</ul>
		    	 <ul title="健康管理" id="health">
		    		<li><a href="#">病历档案</a></li>
		    		<li><a href="#">老人药方</a></li>
		    		<li><a href="#">体检记录</a></li>
		    		<li><a href="#" data-url="${base}/console/drugs/drugsInfo.jhtml">药品管理</a></li>
		    	</ul>
		    	<ul title="院内事务" id="affairs">
		    		<li><a href="#">固定资产</a></li>
		    		<li><a href="#">库存管理</a></li>
		    		<li><a href="#" data-url="${base}/console/donateRecord/donateRecord.jhtml">捐赠管理</a></li>
		    		<li><a href="#">通知公告</a></li>
		    	</ul>
		    	<ul title="系统管理" id="system">
		    		<li><a href="#">用户管理</a></li>
		    		<li><a href="#">权限角色管理</a></li>
		    		<li><a href="#">数据字典</a></li>
		    		<li><a href="#">系统日志</a></li>
		    	</ul>
		    	<ul title="${message("yly.charge.congfig")}" id="chargeConfig">
		    		<li><a href="#" data-url="${base}/console/bedChargeConfig/bedChargeConfig.jhtml">${message("yly.charge.bed")}</a></li>
		    		<li><a href="#" data-url="${base}/console/nurseChargeConfig/nurseChargeConfig.jhtml">${message("yly.charge.nurse")}</a></li>
		    		<li><a href="#" data-url="${base}/console/mealChargeConfig/mealChargeConfig.jhtml">${message("yly.charge.meal")}</a></li>
		    		<li><a href="#" data-url="${base}/console/waterElectricityChargeConfig/waterElectricityChargeConfig.jhtml">${message("yly.charge.water.electricity")}</a></li>
		    		<li><a href="#" data-url="${base}/console/personalizedChargeConfig/personalizedChargeConfig.jhtml">${message("yly.charge.personalized.service")}</a></li>
		    	</ul>     
		    	<ul title="${message("yly.nurseManage.config")}" id="nurseManage">
		    		<li><a href="#">${message("yly.nurse.arrange")}</a></li>
		    		<li><a href="#">${message("yly.nurse.schedule")}</a></li>
		    		<li><a href="#">${message("yly.nurse.plan")}</a></li>
		    		<li><a href="#">${message("yly.nurse.modify")}</a></li>
		    		<li><a href="#">${message("yly.nurse.personal")}</a></li>
		    	</ul>   
		    	<ul title="${message("yly.volunteer.config")}" id="volunteer">
		    		<li><a href="#" data-url="${base}/console/volunteer/volunteer.jhtml">${message("yly.volunteer.manage")}</a></li>
		    	</ul>   
		    	<ul title="${message("yly.charge.manage")}" id="chargeManage">
		    	    <li><a href="#" data-url="${base}/console/billing/checkinCharge.jhtml">${message("yly.charge.checkin")}</a></li>
		    		<li><a href="#" data-url="${base}/console/billing/checkoutCharge.jhtml">${message("yly.charge.checkout")}</a></li>
		    		<li><a href="#" data-url="${base}/console/billing/billing.jhtml">${message("yly.charge.billing")}</a></li>
		    		<li><a href="#" data-url="${base}/console/advanceCharge/advanceCharge.jhtml">${message("yly.charge.advance.manage")}</a></li>
		    		<li><a href="#" data-url="${base}/console/deposit/deposit.jhtml">${message("yly.charge.deposit.manage")}</a></li>
		    		<li><a href="#" data-url="${base}/console/bedNurseChargeRecord/bedNurseChargeRecord.jhtml">${message("yly.charge.bedNurse.record")}</a></li>
		    		<li><a href="#" data-url="${base}/console/mealChargeRecord/mealChargeRecord.jhtml">${message("yly.charge.meal.reocrd")}</a></li>
		    		<li><a href="#" data-url="${base}/console/waterElectricityChargeRecord/waterElectricityChargeRecord.jhtml">${message("yly.charge.water.electricity.reocrd")}</a></li>
		    		<li><a href="#" data-url="${base}/console/personalizedChargeRecord/personalizedChargeRecord.jhtml">${message("yly.charge.personalized.service.record")}</a></li>
		    	</ul>          
		    	<ul title="${message("yly.blacklist.config")}" id="blacklist">
		    		<li><a href="#"  data-url="${base}/console/blackList/blacklist.jhtml">${message("yly.blacklist.list")}</a></li>
		    	</ul>
		    	<ul title="${message("yly.logistics.config")}" id="logistics">
		    		<li><a href="#">${message("yly.logistics.maintainRecord")}</a></li>
		    		<li><a href="#">${message("yly.logistics.waterfeeRecord")}</a></li>
		    		<li><a href="#">${message("yly.logistics.energyChargeRecord")}</a></li>
		    	</ul>   
    </div>   
    <div class="main-content" data-options="region:'center'">
    	<div id="manager-tabs">   
		    <div title="起始页" style="padding:20px;">   
		        <p>欢迎来到管理系统</p>
		    </div>    
		</div> 
    </div>   
    
    <div id="searchElderlyInfo"></div>
    
    
    <!-- JavaScript-->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${base}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.serializejson.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${base}/resources/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/js/message.js"></script>
	<script type="text/javascript" src="${base}/resources/js/main.js"></script>
	<script type="text/javascript" src="${base}/resources/js/dropdown.js"></script>
	<script type="text/javascript" src="${base}/resources/js/validator.js"></script>
	<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
	<script src="${base}/resources/js/webuploader.min.js"></script>
	<script src="${base}/resources/js/fileUploadCommon.js"></script>
	<script>
		var BASE_URL = '${base}/resources' ;
	</script>
  </body>
</html>
