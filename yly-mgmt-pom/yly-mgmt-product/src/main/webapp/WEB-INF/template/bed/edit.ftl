<form id="editBed_form" method="post" >   
		<input value="${bed.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.bed.bedNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="bedNumber" value="${bed.bedNumber}" validtype="length[0,20]" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.room")}:</th>
	    		<td>
					  <input class="easyui-combotree" type="text" id="editBed_form_roomId" name="roomId" data-value="${bed.room.id}" data-options="required:true,editable:false" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.status")}:</th>
	    		<td>
	    			 <input type="radio" name="status" value="ENABLE" [#if bed.status == "ENABLE"]checked = "checked"[/#if]><span>${message("yly.common.enable")}</span>
       				 <input type="radio" name="status" value="DISABLE" [#if bed.status == "DISABLE"]checked = "checked"[/#if]><span>${message("yly.common.disable")}</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.usageState")}:</th>
	    		<td>
	    			 <input type="radio" name="usageState" value="UNAPPROPRIATED" [#if bed.status == "UNAPPROPRIATED"]checked = "checked"[/#if]><span>${message("yly.common.unappropriated")}</span>
       				 <input type="radio" name="usageState" value="OCCUPIED" [#if bed.status == "OCCUPIED"]checked = "checked"[/#if]><span>${message("yly.common.occupied")}</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.description")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${bed.description}" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100,width:200" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
