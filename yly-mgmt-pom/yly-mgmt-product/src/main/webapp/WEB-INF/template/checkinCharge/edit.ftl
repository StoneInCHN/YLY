<form id="editCheckinCharge_form" method="post"> 
	    <input type="hidden" name="id" id="billingId" value="${billing.id}">  
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" value="${billing.elderlyInfo.name}" style="width:180px;" data-options="disabled:true"/>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td colspan="4">
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.record.deposit")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="update_checkin_deposit" value="${billing.deposit.depositAmount}" name="deposit.depositAmount" data-options="required:true,min:0,precision:2" />
					    		</td>
					    		<th>${message("yly.remark")}:</th>
					    		<td width="250px">
					    			 <input class="easyui-textbox" name="deposit.remark" value="${billing.deposit.remark}" style="width:200px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  		
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	 </tr>
	    	 <tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input id="update_bedNursePeriodStartDate" value="${billing.bedNurseCharge.periodStartDate}" name="bedNurseCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false"/>
					    		    ──
				    			    <input id="update_bedNursePeriodEndDate" value="${billing.bedNurseCharge.periodEndDate}" name="bedNurseCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" readonly=true/>    
					    			<span class="margin-left-20">${message("yly.charge.billing.day",billDay)}</span>
					    		</td>
					    		
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.bed")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="update_chargein_bedAmount" value="${billing.bedNurseCharge.bedAmount}" name="bedNurseCharge.bedAmount" data-options="required:true,min:0,precision:2,editable:false" />
					    			<span class="margin-left-20">[${bedNurseConfig[0].chargeItem.configValue}]</span><span class="margin-left-10" id="bedPerMonth" value="${bedNurseConfig[0].amountPerMonth}">${message("yly.charge.record.perMonth",bedNurseConfig[0].amountPerMonth)}</span><span class="margin-left-10" id="bedPerDay" value="${bedNurseConfig[0].amountPerDay}">${message("yly.charge.record.perDay",bedNurseConfig[0].amountPerDay)}</span>
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.nurse")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="update_chargein_nurseAmount"  value="${billing.bedNurseCharge.nurseAmount}" name="bedNurseCharge.nurseAmount" data-options="required:true,min:0,precision:2,editable:false" />
					    		     <span class="margin-left-20">[${bedNurseConfig[1].chargeItem.configValue}]</span><span class="margin-left-10" id="nurseLevelPerMonth" value="${bedNurseConfig[1].amountPerMonth}">${message("yly.charge.record.perMonth",bedNurseConfig[1].amountPerMonth)}</span><span class="margin-left-10" id="nurseLevelPerDay" value="${bedNurseConfig[1].amountPerDay}">${message("yly.charge.record.perDay",bedNurseConfig[1].amountPerDay)}</span>
					    		</td>
					    		
	    			  		</tr>
	    			  		
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" name="bedNurseCharge.remark" style="width:400px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td colspan=4>
	    		     <div><input type="checkbox" name="isMonthlyMeal" value="true" id="update_isMonthlyMeal" style="width:20px;"><span>${message("yly.charge.isMonthly.meal")}</span></div>
	    			  <fieldset id="update_monthlyMeal" style="display:none"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input id="update_mealPeriodStartDate" name="mealCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false,disabled:true"/>
					    		    ──
				    			    <input id="update_mealPeriodEndDate" name="mealCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false,disabled:true" readonly=true/>    
					    		</td>
					    		
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.mealCharge.mealType")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="mealTypeId" id="mealType" panelHeight="150px" data-options="editable:false,disabled:true" />   
					    		     <span class="margin-left-20" id="mealTypeVal"></span><span class="margin-left-10" id="mealPerMonth"></span><span class="margin-left-10" id="mealPerDay"></span>
					    		</td>
	    			  		</tr>
	    			  		
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" id="update_mealRemark" name="mealCharge.remark" style="width:400px" validtype="length[0,50]" data-options="disabled:true"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan=4></td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-numberbox" id="update_chargein_totalAmount" name="totalAmount" data-options="min:0,precision:2,editable:false"/>
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
  
<script type="text/javascript">
</script>