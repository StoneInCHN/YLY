var checkoutPay_manager_tool = {
		add:function(){		
			var _edit_row = $('#checkoutPay-table-list').datagrid('getSelections');
			if (_edit_row.length == 0) {
				$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"), 'warning');
				return false;
			}
			if (_edit_row.length>1) {
				$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow.unique"), 'warning');
				return false;
			}
			if(_edit_row[0].chargeStatus == "PAID"){
				$.messager.alert(message("yly.common.prompt"),message("yly.charge.billing.prompt.already.paid"), 'warning');
				return false;
			}
			$('#addCheckoutPay').dialog({    
			    title: message("yly.charge.checkoutPay"),    
			    width: 650,    
			    height: 800,
			    iconCls:'icon-mini-add',
			    modal:true,
			    cache: false, 
			    onOpen:function(){
			    	$('#addCheckoutPay_form').show();
					$.ajax({
						url :'../checkout/details.jhtml?id='+_edit_row[0].id,
						type : "get",
						success : function(billing, status) {
							if(billing != null){
								$('#addCheckoutPay_checkoutBillingId').val(billing.id);
								$('#addCheckoutPay_elderlyInfo_outHospitalizedDate').textbox('setValue',new Date(billing.elderlyInfo.outHospitalizedDate).
										Format(message("yly.common.dateFormatChina")));
								$('#addCheckoutPay_elderlyInfo_name').textbox('setValue',billing.elderlyInfo.name);
								$('#addCheckoutPay_elderlyInfo_identifier').textbox('setValue',billing.elderlyInfo.identifier);
								$('#addCheckoutPay_elderlyInfo_bedLocation').textbox('setValue',billing.elderlyInfo.bedLocation);
								$('#addCheckoutPay_elderlyInfo_nursingLevel_configValue').textbox('setValue',billing.elderlyInfo.nursingLevel.configValue);
								$('#addCheckoutPay_bedNurseAmount').numberbox('setValue', billing.bedNurseCharge.bedAmount+billing.bedNurseCharge.nurseAmount);
								$('#addCheckoutPay_bedNurseCharge_remark').textbox('setValue',billing.bedNurseCharge.remark);
								$('#addCheckoutPay_mealAmount').numberbox('setValue', billing.mealCharge.mealAmount);
								$('#addCheckoutPay_mealCharge_remark').textbox('setValue', billing.mealCharge.remark);
								
								$('#addCheckoutPay_waterCount').numberbox('setValue',billing.waterElectricityCharge.waterCount);
								$('#addCheckoutPay_waterAmount').numberbox('setValue',billing.waterElectricityCharge.waterAmount);
								$('#addCheckoutPay_electricityCount').numberbox('setValue',billing.waterElectricityCharge.electricityCount);
								$('#addCheckoutPay_electricityAmount').numberbox('setValue',billing.waterElectricityCharge.electricityAmount);
								$('#addCheckoutPay_waterPrice').numberbox('setValue',billing.waterElectricityCharge.waterAmount/billing.waterElectricityCharge.waterCount);
								$('#addCheckoutPay_electricityPrice').numberbox('setValue',billing.waterElectricityCharge.electricityAmount/billing.waterElectricityCharge.electricityCount);
								
								$('#addCheckoutPay_personalizedAmount').numberbox('setValue',billing.personalizedCharge.personalizedAmount);
								$('#addCheckoutPay_personalizedCharge_remark').textbox('setValue',billing.personalizedCharge.remark);
								//$('#addCheckoutPay_advanceChargeAmount').numberbox('setValue',billing.advanceChargeAmount);
								$('#addCheckoutPay_totalAmount').numberbox('setValue',billing.totalAmount);
								
								showAdjustment(billing,"addCheckoutPay_djustmentService");//显示调账信息
							}
						}});
			    },
			    buttons:[{
					text:message("yly.charge.checkoutPay"),
					iconCls:'icon-save',
					handler:function(){
						 //退住结算
						var validate = $('#addCheckoutPay_form').form('validate');
						if(validate){
							$.messager.confirm(message("yly.common.prompt"), message("yly.checkout.confirm_checkoutPay_for_elderly", 
									$("#addCheckoutPay_elderlyInfo_name").textbox('getValue'),$("#addCheckoutPay_totalAmount").numberbox('getValue') ), function(r) {
										if(r){
											$("#addCheckoutPay_totalAmount").removeAttr("disabled"); 
											$.ajax({
												url:"../checkout/checkoutBillPay.jhtml",
												type:"post",
												data:$("#addCheckoutPay_form").serialize(),
												beforeSend:function(){
													$.messager.progress({
														text:message("yly.common.saving")
													});
												},
												success:function(result,response,status){
													    showSuccessMsg(result.content);
													    close_addCheckoutPay_dialog();
													    if(result.type == "success"){
													    	$.messager.progress('close');
															$('#addCheckoutPay').dialog("close");
															$("#checkoutPay-table-list").datagrid('reload');
													    }
														
												},
												error:function (XMLHttpRequest, textStatus, errorThrown) {
													$.messager.progress('close');
													alertErrorMsg();
												}
											});
										}
									});
						}else{
							$.messager.alert(message("yly.common.prompt"),message("yly.checkout.confirm_paymentType_amount"),'warning');
						}			
					}
			    },{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						close_addCheckoutPay_dialog();
					}
			    }
			    ]
			});   
		},
		edit:function(){},
		remove:function(){
			listRemove('checkoutPay-table-list','../checkoutPay/delete.jhtml');
		}
}


