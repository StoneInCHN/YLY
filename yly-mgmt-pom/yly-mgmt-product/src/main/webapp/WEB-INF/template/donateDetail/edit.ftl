<form id="editDonateDetail_form" method="post">   
		<input type="hidden" id="recordId" name="recordId"/>
		<input type="hidden" id="id" name="id" value="${donateDetail.id}"/>  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.donateDetail.donateType")}:</th>
	    		<td>
	    		
	    			<select id="donatorGender" class="easyui-combobox"  name="donateType" style="width:60px;">   
						<option value="MONEY" [#if donateDetail.donateType == 'MALE'] selected = "selected"[/#if]>${message("yly.donateDetail.donateType.money")}</option>
						<option value="ITEM" [#if donateDetail.donateType == 'FEMALE'] selected = "selected"[/#if]>${message("yly.donateDetail.donateType.item")}</option> 
				  </select>
				</td>     
	    		<th>${message("yly.donateDetail.donateAmount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateAmount" data-options="required:true" value="${donateDetail.donateAmount}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.donateDetail.donateItemType")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donateItemTypeId" id="donateItemType" value="${donateDetail.donateItemType.itemName}" data-options="required:true" />
	    		</td>
	    		<th>${message("yly.donateDetail.units")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="units" data-options="required:true" value="${donateDetail.units}" validtype="length[0,5]"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="remark" data-options="required:true" value="${donateDetail.remark}" validtype="length[0,30]"/>   
	    		</td>
	    	</tr>
	    </table>
</form>



