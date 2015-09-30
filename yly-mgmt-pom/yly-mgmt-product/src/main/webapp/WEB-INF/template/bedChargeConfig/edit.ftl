<form id="editBedChargeConfig_form" method="post">   
		<input value="${bedChargeConfig.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.bedCharge.bedType")}</th>
	    		<td>
	    			 ${bedChargeConfig.chargeItem.configValue}   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.day")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${bedChargeConfig.amountPerDay}" name="amountPerDay" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.month")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${bedChargeConfig.amountPerMonth}" name="amountPerMonth" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



