<script src="${base}/resources/modules/mealChargeConfig.js"></script>

<table id="mealChargeConfig_table_list"></table>
<div id="mealChargeConfig_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="mealChargeConfig_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="mealChargeConfig_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="mealChargeConfig_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
	
<div id="addMealChargeConfig">
	<form id="addMealChargeConfig_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.mealCharge.mealType")}</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="chargeItemId" id="mealType" data-options="required:true,editable:false" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.day")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="amountPerDay" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.month")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="amountPerMonth" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  
<div id="editMealChargeConfig"></div>  




