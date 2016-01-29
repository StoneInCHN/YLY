<script type="text/javascript"  src="${base}/resources/modules/personalizedRecord.js"></script>
<table id="personalizedRecord-table-list" data-nurseId="${personalizedNurse.id}"></table>
<div id="addPersonalizedRecord" style="display:none">
	<form id="addPersonalizedRecord_form" method="post" >   
		<input type="hidden" name="personalizedNurseId" value="${personalizedNurse.id}"> 
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedRecord.elderlyInfo")}:</th>
	    		<td>
	    			<input type="hidden" name="elderlyInfoId" value="${personalizedNurse.elderlyInfo.id}" id="addPersonalizedRecord_form_elderlyInfoID"  > 
					<input class="easyui-textbox" prompt="${message("yly.common.please.select")}" value="${personalizedNurse.elderlyInfo.name}"  id="addPersonalizedRecord_form_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#"  class="easyui-linkbutton" onclick="searchElderlyInfo('addPersonalizedRecord_form_elderlyInfo')" iconCls="icon-search" plain=true"></a>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedRecord.operator")}:</th>
	    		<td>
					<input type="hidden" name="operatorId"  id="addPersonalizedRecord_form_operatorID"> 
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addPersonalizedRecord_form_operator"  data-options="required:true" />   
	    			 <a href="#"  class="easyui-linkbutton"  onclick="searchTenantUser('addPersonalizedRecord_form_operator')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedRecord.serviceTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="serviceTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedRecord.nurseContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="nurseContent" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedRecord.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="remark" data-options="multiline:true,height:100,validType:'maxLength[200]'" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
