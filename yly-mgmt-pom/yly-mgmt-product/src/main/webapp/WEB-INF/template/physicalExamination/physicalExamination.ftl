<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/physicalExamination.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.physicalExamination.search")}</legend>
	    <form id="physicalExamination-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input type="text" class="easyui-textbox" id="elderlyInfoName" name="elderlyInfoName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.physicalExamination.operator")}:</label>
			    <input type="text" class="easyui-textbox" id="operatorName" name="operatorName" />
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
	  	  <button id="physicalExamination-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="physicalExamination-table-list"></table>
<div id="physicalExamination_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="physicalExamination_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="physicalExamination_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="physicalExamination_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addPhysicalExamination"> 
	<form id="addPhysicalExamination_form" method="post" class="form-table">
		<input type="hidden" name="elderlyInfoID" id="addPhysicalExamination_elderlyInfoID">   
	    <table id="addPhysicalExamination-table-list" class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.common.elderly")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addPhysicalExamination_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addPhysicalExamination_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<th>${message("yly.physicalExamination.physicalExaminationDate")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="addPhysicalExaminationDate" name="physicalExaminationDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	</tr>
	    </table>
	    <table id="addPhysicalExaminationItem-table-list" class="table table-striped">
	    <table>
	    <div>
	    	<a href="javascript:;" id="addPhysicalExaminationItems" class="btn green-color" onclick="physicalExamination_manager_tool.addExamItemHtml('add')"><i class="fa fa-plus-square-o fa-2x"></i></a>
	    </div>
	</form>
</div>
<div id ="physicalExaminationItemsList"></div>
<div id="editPhysicalExamination"></div>
<div id="physicalExaminationDetail"></div>




