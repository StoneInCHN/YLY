<form id="editTenantUser_form" method="post">   
	<input type="hidden" name="id" value= "${tenantUser.id}">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.tenantUser.realName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "realName" name="realName" data-options="required:true" value="${tenantUser.realName}" validtype="length[0,20]"/>   
	    		</td>
	    		<th>${message("yly.tenantUser.photo")}:</th>
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
	    		<th>${message("yly.gender")}:</th>
				  <td>
	    	 	<select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if tenantUser.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if tenantUser.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.email")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="email" data-options="required:true" value="${tenantUser.email}"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.staffID")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="staffID" data-options="required:true" value="${tenantUser.staffID}" validtype="length[0,30]"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.staffStatus")}:</th>
	    		<td>
	    			<select id="gender" class="easyui-combobox" name="staffStatus"  style="width:80px;">   
    			  		<option value="INSERVICE" [#if tenantUser.staffStatus =="INSERVICE"] selected="selected" [/#if]>${message("yly.tenantUser.staffStatus.inService")}</option>
						<option value="OUTSERVICE" [#if tenantUser.staffStatus =="OUTSERVICE"] selected="selected" [/#if]>${message("yly.tenantUser.staffStatus.outService")}</option>
				  </select>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.address")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="address" data-options="required:true" value="${tenantUser.address}" validtype="length[0,200]"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="telephone"  value="${tenantUser.telephone}" />   
	    		</td>
	    	
	    		<th>${message("yly.mobile")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="mobile" data-options="required:true" value="${tenantUser.mobile}" validtype="mobile"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.department")}:</th>
	    		<td>
	    			 <input class="easyui-combobox"  id="tenantUserDepartment-edit" name="departmentId" data-options="prompt:'${message("yly.common.please.select")}'" data-value="${tenantUser.department.id}"/>   
	    		</td>
	    	
	    		<th>${message("yly.tenantUser.position")}:</th>
	    		<td>
	    			 <input class="easyui-combobox"   type="text" id="tenantUserPosition-edit" name="positionId" data-options="prompt:'${message("yly.common.please.select")}'" data-value="${tenantUser.position.id}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.hireDate")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="hireDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" value="${tenantUser.hireDate}"/>   
	    		</td>
	    		<th>${message("yly.tenantUser.age")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="age" data-options="required:true" value="${tenantUser.age}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.IDCard")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="IDCard" data-options="required:true" value="${tenantUser.IDCard}" validtype="length[0,30]"/>
	    		</td>
	    		<th>${message("yly.tenantUser.workingYear")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="workingYear" data-options="required:true" value="${tenantUser.workingYear}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.tenantUser.birthDay")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="birthDay" name="birthDay" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'});" value="${tenantUser.birthDay}"/>
	    		</td>
	    		<th>${message("yly.tenantUser.zipCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="zipCode" data-options="required:true" value="${tenantUser.zipCode}" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    </table>
</form>



