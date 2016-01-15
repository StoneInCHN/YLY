var dailyBill_manager_tool = {
			add:function(){	
				var _edit_row = $('#dailyBill_table_list')
					.datagrid('getSelections');
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
					$.messager.alert(message("yly.common.prompt"),
							message("yly.charge.billing.prompt.already.paid"), 'warning');
					return false;
				}
				$('#addDailyBillPay').dialog({    
				    title: message("yly.pay.dailyBill"),    
				    width: 650,    
				    height: 650,
				    modal: true,
				    iconCls : 'icon-mini-edit',
				    href : '../billing/payPage.jhtml?path=dailyBill&id='
						+ _edit_row[0].id,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addDailyBillPay_form').form('validate');
							var payType = $("#paymentType").combobox('getValue');
							if(payType == "ADVANCE"){
								var advanceCharge = $("#dailyBill_advanceCharge").numberbox('getValue');
							    var totalAmount = $("#dailyBillPay_totalAmount").numberbox('getValue');
							    if(Number(totalAmount)>Number(advanceCharge)){
									$.messager.alert(message("yly.common.prompt"),message("yly.charge.advancePay.unavaliable"), 'warning');
									return;
								}
							}
							if(validate){
								
								$.ajax({
									url:"../billing/billPay.jhtml",
									type:"post",
									data:$("#addDailyBillPay_form").serialize(),
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
												$('#addDailyBillPay').dialog("close");
												$("#dailyBill_table_list").datagrid('reload');
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
							 $('#addDailyBillPay').dialog("close");
						}
				    }],
				    
				    onLoad:function(){
				    	function updateTotalAmount(){
				    		var waterAmount = $('#waterAmount').numberbox('getValue');
				    		var electricityAmount = $('#electricityAmount').numberbox('getValue');
				    		var curAmount = $('#curAmount').val();
				    		var totalAmount = Number(waterAmount)+Number(electricityAmount)+Number(curAmount);
			    			$('#dailyBillPay_totalAmount').numberbox("setValue",totalAmount);
				    		
				    	}
				    	
				    	$("#waterCount").numberbox({
				    		onChange:function(value){
				    			var waterPrice = $("#waterPrice").html();
				    			$("#waterAmount").numberbox('setValue',value*waterPrice);
				    			updateTotalAmount();
				    		}
				    	});
				    	$("#electricityCount").numberbox({
				    		onChange:function(value){
				    			var electricityPrice = $("#electricityPrice").html();
				    			$("#electricityAmount").numberbox('setValue',value*electricityPrice);
				    			updateTotalAmount();
				    		}
				    	});
				    	
				    	//支付方式
				    	$('#paymentType').combobox({
				    		onChange:function(value){
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
				    	
				    	//混合支付计算
				    	$('#totalAmount_cash').numberbox({
				    		onChange:function(value){
				    			if($('#paymentType').combobox('getValue')=="MIXTURE"){
				    				var totalAmount=$('#dailyBillPay_totalAmount').numberbox('getValue');
				    				$('#totalAmount_card').numberbox('setValue',totalAmount-value);
				    			}
				    			
				    		}
				    	});
				    	
				    	$('#totalAmount_card').numberbox({
				    		onChange:function(value){
				    			if($('#paymentType').combobox('getValue')=="MIXTURE"){
				    				var totalAmount=$('#chargeinPay_totalAmount').numberbox('getValue');
				    				$('#totalAmount_cash').numberbox('setValue',totalAmount-value);
				    			}
				    		}
				    	});
				    },
				    onOpen:function(){
				    },
				    onClose:function(){
				    }
				});  
			}
	}

$(function(){
	
	$("#dailyBill_table_list").datagrid({
		title:message("yly.charge.record.list"),
		fitColumns:true,
		toolbar:"#dailyBill_manager_tool",
		url:'../billing/list.jhtml?billingType=DAILY', 
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#dailyBillDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 680,    
			    height: 600, 
			    cache: false,
			    modal: true,
			    href:'../billing/details.jhtml?id='+rowData.id+'&path=dailyBill',
			    buttons:[{
					text:message("yly.common.print"),
					iconCls:'icon-print',
					handler:function(){
					    var newWindow=window.open(message("yly.common.detail"),"_blank");
					    var docStr = $('#dailyBillDetail').prop('outerHTML');
					    console.info(docStr);
					    newWindow.document.write(docStr);
					    newWindow.document.close();
					    newWindow.print();
					    newWindow.close();
					}
			    },{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#dailyBillDetail').dialog("close");
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
		      //床位费(元)
		      {title:message("yly.charge.record.bed"),field:"bedAmount",width:20,align:'center',sortable:true},
		      //护理费(元)
		      {title:message("yly.charge.record.nurse"),field:"nurseAmount",width:20,align:'center',sortable:true},
		      //伙食费(元)
		      {title:message("yly.charge.record.meal"),field:"mealAmount",width:20,align:'center',sortable:true},
		      //服务费(元)
		      {title:message("yly.charge.record.service"),field:"personalizedAmount",width:20,align:'center',sortable:true},
		      //水费(元)
		      {title:message("yly.charge.record.water"),field:"waterAmount",width:20,align:'center',sortable:true},
		      //电费(元)
		      {title:message("yly.charge.record.electricity"),field:"electricityAmount",width:20,align:'center',sortable:true},
		      //总额(元)
		      {title:message("yly.charge.record.totalAmount"),field:"totalBillAmount",width:20,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(row.totalAmount == 0){
		    		  return null;
		    	  }else{
		    		  return row.totalAmount;
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
		    	  	if(value == "UNPAID_ADJUSTMENT"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.status.unpaid_adjustment")+"</font>";
		    	  	}
		      	}},
		      //收款人
		      {title:message("yly.charge.record.payStaff"),field:"payStaff",width:25,align:'center',sortable:true},
		      //收款时间段
		      {title:message("yly.charge.record.period"),field:"periodEndDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM");
		    	  	}
			  }},
		   ]
		]

	});
	
	$("#dailyBill_search_btn").click(function(){
	  var _queryParams = $("#dailyBillPay_search_form").serializeJSON();
	  $('#dailyBill_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#dailyBill_table_list").datagrid('reload');
	});
	
})