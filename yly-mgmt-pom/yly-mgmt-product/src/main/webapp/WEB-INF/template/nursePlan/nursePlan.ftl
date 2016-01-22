<script type="text/javascript"  src="${base}/resources/modules/nursePlan.js"></script>
<table id="nursePlan-table-list"></table>
<div id="addNursePlan">
	<form id="addNursePlan_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nursePlan.planStartDate")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="planStartDate" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planEndDate")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="planEndDate" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planName")}:</th>
	    		<td>
	    			 <input class="easyui-validatebox"  name="planName" data-options="required:true" validType="minLength[10]"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.planContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="planContent" data-options="required:true,multiline:true,height:100" validType:'maxLength[200]'/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nursePlan.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="remark" data-options="multiline:true,height:100,validType:'maxLength[200]'" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editNursePlan"></div>  




