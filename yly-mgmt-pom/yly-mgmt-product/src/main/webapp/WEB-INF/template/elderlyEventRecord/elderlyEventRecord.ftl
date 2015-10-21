<script src="${base}/resources/modules/elderlyEventRecord.js"></script>

<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.event.search")}</legend>
	    <form id="elderlyEvent_search_form" class="search-form">
			<div class="search-item">
			    <label> ${message("yly.common.search.date.from")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
			<div class="search-item">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="search-item">
			   <label>${message("yly.common.elderly.name")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="elderlyName" name="keysOfElderlyName" validtype="length[0,15]" style="width:110px;"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="elderlyEvent_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="elderlyevent_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="elderlyevent_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"  plain=true onclick="elderlyevent_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  onclick="elderlyevent_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<table id="event-table-list"></table>
<div id="addElderlyEvent">
	<form id="addElderlyEvent_form" method="post" class="form-table">   
		<input type="hidden" name="elderlyInfoID" id="elderlynameID">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="elderlyname" name="elderlyname" data-options="required:true,editable:false" style="width:85px;" />   
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyname')" iconCls="icon-search" plain=true"></a> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="operator" validtype="length[0,15]" data-options="required:true,editable:true" style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.event.eventDate")}:</th>
	    		<td>
	    			  <input class="Wdate" type="text" name="eventDate" data-options="required:true,editable:true,width:110" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});"  /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.event.content")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="eventContent" validtype="length[0,300]" data-options="multiline:true,height:110,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editEvent"></div>  
<div id="showEvent"></div> 




