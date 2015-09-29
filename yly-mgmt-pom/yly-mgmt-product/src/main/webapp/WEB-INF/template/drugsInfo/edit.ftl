<form id="editDrugs_form" method="post">   
		 <input value="${drugs.id}" type="hidden" name="id" />
	   <table class="table table-striped">
	    	<tr>
	    		<th>药品名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${drugs.name}" type="text" name="name" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>别名:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${drugs.alias}" type="text" name="alias"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>拼音简码:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${drugs.phoneticCode}" type="text" name="phoneticCode"  />   
	    		</td>
	    	</tr>
	    	<!--
	    	<tr>
	    		<th>药品分类:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${drugs.drugCategory}" type="text" name="drugCategory" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>药品常用单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${drugs.conventionalUnit}" type="text" name="conventionalUnit"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${drugs.description}" type="text" name="description"  /> 
	    		</td>
	    	</tr>
	    	-->
	    </table>
</form>



