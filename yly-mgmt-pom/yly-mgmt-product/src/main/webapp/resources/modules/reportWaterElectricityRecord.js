//水电费统计
var reportWaterElectricityRecord = {
//	colors : [ '#008000', '#FF0000', '#FFFF00', '#DDDF00', '#24CBE5',
//			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	chart : {
		renderTo : 'waterElectricityRecordReportId',
		backgroundColor : {
			linearGradient : {
				x1 : 0,
				y1 : 0,
				x2 : 1,
				y2 : 1
			},
			stops : [ [ 0, 'rgb(255, 255, 255)' ], [ 1, 'rgb(240, 240, 255)' ] ]
		},
		plotBackgroundColor : 'rgba(255, 255, 255, .9)',
		plotBorderWidth : 1
	},
	title : {
		text : '水电费统计',
		x : -20
	// center
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
			text : '费用'
		},
		plotLines : [ {
			value : 0,
			width : 1,
			color : '#808080'
		} ]
	},
	tooltip : {
		valueSuffix : '元'
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle',
		borderWidth : 0
	},
	series : []
};
var chart = new Highcharts.Chart(reportWaterElectricityRecord);
loadDataLine(reportWaterElectricityRecord,
		'../../console/reportWaterElectricityRecord/report.jhtml',null, 'waterElectricityStatiticsCycle',
		[ 'electricityCount', 'waterCount' ], [ '电费', '水费']);