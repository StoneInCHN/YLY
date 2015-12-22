<script src="${base}/resources/modules/consultation.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.consultation.search")}</legend>
	    <form id="consultation_search_form" class="search-form">
	   	 	<div class="search-item">
			    <label> ${message("yly.consultation.vistor")}:</label>
			    <input class="easyui-textbox" type="text" id="search-vistor" name="visitor" validtype="length[0,15]" style="width:85px;"/>
			    <input type="hidden" id="visitorHidden" name="visitorHidden">
			</div>
			<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input class="easyui-textbox" id="search-elderlyName" type="text" name="elderlyName" style="width:85px;"/> 
			    <input type="hidden" id="elderlyNameHidden" name="elderlyNameHidden">
			</div>
			
			
			<div class="search-item">
				<label> ${message("yly.consultation.checkinIntention")}:</label>
	    		<input class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: 'CONFIRMED',
					value: '${message("yly.consultation.checkinIntention.confirmed")}'
				},{
					label: 'WILL_TO_CHECKIN_STRONGLY',
					value: '${message("yly.consultation.checkinIntention.will_to_checkin_strongly")}'
				},{
					label: 'WILL_TO_CHECKIN_NOT_STRONGLY',
					value: '${message("yly.consultation.checkinIntention.will_to_checkin_not_strongly")}'
				},{
					label: 'WILL_NOT_CHECKIN',
					value: '${message("yly.consultation.checkinIntention.will_not_checkin")}'
				}],
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  id="search-checkinIntention" name="checkinIntention" style="width:130px;"/>
				<input type="hidden" id="checkinIntentionHidden" name="checkinIntentionHidden">
			</div>
			
			<div class="search-item">
				<label> ${message("yly.infoChannel")}:</label>
	    		<input class="easyui-combobox" data-options="
				valueField: 'label',
				textField: 'value',
				data: [{
					label: 'NETWORK',
					value: '${message("yly.common.relation.infochannel.network")}'
				},{
					label: 'COMMUNITY',
					value: '${message("yly.common.relation.infochannel.community")}'
				},{
					label: 'OHTER_INTRODUCT',
					value: '${message("yly.common.relation.infochannel.ohter_introduct")}'
				},{
					label: 'ADVERTISEMENT',
					value: '${message("yly.common.relation.infochannel.advertisement")}'
				},{
					label: 'OTHER',
					value: '${message("yly.common.other")}'
				}],
				prompt:'${message("yly.common.please.select")}',panelMaxHeight:120" id="search-infoChannel" name="infoChannel" style="width:100px;"/>
				<input type="hidden" id="infoChannelHidden" name="infoChannelHidden">
			</div>
			
			<div class="search-item">
			    <label> ${message("yly.consultation.returnVisitDate")}:</label>
			     <input type="text" class="Wdate" id="returnVisitDateBeginDate" name="returnVisitDateBeginDate" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'returnVisitDateEndDate\')}'});" />
			     <input type="hidden" id="returnVisitDateBeginDateHidden" name="returnVisitDateBeginDateHidden">
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="returnVisitDateEndDate"  name="returnVisitDateEndDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'returnVisitDateBeginDate\')}'});"/>
			   	<input type="hidden" id="returnVisitDateEndDateHidden" name="returnVisitDateEndDateHidden">
			</div>
		</form>
		<div class="search-item">
	  	  <button id="consultation_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="consultation-table-list"></table>
<div id="consultation_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="consultation_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="consultation_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="consultation_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="exportData('consultation','consultation_search_form');">导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addConsultation">
	<form id="addConsultation_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.consultation.vistor")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="visitor" validtype="length[0,15]" data-options="required:true" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" validtype="mobile" style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyName" validtype="length[0,15]" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;" data-options="panelMaxHeight:55">   
    			  	<option value="MALE">${message("yly.gender.male")}</option>
					<option value="FEMALE">${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.checkinIntention")}:</th>
	    		<td>
    			  <input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'CONFIRMED',
						value: '${message("yly.consultation.checkinIntention.confirmed")}'
					},{
						label: 'WILL_TO_CHECKIN_STRONGLY',
						value: '${message("yly.consultation.checkinIntention.will_to_checkin_strongly")}'
					},{
						label: 'WILL_TO_CHECKIN_NOT_STRONGLY',
						value: '${message("yly.consultation.checkinIntention.will_to_checkin_not_strongly")}'
					},{
						label: 'WILL_NOT_CHECKIN',
						value: '${message("yly.consultation.checkinIntention.will_not_checkin")}'
					}],
					prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="checkinIntention" style="width:110px;"/>
	    		</td>
	    		
	    		<th>${message("yly.consultation.relation")}:</th>
	    		<td>
				  <input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'SELF',
						value: '${message("yly.common.relation.self")}'
						},{
							label: 'CHILDREN',
							value: '${message("yly.common.relation.children")}'
						},{
							label: 'MARRIAGE_RELATIONSHIP',
							value: '${message("yly.common.relation.marriage_relationship")}'
						},{
							label: 'GRANDPARENTS_AND_GRANDCHILDREN',
							value: '${message("yly.common.relation.grandparents_and_grandchildren")}'
						},{
							label: 'BROTHERS_OR_SISTERS',
							value: '${message("yly.common.relation.brothers_or_sisters")}'
						},{
							label: 'DAUGHTERINLAW_OR_SONINLAW',
							value: '${message("yly.common.relation.daughterinlaw_or_soninlaw")}'
						},{
							label: 'FRIEND',
							value: '${message("yly.common.relation.friend")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
						prompt:'${message("yly.common.please.select")}'"  name="relation" style="width:100px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.infoChannel")}:</th>
	    		<td>
    			 <input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'NETWORK',
						value: '${message("yly.common.relation.infochannel.network")}'
					},{
						label: 'COMMUNITY',
						value: '${message("yly.common.relation.infochannel.community")}'
					},{
						label: 'OHTER_INTRODUCT',
						value: '${message("yly.common.relation.infochannel.ohter_introduct")}'
					},{
						label: 'ADVERTISEMENT',
						value: '${message("yly.common.relation.infochannel.advertisement")}'
					},{
						label: 'OTHER',
						value: '${message("yly.common.other")}'
					}],
					prompt:'${message("yly.common.please.select")}',panelMaxHeight:120"  name="infoChannel" style="width:80px;"/>
	    		</td>
	    		
	    		<th>${message("yly.consultation.returnVisit")}:</th>
	    		<td>
    			  <select id="returnVisit" class="easyui-combobox" name="returnVisit" style="width:45px;" data-options="panelMaxHeight:55">   
					<option value="true">${message("yly.common.yes")}</option>
					<option value="false">${message("yly.common.no")}</option> 
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.returnVisitDate")}:</th>
	    		<td>
	    			  <input id="returnVisitDate" name="returnVisitDate" type="text" class="easyui-datebox" style="width:100px;"/>
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





