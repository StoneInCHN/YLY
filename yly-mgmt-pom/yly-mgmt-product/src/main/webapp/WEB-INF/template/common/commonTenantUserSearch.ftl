<div>
	  <fieldset>
	    <legend>${message("yly.tenantUser.search")}</legend>
	    <form id="tenantUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.tenantUser.search.realName")}:</label>
			    <input type="text" class="easyui-textbox" id="realName" name="realNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.tenantUser.search.department")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserDepartment-search" name="departmentSearchId" data-options="prompt:'${message("yly.common.please.select")}'"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.tenantUser.search.position")}:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserPosition-search" name="positionSearchId"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.tenantUser.staffStatus")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '${message("yly.tenantUser.staffStatus.inService")}'
				     },{
				      label: 'OUTSERVICE',
				      value: '${message("yly.tenantUser.staffStatus.outService")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="staffStatusSearch" style="width:110px;"/>
			    
			</div>
		</form>
		<div class="search-item">
	  	  <button id="tenantUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="common-tenantUser-table-list"></table>




