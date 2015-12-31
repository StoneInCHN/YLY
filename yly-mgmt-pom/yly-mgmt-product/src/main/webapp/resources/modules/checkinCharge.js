var checkinCharge_manager_tool = {
			add:function(){		
				$('#addCheckinCharge').dialog({    
				    title: message("yly.charge.checkin"),    
				    width: 650,    
				    height: 650,
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
									url:"../billing/checkinBill.jhtml",
									type:"post",
									data:$("#addCheckinCharge_form").serialize(),
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
												$('#addCheckinCharge').dialog("close");
												$("#checkinCharge_table_list").datagrid('reload');
												$('#addCheckinCharge_form').form('reset');
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
							 $('#addCheckinCharge').dialog("close");
							 $('#addCheckinCharge_form').form('reset');
							 $("#isMonthlyMeal").attr("checked","");
							 $('#isMonthlyMeal').trigger('click');
						}
				    }],
				    onOpen:function(){
				    	$('#addCheckinCharge_form').show();
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
						    	if(value==null || value=='') return;
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
				    	 //$('#addCheckinCharge_form').form('reset');
				    }
				});  
			},
	
			editBill:function(){
		    	var _edit_row = $('#checkinCharge_table_list')
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
				
				$.ajax({
					url:"../billing/isChargeinBillUpdated.jhtml",
					type:"get",
					data:{id:_edit_row[0].id},
					success:function(result){
						if(result){//缴费账单已修改过，不允许再次修改
							$.messager.alert(message("yly.common.prompt"),
									message("yly.chargein.billing.isUpdated"), 'warning');
							return false;
						}
						
						if(_edit_row[0].chargeStatus == "PAID"){
							$.messager.confirm(message("yly.common.confirm"), message("yly.charge.billing.confirm.paid.adjust"), function(r) {
								if(r){
								   checkinEditBill(_edit_row[0].id);
								}
							});
						}else{
							checkinEditBill(_edit_row[0].id);
						}
					}
		    	});
		     },
			
	  adjustment:function(){
	    	var _edit_row = $('#checkinCharge_table_list')
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
				$.messager.confirm(message("yly.common.confirm"), message("yly.charge.billing.confirm.paid.adjust"), function(r) {
					if(r){
					   checkinAdjustment(_edit_row[0].id);
					}
				});
			}else{
				checkinAdjustment(_edit_row[0].id);
			}
	      }
	}

