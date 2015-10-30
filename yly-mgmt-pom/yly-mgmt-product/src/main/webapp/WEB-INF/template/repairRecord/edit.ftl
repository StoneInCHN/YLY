<form id="editRepairRecord_form" method="post">   
		<input value="${repairRecord.id}" type="hidden" name="id" />
		<input value="${repairRecord.tenantID}" type="hidden" name="tenantID" />
		
		 <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.repairRecord.repairContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairContent" value="${repairRecord.repairContent}" validtype="length[0,20]" data-options="required:true" style="width:200px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.reportOperator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="reportOperator" value="${repairRecord.reportOperator}" validtype="length[0,6]" data-options="required:true" style="width:120px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairPlace")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="repairPlace" value="${repairRecord.repairPlace}" validtype="length[0,40]" data-options="required:true" style="width:200px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairOperator")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="repairOperator" value="${repairRecord.repairOperator}" validtype="length[0,6]" data-options="required:true" style="width:120px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" value="${repairRecord.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:220"/>    
	    		</td>
	    	</tr>
	    </table>
</form>



