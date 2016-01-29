/**
 *绑定右侧点击事件 
 */
function clickNotificationNews(event) {
	var _this = $(event.target);
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
};
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
	
	/**
	 *绑定流程点击事件 
	 */
	$(".content a").click(function(){
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
	//右侧通知栏信息
	$.ajax({
		url : "../../console/notification/showOnMain.jhtml",
		type : "get",
		cache : false,
		success : function(data) {
			for(var i=0 ; i<data.length; i++){
				var title = formatLongString(data[i].title, 5)
				$("#notify-content")
				.append('<li class="news-item">'+title+ '<a href="#" data-url="../../console/notification/showOne.jhtml?id='+data[i].id+'" style="float:right" onClick="clickNotificationNews(event)">Read more...</a></li>');	
			}
			
			$(".notify").bootstrapNews({
		        newsPerPage: 4,
		        autoplay: true,
				pauseOnHover: true,
				navigation: false,
		        direction: 'down',
		        newsTickerInterval: 4000
		    });
		}
	});
	//老人在院情况统计
	var elderlyStatusReport ={
		colors: ['#DDDF00','#058DC7', '#50B432', '#ED561B',  '#000000', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
        chart: {
        	backgroundColor: {
    			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
    			stops: [
    				[0, 'rgb(220, 220, 220)'],
    				[1, 'rgb(211, 211, 211)']
    			]
    		},
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
	loadDataPie(elderlyStatusReport,'../../console/reportElderlyStatus/reportElderlyStatusOnMain.jhtml',null,
			['出院','在院','办理入院','办理出院','过世'],
			['outNursingHome','inNursingHome','inProcessCheckin','inProcessCheckout','dead'],null,null)
	//在院老人年龄段统计
	var elderlyAgeReport = {
		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
		credits:{
            enabled:false // 禁用版权信息
        },
        chart: {
        	backgroundColor: {
    			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
    			stops: [
    				[0, 'rgb(220, 220, 220)'],
    				[1, 'rgb(211, 211, 211)']
    			]
    		},
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

	//居住情况
	 var colors = ['#058DC7', '#50B432', '#ED561B',  '#000000', '#64E572', '#FF9655', '#FFF263', '#6AF9C4','#DDDF00'];
	// Create the chart
	var elderLivingMainReport={
	                chart: {
	                	backgroundColor: {
	            			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
	            			stops: [
	            				[0, 'rgb(220, 220, 220)'],
	            				[1, 'rgb(211, 211, 211)']
	            			]
	            		},
	                    type: 'pie',
	                    renderTo:"elderlyLivingMainReportId"
	                },
	                title: {
	                    text: '房间状态'
	                },
	                yAxis: {
	                    title: {
	                        text: 'Total percent market share'
	                    }
	                },
	                credits : {
	                    enabled : false
	                    // 禁用版权信息
	                },
	                plotOptions: {
	                    pie: {
	                        allowPointSelect: false,
	                        cursor: 'pointer',
	                        dataLabels: {
	                            enabled: true,
	                            color: '#000000',
	                            connectorColor: '#000000',
	                            formatter: function () {
	                                return '<b>' + this.point.name + '</b>: ' + this.percentage.toFixed(3) + ' %';
	                            }
	                        },
	                        showInLegend: true
	                    }
	                },

	                series: [{
	                    name: '床位',
	                    size: '80%',
	                    data:[],
	                    dataLabels: false,
	                    tooltip: {
	                        valueSuffix: '张'
	                    },
	                    point: {
	                        events: {
	                            legendItemClick: function() {
	                                return false;
	                            }
	                        }
	                    },
	                }, {
	                    name: '床位',
	                    data:[],
	                    size: '50%',
	                    innerSize: '0%',
	                    dataLabels: {
	                        formatter: function() {
	                            return this.y > 5 ? this.point.name : null;
	                        },
	                        color: 'white',
	                        distance: -30
	                    },
	                    tooltip: {
	                        valueSuffix: '张'
	                    },
	                    showInLegend: false
	                }]
	};



	$.ajax({
		url : "../../console/reportElderlyLiveStatitics/report.jhtml",
		type : "post",
		cache : false,
		success : function(data) {
			 if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var brightness = 0.2 ;
					elderLivingMainReport.name=data[i].roomType.configValue;
					elderLivingMainReport.series[1].data.push(
						{
		                    name: '已使用',
		                    y: data[i].inUsingCount,
		                    color: Highcharts.Color(colors[i]).brighten(0.1).get()
		                });
					elderLivingMainReport.series[1].data.push(
						{
		                    name: '未使用',
		                    y: data[i].totalCount-data[i].inUsingCount,
		                    color: colors[i]
		                    
		                });
					elderLivingMainReport.series[0].data.push({
	                    name: data[i].roomType.configValue,
	                    y: data[i].totalCount,
	                    color: colors[i]
	                }
					);
				}

			}
			var chart = new Highcharts.Chart(elderLivingMainReport);
		}
	});
	var reportElderlyStatusInRecord = {
			colors : [ '#004B97' ],
			chart : {
				renderTo : 'elderlyStatusInReportId',
				backgroundColor: {
        			linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
        			stops: [
        				[0, 'rgb(220, 220, 220)'],
        				[1, 'rgb(211, 211, 211)']
        			]
        		},
				plotBackgroundColor : 'rgba(255, 255, 255, .9)',
				plotBorderWidth : 1
			},
			title : {
				text : '每月在院人数统计',
				x : -20
			//center
			},
			credits : {
				enabled : false
			// 禁用版权信息
			},
			xAxis : {
				gridLineWidth : 1,
				lineColor : '#000',
				categories : []
			},
			yAxis : {
				minorTickInterval : 'auto',
				lineColor : '#000',
				lineWidth : 1,
				tickWidth : 1,
				tickColor : '#000',
				title : {
					text : '人数'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			tooltip : {
				valueSuffix : '人'
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			},
			series : [ {
				name : '人数',
				data : []
			} ]
		};
		//var chart = new Highcharts.Chart(reportElderlyStatusInRecord);
		loadDataLine(reportElderlyStatusInRecord,"../../console/reportElderlyStatus/reportElderlyInStatusOnMain.jhtml",null,
				"statisticsDate","inNursingHome");
		//当月新增老人，及增长率
		$.ajax({
			url : "../../console/reportElderlyStatus/reportElderlyNewComming.jhtml",
			type : "post",
			cache : false,
			success : function(data) {
				if(Number(data.newElderlyCount) >0)
				{
					$("#increasePercent").html(data.increasePercent);
					$("#elderlyNewComming").html("新增："+data.newElderlyCount);
					 $("#increasePercent").addClass("fa fa-arrow-up"); 
				}else {
					$("#increasePercent").html(data.increasePercent);
					$("#elderlyNewComming").html("减少："+Math.abs(data.newElderlyCount));
					 $("#increasePercent").addClass("fa fa-arrow-down"); 
				}
				
			}});
		
		//男女比例
		$.ajax({
			url : "../../console/reportElderlyGenderRate/list.jhtml",
			type : "post",
			cache : false,
			success : function(data) {
				if(data.length ==1)
				{
					$("#elderlyGenderMale").html(data[0].male);
					$("#elderlyGenderFemale").html(data[0].female);

				}else {
					$("#elderlyGenderMale").html(0);
					$("#elderlyGenderFemale").html(0);
				}
				
			}});
})