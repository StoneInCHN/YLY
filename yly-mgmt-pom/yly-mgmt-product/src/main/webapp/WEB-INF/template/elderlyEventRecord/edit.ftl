
<form id="editEvent_form" method="post">   
		<input type="hidden" value="${elderlyEventRecord.id}"  name="id" />
		<input type="hidden" name="elderlyInfoID" value="${elderlyEventRecord.elderlyInfo.id}" id="elderlyNameEditID"/>
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyEventRecord.elderlyInfo.name}" type="text" id="elderlyNameEdit" name="elderlyname" data-options="required:true,width:85"/>
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyNameEdit')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${elderlyEventRecord.operator}" type="text" name="operator" data-options="required:true,width:85" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.event.eventDate")}:</th>
	    		<td>
	    			 <input class="Wdate" value="${(elderlyEventRecord.eventDate?string("yyyy-MM-dd"))!}" type="text" name="eventDate" data-options="required:true,width:150" readonly="readonly" onclick="WdatePicker({});"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.event.content")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${elderlyEventRecord.eventContent}" name="eventContent" validtype="length[0,300]" data-options="required:true,multiline:true,height:110,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
</form>
	

