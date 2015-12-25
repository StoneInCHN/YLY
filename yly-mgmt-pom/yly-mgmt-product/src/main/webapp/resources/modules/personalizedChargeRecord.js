$(function(){
	
	$("#personalizedChargeRecord_table_list").datagrid({
		title:message("yly.service.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../personalizedChargeRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#personalizedChargeRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 630, 
			    cache: false,
			    modal: true,
			    href:'../personalizedChargeRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#personalizedChargeRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      //老人编号
		      {title:message("yly.elderlyinfo.identifier"),field:"elderlyInfoIdentifier",width:40,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.identifier;
		      }},
		      //房间
		      {title:message("yly.common.bedRoom"),field:"elderlyInfoBedRoom",width:50,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.bedLocation;
		      }},
		      //护理等级
		      {title:message("yly.common.nurseLevel"),field:"elderlyInfoNurseLevel",width:30,align:'center',formatter:function(value,row,index){
		    	  if(row.elderlyInfo.nursingLevel != null){
		    		  return row.elderlyInfo.nursingLevel.configValue;
		    	  }else{
		    		  return null;
		    	  }
		      }},
		      //个性化服务费
		      {title:message("yly.charge.record.service"),field:"personalizedAmount",width:25,align:'center',sortable:true},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"operator",width:30,align:'center',sortable:true},
		      //收款时间段
		      {title:message("yly.charge.record.period"),field:"periodStartDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM");
		    	  	}
				}},
		      //状态
		      {title:message("yly.charge.record.status"),field:"chargeStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "PAID"){
		    	  		return "<font color=#7CCD7C>"+message("yly.charge.status.paid")+"</font>";
		    	  	}
		    	  	if(value == "UNPAID"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.status.unpaid")+"</font>";
		    	  	}
		      	}}
		   ]
		]

	});
	
	$("#personalizedChargeRecord_search_btn").click(function(){
	  var _queryParams = $("#personalizedChargeRecord_search_form").serializeJSON();
	  $('#personalizedChargeRecord_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#personalizedChargeRecord_table_list").datagrid('reload');
	  //隐藏域用于标记上次使用过的查询条件 
	  $("#nameHidden").val($("#name").val());
	  $("#identifierHidden").val($("#identifier").val());
	  $("#statusHidden").val($("#status").val());
	  $("#beginDateHidden").val($("#beginDate").val());
	  $("#endDateHidden").val($("#endDate").val());
	})
	 
})