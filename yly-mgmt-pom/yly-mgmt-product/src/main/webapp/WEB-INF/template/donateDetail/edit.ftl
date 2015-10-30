<form id="editDonateDetail_form" method="post">   
		<input type="hidden" id="recordId" name="recordId"/>
		<input type="hidden" id="id" name="id" value="${donateDetail.id}"/>  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>捐赠类型（钱或物）:</th>
	    		<td>
	    		
	    			<select id="donatorGender" class="easyui-combobox"  name="donateType" style="width:60px;">   
						<option value="MONEY" [#if donateDetail.donateType == 'MALE'] selected = "selected"[/#if]>钱</option>
						<option value="ITEM" [#if donateDetail.donateType == 'FEMALE'] selected = "selected"[/#if]>物</option> 
				  </select>
				</td>     
	    		<th>捐赠数额:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateAmount" data-options="required:true" value="${donateDetail.donateAmount}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>捐赠物品类型:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateItemTypeId" id="donateItemType" value="${donateDetail.donateItemType.itemName}" data-options="required:true" />
	    			    
	    		</td>
	    		<th>物品计量单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="units" data-options="required:true" value="${donateDetail.units}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="remark" data-options="required:true" value="${donateDetail.remark}"/>   
	    		</td>
	    	</tr>
	    </table>
</form>



