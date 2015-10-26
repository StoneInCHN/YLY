<script src="${base}/resources/modules/blackList.js"></script>
<div>
	  <fieldset>
	    <legend>黑名单查询</legend>
	    <form id="blacklist_search_form" class="search-form">
	    	<div class="search-item">
			    <label> 名字查询:</label>
			    <!--<input type="text" class="easyui-textbox" id="blackListName" name="blackListName" style="width:85px;" />-->
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
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="elderlyname" name="elderlyname" data-options="required:true,editable:false" style="width:85px;" />   
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyname')" iconCls="icon-search" plain=true"></a> 
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





