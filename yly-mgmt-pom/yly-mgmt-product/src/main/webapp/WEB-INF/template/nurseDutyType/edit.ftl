<form id="editNurseDutyType_form" method="post">   
	<input value="${nurseDutyType.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyStartTime")}:</th>
	    		<td>
	    			 <input class="easyui-timespinner" value="${nurseDutyType.dutyStartTime}"  name="dutyStartTime" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyEndTime")}:</th>
	    		<td>
	    			 <input class="easyui-timespinner" value="${nurseDutyType.dutyEndTime}"  name="dutyEndTime" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseDutyType.dutyName}"  name="dutyName" data-options="required:true" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.order")}:</th>
	    		<td>
	    			 <input class="easyui-numberspinner" value="${nurseDutyType.order}"  name="orderIndex" data-options="min:1,required:true" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${nurseDutyType.remark}"  name="remark" data-options="required:true,multiline:true,height:100" />
	    		</td>
	    	</tr>
	    </table>
</form>



