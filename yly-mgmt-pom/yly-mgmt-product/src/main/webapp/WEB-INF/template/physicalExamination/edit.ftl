<form id="editPhysicalExamination_form" method="post">   
	 <input type="hidden" name="elderlyInfoID" id="editPhysicalExamination_elderlyInfoID" value="${physicalExamination.elderlyInfo.id}">
	 <input type="hidden" name="id" id="id" value="${physicalExamination.id}">   
    <table class="table table-striped" id="editPhysicalExamination-table-list"  border="0">
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
    	<tr>
    		<td colspan="9">
					<a href="javascript:;" id="editPhysicalExaminationItems" class="btn green-color" onclick="physicalExamination_manager_tool.addExamItemHtml('edit')"><i class="fa fa-plus-square-o fa-2x"></i></a>
				</td>
    	</tr>
    </table>
    <table id="editPhysicalExaminationItem-table-list" class="table table-striped">
    <input type= "hidden" id ="physicalExamItemSize" value="${physicalExamination.physicalExaminationItems?size}"/>
    	[#list physicalExamination.physicalExaminationItems as item]
    	<input type="hidden" name="physicalExaminationItems[${item_index}].id" value="${item.id}"/>
    		<tr id="physicalExaminationItem${item.id}">
    			<th>${message("yly.physicalExamination.physicalExaminationItemName")}：</th>
    			<td>
    				<input class="easyui-textbox input_text_line" value="${item.physicalExaminationItem.configValue}" id="physicalExamItemsConfig${item_index}" type="text" validtype="length[0,15]" style="width:60px;"/>
					<input type="hidden" id="physicalExamItemsConfig${item_index}ID" value="${item.physicalExaminationItem.id}" name="physicalExaminationItems[${item_index}].physicalExaminationItem.id" />
					<a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="physicalExamination_manager_tool.searchPhysicalExaminationItem('physicalExamItemsConfig${item_index}')" iconCls="icon-search" plain=true"></a>
    			</td>
    			<th>${message("yly.physicalExamination.physicalExaminationItemValue")}:</th>
				<td>
					<input class="easyui-textbox input_text_line " value="${item.physicalExaminationItemValue}" type="text" name="physicalExaminationItems[${item_index}].physicalExaminationItemValue" style="width:150px;"/>
				</td>
				<td>
				<a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="physicalExamination_manager_tool.editRemove(${item.id})" iconCls="icon-remove" plain=true"></a>
				</td>
    		</tr>
    	[/#list]
    </table>
</form>



