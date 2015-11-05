<form id="editDepartment_form" method="post" >
		<input  type="hidden" name="id" value="${department.id}"  />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.department.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" value="${department.name}" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.department.parent")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="editDepartment_form_parentName" name="parentId" data-value="${department.parent.id}" type="text" />
	    		</td>
	    	</tr>
	    </table>
	</form>