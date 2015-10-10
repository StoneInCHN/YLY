<script type="text/javascript" src="${base}/resources/modules/room.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.room.search")}</legend>
	    <form id="room_search_form" class="search-form">
			<div class="search-item">
			    <label>${message("yly.room.building")}:</label>
			     <input class="easyui-combobox" id="room_search_form_buildingId"  name="buildingId"  data-options="editable:false" >  
			</div>
			<div class="search-item">
			    <label>${message("yly.room.floor")}:</label>
			    <input class="easyui-numberspinner" type="text" name="floor" data-options="min:-10,max:20" /> 
			</div>
		</form>
		<div class="search-item">
	  	  <button id="room_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="room_table_list"></table>
<div id="room_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="room_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="room_manager_tool.edit();">${message("yly.button.update")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" plain=true onclick="room_manager_tool.remove();">${message("yly.button.delete")}</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" plain=true >导出</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addRoom">
	<form id="addRoom_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.room.roomName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" validtype="length[0,20]" type="text" name="roomName"  />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="roomNumber" validtype="length[0,20]"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.building")}:</th>
	    		<td>
	    			   <input class="easyui-combobox" id="addRoom_form_buildingId"  name="buildingId"  data-options="editable:false" >     
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.floor")}:</th>
	    		<td>
	    			  <input class="easyui-numberspinner" type="text" name="floor" data-options="min:-10,max:20,required:true" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomType")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" id="addRoom_form_roomType" type="text" name="roomTypeId"  /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.room.roomStatus")}:</th>
	    		<td>
	    			 <input type="radio" name="roomStatus" value="ENABLE"><span>${message("yly.common.enable")}</span>
       				 <input type="radio" name="roomStatus" value="DISABLE"><span>${message("yly.common.disable")}</span>
	    		</td>
	    	</tr>
			<tr>
	    		<th>${message("yly.room.description")}:</th>
	    		<td>
	    			  <textarea class="easyui-textbox"   name="description" validtype="length[0,100]" data-options="multiline:true,height:115"></textarea> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>  	
<div id="editRoom"></div>  




