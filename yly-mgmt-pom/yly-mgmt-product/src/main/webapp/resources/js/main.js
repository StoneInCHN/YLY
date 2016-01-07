$(function(){
	
	
	/**
	 *初始化右侧的选项卡
	 */
	$("#manager-tabs").tabs({
		fit:true,
		border:false
	});
	
	
	$("#nav-switcher").mouseover(function(){
		$(".nav-wrap").addClass("nav-silde");
		$("#nav-switcherset").show();
	})
	$("#nav-wrap ul").mouseleave(function(){
		$("#nav-wrap").removeClass("nav-silde");
		$("#nav-switcherset").hide();
	})
	
	$("#dropdownMenu1").dropdown();
	 
	$("#nav-wrap > ul >li >a").click(function(){
		var $this = $(this);
		$(".left-content > ul").hide();
		$($this.attr("href")).show();
	})
	
	/**
	 *绑定左侧导航条的点击事件 
	 */
	$(".left-content a").click(function(){
		var _this = $(this);
		var _url = _this.attr("data-url");
		if(_this.text()){
			if($('#manager-tabs').tabs("exists",_this.text())){
				$('#manager-tabs').tabs("select",_this.text())
			}else{
				$('#manager-tabs').tabs('add',{    
				    title:_this.text(),    
				    href:_url,    
				    closable:true      
				}); 
			}
		}
	})
	
	$("#selectRoom").click(function(){
		$("#searchElderlyInfo").dialog({    
		    title: '选床',    
		    width: 1000,    
		    height: 500,    
		    closed: false,    
		    cache: true,    
		    href: '../room/changeRoom.jhtml',    
		    modal: true,
		    onLoad:function(){
		    	 
		    },
		    onClose:function(){
		    	$('#searchElderlyInfo').empty;  
		    }
		}); 
		
	});
	
	//老人在院情况统计
	var elderlyStatusReport ={
		colors: ['#DDDF00','#058DC7', '#50B432', '#ED561B',  '#000000', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
        chart: {
        	renderTo: 'elderlyStatusReportId',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            borderWidth: null
        },
        credits:{
            enabled:false // 禁用版权信息
        },
        title: {
            text: '老人在院情况统计'
        },
        tooltip: {
        	  pointFormat:  '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        legend:{
            labelFormatter:function(){
                return this.name+':'+ this.y;
            },
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
                 type: 'pie',
                 name:'占比',
                 data: []
        }]
    };
	loadDataPie(elderlyStatusReport,'../../console/reportElderlyStatus/list.jhtml',null,
			['出院','在院','办理入院','办理出院','过世'],
			['outNursingHome','inNursingHome','inProcessCheckin','inProcessCheckout','dead'])
	//在院老人年龄段统计
	var elderlyAgeReport = {
		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
		credits:{
            enabled:false // 禁用版权信息
        },
        chart: {
        	renderTo: 'elderlyAgeReportId',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            borderWidth: null,
        },
        title: {
            text: '在院老人年龄段统计'
        },
        tooltip: {
            pointFormat: '{series.name}:{point.y}</b>'
        },
        plotOptions: {
        	 pie: {
                 allowPointSelect: true,
                 cursor: 'pointer',
                 dataLabels: {
                     enabled: false
                 },
                 showInLegend: true
             }
        },
        series: [{
            type: 'pie',
            name:'人数',
            data: []
        }]
	};
	loadDataPie(elderlyAgeReport,'../../console/reportElderlyAgeStatus/list.jhtml',null,
			['60岁以下','61~65岁','66~70岁','71~75岁','76岁以上'],
			['under60','range61To65','range66To70','range71To75','above76']);
	//男女比例
	var elderlyGenderRateReport={
		credits:{
            enabled:false // 禁用版权信息
        },
        chart: {
        	renderTo: 'elderlyGenderRateReportId',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: true
        },
        title: {
            text: '男女比例'
        },
        tooltip: {
            pointFormat: '{series.name}:{series.y}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
        		showInLegend: true
            }
        },
        series: [{
            type: 'pie',
            name:'人数',
            data: []
        }]
    };
	loadDataPie(elderlyGenderRateReport,'../../console/reportElderlyGenderRate/list.jhtml',null,
			['男','女'],
			['male','female']);
	//每月看病人数统计
	 $(function () {
         $('#elderlyMedicalReport').highcharts({
        	 colors:['#004B97'],
        	 chart:{
        		 backgroundColor: {
         			linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
         			stops: [
         				[0, 'rgb(255, 255, 255)'],
         				[1, 'rgb(240, 240, 255)']
         			]
         		},
         		plotBackgroundColor: 'rgba(255, 255, 255, .9)',
         		plotBorderWidth: 1
        	 },
             title: {
                 text: '每月看病人数统计',
                 x: -20 //center
             },
             credits:{
                 enabled:false // 禁用版权信息
             },
             xAxis: {
            	 gridLineWidth: 1,
         		 lineColor: '#000',
                 categories: ['一月', '二月', '三月', '四月', '五月', '六月','七月', '八月', '九月', '十月', '十一月', '十二月']
             },
             yAxis: {
            	 minorTickInterval: 'auto',
         		lineColor: '#000',
         		lineWidth: 1,
         		tickWidth: 1,
         		tickColor: '#000',
                 title: {
                     text: '人数'
                 },
                 plotLines: [{
                     value: 0,
                     width: 1,
                     color: '#808080'
                 }]
             },
             tooltip: {
                 valueSuffix: '人'
             },
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'middle',
                 borderWidth: 0
             },
             series: [{
                 name: '人数',
                 data: [7, 8, 9, 14, 18, 21, 250, 26, 10, 180, 13, 9]
             }]
         });
     });
})