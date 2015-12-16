<script src="${base}/resources/modules/checkedInElderly.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.search")}</legend>
	    <form id="checkedInElderly_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderlyInfo.identifier")}:</label>
			    <input class="easyui-textbox" type="text" id="identifier" name="identifier" validtype="length[0,15]"  style="width:60px;"/>
			    <input type="hidden" id="identifierHidden" name="identifierHidden">
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" type="text" id="name" name="name" validtype="length[0,15]" style="width:75px;"/> 
			    <input type="hidden" id="nameHidden" name="nameHidden">
			</div>
	    	
	    	<div class="search-item">
				<label> ${message("yly.elderlyInfo.elderlyStatus")}:</label>
	    		<input class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: 'IN_NURSING_HOME',
					value: '${message("yly.elderlyInfo.elderlyStatus.in_nursing_home")}'
				},{
					label: 'OUT_NURSING_HOME',
					value: '${message("yly.elderlyInfo.elderlyStatus.out_nursing_home")}'
				},{
					label: 'IN_PROGRESS_CHECKIN',
					value: '${message("yly.elderlyInfo.elderlyStatus.in_progress_checkin")}'
				},{
					label: 'IN_PROGRESS_CHECKOUT',
					value: '${message("yly.elderlyInfo.elderlyStatus.in_progress_checkout")}'
				},{
					label: 'DEAD',
					value: '${message("yly.elderlyInfo.elderlyStatus.dead")}'
				}],
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:120"  id="elderlyStatus" name="elderlyStatus" style="width:100px;"/>
				<input type="hidden" id="elderlyStatusHidden" name="elderlyStatusHidden">
			</div>
	    
	    
			<div class="search-item">
			    <label> ${message("yly.elderlyInfo.checkinDate")}:</label>
			    <input type="text" class="Wdate" id="beHospitalizedBeginDate" name="beHospitalizedBeginDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'beHospitalizedEndDate\')}'});" />
			    <input type="hidden" id="beHospitalizedBeginDateHiden" name="beHospitalizedBeginDateHiden">
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="beHospitalizedEndDate"  name="beHospitalizedEndDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beHospitalizedBeginDate\')}'});"/>
			   	<input type="hidden" id="beHospitalizedEndDateHidden" name="beHospitalizedEndDateHidden">
			</div>
		</form>
		<div class="search-item">
	  	  <button id="checkedInElderly_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="checkedInElderly_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="checkedInElderly_manager_tool.exportData();">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="checkedInElderly-table-list"></table>
<div id="checkedInElderlyDetail"></div>
