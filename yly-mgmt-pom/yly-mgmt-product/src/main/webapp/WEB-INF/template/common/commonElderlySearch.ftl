<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.search")}</legend>
	   <form id="common_elderlyinfo_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderlyInfo.identifier")}:</label>
			    <input class="easyui-textbox" type="text" name="identifier" validtype="length[0,15]"  style="width:60px;"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" type="text" name="name" validtype="length[0,15]" style="width:75px;"/> 
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
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:120"  name="elderlyStatus" style="width:100px;"/>
			</div>
	    
	    
			<div class="search-item">
			    <label> ${message("yly.elderlyInfo.checkinDate")}:</label>
			    <input type="text" class="Wdate" id="beHospitalizedBeginDate" name="beHospitalizedBeginDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'beHospitalizedEndDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="beHospitalizedEndDate"  name="beHospitalizedEndDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beHospitalizedBeginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="common_elderlyinfo_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="common_elderlyInfoSearch-table-list"></table>
