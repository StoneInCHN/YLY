<form id="editBookingRegistration_form" method="post">   
		<input value="${bookingRegistration.id}" type="hidden" name="id" />
		<input value="${bookingRegistration.tenantID}" type="hidden" name="tenantID" />
		    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.bookingRegistration.peopleWhoBooked")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="peopleWhoBooked" value="${bookingRegistration.peopleWhoBooked}" data-options="required:true" validtype="length[0,15]" style="width:85px;"/>  
	    		</td>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="elderlyName" value="${bookingRegistration.elderlyName}" data-options="required:true" validtype="length[0,15]" style="width:85px;"/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" value="${bookingRegistration.phoneNumber}" validtype="mobile" data-options="required:true" style="width:110px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.bookingRegistration.bookingCheckInDate")}:</th>
	    		<td>
	    			  <input name="bookingCheckInDate" type="text" value="${bookingRegistration.bookingCheckInDate}" class="easyui-datebox"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard" value="${bookingRegistration.IDCard}" style="width:150px;"/> 
	    		</td>
				<th>${message("yly.bookingRegistration.intentRoomType")}:</th>
	    		<td>
	    			  <select class="easyui-combobox"   name="roomTypeId" style="width:80px;"> 
						  [#list systemConfigs as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == bookingRegistration.intentRoomType.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>     
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if consultation.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if consultation.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" value="${bookingRegistration.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



