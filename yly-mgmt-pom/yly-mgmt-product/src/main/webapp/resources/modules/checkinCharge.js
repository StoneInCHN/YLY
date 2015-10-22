var checkinCharge_manager_tool = {
			add:function(){		
				$('#addCheckinCharge').dialog({    
				    title: message("yly.charge.checkin"),    
				    width: 650,    
				    height: 750,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addCheckinCharge_form').form('validate');
							if(validate){
								$.ajax({
									url:"../billing/checkin.jhtml",
									type:"post",
									data:$("#addCheckinCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addCheckinCharge').dialog("close");
											$("#checkinCharge_table_list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addCheckinCharge').dialog("close");
							 $('#addCheckinCharge_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addCheckinCharge_form').show();
				    	$("#mealType").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'MEALTYPE';// 参数
						    }
						});
				    },
				    onClose:function(){
				    	 //$('#addCheckinCharge_form').form('reset');
				    }
				});  
			}
	}

$(function(){
	
	$("#checkinCharge_table_list").datagrid({
		title:message("yly.charge.record.list"),
		fitColumns:true,
		toolbar:"#checkinCharge_manager_tool",
		url:'../billing/list.jhtml?billingType=CHECK_IN', 
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkinDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../billing/details.jhtml?id='+rowData.id+'&path=checkinCharge',
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#checkinDetail').dialog("close");
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
		      //押金(元)
		      {title:message("yly.charge.record.depositAmount"),field:"depositAmount",width:20,align:'center',sortable:true},
		      //床位费(元)
		      {title:message("yly.charge.record.bed"),field:"bedAmount",width:20,align:'center',sortable:true},
		      //护理费(元)
		      {title:message("yly.charge.record.nurse"),field:"nurseAmount",width:20,align:'center',sortable:true},
		      //伙食费(元)
		      {title:message("yly.charge.record.meal"),field:"mealAmount",width:20,align:'center',sortable:true},
		      //总额(元)
		      {title:message("yly.charge.record.totalAmount"),field:"totalAmount",width:20,align:'center',sortable:true},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"operator",width:25,align:'center',sortable:true},
		      //缴费时间
		      {title:message("yly.charge.record.payTime"),field:"payTime",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}}
		   ]
		]

	});
	
	$("#checkinCharge_search_btn").click(function(){
	  var _queryParams = $("#checkinCharge_search_form").serializeJSON();
	  $('#checkinCharge_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#checkinCharge_table_list").datagrid('reload');
	});
	
	//是否伙食费包月
	$("#isMonthlyMeal").click(function(){
		
		if($("#isMonthlyMeal").is(":checked")==true){
			$("#monthlyMeal").css('display','block');
			$("#monthlyMeal table input[type=hidden]").each(function(){
				$(this).prop("disabled",false);
			});
		}
		
		if($("#isMonthlyMeal").is(":checked")==false){
			$("#monthlyMeal").css('display','none'); 
			
			$("#monthlyMeal table input[type=hidden]").each(function(){
				$(this).prop("disabled",true);
			});
		}
		
	});
	
	$('#bedNursePeriodStartDate').datebox({
	    onSelect: function(date){
            var dayStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
//            console.log(dayStr);
	    	$.ajax({
				url:"../systemConfig/getBillEndDate.jhtml",
				type:"post",
				data:{currentDay:dayStr},
				success:function(result,response,status){
//					console.log(result);
					$('#bedNursePeriodEndDate').datebox('setValue',result[0]);
					
				}
			});
	    }
	});
	
	$('#mealPeriodStartDate').datebox({
	    onSelect: function(date){
            var dayStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	$.ajax({
				url:"../systemConfig/getBillEndDate.jhtml",
				type:"post",
				data:{currentDay:dayStr},
				success:function(result){
					$('#mealPeriodEndDate').datebox('setValue',result[0]);
					
				}
			});
	    }
	});
	
	
	$('#addCheckinCharge_elderlyInfo').textbox({  
		  onChange: function(value){  
			  var elderlyInfoID = $("#addCheckinCharge_elderlyInfoID").val();
			  console.log(elderlyInfoID);
				$.ajax({
					url:"../billing/getBedNurseConfig.jhtml",
					type:"post",
					data:{elderlyInfoID:elderlyInfoID},
					success:function(result){
						
					$('#bedType').html("["+result[0].chargeItem.configValue+"]");
					$('#bedType').attr("data-value",result[0].chargeItem.configValue);
					$('#bedPerMonth').html(message("yly.charge.record.perMonth")+result[0].amountPerMonth);
					$('#bedPerMonth').attr("data-value",result[0].amountPerDay);
					$('#bedPerDay').html(message("yly.charge.record.perDay")+result[0].amountPerDay);
					$('#bedPerDay').attr("data-value",result[0].amountPerMonthe);
					
					$('#nurseLevel').html("["+result[1].chargeItem.configValue+"]");
					$('#nurseLevel').attr("data-value",result[1].chargeItem.configValue);
					$('#nurseLevelPerMonth').html(message("yly.charge.record.perMonth")+result[1].amountPerMonth);
					$('#nurseLevelPerMonth').attr("data-value",result[1].amountPerDay);
					$('#nurseLevelPerDay').html(message("yly.charge.record.perDay")+result[1].amountPerDay);
					$('#nurseLevelPerDay').attr("data-value",result[1].amountPerMonthe);
						
					}
				});
		  }  
		});
	

})