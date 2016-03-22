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

/**
 *绑定流程点击事件 
 */
function shortcutNavigation(title,data_url){
	if(title){
		if($('#manager-tabs').tabs("exists",title)){
			$('#manager-tabs').tabs("select",title)
		}else{
			$('#manager-tabs').tabs('add',{    
			    title:title,    
			    href:data_url,    
			    closable:true      
			}); 
		}
	}
};

$(function(){
	function resetHighcharts(){
		var elderlyStatusReportId = $('#elderlyStatusReportId').highcharts();
		elderlyStatusReportId.reflow();
		var elderlyAgeReportId = $('#elderlyAgeReportId').highcharts();
		elderlyAgeReportId.reflow();
		var elderlyLivingMainReportId = $('#elderlyLivingMainReportId').highcharts();
		elderlyLivingMainReportId.reflow();
		var elderlyStatusInReportId = $('#elderlyStatusInReportId').highcharts();
		elderlyStatusInReportId.reflow();
	}
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
	//初始化显示首页，隐藏菜单栏
	$('.easyui-layout').layout('collapse','west');
	
	var westLayour = $('.easyui-layout').layout('panel','west');
	$("#nav-wrap > ul >li >a").click(function(){
		
		var $this = $(this);
		$(".left-content > ul").hide();
		if($this.text()=="首页"){
			$('.easyui-layout').layout('collapse','west');
			$('#manager-tabs').tabs("select",'起始页');
			
			westLayour.hide();
		}else{
			
			$('.easyui-layout').layout('expand','west');
			westLayour.show();
		}
		
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
	
	
	//右侧通知栏信息
	$.ajax({
		url : "../../console/notification/showOnMain.jhtml",
		type : "get",
		cache : false,
		success : function(data) {
			for(var i=0 ; i<2; i++){
				if(data[i]!=undefined ){
					var title = formatLongString(data[i].title, 5)
					$("#notify-content")
					.append('<li class="news-item">'+title+ '<a href="#" data-url="../../console/notification/showOne.jhtml?id='+data[i].id+'" style="float:right" onClick="clickNotificationNews(event)">Read more...</a></li>');
				}
			}
			
			$(".notify").bootstrapNews({
		        newsPerPage: 2,
		        autoplay: true,
				pauseOnHover: true,
				navigation: false,
		        direction: 'down',
		        newsTickerInterval: 4000
		    });
		}
	});
	//右侧通知栏信息
	$.ajax({
		url : "../../console/industryInformation/showOnMain.jhtml",
		type : "get",
		cache : false,
		success : function(data) {
			for(var i=0 ; i<2; i++){
				if(data[i]!=undefined ){
					var title = formatLongString(data[i].title, 5)
					$("#industryInformation-content")
					.append('<li class="news-item">'+title+ '<a href="../../console/industryInformation/showOne.jhtml?id='+data[i].id+'" style="float:right" target="_blank">Read more...</a></li>');
				}
			}
			
			$(".industryInformation").bootstrapNews({
		        newsPerPage: 2,
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
        				[0, 'rgb(250, 250, 250)'],
        				[1, 'rgb(221, 221, 221)']
        			]
    		},
    		borderRadius: 15,
        	renderTo: 'elderlyStatusReportId',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            borderWidth: null,
            panning: false
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
        	itemMarginBottom:0,
            labelFormatter:function(){
                return this.name+':'+ this.y;
            },
            itemStyle: {
            	itemWidth: 100
            }
        },
        plotOptions: {
            pie: {
            	size:'150px',
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
    				[0, 'rgb(250, 250, 250)'],
    				[1, 'rgb(221, 221, 221)']
    			]
    		},
    		borderRadius: 15,
        	renderTo: 'elderlyAgeReportId',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            borderWidth: null,
            panning: true
        },
        title: {
            text: '在院老人年龄段统计'
        },
        tooltip: {
            pointFormat: '{series.name}:{point.y}</b>'
        },
        legend:{
        	itemWidth: 80,
        	 itemStyle: {
                 lineHeight: '10px'
             }
        },
        plotOptions: {
        	 pie: {
        		 size:'150px',
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
	                				[0, 'rgb(250, 250, 250)'],
	                				[1, 'rgb(221, 221, 221)']
	                			]
	            		},
	            		borderRadius: 15,
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
	                legend:{
	                	 itemWidth: 80,
	                	 width:80
	                },
	                plotOptions: {
	                    pie: {
	                    	size:'150px',
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
	                    size:'150px',
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
	                    size: '100px',
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
            				[0, 'rgb(250, 250, 250)'],
            				[1, 'rgb(221, 221, 221)']
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

		/**
		 * 修改密码事件
		 */
		$("#changePasswordHref").click(function(){
			$('#changePassword').dialog({
			    title: "修改密码",//message("yly.drugsInfo.add"),    
			    width: 500,    
			    height: 480,
			    iconCls:'icon-mini-add',
			    href:'../common/changePassword.jhtml',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						debugger;
						var validate = $('#changePassword_form').form('validate');
						if(validate){
							$.ajax({
								url:"../common/savePassword.jhtml",
								type:"post",
								data:$("#changePassword_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#changePassword').dialog("close");
									}else{
										alertErrorMsg();
									}
								}
							});
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#changePassword').dialog("close");
					}
			    }],
			    onOpen:function(){
			    	$('#changePassword').show();
			    },
			
			});
		});
})

