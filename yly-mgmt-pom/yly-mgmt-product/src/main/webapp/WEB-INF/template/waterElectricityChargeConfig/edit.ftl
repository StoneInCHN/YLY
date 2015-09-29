<form id="editWaterElectricityChargeConfig_form" method="post">   
		<input value="${waterElectricityChargeConfig.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	        <tr>
	    		<th>${message("yly.waterElectricityCharge.config.water")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${waterElectricityChargeConfig.waterUnitPrice}" name="waterUnitPrice" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityCharge.config.electricity")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${waterElectricityChargeConfig.electricityUnitPrice}" name="electricityUnitPrice" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



