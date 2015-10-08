<script src="${base}/resources/modules/waterElectricityChargeConfig.js"></script>

<table id="waterElectricityChargeConfig_table_list"></table>
<div id="waterElectricityChargeConfig_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="waterElectricityChargeConfig_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="waterElectricityChargeConfig_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="waterElectricityChargeConfig_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
	
<div id="addWaterElectricityChargeConfig">
	<form id="addWaterElectricityChargeConfig_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.waterElectricityCharge.config.water")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="waterUnitPrice" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityCharge.config.electricity")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="electricityUnitPrice" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  
<div id="editWaterElectricityChargeConfig"></div>  




