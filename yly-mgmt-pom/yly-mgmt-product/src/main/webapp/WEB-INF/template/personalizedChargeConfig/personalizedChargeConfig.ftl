<script src="${base}/resources/modules/personalizedChargeConfig.js"></script>

<table id="personalizedChargeConfig_table_list"></table>
<div id="personalizedChargeConfig_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="personalizedChargeConfig_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="personalizedChargeConfig_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="personalizedChargeConfig_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
	
<div id="addPersonalizedChargeConfig">
	<form id="addPersonalizedChargeConfig_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedCharge.chargeItem")}</th>
	    		<td>
	    			 <input class="easyui-textbox" name="chargeItem" data-options="required:true" validtype="length[0,15]"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.charge.times")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="amountPerTime" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  
<div id="editPersonalizedChargeConfig"></div>  




