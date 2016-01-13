//老人评估结果统计
 var colors = Highcharts.getOptions().colors;
// Create the chart
var reportElderlyLiveStatitics={
                chart: {
                    type: 'pie',
                    renderTo:"elderlyLiveStatiticsReportId"
                },
                title: {
                    text: '老人居住情况统计'
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
                    name: '房间总数',
                    size: '80%',
                    data:[],
                    dataLabels: false,
                    tooltip: {
                        valueSuffix: '间'
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
                    size: '65%',
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
				reportElderlyLiveStatitics.name=data[i].roomType.configValue;
				reportElderlyLiveStatitics.series[1].data.push(
					{
	                    name: '已使用',
	                    y: data[i].inUsingCount,
	                    color: Highcharts.Color(colors[i]).brighten(0.1).get()
	                });
				reportElderlyLiveStatitics.series[1].data.push(
					{
	                    name: '未使用',
	                    y: data[i].totalCount-data[i].inUsingCount,
	                    color: colors[i]
	                    
	                });
				reportElderlyLiveStatitics.series[0].data.push({
                    name: data[i].roomType.configValue,
                    y: data[i].totalCount,
                    color: colors[i]
                }
				);
			}

		}
		var chart = new Highcharts.Chart(reportElderlyLiveStatitics);
	}

});

