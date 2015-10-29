<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/operationLog.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>药品查询</legend>
	    <form id="operationLog-search-form" class="search-form">
	    <div class="search-item">
			    <label> 名称查询:</label>
			    <input type="text" class="easyui-textbox" id="donatorName" name="donatorName" />
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="operationLog-table-list"></table>
<div id="operationLog_manager_tool">
	<div class="tool-filter"></div>
</div>
<div id="operationLog"></div>





