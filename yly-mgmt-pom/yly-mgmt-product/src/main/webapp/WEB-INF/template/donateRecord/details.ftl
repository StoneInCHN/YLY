<form id="donateRecordDetail_form" method="post">   
		 <input value="${donateRecord.id}" type="hidden" name="id" />
	  <table class="table table-striped" border="0">
	    	<tr>
	    		<th>${message("yly.donateRecord.donatorName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorName" value="${donateRecord.donatorName}" data-options="required:true" />   
	    		</td>
	    	
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    			<select id="donatorGender" class="easyui-combobox"  name="donatorGender" style="width:60px;">   
						<option value="MALE" [#if donateRecord.donatorGender == 'MALE'] selected = "selected"[/#if]>男</option>
						<option value="FEMALE" [#if donateRecord.donatorGender == 'FEMALE'] selected = "selected"[/#if]>女</option> 
				  </select>     
	    		</td>
	    	    <th>${message("yly.donateRecord.donatorPhone")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorPhone"  value="${donateRecord.donatorPhone}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.donateRecord.donateTime")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="donateTime" name="donateTime" value="${donateRecord.donateTime}" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>${message("yly.donateRecord.donatorAddress")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" type="text" name="donatorAddress"  value="${donateRecord.donatorAddress}" style="width:100%"/>   
	    		</td>
	    	</tr>
	    	<tr> 
    			<th>${message("yly.donateRecord.donateDescription")}:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="donateDescription" value="${donateRecord.donateDescription}" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>${message("yly.remark")}:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="remark" value="${donateRecord.remark}" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



