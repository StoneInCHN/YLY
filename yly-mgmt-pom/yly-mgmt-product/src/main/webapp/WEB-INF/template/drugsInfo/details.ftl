<form id="drugsDetail_form" method="post">   
		 <input value="${drugs.id}" type="hidden" name="id" />
	   <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.drugsInfo.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.name}" name="name" data-options="required:true" />   
	    		</td>
	    	
	    		<th>${message("yly.drugsInfo.alias")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.alias}" name="alias"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.drugsInfo.phoneticCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.phoneticCode}" name="phoneticCode"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.drugCategory")}:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="drugCategoryId" style="width:80px;"> 
						  [#list  drugCategorys as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.drugCategory.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>       
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.drugsInfo.conventionalUnit")}:</th>
	    		<td>
	    			  <select class="easyui-combobox"   name="conventionalUnitId" style="width:80px;"> 
						  [#list  units as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.conventionalUnit.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>   
	    		</td>
	    		<th>${message("yly.drugsInfo.minUnit")}:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="minUnitId" style="width:80px;"> 
						  [#list units as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.minUnit.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.conversionUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.conversionUnit}" name="conversionUnit"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.dosageUnit")}:</th>
	    		<td>
	    			 <select id="dosageUnit" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="G" [#if drugs.dosageUnit == 'G'] selected = "selected"[/#if]>${message("yly.common.G")}</option>
						<option value="KG" [#if drugs.dosageUnit == 'KG'] selected = "selected"[/#if]>${message("yly.common.KG")}</option>
						<option value="MG" [#if drugs.dosageUnit == 'MG'] selected = "selected"[/#if]>${message("yly.common.MG")}</option> 
						<option value="ML" [#if drugs.dosageUnit == 'ML'] selected = "selected"[/#if]>${message("yly.common.ML")}</option> 
						<option value="L" [#if drugs.dosageUnit == 'L'] selected = "selected"[/#if]>${message("yly.common.L")}</option>
				  	</select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugUseMethod")}:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="drugUseMethodId" style="width:80px;"> 
						  [#list drugUseMethods as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.drugUseMethod.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>-->
						  [/#list]
					 </select>    
	    		</td>
	    		<th>${message("yly.drugsInfo.dosage")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.dosage}" name="dosage"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugSpecifications")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.drugSpecifications}" name="drugSpecifications"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.doseFrequency")}:</th>
	    		<td>
	    			<select id="doseFrequency" class="easyui-combobox" name="doseFrequency" style="width:140px;">   
						<option value="Qd" [#if drugs.doseFrequency == 'Qd'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Qd")}</option>
						<option value="Bid" [#if drugs.doseFrequency == 'Bid'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Bid")}</option>
						<option value="Tid" [#if drugs.doseFrequency == 'Tid'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Tid")}</option> 
						<option value="Qid" [#if drugs.doseFrequency == 'Qid'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Qid")}</option> 
						<option value="Q2h" [#if drugs.doseFrequency == 'Q2h'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Q2h")}</option> 
						<option value="Q4h" [#if drugs.doseFrequency == 'Q4h'] selected = "selected"[/#if]>${message("yly.drugsInfo.doseFrequency.Q4h")}</option>						 
				  	</select>    
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.singleDose")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.singleDose}" name="singleDose"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.manufacturer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.manufacturer}" name="manufacturer"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugStatus")}:</th>
	    		<td>
	    			<select id="drugStatus" class="easyui-combobox"  name="drugStatus" style="width:60px;">   
						<option value="ENABLE" [#if drugs.drugStatus == 'ENABLE'] selected = "selected"[/#if]>${message("yly.common.enable")}</option>
						<option value="DISABLE" [#if drugs.drugStatus == 'DISABLE'] selected = "selected"[/#if]>${message("yly.common.disable")}</option> 
				  </select>     
	    		</td>
	    		
    		</tr>
    		
    		<tr>
	    		<th>${message("yly.drugsInfo.prescriptionPrice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.prescriptionPrice}" name="prescriptionPrice"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.purchasePrice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.purchasePrice}" name="purchasePrice"  />   
	    		</td>
	    	</tr>
    		<tr>
    			<th>${message("yly.drugsInfo.description")} :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${drugs.description}" name="description" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${drugs.remark}" name="remark" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



