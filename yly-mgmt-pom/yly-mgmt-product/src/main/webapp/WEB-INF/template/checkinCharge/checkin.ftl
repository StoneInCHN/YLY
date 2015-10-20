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
	    		<th>${message("yly.common.elderly")}</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${message("yly.common.please.select")}" name="elderlyInfoName" id="addCheckinCharge_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addCheckinCharge_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.charge.money")}</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="checkinAmount" data-options="required:true,min:0,precision:2" /> 
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
	    			<input class="easyui-combobox" type="text" prompt="${message("yly.common.please.select")}" name="paymentType" id="status" panelHeight="100px"
			   		data-options="required:true,editable:false,
					valueField: 'value',
					textField: 'label',
					data: [{
						value: 'CASH',
						label: '${message("yly.common.charge.paymentType.CASH")}'
					},{
						value: 'checkin',
						label: '${message("yly.common.charge.paymentType.checkin")}'
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
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			 <textarea  cols=40 rows=5 type="text" name="remark" maxlength="50"></textarea> 
	    		</td>
	    	</tr>
	    </table>
    </form>
</div>  

