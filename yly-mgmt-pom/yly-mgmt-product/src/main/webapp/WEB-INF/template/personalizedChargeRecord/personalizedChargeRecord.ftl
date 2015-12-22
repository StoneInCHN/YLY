<script src="${base}/resources/modules/personalizedChargeRecord.js"></script>

<div>
	<fieldset>
	    <legend>${message("yly.charge.record.search")}</legend>
	    <form id="personalizedChargeRecord_search_form" class="search-form">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.elderlyName")}" id="name" name="realName"/>
			   	<input type="hidden" id="nameHidden" name="nameHidden">
			</div>
			<div class="search-item">
			    <label>${message("yly.charge.record.elder.identifier")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.identifier")}" id="identifier" name="identifier"/>
			   	<input type="hidden" id="identifierHidden" name="identifierHidden">
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
					<input type="hidden" id="statusHidden" name="statusHidden">
			</div>
			<div class="search-item">
			    <label> ${message("yly.charge.record.date")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" placeholder="${message("yly.common.prompt.beginDate")}" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			    <input type="hidden" id="beginDateHidden" name="beginDateHidden">
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate" name="endDate" placeholder="${message("yly.common.prompt.endDate")}" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			   	<input type="hidden" id="endDateHidden" name="endDateHidden">
			</div>
		</form>
		<div class="search-item">
	  	  <button id="personalizedChargeRecord_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	 </fieldset>
</div>
<div id="personalizedCharge_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportData('personalizedChargeRecord','personalizedChargeRecord_search_form');">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="personalizedChargeRecord_table_list"></table>
<div id="personalizedChargeRecordDetail"></div>





