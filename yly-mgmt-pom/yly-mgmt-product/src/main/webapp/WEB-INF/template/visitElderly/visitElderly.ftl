<script src="${base}/resources/modules/visitElderly.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.visitelderly.search")}</legend>
	    <form id="visitElderly_search_form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.visitelderly.elderlyInfo")}:</label>
			    <input class="easyui-textbox"  type="text" id="elderlyName"  name="elderlyInfo.name" style="width:85px;"/>
			    <input type="hidden" id="elderlyNameHidden" name="elderlyNameHidden">
			</div>
			<div class="search-item">
			    <label> ${message("yly.visitelderly.visitor")}:</label>
			    <input class="easyui-textbox" type="text" id="vistor" name="visitor" validtype="length[0,15]" style="width:85px;"/>
			    <input type="hidden" id="vistorHidden" name="vistorHidden">
			</div>
			<div class="search-item">
			    <label> ${message("yly.visitelderly.visitDate")}:</label>
			    <input type="text" class="Wdate" id="visitDateBeginDate" name="visitDateBeginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'visitDateEndDate\')}'});" />
			    <input type="hidden" id="visitDateBeginDateHidden" name="visitDateBeginDateHidden">
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="visitDateEndDate"  name="visitDateEndDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'visitDateBeginDate\')}'});"/>
			   	<input type="hidden" id="visitDateEndDateHidden" name="visitDateEndDateHidden">
			</div>
		</form>
		<div class="search-item">
	  	  <button id="visitElderly_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="visitElderly-table-list"></table>
<div id="visitElderly_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="visitElderly_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="visitElderly_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="visitElderly_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportData('visitElderly','visitElderly_search_form');">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addVisitElderly">
	<form id="addVisitElderly_form" method="post" class="form-table">   
	 	  <input type="hidden" name="elderlyInfoID" id="addVisitElderly_elderlyInfoID">
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.visitelderly.elderlyInfo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" id="addVisitElderly_elderlyInfo" type="text"  data-options="required:true,editable:false" style="width:85px;"/>  
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addVisitElderly_elderlyInfo')" iconCls="icon-search" plain=true"></a> 
	    		</td>
	    		
	    		<th>${message("yly.visitelderly.visitor")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="visitor" data-options="required:true" validtype="length[0,15]" style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard"  style="width:120px;"/> 
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" validtype="mobile"  style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
				<th>${message("yly.visitelderly.visitPersonnelNumber")}:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" name="visitPersonnelNumber" data-options="min:1,max:100,required:true,editable:false" style="width:50px;"/> 
	    		</td>
	    		<th>${message("yly.visitelderly.relation")}:</th>
	    		<td>
    			  <select id="relation" class="easyui-combobox" name="relation" style="width:100px;">   
					<option value="CHILDREN">${message("yly.common.relation.children")}</option> 
					<option value="MARRIAGE_RELATIONSHIP">${message("yly.common.relation.marriage_relationship")}</option>
					<option value="GRANDPARENTS_AND_GRANDCHILDREN">${message("yly.common.relation.grandparents_and_grandchildren")}</option>
					<option value="BROTHERS_OR_SISTERS">${message("yly.common.relation.brothers_or_sisters")}</option>
					<option value="DAUGHTERINLAW_OR_SONINLAW">${message("yly.common.relation.daughterinlaw_or_soninlaw")}</option>
					<option value="FRIEND">${message("yly.common.relation.friend")}</option>
					<option value="OTHER">${message("yly.common.other")}</option>
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.visitelderly.visitDate")}:</th>
	    		<td>
	    			  <input class="easyui-datetimebox" name="visitDate" data-options="required:true,showSeconds:false,editable:false" style="width:140px">  
	    		</td>
	    		<th>${message("yly.visitelderly.dueLeaveDate")}:</th>
	    		<td>
	    			  <input class="easyui-datetimebox" name="dueLeaveDate" data-options="showSeconds:false,editable:false" style="width:140px">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.visitelderly.reasonForVisit")}:</th>
	    		<td>	    			
	    			  <input class="easyui-textbox" type="text" name="reasonForVisit" validtype="length[0,50]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
	</div>
<div id="editVisitElderly"></div> 






