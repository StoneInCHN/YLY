<script src="${base}/resources/modules/bedNurseChargeRecord.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.charge.record.search")}</legend>
	    <form id="bedNurseChargeRecord_search_form" class="search-form">
	        <div class="search-item">
			    <label>${message("yly.charge.record.elder.name")}:</label>
			   	<input class="easyui-textbox" type="text" prompt="${message("yly.common.prompt.input.elderlyName")}" name="realName" id="realName"/>
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
			    <label> ${message("yly.charge.record.date")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" placeholder="${message("yly.common.prompt.beginDate")}" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate" name="endDate" placeholder="${message("yly.common.prompt.endDate")}" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="bedNurseChargeRecord_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>

<table id="bedNurseChargeRecord_table_list"></table>
<div id="bedNurseChargeRecordDetail"></div>





