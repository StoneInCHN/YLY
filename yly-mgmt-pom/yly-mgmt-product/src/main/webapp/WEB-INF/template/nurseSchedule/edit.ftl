<form id="editNurseSchedule_form" method="post">   
	<input value="${nurseSchedule.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    		<tr>
	    		<th>${message("yly.nurseSchedule.dutyStartTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="dutyStartTime" value="${nurseSchedule.dutyStartTime}" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyEndTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="dutyEndTime" value="${nurseSchedule.dutyEndTime}" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyType")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="editNurseSchedule_form_dutyType" data-value="${nurseSchedule.dutyType.id}"  name="dutyTypeId" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.nurseSchedule.dutyStaff")}:</th>
	    		<td>
	    			 <input type="hidden" id="editNurseSchedule_form_dutyStaffID" value="${nurseSchedule.tenantUser.id}" name="dutyStaffId"/>
	    			 <input class="easyui-textbox"  id="editNurseSchedule_form_dutyStaff" value="${nurseSchedule.dutyStaff}" name="dutyStaff"  readonly="readonly"  data-options="required:true" style="width:130px;"/>  
	    			 <a href="#" id="editNurseSchedule_form_dutyStaff_btn" ></a> 
	    		</td>
	    	</tr>
	    </table>
</form>



