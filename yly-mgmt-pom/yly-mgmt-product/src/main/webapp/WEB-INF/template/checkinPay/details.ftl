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
	    		<th>${message("yly.charge.deposit.amount")}:</th>
	    		<td>
	    			${billing.depositAmount}
	    		</td>
	    		<th>${message("yly.charge.record.bed")}:</th>
	    		<td>
	    			${billing.bedAmount}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.nurse")}:</th>
	    		<td>
	    			  ${billing.nurseAmount} 
	    		</td>
	    		<th>${message("yly.charge.record.meal")}:</th>
	    		<td>
	    			[#if billing.elderlyInfo.mealFeeMonthlyPayment?? && billing.elderlyInfo.mealFeeMonthlyPayment=="true"]
	    				${billing.mealAmount}&nbsp;&nbsp;[${billing.elderlyInfo.mealType.configValue}]
	    			[#else]
	    				${message("yly.charge.meal.not.monthly")}
	    			[/#if]
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
    			   	${billing.totalAmount}
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	${message("yly.common.charge.paymentType.${billing.paymentType}")}
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			${message("yly.common.charge.status.${billing.chargeStatus}")}
	    		</td>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			${billing.operator}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			${billing.payTime}
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
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  ${billing.remark} 
	    		</td>
	    	</tr>
	    </table>
</form>
