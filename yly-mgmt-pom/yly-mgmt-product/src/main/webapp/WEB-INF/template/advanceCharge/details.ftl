<form id="advanceDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${advanceCharge.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${advanceCharge.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${advanceCharge.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${advanceCharge.elderlyInfo.nursingLevel.id}" selected="selected">${advanceCharge.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${advanceCharge.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${advanceCharge.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.money")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="advanceAmount"  readonly=true value="${advanceCharge.advanceAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${advanceCharge.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${advanceCharge.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.budgetType")}:</th>
	    		<td>
	    			<select id="budgetType" class="easyui-combobox" name="budgetType" disabled="disabled" style="width:85px;">   
						<option value="${advanceCharge.budgetType}" selected="selected">${message("yly.common.charge.budgetType.${advanceCharge.budgetType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${advanceCharge.operator}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			<input id="payTime" name="payTime" type="text" value="${advanceCharge.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${advanceCharge.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>
