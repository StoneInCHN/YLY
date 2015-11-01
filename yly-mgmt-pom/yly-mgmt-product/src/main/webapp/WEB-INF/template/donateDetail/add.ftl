
	<form id="addDonateDetail_form" method="post" class="form-table"> 
	 <input type="hidden" id="recordId" name="recordId"/>  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>捐赠类型（钱或物）:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'MONEY',
				      value: '钱'
				     },{
				      label: 'ITEM',
				      value: '物'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="donateType" style="width:110px;"/>
	    		</td>   
	    		</td>
	    		<th>捐赠数额:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateAmount" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>捐赠物品类型:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateItemTypeId" id="donateItemType" data-options="required:true" />   
	    		</td>
	    		<th>物品计量单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="units" data-options="required:true" validtype="length[0,5]"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="remark" data-options="required:true" validtype="length[0,30]"/>   
	    		</td>
	    	</tr>
	    </table>
	</form>





