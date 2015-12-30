<script type="text/javascript"  src="${base}/resources/modules/nurseDutyType.js"></script>
<table id="nurseDutyType-table-list"></table>
<div id="addNurseDutyType">
	<form id="addNurseDutyType_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyStartTime")}:</th>
	    		<td>
	    			 <input class="easyui-timespinner"  name="dutyStartTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyEndTime")}:</th>
	    		<td>
	    			 <input class="easyui-timespinner"  name="dutyEndTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.dutyName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="dutyName" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.order")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="orderIndex" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseDutyType.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="remark" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editNurseDutyType"></div>  




