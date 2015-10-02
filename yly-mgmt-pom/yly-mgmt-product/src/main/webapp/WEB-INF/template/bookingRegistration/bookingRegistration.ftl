<script src="${base}/resources/modules/bookingRegistration.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.bookingRegistration.search")}</legend>
	    <form id="bookingRegistration_search_form" class="search-form">
			<div class="search-item">
			    <label> ${message("yly.bookingRegistration.bookingdate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="bookingRegistration_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="bookingRegistration-table-list"></table>
<div id="bookingRegistration_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="bookingRegistration_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="bookingRegistration_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="bookingRegistration_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addBookingRegistration">
	<form id="addBookingRegistration_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.bookingRegistration.peopleWhoBooked")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="peopleWhoBooked" data-options="required:true" validtype="length[0,15]" style="width:85px;"/>  
	    		</td>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text"  name="elderlyName" data-options="required:true" validtype="length[0,15]" style="width:85px;"/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" validtype="mobile" data-options="required:true"  style="width:110px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.bookingRegistration.bookingCheckInDate")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" name="bookingCheckInDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard"  style="width:150px;"/> 
	    		</td>
				<th>${message("yly.bookingRegistration.intentRoomType")}:</th>
	    		<td>
	    			   <input class="easyui-combobox" id="bookingRegistrationAddRoomType"  name="roomTypeId" style="width:80px;" data-options="editable:false" >     
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE">${message("yly.gender.male")}</option>
					<option value="FEMALE">${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
	</div>
<div id="editBookingRegistration"></div> 






