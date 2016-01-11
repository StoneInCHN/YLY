
	<form id="addDonateDetail_form" method="post" class="form-table"> 
	 <input type="hidden" id="recordId" name="recordId"/>  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.donateDetail.donateType")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'MONEY',
				      value: '${message("yly.donateDetail.donateType.money")}'
				     },{
				      label: 'ITEM',
				      value: '${message("yly.donateDetail.donateType.item")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="donateType" style="width:110px;"/>
	    		</td>   
	    		</td>
	    		<th>${message("yly.donateDetail.donateAmount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateAmount" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.donateDetail.donateItemType")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateItemTypeId" id="donateItemType" />   
	    		</td>
	    		<th>${message("yly.donateDetail.units")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="units" data-options="required:true" validtype="length[0,5]"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="remark" validtype="length[0,30]" data-options="multiline:true,height:90,width:300" />   
	    		</td>
	    	</tr>
	    </table>
	</form>





