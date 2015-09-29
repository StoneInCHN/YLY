<form id="editPersonalizedChargeConfig_form" method="post">   
		<input value="${personalizedChargeConfig.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	        <tr>
	    		<th>${message("yly.personalizedCharge.chargeItem")}</th>
	    		<td>
	    			 ${personalizedChargeConfig.chargeItem} 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.times")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${personalizedChargeConfig.amountPerTime}" name="amountPerTime" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



