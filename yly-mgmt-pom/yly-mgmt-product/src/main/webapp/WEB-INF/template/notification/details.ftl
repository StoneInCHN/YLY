<form id="physicalExaminationDetail_form" method="post">   
		 <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message("yly.common.elderly")}:</th>
    		<td>
    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" value="${physicalExamination.elderlyInfo.name}" disabled="disabled" id="editPhysicalExamination_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
    		</td>
    		<th>${message("yly.physicalExamination.physicalExaminationDate")}:</th>
    		<td>
    			 <input type="text" class="Wdate" id="physicalExaminationDate" value="${physicalExamination.physicalExaminationDate}" name="physicalExaminationDate" disabled="disabled" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
    		</td>
    	</tr>
    </table>
</form>



