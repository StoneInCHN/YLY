<form id="tenantAccountDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.tenantAccount.staffID")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" value="${tenantAccount.staffID}" name="staffID" id= "staffID"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>	
	    		<th>${message("yly.tenantAccount.userName")}:</th>
	    		<td colspan='2'>
	    			 <input type="text" class="easyui-textbox" name="userName" disabled="disabled" value="${tenantAccount.userName}" id= "userName"  data-options="required:true"/>
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
				      [#if tenantAccount.accoutStatus == "ACTIVED"]
				      ,selected: true
				      [/#if]
				     },{
				      label: 'LOCKED',
				      value: '${message("yly.tenantAccount.accoutStatus.locked")}'
				      [#if tenantAccount.accoutStatus == "LOCKED"]
				      , selected: true
				      [/#if]
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100" id="editAccountStatus"  disabled="disabled" name="accoutStatus" style="width:110px;" data-value="${tenantAccount.accoutStatus}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.tenantUser")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="editTenantAccountUser" id= "editTenantAccountUser" disabled="disabled"  value="${tenantAccount.tenantUser.realName}"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantAccount.role")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" disabled="disabled" name="addTenantAccountRole" value = "${tenantAccount.roles[0].name}"id= "addTenantAccountRole"  />
	    		</td>
	    	</tr>
	    </table>
</form>



