$(function(){
	
	$("#waterElectricityChargeRecord_table_list").datagrid({
		title:message("yly.waterElectricity.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../waterElectricityChargeRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#waterElectricityChargeRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 600, 
			    cache: false,   
			    modal: true,
			    href:'../waterElectricityChargeRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#waterElectricityChargeRecordDetail').dialog("close");
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
		    	  return row.elderlyInfo.nursingLevel.configValue;
		      }},
		      //水用量
		      //{title:message("yly.charge.record.water.count"),field:"waterCount",width:25,align:'center',sortable:true},
		      //水费
		      {title:message("yly.charge.record.water"),field:"waterAmount",width:25,align:'center',sortable:true},
		      //电用量
		      //{title:message("yly.charge.record.electricity.count"),field:"electricityCount",width:25,align:'center',sortable:true},
		      //电费
		      {title:message("yly.charge.record.electricity"),field:"electricityAmount",width:25,align:'center',sortable:true},
		      //总金额
		      {title:message("yly.charge.record.totalAmount"),field:"totalAmount",width:25,align:'center'},
		      
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
	
	$("#waterElectricityChargeRecord_search_btn").click(function(){
	  var _queryParams = $("#waterElectricityChargeRecord_search_form").serializeJSON();
	  $('#waterElectricityChargeRecord_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#waterElectricityChargeRecord_table_list").datagrid('reload');
	  //隐藏域用于标记上次使用过的查询条件 
	  $("#nameHidden").val($("#name").val());
	  $("#identifierHidden").val($("#identifier").val());
	  $("#statusHidden").val($("#status").val());
	  $("#beginDateHidden").val($("#beginDate").val());
	  $("#endDateHidden").val($("#endDate").val());
	})
	
	 waterElectricityCharge_manager_tool = {
		exportData:function(){
			$.ajax({
				url:"../waterElectricityChargeRecord/count.jhtml",
				type:"post",
				data:$("#waterElectricityChargeRecord_search_form").serialize(),
				success:function(result,response,status){
					if(result.count != null){
						var text = "";
						if(result.count == 0){
							text = "当前条件无可导出的数据。";
							$.messager.alert(message("yly.common.notice"), text,'warning');
						}else if(result.count <= 500){
							text = "确定导出 "+result.count+" 条记录？";
							$.messager.confirm(message("yly.common.confirm"), text, function(r) {
								if(r){
									$("#waterElectricityChargeRecord_search_form").attr("action","../waterElectricityChargeRecord/exportData.jhtml");
									$("#waterElectricityChargeRecord_search_form").attr("target","_blank");
									$("#waterElectricityChargeRecord_search_form").submit();
								}

							});
						}else{
							text = "导出数据超过500条数据，建议搜索查询条件以缩小查询范围，再导出。";
							$.messager.confirm(message("yly.common.notice"), text, function(r) {
								if (!r) {
									text = "导出共有"+ result.count +"条数据，导出超过500条数据可能需要您耐心等待，仍需操作请确定继续。";
									$.messager.confirm(message("yly.common.confirm"), text, function(yes) {
										if(yes){
											$("#waterElectricityChargeRecord_search_form").attr("action","../waterElectricityChargeRecord/exportData.jhtml");
											$("#waterElectricityChargeRecord_search_form").attr("target","_blank");
											$("#waterElectricityChargeRecord_search_form").submit();
										}
									});
								}
							})
						}
					}
					$("#waterElectricityChargeRecord_table_list").datagrid('reload');
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
		}	
	}
	 
})