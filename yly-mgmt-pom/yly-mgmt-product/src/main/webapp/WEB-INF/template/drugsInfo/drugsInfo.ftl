<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/drugs.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>药品查询</legend>
	    <form id="search-form" class="search-form">
			<div class="search-item">
			    <label> 查询时间从:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="drugs-table-list"></table>
<div id="drugs_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="drugs_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="drugs_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="drugs_manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addDrugs"> 
	<form id="addDrugs_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>药品名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" data-options="required:true" />   
	    		</td>
	    	
	    		<th>别名:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="alias"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>拼音简码:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="phoneticCode"  />   
	    		</td>
	    		<th>药品分类:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugCategoryId" id="drugCategory"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>药品常用单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id = "conventionalUnit" name="conventionalUnitId"  />   
	    		</td>
	    		<th>最小单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="minUnit" name="minUnitId"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>换算单位:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="conversionUnit"  />   
	    		</td>
	    		<th>剂量单位:</th>
	    		<td>
	    			 <select id="dosageUnit" class="easyui-combobox" name="dosageUnit" style="width:60px;">   
						<option value="G">克</option>
						<option value="KG">千克</option>
						<option value="MG">毫克</option> 
						<option value="ML">毫升</option> 
						<option value="L">升</option>
				  	</select>   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品用法:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugUseMethodId" id="drugUseMethod" />   
	    		</td>
	    		<th>剂量:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="dosage"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品规格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="drugSpecifications"  />   
	    		</td>
	    		<th>频度:</th>
	    		<td>
	    			<select id="doseFrequency" class="easyui-combobox" name="doseFrequency" style="width:140px;">   
						<option value="Qd">一天一次(Qd)</option>
						<option value="Bid">一天两次(Bid)</option>
						<option value="Tid">一天三次(Tid)</option> 
						<option value="Qid">一天四次(Qid)</option> 
						<option value="Q2h">两小时一次(Q2h)</option> 
						<option value="Q4h">四小时一次(Q4h)</option>						 
				  	</select>    
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>单次用量:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="singleDose"  />   
	    		</td>
	    		<th>生产厂家:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="manufacturer"  />   
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>药品状态:</th>
	    		<td>
	    			<select id="drugStatus" class="easyui-combobox" name="drugStatus" style="width:60px;">   
						<option value="ENABLE">启用</option>
						<option value="DISABLE">禁用</option> 
				  </select>     
	    		</td>
	    		
    		</tr>
    		
    		<tr>
	    		<th>处方价格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="prescriptionPrice"  />   
	    		</td>
	    		<th>采购价格:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="purchasePrice"  />   
	    		</td>
	    	</tr>
    		<tr>
    			<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="description" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    	<tr>
    			<th>备注:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" data-options="multiline:true,height:90,width:300" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editDrugs"></div>




