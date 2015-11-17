<form id="physicalExaminationDetail_form" method="post">   
		 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="configValue" disabled="disabled" value="${physicalExaminationItemConfig.configValue}" id="configValue" panelHeight="150px" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.configOrder")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="configOrder" disabled="disabled" value="${physicalExaminationItemConfig.configOrder}" id="configOrder" panelHeight="150px" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.isEnable")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("yly.common.enable")}'
				      [#if physicalExaminationItemConfig.isEnable]
				      ,selected:true
				      [/#if]
				     },{
				      label: 'false',
				      value: '${message("yly.common.disable")}'
				      [#if !physicalExaminationItemConfig.isEnable]
				      ,selected:true
				      [/#if]
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100" disabled="disabled" name="isEnable" style="width:110px;"/>
	    		</td>
	    	</tr>
	    </table>
</form>



