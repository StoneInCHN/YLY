<script src="${base}/resources/modules/leave.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.search")}</legend>
	    <form id="admission_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderlyInfo.identifier")}:</label>
			    <input class="easyui-textbox" type="text" name="identifier" validtype="length[0,15]"  style="width:60px;"/>
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" type="text" name="name" validtype="length[0,15]" style="width:75px;"/> 
			</div>
	    			    	<div class="search-item">
				<label> ${message("yly.elderlyInfo.elderlyStatus")}:</label>
	    		<input class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: 'OUT_NURSING_HOME',
					value: '${message("yly.elderlyInfo.elderlyStatus.out_nursing_home")}'
				},{
					label: 'IN_PROGRESS_CHECKOUT',
					value: '${message("yly.elderlyInfo.elderlyStatus.in_progress_checkout")}'
				}],
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:120"  name="elderlyStatus" style="width:100px;"/>
			</div>    	    
		</form>
		<div class="search-item">
	  	  <button id="leave_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="leave-table-list"></table>
<div id="leave_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="admission_manager_tool.add();">办理出院</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="admission_manager_tool.edit();"">查看退住结算账单</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="admission_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportData('elderlyInfo','checkedInElderly_search_form');">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addLeave">
	<form id="addLeave_form" action="add.jhtml" method="post" class="form-table" enctype="multipart/form-data">   

	</form>
</div>
<div id="leaveDetail"></div>

