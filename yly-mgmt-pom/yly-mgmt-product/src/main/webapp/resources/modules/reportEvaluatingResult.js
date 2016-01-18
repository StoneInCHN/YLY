$(function(){
	var selectId = 0;
	$('#reportFormName').combobox({    
	    url:'../../console/reportEvaluatingResult/findAllFormName.jhtml',    
	    valueField:'id',
	    textField:'formName',
	    width:250,
	    editable:false,
	    onChange:function(value){
//	    	loadDataPie(evaluatingResultReport,
//			'../../console/reportEvaluatingResult/report.jhtml',{evaluatingFormId:value},null,null,'evaluatingResultName','elderlyCount');
	    	$("#reportEvaluatingResult-table-list").datagrid("reload",{evaluatingFormId:value});
	    },
	    loadFilter:function(data){
	    	for(var i = 0; i < data.length; i++){
	    		if(data[i].evaluatingFormStatus == "ENABLE"){
	    			selectId=data[i].id;
	    		}
	    	}
	    	return data;
	    },
	    onLoadSuccess:function(){
	    	$('#reportFormName').combobox("setValue",selectId);
	    },
	});  
	
});


//老人评估结果统计自定义
var evaluatingResultReport = {
	colors : [ '#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5',
			'#64E572', '#FF9655', '#FFF263', '#6AF9C4' ],
	credits : {
		enabled : false
	// 禁用版权信息
	},
//	 subtitle: {
//         text: '自定义卷',
//         align: "right"
//     },
	chart : {
		renderTo : 'evaluatingResultReportId',
		backgroundColor: {
			linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
			stops: [
				[0, 'rgb(255, 255, 255)'],
				[1, 'rgb(240, 240, 255)']
			]
		},
		borderWidth: null,
		plotBackgroundColor: 'rgba(255, 255, 255, .9)',
		plotShadow: true,
		plotBorderWidth: 1
	},
	legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle',
        borderWidth: 0
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
	xAxis: {
		gridLineWidth: 1,
		lineColor: '#000',
		tickColor: '#000',
		labels: {
			style: {
				color: '#000',
				font: '11px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {
			style: {
				color: '#333',
				fontWeight: 'bold',
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'

			}
		}
	},
	yAxis: {
		minorTickInterval: 'auto',
		lineColor: '#000',
		lineWidth: 1,
		tickWidth: 1,
		tickColor: '#000',
		labels: {
			style: {
				color: '#000',
				font: '11px Trebuchet MS, Verdana, sans-serif'
			}
		},
		title: {
			style: {
				color: '#333',
				fontWeight: 'bold',
				fontSize: '12px',
				fontFamily: 'Trebuchet MS, Verdana, sans-serif'
			}
		}
	},
	series : [ {
		type : 'pie',
		name : '人数',
		data : []
	} ]
};

$("#reportEvaluatingResult-table-list").datagrid({
	title:"评估结果统计",
	fitColumns:true,
	pagination:true,
	url : "../../console/reportEvaluatingResult/report.jhtml",
	loadMsg:message("yly.common.loading"),
	striped:true,
	pagination:false,
	columns:[
		    [
		     {title:"统计名称",field:"evaluatingResultName",width:100,sortable:true},
		     {title:"人数",field:"elderlyCount",width:100,sortable:true},
		     {title:"评估表类型",field:"evaluatingType",width:100,sortable:true,
		    	 formatter: function(value,row,index){
			    	  if( value =="SYSTEM_FORM"){
			    		  return  "全国卷";
			    	  }else{
			    		  return  "自定义卷";
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
		evaluatingResultReport.series=[ {
			type : 'pie',
			name : '人数',
			data : []
		} ];
		refreshPie(evaluatingResultReport,data.rows,
				null,null,'evaluatingResultName','elderlyCount');
	}

});

