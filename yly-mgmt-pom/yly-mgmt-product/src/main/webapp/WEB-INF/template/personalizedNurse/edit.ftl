<form id="editPersonalizedNurse_form" method="post">   
	<input value="${personalizedNurse.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedNurse.elderlyInfo")}:</th>
	    		<td>
	    			 <input class="easyui-datebox" value="${personalizedNurse.elderlyInfo}"  name="elderlyInfo" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.applyTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox" value="${personalizedNurse.applyTime}"  name="applyTime" data-options="required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.sumCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${personalizedNurse.sumCount}"  name="sumCount" data-options="required:true" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.usedCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${personalizedNurse.usedCount}"  name="usedCount" data-options="required:true,multiline:true,height:100" />  
	    		</td>
	    	</tr>
 			<tr>
	    		<th>${message("yly.personalizedNurse.nurseContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${personalizedNurse.nurseContent}"  name="nurseContent" data-options="required:true,multiline:true,height:100" />  
	    		</td>
	    	</tr>
	    	 tr>
	    		<th>${message("yly.personalizedNurse.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${personalizedNurse.operator}"  name="operator" data-options="required:true,multiline:true,height:100" />  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${personalizedNurse.remark}"  name="remark" data-options="required:true,multiline:true,height:100" />
	    		</td>
	    	</tr>
	    </table>
</form>



