<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/fixedAssets.js"></script>
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
<table id="fixedAssets-table-list"></table>
<div id="fixedAssets_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="fixedAssets_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="fixedAssets_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="fixedAssets_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addFixedAssets"> 
	<form id="addFixedAssets_form" method="post" class="form-table">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>资产编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetNo" data-options="required:true" />   
	    		</td>
	    	
	    		<th>资产名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetName" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>存放地点（部门）:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="department" name="departmentId" />   
	    		</td>
	    	
	    		<th>数量:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetCount" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>计量单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetUnit" />   
	    		</td>
	    	
	    		<th>使用状态:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '使用中'
				     },{
				      label: 'OUTSERVICE',
				      value: '未使用'
				     },{
				      label: 'NONEED',
				      value: '不需用'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetUsage" style="width:110px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>规格型号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetSpecification"/>   
	    		</td>
	    	
	    		<th>产地:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetOrigin"  />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>制造商:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetManufacturer"  />   
	    		</td>
	    	
	    		<th>供应商:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetProvider"  />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>资产价值（RMB）:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetValue" />   
	    		</td>
	    	
	    		<th>资产类型:</th>
	    		<td>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BUILDING',
				      value: '房屋建筑物'
				     },{
				      label: 'PRODUCTION',
				      value: '生产经营'
				     },{
				      label: 'VEHICLE',
				      value: '交通工具'
				     },{
				      label: 'ELECTRONIC',
				      value: '电子设备'
				     },{
				      label: 'other',
				      value: '其他'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetType" style="width:110px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>录入日期:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="assetTime" name="assetTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>备注:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="remark" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDonateRecord"></div>
<div id="donateRecordDetail"></div>




