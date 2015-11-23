<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/donateRecord.js"></script>
<script src="${base}/resources/modules/donateDetail.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.donateRecord.search")}</legend>
	    <form id="donateRecord-search-form" class="search-form">
	    <div class="search-item">
			    <label>${message("yly.donateRecord.search.name")}:</label>
			    <input type="text" class="easyui-textbox" id="donatorName" name="donatorName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.donateRecord.donateTime")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="donateRecord-table-list"></table>
<div id="donateRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="donateRecord_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="donateRecord_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="donateRecord_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addDonateRecord"> 
	<form id="addDonateRecord_form" method="post" class="form-table">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.donateRecord.donatorName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorName" data-options="required:true" validtype="length[0,15]"/>   
	    		</td>
	    	
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    			<select id="donatorGender" class="easyui-combobox"  name="donatorGender" style="width:60px;" >   
						<option value="MALE">${message("yly.gender.male")}</option>
						<option value="FEMALE">${message("yly.gender.female")}</option> 
				  </select>     
	    		</td>
	    	    <th>${message("yly.donateRecord.donatorPhone")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorPhone"  validtype="mobile"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.donateRecord.donateTime")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="donateTime" name="donateTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>${message("yly.donateRecord.donatorAddress")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" type="text" name="donatorAddress"  style="width:100%" validtype="length[0,50]"/>   
	    		</td>
	    	</tr>
	    	<tr >
    			<th>${message("yly.donateRecord.donateDescription")}:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="donateDescription" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>${message("yly.remark")}:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="remark" data-options="multiline:true,height:90,width:550" validtype="length[0,50]"/> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDonateRecord"></div>
<div id="donateRecordDetail"></div>
<div id="donateDetail_manager_tool" style ="display:none">

	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="donateDetail_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="donateDetail_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="donateDetail_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
	<table id="donateDetail-table-list"></table>
</div>

<div id="donateDetail"></div>
<div id="addDonateDetail"></div>
<div id="editDonateDetail"></div>


