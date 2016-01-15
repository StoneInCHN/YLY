<form id="addDailyBillPay_form" method="post"> 
	    <table class="table table-striped">
	    	<input type="hidden" name="billingId" value="${billing.id}">
	    	<input type="hidden" id="curAmount" value="${currentAmount}">
	    	<tr>
	    		<th width="90px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" value="${billing.elderlyInfo.name}" style="width:85px;" data-options="disabled:true"/>   
	    		</td>
	    		<th width="90px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" value="${billing.elderlyInfo.identifier}" style="width:130px;" data-options="disabled:true"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom" value="${billing.elderlyInfo.bedLocation}" style="width:180px;" data-options="disabled:true"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select class="easyui-combobox" name="elderlyInfoNurseLevel" style="width:85px;" data-options="disabled:true">   
						<option value="${billing.elderlyInfo.nursingLevel.id}" selected="selected">${billing.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${billing.invoiceNo}" style="width:150px;" data-options="disabled:true"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${billing.billingNo}" style="width:150px;" data-options="disabled:true"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.advanceCharge.money")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" id="dailyBill_advanceCharge" name="advanceCharge"  readonly=true value="${billing.elderlyInfo.advanceChargeAmount}" style="width:150px;" data-options="disabled:true"/> 
	    		</td>
	    	</tr>
	    	 <tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input value="${billing.bedNurseCharge.periodStartDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>
					    		    ──
				    			    <input value="${billing.bedNurseCharge.periodEndDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>    
					    		</td>
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.bed")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="dailyBedAmount" value="${billing.bedAmount}" data-options="disabled:true,min:0,precision:2" />
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.nurse")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="dailyNurseAmount" value="${billing.nurseAmount}" data-options="disabled:true,min:0,precision:2" />
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" value="${billing.bedNurseCharge.remark}" style="width:400px" data-options="disabled:true"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	
	    	[#if billing.elderlyInfo.mealFeeMonthlyPayment?? && billing.elderlyInfo.mealFeeMonthlyPayment=="true"]
	    	<tr>
	    		<td colspan=4>
	    		    <fieldset> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input value="${billing.mealCharge.periodStartDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>
					    		    ──
				    			    <input value="${billing.mealCharge.periodEndDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>    
					    		</td>
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.mealCharge.mealType")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" value="${billing.elderlyInfo.mealType.configValue}" data-options="disabled:true" />   
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="dailyMealAmount" value="${billing.mealAmount}" data-options="min:0,precision:2,disabled:true" />
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" id="mealRemark" value="${billing.mealCharge.remark}" style="width:400px" data-options="disabled:true"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	[/#if]
	    	[#if billing.personalizedCharge??]
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input value="${billing.personalizedCharge.periodStartDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>
					    		    ──
				    			    <input value="${billing.personalizedCharge.periodEndDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>    
					    		</td>
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.service.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="dailyServiceAmount" value="${billing.personalizedAmount}" data-options="disabled:true,min:0,precision:2" />
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" value="${billing.personalizedCharge.remark}" style="width:400px" data-options="disabled:true"/> 
					    		</td>
	    			  		</tr>
	    			  		<tr>
					    		<td colspan=2>
					    			  <fieldset> 
					    			  	<legend>${message("yly.charge.record.service.details")}</legend>
					    			  	<table class="table table-striped">
					    			  		<tr>
					    			  			<th>${message("yly.charge.record.service.item")}</th>
						    			  		<th>${message("yly.common.charge.unitPrice")}</th>
						    			  		<th>${message("yly.common.charge.count")}</th>
						    			  		<th>${message("yly.common.charge.money")}</th>
					    			  		</tr>
					    			  		[#list serviceDetails as serviceItem]
					    			  		<tr>
					    			  			<td>${serviceItem.serviceName}</td>
					    			  			<td>${serviceItem.serviceUnitPrice}</td>
					    			  			<td>${serviceItem.serviceCount}</td>
					    			  			<td>${serviceItem.serviceAmount}</td>
					    			  		</tr> 
					    			  		[/#list]
					    			  	</table>
					    			  </fieldset>
								 </td>
							</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	[/#if]
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.record.water.electricity")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input value="${billing.periodStartDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>
					    		    ──
				    			    <input value="${billing.periodEndDate}" type="text" class="easyui-datebox" style="width:100px;" data-options="disabled:true"/>    
					    		</td>
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" name="waterElectricity_remark" style="width:400px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  		<tr>
					    		<td colspan=2>
					    			  <fieldset> 
					    			  	<legend>${message("yly.charge.record.water.electricity.details")}</legend>
					    			  	<table class="table table-striped">
					    			  		<tr>
					    			  			<th>${message("yly.charge.record.item")}</th>
						    			  		<th>${message("yly.common.charge.unitPrice")}</th>
						    			  		<th>${message("yly.charge.record.water.electricity.count")}</th>
						    			  		<th>${message("yly.common.charge.money")}</th>
					    			  		</tr>
					    			  		
					    			  		<tr>
					    			  			<td>${message("yly.charge.water")}</td>
					    			  			<td><span id="waterPrice">${waterElectricityConfig.waterUnitPrice}</span></td>
					    			  			<td><input class="easyui-numberbox" name="waterCount" id="waterCount" style="width:50px" data-options="required:true,min:0,precision:2" /></td>
					    			  			<td><input class="easyui-numberbox" name="waterAmount" id="waterAmount" style="width:50px" data-options="required:true,editable:false,min:0,precision:2" /></td>
					    			  		</tr> 
					    			  		<tr>
					    			  			<td>${message("yly.charge.electricity")}</td>
					    			  			<td><span id="electricityPrice">${waterElectricityConfig.electricityUnitPrice}</span></td>
					    			  			<td><input class="easyui-numberbox" name="electricityCount" id="electricityCount" style="width:50px" data-options="required:true,min:0,precision:2" /></td>
					    			  			<td><input class="easyui-numberbox" name="electricityAmount" id="electricityAmount" style="width:50px" data-options="required:true,editable:false,min:0,precision:2" /></td>
					    			  		</tr> 
					    			  	</table>
					    			</fieldset>
							   </td>
							</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
			</tr>
	    	[#if billing.billingAdjustment?? && billing.billingAdjustment.size()>0]
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.bill.adjustment")}</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th>${message("yly.bill.adjustment.cause")}</th>
		    			  		<th>${message("yly.bill.adjustment.amount")}</th>
		    			  		<th>${message("yly.common.charge.status")}</th>
		    			  		<th>${message("yly.common.charge.operator")}</th>
		    			  		<th>${message("yly.common.charge.oprTime")}</th>
	    			  		</tr>
	    			  			[#list billing.billingAdjustment as adjustItem]
		    			  		   <tr>
			    			  			<td>${adjustItem.adjustmentCause}</td>
			    			  			<td>${adjustItem.adjustmentAmount}</td>
			    			  			<td>${message("yly.common.charge.status.${adjustItem.chargeStatus}")}</td>
			    			  			<td>${adjustItem.operator}</td>
			    			  			<td>${adjustItem.createDate}</td>
		    			  		   </tr> 
	    			  			[/#list]
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	 </tr>
	    	[/#if]
	    	<tr>
	    		<td colspan=4></td>
	    	</tr>
	    	[#if billing.chargeStatus == "UNPAID_ADJUSTMENT"]
		    	<tr>
		    		<th>${message("yly.common.charge.totalAmount.PAID")}:</th>
		    		<td colspan=3>
		    			 <input class="easyui-numberbox" value="${paidAmount}" data-options="disabled:true,min:0,precision:2"/>
		    		</td>
		    	</tr>
	    	[/#if]
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount.pay")}:</th>
	    		<td colspan=3>
	    			 <input class="easyui-numberbox" id="dailyBillPay_totalAmount" name="payTotalAmount" data-options="required:true,editable:false,min:0,precision:2"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td colspan=3>
	    			<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="paymentType" id="paymentType" panelHeight="100px"
			   		data-options="required:true,editable:false,
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'CASH',
						label: '${message("yly.common.charge.paymentType.CASH")}'
					},{
						value: 'CARD',
						label: '${message("yly.common.charge.paymentType.CARD")}'
					},{
						value: 'ADVANCE',
						label: '${message("yly.common.charge.paymentType.ADVANCE")}'
					},{
						value: 'MIXTURE',
						label: '${message("yly.common.charge.paymentType.MIXTURE")}'
					}]" /> 
	    		</td>
	    	</tr>
	    	<tr id="mixturePay" style="display:none">
	    		<th>${message("yly.common.charge.paymentType.CASH")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="totalAmount_cash" name="cashAmount" data-options="min:0,precision:2"/>    
	    		</td>
	    		 <th>${message("yly.common.charge.paymentType.CARD")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="totalAmount_card" name="cardAmount" data-options="min:0,precision:2"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" name="remark" style="width:450px" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    </table>
    </form>