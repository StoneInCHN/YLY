<script src="${base}/resources/modules/reportNurseLevelStatistics.js"></script>
<div>
	  <fieldset>
	    <form id="report_nurse_level_search_form" class="search-form">
			<div class="search-item">
			    <label> 筛选时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="report_nurse_level_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="reportNurseLevelStatistics">
	<div id="nurseLevelStatisticsReportId" style="height:300px;width: 680px;"></div>
	<table id = "reportNurseLevel-table-list" class="table table-striped" >   
	</table>
</div>