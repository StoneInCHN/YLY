<script type="text/javascript"  src="${base}/resources/modules/building.js"></script>
<div>
	  <fieldset>
	    <legend>楼宇查询</legend>
	    <form id="building_search_form" class="search-form">
			<div class="search-item">
			    <label> 查询时间从:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="building_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="building-table-list"></table>
<div id="building_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="building_manager_tool.add();">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"  plain=true onclick="building_manager_tool.edit();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  onclick="building_manager_tool.remove();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addBulid">
	<form id="addBulid_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>楼宇名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="buildingName" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="description" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editBuild"></div>  




