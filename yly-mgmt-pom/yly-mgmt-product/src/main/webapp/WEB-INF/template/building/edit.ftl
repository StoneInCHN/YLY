<form id="editBuild_form" method="post">   
		 <input value="${building.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.building.buildingName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${building.buildingName}" type="text" name="buildingName" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.building.description")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${building.description}" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



