<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/medicalRecord.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.fixedAssets.search")}</legend>
	    <form id="medicalRecord-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("yly.elderly.name")}:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" />
			</div>
			<div class="search-item">
			    <label> ${message("yly.createDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="medicalRecord-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="medicalRecord-table-list"></table>
<div id="medicalRecord_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="medicalRecord_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="medicalRecord_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="medicalRecord_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addMedicalRecord"> 
	<form id="addMedicalRecord_form" method="post" class="form-table">
		<input type="hidden" name="elderlyInfoID" id="addMedicalRecord_elderlyInfoID">   
	    <table class="table table-striped"  border="0">
	    	<tr>
	    	<th>${message("yly.common.elderly")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfoName" id="addMedicalRecord_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('addMedicalRecord_elderlyInfo')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<th>${message("yly.medicalRecord.chiefComplaint")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="chiefComplaint" validtype="length[0,40]" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.allergicHistory")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" id="allergicHistory" name="allergicHistory" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    	
	    		<th>${message("yly.medicalRecord.clinicDiagnosis")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="clinicDiagnosis" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.treatmentAdvice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="treatmentAdvice" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    	
	    		<th>${message("yly.medicalRecord.outpatientTreatment")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="outpatientTreatment" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.medicalRecord.outpatientMedicalAdvice")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="outpatientMedicalAdvice" data-options="multiline:true" style="height:100px;width:300px"/>   
	    		</td>
	    		<th>${message("yly.medicalRecord.medicalHistory")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="medicalHistory" data-options="multiline:true" style="height:100px;width:300px"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editMedicalRecord"></div>
<div id="medicalRecordDetail"></div>




