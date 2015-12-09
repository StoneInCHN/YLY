<script src="${base}/resources/modules/role.js"></script>
<div>
	  <fieldset>
	    <legend>角色查询</legend>
	    <form id="role_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 角色:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" validtype="length[0,20]" style="width:85px;" />
			</div>
			<div class="search-item">
			    <label> 录入时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="role_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="role-table-list"></table>
<div id="role_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-man" plain=true onclick="role_manager_tool.auth();">授权</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="role_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="role_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="role_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addrole">
	<form id="addrole_form" method="post" class="form-table">  
	   <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.role.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="name" validtype="length[0,150]" data-options="required:true,multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.role.description")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="description" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="role-dialog-auth">
	<ul id="roleTreeAuth" class="easyui-tree" checkbox="true"></ul>  
</div>

<div id="editRole"></div> 





