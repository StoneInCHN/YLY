<script src="${base}/resources/modules/role.js"></script>
<div>
	  <fieldset>
	    <legend>角色查询</legend>
	    <form id="role_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 角色:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" validtype="length[0,20]" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="role_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="common-roles-table-list"></table>









