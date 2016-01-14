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
			text : '用量'
		},
		plotLines : [ {
			value : 0,
			width : 1,
			color : '#808080'
		} ]
	},
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle',
		borderWidth : 0
	},
	series : []
};

$("#reportWaterElectricityRecord-table-list").datagrid({
	fitColumns:true,
	pagination:true,
	checkOnSelect:false,
	url : "../../console/reportWaterElectricityRecord/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"用水量（立方）",field:"waterCount",width:100,sortable:true},
		     {title:"用电量（度）",field:"electricityCount",width:100,sortable:true},
		     {title:"统计周期",field:"waterElectricityStatiticsCycle",width:100,sortable:true,
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
		reportWaterElectricityRecord.series= [ ];
		refreshLine(reportWaterElectricityRecord,data.rows,
				'waterElectricityStatiticsCycle',
				[ 'electricityCount', 'waterCount' ], [ '用电量(度)', '用水量(立方)']);
	}

});
$("#report_water_electricity_record_search_btn").click(function(){
	  var _queryParams = $("#report_water_electricity_record_search_form").serializeJSON();
	  $('#reportWaterElectricityRecord-table-list').datagrid('options').queryParams = _queryParams;
	  $("#reportWaterElectricityRecord-table-list").datagrid('reload');
	})