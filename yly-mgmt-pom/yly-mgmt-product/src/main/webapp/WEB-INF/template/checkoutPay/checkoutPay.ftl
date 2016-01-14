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
			    <input class="easyui-textbox" type="text" name="name" validtype="length[0,15]" style="width:75px;"/> 
			</div>
	    	<div class="search-item">
			    <label>账单${message("yly.common.charge.status")}:</label>
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
	    			 出院日期: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_outHospitalizedDate"  data-options="width:150" disabled=true "  />
	    		</td>
	    		<td>
	    			
	    		</td>
	    	</tr>
	    </table>
	    
	  <fieldset> 
	   <legend>老人基本信息:</legend>
	    <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_name"  style="width:180px;" disabled=true />
	    		</td>
	    		<td>
	    			 老人${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  id="addCheckoutPay_elderlyInfo_identifier"  style="width:180px;" disabled=true />
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
	    			  	<p>截止到办理出院，欠水（吨）：<input class="easyui-numberbox"   id="addCheckoutPay_waterCount"   style="width:90px;" disabled=true data-options="min:0,precision:1"/> 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[单价 ￥<input class="easyui-numberbox" id="addCheckoutPay_waterPrice" " style="width:35px;" disabled=true data-options="min:0,precision:2"/> / 吨]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;水费小计：<input class="easyui-numberbox" id="addCheckoutPay_waterAmount"  style="width:90px;" disabled=true data-options="min:0,precision:2"/> </p> 
	    			  	<p>截止到办理出院，欠电（度）：<input class="easyui-numberbox"   id="addCheckoutPay_electricityCount"  style="width:90px;" disabled=true data-options="min:0,precision:1"/>
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[单价 ￥<input class="easyui-numberbox" id="addCheckoutPay_electricityPrice" " style="width:35px;" disabled=true data-options="min:0,precision:2"/> / 度]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;电费小计：<input class="easyui-numberbox" id="addCheckoutPay_electricityAmount"  style="width:90px;" disabled=true data-options="min:0,precision:2"/> </p>	    			  		    			  	
	    </fieldset>
	    <fieldset id="addCheckoutPay_djustmentService" style="display:none"> 
	    </fieldset>
	     <table class="table table-striped">
	    	<tr>
	    		<th>总金额:</th>
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
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 出院日期: <input class="easyui-textbox"  id="viewCheckoutPay_elderlyInfo_outHospitalizedDate"  data-options="width:150" readonly="readonly""  />
	    		</td>
	    		<td>
	    			
	    		</td>
	    	</tr>
	    </table>
	    
	  <fieldset> 
	   <legend>老人基本信息:</legend>
	    <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox"  id="viewCheckoutPay_elderlyInfo_name"  style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 老人${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  id="viewCheckoutPay_elderlyInfo_identifier"  style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly.bedRoom")}: <input class="easyui-textbox"   id="viewCheckoutPay_elderlyInfo_bedLocation"  style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly.nurseLevel")}: <input class="easyui-textbox"   id="viewCheckoutPay_elderlyInfo_nursingLevel_configValue"  style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
		</table>
		</fieldset>		  	
	   <fieldset id="viewCheckoutPay_bedNurseService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutPay_bedNurseAmount"  style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="viewCheckoutPay_bedNurseCharge_remark"  data-options="multiline:true,height:150,width:550" readonly="readonly" /></p>
	   </fieldset>
	    <fieldset id="viewCheckoutPay_mealService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}</legend>
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutPay_mealAmount" value="${billing.mealAmount}" style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox"  id="viewCheckoutPay_mealCharge_remark"   data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	   <fieldset id="viewCheckoutPay_personalizedService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}</legend>   			
	    			  	<p><input class="easyui-numberbox"  id="viewCheckoutPay_personalizedAmount"  style="width:550px;" readonly="readonly" data-options="min:0,precision:2"/></p>
	    			  	<p><input class="easyui-textbox" id="viewCheckoutPay_personalizedCharge_remark"  data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	    <fieldset id="viewCheckoutPay_waterElectricityService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.water.electricity.reocrd")}</legend>
	    			  	<p>截止到办理出院，欠水（吨）：<input class="easyui-numberbox"   id="viewCheckoutPay_waterCount"   style="width:90px;" data-options="required:true,min:0,precision:1"/> 
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[单价 ￥<input class="easyui-numberbox" id="viewCheckoutPay_waterPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / 吨]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;水费小计：<input class="easyui-numberbox" id="viewCheckoutPay_waterAmount"  style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p> 
	    			  	<p>截止到办理出院，欠电（度）：<input class="easyui-numberbox"   id="viewCheckoutPay_electricityCount"  style="width:90px;" data-options="required:true,min:0,precision:1"/>
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;[单价 ￥<input class="easyui-numberbox" id="viewCheckoutPay_electricityPrice" " style="width:35px;" data-options="required:true,editable:false,min:0,precision:2"/> / 度]  
	    			  		&nbsp;&nbsp;&nbsp;&nbsp;电费小计：<input class="easyui-numberbox" id="viewCheckoutPay_electricityAmount"  style="width:90px;" data-options="required:true,editable:false,min:0,precision:2"/> </p>	    			  		    			  	
	    </fieldset>
	   <fieldset id="viewCheckoutPay_djustmentService" style="display:none"> 
	    </fieldset>
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 使用了预存款: <input class="easyui-numberbox"  id="viewCheckoutPay_advanceChargeAmount" style="width:90px;" readonly=true" data-options="min:0,precision:2"/> 付款 
	    		</td>
	    		<td>
	    			总金额(已扣除押金)：<input class="easyui-numberbox"  id="viewCheckoutPay_totalAmount"  style="width:120px;" readonly="readonly" data-options="min:0,precision:2"/>
	    		</td>
	    	</tr>
	    </table>
	    <br>
	</form></div>
<div id="searchBilling"></div>
