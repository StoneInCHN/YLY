<form id="repairRecordDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.repairRecord.repairContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairContent" value="${repairRecord.repairContent}" validtype="length[0,15]" style="width:200px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.reportOperator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="reportOperator" value="${repairRecord.reportOperator}" validtype="length[0,15]" style="width:120px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairPlace")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="repairPlace" value="${repairRecord.repairPlace}" validtype="length[0,50]" style="width:200px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairOperator")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="repairOperator" value="${repairRecord.repairOperator}" validtype="length[0,10]" style="width:120px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="contactPhone" value="${repairRecord.contactPhone}" validtype="length[0,30]" style="width:120px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" value="${repairRecord.remark}" validtype="length[0,30]" data-options="multiline:true,height:90,width:220"/>    
	    		</td>
	    	</tr>
	    </table>
</form>


