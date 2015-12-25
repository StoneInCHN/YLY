//老人评估结果统计
var reportElderlyLiveStatitics = {
	colors : [ '#FF00FF', '#0000FF', '#ED561B', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	credits : {
		enabled : false
	// 禁用版权信息
	},
	chart : {
		renderTo : 'elderlyLiveStatiticsReportId',
		plotBackgroundColor : null,
		plotBorderWidth : null,
		plotShadow : false,
		borderWidth : null,
	},
	title : {
		text : '老人居住情况统计'
	},
	tooltip : {
		pointFormat : '{series.name}:{point.y}</b>'
	},
	plotOptions : {
		pie : {
			allowPointSelect : true,
			cursor : 'pointer',
			dataLabels : {
				enabled : false
			},
			showInLegend : true
		}
	},
	series : [ {
		type : 'pie',
		name : '间',
		data : []
	} ]
};
loadDataPie(reportElderlyLiveStatitics,
		'../../console/reportElderlyLiveStatitics/report.jhtml', [ '使用', '空闲'], [ 'inUsingZoomCount', 'totalZoomCount']);