<form id="editRoom_form" method="post">   
		<input value="${room.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>房间名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="roomName" value="${room.roomName}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>房间编号:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomNumber" value="${room.roomNumber}"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所属楼宇:</th>
	    		<td>
	    			    <select class="easyui-combobox" name="buildingId"  data-options="required:true,width:150" >   
					     [#list buildings as building]
		               		<option value="${building.id}" [#if building.id == room.building.id] selected = "selected"[/#if]>${building.buildingName}</option>
		                 [/#list]
					  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所在楼层:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" value="${room.floor}" name="floor" data-options="min:-10,max:20,required:true" /> 
	    		</td>
	    	</tr>
	    	<!--
	    	<tr>
	    		<th>房间类型:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomType"  /> 
	    		</td>
	    	</tr>
	    	-->
	    	<tr>
	    		<th>房间状态:</th>
	    		<td>
	    			 <input type="radio" name="roomStatus" value="ENABLE" [#if room.roomStatus == "ENABLE"]checked = "checked"[/#if]><span>启用</span>
       				 <input type="radio" name="roomStatus" value="DISABLE" [#if room.roomStatus == "DISABLE"]checked = "checked"[/#if]><span>禁用</span>
	    		</td>
	    	</tr>
			<tr>
	    		<th>描述:</th>
	    		<td>
	    			  <textarea class="easyui-validatebox"  value="${room.description}" data-options="max:100"   name="description" style="height:60px;width:200px"></textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



