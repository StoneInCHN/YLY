<form id="personalizedChargeRecord_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th width="85px">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoName" readonly=true value="${personalizedCharge.elderlyInfo.name}" style="width:85px;"/>   
	    		</td>
	    		<th width="85px">${message("yly.common.elderly.identifier")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyInfoIdentifier" readonly=true value="${personalizedCharge.elderlyInfo.identifier}" style="width:130px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.bedRoom")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfoBedRoom"  readonly=true value="${personalizedCharge.elderlyInfo.bedLocation}" style="width:180px;"/> 
	    		</td>
	    		<th>${message("yly.common.elderly.nurseLevel")}:</th>
	    		<td>
    			  	<select id="elderlyInfoNurseLevel" class="easyui-combobox" name="elderlyInfoNurseLevel" disabled="disabled" style="width:85px;">   
						<option value="${personalizedCharge.elderlyInfo.nursingLevel.id}" selected="selected">${personalizedCharge.elderlyInfo.nursingLevel.configValue}</option>
				    </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="invoiceNo"  readonly=true value="${personalizedCharge.invoiceNo}" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.billingNo")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="billingNo"  readonly=true value="${personalizedCharge.billingNo}" style="width:150px;"/> 
	    		</td>
	    	</tr>
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
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="totalAmount"  readonly=true value="${personalizedCharge.personalizedAmount}" style="width:75px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
    			  	<select id="paymentType" class="easyui-combobox" name="paymentType" disabled="disabled" style="width:120px;">   
						<option value="${personalizedCharge.paymentType}" selected="selected">${message("yly.common.charge.paymentType.${personalizedCharge.paymentType}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.period")}:</th>
	    		<td>
	    		    <input id="periodStartDate" name="periodStartDate" type="text" value="${personalizedCharge.periodStartDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		    ─
    			    <input id="periodEndDate" name="periodEndDate" type="text" value="${personalizedCharge.periodEndDate}" class="easyui-datebox" style="width:100px;" disabled=true/>
	    		</td>
	    		<th>${message("yly.common.charge.status")}:</th>
	    		<td>
	    			<select id="chargeStatus" class="easyui-combobox" name="chargeStatus" disabled="disabled" style="width:85px;">   
						<option value="${personalizedCharge.chargeStatus}" selected="selected">${message("yly.common.charge.status.${personalizedCharge.chargeStatus}")}</option>
				    </select>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.common.charge.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator"  readonly=true value="${personalizedCharge.operator}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.common.charge.payTime")}:</th>
	    		<td>
	    			  <input id="payTime" name="payTime" type="text" value="${personalizedCharge.payTime}" class="easyui-datetimebox" style="width:150px;" disabled=true /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=5 type="text" name="remark" readonly=true>${personalizedCharge.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



