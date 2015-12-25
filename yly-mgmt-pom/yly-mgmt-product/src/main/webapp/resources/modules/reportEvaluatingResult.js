//老人评估结果统计
var evaluatingResultReport = {
	colors : [ '#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	credits : {
		enabled : false
	// 禁用版权信息
	},
	chart : {
		renderTo : 'evaluatingResultReportId',
		plotBackgroundColor : null,
		plotBorderWidth : null,
		plotShadow : false,
		borderWidth : null,
	},
	title : {
		text : '老人评估结果统计'
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
		name : '人数',
		data : []
	} ]
};
loadDataPie(evaluatingResultReport,
		'../../console/reportEvaluatingResult/report.jhtml', [ '60岁以下', '61~65岁',
				'66~70岁', '71~75岁', '76岁以上' ], [ 'under60', 'range61To65',
				'range66To70', 'range71To75', 'above76' ]);