<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/tenantUser.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>药品查询</legend>
	    <form id="tenantUser-search-form" class="search-form">
	    	<div class="search-item">
			    <label> 名称查询:</label>
			    <input type="text" class="easyui-textbox" id="realName" name="realNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 部门:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserDepartment-search" name="departmentSearchId"/>
			</div>
			<div class="search-item">
			    <label> 职位:</label>
			    <input type="text" class="easyui-combobox" id="tenantUserPosition-search" name="positionSearchId"/>
			</div>
			<div class="search-item">
			    <label> 状态:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '在职'
				     },{
				      label: 'OUTSERVICE',
				      value: '离职'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="staffStatusSearch" style="width:110px;"/>
			    
			</div>
			<div class="search-item">
			    <label> 入职时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="tenantUser-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="tenantUser-table-list"></table>
<div id="tenantUser_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="tenantUser_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="tenantUser_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="tenantUser_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addTenantUser"> 
	<form id="addTenantUser_form" method="post" class="form-table">  
	<input type="hidden" id="addTenantUser_form_file_input" name="photo"> 
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>姓名 :</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "realName" name="realName" data-options="required:true" />   
	    		</td>
	    		<th>相片:</th>
	    		<td  rowspan="6">
	    			<div title="头像上传" class="easyui-tooltip headWarp">
	    				<div id="tenantUserUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="tenantUserFilePicker-add" ></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>性别:</th>
				  
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'MALE',
				      value: '男'
				     },{
				      label: 'FEMALE',
				      value: '女'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="gender" style="width:110px;"/>
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>邮箱:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="email" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>员工编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="staffID" data-options="required:true" validtype="length[0,30]"/>   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>员工状态:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '在职'
				     },{
				      label: 'OUTSERVICE',
				      value: '离职'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="staffStatus" style="width:110px;"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>地址:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="address" data-options="required:true" validtype="length[0,200]" />   
	    		</td>
	    	
	    		
	    	</tr>
	    	<tr>
	    		<th>电话:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="telephone" data-options="required:true" />   
	    		</td>
	    	
	    		<th>手机:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="mobile" data-options="required:true" validtype="mobile"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所在部门:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="tenantUserDepartment-add" name="departmentId" data-options="prompt:'${message("yly.common.please.select")}'" />   
	    		</td>
	    	
	    		<th>担任职务:</th>
	    		<td>
	    			 <input class="easyui-combobox" type="text" id="tenantUserPosition-add" name="positionId" id="position" data-options="prompt:'${message("yly.common.please.select")}'" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>入职时间:</th>
	    		<td>
	    			 <input type="text" class="Wdate" name="hireDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    		<th>年龄:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="age" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>身份证:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="IDCard" data-options="required:true" validtype="length[0,30]"/>
	    		</td>
	    		<th>工作年限:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="workingYear" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>出生日期:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="birthDay" name="birthDay" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    		<th>邮编:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="zipCode" data-options="required:true" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editTenantUser"></div>
<div id="tenantUserDetail"></div>




