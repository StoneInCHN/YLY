<form id="editVisitElderly_form" method="post">   
		<input value="${visitElderlyRecord.id}" type="hidden" name="id" />
		<input value="${visitElderlyRecord.tenantID}" type="hidden" name="tenantID" />
		<input type="hidden" value="${visitElderlyRecord.elderlyInfo.id}" name="elderlyInfoID" id="addVisitElderly_elderlyInfo_editID">
		    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.visitelderly.elderlyInfo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" id="addVisitElderly_elderlyInfo_edit" type="text"  value ="${visitElderlyRecord.elderlyInfo.name}" data-options="required:true,editable:false" style="width:85px;"/>  
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addVisitElderly_elderlyInfo_edit')" iconCls="icon-search" plain=true"></a> 
	    		</td>
	    		
	    		<th>${message("yly.visitelderly.visitor")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="visitor" value ="${visitElderlyRecord.visitor}" data-options="required:true" validtype="length[0,15]" style="width:85px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard" value ="${visitElderlyRecord.IDCard}" style="width:120px;"/> 
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="phoneNumber" value ="${visitElderlyRecord.phoneNumber}" validtype="mobile"  style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
				<th>${message("yly.visitelderly.visitPersonnelNumber")}:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" name="visitPersonnelNumber" value ="${visitElderlyRecord.visitPersonnelNumber}" data-options="min:1,max:100,required:true,editable:false" style="width:50px;"/> 
	    		</td>
	    		<th>${message("yly.visitelderly.relation")}:</th>
	    		<td>
    			  <select id="relation" class="easyui-combobox" name="relation" style="width:100px;">   
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
	    		<th>${message("yly.visitelderly.visitDate")}:</th>
	    		<td>
	    			  <input class="easyui-datetimebox" name="visitDate" value ="${visitElderlyRecord.visitDate}" data-options="required:true,showSeconds:false,editable:false" style="width:140px">  
	    		</td>
	    		<th>${message("yly.visitelderly.dueLeaveDate")}:</th>
	    		<td>
	    			  <input class="easyui-datetimebox" name="dueLeaveDate" value ="${visitElderlyRecord.dueLeaveDate}" data-options="showSeconds:false,editable:false" style="width:140px">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.visitelderly.reasonForVisit")}:</th>
	    		<td>	    			
	    			  <input class="easyui-textbox" type="text" name="reasonForVisit" value ="${visitElderlyRecord.reasonForVisit}" validtype="length[0,50]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" value ="${visitElderlyRecord.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