function checkinEditBill(billId){
	$('#editCheckinBill').dialog({    
	    title: message("yly.charge.billing.edit"),    
	    width: 650,    
	    height: 650,
	    modal: true,
	    iconCls : 'icon-mini-edit',
	    href : '../billing/edit.jhtml?&id='+ billId,
	    cache: false, 
	    buttons:[{
	    	text:message("yly.common.save"),
	    	iconCls:'icon-save',
			handler:function(){
				var validate = $('#editCheckinCharge_form').form('validate');
				if(validate){
					$.ajax({
						url:"../billing/updateCheckinBill.jhtml",
						type:"post",
						data:$("#editCheckinCharge_form").serialize(),
						beforeSend:function(){
							$.messager.progress({
								text:message("yly.common.saving")
							});
						},
						success:function(result,response,status){
							    showSuccessMsg(result.content);
							    if(result.type == "success"){
							    	$.messager.progress('close');
									$('#editCheckinBill').dialog("close");
									$("#checkinCharge_table_list").datagrid('reload');
							    }else if(result.type == "error"){
							    	$.messager.progress('close');
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
				 $('#editCheckinBill').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	update_calculChargeInAmount();
	    	$('#update_mealPeriodStartDate').datebox({
	    	    onSelect: function(date){
	                var dayStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	    	$.ajax({
	    				url:"../systemConfig/getBillEndDate.jhtml",
	    				type:"post",
	    				data:{currentDay:dayStr},
	    				success:function(result){
	    					$('#update_mealPeriodEndDate').datebox({disabled:false});
	    					$('#update_mealPeriodEndDate').datebox('setValue',result.billDate);
	    					//console.log(result.periodMonth+"个月"+result.periodDay+"天");
	    					$('#update_periodMonMeal').val(result.periodMonth);
	    					$('#update_periodDayMeal').val(result.periodDay);
	    					$('#update_mealType').textbox("clear");
	    					$('#update_chargein_mealAmount').textbox("clear");
	    					$('#update_mealTypeVal').html("");
	    					$('#update_mealTypeVal').attr("data-value","");
	    					$('#update_mealPerMonth').html("");
	    					$('#update_mealPerMonth').attr("data-value","");
	    					$('#update_mealPerDay').html("");
	    					$('#update_mealPerDay').attr("data-value","");
	    				}
	    			});
	    	    }
	    	});
	    	
	    	$('#update_bedNursePeriodStartDate').datebox({
	    	    onSelect: function(date){
	                var dayStr = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	    	$.ajax({
	    				url:"../systemConfig/getBillEndDate.jhtml",
	    				type:"post",
	    				data:{currentDay:dayStr},
	    				success:function(result,response,status){
	    					$('#update_bedNursePeriodEndDate').datebox('setValue',result.billDate);
	    					var bedPerMonth = $('#update_bedPerMonth').attr("value");
	    					var bedPerDay = $('#update_bedPerDay').attr("value");
	    					var nurseLevelPerMonth = $('#update_nurseLevelPerMonth').attr("value");
	    					var nurseLevelPerDay = $('#update_nurseLevelPerDay').attr("value");
	    					var bedAmount = result.periodMonth*bedPerMonth+result.periodDay*bedPerDay;
	    					var nurseAmount = result.periodMonth*nurseLevelPerMonth+result.periodDay*nurseLevelPerDay;
	    					$('#update_chargein_bedAmount').numberbox('setValue',bedAmount);
	    					$('#update_chargein_nurseAmount').numberbox('setValue',nurseAmount);
	    				}
	    			});
	    	    }
	    	});
	    	
	    	//是否伙食费包月
	    	$("#update_isMonthlyMeal").click(function(){
	    		
	    		if($("#update_isMonthlyMeal").is(":checked")==true){
	    			$("#update_monthlyMeal").css('display','block');
	    			$("#update_mealRemark").textbox({
	    				disabled:false
	    				
	    			});
	    			$("#update_mealPeriodStartDate").textbox({
	    				disabled:false
	    				
	    			});
	    			$("#update_mealPeriodEndDate").textbox({
	    				disabled:false
	    				
	    			});
	    			$("#update_mealType").textbox({
	    				required:true,
	    				disabled:false
	    				
	    			});
	    			$("#update_chargein_mealAmount").numberbox({
	    				required:true,
	    				disabled:false
	    			});
	    			$("#update_monthlyMeal table input[type=hidden]").each(function(){
	    				$(this).attr("disabled",false);
	    			});
	    		}
	    		
	    		if($("#update_isMonthlyMeal").is(":checked")==false){
	    			$("#update_monthlyMeal").css('display','none'); 
	    			$("#update_mealRemark").textbox({
	    				disabled:true
	    			});
	    			$("#update_mealPeriodStartDate").textbox({
	    				disabled:true
	    				
	    			});
	    			$("#update_mealPeriodEndDate").textbox({
	    				disabled:true
	    				
	    			});
	    			$("#update_mealType").textbox('clear');
	    			$("#update_mealType").textbox({
	    				required:false,
	    				disabled:true
	    			});
	    			$("#update_chargein_mealAmount").numberbox('clear');
	    			$("#update_chargein_mealAmount").numberbox({
	    				required:false,
	    				disabled:true
	    			});
	    			$("#update_monthlyMeal table input[type=hidden]").each(function(){
	    				$(this).attr("disabled",true);
	    			});
	    			$('#update_mealTypeVal').html("");
					$('#update_mealTypeVal').attr("data-value","");
					$('#update_mealPerMonth').html("");
					$('#update_mealPerMonth').attr("data-value","");
					$('#update_mealPerDay').html("");
					$('#update_mealPerDay').attr("data-value","");
	    		}
	    		
	    	});
	    	
	    	$('#update_checkin_deposit').numberbox({
	    		onChange:function(value){
	    			update_calculChargeInAmount();
	    		}
	    	});
	    	
	    	$('#update_chargein_bedAmount').numberbox({
	    		onChange:function(value){
	    			update_calculChargeInAmount();
	    		}
	    	});
	    	
	    	$('#update_chargein_nurseAmount').numberbox({
	    		onChange:function(value){
	    			update_calculChargeInAmount();
	    		}
	    	});
	    	
	    	$('#update_chargein_mealAmount').numberbox({
	    		onChange:function(value){
	    			update_calculChargeInAmount();
	    		}
	    	});
	    	
	    	$("#update_mealType").combobox({    
			    valueField:'id',    
			    textField:'configValue',
			    cache: true,
			    panelHeight:100,
			    url:'../systemConfig/findByConfigKey.jhtml',
			    onBeforeLoad : function(param) {
			        param.configKey = 'MEALTYPE';// 参数
			    },
			    onLoadSuccess:function(){
			    	$('#update_mealType').combobox('setValue', $("#update_mealTypeId").val());
			    },
			    onChange:function(value){
			    	if(value==null || value=='') return;
			    	$.ajax({
						url:"../mealChargeConfig/detail.jhtml",
						type:"post",
						data:{configId:value},
						success:function(result,response,status){
							$('#update_mealTypeVal').html("["+result.chargeItem.configValue+"]");
							$('#update_mealTypeVal').attr("data-value",result.chargeItem.configValue);
							$('#update_mealPerMonth').html(message("yly.charge.record.perMonth")+result.amountPerMonth);
							$('#update_mealPerMonth').attr("data-value",result.amountPerMonth);
							$('#update_mealPerDay').html(message("yly.charge.record.perDay")+result.amountPerDay);
							$('#update_mealPerDay').attr("data-value",result.amountPerDay);
							var mealPerMonth = $('#update_mealPerMonth').attr("data-value");
							var mealPerDay = $('#update_mealPerDay').attr("data-value");
							var periodMonMeal=$('#update_periodMonMeal').val();
							var periodDayMeal=$('#update_periodDayMeal').val();

							if(periodMonMeal!="" && periodDayMeal!=""){
								var mealAmount = periodMonMeal*mealPerMonth+periodDayMeal*mealPerDay;
								console.log(mealAmount);
								$('#update_chargein_mealAmount').numberbox('setValue',mealAmount);
							}
							
						}
					});
			    }
			});
			    	
	    	function update_calculChargeInAmount(){
	    		var depositAmount = $('#update_checkin_deposit').numberbox('getValue');
	    		var bedAmount = $('#update_chargein_bedAmount').numberbox('getValue');
	    		var nurseAmount = $('#update_chargein_nurseAmount').numberbox('getValue');
	    		var mealAmount = $('#update_chargein_mealAmount').numberbox('getValue');
	    		//console.log(bedAmount+"-"+nurseAmount+"-"+mealAmount);
	    		if(bedAmount == null && nurseAmount == null && mealAmount == null && deposit == null){
	    			$('#update_chargein_totalAmount').numberbox("reset");
	    		}else{
	    			var totalAmount = Number(bedAmount)+Number(nurseAmount)+Number(mealAmount)+Number(depositAmount);
	    			$('#update_chargein_totalAmount').numberbox("setValue",totalAmount);
	    		}
	    	}
	    },
	    
	    onClose:function(){
	    }
	}); 
}

function checkinAdjustment(billId){
	$('#addCheckinAdjust').dialog({    
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
				var validate = $('#addCheckinAdjust_form').form('validate');
				if(validate){
					$.ajax({
						url:"../billingAdjust/billAmountAdjust.jhtml",
						type:"post",
						data:$("#addCheckinAdjust_form").serialize(),
						beforeSend:function(){
							$.messager.progress({
								text:message("yly.common.saving")
							});
						},
						success:function(result,response,status){
							    showSuccessMsg(result.content);
							    if(result.type == "success"){
							    	$.messager.progress('close');
									$('#addCheckinAdjust').dialog("close");
									$("#checkinCharge_table_list").datagrid('reload');
									$('#addCheckinAdjust_form').form("reset");
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
				 $('#addCheckinAdjust').dialog("close");
				 $('#addCheckinAdjust_form').form("reset");
			}
	    }],
	    
	    onOpen:function(){
	    	$('#addCheckinAdjust_form').show();
	    	$('#billId').val(billId);
	    	$("#adjustmentCause").combobox({    
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
	        $('#addCheckinAdjust_form').form("reset");
	    }
	}); 
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
			    height: 600, 
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
		    	  if(row.elderlyInfo.nursingLevel != null){
		    		  return row.elderlyInfo.nursingLevel.configValue;
		    	  }else{
		    		  return null;
		    	  }
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
		      //办理时间
		      {title:message("yly.charge.record.oprTime"),field:"createDate",width:35,align:'center',sortable:true,formatter: function(value,row,index){
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
			$("#mealType").textbox('reset');
			$("#mealType").textbox({
				required:false,
				disabled:true
			});
			$("#chargein_mealAmount").numberbox('reset');
			$("#chargein_mealAmount").numberbox({
				required:false,
				disabled:true
			});
			$("#monthlyMeal table input[type=hidden]").each(function(){
				$(this).attr("disabled",true);
			});
			$('#mealTypeVal').html("");
			$('#mealTypeVal').attr("data-value","");
			$('#mealPerMonth').html("");
			$('#mealPerMonth').attr("data-value","");
			$('#mealPerDay').html("");
			$('#mealPerDay').attr("data-value","");
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
					$('#chargein_bedAmount').numberbox('setValue',bedAmount+"");
					$('#chargein_nurseAmount').numberbox('setValue',nurseAmount+"");
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
					$('#mealPeriodEndDate').datebox({disabled:false});
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
	
	
	$('#addCheckinCharge_elderlyInfo').textbox({  
		  onChange: function(value){  
			  if(value==null||value=='') {
				  return;
			  }
			  var elderlyInfoID = $("#addCheckinCharge_elderlyInfoID").val();
			  $("#addCheckinCharge_form input[class*='easyui'][textboxname!=elderlyInfoName]").each(function(){
				  $(this).textbox('reset');
			  });
			  $("#addCheckinCharge_form span[class*='margin-left'][id!=billDay]").each(function(){
				  $(this).html("");
				  $(this).attr("data-value","");
			  });
			  
				$.ajax({
					url:"../billing/getBedNurseConfig.jhtml",
					type:"post",
					data:{elderlyInfoID:elderlyInfoID},
					success:function(result){
						if(result[0].errMsg!=null){
							$('#addCheckinCharge_elderlyInfo').textbox('reset');
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
	
	$('#checkin_deposit').numberbox({
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
		var depositAmount = $('#checkin_deposit').numberbox('getValue');
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
