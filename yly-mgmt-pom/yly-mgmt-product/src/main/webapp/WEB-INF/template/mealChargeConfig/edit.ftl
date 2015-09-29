<form id="editMealChargeConfig_form" method="post">   
		<input value="${mealChargeConfig.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.mealCharge.mealType")}</th>
	    		<td>
	    			 ${mealChargeConfig.chargeItem.configValue}   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.day")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${mealChargeConfig.amountPerDay}" type="text" name="amountPerDay" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.month")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${mealChargeConfig.amountPerMonth}" type="text" name="amountPerMonth" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



