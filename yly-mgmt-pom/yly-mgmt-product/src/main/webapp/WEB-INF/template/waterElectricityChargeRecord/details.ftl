<form id="waterElectricityChargeRecord_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${waterElectricityCharge.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${waterElectricityCharge.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${waterElectricityCharge.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${waterElectricityCharge.elderlyInfo.nursingLevel.id}" selected="selected">${waterElectricityCharge.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${waterElectricityCharge.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${waterElectricityCharge.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.record.water.electricity.details")}</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th width="50px"></th>
		    			  		<th>${message("yly.charge.record.water.electricity.count")}</th>
		    			  		<th>${message("yly.common.charge.unitPrice")}</th>
		    			  		<th>${message("yly.common.charge.money")}</th>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.common.water")}:</th>
	    			  			<td>${waterElectricityCharge.waterCount}</td>
	    			  			<td>${waterElectricityChargeConfig.waterUnitPrice}</td>
	    			  			<td>${waterElectricityCharge.waterAmount}</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.common.electricity")}:</th>
	    			  			<td>${waterElectricityCharge.electricityCount}</td>
	    			  			<td>${waterElectricityChargeConfig.electricityUnitPrice}</td>
	    			  			<td>${waterElectricityCharge.electricityAmount}</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="totalAmount"  readonly=true value="${waterElectricityCharge.totalAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${waterElectricityCharge.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${waterElectricityCharge.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    		    <input id="periodStartDate" name="periodStartDate" type="text" value="${waterElectricityCharge.periodStartDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		    ─
    			    <input id="periodEndDate" name="periodEndDate" type="text" value="${waterElectricityCharge.periodEndDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:85px;">   
						<option value="${waterElectricityCharge.chargeStatus}" selected="selected">${message("yly.common.charge.status.${waterElectricityCharge.chargeStatus}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${waterElectricityCharge.operator}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			  <input id="payTime" name="payTime" type="text" value="${waterElectricityCharge.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${waterElectricityCharge.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



