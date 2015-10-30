<form id="editvolunteer_form" method="post">   
		<input value="${volunteer.id}" type="hidden" name="id" />
		<input value="${volunteer.tenantID}" type="hidden" name="tenantID" />
		
		 <table class="table table-striped table-bordered">
			  <table class="table table-striped table-bordered">
		    	<tr>
		    		<th>${message("yly.volunteer.name")}:</th>
		    		<td>
		    			 <input class="easyui-textbox" type="text" name="volunteerName" value="${volunteer.volunteerName}" validtype="length[0,6]" data-options="required:true" style="width:100px;"/>   
		    		</td>
		    		<th>${message("yly.idCard")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="idcard" value="${volunteer.idcard}" validtype="idcard" style="width:150px;"/> 
		    		</td>
		    		
		    	</tr>
		    	<tr>
		    		<th>${message("yly.type")}:</th>
		    		<td>
		    		  <select id="gender" class="easyui-combobox" name="volunteerType" editable="false" data-options="required:true" style="width:85px;">   
	    			  	<option value="PERSONAL" [#if volunteer.volunteerType =="PERSONAL"] selected="selected" [/#if]>${message("yly.volunteer.personal")}</option>
						<option value="ORGANIZATION" [#if volunteer.volunteerType =="ORGANIZATION"] selected="selected" [/#if]>${message("yly.volunteer.organization")}</option>  
					  </select>  
		    		</td>
		    		<th>${message("yly.email")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="email" value="${volunteer.email}" validtype="email" style="width:150px;"/> 
		    		</td>
		    	</tr>
		    	</table>
		    	<table class="table table-striped table-bordered">
		    	<tr>
		    		<th>${message("yly.volunteer.activityTime")}:</th>
		    		<td>
		    			  <input type="text" class="easyui-datebox" name="activityTime" editable="false" value="${volunteer.activityTime}" required="required" /> 
		    		</td>
	    		</tr>
		    	<tr>
		    		<th>${message("yly.mobile")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="mobile" value="${volunteer.mobile}" validtype="mobile" data-options="required:true"  style="width:150px;"/> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.phoneNumber")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="telephone" value="${volunteer.telephone}" validtype="length[0,30]" style="width:150px;"/>    
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.zipCode")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="zipCode" value="${volunteer.zipCode}" validtype="length[0,50]" style="width:100px;"/>  
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>${message("yly.address")}:</th>
		    		<td>
		    			  <input class="easyui-textbox" type="text" name="address" value="${volunteer.address}" validtype="length[0,100]" style="width:280px;"/> 
		    		</td>
		    	</tr>
		    	
		    	<tr>
		    		<th>${message("yly.remark")}:</th>
		    		<td>
		    			  <input type="text" class="easyui-textbox" name="remark" value="${volunteer.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:280" />
		    		</td>
		    	</tr>
		    </table>
	  </table>
</form>



