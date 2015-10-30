<form id="editConsultation_form" method="post">   
		  <table class="table table-striped table-bordered">
	   	  	<table class="table table-striped table-bordered">
			<tr>
				<th>${message("yly.bed.room")}:</th>
				<td>
				    <input class="easyui-textbox" type="text" id="roomNumber" name="roomId" value="${waterElectricityRecord.room.roomNumber}" data-options="editable:false" />   
				</td>
			</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="operator" value="${waterElectricityRecord.operator}" data-options="editable:false" style="width:100px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordStartDate")}:</th>
	    		<td>
	    			<input type="text" class="easyui-datebox"  name="recordStartDate" value="${waterElectricityRecord.recordStartDate}" data-options="disabled:true" style="width:150px;" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordEndDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox"  name="recordEndDate" value="${waterElectricityRecord.recordEndDate}" data-options="disabled:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" value="${waterElectricityRecord.remark}" data-options="editable:false,multiline:true,height:90,width:280" />
	    		</td>
	    	</tr>
	    	</table>
	    	<fieldset>
	    	<legend>水用量抄表记录</legend>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterCount")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox"  name="waterCount" value="${waterElectricityRecord.waterCount}" data-options="editable:false" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterDerate")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="waterDerate" value="${waterElectricityRecord.waterDerate}" data-options="editable:false"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterActual")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="electricityActual" value="${waterElectricityRecord.waterActual}" data-options="editable:false"  style="width:150px;"/> 
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
	    			  <input type="text" class="easyui-numberbox"  name="electricityCount" value="${waterElectricityRecord.electricityCount}" data-options="editable:false" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityDerate")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="electricityDerate" value="${waterElectricityRecord.electricityDerate}" data-options="editable:false"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityActual")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="electricityActual" value="${waterElectricityRecord.electricityActual}" data-options="editable:false"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	    </table>
</form>



