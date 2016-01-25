<script type="text/javascript" src="${base}/resources/modules/nurseArrangement.js"></script>
<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'west',split:true" style="width:300px">
             <div id="calendar-panel" class="easyui-panel" title="老人信息"     
	        style="width:300px;height:400px;padding:5px;background:#fafafa;"   
	        data-options="collapsible:true">
                <div id="cc" class="easyui-calendar" style="width:180px;height:200px;"></div>  
         </div>
         <div id="notify" class="easyui-panel" title="护理员信息"     
	        style="width:300px;height:400px;padding:5px;background:#fafafa;"   
	        data-options="collapsible:true">
               <ul id="notify-content" class="notify" style="overflow-y: hidden; height: 180px;">
			 </ul>
         </div>  
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" data-options="fit:true,border:false">
			<table id="nurseArrangement_table_list"></table>
			<div id="nurseArrangement_manager_tool">
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="nurseArrangement_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="nurseArrangement_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="nurseArrangement_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>
				<div class="tool-filter"></div>
			</div> 
		</div>        
    </div>
</div>
