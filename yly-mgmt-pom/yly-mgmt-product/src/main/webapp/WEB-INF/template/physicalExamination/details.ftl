<form id="physicalExaminationDetail_form" method="post">   
		<table class="table table-striped" id="editPhysicalExamination-table-list"  border="0">
    	<tr>
    		<th>${message("yly.common.elderly")}:</th>
    		<td>
    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" value="${physicalExamination.elderlyInfo.name}" id="editPhysicalExamination_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" disabled="disabled"/>
    		</td>
    		<th>${message("yly.physicalExamination.physicalExaminationDate")}:</th>
    		<td>
    			 <input type="text" class="Wdate" id="physicalExaminationDate" value="${physicalExamination.physicalExaminationDate}" name="physicalExaminationDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" disabled="disabled"/>   
    		</td>
    	</tr>
    	[#list physicalExamination.physicalExaminationItems as item]
    	<input type="hidden" name="physicalExaminationItems[${item_index}].id" value="${item.id}"/>
    		<tr id="physicalExaminationItem${item.id}">
    			<th>${message("yly.physicalExamination.physicalExaminationItemName")}</th>
    			<td>
    				<input class="easyui-textbox input_text_line" value="${item.physicalExaminationItem.configValue}" id="physicalExamItemsConfig${item_index}" type="text" validtype="length[0,15]" style="width:60px;" disabled="disabled"/>
    			</td>
    			<th>${message("yly.physicalExamination.physicalExaminationItemValue")}</th>
				<td>
					<input class="easyui-textbox input_text_line " value="${item.physicalExaminationItemValue}" type="text" name="physicalExaminationItems[${item_index}].physicalExaminationItemValue" style="width:150px;" disabled="disabled"/>
				</td>
    		</tr>
    	[/#list]
</form>



