	<form id="editPrescription_form" method="post">
		<input type="hidden" name="elderlyInfoID" id="editPrescription_elderlyInfoID" value="${prescription.elderlyInfo.id}">
		<input type="hidden" name="drugsInfoID" id="editPrescription_drugsInfoID">     
	    <input type="hidden" name="id" value="${prescription.id}">
    	<table border="0">
	    	<tr>
	    		<th style="width:80px;">${message("yly.common.elderly")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" value="${prescription.elderlyInfo.name}" name="elderlyInfoName" id="editPrescription_elderlyInfo" panelHeight="150px" style="width:80px;" data-options="required:true,editable:false " />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('editPrescription_elderlyInfo')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>${message("yly.prescription.prescriptionType")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'CHINESE_MEDICINE',
					      value: '${message("yly.prescription.prescriptionType.CH")}'
					      [#if prescription.prescriptionType =="CHINESE_MEDICINE"]
					      ,selected:true
					      [/#if]
					     },{
					      label: 'WESTEN_MEDICINE',
					      value: '${message("yly.prescription.prescriptionType.WEST")}'
					       [#if prescription.prescriptionType =="WESTEN_MEDICINE"]
					      ,selected:true
					      [/#if]
					     }],
					     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="prescriptionType" id="editPrescriptionType" style="width:110px;"/>
				        
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    			<select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if prescription.elderlyInfo.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if prescription.elderlyInfo.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			 <input class="input_text_line" name="age" id="age" value ="${prescription.elderlyInfo.age}" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr class="edithid">
	    		<th>每日剂量:</th>
	    		<td>
	    			 <input class="input_text_line" value="${prescription.dailyDose}" name="dailyDose" id="dailyDose" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>药品用法:</th>
	    		<td>
	    			 <input class="easyui-combobox" name="prescriptionUseMethodId" id="editPrescriptionUseMethod" style="width:80px;" data-value="${prescription.drugUseMethod.id}"/>
	    		</td>
	    		<th>用药天数:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="medicationDays" id="medicationDays" value="${prescription.medicationDays}" style="width:80px;"/>
	    		</td>
	    			<th>${message("yly.drugsInfo.doseFrequency")}:</th>
	    		<td>
	    			<select id="doseFrequency" class="easyui-combobox" name="doseFrequency" style="width:120px;">   
						<option value="Qd" [#if prescription.doseFrequency =="Qd"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Qd")}</option>
						<option value="Bid" [#if prescription.doseFrequency =="Bid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Bid")}</option>
						<option value="Tid" [#if prescription.doseFrequency =="Tid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Tid")}</option> 
						<option value="Qid" [#if prescription.doseFrequency =="Qid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Qid")}</option> 
						<option value="Q2h" [#if prescription.doseFrequency =="Q2h"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Q2h")}</option> 
						<option value="Q4h" [#if prescription.doseFrequency =="Q4h"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Q4h")}</option>						 
				  	</select>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>就诊时间:</th>
	    		<td>
	    			 <input class="input_text_line" name="time" id="time" value="${prescription.time}" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    </table>
	    <div id="editPrescriptionDrugs"></div>
	    <div align="center" style="font-size: large">处方笺</div>
	    <div style="width:100%;height:1px;border-top:solid rgb(0,0,0) 1px;margin-bottom:10px;">
		<table id ="editPrescriptionDrugs-table-list" border="0">
			<input type= "hidden" id ="drugsItemSize" value="${prescription.prescriptionDrugsItems?size}"/>
			[#list prescription.prescriptionDrugsItems as item]
				<tr id="drugsItem${item.id}">
				<input type="hidden" name="prescriptionDrugsItems[${item_index}].id" value="${item.id}"/>
					<th>药品名称:</th>
					<td>
						<input class="input_text_line" value = "${item.drugsInfo.name}" type="text" validtype="length[0,15]" style="width:60px;"/>
						<input type="hidden" name="prescriptionDrugsItems[${item_index}].drugsInfo.id" value="${item.drugsInfo.id}"/>
						</td>
					[#if prescription.prescriptionType != 'CHINESE_MEDICINE']
					<th>单次用量:</th>
					<td>
						<input class="input_text_line" type="text" name="prescriptionDrugsItems[${item_index}].singleDose" value="${item.singleDose}" style="width:25px;"/>
					</td>
					<th>频度:</th>
					<td>
						<select id="doseFrequency" class="easyui-combobox" name="prescriptionDrugsItems[${item_index}].doseFrequency" style="width:120px;">   
							<option value="Qd" [#if item.doseFrequency =="Qd"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Qd")}</option>
							<option value="Bid" [#if item.doseFrequency =="Bid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Bid")}</option>
							<option value="Tid" [#if item.doseFrequency =="Tid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Tid")}</option> 
							<option value="Qid" [#if item.doseFrequency =="Qid"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Qid")}</option> 
							<option value="Q2h" [#if item.doseFrequency =="Q2h"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Q2h")}</option> 
							<option value="Q4h" [#if item.doseFrequency =="Q4h"] selected="selected" [/#if]>${message("yly.drugsInfo.doseFrequency.Q4h")}</option>						 
					  	</select>
					</td>
					<th>药品用法:</th>
					<td>
					  	<input class="input_text_line" use-method="editPrescriptionDrugsMethod" name="prescriptionDrugsItems[${item_index}].drugUseMethod.id" id="editPrescriptionDrugsItems${item_index}_drugUseMethod" style="width:80px;" data-value="${item.drugUseMethod.id}"/>
					 </td>
					<th>用药天数:</th>
					<td>
						<input class="input_text_line" value = "${item.medicationDays}" type="text" name="prescriptionDrugsItems[${item_index}].medicationDays" style="width:50px;"\/>
					</td>
					[/#if]
					<th>药总数:</th>
					<td>
						<input class="input_text_line" value = "${item.medicineTotal}" type="text" name="prescriptionDrugsItems[${item_index}].medicineTotal" style="width:50px;"\/>
					</td>
					<td colspan='2'>
						<a href="#" class="easyui-linkbutton family-member-remove red-color" plain=true onclick="prescriptionDrugsItem_manager_tool.editRemove(${item.id});"><i class="fa fa-times fa-2x"></i></a>
					</td>
					</tr>
			[/#list]
		</table>
		<div><a href="javascript:;" id="addprescriptionDrugs" class="btn green-color" onclick="prescription_manager_tool.editAddDrgus()"><i class="fa fa-plus-square-o fa-2x"></i></a><div>
    </form>   
</div>  

<div id="editPrescriptionDrugs"></div>


