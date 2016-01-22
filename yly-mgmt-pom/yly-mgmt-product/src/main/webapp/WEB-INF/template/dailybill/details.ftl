<form id="checkinDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			${billing.elderlyInfo.name}
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  ${billing.elderlyInfo.identifier} 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 ${billing.elderlyInfo.bedLocation} 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	${billing.elderlyInfo.nursingLevel.configValue}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			${billing.invoiceNo}
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			${billing.billingNo}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.bed")}:</th>
	    		<td>
	    			${billing.bedAmount}
	    		</td>
	    		<th>${message("yly.charge.record.nurse")}:</th>
	    		<td>
	    			 ${billing.nurseAmount} 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.meal")}:</th>
	    		<td>
	    			[#if billing.elderlyInfo.mealFeeMonthlyPayment?? && billing.elderlyInfo.mealFeeMonthlyPayment=="true"]
	    				${billing.mealAmount}&nbsp;&nbsp;[${billing.elderlyInfo.mealType.configValue}]
	    			[#else]
	    				${message("yly.charge.meal.not.monthly")}
	    			[/#if]
	    		</td>
	    		<th>${message("yly.charge.record.service.money")}:</th>
	    		<td>
	    			${billing.personalizedAmount}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.water")}:</th>
	    		<td>
	    			${billing.waterAmount}
	    		</td>
	    		<th>${message("yly.charge.record.electricity")}:</th>
	    		<td>
	    			${billing.electricityAmount}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
	    			[#if billing.totalAmount!=0]${billing.totalAmount}[/#if]
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			    ${message("yly.common.charge.paymentType.${billing.paymentType}")}
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    			${billing.periodStartDate?string("yyyy-MM-dd")} ─ ${billing.periodEndDate?string("yyyy-MM-dd")}
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			${message("yly.common.charge.status.${billing.chargeStatus}")}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			${billing.payStaff}
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			${billing.payTime}
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
	    			  ${billing.remark} 
	    		</td>
	    	</tr>
	    </table>
</form>
