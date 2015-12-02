<div>
	  <fieldset>
	    <legend>题库查询</legend>
	    <form id="item_search_form" class="search-form">
	        <div class="search-item">&nbsp;&nbsp;</div>
			<div class="search-item">
			   <label>题目名称:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="keysOfItemName" name="keysOfItemName" validtype="length[0,15]" style="width:110px;"/>
			</div>			        
		</form>
		<div class="search-item">
	  	  <button id="item_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchItemsByKeys()">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="searchAllItems-table-list"></table>