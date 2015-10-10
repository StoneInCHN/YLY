$(function(){
	
	$("#bedNurseChargeRecord-table-list").datagrid({
		title:message("yly.bedNurse.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../bedNurseChargeRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#bedNurseChargeRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 500, 
			    cache: false,   
			    href:'../bedNurseChargeRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#bedNurseChargeRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"elderlyInfo",width:30,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.name;
		    	  }
		      }},
		      //老人编号
		      {title:message("yly.elderlyinfo.identifier"),field:"elderlyInfo",width:70,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.identifier;
		    	  }
		      }},
		      //房间
		      {title:message("yly.common.bedRoom"),field:"elderlyInfo",width:120,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.bedLocation;
		    	  }
		      }},
		      //护理等级
		      {title:message("yly.common.nurseLevel"),field:"elderlyInfo",width:40,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null && value.nursingLevel!=null){
		    		  return value.nursingLevel.configValue;
		    	  }
		      }},
		      //床位费
		      {title:message("yly.charge.record.bed"),field:"bedAmount",width:20,align:'center',sortable:true},
		      //护理费
		      {title:message("yly.charge.record.nurse"),field:"nurseAmount",width:20,align:'center',sortable:true},
		      //总金额
		      {title:message("yly.charge.record.totalAmount"),field:"totalAmount",width:20,align:'center'},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"operator",width:30,align:'center',sortable:true},
		      //收款时间段
		      {title:message("yly.charge.record.period"),field:"periodEndDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM");
		    	  	}
				}},
		      //状态
		      {title:message("yly.charge.record.status"),field:"chargeStatus",width:120,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "PAID"){
		    	  		return message("yly.charge.status.paid");
		    	  	}
		    	  	if(value == "UNPAID"){
		    	  		return message("yly.charge.status.unpaid");
		    	  	}
		      	}}
		   ]
		]

	});
	
	$("#bedNurseChargeRecord_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
			  realName:$("#realName").val(),
			  identifier:$("#identifier").val()
	  }
	  $('#bedNurseChargeRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#bedNurseChargeRecord-table-list").datagrid('reload');
	})
	
	 
	 
})