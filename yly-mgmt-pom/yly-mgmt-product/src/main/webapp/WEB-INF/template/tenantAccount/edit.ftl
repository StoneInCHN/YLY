<form id="editTenantAccount_form" method="post">   
		<input type="hidden" name="id" value= "${tenantAccount.id}"/>
		<input type="hidden" name="enPassword" value= "${tenantAccount.password}"/>
		<input type="hidden" name="staffID" value= "${tenantAccount.staffID}"/>
	    <input type="hidden" name="tenantUserID" id="editTenantAccountUserID" value="${tenantAccount.tenantUser.id}"/>
		<input type="hidden" name="roleID" id="editTenantAccountRoleID" value="${roleInfo.id}"/>  
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.tenantAccount.staffID")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" value="${tenantAccount.staffID}" name="staffID" id= "staffID"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.userName")}:</th>
	    		<td colspan="2">
	    			 <input type="text" class="easyui-textbox" name="userName" value="${tenantAccount.userName}" id= "userName"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.password")}:</th>
	    		<td colspan='2'>
	    			 <input type="password" class="easyui-textbox" name="password" id= "password" value="${tenantAccount.password}"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.accoutStatus")}:</th>
	    		<td colspan="2">
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
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100" id="editAccountStatus"  name="accoutStatus" style="width:110px;" data-value="${tenantAccount.accoutStatus}"/>
				     
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.tenantUser")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="editTenantAccountUser" id= "editTenantAccountUser"  value="${tenantAccount.tenantUser.realName}"/>
	    		</td>
	    		<td>
	    			<a href="#" id="tenant_user_search_btn" class="easyui-linkbutton" onclick="searchTenantUser('editTenantAccountUser')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.role")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="addTenantAccountRole" value = "${roleInfo.name}" id= "editTenantAccountRole"  />
	    		</td>
	    		<td>
	    			<a href="#" id="role_search_btn" class="easyui-linkbutton" onclick="searchRoles('editTenantAccountRole')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    </table>
</form>



