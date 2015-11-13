<form id="editMedicalRecord_form" method="post">   
		 <input type="hidden" name="elderlyInfoID" id="addMedicalRecord_elderlyInfoID" value="${medicalRecord.elderlyInfo.id}">
		 <input type="hidden" name="id" id="id" value="${medicalRecord.id}">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    	<th>${message("yly.common.elderly")}:</th>
	    		<td>
	    		<input class="easyui-textbox" prompt="${message("yly.common.please.select")}" value="${medicalRecord.elderlyInfo.name}" name="elderlyInfoName" id="addMedicalRecord_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addMedicalRecord_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<th>${message("yly.medicalRecord.chiefComplaint")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${medicalRecord.chiefComplaint}" type="text" name="chiefComplaint" validtype="length[0,40]" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.allergicHistory")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${medicalRecord.allergicHistory}" id="allergicHistory" name="allergicHistory" data-options="multiline:true" style="height:100px;width:300px" />   
	    		</td>
	    	
	    		<th>${message("yly.medicalRecord.clinicDiagnosis")}:</th>
	    		<td>
	    			<input class="easyui-textbox" value="${medicalRecord.clinicDiagnosis}" type="text" name="clinicDiagnosis" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.treatmentAdvice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${medicalRecord.treatmentAdvice}" name="treatmentAdvice" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    	
	    		<th>${message("yly.medicalRecord.outpatientTreatment")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" value="${medicalRecord.outpatientTreatment}" name="outpatientTreatment" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.outpatientMedicalAdvice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${medicalRecord.outpatientMedicalAdvice}" name="outpatientMedicalAdvice" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    		<th>${message("yly.medicalRecord.medicalHistory")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${medicalRecord.medicalHistory}" name="medicalHistory" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    </table>
</form>



