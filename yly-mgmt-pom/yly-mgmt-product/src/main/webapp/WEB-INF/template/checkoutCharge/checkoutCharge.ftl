<script src="${base}/resources/modules/checkoutCharge.js"></script>
<script src="${base}/resources/modules/checkout.js"></script>
<style type="text/css">
p
{
text-align:center;
}

</style>
<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.search")}</legend>
	    <form id="checkoutCharge_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderlyInfo.identifier")}:</label>
			    <input class="easyui-textbox" type="text" name="identifier" validtype="length[0,15]"  style="width:60px;"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" type="text" name="realName" validtype="length[0,15]" style="width:75px;"/> 
			</div>
	    	<div class="search-item">
			    <label>${message("yly.charge.billing.status")}:</label>
			   	<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="status" id="status" panelHeight="50px"
			   	data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'PAID',
						label: '${message("yly.common.charge.status.PAID")}'
					},{
						value: 'UNPAID',
						label: '${message("yly.common.charge.status.UNPAID")}'
					}]" />
			</div>
		</form>
		<div class="search-item">
	  	  <button id="checkoutCharge_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="checkoutCharge-table-list"></table>
<div id="checkoutCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="checkoutCharge_manager_tool.add();">${message("yly.charge.checkoutCharge")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="checkoutCharge_manager_tool.editBill();">${message("yly.bill.editBill")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="checkoutCharge_manager_tool.adjustment();">${message("yly.bill.adjustment")}</a>	
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addCheckoutCharge">
	<form id="addCheckoutCharge_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyId" id="addCheckoutCharge_elderlyInfoID">  
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 是否立即办理出院？ 
	    			 <input type="checkbox"   id="addCheckoutCharge_checkoutNow"  onclick="checkoutNow()"/>
	    		</td>
	    		<td>
	    			<div id="checkoutDateDiv">
	    			或者请选择出院日期: <input class="Wdate" type="text" id="addCheckoutCharge_checkoutDate" name="checkoutDate" data-options="required:true,editable:true,width:70" readonly="readonly" onclick="WdatePicker({});"  />
	    			</div>
	    		</td>
	    	</tr>
	    </table>
	    
	  <fieldset> 
	   <legend>${message("yly.common.elderly.information")}:</legend>
	    <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addCheckoutCharge_elderlyName" style="width:180px;" data-options="required:true,editable:false" />
	    			 <a href="#" id="billing_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('checkoutCharge')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly")}${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  id="addCheckoutCharge_elderlyIdentifier" style="width:180px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly.bedRoom")}: <input class="easyui-textbox"   id="addCheckoutCharge_bedRoom" style="width:180px;" data-options="required:true,editable:false" />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly.nurseLevel")}: <input class="easyui-textbox"   id="addCheckoutCharge_nurseLevel" style="width:180px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
		</table>
		</fieldset>
		 <fieldset id="addCheckoutCharge_depositService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.record.deposit")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutCharge_totalDepositCharge" style="width:550px;" data-options="required:true,editable:false,min:0,precision:2" /></p>
	    			  	<p><input class="easyui-textbox" type="text" id="addCheckoutCharge_depositRemark"  data-options="multiline:true,height:50,width:550,required:true,editable:false" /></p>
	    </fieldset>			  	
	   <fieldset id="addCheckoutCharge_bedNurseService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}</legend>
	    			  	<input type="hidden" id="addCheckoutCharge_bedCharge" name="bedNurseCharge.bedAmount"/>
	    			  	<input type="hidden" id="addCheckoutCharge_nurseCharge" name="bedNurseCharge.nurseAmount"/>
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutCharge_totalBedNurseCharge"  style="width:550px;" data-options="required:true,editable:false,min:0,precision:2" /></p>
	    			  	<p><input class="easyui-textbox" type="text" id="addCheckoutCharge_bedNurseRemark" name="bedNurseCharge.remark"  data-options="multiline:true,height:150,width:550,required:true,editable:false" /></p>
	   </fieldset>
	    <fieldset id="addCheckoutCharge_mealService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutCharge_totalMealCharge" name="mealCharge.mealAmount" style="width:550px;" data-options="required:true,editable:false,min:0,precision:2" /></p>
	    			  	<p><input class="easyui-textbox" type="text" id="addCheckoutCharge_mealRemark" name="mealCharge.remark"  data-options="multiline:true,height:150,width:550,required:true,editable:false" /></p>
	    </fieldset>
	    <fieldset id="addCheckoutCharge_personalizedService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}</legend>   			
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutCharge_totalPersonalizedServiceCharge" name="personalizedCharge.personalizedAmount" style="width:550px;" data-options="required:true,editable:false,min:0,precision:2" /></p>
	    			  	<p><input class="easyui-textbox" type="text" id="addCheckoutCharge_personalizedServiceRemark" name="personalizedCharge.remark"  data-options="multiline:true,height:150,width:550,required:true,editable:false" /></p>
	    </fieldset>
	    <fieldset id="addCheckoutCharge_waterElectricityService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.water.electricity.reocrd")}</legend>
	    			  	<p>${message("yly.checkout.need_pay.water.count")}：<input class="easyui-numberbox" prompt="${message("yly.common.please.keyin")}" id="addCheckoutCharge_waterCount"  name="waterElectricityCharge.waterCount" style="width:90px;" data-options="required:true,min:0,precision:1"/> 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="addCheckoutCharge_waterPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / ${message("yly.charge.ton")}]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.water.sum.amount")}：<input class="easyui-numberbox" id="addCheckoutCharge_waterAmount" name="waterElectricityCharge.waterAmount" style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p> 
	    			  	<p>${message("yly.checkout.need_pay.electricity.count")}：<input class="easyui-numberbox" prompt="${message("yly.common.please.keyin")}" id="addCheckoutCharge_electricityCount" name="waterElectricityCharge.electricityCount" style="width:90px;" data-options="required:true,min:0,precision:1"/>
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="addCheckoutCharge_electricityPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / ${message("yly.charge.degree")}]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.electricity.sum.amount")}：<input class="easyui-numberbox" id="addCheckoutCharge_electricityAmount" name="waterElectricityCharge.electricityAmount" style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p>	    			  		    			  	
	    </fieldset>
	    <p><p>
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.checkout.use.advanceChargeAmount")}: 
	    			 <input class="easyui-numberbox"   id="addCheckoutCharge_advanceChargeAmount" style="width:90px;" data-options="required:true,editable:false,min:0,precision:2" />
	    			 ${message("yly.charge.pay")} 
	    			 <input type="checkbox"   id="addCheckoutCharge_isAdvanceCharge" name="isAdvanceCharge"/>
	    		</td>
	    		<td>
	    			<input type="hidden" id="addCheckoutCharge_totalChargeWithoutAdvance" value="0"/> 
	    			${message("yly.charge.totalAmount.without_deposit")}：<input class="easyui-numberbox"   id="addCheckoutCharge_totalCharge" name="totalAmount" style="width:120px;" data-options="required:true,editable:false,min:0,precision:2" />
	    		</td>
	    	</tr>
	    </table>
	    <br>
	</form>
