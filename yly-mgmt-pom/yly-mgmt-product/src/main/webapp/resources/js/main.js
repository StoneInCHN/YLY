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
	});
	
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
			['outNursingHome','inNursingHome','inProcessCheckin','inProcessCheckout','dead'],null,null)
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
			['under60','range61To65','range66To70','range71To75','above76'],null,null);
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
			['male','female'],null,null);
	//每月看病人数统计
	var elderLivingMainReport={
		colors: ['#50B432', '#058DC7', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
        chart: {
        	renderTo: 'elderlyLivingMainReportId',
            type: 'column'
        },
        credits:{
            enabled:false // 禁用版权信息
        },
        title: {
            text: '居住情况'
        },
        xAxis: {
            categories: []
        },
        yAxis: {
            min: 0,

            stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        legend: {
            align: 'right',
            x: -70,
            verticalAlign: 'top',
            y: 20,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br/>'+
                    this.series.name +': '+ this.y +'<br/>'+
                    'Total: '+ this.point.stackTotal;
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: false,
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                },
                pointWidth: 20
            }
        },
        series: [ {
            name: '未使用',
            data: []
        },
        {
            name: '已使用',
            data: []
        }]
    };
	var chart = new Highcharts.Chart(elderLivingMainReport);
	$.ajax({
		url : "../../console/reportElderlyLiveStatitics/report.jhtml",
		type : "post",
		cache : false,
		success : function(data) {
			 if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					
					elderLivingMainReport.series[0].data.push(data[i].totalCount-data[i].inUsingCount);
					elderLivingMainReport.series[1].data.push(data[i].inUsingCount);
					elderLivingMainReport.xAxis.categories.push(data[i].roomType.configValue);
				}

			}
			var chart = new Highcharts.Chart(elderLivingMainReport);
		}

	});
	//右侧通知栏信息
	$.ajax({
		url : "../../console/notification/showOnMain.jhtml",
		type : "get",
		cache : false,
		success : function(data) {
			for(var i=0 ; i<data.length; i++){
				var content = formatLongString(data[i].content, 20)
				$("#notify-content")
				.append('<li class="news-item">'+data[i].content+ '<a href="#" data-url="../../console/notification/showOne.jhtml?id='+data[i].id+'" style="float:right">Read more...</a></li>');	
			}
			
			$(".notify").bootstrapNews({
		        newsPerPage: 4,
		        autoplay: true,
				pauseOnHover: true,
				navigation: false,
		        direction: 'down',
		        newsTickerInterval: 2000,
		        onToDo: function () {
		            //console.log(this);
		        }
		    });
			
			/**
			 *绑定右侧点击事件 
			 */
			$("#notify-content .news-item a").click(function(){
				var _this = $(this);
				var _url = _this.attr("data-url");
				if($('#manager-tabs').tabs("exists","通知消息")){
					$('#manager-tabs').tabs("select","通知消息");

					// 调用 'refresh' 方法更新选项卡面板的内容
					var tab = $('#manager-tabs').tabs('getSelected');  // 获取选择的面板
					tab.panel('refresh', _url);
				}else{
					$('#manager-tabs').tabs('add',{    
					    title:"通知消息",    
					    href:_url,    
					    closable:true      
					}); 
				}
			});
		}

	});
	
	
})