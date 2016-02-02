	<form id="editNurseChange_form" method="post"> 
		<input type="hidden" name="id" id="editNurseChange_ID" value="${nurseLevelChangeRecord.id}">  
	    <input type="hidden" name="elderlyInfo.id" id="editNurseChange_elderlyInfoID" value="${nurseLevelChangeRecord.elderlyInfo.id}">  
	   <fieldset> 
	   <legend>护理变更前信息:</legend>	    
	    <table class="table table-striped">
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="editNurseChange_elderlyName" value="${nurseLevelChangeRecord.elderlyInfo.name}"
	    			 			style="width:150px;" data-options="required:true,editable:false" />
	    			 <a href="#" class="easyui-linkbutton" onclick="searchElderlyInfo('edit_NurseChange')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>变更前等级: </th>
	    		<td>
					<input class="easyui-combobox" id="editNurseChange_oldNurseLevel"  value="${nurseLevelChangeRecord.oldNurseLevel.id}"
					name="oldNurseLevel.id" style="width:120px;" data-options="editable:false" disabled=true>
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	   <fieldset> 
	   <legend>护理变更后信息:</legend>	    
	    <table class="table table-striped">	    	
	    	<tr>
	    		<th>老人姓名: </th>
	    		<td>
	    			 <input class="easyui-textbox"  id="editNurseChange_elderlyName_Temp" value="${nurseLevelChangeRecord.elderlyInfo.name}"
	    			 			style="width:150px;" data-options="required:true,editable:false" />
	    		</td>
	    		<th>变更后等级: </th>
	    		<td>
					<input class="easyui-combobox"  id="editNurseChange_newNurseLevel" value="${nurseLevelChangeRecord.newNurseLevel.id}"
					  name="newNurseLevel.id" style="width:120px;" data-options="required:true,editable:false" >
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注: </th>
	    		<td colspan='3'>
	    			 <input class="easyui-textbox"   name="remark" value="${nurseLevelChangeRecord.remark}" data-options="multiline:true,height:100,width:460,required:true" />
	    		</td>
	    	</tr>
	    	</table>
	    	</fieldset>
	</form>