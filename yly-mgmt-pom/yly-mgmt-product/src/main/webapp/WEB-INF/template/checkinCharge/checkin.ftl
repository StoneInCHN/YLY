<script src="${base}/resources/modules/checkinCharge.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.charge.record.search")}</legend>
	    <form id="checkinCharge_search_form" class="search-form">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" prompt="${message("yly.common.prompt.input.elderlyName")}" type="text" name="realName" id="realName"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.charge.record.elder.identifier")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.identifier")}" name="identifier" id="identifier"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.common.charge.payTime")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" placeholder="${message("yly.common.prompt.beginDate")}" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" placeholder="${message("yly.common.prompt.endDate")}" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="checkinCharge_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="checkinCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="checkinCharge_manager_tool.add();">${message("yly.charge.checkin")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="checkinCharge_table_list"></table>
<div id="checkinDetail"></div>
<div id="addCheckinCharge">
	<form id="addCheckinCharge_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyInfoID" id="addCheckinCharge_elderlyInfoID">  
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addCheckinCharge_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addCheckinCharge_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		 <th>${message("yly.common.charge.invoiceNo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="invoiceNo" validtype="length[0,30]"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.paymentType")}:</th>
	    		<td colspan=3>
	    			<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="paymentType" id="status" panelHeight="100px"
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
	    	
	    	<tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.record.deposit")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" name="deposit.depositAmount" data-options="required:true,min:0,precision:2" />
					    		</td>
					    		<th>${message("yly.remark")}:</th>
					    		<td width="250px">
					    			 <input class="easyui-textbox" name="deposit.remark" style="width:200px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  		
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	 </tr>
	    	 <tr>
	    		<td colspan=4>
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input id="bedNursePeriodStartDate" name="bedNurseCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;"/>
					    		    ──
				    			    <input id="bedNursePeriodEndDate" name="bedNurseCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" readonly=true/>    
					    			<span class="margin-left-20" id="billDay"></span>
					    		</td>
					    		
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.bed")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" name="bedNurseCharge.bedAmount" data-options="required:true,min:0,precision:2" />
					    			<span class="margin-left-20" id="bedType"></span><span class="margin-left-10" id="bedPerMonth"></span><span class="margin-left-10" id="bedPerDay"></span>
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.nurse")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" name="bedNurseCharge.nurseAmount" data-options="required:true,min:0,precision:2" />
					    		     <span class="margin-left-20" id="nurseLevel"></span><span class="margin-left-10" id="nurseLevelPerMonth"></span><span class="margin-left-10" id="nurseLevelPerDay"></span>
					    		</td>
					    		
	    			  		</tr>
	    			  		
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" name="bedNurseCharge.remark" style="width:400px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td colspan=4>
	    		     <div><input type="checkbox" id="isMonthlyMeal" style="width:20px;"><span>${message("yly.charge.isMonthly.meal")}</span></div>
	    			  <fieldset id="monthlyMeal" style="display:none"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input id="mealPeriodStartDate" name="mealCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;"/>
					    		    ──
				    			    <input id="mealPeriodEndDate" name="mealCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" readonly=true/>    
					    		</td>
					    		
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.mealCharge.mealType")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="mealTypeId" id="mealType" panelHeight="150px" data-options="required:true,editable:false" />   
					    		     <span class="margin-left-20" id="mealTypeVal"></span><span class="margin-left-10" id="mealPerMonth"></span><span class="margin-left-10" id="mealPerDay"></span>
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" name="mealCharge.mealAmount" data-options="required:true,min:0,precision:2" />
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" name="mealCharge.remark" style="width:400px" validtype="length[0,50]"/> 
					    		</td>
	    			  		</tr>
	    			  	</table>
	    			  </fieldset>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan=4></td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.totalAmount")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-numberbox" name="totalAmount" data-options="required:true,min:0,precision:2" readonly="true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" name="remark" style="width:450px" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  

