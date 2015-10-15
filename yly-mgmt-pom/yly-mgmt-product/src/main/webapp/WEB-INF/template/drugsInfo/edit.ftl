<form id="editDrugs_form" method="post">   
		 <input value="${drugs.id}" type="hidden" name="id" />
	   <table class="table table-striped">
	    	<tr>
	    		<th>药品名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.name}" name="name" data-options="required:true" />   
	    		</td>
	    	
	    		<th>别名:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.alias}" name="alias"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>拼音简码:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.phoneticCode}" name="phoneticCode"  />   
	    		</td>
	    		<th>药品分类:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="drugCategoryId" style="width:80px;"> 
						  [#list  drugCategorys as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.drugCategory.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>       
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>药品常用单位:</th>
	    		<td>
	    			  <select class="easyui-combobox"   name="conventionalUnitId" style="width:80px;"> 
						  [#list  units as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.conventionalUnit.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>   
	    		</td>
	    		<th>最小单位:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="minUnitId" style="width:80px;"> 
						  [#list units as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.minUnit.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>
						  [/#list]
					 </select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>换算单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.conversionUnit}" name="conversionUnit"  />   
	    		</td>
	    		<th>剂量单位:</th>
	    		<td>
	    			 <select id="dosageUnit" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="G" [#if drugs.dosageUnit == 'G'] selected = "selected"[/#if]>克</option>
						<option value="KG" [#if drugs.dosageUnit == 'KG'] selected = "selected"[/#if]>千克</option>
						<option value="MG" [#if drugs.dosageUnit == 'MG'] selected = "selected"[/#if]>毫克</option> 
						<option value="ML" [#if drugs.dosageUnit == 'ML'] selected = "selected"[/#if]>毫升</option> 
						<option value="L" [#if drugs.dosageUnit == 'L'] selected = "selected"[/#if]>升</option>
				  	</select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品用法:</th>
	    		<td>
	    			 <select class="easyui-combobox"   name="drugUseMethodId" style="width:80px;"> 
						  [#list drugUseMethods as systemConfig]
								<option value="${systemConfig.id}" [#if systemConfig.id == drugs.drugUseMethod.id] selected = "selected"[/#if]>${systemConfig.configValue}</option>-->
						  [/#list]
					 </select>    
	    		</td>
	    		<th>剂量:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.dosage}" name="dosage"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品规格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.drugSpecifications}" name="drugSpecifications"  />   
	    		</td>
	    		<th>频度:</th>
	    		<td>
	    			<select id="doseFrequency" class="easyui-combobox" name="doseFrequency" style="width:140px;">   
						<option value="Qd" [#if drugs.doseFrequency == 'Qd'] selected = "selected"[/#if]>一天一次(Qd)</option>
						<option value="Bid" [#if drugs.doseFrequency == 'Bid'] selected = "selected"[/#if]>一天两次(Bid)</option>
						<option value="Tid" [#if drugs.doseFrequency == 'Tid'] selected = "selected"[/#if]>一天三次(Tid)</option> 
						<option value="Qid" [#if drugs.doseFrequency == 'Qid'] selected = "selected"[/#if]>一天四次(Qid)</option> 
						<option value="Q2h" [#if drugs.doseFrequency == 'Q2h'] selected = "selected"[/#if]>两小时一次(Q2h)</option> 
						<option value="Q4h" [#if drugs.doseFrequency == 'Q4h'] selected = "selected"[/#if]>四小时一次(Q4h)</option>						 
				  	</select>    
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>单次用量:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.singleDose}" name="singleDose"  />   
	    		</td>
	    		<th>生产厂家:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.manufacturer}" name="manufacturer"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品状态:</th>
	    		<td>
	    			<select id="drugStatus" class="easyui-combobox"  name="drugStatus" style="width:60px;">   
						<option value="ENABLE" [#if drugs.drugStatus == 'ENABLE'] selected = "selected"[/#if]>启用</option>
						<option value="DISABLE" [#if drugs.drugStatus == 'DISABLE'] selected = "selected"[/#if]>禁用</option> 
				  </select>     
	    		</td>
	    		
    		</tr>
    		
    		<tr>
	    		<th>处方价格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.prescriptionPrice}" name="prescriptionPrice"  />   
	    		</td>
	    		<th>采购价格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" value="${drugs.purchasePrice}" name="purchasePrice"  />   
	    		</td>
	    	</tr>
    		<tr>
    			<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${drugs.description}" name="description" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>备注:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${drugs.remark}" name="remark" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    </table>
</form>



