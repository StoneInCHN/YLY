var checkoutCharge_manager_tool = {
			add:function(){		
				$('#addCheckoutCharge').dialog({    
				    title: message("yly.charge.checkout"),    
				    width: 650,    
				    height: 750,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addCheckoutCharge_form').form('validate');
							if(validate){
								$.ajax({
									url:"../billing/checkout.jhtml",
									type:"post",
									data:$("#addCheckoutCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										    showSuccessMsg(result.content);
										    console.log(result);
										    if(result.type == "success"){
										    	$.messager.progress('close');
												$('#addCheckoutCharge').dialog("close");
												$("#checkoutCharge_table_list").datagrid('reload');
												$('#addCheckoutCharge_form').form('reset');
												$("#isMonthlyMeal").attr("checked","");
												$('#isMonthlyMeal').trigger('click');
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
							 $('#addCheckoutCharge').dialog("close");
							 $('#addCheckoutCharge_form').form('reset');
							 $("#isMonthlyMeal").attr("checked","");
							 $('#isMonthlyMeal').trigger('click');
						}
				    }],
				    onOpen:function(){
				    	$('#addCheckoutCharge_form').show();
				    	$.ajax({
							url:"../systemConfig/findByConfigKey.jhtml",
							type:"post",
							data:{configKey:"BILLDAY"},
							success:function(result){
//								console.log(result);
								$('#billDay').html(message("yly.charge.billing.day.prefix")+result[0].configValue+message("yly.charge.billing.day.suffix"));
								
							}
				    	});
				    	
				    	$("#mealType").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    panelHeight:100,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'MEALTYPE';// 参数
						    },
						    onChange:function(value){
						    	$.ajax({
									url:"../mealChargeConfig/detail.jhtml",
									type:"post",
									data:{configId:value},
									success:function(result,response,status){
										$('#mealTypeVal').html("["+result.chargeItem.configValue+"]");
										$('#mealTypeVal').attr("data-value",result.chargeItem.configValue);
										$('#mealPerMonth').html(message("yly.charge.record.perMonth")+result.amountPerMonth);
										$('#mealPerMonth').attr("data-value",result.amountPerMonth);
										$('#mealPerDay').html(message("yly.charge.record.perDay")+result.amountPerDay);
										$('#mealPerDay').attr("data-value",result.amountPerDay);
										var mealPerMonth = $('#mealPerMonth').attr("data-value");
										var mealPerDay = $('#mealPerDay').attr("data-value");
										var periodMonMeal=$('#periodMonMeal').val();
										var periodDayMeal=$('#periodDayMeal').val();
										if(periodMonMeal!="" && periodDayMeal!=""){
											var mealAmount = periodMonMeal*mealPerMonth+periodDayMeal*mealPerDay;
											$('#chargein_mealAmount').numberbox('setValue',mealAmount);
										}
										
										
									}
								});
						    }
						});
				    },
				    onClose:function(){
				    	 //$('#addCheckoutCharge_form').form('reset');
				    }
				});  
			}
	}

