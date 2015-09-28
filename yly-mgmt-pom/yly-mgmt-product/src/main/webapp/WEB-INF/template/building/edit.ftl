<form id="editBuild_form" method="post">   
		 <input value="${building.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>楼宇名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${building.buildingName}" type="text" name="buildingName" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${building.description}" name="description" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



