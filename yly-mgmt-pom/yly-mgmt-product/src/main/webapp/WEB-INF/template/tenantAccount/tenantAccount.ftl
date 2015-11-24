<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantAccount.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.tenantAccount.search")}</legend>
	    <form id="tenantAccount-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.tenantAccount.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.tenantUser.staffStatus")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("yly.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("yly.tenantAccount.accoutStatus.locked")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="accountStatusSearch" id="accountStatusSearch" style="width:110px;"/>
			</div>
			
		</form>
		<div class="search-item">
	  	  <button id="tenantAccount-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantAccount-table-list"></table>
<div id="tenantAccount_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantAccount_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantAccount_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="tenantAccount_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantAccount"> 
	<form id="addTenantAccount_form" method="post" class="form-table">
	<input type="hidden" name="tenantUserID" id="addTenantAccountUserID">
	<input type="hidden" name="roleID" id="addTenantAccountRoleID">  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.tenantAccount.staffID")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="staffID" id= "staffID"   data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="userName" id= "userName"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.password")}:</th>
	    		<td colspan='2'>
	    			 <input type="password" class="easyui-textbox" name="password" id= "password"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.accoutStatus")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ACTIVED',
				      value: '${message("yly.tenantAccount.accoutStatus.active")}'
				     },{
				      label: 'LOCKED',
				      value: '${message("yly.tenantAccount.accoutStatus.locked")}'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="accoutStatus" style="width:110px;"/>
	    		</td>
	    		</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.tenantUser")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="addTenantAccountUser" id= "addTenantAccountUser"  />
	    		</td>
	    		<td>
	    			<a href="#" id="tenant_user_search_btn" class="easyui-linkbutton" onclick="searchTenantUser('addTenantAccountUser')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    	<th>${message("yly.tenantAccount.role")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="addTenantAccountRole" id= "addTenantAccountRole"  />
	    		</td>
	    		<td>
	    			<a href="#" id="role_search_btn" class="easyui-linkbutton" onclick="searchRoles('addTenantAccountRole')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editTenantAccount"></div>
<div id="tenantAccountDetail"></div>




