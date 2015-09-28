<script type="text/javascript" src="${base}/resources/modules/room.js"></script>
<div>
	  <fieldset>
	    <legend>房间查询</legend>
	    <form id="room_search_form" class="search-form">
			<div class="search-item">
			    <label> 所属楼宇:</label>
			     <input class="easyui-combobox" id="room_search_form_buildingId"  name="buildingId"  data-options="editable:false" >  
			</div>
			<div class="search-item">
			    <label>所属楼层:</label>
			    <input class="easyui-numberspinner" type="text" name="floor" data-options="min:-10,max:20" /> 
			</div>
		</form>
		<div class="search-item">
	  	  <button id="room_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="room_table_list"></table>
<div id="room_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="room_manager_tool.add();">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="room_manager_tool.edit();">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="room_manager_tool.remove();">删除</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addRoom">
	<form id="addRoom_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>房间名称:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="roomName"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>房间编号:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomNumber" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所属楼宇:</th>
	    		<td>
	    			   <input class="easyui-combobox" id="addRoom_form_buildingId"  name="buildingId"  data-options="editable:false" >     
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>所在楼层:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" name="floor" data-options="min:-10,max:20,required:true" /> 
	    		</td>
	    	</tr>
	    	<!--
	    	<tr>
	    		<th>房间类型:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomType"  /> 
	    		</td>
	    	</tr>
	    	-->
	    	<tr>
	    		<th>房间状态:</th>
	    		<td>
	    			 <input type="radio" name="roomStatus" value="ENABLE"><span>启用</span>
       				 <input type="radio" name="roomStatus" value="DISABLE"><span>禁用</span>
	    		</td>
	    	</tr>
			<tr>
	    		<th>描述:</th>
	    		<td>
	    			  <textarea class="easyui-validatebox"   data-options="max:100"   name="description" style="height:60px;width:200px"></textarea> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>  	
<div id="editRoom"></div>  




