<div>
	  <fieldset>
	    <legend>账单查询</legend>
	   <form id="billing_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderlyInfo.identifier")}:</label>
			    <input class="easyui-textbox" type="text" name="identifier" validtype="length[0,15]"  style="width:60px;"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" type="text" name="name" validtype="length[0,15]" style="width:75px;"/> 
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
	  	  <button id="billing_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="billingSearch-table-list"></table>
