<script src="${base}/resources/modules/importData.js"></script>
<div>
	  <fieldset>
	    <legend>导入老人信息</legend>
	    <form id="importData_form" method="post" enctype="multipart/form-data"> 
	    <table>
		    <tr>
		    	<td><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="downloadTemplate('elderlyInfo')">下载excel模板</a></td>
		    	<td>  选择excel文件:</td>
		    	<td><input type="file" id="excelFile" name="excelFile"></td>
		    	<td><a href="#" class="easyui-linkbutton" iconCls="icon-ok" plain=true onclick="importData()">开始导入</a></td>
		    </tr>

	    </table>
	    </from>
	</fieldset>
</div>
<table id="elderlyInfo-table-list"></table>





