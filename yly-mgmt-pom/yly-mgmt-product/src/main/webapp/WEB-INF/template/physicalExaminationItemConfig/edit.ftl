<form id="editPhysicalExamination_form" method="post">   
	 <input type="hidden" name="elderlyInfoID" id="editPhysicalExamination_elderlyInfoID" value="${physicalExamination.elderlyInfo.id}">
	 <input type="hidden" name="id" id="id" value="${physicalExaminationItemConfig.id}">
   	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="configValue" value="${physicalExaminationItemConfig.configValue}" id="configValue" panelHeight="150px" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.configOrder")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="configOrder" value="${physicalExaminationItemConfig.configOrder}" id="configOrder" panelHeight="150px" data-options="required:true" />
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
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="isEnable" style="width:110px;"/>
	    		</td>
	    	</tr>
	    </table>
</form>



