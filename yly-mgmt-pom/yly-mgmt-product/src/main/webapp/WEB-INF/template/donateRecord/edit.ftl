<form id="editDonateRecord_form" method="post">   
		 <input value="${donateRecord.id}" type="hidden" name="id" />
	  <table class="table table-striped" border="0">
	    	<tr>
	    		<th>捐赠人姓名:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorName" value="${donateRecord.donatorName}" data-options="required:true" />   
	    		</td>
	    	
	    		<th>性别:</th>
	    		<td>
	    			<select id="donatorGender" class="easyui-combobox"  name="donatorGender" style="width:60px;">   
						<option value="MALE" [#if donateRecord.donatorGender == 'MALE'] selected = "selected"[/#if]>男</option>
						<option value="FEMALE" [#if donateRecord.donatorGender == 'FEMALE'] selected = "selected"[/#if]>女</option> 
				  </select>     
	    		</td>
	    	    <th>捐赠人电话:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorPhone"  value="${donateRecord.donatorPhone}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>时间:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="donateTime" name="donateTime" value="${donateRecord.donateTime}" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>捐赠人地址:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" type="text" name="donatorAddress"  value="${donateRecord.donatorAddress}" style="width:100%"/>   
	    		</td>
	    	</tr>
	    	<tr>
    			<th>捐赠描述:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="donateDescription" value="${donateRecord.donateDescription}" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>备注:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="remark" value="${donateRecord.remark}" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



