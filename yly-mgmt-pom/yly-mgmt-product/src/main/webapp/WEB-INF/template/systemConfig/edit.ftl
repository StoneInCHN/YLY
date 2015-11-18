<form id="editSystemConfig_form" method="post">   
		<input value="${systemConfig.id}" type="hidden" name="id" />
		<input value="${systemConfig.configKey}" type="hidden" name="configKey" />   
		<input value="${systemConfig.tenantID}" type="hidden" name="tenantID" />   
				<table class="table table-striped table-bordered">
				    	<tr>
				    		<th>${message("yly.systemConfig.configValue")}:</th>
				    		<td>
				    			 <input class="easyui-textbox" validtype="length[0,20]" value="${systemConfig.configValue}" data-options="required:true" type="text" name="configValue"  />   
				    		</td>
				    	</tr>
				    	<tr>
				    		<th>${message("yly.systemConfig.isEnabled")}:</th>
				    		<td>
			    			  <select id="isEnabled" class="easyui-combobox" name="isEnabled" style="width:45px;" data-options="panelMaxHeight:55">   
								<option value="true" [#if systemConfig.isEnabled ==true] selected="selected" [/#if]>${message("yly.common.yes")}</option>
								<option value="false" [#if systemConfig.isEnabled ==false] selected="selected" [/#if]>${message("yly.common.no")}</option> 
							  </select>  
	    					</td>
				    	</tr>
			    </table>
</form>



