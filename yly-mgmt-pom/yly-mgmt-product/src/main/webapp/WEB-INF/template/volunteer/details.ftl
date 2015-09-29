<form id="editConsultation_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.volunteer.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="volunteerName" value="${volunteer.volunteerName}" validtype="length[0,15]" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.type")}:</th>
	    		<td>
	    		  <select id="gender" class="easyui-combobox" name="volunteerType" style="width:85px;">   
    			  	<option value="PERSONAL">${message("yly.volunteer.personal")}</option>
					<option value="ORGANIZATION">${message("yly.volunteer.organization")}</option>  
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.idCard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="idcard" value="${volunteer.idcard}" validtype="length[0,18]" data-options="required:true" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.email")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="email" value="${volunteer.email}" validtype="email" style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.address")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="address" value="${volunteer.address}" validtype="length[0,100]" style="width:85px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.zipCode")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="zipCode" value="${volunteer.zipCode}" validtype="length[0,50]" style="width:85px;"/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="telephone" value="${volunteer.telephone}" validtype="length[0,30]" style="width:85px;"/>    
	    		</td>
	    		
	    		<th>${message("yly.mobile")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="mobile" value="${volunteer.mobile}" validtype="mobile" data-options="required:true"  style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" />
	    		</td>
	    	</tr>
	    </table>
</form>



