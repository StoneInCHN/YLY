<form id="consultationDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.consultation.vistor")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="visitor" readonly=true value="${consultation.visitor}" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber"  readonly=true value="${consultation.phoneNumber}" style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyName"  readonly=true value="${consultation.elderlyName}" style="width:85px;"/> 
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" disabled="disabled" style="width:50px;">   
    			  	<option value="MALE" [#if consultation.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if consultation.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
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
					prompt:'${message("yly.common.please.select")}',panelMaxHeight:100,value:'${consultation.checkinIntention}'" disabled="disabled" name="checkinIntention" style="width:110px;"/>
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
						prompt:'${message("yly.common.please.select")}',value:'${consultation.relation}'" disabled="disabled"  name="relation" style="width:100px;"/>
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
					prompt:'${message("yly.common.please.select")}',panelMaxHeight:120,value:'${consultation.infoChannel}'" disabled="disabled" name="infoChannel" style="width:80px;"/>
	    		</td>
	    		
	    		<th>${message("yly.consultation.returnVisit")}:</th>
	    		<td>
    			  <select id="returnVisit" class="easyui-combobox" name="returnVisit" disabled="disabled" style="width:45px;">   
					<option value="true" [#if consultation.returnVisit == true] selected="selected" [/#if]>${message("yly.common.yes")}</option>
					<option value="false" [#if consultation.returnVisit == false] selected="selected" [/#if]>${message("yly.common.no")}</option> 
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.returnVisitDate")}:</th>
	    		<td>
	    			  <input id="returnVisitDate" name="returnVisitDate" type="text" value="${consultation.returnVisitDate}" style="width:100px;" class="easyui-datebox" disabled=true/> 
	    		</td>
	    		<th>${message("yly.createDate")}:</th>
	    		<td>
	    			  <input id="createDate" name="createDate" type="text" value="${consultation.createDate}" class="easyui-datebox" disabled=true/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.consultation.elderlyHealth")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=4 type="text" name="elderlyHealth" readonly=true>${consultation.elderlyHealth}</textarea> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td colspan=3>
	    			  <textarea  cols=60 rows=4 type="text" name="remark" readonly=true>${consultation.remark}</textarea> 
	    		</td>
	    	</tr>
	    </table>
</form>



