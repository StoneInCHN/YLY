<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/donateRecord.js"></script>
<script src="${base}/resources/modules/donateDetail.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>药品查询</legend>
	    <form id="donateRecord-search-form" class="search-form">
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
<table id="donateRecord-table-list"></table>
<div id="donateRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="donateRecord_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="donateRecord_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="donateRecord_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addDonateRecord"> 
	<form id="addDonateRecord_form" method="post" class="form-table">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>捐赠人姓名:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorName" data-options="required:true" />   
	    		</td>
	    	
	    		<th>性别:</th>
	    		<td>
	    			<select id="donatorGender" class="easyui-combobox"  name="donatorGender" style="width:60px;">   
						<option value="MALE">男</option>
						<option value="FEMALE">女</option> 
				  </select>     
	    		</td>
	    	    <th>捐赠人电话:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="donatorPhone"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>时间:</th>
	    		<td>
	    			 <!--<input class="easyui-textbox" type="text" name="donatorTime" />-->
	    			 <input type="text" class="Wdate" id="donateTime" name="donateTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>捐赠人地址:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" type="text" name="donatorAddress"  style="width:100%"/>   
	    		</td>
	    	</tr>
	    	<tr >
    			<th>捐赠描述:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="donateDescription" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>备注:</th>
	    		<td colspan="6">
	    			  <input class="easyui-textbox" type="text" name="remark" data-options="multiline:true,height:90,width:550" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDonateRecord"></div>
<div id="donateRecordDetail"></div>
<div id="donateDetail_manager_tool" style ="display:none">

	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="donateDetail_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="donateDetail_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="donateDetail_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
	<table id="donateDetail_table_list"></table>
</div>

<div id="donateDetail"></div>
<div id="addDonateDetail"></div>
<div id="editDonateDetail"></div>


