<script type="text/javascript"  src="${base}/resources/modules/nurseSchedule.js"></script>
<table id="nurseSchedule-table-list"></table>
<div id="addNurseSchedule">
	<form id="addNurseSchedule_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyStartTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="dutyStartTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyEndTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="dutyEndTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyType")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="addNurseSchedule_form_dutyType"  name="dutyTypeId" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyStaff")}:</th>
	    		<td>
	    			 <input type="hidden" id="addNurseSchedule_form_dutyStaffID" name="dutyStaffId"/>
	    			 <input class="easyui-textbox" type="text" id="addNurseSchedule_form_dutyStaff"  readonly="readonly"  data-options="required:true" style="width:130px;"/>  
	    			 <a href="#" id="addNurseSchedule_form_dutyStaff_btn" ></a>  
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editNurseSchedule"></div>  




