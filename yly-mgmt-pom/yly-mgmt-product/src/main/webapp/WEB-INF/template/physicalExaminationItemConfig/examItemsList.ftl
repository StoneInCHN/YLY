<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/js/jquery.edatagrid.js"></script>
<script src="${base}/resources/modules/physicalExaminationItemConfig.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.physicalExaminationItemConfig.search")}</legend>
	    <form id="physicalExaminationItemsList-search-form" class="search-form">
	    	<input type="hidden" name="configKey" value="PHYSICALEXAMITEM"/>
	    	<div class="search-item">
			    <label> ${message("yly.physicalExaminationItemConfig.name")}:</label>
			    <input type="text" class="easyui-textbox" id="searchItemName" name="searchItemName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.physicalExamination.physicalExaminationDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="physicalExaminationItemList-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="physicalExaminationItemsList-table-list"></table>




