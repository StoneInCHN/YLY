<form id="editRoom_form" method="post">   
		<input value="${room.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.room.roomName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="roomName" value="${room.roomName}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomNumber" value="${room.roomNumber}"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.building")}:</th>
	    		<td>
	    			    <select class="easyui-combobox" name="buildingId"  data-options="required:true,width:150" >   
						     [#list buildings as building]
			               		<option value="${building.id}" [#if building.id == room.building.id] selected = "selected"[/#if]>${building.buildingName}</option>
			                 [/#list]
					    </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.floor")}:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" value="${room.floor}" name="floor" data-options="min:-10,max:20,required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomType")}:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="roomTypeId"  data-options="required:true,width:150"> 
						  [#list systemConfigs as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == room.roomType.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomStatus")}:</th>
	    		<td>
	    			 <input type="radio" name="roomStatus" value="ENABLE" [#if room.roomStatus == "ENABLE"]checked = "checked"[/#if]><span>${message("yly.common.enable")}</span>
       				 <input type="radio" name="roomStatus" value="DISABLE" [#if room.roomStatus == "DISABLE"]checked = "checked"[/#if]><span>${message("yly.common.disable")}</span>
	    		</td>
	    	</tr>
			<tr>
	    		<th>${message("yly.room.description")}:</th>
	    		<td>
					 <input class="easyui-textbox"  value="${room.description}" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:150" />  
	    		</td>
	    	</tr>
	    </table>
</form>



