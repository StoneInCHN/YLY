//维修记录统计
var reportRepairRecord = {
	colors : [ '#008000', '#FF0000', '#FFFF00', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	chart : {
		type: 'column',
		renderTo : 'repairRecordReportId',
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
		text : '维修记录统计',
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
		valueSuffix : '次'
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle',
		borderWidth : 0
	},
	series : []
};

$("#reportRepairRecord-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportRepairRecord/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"维修次数",field:"repairedCount",width:100,sortable:true},
		     {title:"统计周期",field:"repairedStatiticsCycle",width:100,sortable:true,
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
	},onLoadSuccess:function(data){
		reportRepairRecord.series= [ ];
		refreshLine(reportRepairRecord,data.rows,
				'repairedStatiticsCycle',
				[ 'repairedCount'], ['维修次数']);
	}

});
$("#report_repair_record_search_btn").click(function(){
	  var _queryParams = $("#report_repair_record_search_form").serializeJSON();
	  $('#reportRepairRecord-table-list').datagrid('options').queryParams = _queryParams;
	  $("#reportRepairRecord-table-list").datagrid('reload');
	})