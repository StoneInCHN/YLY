<script type="text/javascript" src="${base}/resources/modules/nurseArrangement.js"></script>

<div class="easyui-layout" data-options="fit:true">
      <div data-options="region:'west',split:true" style="width:300px" title="过滤">
             <div id="elderlyInfo-panel" class="easyui-panel" title="老人信息"    style="width:293px;height:400px;background:#fafafa;"  data-options="collapsible:true">
		        <fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="elderlyInfo_search_form" class="search-form">
				    	<div class="search-item">
						   <label>姓名:</label>
						   <input type="text" class="easyui-textbox"   id="elderlyName" name="name" validtype="length[0,10]" style="width:64px;"/>
						</div>
						<div class="search-item">
						   <label>编号:</label>
						   <input type="text" class="easyui-textbox"  id="elderlyIdentifier" name="identifier" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="elderlyInfo_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("yly.search")}</button>
				    </div>
				</fieldset>
                <table id="elderlyInfoSearch-table-list"></table>
         </div>
         <div id="nurseAssistant-panel" class="easyui-panel" title="护理员信息"  style="width:293px;height:400px;background:#fafafa;"  data-options="collapsible:true">
			        <fieldset style="padding:5px 0px 4px 4px;margin:0px">
				        <form id="nurseAssistant_search_form" class="search-form">
					    	<div class="search-item">
							   <label>姓名:</label>
							   <input type="text" class="easyui-textbox"    id="nurseAssistantName" name="realNameSearch" validtype="length[0,10]" style="width:65px;"/>
							</div>
							<div class="search-item">
							   <label>编号:</label>
							   <input type="text" class="easyui-textbox"    id="staffID" name="staffIDSearch" validtype="length[0,10]" style="width:65px;"/>
							</div>
						</form>
						<div class="search-item">
					  	  <button id="nurseAssistant_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
					    </div>	   
				 </fieldset>     
               <table id="nurseAssistantSearch-table-list"></table>
         </div>  
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" style="height:400px;background:#fafafa;" data-options="border:false">
			 <fieldset>
			    <legend>护理员安排查询</legend>
			    <form id="nurseArrangement_search_form" class="search-form">
			    	<div class="search-item">
					   <label>护理名称:</label>
					   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="nurseName" name="nurseNameSearch" validtype="length[0,50]" style="width:110px;"/>
					</div>
					<div class="search-item">
					    <label> 护理日期从:</label>
					    <input type="text" class="Wdate" id="nurseStartDate" name="nurseStartDateSearch"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'nurseEndDate\')}'});" />
					</div>
					<div class="search-item">
					    <label>${message("yly.to")}:</label>
					   	<input type="text" class="Wdate" id="nurseEndDate"  name="nurseEndDateSearch"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'nurseStartDate\')}'});"/>
					</div>
					<input type="hidden" name="elderlyIDSearch" id="elderlyIDSearch">  
					<input type="hidden" name="nurseAssistantIDSearch" id="nurseAssistantIDSearch">  
				</form>
				<div class="search-item">
			  	  <button id="nurseArrangement_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
			    </div>
			</fieldset>
			<table id="nurseArrangement-table-list"></table>
			<div id="nurseArrangement_manager_tool">
				<div class="tool-filter"></div>
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="nurseArrangement_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="nurseArrangement_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="nurseArrangement_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>				
			</div> 
			</div> 
			<div class="easyui-panel" style="height:400px;background:#fafafa;" data-options="border:false">
			<fieldset>
			    <legend>护理员护理明细查询</legend>
			    <form id="nurseArrangementRecord_search_form" class="search-form">
			    	<div class="search-item">
					   <label>护理名称:</label>
					   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  name="nurseNameSearchForRecord" validtype="length[0,50]" style="width:110px;"/>
					</div>
					<input type="hidden" name="nurseArrangemenIDSearch" id="nurseArrangemenIDSearch">
				</form>
				<div class="search-item">
			  	  <button id="nurseArrangementRecord_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
			    </div>
			</fieldset>
			<table id="nurseArrangementRecord-table-list"></table>
			<div id="nurseArrangementRecord_manager_tool">
				<div class="tool-filter"></div>
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="nurseArrangementRecord_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="nurseArrangementRecord_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="nurseArrangementRecord_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>				
			</div> 
			</div> 
		</div>        
    </div>
