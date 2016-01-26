var checkoutCharge_manager_tool = {
		add:function(){		
			$('#addCheckoutCharge').dialog({    
			    title: message("yly.elderly.status.in_progress_checkout"),    
			    width: 650,    
			    height: 800,
			    iconCls:'icon-mini-add',
			    modal:true,
			    cache: false, 
			    buttons:[{
			    	text:message("yly.elderly.status.in_progress_checkout"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addCheckoutCharge_form').form('validate');
						if(validate){
							$.messager.confirm(message("yly.common.confirm"), message("yly.checkout.confirm_checkoutCharge_for_elderly", 
									$("#addCheckoutCharge_elderlyName").textbox('getValue')), function(r) {
										if(r){
											$("#addCheckoutCharge_checkoutNow").show();
											$("#addCheckoutCharge_checkoutDate").show();
											$("#addCheckoutCharge_checkoutNow").removeAttr("disabled"); 
											$("#addCheckoutCharge_checkoutDate").removeAttr("disabled"); 
											//办理出院
											$.ajax({
												url:"../checkout/checkoutBill.jhtml",
												type:"post",
												data:$("#addCheckoutCharge_form").serialize(),
												beforeSend:function(){
													$.messager.progress({
														text:message("yly.common.saving")
													});
												},
												success:function(result,response,status){
													$.messager.progress('close');
													showSuccessMsg(result.content);
													close_addCheckoutCharge_dialog
													$("#checkoutCharge-table-list").datagrid('reload');
												},
												error:function (XMLHttpRequest, textStatus, errorThrown) {
													$.messager.progress('close');
													alertErrorMsg();
												}
											});
											
										}
									});
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						close_addCheckoutCharge_dialog
					}
			    }],
			    onOpen:function(){
			    	$('#addCheckoutCharge_form').show();
			    	$.ajax({
						url:"../checkout/getWaterElecConfig.jhtml",
						type:"get",
						success:function(map,status){
							$("#addCheckoutCharge_waterPrice").numberbox("setValue",map.waterUnitPrice);
							$("#addCheckoutCharge_electricityPrice").numberbox("setValue",map.elecUnitPrice);
						}
					});
			    },
			    onClose:function(){
			    	close_addCheckoutCharge_dialog
			    }
			});  
		},
		  adjustment:function(){
		    	var _edit_row = $('#checkoutCharge-table-list').datagrid('getSelections');
				if (_edit_row.length == 0) {
					$.messager.alert(message("yly.common.prompt"),
							message("yly.common.select.editRow"), 'warning');
					return false;
				}
				if (_edit_row.length>1) {
					$.messager.alert(message("yly.common.prompt"),
							message("yly.common.select.editRow.unique"), 'warning');
					return false;
				}
				
				if(_edit_row[0].chargeStatus == "PAID"){
					$.messager.alert(message("yly.common.prompt"),message("yly.checkout.elderlyStatus.invalid.adjustment",_edit_row[0].elderlyInfo.name), 'warning');
				}else{
					checkoutAdjustment(_edit_row[0].id);
				}
		      },
		edit:function(){},
		remove:function(){
			listRemove('checkoutCharge-table-list','../checkoutCharge/delete.jhtml');
		}
}


$(function(){
	
	$("#checkoutCharge-table-list").datagrid({
		title:message("yly.checkout.charge"),
		fitColumns:true,
		toolbar:"#checkoutCharge_manager_tool",
		url:'../billing/list.jhtml?billingType=CHECK_OUT',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkoutChargeDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 650,    
			    height: 800,
			    iconCls:'icon-mini-add',
			    modal:true,
			    cache: false, 
			    onOpen:function(){
			    	$('#checkoutCharge_form').show();
					$.ajax({
						url :'../checkout/details.jhtml?id='+rowData.id,
						type : "get",
						success : function(billing, status) {
							if(billing != null){
								$('#viewCheckoutCharge_elderlyInfo_outHospitalizedDate').textbox('setValue',new Date(billing.elderlyInfo.outHospitalizedDate).
										Format(message("yly.common.dateFormatChina")));
								$('#viewCheckoutCharge_elderlyInfo_name').textbox('setValue',billing.elderlyInfo.name);
								$('#viewCheckoutCharge_elderlyInfo_identifier').textbox('setValue',billing.elderlyInfo.identifier);
								$('#viewCheckoutCharge_elderlyInfo_bedLocation').textbox('setValue',billing.elderlyInfo.bedLocation);
								$('#viewCheckoutCharge_elderlyInfo_nursingLevel_configValue').textbox('setValue',billing.elderlyInfo.nursingLevel.configValue);
								$('#viewCheckoutCharge_bedNurseAmount').numberbox('setValue', billing.bedNurseCharge.bedAmount+billing.bedNurseCharge.nurseAmount);
								$('#viewCheckoutCharge_bedNurseCharge_remark').textbox('setValue',billing.bedNurseCharge.remark);
								$('#viewCheckoutCharge_mealAmount').numberbox('setValue', billing.mealCharge.mealAmount);
								$('#viewCheckoutCharge_mealCharge_remark').textbox('setValue', billing.mealCharge.remark);
								
								$('#viewCheckoutCharge_waterCount').numberbox('setValue',billing.waterElectricityCharge.waterCount);
								$('#viewCheckoutCharge_waterAmount').numberbox('setValue',billing.waterElectricityCharge.waterAmount);
								$('#viewCheckoutCharge_electricityCount').numberbox('setValue',billing.waterElectricityCharge.electricityCount);
								$('#viewCheckoutCharge_electricityAmount').numberbox('setValue',billing.waterElectricityCharge.electricityAmount);
								$('#viewCheckoutCharge_waterPrice').numberbox('setValue',billing.waterElectricityCharge.waterAmount/billing.waterElectricityCharge.waterCount);
								$('#viewCheckoutCharge_electricityPrice').numberbox('setValue',billing.waterElectricityCharge.electricityAmount/billing.waterElectricityCharge.electricityCount);
								
								$('#viewCheckoutCharge_personalizedAmount').numberbox('setValue',billing.personalizedCharge.personalizedAmount);
								$('#viewCheckoutCharge_personalizedCharge_remark').textbox('setValue',billing.personalizedCharge.remark);
								$('#viewCheckoutCharge_advanceChargeAmount').numberbox('setValue',billing.advanceChargeAmount);
								$('#viewCheckoutCharge_totalAmount').numberbox('setValue',billing.totalAmount);
								
								showAdjustment(billing,"viewCheckoutCharge_djustmentService");//显示调账信息
								
							}
						}});
			    },
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 close_viewCheckoutCharge_dialog();
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
		      //总额(元)
		      {title:message("yly.charge.record.totalAmount"),field:"totalAmount",width:20,align:'center',sortable:true},
		      //状态
		      {title:message("yly.charge.billing.status"),field:"chargeStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "PAID"){
		    	  		return "<font color=#7CCD7C>"+message("yly.charge.status.paid")+"</font>";
		    	  	}
		    	  	if(value == "UNPAID"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.status.unpaid")+"</font>";
		    	  	}
		    	  	if(value == "UNPAID_ADJUSTMENT"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.status.unpaid_adjustment")+"</font>";
		    	  	}
		      	}},
		      //办理时间
		      {title:message("yly.charge.record.oprTime"),field:"createDate",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}},
		      {title:message("yly.elderlyInfo.beHospitalizedDate"),field:"beHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(row.elderlyInfo.beHospitalizedDate != null){
		    	  		return new Date(row.elderlyInfo.beHospitalizedDate).Format("yyyy-MM-dd");
		      	}
		      }},
		      {title:message("yly.elderlyInfo.outHospitalizedDate"),field:"outHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(row.elderlyInfo.outHospitalizedDate != null){
		    	  		return new Date(row.elderlyInfo.outHospitalizedDate).Format("yyyy-MM-dd");
		      	}
		      }},		     
		      {title:message("yly.elderly.status"),field:"elderlyStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(row.elderlyInfo.elderlyStatus == "OUT_NURSING_HOME"){
		    	  		return "<font color=#FF0000>"+message("yly.elderly.status.out_nursing_home")+"</font>";
		    	  	}
		    	  	if(row.elderlyInfo.elderlyStatus == "IN_PROGRESS_CHECKOUT"){
		    	  		return  message("yly.elderly.status.in_progress_checkout");
		    	  	}
		      	}}		      
		   ]
		]
	});
	
	$("#checkoutCharge_search_btn").click(function(){
	 var _queryParams = $("#checkoutCharge_search_form").serializeJSON();
	  $('#checkoutCharge-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#checkoutCharge-table-list").datagrid('reload');
	});
	$('#addCheckoutCharge_waterCount').numberbox({
		onChange:function(value){
			if($("#addCheckoutCharge_waterCount").val() != null && $("#addCheckoutCharge_waterCount").val().trim() != ""){
				var waterCount = $("#addCheckoutCharge_waterCount").val();
				var waterPrice = $("#addCheckoutCharge_waterPrice").val();
				var waterAmount = waterCount*waterPrice;
				$("#addCheckoutCharge_waterAmount").numberbox("setValue", waterAmount);
				useAdvanceCharge();
				useWaterElecCharge();
			}
		}
	});
	$('#addCheckoutCharge_electricityCount').numberbox({
		onChange:function(value){
			if($("#addCheckoutCharge_electricityCount").val() != null && $("#addCheckoutCharge_electricityCount").val().trim() != ""){
				var electricityCount = $("#addCheckoutCharge_electricityCount").val();
				var electricityPrice = $("#addCheckoutCharge_electricityPrice").val();
				var electricityAmount = electricityCount*electricityPrice;
				$("#addCheckoutCharge_electricityAmount").numberbox("setValue", electricityAmount);
				useAdvanceCharge();
				useWaterElecCharge();
			}
		}
	});
});
$("#addCheckoutCharge_isAdvanceCharge").click(function(){
	useAdvanceCharge();
	useWaterElecCharge();
});
function useAdvanceCharge(){
	var totalChargewithoutAdvance = $("#addCheckoutCharge_totalChargeWithoutAdvance");
	if(totalChargewithoutAdvance && totalChargewithoutAdvance.val() != ""){
		if($("#addCheckoutCharge_isAdvanceCharge").is(":checked")){
			var advanceCharge = $("#addCheckoutCharge_advanceChargeAmount").numberbox('getValue');
			$("#addCheckoutCharge_totalCharge").numberbox('setValue',totalChargewithoutAdvance.val() - advanceCharge);
		}else{
			$("#addCheckoutCharge_totalCharge").numberbox('setValue',totalChargewithoutAdvance.val());
		}
	}
}
function useWaterElecCharge(){
	var totalCharge = $("#addCheckoutCharge_totalCharge");
	var waterAmount = $("#addCheckoutCharge_waterAmount");
	var electricityAmount = $("#addCheckoutCharge_electricityAmount");
	if(waterAmount && waterAmount.val() != ""){
		$("#addCheckoutCharge_totalCharge").numberbox('setValue', parseFloat(totalCharge.val())+parseFloat(waterAmount.val()));
	}
	if(electricityAmount && electricityAmount.val() != ""){
		$("#addCheckoutCharge_totalCharge").numberbox('setValue', parseFloat(totalCharge.val())+parseFloat(electricityAmount.val()));
	}
}
function checkoutAdjustment(billId){
	$('#addCheckoutAdjust').dialog({    
	    title: message("yly.charge.billing.adjust"),    
	    width: 450,    
	    height: 300,
	    modal: true,
	    iconCls : 'icon-mini-edit',
	    cache: false, 
	    buttons:[{
	    	text:message("yly.common.save"),
	    	iconCls:'icon-save',
			handler:function(){
				var validate = $('#addCheckoutAdjust_form').form('validate');
				if(validate){
					$.ajax({
						url:"../checkout/checkoutBillAdjust.jhtml",
						type:"post",
						data:$("#addCheckoutAdjust_form").serialize(),
						beforeSend:function(){
							$.messager.progress({
								text:message("yly.common.saving")
							});
						},
						success:function(result,response,status){
							    showSuccessMsg(result.content);
							    if(result.type == "success"){
							    	$.messager.progress('close');
									$('#addCheckoutAdjust').dialog("close");
									$("#checkoutCharge-table-list").datagrid('reload');
									$('#addCheckoutAdjust_form').form("reset");
							    }
								
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
				 $('#addCheckoutAdjust').dialog("close");
				 $('#addCheckoutAdjust_form').form("reset");
			}
	    }],
	    
	    onOpen:function(){
	    	$('#addCheckoutAdjust_form').show();
	    	$('#addCheckout_billId').val(billId);
	    	$("#addCheckout_adjustmentCause").combobox({    
			    valueField:'configValue',    
			    textField:'configValue',
			    cache: true,
			    url:'../systemConfig/findByConfigKey.jhtml',
			    onBeforeLoad : function(param) {
			        param.configKey = 'ADJUSTMENTCAUSE';// 参数
			    }
			});
	    },
	    onClose:function(){
	        $('#addCheckoutAdjust_form').form("reset");
	    }
	}); 
}
/**
 * 老人账单查询功能
 * dataMap.id为老人id
 */
function detectBillingUnderElderly(dataMap){
	 $("#addCheckoutCharge_checkoutNow").attr("disabled","disabled"); 
	 $("#addCheckoutCharge_checkoutDate").attr("disabled","disabled"); 
	 clearAddCheckoutChargeText();	
	 $("#addCheckoutCharge_elderlyInfoID").val(dataMap.id); // 隐藏域 老人id
	 $("#addCheckoutCharge_elderlyName").textbox('setValue',dataMap.name); // 老人姓名
	 $("#addCheckoutCharge_elderlyIdentifier").textbox('setValue',dataMap.identifier); // 老人编号
	 $("#addCheckoutCharge_bedRoom").textbox('setValue',dataMap.bedLocation); // 床位
	 $("#addCheckoutCharge_nurseLevel").textbox('setValue',dataMap.nursingLevel); // 护理级别
	 $("#addCheckoutCharge_advanceChargeAmount").numberbox('setValue',dataMap.advanceChargeAmount);  //预存款
	 var checkoutDate = $("#addCheckoutCharge_checkoutDate").val();
		$.ajax({
			url :"../checkout/searchListByFilter.jhtml?elderlyId="+dataMap.id+"&billingType=CHECK_OUT&checkoutDate=" + checkoutDate ,
			type : "get",
			success : function(billings, status) {
				if(billings.length>0){
					var depositInfo = ""; //押金详情
					var bedNurseChargeInfo = ""; //床位护理费详情
					var mealChargeInfo=""; //伙食费详情
					var waterElectricityChargeInfo = ""; //水电费详情
					var personalizedChargeInfo = ""; //个性化服务费详情
					var checkoutBilling = null;
					for(var i=0; i< billings.length; i++){
						var billing = billings[i];
						if(billing.billType == "CHECK_OUT"){
							checkoutBilling = billing;
							$("#addCheckoutCharge_bedCharge").val(billing.bedNurseCharge.bedAmount);
							$("#addCheckoutCharge_nurseCharge").val(billing.bedNurseCharge.nurseAmount);
							$("#addCheckoutCharge_totalBedNurseCharge").numberbox('setValue',billing.bedNurseCharge.bedAmount+billing.bedNurseCharge.nurseAmount);
							$("#addCheckoutCharge_totalMealCharge").numberbox('setValue',billing.mealCharge.mealAmount);
//							$("#addCheckoutCharge_waterCharge").val(billing.waterElectricityCharge.waterAmount);
//							$("#addCheckoutCharge_electricityCharge").val(billing.waterElectricityCharge.electricityAmount);
//							$("#addCheckoutCharge_totalWaterElectricityCharge").textbox('setValue',billing.waterElectricityCharge.waterAmount+billing.waterElectricityCharge.electricityAmount);
							$("#addCheckoutCharge_totalPersonalizedServiceCharge").numberbox('setValue',billing.personalizedCharge.personalizedAmount);
							//总共费用
							$("#addCheckoutCharge_totalChargeWithoutAdvance").val(billing.totalAmount);// 隐藏域 未扣除预存款的总金额
							var totalChargewithoutAdvance = $("#addCheckoutCharge_totalChargeWithoutAdvance");
							var advanceCharge = $("#addCheckoutCharge_advanceChargeAmount").numberbox('getValue');
							$("#addCheckoutCharge_isAdvanceCharge").attr("checked",true);
							$("#addCheckoutCharge_totalCharge").numberbox('setValue',totalChargewithoutAdvance.val() - advanceCharge);
							useWaterElecCharge();
						}else{
							//押金
							if(billing.deposit != null){
								if(billing.deposit.chargeStatus == "PAID"){
									$("#addCheckoutCharge_totalDepositCharge").numberbox('setValue',billing.deposit.depositAmount);
								}else{
									$("#addCheckoutCharge_totalDepositCharge").numberbox('setValue', 0);
								}
								if(billing.deposit.depositAmount != null){
									depositInfo += message("yly.checkout.charge.depositInfo",billing.deposit.depositAmount, message("yly.charge."+billing.deposit.chargeStatus));
								}
								if(billing.deposit.remark != null){
									depositInfo += message("yly.checkout.charge.remarkInfo",billing.deposit.remark);
								}
							}
							//床位护理费
							if(billing.bedNurseCharge != null ){
								if(billing.bedNurseCharge.periodStartDate != null && billing.bedNurseCharge.periodEndDate != null){
									var periodTimeInfo = message("yly.checkout.charge.DateFromTo",new Date(billing.bedNurseCharge.periodStartDate).Format(message("yly.common.dateFormatChina")),
											new Date(billing.bedNurseCharge.periodEndDate).Format(message("yly.common.dateFormatChina"))); 
									bedNurseChargeInfo += periodTimeInfo +  message("yly.checkout.charge.bedAmountInfo",billing.bedNurseCharge.bedAmount, message("yly.charge."+billing.bedNurseCharge.chargeStatus));
									bedNurseChargeInfo += periodTimeInfo +  message("yly.checkout.charge.nurseAmountInfo",billing.bedNurseCharge.nurseAmount, message("yly.charge."+billing.bedNurseCharge.chargeStatus));
								}
								if(billing.bedNurseCharge.remark!=null){
									bedNurseChargeInfo += message("yly.checkout.charge.remarkInfo",billing.bedNurseCharge.remark);
								}
							}
							//伙食费
							if(billing.mealCharge != null ){
								if(billing.mealCharge.periodStartDate != null && billing.mealCharge.periodEndDate != null){
									var periodTimeInfo = message("yly.checkout.charge.DateFromTo",new Date(billing.mealCharge.periodStartDate).Format(message("yly.common.dateFormatChina")),
											new Date(billing.mealCharge.periodEndDate).Format(message("yly.common.dateFormatChina"))); 
									mealChargeInfo += periodTimeInfo +  message("yly.checkout.charge.mealAmountInfo",billing.mealCharge.mealAmount, message("yly.charge."+billing.mealCharge.chargeStatus));
								}
								if(billing.bedNurseCharge.remark != null){
									mealChargeInfo += message("yly.checkout.charge.remarkInfo",billing.mealCharge.remark);
								}
							}
							//水电费
							if(billing.waterElectricityCharge != null ){
								if(billing.waterElectricityCharge.periodStartDate != null && billing.waterElectricityCharge.periodEndDate != null){
									var periodTimeInfo = message("yly.checkout.charge.DateFromTo",new Date(billing.waterElectricityCharge.periodStartDate).Format(message("yly.common.dateFormatChina")),
											new Date(billing.waterElectricityCharge.periodEndDate).Format(message("yly.common.dateFormatChina"))); 
									waterElectricityChargeInfo += periodTimeInfo +  message("yly.checkout.charge.waterAmountInfo",billing.waterElectricityCharge.waterAmount, message("yly.charge."+billing.waterElectricityCharge.chargeStatus));
									waterElectricityChargeInfo += periodTimeInfo +  message("yly.checkout.charge.electricityAmountInfo",billing.waterElectricityCharge.electricityAmount, message("yly.charge."+billing.waterElectricityCharge.chargeStatus));
								}
								if(billing.waterElectricityCharge.remark!=null){
									waterElectricityChargeInfo += message("yly.checkout.charge.remarkInfo",billing.waterElectricityCharge.remark);
								}
							}
							//个性化服务费
							if(billing.personalizedCharge != null ){
								if(billing.personalizedCharge.periodStartDate != null && billing.personalizedCharge.periodEndDate != null){
									var periodTimeInfo = message("yly.checkout.charge.DateFromTo",new Date(billing.personalizedCharge.periodStartDate).Format(message("yly.common.dateFormatChina")),
											new Date(billing.personalizedCharge.periodEndDate).Format(message("yly.common.dateFormatChina"))); 
									personalizedChargeInfo += periodTimeInfo +  message("yly.checkout.charge.personalizedAmountInfo",billing.personalizedCharge.personalizedAmount, message("yly.charge."+billing.personalizedCharge.chargeStatus));
								}
								if(billing.personalizedCharge.remark != null){
									personalizedChargeInfo += message("yly.checkout.charge.remarkInfo",billing.personalizedCharge.remark);
								}
							}
						}
					}	
					//押金
					if(depositInfo != ""){
						$("#addCheckoutCharge_depositRemark").textbox('setValue',depositInfo);
					}else{
						$("#addCheckoutCharge_depositRemark").textbox('setValue',message("yly.checkout.charge.null"));
					}
					//床位护理费
					if(bedNurseChargeInfo!=""){
						if(checkoutBilling.bedNurseCharge.remark != null && checkoutBilling.bedNurseCharge.remark.trim() != ""){
							bedNurseChargeInfo += "\n----------------------------------------------------------------------------------------------------------------\n";
							bedNurseChargeInfo += checkoutBilling.bedNurseCharge.remark;
						}
						$("#addCheckoutCharge_bedNurseRemark").textbox('setValue',bedNurseChargeInfo );
					}else{
						$("#addCheckoutCharge_bedNurseRemark").textbox('setValue',message("yly.checkout.charge.null"));
					}
					//伙食费
					if(mealChargeInfo!=""){
						if(checkoutBilling.mealCharge.remark != null && checkoutBilling.mealCharge.remark.trim() != ""){
							mealChargeInfo += "\n----------------------------------------------------------------------------------------------------------------\n";
							mealChargeInfo += checkoutBilling.mealCharge.remark;
						}
						$("#addCheckoutCharge_mealRemark").textbox('setValue',mealChargeInfo);
					}else{
						$("#addCheckoutCharge_mealRemark").textbox('setValue',message("yly.checkout.charge.null"));
					}
					//水电费
					if(waterElectricityChargeInfo!=""){
						if(checkoutBilling.waterElectricityCharge.remark != null &&  checkoutBilling.waterElectricityCharge.remark.trim() != ""){
							waterElectricityChargeInfo += "\n----------------------------------------------------------------------------------------------------------------\n";
							waterElectricityChargeInfo += checkoutBilling.waterElectricityCharge.remark;
						}
						$("#addCheckoutCharge_waterElectricityCharge").textbox('setValue',waterElectricityChargeInfo);
					}else{
						$("#addCheckoutCharge_waterElectricityCharge").textbox('setValue',message("yly.checkout.charge.null"));
					}
					//个性化服务费
					if(personalizedChargeInfo!=""){
						if(checkoutBilling.personalizedCharge.remark != null && checkoutBilling.personalizedCharge.remark.trim() != ""){
							personalizedChargeInfo += "\n----------------------------------------------------------------------------------------------------------------\n";
							personalizedChargeInfo += checkoutBilling.personalizedCharge.remark;
						}
						$("#addCheckoutCharge_personalizedServiceRemark").textbox('setValue',personalizedChargeInfo);
					}else{
						$("#addCheckoutCharge_personalizedServiceRemark").textbox('setValue',message("yly.checkout.charge.null"));
					}
				}else{
					$.messager.alert(message("yly.common.prompt"),message("yly.checkout.charge.no_billing"),'warning');
				}
			}
		});
}

function checkoutNow(){
	if($("#addCheckoutCharge_checkoutNow").is(":checked")){
		$("#checkoutDateDiv").hide();
	}else{
		$("#checkoutDateDiv").show();
	}
}

function clearAddCheckoutChargeText(){
		$("input").each(function(){
		   $(this).val(null);
		});
		$("textarea").each(function(){
			   $(this).val(null);
		});	
}
function close_addCheckoutCharge_dialog(){
	$("#addCheckoutCharge_checkoutNow").show();
	$("#addCheckoutCharge_checkoutDate").show();
	$("#addCheckoutCharge_checkoutNow").removeAttr("disabled"); 
	$("#addCheckoutCharge_checkoutDate").removeAttr("disabled"); 
	$("#addCheckoutCharge_checkoutDate").val(null);
	clearAddCheckoutChargeText();
	 $('#addCheckoutCharge').dialog("close");
}
function clearViewCheckoutChargeText(){
	$("input").each(function(){
	   $(this).val(null);
	});
	$("textarea").each(function(){
		   $(this).val(null);
		});	
	$('#viewCheckoutCharge_djustmentService').html(null);
}
function close_viewCheckoutCharge_dialog(){
	clearViewCheckoutText();
	$('#checkoutChargeDetail').dialog("close");
}