$(function(){
	
	$("#checkoutCharge_table_list").datagrid({
		title:message("yly.charge.checkout.list"),
		fitColumns:true,
		toolbar:"#checkoutCharge_manager_tool",
		url:'../billing/list.jhtml?billingType=CHECK_OUT', 
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkoutDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../billing/details.jhtml?id='+rowData.id+'&path=checkoutCharge',
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#checkoutDetail').dialog("close");
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
//		      {title:message("yly.common.bedRoom"),field:"elderlyInfoBedRoom",width:50,align:'center',formatter:function(value,row,index){
//		    	  return row.elderlyInfo.bedLocation;
//		      }},
		      //护理等级
//		      {title:message("yly.common.nurseLevel"),field:"elderlyInfoNurseLevel",width:30,align:'center',formatter:function(value,row,index){
//		    	  return row.elderlyInfo.nursingLevel.configValue;
//		      }},
		      //押金(元)
		      {title:message("yly.charge.record.depositAmount"),field:"depositAmount",width:20,align:'center',sortable:true},
		      //床位费(元)
		      {title:message("yly.charge.record.bed"),field:"bedAmount",width:20,align:'center',sortable:true},
		      //护理费(元)
		      {title:message("yly.charge.record.nurse"),field:"nurseAmount",width:20,align:'center',sortable:true},
		      //伙食费(元)
		      {title:message("yly.charge.record.meal"),field:"mealAmount",width:20,align:'center',sortable:true},
		      //水费(元)
		      {title:message("yly.charge.record.water"),field:"waterAmount",width:20,align:'center',sortable:true},
		      //电费(元)
		      {title:message("yly.charge.record.electricity"),field:"electricityAmount",width:20,align:'center',sortable:true},
		      //服务费(元)
		      {title:message("yly.charge.record.service"),field:"personalizedAmount",width:20,align:'center',sortable:true},
		      //总额(元)
		      {title:message("yly.charge.record.totalAmount"),field:"totalAmount",width:20,align:'center',sortable:true},
		      //缴费时间
		      {title:message("yly.charge.record.calTime"),field:"payTime",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}}
		   ]
		]

	});
	
	$("#checkoutCharge_search_btn").click(function(){
	  var _queryParams = $("#checkoutCharge_search_form").serializeJSON();
	  $('#checkoutCharge_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#checkoutCharge_table_list").datagrid('reload');
	});
	
	//是否伙食费包月
	$("#isMonthlyMeal").click(function(){
		
		if($("#isMonthlyMeal").is(":checked")==true){
			$("#monthlyMeal").css('display','block');
			$("#mealRemark").textbox({
				disabled:false
				
			});
			$("#mealPeriodStartDate").textbox({
				disabled:false
				
			});
			$("#mealPeriodEndDate").textbox({
				disabled:false
				
			});
			$("#mealType").textbox({
				required:true,
				disabled:false
				
			});
			$("#chargein_mealAmount").numberbox({
				required:true,
				disabled:false
			});
			$("#monthlyMeal table input[type=hidden]").each(function(){
				$(this).attr("disabled",false);
			});
		}
		
		if($("#isMonthlyMeal").is(":checked")==false){
			$("#monthlyMeal").css('display','none'); 
			$("#mealRemark").textbox({
				disabled:true
				
			});
			$("#mealPeriodStartDate").textbox({
				disabled:true
				
			});
			$("#mealPeriodEndDate").textbox({
				disabled:true
				
			});
			$("#mealType").textbox({
				required:false,
				disabled:true
				
			});
			$("#chargein_mealAmount").numberbox({
				required:false,
				disabled:true
			});
			$("#monthlyMeal table input[type=hidden]").each(function(){
				$(this).attr("disabled",true);
			});
		}
		
	});
	
	//支付方式
	$('#paymentType').combobox({
		onChange:function(value){
			console.log("111"+value);
			if(value == "MIXTURE"){
				$('#mixturePay').css("display","table-row");
				$("#totalAmount_cash").numberbox({
					required:true,
				});
				$("#totalAmount_card").numberbox({
					required:true,
				});
			}else{
				$('#mixturePay').css("display","none");
				$("#totalAmount_cash").numberbox('reset');
				$("#totalAmount_cash").numberbox({
					required:false,
				});
				$("#totalAmount_card").numberbox('reset');
				$("#totalAmount_card").numberbox({
					required:false,
				});
				
			}
		}
	});
	
	$('#chargein_totalAmount').numberbox({
		onChange:function(value){
			$("#totalAmount_card").numberbox('reset');
			$("#totalAmount_cash").numberbox('reset');
		}
	});
	//混合支付计算
	$('#totalAmount_cash').numberbox({
		onChange:function(value){
			if($('#paymentType').combobox('getValue')=="MIXTURE"){
				var totalAmount=$('#chargein_totalAmount').numberbox('getValue');
				$('#totalAmount_card').numberbox('setValue',totalAmount-value);
			}
			
		}
	});
	
	$('#totalAmount_card').numberbox({
		onChange:function(value){
			if($('#paymentType').combobox('getValue')=="MIXTURE"){
				var totalAmount=$('#chargein_totalAmount').numberbox('getValue');
				$('#totalAmount_cash').numberbox('setValue',totalAmount-value);
			}
		}
	});
	
	$('#bedNursePeriodStartDate').datebox({
	    onSelect: function(date){
            var dayStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	$.ajax({
				url:"../systemConfig/getBillEndDate.jhtml",
				type:"post",
				data:{currentDay:dayStr},
				success:function(result,response,status){
					$('#bedNursePeriodEndDate').datebox('setValue',result.billDate);
					//console.log(result.periodMonth+"个月"+result.periodDay+"天");
					var bedPerMonth = $('#bedPerMonth').attr("data-value");
					var bedPerDay = $('#bedPerDay').attr("data-value");
					var nurseLevelPerMonth = $('#nurseLevelPerMonth').attr("data-value");
					var nurseLevelPerDay = $('#nurseLevelPerDay').attr("data-value");
					var bedAmount = result.periodMonth*bedPerMonth+result.periodDay*bedPerDay;
					var nurseAmount = result.periodMonth*nurseLevelPerMonth+result.periodDay*nurseLevelPerDay;
					$('#chargein_bedAmount').numberbox('setValue',bedAmount);
					$('#chargein_nurseAmount').numberbox('setValue',nurseAmount);
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
					$('#mealPeriodEndDate').datebox('setValue',result.billDate);
					//console.log(result.periodMonth+"个月"+result.periodDay+"天");
					$('#periodMonMeal').val(result.periodMonth);
					$('#periodDayMeal').val(result.periodDay);
					
					$('#mealType').textbox("reset");
					$('#chargein_mealAmount').textbox("reset");
					$('#mealTypeVal').html("");
					$('#mealTypeVal').attr("data-value","");
					$('#mealPerMonth').html("");
					$('#mealPerMonth').attr("data-value","");
					$('#mealPerDay').html("");
					$('#mealPerDay').attr("data-value","");
				}
			});
	    }
	});
	
	
	$('#addCheckoutCharge_elderlyInfo').textbox({  
		  onChange: function(value){  
			  if(value==null||value=='') {
				  return;
			  }
			  var elderlyInfoID = $("#addCheckoutCharge_elderlyInfoID").val();
			  $("#addCheckoutCharge_form input[class*='easyui'][textboxname!=elderlyInfoName]").each(function(){
				  $(this).textbox('reset');
			  });
			  $("#addCheckoutCharge_form span[class*='margin-left'][id!=billDay]").each(function(){
				  $(this).html("");
				  $(this).attr("data-value","");
			  });
			  
			  $('#mixturePay').css("display","none");
			  $("#totalAmount_cash").numberbox('reset');
			  $("#totalAmount_card").numberbox('reset');
			  
				$.ajax({
					url:"../billing/getBedNurseConfig.jhtml",
					type:"post",
					data:{elderlyInfoID:elderlyInfoID},
					success:function(result){
						if(result[0].errMsg!=null){
							$('#addCheckoutCharge_elderlyInfo').textbox('reset');
							showSuccessMsg(result[0].errMsg);
							return;
						}
					$('#bedType').html("["+result[0].chargeItem.configValue+"]");
					$('#bedType').attr("data-value",result[0].chargeItem.configValue);
					$('#bedPerMonth').html(message("yly.charge.record.perMonth")+result[0].amountPerMonth);
					$('#bedPerMonth').attr("data-value",result[0].amountPerMonth);
					$('#bedPerDay').html(message("yly.charge.record.perDay")+result[0].amountPerDay);
					$('#bedPerDay').attr("data-value",result[0].amountPerDay);
					
					$('#nurseLevel').html("["+result[1].chargeItem.configValue+"]");
					$('#nurseLevel').attr("data-value",result[1].chargeItem.configValue);
					$('#nurseLevelPerMonth').html(message("yly.charge.record.perMonth")+result[1].amountPerMonth);
					$('#nurseLevelPerMonth').attr("data-value",result[1].amountPerMonth);
					$('#nurseLevelPerDay').html(message("yly.charge.record.perDay")+result[1].amountPerDay);
					$('#nurseLevelPerDay').attr("data-value",result[1].amountPerDay);
						
					}
				});
		  }  
		});
	
	$('#checkout_deposit').numberbox({
		onChange:function(value){
			calculChargeInAmount();
		}
	});
	
	$('#chargein_bedAmount').numberbox({
		onChange:function(value){
			calculChargeInAmount();
		}
	});
	
	$('#chargein_nurseAmount').numberbox({
		onChange:function(value){
			calculChargeInAmount();
		}
	});
	
	$('#chargein_mealAmount').numberbox({
		onChange:function(value){
			calculChargeInAmount();
		}
	});
	
	function calculChargeInAmount(){
		var depositAmount = $('#checkout_deposit').numberbox('getValue');
		var bedAmount = $('#chargein_bedAmount').numberbox('getValue');
		var nurseAmount = $('#chargein_nurseAmount').numberbox('getValue');
		var mealAmount = $('#chargein_mealAmount').numberbox('getValue');
		//console.log(bedAmount+"-"+nurseAmount+"-"+mealAmount);
		if(bedAmount == null && nurseAmount == null && mealAmount == null && deposit == null){
			$('#chargein_totalAmount').numberbox("reset");
		}else{
			var totalAmount = Number(bedAmount)+Number(nurseAmount)+Number(mealAmount)+Number(depositAmount);
			$('#chargein_totalAmount').numberbox("setValue",totalAmount);
		}
		
	}

})