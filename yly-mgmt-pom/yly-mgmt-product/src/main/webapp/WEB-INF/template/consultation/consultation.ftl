<script src="${base}/resources/modules/consultation.js"></script>
<div>
	  <fieldset>
	    <legend>${("yly.consultation.search")}</legend>
	    <form id="consultation_search_form" class="search-form">
			<div class="search-item">
			    <label> ${("yly.consultation.date")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="consultation_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="consultation-table-list"></table>
<div id="consultation_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="consultation_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="consultation_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="consultation_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addConsultation">
	<form id="addConsultation_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.consultation.vistor")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="vistor" validtype="length[0,15]" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" validtype="mobile" style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyName" validtype="length[0,15] style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE">${message("yly.gender.male")}</option>
					<option value="FEMALE">${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.checkinIntention")}:</th>
	    		<td>
    			  <select id="checkinIntention" class="easyui-combobox" name="checkinIntention" style="width:110px;">   
					<option value="CONFIRMED">${message("yly.consultation.checkinIntention.confirmed")}</option>
					<option value="WILL_TO_CHECKIN_STRONGLY">${message("yly.consultation.checkinIntention.will_to_checkin_strongly")}</option> 
					<option value="WILL_TO_CHECKIN_NOT_STRONGLY">${message("yly.consultation.checkinIntention.will_to_checkin_not_strongly")}</option>
					<option value="WILL_NOT_CHECKIN">${message("yly.consultation.checkinIntention.will_not_checkin")}</option>
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.consultation.relation")}:</th>
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
	    		<th>${message("yly.infoChannel")}:</th>
	    		<td>
    			  <select id="infoChannel" class="easyui-combobox" name="infoChannel" style="width:80px;">   
					<option value="NETWORK">${message("yly.common.relation.infochannel.network")}</option>
					<option value="COMMUNITY">${message("yly.common.relation.infochannel.community")}</option> 
					<option value="OHTER_INTRODUCT">${message("yly.common.relation.infochannel.ohter_introduct")}</option>
					<option value="ADVERTISEMENT">${message("yly.common.relation.infochannel.advertisement")}</option>
					<option value="OTHER">${message("yly.common.other")}</option>
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.consultation.returnVisit")}:</th>
	    		<td>
    			  <select id="returnVisit" class="easyui-combobox" name="returnVisit" style="width:45px;">   
					<option value="true">${message("yly.common.yes")}</option>
					<option value="false">${message("yly.common.no")}</option> 
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.returnVisitDate")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" id="returnVisitDate" name="returnVisitDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.elderlyHealth")}:</th>
	    		<td>	    			
	    			  <input class="easyui-textbox" type="text" name="elderlyHealth" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
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
<div id="editConsultation"></div> 
<div id="consultationDetail"></div>





