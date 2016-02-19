 <form id="changePassword_form" method="post" >
 <input type="hidden" id="newEnPassword">
	<table class="table table-striped">
	<tr>
		<th>${message("yly.tenantAccount.oldPassword")}:</th>
		<td >
			 <input type="password" class="easyui-textbox" name="oldPassword" data-options="required:true"/>
		</td>
	</tr>
	<tr>
		<th>${message("yly.tenantAccount.newPassword")}:</th>
		<td>
			 <input id="newPassword" type="password" class="easyui-textbox" name="newPassword" 
			 	data-options="required:true" validType="minLength[6]" />
		</td>
	</tr>
	<tr>
		<th>${message("yly.tenantAccount.confirmPassword")}:</th>
		<td>
			 <input type="password" class="easyui-textbox" name="confirmPassword"  
			 	data-options="required:true" validType="passwordEequals['#newPassword']" />
		</td>
	</tr>
</form>




