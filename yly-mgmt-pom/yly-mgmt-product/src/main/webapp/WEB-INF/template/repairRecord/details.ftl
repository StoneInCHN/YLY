<form id="repairRecordDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.repairRecord.repairContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairContent" value="${repairRecord.repairContent}" data-options="editable:false" style="width:200px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.reportOperator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="reportOperator" value="${repairRecord.reportOperator}" data-options="editable:false" style="width:120px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairPlace")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="repairPlace" value="${repairRecord.repairPlace}" data-options="editable:false" style="width:200px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairOperator")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="repairOperator" value="${repairRecord.repairOperator}" data-options="editable:false" style="width:120px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" value="${repairRecord.remark}" data-options="editable:false,multiline:true,height:90,width:220"/>    
	    		</td>
	    	</tr>
	    </table>
</form>



