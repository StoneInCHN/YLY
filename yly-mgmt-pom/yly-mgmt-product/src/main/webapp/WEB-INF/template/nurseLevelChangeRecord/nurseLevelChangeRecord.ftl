<script type="text/javascript" src="${base}/resources/modules/nurseLevelChangeRecord.js"></script>

<div class="easyui-layout" data-options="fit:true">
      <div data-options="region:'west',split:true" style="width:300px" title="过滤">
             <div id="elderlyInfo-panel" class="easyui-panel" title="老人信息"    style="width:293px;height:800px;background:#fafafa;"  data-options="collapsible:true">
		        <fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="nurseChange_elderlyInfo_search_form" class="search-form">
				    	<div class="search-item">
						   <label>姓名:</label>
						   <input type="text" class="easyui-textbox"   id="nurseChange_elderlyName" name="name" validtype="length[0,10]" style="width:64px;"/>
						</div>
						<div class="search-item">
						   <label>编号:</label>
						   <input type="text" class="easyui-textbox"  id="nurseChange_elderlyIdentifier" name="identifier" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="nurseChange_elderlyInfo_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("yly.search")}</button>
				    </div>
				</fieldset>
                <table id="nurseChange_elderlySearch-table-list"></table>
         </div>
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" style="height:400px;background:#fafafa;" data-options="border:false">
			 <fieldset>
			    <legend>护理级别变更记录查询</legend>
			    <form id="nurseChange_search_form" class="search-form">
	    			<div class="search-item">
						<label> 护理级别变更记录的状态:</label>
			    		<input class="easyui-combobox" data-options="
							valueField: 'label',
							textField: 'value',
							data: [{
								label: 'ENABLE',
								value: '启用'
							},{
								label: 'DISABLE',
								value: '已废弃'
							}],
							prompt:'${message("yly.common.please.select")}',panelMaxHeight:60"  id="nurseChange_status" name="nurseChangeStatus" style="width:100px;"/>
					</div>
					<div class="search-item">
					    <label> 变更日期从:</label>
					    <input type="text" class="Wdate" id="nurseChange_startDate" name="nurseChangeStartDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'nurseChange_endDate\')}'});" />
					</div>
					<div class="search-item">
					    <label>${message("yly.to")}:</label>
					   	<input type="text" class="Wdate" id="nurseChange_endDate"  name="nurseChangeEndDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'nurseChange_startDate\')}'});"/>
					</div>
					<input type="hidden" name="nurseChangeElderlyID" id="nurseChange_elderlyIDSearch">  
				</form>
				<div class="search-item">
			  	  <button id="nurseChange_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
			    </div>
			</fieldset>
			<table id="nurseChange-table-list"></table>
			<div id="nurseChange_manager_tool">
				<div class="tool-filter"></div>
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="nurseChange_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="nurseChange_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="nurseChange_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>				
			</div> 
			</div> 
		</div>        
    </div>
</div>
<div id="add_nurseChange">
	<form id="addNurseChange_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyInfo.id" id="addNurseChange_elderlyInfoID">  
	   <fieldset> 
	   <legend>护理变更前信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addNurseChange_elderlyName" style="width:150px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchElderlyInfo('add_NurseChange')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>变更前等级: </th>
	    		<td>
					<input class="easyui-combobox addNurseChange-nurseLevel" id="addNurseChange_oldNurseLevel"  name="oldNurseLevel" style="width:120px;" data-options="editable:false" disabled=true>
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理变更后信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  id="addNurseChange_elderlyName_Temp" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>变更后等级: </th>
	    		<td>
					<input class="easyui-combobox addNurseChange-nurseLevel"  id="addNurseChange_newNurseLevel" prompt="${message("yly.common.please.select")}"  name="newNursingLevelId" style="width:120px;" data-options="required:true,editable:false" >
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   name="remark" data-options="multiline:true,height:100,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>
</div>
<div id="view_nurseChange"></div>
<div id="edit_nurseChange"></div>
<div id="nurseChange_view_elderlyInfo"></div>


