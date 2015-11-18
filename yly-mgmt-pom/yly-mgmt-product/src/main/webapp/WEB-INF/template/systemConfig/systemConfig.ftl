<script type="text/javascript" src="${base}/resources/modules/systemConfig.js"></script>
<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'west',split:true" style="width:200px;padding-left:20px" title="筛选">
             <ul id="configKey_Tree"></ul>  
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" data-options="fit:true,border:false">
			<table id="systemConfig_table_list"></table>
			<div id="systemConfig_manager_tool">
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="systemConfig_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="systemConfig_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="systemConfig_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>
			<div class="tool-filter"></div>
		</div> 
	</div>
</div>
<div id="editSystemConfig"></div>  
<div id="addSystemConfig">
			<form id="addSystemConfig_form" method="post" class="form-table">
				<input id="addSystemConfig_form_configKey" type="hidden" name="configKeyId" />   
				<table class="table table-striped table-bordered">
				    	<tr>
				    		<th>${message("yly.systemConfig.configValue")}:</th>
				    		<td>
				    			 <input class="easyui-textbox" validtype="length[0,20]" data-options="required:true" type="text" name="configValue"  />   
				    		</td>
				    	</tr>
				    	<tr>
				    		<th>${message("yly.systemConfig.isEnabled")}:</th>
				    		<td>
			    			  <select id="isEnabled" class="easyui-combobox" name="isEnabled" style="width:45px;" data-options="panelMaxHeight:55">   
								<option value="true">${message("yly.common.yes")}</option>
								<option value="false">${message("yly.common.no")}</option> 
							  </select>  
	    					</td>
				    	</tr>
				    	
			    </table>
			</form>
</div> 
