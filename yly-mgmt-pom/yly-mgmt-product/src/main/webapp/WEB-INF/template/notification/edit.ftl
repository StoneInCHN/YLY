<form id="editNotification_form" method="post">   
	 <input type="hidden" name="id" id="id" value="${notification.id}">   
    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("yly.notification.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value ="${notification.operator}" name="operator" id= "operator" data-options="required:true" />
	    		</td>
	    		<th>${message("yly.common.title")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value ="${notification.title}" name="title" id= "title" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr >
		    	<th >${message("yly.notification.publishTime")}:</th>
		    	<td colspan="3">
		    		<input type="text" class="Wdate" value ="${notification.publishTime}" id="publishTime" name="publishTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
		    	</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.notification.content")}:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" value ="${notification.content}" name="content" id= "content" data-options="required:true" style="height:200px;width:300px"/>   
	    		</td>
	    	</tr>
	    </table>
</form>



