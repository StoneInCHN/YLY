<script type="text/javascript"  src="${base}/resources/modules/building.js"></script>
<table id="building-table-list"></table>
<div id="building_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="building_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"  plain=true onclick="building_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  onclick="building_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addBulid">
	<form id="addBulid_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.building.buildingName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="buildingName" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.building.description")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="description" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editBuild"></div>  




