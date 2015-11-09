<div>
	  <fieldset>
	    <legend>${message("yly.drugsInfo.search")}</legend>
	    <form id="search-form" class="search-form">
	    <div class="search-item">
			    <label> ${message("yly.drugsInfo.search.name")}:</label>
			    <input type="text" class="easyui-textbox" id="drugName" name="drugName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.drugsInfo.search.time")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="prescriptionAddDrugs-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id ="drgusAll-table-list"></table>
