<form id="editFixedAssets_form" method="post">   
		 <input value="${fixedAssets.id}" type="hidden" name="id" />
	  <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetNo")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetNo" data-options="required:true" value ="${fixedAssets.assetNo}" validtype="length[0,40]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetName" data-options="required:true" value ="${fixedAssets.assetName}" validtype="length[0,60]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetsDepartment")}:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="assetsDepartment-edit" name="departmentId" data-value ="${fixedAssets.department.id}"/>
	    			 
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetCount")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetCount" data-options="required:true" value ="${fixedAssets.assetCount}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetUnit")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetUnit" value ="${fixedAssets.assetUnit}" validtype="length[0,10]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetUsage")}:</th>
	    		<td>
				     <select id="assetUsage" class="easyui-combobox" name="assetUsage" style="width:80px;">   
						<option value="INSERVICE" [#if fixedAssets.assetUsage == 'INSERVICE'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetUsage.inService")}</option>
						<option value="OUTSERVICE" [#if fixedAssets.assetUsage == 'OUTSERVICE'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetUsage.outService")}</option>
						<option value="NONEED" [#if fixedAssets.assetUsage == 'NONEED'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetUsage.noNeed")}</option> 
				  	</select>   
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetSpecification")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetSpecification" value ="${fixedAssets.assetSpecification}" validtype="length[0,30]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetOrigin")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetOrigin"  value ="${fixedAssets.assetOrigin}" validtype="length[0,20]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetManufacturer")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetManufacturer"  value ="${fixedAssets.assetManufacturer}" validtype="length[0,50]"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetProvider")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetProvider"  value ="${fixedAssets.assetProvider}" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetValue")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetValue" value ="${fixedAssets.assetValue}"/>   
	    		</td>
	    	
	    		<th>${message("yly.fixedAssets.assetsType")}:</th>
	    		<td>
				      <select id="assetsType" class="easyui-combobox" name="dosageUnit" style="width:100px;">   
						<option value="BUILDING" [#if fixedAssets.assetsType == 'BUILDING'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetsType.buiding")}</option>
						<option value="PRODUCTION" [#if fixedAssets.assetsType == 'PRODUCTION'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetsType.production")}</option>
						<option value="VEHICLE" [#if fixedAssets.assetsType == 'VEHICLE'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetsType.vehicle")}</option> 
						<option value="ELECTRONIC" [#if fixedAssets.assetsType == 'ELECTRONIC'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetsType.electronic")}</option>
						<option value="OTHERS" [#if fixedAssets.assetsType == 'OTHERS'] selected = "selected"[/#if]>${message("yly.fixedAssets.assetsType.other")}</option>
				  	</select> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.fixedAssets.assetTime")}:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="assetTime" name="assetTime" value ="${fixedAssets.assetTime}" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="remark" value ="${fixedAssets.remark}" validtype="length[0,50]"/>
	    		</td>
	    	</tr>
	    </table>
</form>



