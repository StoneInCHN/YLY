<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/notification.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.fixedAssets.search")}</legend>
	    <form id="notification-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.physicalExamination.physicalExaminationDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="notification-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="notification-table-list"></table>
<div id="notification_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="notification_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="notification_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="physicalExamination_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addNotification"> 
	<form id="addNotification_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.notification.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="operator" id= "operator" data-options="required:true" />
	    		</td>
	    		<th>${message("yly.common.title")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="title" id= "title" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr >
		    	<th >${message("yly.notification.publishTime")}:</th>
		    	<td colspan="3">
		    		<input type="text" class="Wdate" id="publishTime" name="publishTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
		    	</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.notification.content")}:</th>
	    		<td colspan="3">
	    			 <textarea name="content" id= "editor_id"  style="height:200px;width:300px" textarea/>   
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editNotification"></div>
<div id="notificationDetail"></div>




