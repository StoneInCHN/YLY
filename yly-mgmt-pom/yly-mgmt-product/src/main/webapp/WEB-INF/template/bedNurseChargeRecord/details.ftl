<form id="bedNurseChargeRecord_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${bedNurseCharge.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${bedNurseCharge.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${bedNurseCharge.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${bedNurseCharge.elderlyInfo.nursingLevel.id}" selected="selected">${bedNurseCharge.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${bedNurseCharge.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${bedNurseCharge.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.record.bed")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="bedAmount"  readonly=true value="${bedNurseCharge.bedAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.charge.record.nurse")}:</th>
	    		<td>
    			     <input class="easyui-textbox" type="text" name="nurseAmount"  readonly=true value="${bedNurseCharge.nurseAmount}" style="width:75px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="totalAmount"  readonly=true value="${bedNurseCharge.totalAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${bedNurseCharge.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${bedNurseCharge.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    		    <input id="periodStartDate" name="periodStartDate" type="text" value="${bedNurseCharge.periodStartDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		    ─
    			    <input id="periodEndDate" name="periodEndDate" type="text" value="${bedNurseCharge.periodEndDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:85px;">   
						<option value="${bedNurseCharge.chargeStatus}" selected="selected">${message("yly.common.charge.status.${bedNurseCharge.chargeStatus}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${bedNurseCharge.operator}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			<input id="payTime" name="payTime" type="text" value="${bedNurseCharge.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${bedNurseCharge.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



