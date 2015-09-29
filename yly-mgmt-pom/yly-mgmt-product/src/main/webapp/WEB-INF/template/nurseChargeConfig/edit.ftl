<form id="editNurseChargeConfig_form" method="post">   
		<input value="${nurseChargeConfig.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nurseCharge.nurseLevel")}</th>
	    		<td>
	    			 ${nurseChargeConfig.chargeItem.configValue}   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.day")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${nurseChargeConfig.amountPerDay}" name="amountPerDay" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.month")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${nurseChargeConfig.amountPerMonth}" name="amountPerMonth" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



