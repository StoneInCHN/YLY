<div>
 			<fieldset>
			    <legend>护理员安排查询</legend>
			     <form id="common_nurseArrangement_search_form" class="search-form">
			    	<div class="search-item">
					   <label>护理名称:</label>
					   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="nurseName" name="nurseNameSearch" validtype="length[0,50]" style="width:110px;"/>
					</div>
					<div class="search-item">
					    <label> 护理日期从:</label>
					    <input type="text" class="Wdate" id="common_nurseStartDate" name="nurseStartDateSearch"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'common_nurseEndDate\')}'});" />
					</div>
					<div class="search-item">
					    <label>${message("yly.to")}:</label>
					   	<input type="text" class="Wdate" id="common_nurseEndDate"  name="nurseEndDateSearch"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'common_nurseStartDate\')}'});"/>
					</div>
				</form>
				<div class="search-item">
			  	  <button id="common_nurseArrangement_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
			    </div>
			</fieldset>	  
</div>
<table id="common-nurseArrangement-table-list"></table>




