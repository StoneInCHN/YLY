//捐赠统计
var reportDonateStatistics = {
//	colors : [ '#008000', '#FF0000', '#FFFF00', '#DDDF00', '#24CBE5',
//			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	chart : {
		type: 'column',
		renderTo : 'donateStatisticsReportId',
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
		text : '捐赠统计',
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
	legend : {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'middle',
		borderWidth : 0
	},
	series: [{
		name:"钱",
		data:[],
		tooltip: {
			 valueSuffix : '元'
		}
	},{
		name:"物",
		data:[],
		tooltip: {
			valueSuffix : '件'
		}
	}]
};
var chart = new Highcharts.Chart(reportDonateStatistics);
loadDataColumn(reportDonateStatistics,
		'../../console/reportDonateStatistics/report.jhtml', 'donateStatisticsCycle',
		'donateCount', 'donateName');

//捐赠统计 饼图
var donateDetailReport ={
	colors: ['#DDDF00','#058DC7', '#50B432', '#ED561B',  '#000000', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
    chart: {
    	renderTo: 'donateStatisticsReportPieId',
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        borderWidth: null
        },
    credits:{
        enabled:false // 禁用版权信息
    },
    title: {
        text: '捐赠详情'
    },
    tooltip: {
    	  pointFormat:  '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    legend:{
        labelFormatter:function(){
            return this.name+':'+ this.y;
        },
    },
    plotOptions: {
        pie: {
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

$(function(){
	$("#reportDonateStatistics-table-list").datagrid({
		fitColumns:true,
		pagination:true,
		checkOnSelect:false,
		url : "../../console/reportDonateStatistics/report.jhtml",
		loadMsg:message("yly.common.loading"),
		striped:true,
		pagination:false,
		columns:[
			    [
			     {title:"名称",field:"donateName",width:100,sortable:true},
			     {title:"类型",field:"donateType",width:100,sortable:true,
			    	 formatter: function(value,row,index){
				    	 if(value == "ITEM"){
				    		 return "物品";
				    	 }else{
				    		 return "金钱";
				    	 }
				    		 
			    	  }
			     },
			     {title:"数量",field:"donateCount",width:100,sortable:true},
			     {title:"统计周期",field:"donateStatisticsCycle",width:100,sortable:true,
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
		onLoadSuccess: function(data){
			donateDetailReport.series= [{type: 'pie', name:'占比', data: []}];
			refreshPie(donateDetailReport,data.rows,
	    			null,null,
	    			'donateName','donateCount');
		}

	});
	
	$('#reportDonateDate').combobox({    
	    url:'../../console/reportDonateStatistics/report.jhtml',    
	    valueField:'donateStatisticsCycleValue',
	    textField:'donateStatisticsCycle',
	    width:250,
	    editable:false,
	    formatter: function(row){
			var opts = $(this).combobox('options');
			data = row[opts.textField];
			row.donateStatisticsCycleValue = data;
			row[opts.textField] =new Date(data).Format("yyyy年MM月");
			return row[opts.textField];
		},
		loadFilter: function(data){
			
			var opts = $(this).combobox('options');
			var selectOptions = [];
			for(var i = 0 ; i < data.length; i++){
				var value = {"donateStatisticsCycle":data[i]['donateStatisticsCycle']}
				var flag = false;
				$.each(selectOptions, function(j) {     
				    if(selectOptions[j].donateStatisticsCycle  = data[i]['donateStatisticsCycle']){
				    	flag = true;
				    }          
				});
				if(flag == false){
					selectOptions.push(value);
				}
			}
			return selectOptions;
		},
	    onChange:function(value){
	    	$("#reportDonateStatistics-table-list").datagrid("reload",{donateStatisticsCycle:value});
	    }
	});  
	
});