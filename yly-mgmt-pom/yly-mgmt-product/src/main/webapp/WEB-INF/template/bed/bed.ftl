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
<div id="addBed">
	<form id="addBed_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>床位编号:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="bedNumber" validtype="length[0,20]" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所属房间:</th>
	    		<td>
	    			 <input class="easyui-combobox" type="text" id="addBed_form_roomId" name="roomId" data-options="required:true,editable:false" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>床位状态:</th>
	    		<td>
	    			 <input type="radio" name="status" value="ENABLE"><span>启用</span>
       				 <input type="radio" name="status" value="DISABLE"><span>禁用</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>描述 :</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div> 
<div id="editBed"></div> 