	<form id="viewNurseArrangement_form" method="post"> 
	   <fieldset> 
	   <legend>基本信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangement.elderlyInfo.name}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  value="${nurseArrangement.elderlyInfo.name}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>老人床位: </th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseArrangement.bedLocation}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理级别: </th>
	    		<td>
	    			 <input class="easyui-textbox" value="${nurseArrangement.nursingLevel}"  style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   value="${nurseArrangement.nurseName}"  style="width:400px;" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangement.nurseStartDate}" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'addNurseArrangement_nurseEndDate\')}'});" />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate" value="${nurseArrangement.nurseEndDate}" onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNurseArrangement_nurseStartDate\')}'});"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox" value="${nurseArrangement.remark}"  data-options="multiline:true,height:100,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>