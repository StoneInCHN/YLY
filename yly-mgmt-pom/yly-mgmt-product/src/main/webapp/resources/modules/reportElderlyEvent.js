//老人看病情况统计
var reportElderlyEvent = {
	colors : [ '#008000', '#FF0000', '#FFFF00', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	chart : {
		renderTo : 'elderlyEventReportId',
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
		text : '老人事件统计',
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
	series : []
};

$("#reportElderlyEvent-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportElderlyEvent/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"消极事件",field:"negativeEvent",width:100,sortable:true},
		     {title:"一般事件",field:"normalEvent",width:100,sortable:true},
		     {title:"积极事件",field:"positiveEvent",width:100,sortable:true},
		     {title:"统计周期",field:"eventStatiticsCycle",width:100,sortable:true,
		    	 formatter: function(value,row,index){
	    			if(value != null){
		    	  		return new Date(value).Format("yyyy年MM月");
		    	  	}
		    	  }
		     }
		 ]
	],
	rowStyler: function(index,row){
		if (index % 2 == 0){
			return 'background-color:#D4D4D4;';
		}
	},
	onLoadSuccess:function(data){
		reportElderlyEvent.series= [ ];
		refreshLine(reportElderlyEvent,data.rows,
				'eventStatiticsCycle',
				[ 'positiveEvent', 'negativeEvent', 'normalEvent' ], [ '积极事件', '消极事件',
						'一般事件' ]);
	}
});
$("#report_elderly_event_search_btn").click(function(){
	  var _queryParams = $("#report_elderly_event_search_form").serializeJSON();
	  $('#reportElderlyEvent-table-list').datagrid('options').queryParams = _queryParams;
	  $("#reportElderlyEvent-table-list").datagrid('reload');
	  
	})