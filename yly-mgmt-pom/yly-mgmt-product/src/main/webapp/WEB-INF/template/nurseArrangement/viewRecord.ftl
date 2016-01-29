	<form id="viewNurseArrangementRecord_form" method="post"> 
		<fieldset>
	     <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox" value="${nurseArrangementRecord.nurseName}"  style="width:400px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangementRecord.nurseArrangement.nurseStartDate}"   data-options="required:true,editable:false"  />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate"value="${nurseArrangementRecord.nurseArrangement.nurseEndDate}"   data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>执行时间: </th>
	    		<td colspan='3'>
	    			 <input type="text" class="Wdate" value="${nurseArrangementRecord.nurseServiceTime}"   data-options="required:true,editable:false"  />
	    		</td>
	    	</tr>	    	
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.remark}"  data-options="multiline:true,height:110,width:460,required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>基本信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.elderlyName}"   style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.nurseOperator}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>老人床位: </th>
	    		<td>
	    			 <input class="easyui-textbox"   value="${nurseArrangementRecord.nurseArrangement.bedLocation}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理级别: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.nurseArrangement.nursingLevel}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>