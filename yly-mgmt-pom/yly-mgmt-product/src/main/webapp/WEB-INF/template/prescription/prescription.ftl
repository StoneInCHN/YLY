<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/prescription.js"></script>
<script src="${base}/resources/modules/prescriptionDrugsItem.js"></script>

<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.drugsInfo.search")}</legend>
	    <form id="prescription-search-form" class="search-form">
	    <div class="search-item">
			    <label> ${message("yly.common.elderly.name")}:</label>
			    <input type="text" class="easyui-textbox" id="elderNameSearch" name="elderNameSearch" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.drugsInfo.search.time")}:</label>
			    <input type="text" class="Wdate" id="prescriptionBeginDate" name="prescriptionBeginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="prescriptionEndDate"  name="prescriptionEndDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="prescription-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="prescription-table-list"></table>
<div id="prescription_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="prescription_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="prescription_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="prescription_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
	<div id ="addPrescription"></div>
</div>

<table id="drugsInfoSearch-table-list"></table>
<div id="editPrescription"></div>
<div id="prescriptionDetail"></div>
<div id="addPrescriptionDrugs"></div>
<div id="prescriptionDrugs"></div>


