<script src="${base}/resources/modules/volunteer.js"></script>
<div>
	  <fieldset>
	    <legend>志愿者查询</legend>
	    <form id="volunteer_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 志愿者:</label>
			    <input type="text" class="easyui-textbox" id="volunteerName" validtype="length[0,20]" name="volunteerName" style="width:85px;" />
			</div>
			<div class="search-item">
				<label> 机构类型:</label>
	    		<input class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: 'PERSONAL',
					value: '${message("yly.volunteer.personal")}'
				},{
					label: 'ORGANIZATION',
					value: '${message("yly.volunteer.organization")}'
				}],
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  id="volunteerType" name="volunteerType" style="width:130px;"/>
			</div>
			<div class="search-item">
			    <label> 活动时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="volunteer_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="volunteer-table-list"></table>
<div id="volunteer_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="volunteer_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="volunteer_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="volunteer_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addvolunteer">
	<form id="addvolunteer_form" method="post" class="form-table">   
	   	  <table class="table table-striped table-bordered">
	   	  	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.volunteer.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="volunteerName" validtype="length[0,15]" data-options="required:true" style="width:100px;"/>   
	    		</td>
	    		<th>${message("yly.idCard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="idcard" value="${volunteer.idcard}" validtype="idcard" style="width:150px;"/> 
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.type")}:</th>
	    		<td>
	    		  <select class="easyui-combobox" name="volunteerType" editable="false" required="required" style="width:85px;">   
    			  	<option value="PERSONAL">${message("yly.volunteer.personal")}</option>
					<option value="ORGANIZATION">${message("yly.volunteer.organization")}</option>  
				  </select>  
	    		</td>
	    		<th>${message("yly.email")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="email" value="${volunteer.email}" validtype="email" style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<th>${message("yly.volunteer.activityTime")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-datebox"  editable="false" name="activityTime" required="required" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.mobile")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="mobile" value="${volunteer.mobile}" validtype="mobile" data-options="required:true"  style="width:150px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="telephone" value="${volunteer.telephone}" validtype="length[0,30]" style="width:150px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.zipCode")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="zipCode" value="${volunteer.zipCode}" validtype="length[0,50]" style="width:100px;"/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.address")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="address" value="${volunteer.address}" validtype="length[0,100]" style="width:280px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:280" />
	    		</td>
	    	</tr>
	    	</table>
	    </table>
	</form>
	</div>
<div id="editvolunteer"></div> 
<div id="volunteerDetail"></div>





