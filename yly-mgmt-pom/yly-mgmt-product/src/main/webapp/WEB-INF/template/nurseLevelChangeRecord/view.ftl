<form id="viewNurseChange_form" method="post" > 
	   <fieldset> 
	   <legend>护理变更前信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseLevelChangeRecord.elderlyInfo.name}"   style="width:150px;" data-options="required:true,editable:false" disabled=true/>
	    		</td>
	    		<th>变更前等级: </th>
	    		<td>
					<input class="easyui-combobox nurseChange-nurseLevel" value="${nurseLevelChangeRecord.oldNurseLevel.configValue}" style="width:120px;" data-options="editable:false" disabled=true>
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理变更后信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseLevelChangeRecord.elderlyInfo.name}"  style="width:150px;" data-options="required:true,editable:false" disabled=true/>
	    		</td>
	    		<th>变更后等级: </th>
	    		<td>
					<input class="easyui-combobox nurseChange-nurseLevel"  value="${nurseLevelChangeRecord.newNurseLevel.configValue}" style="width:120px;" data-options="required:true,editable:false" disabled=true>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   value="${nurseLevelChangeRecord.remark}" data-options="multiline:true,height:100,width:460,required:true" disabled=true/>
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>