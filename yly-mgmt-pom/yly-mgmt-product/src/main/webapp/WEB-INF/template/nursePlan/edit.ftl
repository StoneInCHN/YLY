<form id="editNursePlan_form" method="post">   
	<input value="${nursePlan.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nursePlan.planStartDate")}:</th>
	    		<td>
	    			 <input class="easyui-datebox" value="${nursePlan.planStartDate}"  name="planStartDate" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planEndDate")}:</th>
	    		<td>
	    			 <input class="easyui-datebox" value="${nursePlan.planEndDate}"  name="planEndDate" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nursePlan.planName}"  name="planName" data-options="required:true" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nursePlan.planContent}"  name="planContent" data-options="required:true,multiline:true,height:100" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${nursePlan.remark}"  name="remark" data-options="required:true,multiline:true,height:100" />
	    		</td>
	    	</tr>
	    </table>
</form>



