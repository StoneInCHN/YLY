<form id="editTenantUser_form" method="post">   
	<input type="hidden" name="id" value= "${tenantUser.id}">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>姓名 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "realName" name="realName" data-options="required:true" value="${tenantUser.realName}" validtype="length[0,20]"/>   
	    		</td>
	    		<th>相片:</th>
	    		<td rowspan="6">
	    			<div title="头像上传" class="easyui-tooltip">
	    				<div id="tenantUserUploader-edit" class="single-uploader">
						  	<div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="tenantUserFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										    <img id ="tenantUserPhoto-edit" src="${tenantUser.photo}" style ="width:110px;hight:110 px">
									 </p>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						        <div id="tenantUserFilePicker-edit2" class="margin-left-40">选择文件</div>
						        <div class="btn btn-info savePhoto margin-left-40" style="display:none">保存头像</div>
						    </div>
						</div>
	    			</div>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>性别:</th>
				  <td>
	    	 	<select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if tenantUser.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if tenantUser.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>邮箱:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="email" data-options="required:true" value="${tenantUser.email}"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>员工编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="staffID" data-options="required:true" value="${tenantUser.staffID}" validtype="length[0,30]"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>员工状态:</th>
	    		<td>
	    			<select id="gender" class="easyui-combobox" name="staffStatus"  style="width:80px;">   
    			  		<option value="INSERVICE" [#if tenantUser.staffStatus =="INSERVICE"] selected="selected" [/#if]>在职</option>
						<option value="OUTSERVICE" [#if tenantUser.staffStatus =="OUTSERVICE"] selected="selected" [/#if]>离职</option>
				  </select>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>地址:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="address" data-options="required:true" value="${tenantUser.address}" validtype="length[0,200]"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>电话:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="telephone" data-options="required:true" value="${tenantUser.telephone}" />   
	    		</td>
	    	
	    		<th>手机:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="mobile" data-options="required:true" value="${tenantUser.mobile}" validtype="mobile"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所在部门:</th>
	    		<td>
	    			 <input class="easyui-combobox"  id="tenantUserDepartment-edit" name="departmentId" data-options="prompt:'${message("yly.common.please.select")}'" data-value="${tenantUser.department.id}"/>   
	    		</td>
	    	
	    		<th>担任职务:</th>
	    		<td>
	    			 <input class="easyui-combobox"   type="text" id="tenantUserPosition-edit" name="positionId" data-options="prompt:'${message("yly.common.please.select")}'" data-value="${tenantUser.position.id}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>入职时间:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="hireDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" value="${tenantUser.hireDate}"/>   
	    		</td>
	    		<th>年龄:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="age" data-options="required:true" value="${tenantUser.age}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>身份证:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="IDCard" data-options="required:true" value="${tenantUser.IDCard}" validtype="length[0,30]"/>
	    		</td>
	    		<th>工作年限:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="workingYear" data-options="required:true" value="${tenantUser.workingYear}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>出生日期:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="birthDay" name="birthDay" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" value="${tenantUser.birthDay}"/>
	    		</td>
	    		<th>邮编:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="zipCode" data-options="required:true" value="${tenantUser.zipCode}" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    </table>
</form>



