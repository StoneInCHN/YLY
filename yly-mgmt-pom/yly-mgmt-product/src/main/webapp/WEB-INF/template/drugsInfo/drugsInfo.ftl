<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/drugs.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.drugsInfo.search")}</legend>
	    <form id="search-form" class="search-form">
	    <div class="search-item">
			    <label> ${message("yly.drugsInfo.search.name")}:</label>
			    <input type="text" class="easyui-textbox" id="drugName" name="drugName" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.drugsInfo.search.time")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="drugs-table-list"></table>
<div id="drugs_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="drugs_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="drugs_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="drugs_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addDrugs"> 
	<form id="addDrugs_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.drugsInfo.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" data-options="required:true" validtype="length[0,60]"/>   
	    		</td>
	    	
	    		<th>${message("yly.drugsInfo.alias")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="alias"  validtype="length[0,60]"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.drugsInfo.phoneticCode")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="phoneticCode"  validtype="length[0,20]"/>   
	    		</td>
	    		<th>${message("yly.drugsInfo.drugCategory")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugCategoryId" id="drugCategory"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.drugsInfo.conventionalUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "conventionalUnit" name="conventionalUnitId"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.minUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="minUnit" name="minUnitId"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.conversionUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="conversionUnit"  />   
	    		</td>
	    		<th>${message("yly.drugsInfo.dosageUnit")}:</th>
	    		<td>
	    			 <select id="dosageUnit" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="G">${message("yly.common.G")}</option>
						<option value="KG">${message("yly.common.KG")}</option>
						<option value="MG">${message("yly.common.MG")}</option> 
						<option value="ML">${message("yly.common.ML")}</option> 
						<option value="L">${message("yly.common.L")}</option>
				  	</select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugUseMethod")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugUseMethodId" id="drugUseMethod" />   
	    		</td>
	    		<th>${message("yly.drugsInfo.dosage")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="dosage"  min="0.00"/>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugSpecifications")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugSpecifications"  validtype="length[0,30]"/>   
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
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.singleDose")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="singleDose" min="0.00" />   
	    		</td>
	    		<th>${message("yly.drugsInfo.manufacturer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="manufacturer"  validtype="length[0,150]"/>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>${message("yly.drugsInfo.drugStatus")}:</th>
	    		<td>
	    			<select id="drugStatus" class="easyui-combobox" name="drugStatus" style="width:60px;">   
						<option value="ENABLE">${message("yly.common.enable")}</option>
						<option value="DISABLE">${message("yly.common.disable")}</option> 
				  </select>     
	    		</td>
	    		
    		</tr>
    		
    		<tr>
	    		<th>${message("yly.drugsInfo.prescriptionPrice")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="prescriptionPrice"  min="0.00"/>   
	    		</td>
	    		<th>${message("yly.drugsInfo.purchasePrice")}:</th>
	    		<td>
	    			 <input class="easyui-numberbox" type="text" name="purchasePrice" min="0.00" />   
	    		</td>
	    	</tr>
    		<tr>
    			<th>${message("yly.drugsInfo.description")} :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="description" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDrugs"></div>
<div id="drugsDetail"></div>




