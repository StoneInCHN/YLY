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
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.tenantAccount.staffID")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="userName" id= "userName"  data-options="required:true,editable:false"/>
	    		</td>
	    		<th>${message("yly.tenantAccount.realName")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="realName" id= "realName"  data-options="required:true,editable:false"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.userName")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="userName" id= "userName"  data-options="required:true,editable:false"/>
	    		</td>
	    		<th>${message("yly.common.isSystem")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="isSystem" id= "isSystem"  data-options="required:true,editable:false"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.accoutStatus")}:</th>
	    		<td>
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
	    		<th>${message("yly.tenantAccount.tenantUser")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="tenantUser" id= "tenantUser"  data-options="required:true,editable:false"/>
	    		</td>
	    		<th>${message("yly.tenantAccount.role")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="role" id= "role"  data-options="required:true,editable:false"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editTenantAccount"></div>
<div id="tenantAccountDetail"></div>




