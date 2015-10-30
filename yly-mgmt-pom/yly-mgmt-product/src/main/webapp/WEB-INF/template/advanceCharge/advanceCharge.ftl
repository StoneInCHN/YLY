<script src="${base}/resources/modules/advanceCharge.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.charge.advanceCharge.account")}</legend>
	    <form id="advanceCharge_search_form" class="search-form">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" prompt="${message("yly.common.prompt.input.elderlyName")}" type="text" name="name" id="realName"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.charge.record.elder.identifier")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.identifier")}" name="identifier" id="identifier"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="advanceCharge_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="advanceCharge_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="advanceCharge_manager_tool.add();">${message("yly.charge.advance.add")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="advanceCharge_table_list"></table>
<div id="addAdvanceCharge">
	<form id="addAdvanceCharge_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyInfoID" id="addAdvanceCharge_elderlyInfoID">  
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly")}</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addAdvanceCharge_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addAdvanceCharge_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.money")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="advanceAmount" data-options="required:true,min:0,precision:2" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.invoiceNo")}</th>
	    		<td>
	    			 <input class="easyui-textbox" name="invoiceNo" validtype="length[0,30]"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.paymentType")}</th>
	    		<td>
	    			<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="paymentType" id="status" panelHeight="70px"
			   		data-options="required:true,editable:false,
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'CASH',
						label: '${message("yly.common.charge.paymentType.CASH")}'
					},{
						value: 'CARD',
						label: '${message("yly.common.charge.paymentType.CARD")}'
					}]" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			 <textarea  cols=40 rows=5 type="text" name="remark" maxlength="50"></textarea> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  


<div>
	  <fieldset>
	  	<legend>${message("yly.charge.advanceCharge.details")}</legend>
	    <form id="advanceChargeDetails_search_form" class="search-form">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" prompt="${message("yly.common.prompt.input.elderlyName")}" type="text" name="realName" id="realName"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.charge.record.elder.identifier")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.identifier")}" name="identifier" id="identifier"/>
			</div>
			<div class="search-item">
			    <label>${message("yly.common.charge.budgetType")}:</label>
			   	<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="budgetType" id="budgetType" panelHeight="50px"
			   	data-options="
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'INCOME',
						label: '${message("yly.common.charge.budgetType.INCOME")}'
					},{
						value: 'COST',
						label: '${message("yly.common.charge.budgetType.COST")}'
					}]" />
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
	  	  <button id="advanceChargeDetails_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="advanceChargeDetails_table_list"></table>
<div id="advanceChargeDetail"></div>






