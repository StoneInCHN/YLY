<script type="text/javascript" src="${base}/resources/modules/bed.js"></script>
<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'west',split:true" style="width:200px;padding-left:20px" title="筛选">
             <ul id="roomTreeForBed"></ul>  
     </div>
	<div data-options="region:'center'" >
		<div class="easyui-panel" data-options="fit:true,border:false">
			<table id="bed_table_list"></table>
			<div id="bed_manager_tool">
				<div class="tool-button">
					<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="bed_manager_tool.add();">${message("yly.button.add")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="bed_manager_tool.edit();">${message("yly.button.update")}</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="bed_manager_tool.remove();">${message("yly.button.delete")}</a>
				</div>
				<div class="tool-filter"></div>
			</div> 
			<div id="addBed">
				<form id="addBed_form" method="post" class="form-table">   
				    <table class="table table-striped">
				    	<tr>
				    		<th>${message("yly.bed.bedNumber")}:</th>
				    		<td>
				    			 <input class="easyui-textbox" type="text" name="bedNumber" validtype="length[0,20]" data-options="required:true" />   
				    		</td>
				    	</tr>
				    	<tr>
				    		<th>${message("yly.bed.room")}:</th>
				    		<td>
				    			 <input class="easyui-combotree" type="text" id="addBed_form_roomId" name="roomId" data-options="required:true,editable:false" />   
				    		</td>
				    	</tr>
				    	<tr>
				    		<th>${message("yly.bed.status")}:</th>
				    		<td>
				    			 <input type="radio" name="status" value="ENABLE"><span>${message("yly.common.enable")}</span>
			       				 <input type="radio" name="status" value="DISABLE"><span>${message("yly.common.disable")}</span>
				    		</td>
				    	</tr>
				    	<tr>
				    		<th>${message("yly.bed.description")}:</th>
				    		<td>
				    			  <input class="easyui-textbox" type="text" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100" /> 
				    		</td>
				    	</tr>
				    </table>
				</form>
			</div> 
			<div id="editBed"></div> 
		
		</div>
    </div>
</div>
