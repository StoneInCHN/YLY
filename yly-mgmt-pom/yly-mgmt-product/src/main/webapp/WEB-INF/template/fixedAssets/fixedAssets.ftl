<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/fixedAssets.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.fixedAssets.search")}</legend>
	    <form id="fixedAssets-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.fixedAssets.search.name")}:</label>
			    <input type="text" class="easyui-textbox" id="assetName" name="assetName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.fixedAssets.search.assetsDepartment")}:</label>
			    <input type="text" class="easyui-combobox" id="assetsDepartment-search" name="departmentSearchId"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.fixedAssets.search.time")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="fixedAssets-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="fixedAssets-table-list"></table>
<div id="fixedAssets_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="fixedAssets_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="fixedAssets_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="fixedAssets_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addFixedAssets"> 
	<form id="addFixedAssets_form" method="post" class="form-table">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetNo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetNo" data-options="required:true" validtype="length[0,40]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetName" data-options="required:true" validtype="length[0,60]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetsDepartment")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="assetsDepartment-add" name="departmentId" data-options="prompt:'${message("yly.common.please.select")}'" />   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetCount")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetCount" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetUnit" validtype="length[0,10]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetUsage")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '${message("yly.fixedAssets.assetUsage.inService")}'
				     },{
				      label: 'OUTSERVICE',
				      value: '${message("yly.fixedAssets.assetUsage.outService")}'
				     },{
				      label: 'NONEED',
				      value: '${message("yly.fixedAssets.assetUsage.noNeed")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetUsage" style="width:110px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetSpecification")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetSpecification" validtype="length[0,30]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetOrigin")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetOrigin" validtype="length[0,20]" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetManufacturer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetManufacturer" validtype="length[0,50]" />   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetProvider")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetProvider"  validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetValue")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetValue" />   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetsType")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BUILDING',
				      value: '${message("yly.fixedAssets.assetsType.buiding")}'
				     },{
				      label: 'PRODUCTION',
				      value: '${message("yly.fixedAssets.assetsType.production")}'
				     },{
				      label: 'VEHICLE',
				      value: '${message("yly.fixedAssets.assetsType.vehicle")}'
				     },{
				      label: 'ELECTRONIC',
				      value: '${message("yly.fixedAssets.assetsType.electronic")}'
				     },{
				      label: 'OTHERS',
				      value: '${message("yly.fixedAssets.assetsType.other")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetsType" style="width:110px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetTime")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="assetTime" name="assetTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="remark" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editFixedAssets"></div>
<div id="fixedAssetsDetail"></div>




