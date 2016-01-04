<form id="addCheckinPay_form" method="post"> 
	    <table class="table table-striped">
	    	<input type="hidden" name="billingId" value="${billing.id}">
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
	    	<tr><td colspan=4><font color="red">${message("yly.checkin.changeBill.prompt")}</font></td></tr>
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			    [#if isDepositSupp?? && isDepositSupp == "true"]
	    			    	<legend><font color="red">${message("yly.charge.record.deposit")}:</font></legend>
	    			    [#else]
	    			    	<legend>${message("yly.charge.record.deposit")}:</legend>
	    			    [/#if]
	    			  	
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" value="${billing.depositAmount}" data-options="disabled:true,min:0,precision:2" />
					    		</td>
					    		<th>${message("yly.remark")}:</th>
					    		<td width="250px">
					    			 <input class="easyui-textbox" value="${billing.deposit.remark}" style="width:200px" data-options="disabled:true"/> 
					    		</td>
	    			  		</tr>
	    			  		
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	 </tr>
	    	 <tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			    [#if isBedNurseSupp?? && isBedNurseSupp == "true"]
	    			  		<legend><font color="red">${message("yly.charge.bedNurse.record")}:</font></legend>
	    			  	[#else]
	    			  		<legend>${message("yly.charge.bedNurse.record")}:</legend>
	    			  	[/#if]
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
					    			 <input class="easyui-numberbox" value="${billing.bedAmount}" data-options="disabled:true,min:0,precision:2" />
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.nurse")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" value="${billing.nurseAmount}" data-options="disabled:true,min:0,precision:2" />
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
	    			  	[#if isMealSupp?? && isMealSupp == "true"]
	    			  		<legend><font color="red">${message("yly.charge.meal.reocrd")}:</font></legend>
	    			  	[#else]
	    			  		<legend>${message("yly.charge.meal.reocrd")}:</legend>
	    			  	[/#if]
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
					    			 <input class="easyui-numberbox" value="${billing.mealAmount}" data-options="min:0,precision:2,disabled:true" />
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
	    			 <input class="easyui-numberbox" id="chargeinPay_totalAmount" name="payTotalAmount" value="${billing.totalAmount}" data-options="editable:false,min:0,precision:2"/>
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