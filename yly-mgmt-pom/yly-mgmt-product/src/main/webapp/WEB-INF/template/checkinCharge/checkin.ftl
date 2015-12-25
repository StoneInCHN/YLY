<script src="${base}/resources/modules/checkinCharge.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.charge.record.search")}</legend>
	    <form id="checkinCharge_search_form" class="search-form">
	    	<input type="hidden" name="isCreateTime" value="true">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" prompt="${message("yly.common.prompt.input.elderlyName")}" type="text" name="realName" id="realName"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.charge.record.elder.identifier")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.identifier")}" name="identifier" id="identifier"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.common.charge.status")}:</label>
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
			<div class="search-item">
			    <label> ${message("yly.common.charge.oprTime")}:</label>
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
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="checkinCharge_manager_tool.editBill();">${message("yly.bill.editBill")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="checkinCharge_manager_tool.adjustment();">${message("yly.bill.adjustment")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="checkinCharge_table_list"></table>
<div id="checkinDetail"></div>
<div id="editCheckinBill"></div>
<div id="addCheckinAdjust">
	<form id="addCheckinAdjust_form" method="post" class="form-table">
		 <table class="table table-striped">
		 	<input type="hidden" name="billId" id="billId">
		 	<tr>
	    		<th>${message("yly.bill.adjustment.cause")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="adjustmentCause" id="adjustmentCause" panelHeight="100px" style="width:130px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bill.adjustment.amount")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" id="adjustmentAmount" name="adjustmentAmount" data-options="required:true,precision:2"/>
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
<div id="addCheckinCharge">
	<form id="addCheckinCharge_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyInfoID" id="addCheckinCharge_elderlyInfoID">  
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addCheckinCharge_elderlyInfo" style="width:180px;" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addCheckinCharge_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td colspan="4">
	    			  <fieldset> 
	    			  	<legend>${message("yly.charge.record.deposit")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="checkin_deposit" name="deposit.depositAmount" data-options="required:true,min:0,precision:2" />
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
					    			<input id="bedNursePeriodStartDate" name="bedNurseCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false"/>
					    		    ──
				    			    <input id="bedNursePeriodEndDate" name="bedNurseCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" readonly=true/>    
					    			<span class="margin-left-20" id="billDay"></span>
					    		</td>
					    		
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.bed")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="chargein_bedAmount" name="bedNurseCharge.bedAmount" data-options="required:true,min:0,precision:2,editable:false" />
					    			<span class="margin-left-20" id="bedType"></span><span class="margin-left-10" id="bedPerMonth"></span><span class="margin-left-10" id="bedPerDay"></span>
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.charge.record.nurse")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="chargein_nurseAmount" name="bedNurseCharge.nurseAmount" data-options="required:true,min:0,precision:2,editable:false" />
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
	    		     <div><input type="checkbox" name="isMonthlyMeal" value="true" id="isMonthlyMeal" style="width:20px;"><span>${message("yly.charge.isMonthly.meal")}</span></div>
	    			  <fieldset id="monthlyMeal" style="display:none"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}:</legend>
	    			  	<table class="table table-striped">
	    			  		<tr>
					    		<th>${message("yly.common.charge.period")}:</th>
					    		<td>
					    			<input id="mealPeriodStartDate" name="mealCharge.periodStartDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false,disabled:true"/>
					    		    ──
				    			    <input id="mealPeriodEndDate" name="mealCharge.periodEndDate" type="text" class="easyui-datebox" style="width:100px;" data-options="editable:false,disabled:true" readonly=true/>    
					    		</td>
					    		<input type="hidden" id="periodMonMeal">
					    		<input type="hidden" id="periodDayMeal">
					    	</tr>
	    			  		<tr>
	    			  			<th>${message("yly.mealCharge.mealType")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="mealTypeId" id="mealType" panelHeight="150px" data-options="editable:false,disabled:true" />   
					    		     <span class="margin-left-20" id="mealTypeVal"></span><span class="margin-left-10" id="mealPerMonth"></span><span class="margin-left-10" id="mealPerDay"></span>
					    		</td>
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.common.charge.money")}:</th>
					    		<td>
					    			 <input class="easyui-numberbox" id="chargein_mealAmount" name="mealCharge.mealAmount" data-options="min:0,precision:2,editable:false,disabled:true" />
					    		</td>
					    		
	    			  		</tr>
	    			  		<tr>
	    			  			<th>${message("yly.remark")}:</th>
					    		<td>
					    			 <input class="easyui-textbox" id="mealRemark" name="mealCharge.remark" style="width:400px" validtype="length[0,50]" data-options="disabled:true"/> 
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
	    			 <input class="easyui-numberbox" id="chargein_totalAmount" name="totalAmount" data-options="required:true,min:0,precision:2,editable:false"/>
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

