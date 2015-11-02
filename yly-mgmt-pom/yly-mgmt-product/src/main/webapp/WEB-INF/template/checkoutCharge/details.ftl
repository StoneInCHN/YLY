<form id="checkoutDetail_form" method="post">   
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
	    		<th>${message("yly.charge.deposit.amount")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="depositAmount"  readonly=true [#if billing.depositAmount<0]value="${message("yly.checkout.refund",-billing.depositAmount)}"[#else]value="${message("yly.checkout.additional",billing.depositAmount)}"[/#if] style="width:100px;"/> 
	    		</td>
	    		<th>${message("yly.charge.record.bed")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="bedAmount"  readonly=true [#if billing.bedAmount<0]value="${message("yly.checkout.refund",-billing.bedAmount)}"[#else]value="${message("yly.checkout.additional",billing.bedAmount)}"[/#if] style="width:100px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.nurse")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="nurseAmount"  readonly=true [#if billing.nurseAmount<0]value="${message("yly.checkout.refund",-billing.nurseAmount)}"[#else]value="${message("yly.checkout.additional",billing.nurseAmount)}"[/#if] style="width:100px;"/> 
	    		</td>
	    		<th>${message("yly.charge.record.meal")}:</th>
	    		<td>
	    			[#if billing.mealCharge??]
	    				<input class="easyui-textbox" type="text" name="mealAmount"  readonly=true [#if billing.mealAmount<0]value="${message("yly.checkout.refund",-billing.mealAmount)}"[#else]value="${message("yly.checkout.additional",billing.mealAmount)}"[/#if] style="width:100px;"/>
	    			[#else]
	    				${message("yly.charge.meal.not.monthly")}
	    			[/#if]
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="totalAmount"  readonly=true [#if billing.totalAmount<0]value="${message("yly.checkout.refund",-billing.totalAmount)}"[#else]value="${message("yly.checkout.additional",billing.totalAmount)}"[/#if] style="width:100px;"/>
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${billing.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${billing.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:85px;">   
						<option value="${billing.chargeStatus}" selected="selected">${message("yly.common.charge.status.${billing.chargeStatus}")}</option>
				    </select>
	    		</td>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${billing.operator}" style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			<input id="payTime" name="payTime" type="text" value="${billing.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${billing.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>
