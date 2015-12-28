//老人看病情况统计
var reportElderlyMedicalRecord = {
	//	colors : [ '#FF00FF', '#0000CD', '#ED561B', '#DDDF00', '#24CBE5',
	//			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],

	colors : [ '#004B97' ],
	chart : {
		renderTo : 'elderlyMedicalRecordReportId',
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
		text : '每月看病人数统计',
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
var chart = new Highcharts.Chart(reportElderlyMedicalRecord);
loadDataLine(reportElderlyMedicalRecord,
		'../../console/reportElderlyMedicalRecord/report.jhtml',
		'medicalStatiticsCycle', 'elderlyCount');