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
	    			 <input class="easyui-textbox" value="${message("yly.common.please.select")}" name="chargeItemId" id="mealType" panelHeight="150px" data-options="required:true,editable:false" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.day")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="amountPerDay" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.month")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="amountPerMonth" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  
<div id="editMealChargeConfig"></div>  




