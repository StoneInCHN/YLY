<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/js/jquery.edatagrid.js"></script>
<script src="${base}/resources/modules/physicalExaminationItemConfig.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.physicalExaminationItemConfig.search")}</legend>
	    <form id="physicalExaminationItemConfig-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.physicalExaminationItemConfig.name")}:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.physicalExamination.physicalExaminationDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="physicalExaminationItemConfig-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="physicalExaminationItemConfig-table-list"></table>
<div id="physicalExaminationItemConfig_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="physicalExaminationItemConfig_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="physicalExaminationItemConfig_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="physicalExaminationItemConfig_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addPhysicalExaminationItemConfig"> 
	<form id="addPhysicalExaminationItemConfig_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="configValue" id="configValue" panelHeight="150px" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.configOrder")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" name="configOrder" id="configOrder" panelHeight="150px" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.physicalExaminationItemConfig.isEnable")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("yly.common.enable")}'
				     },{
				      label: 'false',
				      value: '${message("yly.common.disable")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="isEnable" style="width:110px;"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editPhysicalExaminationItemConfig"></div>
<div id="physicalExaminationItemConfigDetail"></div>