$(function(){
	
	$("#checkoutPay-table-list").datagrid({
		title:message("yly.checkout.dialog.title"),
		fitColumns:true,
		toolbar:"#checkoutPay_manager_tool",
		url:'../billing/list.jhtml?billingType=CHECK_OUT',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkoutPayDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 650,    
			    height: 800,
			    iconCls:'icon-mini-add',
			    modal:true,
			    cache: false, 
			    onOpen:function(){
			    	$('#checkoutPay_form').show();
					$.ajax({
						url :'../checkout/details.jhtml?id='+rowData.id,
						type : "get",
						success : function(billing, status) {
							if(billing != null){
								$('#viewCheckoutPay_elderlyInfo_outHospitalizedDate').html(new Date(billing.elderlyInfo.outHospitalizedDate).
										Format(message("yly.common.dateFormatChina")));
								$('#viewCheckoutPay_elderlyInfoLable').html(message("yly.common.elderlyInfo"));
								showElderlyInfo(billing,"viewCheckoutPay_elderlyInfo_remark");//显示老人基本信息
								$('#viewCheckoutPay_bedNurseAmount').html(message("yly.bedNurse.charge.label",(billing.bedNurseCharge.bedAmount+billing.bedNurseCharge.nurseAmount).toFixed(2)));
								$('#viewCheckoutPay_bedNurseCharge_remark').html(billing.bedNurseCharge.remark);
								$('#viewCheckoutPay_mealAmount').html(message("yly.meal.charge.label",billing.mealCharge.mealAmount.toFixed(2)));
								$('#viewCheckoutPay_mealCharge_remark').html(billing.mealCharge.remark);
								$('#viewCheckoutPay_waterElectricityAmount').html(message("yly.waterElectricity.charge.label",
									   (billing.waterElectricityCharge.waterAmount+billing.waterElectricityCharge.electricityAmount).toFixed(2)));
								$('#viewCheckoutPay_waterElectricity_remark').html(message("yly.waterElectricity.remark.label",
										billing.waterElectricityCharge.waterCount.toFixed(1),(billing.waterElectricityCharge.waterAmount/billing.waterElectricityCharge.waterCount).toFixed(2),
										billing.waterElectricityCharge.waterAmount.toFixed(2),billing.waterElectricityCharge.electricityAmount.toFixed(1),
									   (billing.waterElectricityCharge.electricityAmount/billing.waterElectricityCharge.electricityCount).toFixed(2),billing.waterElectricityCharge.electricityAmount.toFixed(2)));						
								$('#viewCheckoutPay_personalizedAmount').html(message("yly.personalized.charge.label",billing.personalizedCharge.personalizedAmount.toFixed(2)));
								$('#viewCheckoutPay_personalizedCharge_remark').html(billing.personalizedCharge.remark);
								$('#viewCheckoutPay_advanceChargeAmount').html(billing.advanceChargeAmount.toFixed(2));
								$('#viewCheckoutPay_totalAmount').html(billing.totalAmount.toFixed(2));
								$('#viewCheckoutPay_djustmentLable').html(message("yly.charge.billing.adjustment"));
								showAdjustment(billing,"viewCheckoutPay_djustmentService");//显示调账信息
							}
						}});
			    },
			    buttons:[{
					text:message("yly.common.print"),
					iconCls:'icon-print',
					handler:function(){
					    var newWindow=window.open(message("yly.common.detail"),"_blank");
					    var docStr = $('#checkoutPayDetail').prop('outerHTML');
					   console.info(docStr);
					    newWindow.document.write(docStr);
					    newWindow.document.close();
					    newWindow.print();
					    newWindow.close();
						// $('#listEvaluating').dialog("close");
					}
			    },{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						close_viewCheckoutPay_dialog();
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
		      {title:"账单状态",field:"chargeStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
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
			  //收款人
			  {title:message("yly.charge.record.payStaff"),field:"payStaff",width:25,align:'center',sortable:true},
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
		],
		onLoadSuccess:function(data){
	           $(".tips-span").tooltip({
	        	   position: 'top',
	               onShow: function(){
	                   $(this).tooltip('tip').css({ 
	                       width:'300'
	                   });
	               }
	           });
	        }

	});
	
	$("#checkoutPay_search_btn").click(function(){
	 var _queryParams = $("#checkoutPay_search_form").serializeJSON();
	  $('#checkoutPay-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#checkoutPay-table-list").datagrid('reload');
	});
 	//支付方式
	$('#addCheckoutPay_paymentType').combobox({
		onChange:function(value){
			if(value == "MIXTURE"){
				$('#addCheckoutPay_mixturePay').css("display","table-row");
				$("#addCheckoutPay_totalAmount_cash").numberbox({
					required:true,
				});
				$("#addCheckoutPay_totalAmount_card").numberbox({
					required:true,
				});
			}else{
				$('#addCheckoutPay_mixturePay').css("display","none");
				$("#addCheckoutPay_totalAmount_cash").numberbox('reset');
				$("#addCheckoutPay_totalAmount_cash").numberbox({
					required:false,
				});
				$("#addCheckoutPay_totalAmount_card").numberbox('reset');
				$("#addCheckoutPay_totalAmount_card").numberbox({
					required:false,
				});
				
			}
		}
	});
	
	//混合支付计算
	$('#addCheckoutPay_totalAmount_cash').numberbox({
		onChange:function(value){
			if($('#addCheckoutPay_paymentType').combobox('getValue')=="MIXTURE"){
				var totalAmount=$('#addCheckoutPay_totalAmount').numberbox('getValue');
				$('#addCheckoutPay_totalAmount_card').numberbox('setValue',totalAmount-value);
			}
			
		}
	});
	
	$('#addCheckoutPay_totalAmount_card').numberbox({
		onChange:function(value){
			if($('#addCheckoutPay_paymentType').combobox('getValue')=="MIXTURE"){
				var totalAmount=$('#addCheckoutPay_totalAmount').numberbox('getValue');
				$('#addCheckoutPay_totalAmount_cash').numberbox('setValue',totalAmount-value);
			}
		}
	});
});

function clearAddCheckoutPayText(){
		$("input").each(function(){
		   $(this).val(null);
		});
		$("textarea").each(function(){
			   $(this).val(null);
		});		
}
function close_addCheckoutPay_dialog(){
	clearAddCheckoutPayText();
	$('#addCheckoutPay').dialog("close");
}
function clearViewCheckoutPayText(){
	alert("clearViewCheckoutPayText");
	$("p[title=viewCheckoutPay]").each(function(){
	   $(this).html(null);
	});
	$("span[title=viewCheckoutPay]").each(function(){
		   $(this).html(null);
	});		
}
function close_viewCheckoutPay_dialog(){
	clearViewCheckoutPayText();
	$('#checkoutPayDetail').dialog("close");
}