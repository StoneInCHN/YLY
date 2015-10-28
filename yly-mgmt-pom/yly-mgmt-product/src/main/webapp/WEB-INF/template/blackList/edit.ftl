<form id="editBlacklist_form" method="post">   
		<input value="${blackList.id}" type="hidden" name="id" />
		<input value="${blackList.tenantID}" type="hidden" name="tenantID" />
		<input type="hidden" name="elderlyInfoID" value="${blackList.elderlyInfo.id}" />
		 <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="elderlyname" name="elderlyname" value= "${blackList.elderlyInfo.name}" data-options="required:true,editable:false" style="width:85px;" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.blacklist.casue")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="cause" validtype="length[0,150]" value= "${blackList.cause}" data-options="required:true,multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" value= "${blackList.remark}" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    </table>
</form>



