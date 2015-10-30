<form id="tenantUserDetail_form" method="post">   
	 <input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>姓名 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" disabled="disabled" id = "realName" name="realName" data-options="required:true" value="${tenantUser.realName}" />   
	    		</td>
	    		<th>相片:</th>
	    		<td  rowspan="6">
	    				<p class="imgWrap img-thumbnail">
						     <img src="${tenantUser.photo}" style ="width:110px;hight:110 px">
						</p>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>性别:</th>
				  <td>
	    	 	<select id="gender" class="easyui-combobox" disabled="disabled" name="gender" disabled="disabled" style="width:50px;">   
    			  	<option value="MALE" [#if tenantUser.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if tenantUser.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>邮箱:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="email" data-options="required:true" value="${tenantUser.email}"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>员工编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="staffID" data-options="required:true" value="${tenantUser.staffID}"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>员工状态:</th>
	    		<td>
	    			<select id="gender" class="easyui-combobox" name="staffStatus" disabled="disabled" style="width:80px;">   
    			  		<option value="INSERVICE" [#if tenantUser.staffStatus =="INSERVICE"] selected="selected" [/#if]>在职</option>
						<option value="OUTSERVICE" [#if tenantUser.staffStatus =="OUTSERVICE"] selected="selected" [/#if]>离职</option>
				  </select>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>地址:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="address" data-options="required:true" value="${tenantUser.address}"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>电话:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="telephone" data-options="required:true" value="${tenantUser.telephone}"/>   
	    		</td>
	    	
	    		<th>手机:</th>
	    		<td>
	    			 <input class="easyui-textbox"  disabled="disabled" type="text" name="mobile" data-options="required:true" value="${tenantUser.mobile}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所在部门:</th>
	    		<td>
	    			 <input class="easyui-combobox" disabled="disabled" id="tenantUserDepartment-detail" name="departmentId" data-options="prompt:'${message("yly.common.please.select")}'" value="${tenantUser.department.name}"/>   
	    		</td>
	    	
	    		<th>担任职务:</th>
	    		<td>
	    			 <input class="easyui-combobox"  disabled="disabled" type="text" id="tenantUserPosition-detail" name="positionId" id="position" data-options="prompt:'${message("yly.common.please.select")}'" value="${tenantUser.position.name}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>入职时间:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="hireDate" readonly="readonly" value="${tenantUser.hireDate}"/>   
	    		</td>
	    		<th>年龄:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="age" data-options="required:true" value="${tenantUser.age}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>身份证:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="IDCard" data-options="required:true" value="${tenantUser.IDCard}"/>
	    		</td>
	    		<th>工作年限:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="workingYear" data-options="required:true" value="${tenantUser.workingYear}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>出生日期:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="birthDay" name="birthDay" readonly="readonly" value="${tenantUser.birthDay}"/>
	    		</td>
	    		<th>邮编:</th>
	    		<td>
	    			 <input class="easyui-textbox" disabled="disabled" type="text" name="zipCode" data-options="required:true" value="${tenantUser.zipCode}"/>
	    		</td>
	    	</tr>
	    </table>
</form>



