<script src="${base}/resources/modules/elderlyStuffDeposit.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.stuffDeposit.search")}</legend>
	    <form id="elderlyStuff_search_form" class="search-form">
			<div class="search-item">
			   <label>${message("yly.elderlyInfo.stuffDeposit.elderlyName")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="elderlyName" name="keysOfElderlyName" validtype="length[0,15]" style="width:110px;"/>
			</div>		    
			<div class="search-item">
			   <label>${message("yly.elderlyInfo.stuffDeposit.name")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="stuffName" name="keysOfStuffName" validtype="length[0,30]" style="width:110px;"/>
			</div>
			<div class="search-item">
			   <label>${message("yly.elderlyInfo.stuffDeposit.stuffNumer")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="stuffNumber" name="keysOfStuffNumber" validtype="length[0,15]" style="width:110px;"/>
			</div>
			<p/>
			<div class="search-item">
			    <label> ${message("yly.stuffDeposit.putin.date.from")}:</label>
			    <input type="text" class="Wdate" id="beginPutInDate" name="beginPutInDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endPutInDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endPutInDate"  name="endPutInDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginPutInDate\')}'});"/>
			</div>
			<div class="search-item">&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="search-item">
			    <label> ${message("yly.stuffDeposit.takeaway.date.from")}:</label>
			    <input type="text" class="Wdate" id="beginTakeAwayDate" name="beginTakeAwayDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endTakeAwayDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endTakeAwayDate"  name="endTakeAwayDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginTakeAwayDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="elderlyStuff_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="elderlystuff_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="elderlystuff_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"  plain=true onclick="elderlystuff_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  onclick="elderlystuff_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="stuffDeposit-table-list"></table>
<div id="addElderlyStuffDeposit">
	<form id="addelderlyStuff_form" method="post" class="form-table">   
		<input type="hidden" name="elderlyInfoID" id="elderlyname1ID">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="name" name="name" validtype="length[0,30]" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.count")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" id="count" name="count" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>	    	
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.stuffNumer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="stuffNumber" validtype="length[0,15]" name="stuffNumber" data-options="required:true" style="width:85px;" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.status")}:</th>
	    		<td>   					
	    		<input class="easyui-combobox" data-options="
	    			valueField: 'label',
					textField: 'value',
						data: [{
							label: 'PUT_IN',
							value: '${message("yly.elderlyInfo.stuffDeposit_PUT_IN")}'
						},{
							label: 'TAKE_ALWAY',
							value: '${message("yly.elderlyInfo.stuffDeposit_TAKE_ALWAY")}'
						}],
						prompt:'${message("yly.common.please.select")}'" panelHeight="50"  id="stuffDepositStatus" name="stuffDepositStatus" data-options="required:true" style="width:85px;" />
	    		</td>
	    	</tr>	    	

	    	<tr id="putinDateTR">
	    		<th>${message("yly.elderlyInfo.stuffDeposit.inputDate")}:</th>
	    		<td>
	    			  <input class="Wdate" type="text" id="putinDate" name="putinDate" data-options="required:true,editable:true,width:110" readonly="readonly" onclick="WdatePicker({});"  /> 
	    		</td>
	    	</tr>
	    	<tr id="takeAlwayDateTR">
	    		<th>${message("yly.elderlyInfo.stuffDeposit.takeAwayDate")}:</th>
	    		<td>
	    			  <input class="Wdate" type="text" id="takeAlwayDate" name="takeAlwayDate" data-options="editable:true,width:110" readonly="readonly" onclick="WdatePicker({});"  /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator" validtype="length[0,15]" data-options="required:true,editable:true" style="width:85px;"/> 
	    		</td>
	    	</tr>	    		    	
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.elderlyName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="elderlyname1" name="elderlyname" data-options="required:true,editable:false" style="width:85px;" />   
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyname1')" iconCls="icon-search" plain=true"></a> 
	    		</td>
	    	</tr>
			<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.remark")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="remark" name="remark" validtype="length[0,150]" data-options="multiline:true,required:true,height:110,width:320" />   
	    		</td>
	    	</tr>	    
	    </table>
	</form>
</div>
<div id="editStuffDeposit"></div>  
<div id="showStuffDeposit"></div> 




