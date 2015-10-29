<form id="fixedAssetsDetail_form" method="post">   
		 <input value="${fixedAssets.id}" type="hidden" name="id" />
	  <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>资产编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetNo" data-options="required:true" value ="${fixedAssets.assetNo}"/>   
	    		</td>
	    	
	    		<th>资产名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetName" data-options="required:true" value ="${fixedAssets.assetName}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>存放地点（部门）:</th>
	    		<td>
	    			 <input class="easyui-combobox" id="department" name="departmentId" value ="${fixedAssets.department.name}"/>   
	    		</td>
	    	
	    		<th>数量:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetCount" data-options="required:true" value ="${fixedAssets.assetCount}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>计量单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetUnit" value ="${fixedAssets.assetUnit}"/>   
	    		</td>
	    	
	    		<th>使用状态:</th>
	    		<td>
				     <select id="assetUsage" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="INSERVICE" [#if fixedAssets.assetUsage == 'INSERVICE'] selected = "selected"[/#if]>使用中</option>
						<option value="OUTSERVICE" [#if fixedAssets.assetUsage == 'OUTSERVICE'] selected = "selected"[/#if]>未使用</option>
						<option value="NONEED" [#if fixedAssets.assetUsage == 'NONEED'] selected = "selected"[/#if]>不需用</option> 
				  	</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>规格型号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetSpecification" value ="${fixedAssets.assetSpecification}"/>   
	    		</td>
	    	
	    		<th>产地:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetOrigin"  value ="${fixedAssets.assetOrigin}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>制造商:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetManufacturer"  value ="${fixedAssets.assetManufacturer}"/>   
	    		</td>
	    	
	    		<th>供应商:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="assetProvider"  value ="${fixedAssets.assetProvider}"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>资产价值（RMB）:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="assetValue" value ="${fixedAssets.assetValue}"/>   
	    		</td>
	    	
	    		<th>资产类型:</th>
	    		<td>
				    <select id="assetsType" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="BUILDING" [#if fixedAssets.assetsType == 'BUILDING'] selected = "selected"[/#if]>房屋建筑物</option>
						<option value="PRODUCTION" [#if fixedAssets.assetsType == 'PRODUCTION'] selected = "selected"[/#if]>生产经营</option>
						<option value="VEHICLE" [#if fixedAssets.assetsType == 'VEHICLE'] selected = "selected"[/#if]>交通工具</option> 
						<option value="ELECTRONIC" [#if fixedAssets.assetsType == 'ELECTRONIC'] selected = "selected"[/#if]>电子设备</option>
						<option value="OTHERS" [#if fixedAssets.assetsType == 'OTHERS'] selected = "selected"[/#if]>其他</option>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>录入日期:</th>
	    		<td>
	    			 <input type="text" class="Wdate" id="assetTime" name="assetTime" value ="${fixedAssets.assetTime}" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
	    		</td>
	    	
	    		<th>备注:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="remark" value ="${fixedAssets.remark}"/>
	    		</td>
	    	</tr>
	    </table>
</form>



