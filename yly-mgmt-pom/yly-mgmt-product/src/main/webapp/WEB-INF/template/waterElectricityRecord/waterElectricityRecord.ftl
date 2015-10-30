<script src="${base}/resources/modules/waterElectricityRecord.js"></script>
<div>
	  <fieldset>
	    <legend>水电抄表记录查询</legend>
	    <form id="waterElectricityRecord_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 房间编号查询:</label>
			    <input type="text" class="easyui-textbox" id="room_roomNumber" name="room.roomNumber" style="width:85px;" />
			</div>
			<div class="search-item">
				<label> 房间名称查询:</label>
	    		<input type="text" class="easyui-textbox" id="room_roomName" name="room.roomName" style="width:85px;" />
			</div>
			<div class="search-item">
				<label> 抄表人查询:</label>
	    		<input type="text" class="easyui-textbox" id="operator" name="operator" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 活动发生时间从:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="waterElectricityRecord_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="waterElectricityRecord-table-list"></table>
<div id="waterElectricityRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="waterElectricityRecord_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="waterElectricityRecord_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="waterElectricityRecord_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addWaterElectricityRecord">
	<form id="addWaterElectricityRecord_form" method="post" class="form-table">   
	   	  <table class="table table-striped table-bordered">
	   	  	<table class="table table-striped table-bordered">
	   	  	<tr>
				<th>${message("yly.bed.room")}:</th>
				<td>
				    <input class="easyui-combotree" type="text" id="addBed_form_roomId" name="roomId" data-options="required:true,editable:false" />   
				</td>
			</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="operator" validtype="length[0,15]" data-options="required:true" style="width:100px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordStartDate")}:</th>
	    		<td>
	    			<input type="text" class="easyui-datebox"  name="recordStartDate" validtype="length[0,30]" style="width:150px;" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.recordEndDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox"  name="recordEndDate" validtype="length[0,30]" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:280" />
	    		</td>
	    	</tr>
	    	</table>
	    	<fieldset>
	    	<legend>水用量抄表记录</legend>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterCount")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox"  name="waterCount" validtype="length[0,30]" data-options="required:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.waterDerate")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="waterDerate" data-options="required:true"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	    	<fieldset>
	    	<legend>电用量抄表记录</legend>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityCount")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox"  name="electricityCount" validtype="length[0,30]" data-options="required:true" style="width:150px;" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.waterElectricityRecord.electricityDerate")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="electricityDerate" data-options="required:true"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	    </table>
	</form>
	</div>
<div id="editwaterElectricityRecord"></div> 
<div id="waterElectricityRecordDetail"></div>




