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
					     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="prescriptionType" style="width:110px;"/>
				        
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    			 <input class="input_text_line" name="gender" id="gender" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			 <input class="input_text_line" name="age" id="age" style = "width:20px" data-options="required:true,editable:false" />
	    		</td>
	    		<th>就诊时间:</th>
	    		<td>
	    			 <input class="input_text_line" name="time" id="phone" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	
	    </table>
	    <div id="addPrescriptionDrugs"></div>
	    <div align="center" style="font-size: large">处方笺</div>
	    <div style="width:100%;height:1px;border-top:solid rgb(0,0,0) 1px;margin-bottom:10px;">
		<table id ="prescriptionDrugsAdd-table-list">
			<tr>
				<td colspan="9">
					<a href="javascript:;" id="addprescriptionDrugs" class="btn green-color" onclick="prescription_manager_tool.addDrgus()"><i class="fa fa-plus-square-o fa-2x"></i></a>
				</td>
			</tr>
		</table>
		
    </form>   
</div>  
</div>

<div id="prescriptionDrugs"></div>

