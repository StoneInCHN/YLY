<form id="mealChargeRecord_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${mealCharge.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${mealCharge.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${mealCharge.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${mealCharge.elderlyInfo.nursingLevel.id}" selected="selected">${mealCharge.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${mealCharge.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${mealCharge.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.meal.type")}:</th>
	    		<td>
	    			 <select id="elderlyInfoMealType" class="easyui-combobox" name="elderlyInfoMealType" disabled="disabled" style="width:85px;">   
						<option value="${mealCharge.elderlyInfo.mealType.id}" selected="selected">${mealCharge.elderlyInfo.mealType.configValue}</option>
				    </select> 
	    		</td>
	    		<th>${message("yly.charge.record.meal")}:</th>
	    		<td>
    			     <input class="easyui-textbox" type="text" name="mealAmount"  readonly=true value="${mealCharge.mealAmount}" style="width:75px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td colspan=3>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${mealCharge.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${mealCharge.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    		    <input id="periodStartDate" name="periodStartDate" type="text" value="${mealCharge.periodStartDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		    ─
    			    <input id="periodEndDate" name="periodEndDate" type="text" value="${mealCharge.periodEndDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:85px;">   
						<option value="${mealCharge.chargeStatus}" selected="selected">${message("yly.common.charge.status.${mealCharge.chargeStatus}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${mealCharge.operator}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			  <input id="payTime" name="payTime" type="text" value="${mealCharge.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${mealCharge.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



