<form id="editConsultation_form" method="post">   
		<input value="${consultation.id}" type="hidden" name="id" />
		<input value="${consultation.tenantID}" type="hidden" name="tenantID" />
		
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.consultation.vistor")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="vistor" value="${consultation.vistor}" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" value="${consultation.phoneNumber}" style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyName" value="${consultation.elderlyName}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if consultation.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if consultation.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.checkinIntention")}:</th>
	    		<td>
    			  <select id="checkinIntention" class="easyui-combobox" name="checkinIntention" style="width:110px;">   
					<option value="CONFIRMED" [#if consultation.checkinIntention =="CONFIRMED"] selected="selected" [/#if]>${message("yly.consultation.checkinIntention.confirmed")}</option>
					<option value="WILL_TO_CHECKIN_STRONGLY" [#if consultation.checkinIntention =="WILL_TO_CHECKIN_STRONGLY"] selected="selected" [/#if]>${message("yly.consultation.checkinIntention.will_to_checkin_strongly")}</option> 
					<option value="WILL_TO_CHECKIN_NOT_STRONGLY" [#if consultation.checkinIntention =="WILL_TO_CHECKIN_NOT_STRONGLY"] selected="selected" [/#if]>${message("yly.consultation.checkinIntention.will_to_checkin_not_strongly")}</option>
					<option value="WILL_NOT_CHECKIN" [#if consultation.checkinIntention =="WILL_NOT_CHECKIN"] selected="selected" [/#if]>${message("yly.consultation.checkinIntention.will_not_checkin")}</option>
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.consultation.relation")}:</th>
	    		<td>
    			  <select id="relation" class="easyui-combobox" name="relation" style="width:100px;">   
					<option value="SELF" [#if consultation.relation =="SELF"] selected="selected" [/#if]>${message("yly.common.relation.self")}</option>
					<option value="CHILDREN" [#if consultation.relation =="CHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.children")}</option> 
					<option value="MARRIAGE_RELATIONSHIP" [#if consultation.relation =="MARRIAGE_RELATIONSHIP"] selected="selected" [/#if]>${message("yly.common.relation.marriage_relationship")}</option>
					<option value="GRANDPARENTS_AND_GRANDCHILDREN" [#if consultation.relation =="GRANDPARENTS_AND_GRANDCHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.grandparents_and_grandchildren")}</option>
					<option value="BROTHERS_OR_SISTERS" [#if consultation.relation =="BROTHERS_OR_SISTERS"] selected="selected" [/#if]>${message("yly.common.relation.brothers_or_sisters")}</option>
					<option value="DAUGHTERINLAW_OR_SONINLAW" [#if consultation.relation =="DAUGHTERINLAW_OR_SONINLAW"] selected="selected" [/#if]>${message("yly.common.relation.daughterinlaw_or_soninlaw")}</option>
					<option value="FRIEND" [#if consultation.relation =="FRIEND"] selected="selected" [/#if]>${message("yly.common.relation.friend")}</option>
					<option value="OTHER" [#if consultation.relation =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.infoChannel")}:</th>
	    		<td>
    			  <select id="infoChannel" class="easyui-combobox" name="infoChannel" style="width:80px;">   
					<option value="NETWORK" [#if consultation.infoChannel =="NETWORK"] selected="selected" [/#if]>${message("yly.common.relation.infochannel.network")}</option>
					<option value="COMMUNITY" [#if consultation.infoChannel =="COMMUNITY"] selected="selected" [/#if]>${message("yly.common.relation.infochannel.community")}</option> 
					<option value="OHTER_INTRODUCT" [#if consultation.infoChannel =="OHTER_INTRODUCT"] selected="selected" [/#if]>${message("yly.common.relation.infochannel.ohter_introduct")}</option>
					<option value="ADVERTISEMENT" [#if consultation.infoChannel =="ADVERTISEMENT"] selected="selected" [/#if]>${message("yly.common.relation.infochannel.advertisement")}</option>
					<option value="OTHER" [#if consultation.infoChannel =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.consultation.returnVisit")}:</th>
	    		<td>
    			  <select id="returnVisit" class="easyui-combobox" name="returnVisit" style="width:45px;">   
					<option value="true" [#if consultation.returnVisit == true] selected="selected" [/#if]>${message("yly.common.yes")}</option>
					<option value="false" [#if consultation.returnVisit == false] selected="selected" [/#if]>${message("yly.common.no")}</option> 
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.returnVisitDate")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" id="returnVisitDate" name="returnVisitDate" value="${consultation.returnVisitDate}" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.elderlyHealth")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=4 type="text" name="elderlyHealth">${consultation.elderlyHealth}</textarea> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=4 type="text" name="remark">${consultation.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



