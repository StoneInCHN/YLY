<form id="editConsultation_form" method="post">   
		  <table class="table table-striped table-bordered">
			  <table class="table table-striped table-bordered">
		    	<tr>
		    		<th>${message("yly.volunteer.name")}:</th>
		    		<td>
		    			 <input class="easyui-textbox" type="text" name="volunteerName" value="${volunteer.volunteerName}" data-options="disabled:true" style="width:100px;"/>   
		    		</td>
		    		<th>${message("yly.idCard")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="idcard" value="${volunteer.idcard}" data-options="disabled:true" style="width:150px;"/> 
		    		</td>
		    		
		    	</tr>
		    	<tr>
		    		<th>${message("yly.type")}:</th>
		    		<td>
		    		  <select id="gender" class="easyui-combobox" name="volunteerType" data-options="disabled:true" style="width:85px;">   
	    			  	<option value="PERSONAL" [#if volunteer.volunteerType =="PERSONAL"] selected="selected" [/#if]>${message("yly.volunteer.personal")}</option>
						<option value="ORGANIZATION" [#if volunteer.volunteerType =="ORGANIZATION"] selected="selected" [/#if]>${message("yly.volunteer.organization")}</option>  
					  </select>  
		    		</td>
		    		<th>${message("yly.email")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="email" value="${volunteer.email}" data-options="disabled:true" style="width:150px;"/> 
		    		</td>
		    	</tr>
		    	</table>
		    	<table class="table table-striped table-bordered">
		    	<tr>
		    		<th>${message("yly.volunteer.activityTime")}:</th>
		    		<td>
		    			  <input type="text" class="easyui-datebox"  name="activityTime" value="${volunteer.activityTime}" data-options="disabled:true" style="width:150px;" /> 
		    		</td>
	    		</tr>
		    	<tr>
		    		<th>${message("yly.mobile")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="mobile" value="${volunteer.mobile}" data-options="disabled:true" data-options="required:true"  style="width:150px;"/> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.phoneNumber")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="telephone" value="${volunteer.telephone}" data-options="disabled:true" style="width:150px;"/>    
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.zipCode")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="zipCode" value="${volunteer.zipCode}" data-options="disabled:true" style="width:100px;"/>  
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.address")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="address" value="${volunteer.address}" data-options="disabled:true" style="width:280px;"/> 
		    		</td>
		    	</tr>
		    	
		    	<tr>
		    		<th>${message("yly.remark")}:</th>
		    		<td>
		    			  <input type="text" class="easyui-textbox" name="remark" value="${volunteer.remark}" data-options="disabled:true,multiline:true,height:90,width:280" />
		    		</td>
		    	</tr>
		    </table>
	  </table>
</form>



