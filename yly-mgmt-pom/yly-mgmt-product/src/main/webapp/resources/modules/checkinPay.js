var checkinPay_manager_tool = {
			add:function(){	
				var _edit_row = $('#checkinPay_table_list')
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
				$('#addCheckinPay').dialog({    
				    title: message("yly.pay.checkin"),    
				    width: 650,    
				    height: 650,
				    modal: true,
				    iconCls : 'icon-mini-edit',
				    href : '../billing/payPage.jhtml?path=checkinPay&id='
						+ _edit_row[0].id,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addCheckinPay_form').form('validate');
							if(validate){
								$.ajax({
									url:"../billing/billPay.jhtml",
									type:"post",
									data:$("#addCheckinPay_form").serialize(),
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
												$('#addCheckinPay').dialog("close");
												$("#checkinPay_table_list").datagrid('reload');
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
							 $('#addCheckinPay').dialog("close");
						}
				    }],
				    
				    onLoad:function(){
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
				    				var totalAmount=$('#chargeinPay_totalAmount').numberbox('getValue');
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
	
	$("#checkinPay_table_list").datagrid({
		title:message("yly.charge.record.list"),
		fitColumns:true,
		toolbar:"#checkinPay_manager_tool",
		url:'../billing/list.jhtml?billingType=CHECK_IN', 
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#checkinDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 680,    
			    height: 600, 
			    cache: false,
			    modal: true,
			    href:'../billing/details.jhtml?id='+rowData.id+'&path=checkinPay',
			    buttons:[{
					text:message("yly.common.print"),
					iconCls:'icon-print',
					handler:function(){
					    var newWindow=window.open(message("yly.common.detail"),"_blank");
					    var docStr = $('#checkinDetail').prop('outerHTML');
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
		      //收款人
		      {title:message("yly.charge.record.payStaff"),field:"payStaff",width:25,align:'center',sortable:true},
		      //缴费时间
		      {title:message("yly.charge.record.payTime"),field:"payTime",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}}
		   ]
		]

	});
	
	$("#checkinPay_search_btn").click(function(){
	  var _queryParams = $("#checkinPay_search_form").serializeJSON();
	  $('#checkinPay_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#checkinPay_table_list").datagrid('reload');
	});
	
})