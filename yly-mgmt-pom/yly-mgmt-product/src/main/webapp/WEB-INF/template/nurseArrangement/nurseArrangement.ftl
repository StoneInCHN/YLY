<script type="text/javascript" src="${base}/resources/modules/nurseArrangement.js"></script>

<div class="easyui-layout" data-options="fit:true">
      <div data-options="region:'west',split:true" style="width:300px" title="过滤">
             <div id="calendar-panel" class="easyui-panel" title="老人信息"    style="width:293px;height:400px;background:#fafafa;"  data-options="collapsible:true">
		        <fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="elderlyInfo_search_form" class="search-form">
				    	<div class="search-item">
						   <label>姓名:</label>
						   <input type="text" class="easyui-textbox"   id="elderlyName" name="elderlyInfo.name" validtype="length[0,10]" style="width:65px;"/>
						</div>
						<div class="search-item">
						   <label>编号:</label>
						   <input type="text" class="easyui-textbox"  id="elderlyIdentifier" name="elderlyInfo.identifier" validtype="length[0,10]" style="width:65px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="elderlyInfo_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("yly.search")}</button>
				    </div>
				</fieldset>
                <table id="elderlyInfoSearch-table-list"></table>
         </div>
         <div id="notify" class="easyui-panel" title="护理员信息"  style="width:293px;height:400px;background:#fafafa;"  data-options="collapsible:true">
			        <fieldset style="padding:5px 0px 4px 4px;margin:0px">
				        <form id="nurseAssistant_search_form" class="search-form">
					    	<div class="search-item">
							   <label>姓名:</label>
							   <input type="text" class="easyui-textbox"    id="nurseAssistantName" name="nurseAssistant.realName" validtype="length[0,10]" style="width:65px;"/>
							</div>
							<div class="search-item">
							   <label>编号:</label>
							   <input type="text" class="easyui-textbox"    id="staffID" name="nurseAssistant.staffID" validtype="length[0,10]" style="width:65px;"/>
							</div>
						</form>
						<div class="search-item">
					  	  <button id="elderlyInfo_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
					    </div>	   
				 </fieldset>     
               <table id="nurseAssistantSearch-table-list"></table>
         </div>  
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" data-options="border:false">
			 <fieldset>
			    <legend>护理员安排查询</legend>
			    <form id="nurseArrangement_search_form" class="search-form">
			    	<div class="search-item">
					   <label>护理名称:</label>
					   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="nurseName" name="nurseName" validtype="length[0,50]" style="width:110px;"/>
					</div>
					<div class="search-item">
					    <label> 护理日期从:</label>
					    <input type="text" class="Wdate" id="nurseStartDate" name="nurseStartDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'nurseEndDate\')}'});" />
					</div>
					<div class="search-item">
					    <label>${message("yly.to")}:</label>
					   	<input type="text" class="Wdate" id="nurseEndDate"  name="nurseEndDate"  onclick="WdatePicker({minDate: '#F{$dp.$D(\'nurseStartDate\')}'});"/>
					</div>
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
			<fieldset>
			    <legend>护理员护理明细查询</legend>
			    <form id="nurseArrangementRecord_search_form" class="search-form">
			    	<div class="search-item">
					   <label>护理名称:</label>
					   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="nurseName" name="nurseName" validtype="length[0,50]" style="width:110px;"/>
					</div>
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
<div id="add_nurseArrangement"></div>
<div id="view_nurseArrangement"></div>
<div id="edit_nurseArrangement"></div>
<div id="view_elderlyInfo"></div>
<div id="view_nurseAssistant"></div>
<div id="add_nurseArrangementRecord"></div>
<div id="view_nurseArrangementRecord"></div>
<div id="edit_nurseArrangementRecord"></div>

