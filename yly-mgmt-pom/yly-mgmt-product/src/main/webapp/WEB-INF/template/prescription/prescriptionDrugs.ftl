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
	    			<select id="doseFrequency" class="easyui-combobox" name="doseFrequency" style="width:140px;">   
						<option value="Qd">${message("yly.drugsInfo.doseFrequency.Qd")}</option>
						<option value="Bid">${message("yly.drugsInfo.doseFrequency.Bid")}</option>
						<option value="Tid">${message("yly.drugsInfo.doseFrequency.Tid")}</option> 
						<option value="Qid">${message("yly.drugsInfo.doseFrequency.Qid")}</option> 
						<option value="Q2h">${message("yly.drugsInfo.doseFrequency.Q2h")}</option> 
						<option value="Q4h">${message("yly.drugsInfo.doseFrequency.Q4h")}</option>						 
				  	</select>    
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


