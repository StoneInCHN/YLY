<script src="${base}/resources/modules/visitElderly.js"></script>
<div>
	  <fieldset>
	    <legend>咨询查询</legend>
	    <form id="visitElderly_search_form" class="search-form">
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
	  	  <button id="visitElderly_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="visitElderly-table-list"></table>
<div id="visitElderly_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="visitElderly_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="visitElderly_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="visitElderly_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addVisitElderly">
	<form id="addVisitElderly_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.visitelderly.elderlyInfo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyInfo" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.visitelderly.visitor")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="visitor" validtype="length[0,15]" style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard"  style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" validtype="mobile"  style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
				<th>${message("yly.visitelderly.visitPersonnelNumber")}:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" name="visitPersonnelNumber" data-options="min:1,max:100,required:true" /> 
	    		</td>
	    		<th>${message("yly.visitelderly.relation")}:</th>
	    		<td>
    			  <select id="relation" class="easyui-combobox" name="relation" style="width:100px;">   
					<option value="SELF">${message("yly.common.relation.self")}</option>
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
	    			  <input type="text" class="Wdate" id="visitDate" name="visitDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    		<th>${message("yly.visitelderly.dueLeaveDate")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" id="dueLeaveDate" name="dueLeaveDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
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





