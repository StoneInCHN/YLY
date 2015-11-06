<form id="editPosition_form" method="post" >
		<input  type="hidden" name="id" value="${position.id}"  />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.position.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" value="${position.name}" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.position.department")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="editPosition_form_departmentId" name="departmentId" data-value="${position.department.id}"  />
	    		</td>
	    	</tr>
	    </table>
	</form>