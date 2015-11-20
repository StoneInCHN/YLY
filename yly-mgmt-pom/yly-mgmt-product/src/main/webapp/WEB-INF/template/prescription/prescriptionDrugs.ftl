	<form id="addPrescriptionDrugs_form" method="post">
		<input type="hidden" name="elderlyInfoID" id="addPrescription_elderlyInfoID">
		<input type="hidden" name="drugsInfoID" id = "drugsInfoID" value="${drugsInfo.id}">     
	    
    	<table border="0">
	    	<tr >
	    	<th>药品名称:</th>
	    		<td>
	    			<input class="easyui-textbox" name="name" id="name" value="${drugsInfo.name}"/>
	    			
	    		</td>
	    	</tr>
	    	<tr class='hidCHI'>
	    		<th>单次用量:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="singleDose" id="singleDose"/>
	    		</td>
	    		<th>${message("yly.drugsInfo.doseFrequency")}:</th>
	    			<td>
	    			
		    		<input class="easyui-combobox" data-options="
					     valueField: 'label',
						     textField: 'value',
						     data: [{
						      label: 'Qd',
						      value: '${message("yly.drugsInfo.doseFrequency.Qd")}'
						     },{
						      label: 'Bid',
						      value: '${message("yly.drugsInfo.doseFrequency.Bid")}'
						     },{
						      label: 'Tid',
						      value: '${message("yly.drugsInfo.doseFrequency.Tid")}'
						     },{
						      label: 'Qid',
						      value: '${message("yly.drugsInfo.doseFrequency.Qid")}'
						     },{
						      label: 'Q2h',
						      value: '${message("yly.drugsInfo.doseFrequency.Q2h")}'
						     },{
						      label: 'Q4h',
						      value: '${message("yly.drugsInfo.doseFrequency.Q4h")}'
						     }
						     ],
						     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="doseFrequency" id="prescriptionDrugDoseFrequency" style="width:110px;"/>
				</td> 
	    	</tr>
	    	<tr class='hidCHI'>
	    		
	    		<th>药品用法:</th>
	    		<td>
	    			 <input class="easyui-combobox" name="prescriptionDrugUseMethod" id="prescriptionDrugUseMethod" />
	    		</td>
	    		<th>用药天数:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="medicationDays" id="prescriptionDrugMedicationDays" />
	    		</td>
	    	</tr>
	    	<tr>
	    	<th >药总数:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="medicineTotal" id="prescriptionDrugMedicineTotal" />
	    		</td>
	    	</tr>
	    </table>
    </form>   


