//老人评估结果统计
var reportElderlyLiveStatitics = {
	colors : [ '#FF00FF', '#0000CD', '#ED561B', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	credits : {
		enabled : false
	// 禁用版权信息
	},
	chart : {
		renderTo : 'elderlyLiveStatiticsReportId',
		plotBackgroundColor: 'rgba(255, 255, 255, .9)',
		plotShadow: true,
		backgroundColor: {
			linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
			stops: [
				[0, 'rgb(255, 255, 255)'],
				[1, 'rgb(240, 240, 255)']
			]
		},
		options3d: {
	        enabled: true,
	        alpha: 45,
	        beta: 0
		}
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
			depth: 20,
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