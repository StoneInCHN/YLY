		<form id="editNurseArrangementRecord_form" method="post"> 
		<input type="hidden" name="id"  value="${nurseArrangementRecord.id}">  
		<fieldset>
	     <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox" value="${nurseArrangementRecord.nurseName}"   name="nurseName"
	    			 					id="addNurseArrangementRecord_nurseName"  style="width:400px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangementRecord.nurseArrangement.nurseStartDate}"  
	    			 							id="editNurseArrangementRecord_nurseStartDate" data-options="required:true,editable:false"  />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate"value="${nurseArrangementRecord.nurseArrangement.nurseEndDate}" 
	    			 								 id="editNurseArrangementRecord_nurseEndDate"  data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>执行时间: </th>
	    		<td colspan='3'>
	    			 <input type="text" class="Wdate" value="${nurseArrangementRecord.nurseServiceTime}"  name="nurseServiceTime" data-options="required:true" 
	    			 			onclick="WdatePicker({minDate: '#F{$dp.$D(\'editNurseArrangementRecord_nurseStartDate\')}',maxDate: '#F{$dp.$D(\'editNurseArrangementRecord_nurseEndDate\')}'});" />
	    		</td>
	    	</tr>	    	
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.remark}" name="remark" data-options="multiline:true,height:110,width:460,required:true" />
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
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.elderlyName}"  name="elderlyName"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangementRecord.nurseOperator}" name="nurseOperator" style="width:150px;" data-options="required:true,editable:false" />
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