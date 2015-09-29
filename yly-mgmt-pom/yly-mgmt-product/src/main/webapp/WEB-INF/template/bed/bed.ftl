<script type="text/javascript" src="${base}/resources/modules/bed.js"></script>
<table id="bed_table_list"></table>
<div id="bed_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="bed_manager_tool.add();">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="bed_manager_tool.edit();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="bed_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addBed"></div> 
<div id="editBed"></div> 