</div>
<div id="checkoutChargeDetail">
	<form id="checkoutCharge_form" method="post" class="form-table"> 
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.checkout.elderly.outHospitalizedDate")}: <input class="easyui-textbox"  id="viewCheckoutCharge_elderlyInfo_outHospitalizedDate"  data-options="width:150" readonly="readonly""  />
	    		</td>
	    		<td>
	    			
	    		</td>
	    	</tr>
	    </table>
	    
	  <fieldset> 
	   <legend>${message("yly.common.elderly.information")}:</legend>
	    <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox"  id="viewCheckoutCharge_elderlyInfo_name"  style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly")}${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  id="viewCheckoutCharge_elderlyInfo_identifier"  style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly.bedRoom")}: <input class="easyui-textbox"   id="viewCheckoutCharge_elderlyInfo_bedLocation"  style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly.nurseLevel")}: <input class="easyui-textbox"   id="viewCheckoutCharge_elderlyInfo_nursingLevel_configValue"  style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
		</table>
		</fieldset>		  	
	   <fieldset id="viewCheckoutCharge_bedNurseService"  style="display:blank"> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutCharge_bedNurseAmount"  style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="viewCheckoutCharge_bedNurseCharge_remark"  data-options="multiline:true,height:150,width:550" readonly="readonly" /></p>
	   </fieldset>
	    <fieldset id="viewCheckoutCharge_mealService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutCharge_mealAmount" value="${billing.mealAmount}" style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="viewCheckoutCharge_mealCharge_remark"   data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	   <fieldset id="viewCheckoutCharge_personalizedService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}</legend>   			
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutCharge_personalizedAmount"  style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox" id="viewCheckoutCharge_personalizedCharge_remark"  data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	    <fieldset id="viewCheckoutCharge_waterElectricityService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.water.electricity.reocrd")}</legend>
	    			  	<p>${message("yly.checkout.need_pay.water.count")}：<input class="easyui-numberbox" prompt="请输入..." id="viewCheckoutCharge_waterCount"   style="width:90px;" data-options="required:true,min:0,precision:1"/> 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="viewCheckoutCharge_waterPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / ${message("yly.charge.ton")}]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.water.sum.amount")}：<input class="easyui-numberbox" id="viewCheckoutCharge_waterAmount"  style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p> 
	    			  	<p>${message("yly.checkout.need_pay.electricity.count")}：<input class="easyui-numberbox" prompt="请输入..." id="viewCheckoutCharge_electricityCount"  style="width:90px;" data-options="required:true,min:0,precision:1"/>
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="viewCheckoutCharge_electricityPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / ${message("yly.charge.degree")}]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.electricity.sum.amount")}：<input class="easyui-numberbox" id="viewCheckoutCharge_electricityAmount"  style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p>	    			  		    			  	
	    </fieldset>
	    <fieldset id="viewCheckoutCharge_djustmentService" style="display:none"> 
	    </fieldset>
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.checkout.use.advanceChargeAmount")}: <input class="easyui-numberbox"  id="viewCheckoutCharge_advanceChargeAmount" style="width:90px;" readonly=true" data-options="min:0,precision:2"/> ${message("yly.charge.pay")} 
	    		</td>
	    		<td>
	    			${message("yly.charge.totalAmount.without_deposit")}：<input class="easyui-numberbox"  id="viewCheckoutCharge_totalAmount"  style="width:120px;" readonly="readonly" data-options="min:0,precision:2"/>
	    		</td>
	    	</tr>	    	
	    </table>
	    <br>
	</form>
	</div>
	<div id="addCheckoutAdjust">
	<form id="addCheckoutAdjust_form" method="post" class="form-table">
		 <table class="table table-striped">
		 	<input type="hidden" name="billId" id="addCheckout_billId">
		 	<tr>
	    		<th>${message("yly.bill.adjustment.cause")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="adjustmentCause" id="addCheckout_adjustmentCause" panelHeight="100px" style="width:130px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bill.adjustment.amount")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="addCheckout_adjustmentAmount" name="adjustmentAmount" data-options="required:true,precision:2"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			 <textarea  cols=40 rows=5 type="text" name="remark"></textarea>
	    		</td>
	    	</tr>
		 </table>
	</form>
</div>
<div id="searchBilling"></div>

