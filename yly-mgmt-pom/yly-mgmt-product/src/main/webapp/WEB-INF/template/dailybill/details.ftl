<form id="checkinDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${billing.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${billing.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${billing.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${billing.elderlyInfo.nursingLevel.id}" selected="selected">${billing.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${billing.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${billing.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.bed")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="bedAmount"  readonly=true value="${billing.bedAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.charge.record.nurse")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="nurseAmount"  readonly=true value="${billing.nurseAmount}" style="width:75px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.meal")}:</th>
	    		<td>
	    			[#if billing.elderlyInfo.mealFeeMonthlyPayment?? && billing.elderlyInfo.mealFeeMonthlyPayment=="true"]
	    				<input class="easyui-textbox" type="text" name="mealAmount"  readonly=true value="${billing.mealAmount}" style="width:120px;"/>&nbsp;[${billing.elderlyInfo.mealType.configValue}]
	    			[#else]
	    				${message("yly.charge.meal.not.monthly")}
	    			[/#if]
	    		</td>
	    		<th>${message("yly.charge.record.service.money")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="serviceAmount"  readonly=true value="${billing.personalizedAmount}" style="width:75px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.water")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="waterAmount"  readonly=true value="${billing.waterAmount}" style="width:75px;"/>&nbsp;[${message("yly.charge.record.water.count")}:${billing.waterElectricityCharge.waterCount}] 
	    		</td>
	    		<th>${message("yly.charge.record.electricity")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="electricityAmount"  readonly=true value="${billing.electricityAmount}" style="width:75px;"/>&nbsp;[${message("yly.charge.record.electricity.count")}:${billing.waterElectricityCharge.electricityCount}]  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="totalAmount"  readonly=true [#if billing.totalAmount!=0]value="${billing.totalAmount}"[/#if] style="width:75px;"/>
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${billing.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${billing.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    		    <input id="periodStartDate" name="periodStartDate" type="text" value="${billing.periodStartDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		    ─
    			    <input id="periodEndDate" name="periodEndDate" type="text" value="${billing.periodEndDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:120px;">   
						<option value="${billing.chargeStatus}" selected="selected">${message("yly.common.charge.status.${billing.chargeStatus}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${billing.payStaff}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			<input id="payTime" name="payTime" type="text" value="${billing.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	[#if billing.personalizedCharge??]
	    	<tr>
	    		<td colspan=4>
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
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${billing.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>
