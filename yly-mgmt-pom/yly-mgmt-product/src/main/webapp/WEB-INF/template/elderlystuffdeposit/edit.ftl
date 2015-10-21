
<form id="editStuffDeposit_form" method="post">   
		<input type="hidden" value="${elderlyStuffDeposit.id}"  name="id" />
		<input type="hidden" name="elderlyInfoID" value="${elderlyStuffDeposit.elderlyInfo.id}" id="elderlyNameEditID"/>
	     <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyStuffDeposit.name}" type="text" id="name" name="name" validtype="length[0,30]" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.count")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" value="${elderlyStuffDeposit.count}" type="text" id="count" name="count" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.stuffNumer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyStuffDeposit.stuffNumber}" type="text" id="stuffNumber" name="stuffNumber" validtype="length[0,15]" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>    	
			<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.status")}:</th>
	    		<td>   					
	    		<input class="easyui-combobox" data-options="
	    			valueField: 'label',
					textField: 'value',
						data: [{
							label: 'PUT_IN',
							value: '${message("yly.elderlyInfo.stuffDeposit_PUT_IN")}'
						},{
							label: 'TAKE_ALWAY',
							value: '${message("yly.elderlyInfo.stuffDeposit_TAKE_ALWAY")}'
						}],
						prompt:'${message("yly.common.please.select")}'" value="${elderlyStuffDeposit.stuffDepositStatus}" panelHeight="50"  id="stuffDepositStatus" name="stuffDepositStatus" data-options="required:true" style="width:85px;"/>
	    		</td>
	    	</tr>	    	
	    	<tr id="editPutInDateTR">
	    		<th>${message("yly.elderlyInfo.stuffDeposit.inputDate")}:</th>
	    		<td>
	    			  <input class="Wdate" value="${(elderlyStuffDeposit.putinDate?string("yyyy-MM-dd"))!}" type="text" name="putinDate" data-options="required:true,editable:true,width:110" readonly="readonly" onclick="WdatePicker({});"  /> 
	    		</td>
	    	</tr>
	    	<tr id="editTakeAlwayDateTR">
	    		<th>${message("yly.elderlyInfo.stuffDeposit.takeAwayDate")}:</th>
	    		<td>
	    			  <input class="Wdate" value="${(elderlyStuffDeposit.takeAlwayDate?string("yyyy-MM-dd"))!}" type="text" name="takeAlwayDate" data-options="required:true,editable:true,width:110" readonly="readonly" onclick="WdatePicker({});"  /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${elderlyStuffDeposit.operator}" type="text" name="operator" data-options="required:true,width:85" /> 
	    		</td>
	    	</tr>	    		    	
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.elderlyName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyStuffDeposit.elderlyInfo.name}" type="text" id="elderlyNameEdit" name="elderlyname" data-options="required:true,width:85"/>
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyNameEdit')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.remark")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyStuffDeposit.remark}" type="text" id="remark" name="remark" validtype="length[0,150]" data-options="multiline:true,required:true,height:110,width:320" />   
	    		</td>
	    	</tr>
	    </table>
</form>
	

