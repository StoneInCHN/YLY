<div>
	  <fieldset>
	    <legend>相册查询</legend>
	    <form id="photoAlbum_search_form" class="search-form">
	        <div class="search-item">&nbsp;&nbsp;</div>
			<div class="search-item">
			   <label>相册名称:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="keysOfPhotoAlbumName" name="keysOfPhotoAlbumName" validtype="length[0,15]" style="width:110px;"/>
			</div>			        
	    	<div class="search-item">
			   <label>${message("yly.common.elderly.name")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="keysOfElderlyName" name="keysOfElderlyName" validtype="length[0,15]" style="width:110px;"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="photoAlbum_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="albumSearch-table-list"></table>