</div>
<div id="add_nurseArrangement">
	<form id="addNurseArrangement_form" method="post" class="form-table"> 
	    <input type="hidden" name="elderlyInfo.id" id="addNurseArrangement_elderlyInfoID">  
	    <input type="hidden" name="nurseAssistant.id" id="addNurseArrangement_nurseAssistantID">  
	   <fieldset> 
	   <legend>基本信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addNurseArrangement_elderlyName" style="width:150px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchElderlyInfo('add_NurseArrangement')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  prompt="${message("yly.common.please.select")}" id="addNurseArrangement_nurseAssistantName" style="width:145px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchTenantUser('add_NurseArrangement')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>老人床位: </th>
	    		<td>
	    			 <input class="easyui-textbox"   id="addNurseArrangement_bedLocation" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理级别: </th>
	    		<td>
	    			 <input class="easyui-textbox"   id="addNurseArrangement_nursingLevel" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   id="addNurseArrangement_nurseName" name="nurseName" style="width:400px;" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" id="addNurseArrangement_nurseStartDate" name="nurseStartDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'addNurseArrangement_nurseEndDate\')}'});" />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate" id="addNurseArrangement_nurseEndDate"  name="nurseEndDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNurseArrangement_nurseStartDate\')}'});"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   id="addNurseArrangement_remark" name="remark" data-options="multiline:true,height:100,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>
</div>
<div id="view_nurseArrangement"></div>
<div id="edit_nurseArrangement"></div>
<div id="view_elderlyInfo"></div>
<div id="view_nurseAssistant"></div>
<div id="add_nurseArrangementRecord">
	<form id="addNurseArrangementRecord_form" method="post" class="form-table"> 
		<input type="hidden" name="nurseArrangementID" id="addNurseArrangementRecord_ID">  
		<fieldset>
	     <legend>护理信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>护理名称: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"   name="nurseName"
	    			 					id="addNurseArrangementRecord_nurseName"  style="width:400px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchNurseArrangement('add_NurseArrangementRecord')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>开始日期: </th>	
	    		<td>
	    			 <input type="text" class="Wdate" id="addNurseArrangementRecord_nurseStartDate"  data-options="required:true,editable:false" 
	    			 							onclick="WdatePicker({maxDate: '#F{$dp.$D(\'addNurseArrangementRecord_nurseEndDate\')}'});" />
	    		</td>
	    		<th>结束日期: </th>
	    		<td>
	    			 <input type="text" class="Wdate" id="addNurseArrangementRecord_nurseEndDate"  data-options="required:true,editable:false" 
	    			 							onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNurseArrangementRecord_nurseStartDate\')}'});"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>执行时间: </th>
	    		<td colspan='3'>
	    			 <input type="text" class="Wdate" id="addNurseArrangementRecord_nurseServiceTime"  name="nurseServiceTime" data-options="required:true" 
	    			 			onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNurseArrangementRecord_nurseStartDate\')}',maxDate: '#F{$dp.$D(\'addNurseArrangementRecord_nurseEndDate\')}'});" />
	    		</td>
	    	</tr>	    	
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   id="addNurseArrangementRecord_remark" name="remark" data-options="multiline:true,height:110,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>基本信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  id="addNurseArrangementRecord_elderlyName" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理员姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  id="addNurseArrangementRecord_nurseAssistantName" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>老人床位: </th>
	    		<td>
	    			 <input class="easyui-textbox"   id="addNurseArrangementRecord_bedLocation" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>护理级别: </th>
	    		<td>
	    			 <input class="easyui-textbox"   id="addNurseArrangementRecord_nursingLevel" style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>
</div>
<div id="view_nurseArrangementRecord"></div>
<div id="edit_nurseArrangementRecord"></div>

