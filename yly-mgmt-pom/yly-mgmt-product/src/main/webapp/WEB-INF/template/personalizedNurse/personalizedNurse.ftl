<script type="text/javascript"  src="${base}/resources/modules/personalizedNurse.js"></script>
<table id="personalizedNurse-table-list" style="height:350px" ></table>
<div id="addPersonalizedNurse">
	<form id="addPersonalizedNurse_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedNurse.elderlyInfo")}:</th>
	    		<td>
	    			<input type="hidden" name="elderlyInfoId" id="addPersonalizedNurse_form_elderlyInfoID"  > 
					<input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addPersonalizedNurse_form_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#"  class="easyui-linkbutton" onclick="searchElderlyInfo('addPersonalizedNurse_form_elderlyInfo')" iconCls="icon-search" plain=true"></a>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.operator")}:</th>
	    		<td>
					<input type="hidden" name="operatorId" id="addPersonalizedNurse_form_operatorID"> 
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}"  id="addPersonalizedNurse_form_operator"  data-options="required:true" />   
	    			 <a href="#"  class="easyui-linkbutton"  onclick="searchTenantUser('addPersonalizedNurse_form_operator')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
			<tr>
				 <th>${message("yly.personalizedNurse.nurseLevel")}:</th>
				 <td>
				    <input class="easyui-textbox" id="addPersonalizedNurse_form_personalized"  type="text" name="personalizedId"  data-options="required:true"/> 
				 </td>
			</tr>
			<tr>
	    		<th>${message("yly.personalizedNurse.sumCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="sumCount" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.applyTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="applyTime" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.usedCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="usedCount" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.nurseContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="nurseContent" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="remark" data-options="multiline:true,height:100,validType:'maxLength[200]'" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editPersonalizedNurse"></div>  
<div id="incluedPersonalizedRecord" class="easyui-panel" style="height:300px"> </div>  
</div>  


