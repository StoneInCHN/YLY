<script src="${base}/resources/modules/blackList.js"></script>
<div>
	  <fieldset>
	    <legend>黑名单查询</legend>
	    <form id="blacklist_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 名字查询:</label>
			    <input type="text" class="easyui-textbox" id="blackListName" name="blackListName" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 查询时间从:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="blacklist_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="blacklist-table-list"></table>
<div id="blacklist_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="blacklist_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="blacklist_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="blacklist_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addBlacklist">
	<form id="addBlacklist_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" validtype="length[0,15]" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.blacklist.geracomium")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="geracomium" validtype="length[0,15]" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    		  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE">${message("yly.gender.male")}</option>
					<option value="FEMALE">${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    		<th>${message("yly.blacklist.photo")}:</th>
	    		<td>
	    			 <input class="easyui-filebox" name="file" id="photoUpload-filebox" data-options="prompt:'${message("yly.blacklist.choose.photo")}...'" style="width:200px;">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.placeOfOrigin")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="placeOfOrigin" validtype="length[0,50]" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.elderlyInfo.nation")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="nation" validtype="length[0,10]" style="width:80px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="idCard"  validtype="idcard" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" data-options="min:0,max:200" style="width:40px;">
	    		</td>
	    	</tr>	
	    	<tr>
	    		<th>${message("yly.elderlyInfo.birthday")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" id="birthday"  name="birthday" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>  
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="contactPhone" validtype="mobile" style="width:85px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.registeredResidence")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="registeredResidence" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.residentialAddress")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="residentialAddress" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.blacklist.casue")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="casue" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:120" />
	    		</td>
	    	</tr>
	    </table>
	</form>
	</div>
<div id="editBlacklist"></div> 
<div id="blacklistDetail"></div>





