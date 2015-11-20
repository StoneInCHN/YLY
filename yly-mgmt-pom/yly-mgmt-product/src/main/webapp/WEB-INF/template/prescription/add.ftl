<div id="addPrescription"> 
	<form id="addPrescription_form" method="post" class="form-table">
		<input type="hidden" name="elderlyInfoID" id="addPrescription_elderlyInfoID">
		<input type="hidden" name="drugsInfoID" id="addPrescription_drugsInfoID">     
	    
    	<table border="0">
	    	<tr>
	    		<th>${message("yly.common.elderly")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addPrescription_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    		</td>
	    		<td>	 
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addPrescription_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<th>${message("yly.prescription.prescriptionType")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'CHINESE_MEDICINE',
					      value: '${message("yly.prescription.prescriptionType.CH")}'
					     },{
					      label: 'WESTEN_MEDICINE',
					      value: '${message("yly.prescription.prescriptionType.WEST")}'
					     }],
					     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="prescriptionType" id="addPrescriptionType" style="width:110px;"/>
				        
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    			 <input class="input_text_line" name="gender" id="addPrescription_elderlyInfo_gender" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			 <input class="input_text_line" name="age" id="addPrescription_elderlyInfo_age" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>就诊时间:</th>
	    		<td>
	    			 <input class="input_text_line" name="time" id="addPrescriptionTime" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr class="hid">
	    		<th>每日剂量:</th>
	    		<td>
	    			 <input class="input_text_line" name="dailyDose" id="addPrescriptiondailyDose" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>药品用法:</th>
	    		<td>
	    			 <input class="easyui-combobox" name="prescriptionDrugsUseMethodId" id="addPrescriptionUseMethod" style="width:80px;" />
	    		</td>
	    		<th>用药天数:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="medicationDays" id="addPrescriptionmedicationDays" />
	    		</td>
	    			<th>${message("yly.drugsInfo.doseFrequency")}:</th>
	    		<td>
	    			<select id="addPrescriptiondoseFrequency" class="easyui-combobox" name="doseFrequency" style="width:120px;">   
						<option value="Qd">${message("yly.drugsInfo.doseFrequency.Qd")}</option>
						<option value="Bid">${message("yly.drugsInfo.doseFrequency.Bid")}</option>
						<option value="Tid">${message("yly.drugsInfo.doseFrequency.Tid")}</option> 
						<option value="Qid">${message("yly.drugsInfo.doseFrequency.Qid")}</option> 
						<option value="Q2h">${message("yly.drugsInfo.doseFrequency.Q2h")}</option> 
						<option value="Q4h">${message("yly.drugsInfo.doseFrequency.Q4h")}</option>						 
				  	</select>    
	    		</td>
	    	</tr>
	    </table>
	    <div align="center" style="font-size: large">处方笺</div>
	    <div style="width:100%;height:1px;border-top:solid rgb(0,0,0) 1px;margin-bottom:10px;">
		<table id ="addPrescriptionDrugs-table-list">
		</table>
		<div>
			<a href="javascript:;" id="addprescriptionDrugs" class="btn green-color" onclick="prescription_manager_tool.addDrgus()"><i class="fa fa-plus-square-o fa-2x"></i></a>
		</div>
    </form>   
</div>  
</div>



