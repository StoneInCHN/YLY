<form id="editRole_form" method="post">  
		<input value="${role.id}" type="hidden" name="id" />
		<input value="${role.tenantID}" type="hidden" name="tenantID" />
	   <table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.role.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${role.name}" name="name" validtype="length[0,150]" data-options="required:true,multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.role.description")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" value="${role.description}" name="description" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    </table>
</form>

