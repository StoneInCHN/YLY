<script src="${base}/resources/modules/checkoutPay.js"></script>
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
	    <form id="checkoutPay_search_form" class="search-form">
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
	  	  <button id="checkoutPay_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="checkoutPay-table-list"></table>
<div id="checkoutPay_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="checkoutPay_manager_tool.add();">${message("yly.charge.checkoutPay")}</a>	
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addCheckoutPay">
	<form id="addCheckoutPay_form" method="post" class="form-table"> 
		<input type="hidden" name="checkoutBillingId" id="addCheckoutPay_checkoutBillingId">
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.checkout.elderly.outHospitalizedDate")}: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_outHospitalizedDate"  data-options="width:150" disabled=true "  />
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
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_name"  style="width:180px;" disabled=true />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly")}${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_identifier"  style="width:180px;" disabled=true />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly.bedRoom")}: <input class="easyui-textbox"   id="addCheckoutPay_elderlyInfo_bedLocation"  style="width:180px;" disabled=true />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly.nurseLevel")}: <input class="easyui-textbox"   id="addCheckoutPay_elderlyInfo_nursingLevel_configValue"  style="width:180px;" disabled=true />
	    		</td>
	    	</tr>
		</table>
		</fieldset>		  	
	   <fieldset id="addCheckoutPay_bedNurseService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutPay_bedNurseAmount"  style="width:550px;" disabled=true data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="addCheckoutPay_bedNurseCharge_remark"  data-options="multiline:true,height:150,width:550" disabled=true /></p>
	   </fieldset>
	    <fieldset id="addCheckoutPay_mealService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutPay_mealAmount" value="${billing.mealAmount}" style="width:550px;" disabled=true data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="addCheckoutPay_mealCharge_remark"   data-options="multiline:true,height:150,width:550" disabled=true/></p>
	    </fieldset>
	   <fieldset id="addCheckoutPay_personalizedService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}</legend>   			
	    			  	<p><input class="easyui-numberbox"  id="addCheckoutPay_personalizedAmount"  style="width:550px;" disabled=true data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox" id="addCheckoutPay_personalizedCharge_remark"  data-options="multiline:true,height:150,width:550" disabled=true/></p>
	    </fieldset>
	    <fieldset id="addCheckoutPay_waterElectricityService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.water.electricity.reocrd")}</legend>
	    			  	<p>${message("yly.checkout.need_pay.water.count")}：<input class="easyui-numberbox"   id="addCheckoutPay_waterCount"   style="width:90px;" disabled=true data-options="min:0,precision:1"/> 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="addCheckoutPay_waterPrice" " style="width:35px;" disabled=true data-options="min:0,precision:2"/> / ${message("yly.charge.ton")}] 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.water.sum.amount")}：<input class="easyui-numberbox" id="addCheckoutPay_waterAmount"  style="width:90px;" disabled=true data-options="min:0,precision:2"/> </p> 
	    			  	<p>${message("yly.checkout.need_pay.electricity.count")}：<input class="easyui-numberbox"   id="addCheckoutPay_electricityCount"  style="width:90px;" disabled=true data-options="min:0,precision:1"/>
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[${message("yly.charge.unit.price.label")}<input class="easyui-numberbox" id="addCheckoutPay_electricityPrice" " style="width:35px;" disabled=true data-options="min:0,precision:2"/> / ${message("yly.charge.degree")}]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;${message("yly.charge.electricity.sum.amount")}：<input class="easyui-numberbox" id="addCheckoutPay_electricityAmount"  style="width:90px;" disabled=true data-options="min:0,precision:2"/> </p>	    			  		    			  	
	    </fieldset>
	    <fieldset id="addCheckoutPay_djustmentService" style="display:none"> 
	    </fieldset>
	     <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.charge.totalAmount")}:</th>
	    		<td>
	    			<input class="easyui-numberbox"  id="addCheckoutPay_totalAmount" name="payTotalAmount" style="width:90px;" data-options="min:0,precision:2"/>
	    		</td>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td>
	    			<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="paymentType" id="addCheckoutPay_paymentType" panelHeight="100px"
			   		data-options="required:true,editable:false,
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'CASH',
						label: '${message("yly.common.charge.paymentType.CASH")}'
					},{
						value: 'CARD',
						label: '${message("yly.common.charge.paymentType.CARD")}'
					},{
						value: 'MIXTURE',
						label: '${message("yly.common.charge.paymentType.MIXTURE")}'
					}]" /> 
	    		</td>
	    	</tr>
	    	<tr id="addCheckoutPay_mixturePay" style="display:none">
	    		<th>${message("yly.common.charge.paymentType.CASH")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="addCheckoutPay_totalAmount_cash" name="cashAmount" data-options="min:0,precision:2"/>    
	    		</td>
	    		<th>${message("yly.common.charge.paymentType.CARD")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="addCheckoutPay_totalAmount_card" name="cardAmount" data-options="min:0,precision:2"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" name="remark" style="width:450px" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    </table>
	    <br>
	</form></div>
<div id="checkoutPayDetail">
	<form id="checkoutPay_form" method="post" class="form-table"> 
					 <p>
	   	    		 <p style="text-align:left">${message("yly.checkout.elderly.outHospitalizedDate")}：
	    			 <span title="viewCheckoutPay"  style="border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:1px 4px;text-align:left;" id="viewCheckoutPay_elderlyInfo_outHospitalizedDate"></span>
	    			 </p> 
						<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;"  id="viewCheckoutPay_elderlyInfoLable"></p>
						<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;" id="viewCheckoutPay_elderlyInfo_remark"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;" id="viewCheckoutPay_bedNurseAmount"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;" id="viewCheckoutPay_bedNurseCharge_remark"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;" id="viewCheckoutPay_mealAmount"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;" id="viewCheckoutPay_mealCharge_remark"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;" id="viewCheckoutPay_personalizedAmount"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;"  id="viewCheckoutPay_personalizedCharge_remark"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;" id="viewCheckoutPay_waterElectricityAmount"></p>
	    			  	<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;"  id="viewCheckoutPay_waterElectricity_remark"></p>
	    				<p title="viewCheckoutPay" style="white-space:pre-wrap;margin:16px 4px 0px 4px;padding:2px 12px;text-align:left;"  id="viewCheckoutPay_djustmentLable"></p>
	    				<p title="viewCheckoutPay" style="white-space:pre-wrap;border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:4px 12px;text-align:left;"  id="viewCheckoutPay_djustmentService"></p>
	    				<p>			  	    			  		    			  		    
	    			 <table>
	    			 	<tr>
	    			 		<td style="padding:4px 32px 4px 0px">
	    			 				${message("yly.checkout.use.advanceChargeAmount")}: 
	    			 				<span title="viewCheckoutPay"  style="border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:1px 4px;text-align:left;width:90px" id="viewCheckoutPay_advanceChargeAmount"></span>
	    			  				${message("yly.charge.pay")}
	    			  		</td>
	    			  		<td style="margin:4px;padding:4px 32px 4px 0px">
	    			  				&nbsp;&nbsp;&nbsp;&nbsp;
	    			  		</td>
							<td style="padding:4px 32px 4px 0px">
	    							${message("yly.charge.totalAmount.without_deposit")}：
	    							<span title="viewCheckoutPay" style="border:1px solid #b1b2b3;border-radius:4px 4px 4px 4px;margin:4px;padding:1px 4px;text-align:right;width:90px"  id="viewCheckoutPay_totalAmount"></span>
	    					</td>
	    				</tr>
	    			</table>
	    <br>
	</form></div>
<div id="searchBilling"></div>

