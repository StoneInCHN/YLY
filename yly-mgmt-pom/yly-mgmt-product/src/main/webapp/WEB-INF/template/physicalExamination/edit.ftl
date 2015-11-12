<form id="editPhysicalExamination_form" method="post">   
	 <input type="hidden" name="elderlyInfoID" id="editPhysicalExamination_elderlyInfoID" value="${physicalExamination.elderlyInfo.id}">
	 <input type="hidden" name="id" id="id" value="${physicalExamination.id}">   
    <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message("yly.common.elderly")}:</th>
    		<td>
    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" value="${physicalExamination.elderlyInfo.name}" id="editPhysicalExamination_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('editPhysicalExamination_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
    		</td>
    		<th>${message("yly.physicalExamination.physicalExaminationDate")}:</th>
    		<td>
    			 <input type="text" class="Wdate" id="physicalExaminationDate" value="${physicalExamination.physicalExaminationDate}" name="physicalExaminationDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
    		</td>
    	</tr>
    </table>
</form>



