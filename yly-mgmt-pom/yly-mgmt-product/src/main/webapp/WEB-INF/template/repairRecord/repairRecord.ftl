<script src="${base}/resources/modules/repairRecord.js"></script>
<div>
	  <fieldset>
	    <legend>维修查询</legend>
	    <form id="repairRecord_search_form" class="search-form">
	    	<!--<div class="search-item">
			    <label> 维修内容:</label>
			    <input type="text" class="easyui-textbox" id="repairContent" name="repairContent" validtype="length[0,20]" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 维修地点:</label>
			    <input type="text" class="easyui-textbox" id="repairPlace" name="repairPlace" validtype="length[0,40]" style="width:85px;" />
			</div>-->
			<div class="search-item">
			    <label> 维修人员:</label>
			    <input type="text" class="easyui-textbox" id="reportOperator" name="reportOperator" validtype="length[0,20]" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="repairRecord_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="repairRecord-table-list"></table>
<div id="repairRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="repairRecord_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="repairRecord_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="repairRecord_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addRepairRecord">
	<form id="addRepairRecord_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.repairRecord.repairContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairContent" validtype="length[0,20]" data-options="required:true" style="width:200px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.reportOperator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="reportOperator" validtype="length[0,6]" data-options="required:true" style="width:120px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairPlace")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairPlace" validtype="length[0,40]" data-options="required:true" style="width:200px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.repairRecord.repairOperator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="repairOperator" validtype="length[0,6]" data-options="required:true" style="width:120px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:220" />
	    		</td>
	    	</tr>
	    </table>
	</form>
	</div>
<div id="editRepairRecord"></div> 
<div id="repairRecordDetail"></div>





