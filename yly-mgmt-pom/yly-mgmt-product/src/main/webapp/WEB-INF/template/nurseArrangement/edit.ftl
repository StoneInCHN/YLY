	<form id="editNurseArrangement_form" method="post"> 
		<input type="hidden" name="id" value="${nurseArrangement.id}">  
		<input type="hidden" name="elderlyInfo.id" value="${nurseArrangement.elderlyInfo.id}" id="editNurseArrangement_elderlyInfoID">  
	    <input type="hidden" name="nurseAssistant.id" value="${nurseArrangement.nurseAssistant.id}" id="editNurseArrangement_nurseAssistantID">  
	   <fieldset> 
	   <legend>基本信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangement.elderlyInfo.name}"  id="editNurseArrangement_elderlyName" style="width:150px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchElderlyInfo('edit_NurseArrangement')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangement.nurseAssistant.realName}" id="editNurseArrangement_nurseAssistantName"  style="width:150px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchTenantUser('edit_NurseArrangement')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>老人床位: </th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseArrangement.bedLocation}"  id="editNurseArrangement_bedLocation" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理级别: </th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseArrangement.nursingLevel}"  id="editNurseArrangement_nursingLevel" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   value="${nurseArrangement.nurseName}"  name="nurseName" style="width:400px;" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangement.nurseStartDate}" id="editNurseArrangement_nurseStartDate" name="nurseStartDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'addNurseArrangement_nurseEndDate\')}'});" />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangement.nurseEndDate}" id="editNurseArrangement_nurseEndDate" name="nurseEndDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNurseArrangement_nurseStartDate\')}'});"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox" value="${nurseArrangement.remark}"  name="remark" data-options="multiline:true,height:100,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>