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
	    			 <input class="easyui-textbox" id="department" name="departmentId" value ="${fixedAssets.department.name}"/>   
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
	    		<!--<td>
	    			<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'INSERVICE',
				      value: '使用中'
				     },{
				      label: 'OUTSERVICE',
				      value: '未使用'
				     },{
				      label: 'NONEED',
				      value: '不需用'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetUsage" style="width:110px;"/>
	    		</td>-->
	    		<td>
    			  	<select id="assetUsage" class="easyui-combobox" name="assetUsage" disabled="disabled" style="width:120px;">   
						<option value="${fixedAssets.assetUsage}" selected="selected">${fixedAssets.assetUsage}</option>
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
	    		<!--<td>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'BUILDING',
				      value: '房屋建筑物'
				     },{
				      label: 'PRODUCTION',
				      value: '生产经营'
				     },{
				      label: 'VEHICLE',
				      value: '交通工具'
				     },{
				      label: 'ELECTRONIC',
				      value: '电子设备'
				     },{
				      label: 'other',
				      value: '其他'
				     }],
				     prompt:'${message("yly.common.please.select")}',panelMaxHeight:100"  name="assetType" style="width:110px;"/>
	    		</td>-->
	    		<td>
    			  	<select id="assetsType" class="easyui-combobox" name="assetUsage" disabled="disabled" style="width:120px;">   
						<option value="${fixedAssets.assetsType}" selected="selected">${fixedAssets.assetsType}</option>
				    </select>
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



