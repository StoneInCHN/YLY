<script type="text/javascript"  src="${base}/resources/modules/position.js"></script>
<table id="position-table-list"></table>
<div id="addPosition">
	<form id="addPosition_form" method="post" class="form-table">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.position.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.position.department")}:</th>
	    		<td>
	    			  <input class="easyui-combotree" id="addPosition_form_departmentId" name="departmentId" type="text" data-options="required:true"/>
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editPosition"></div>  