<form id="editBed_form" method="post" >   
		<input value="${bed.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>床位编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="bedNumber" value="${bed.bedNumber}" validtype="length[0,20]" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所属房间:</th>
	    		<td>
					 <select class="easyui-combobox" name="roomId"  data-options="required:true,width:150" >   
					     [#list rooms as room]
		               		<option value="${room.id}" [#if room.id == bed.room.id] selected = "selected"[/#if]>${room.roomNumber}</option>
		                 [/#list]
					  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>床位状态:</th>
	    		<td>
	    			 <input type="radio" name="status" value="ENABLE" [#if bed.status == "ENABLE"]checked = "checked"[/#if]><span>启用</span>
       				 <input type="radio" name="status" value="DISABLE" [#if bed.status == "DISABLE"]checked = "checked"[/#if]><span>禁用</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${bed.description}" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100,width:200" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>