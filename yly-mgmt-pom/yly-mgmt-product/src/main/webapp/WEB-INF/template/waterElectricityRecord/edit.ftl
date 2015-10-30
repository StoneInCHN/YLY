<form id="editwaterElectricityRecord_form" method="post">   
		<input value="${waterElectricityRecord.id}" type="hidden" name="id" />
		<input value="${waterElectricityRecord.tenantID}" type="hidden" name="tenantID" />
		<input value="${waterElectricityRecord.room.id}" type="hidden" name="roomId" />
		<table class="table table-striped table-bordered">
	   	  	<table class="table table-striped table-bordered">
			<tr>
				<th>${message("yly.bed.room")}:</th>
				<td>
				    <input class="easyui-textbox" type="text" id="roomNumber" name="roomNumber" value="${waterElectricityRecord.room.roomNumber}" data-options="editable:false" />   
				</td>
			</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="operator" validtype="length[0,6]" value="${waterElectricityRecord.operator}" data-options="required:true" style="width:100px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordStartDate")}:</th>
	    		<td>
	    			<input type="text" class="easyui-datebox"  name="recordStartDate" value="${waterElectricityRecord.recordStartDate}" validtype="length[0,30]" data-options="required:true" style="width:150px;" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordEndDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox"  name="recordEndDate" value="${waterElectricityRecord.recordEndDate}" validtype="length[0,30]" data-options="required:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" value="${waterElectricityRecord.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:280" />
	    		</td>
	    	</tr>
	    	</table>
	    	<fieldset>
	    	<legend>水用量抄表记录</legend>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterCount")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox"  name="waterCount" value="${waterElectricityRecord.waterCount}" validtype="length[0,10]" data-options="required:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterDerate")}:</th>
	    		<td>
	    			  <input class="easyui-numberbox" type="text" name="waterDerate" value="${waterElectricityRecord.waterDerate}" validtype="length[0,10]" data-options="required:true"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	    	<fieldset>
	    	<legend>电用量抄表记录</legend>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityCount")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox"  name="electricityCount" value="${waterElectricityRecord.electricityCount}" validtype="length[0,10]" data-options="required:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityDerate")}:</th>
	    		<td>
	    			  <input class="easyui-numberbox" type="text" name="electricityDerate" value="${waterElectricityRecord.electricityDerate}" validtype="length[0,10]" data-options="required:true"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	    </table>
</form>



