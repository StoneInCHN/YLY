<script type="text/javascript"  src="${base}/resources/modules/personalizedNurse.js"></script>
<table id="personalizedNurse-table-list"></table>
<div id="addPersonalizedNurse">
	<form id="addPersonalizedNurse_form" method="post" class="form-table">   
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedNurse.elderlyInfo")}:</th>
	    		<td>
	    			<input type="hidden" name="elderlyInfo" id="addPersonalizedNurse_form_elderlyInfoID"> 
					<input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="elderlyInfo" id="addPersonalizedNurse_form_elderlyInfo" panelHeight="150px" data-options="required:true,editable:false" />
	    			 <a href="#"  class="easyui-linkbutton" onclick="searchElderlyInfo('addPersonalizedNurse_form_elderlyInfo')" iconCls="icon-search" plain=true"></a>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.operator")}:</th>
	    		<td>
					<input type="hidden" name="operator" id="addPersonalizedNurse_form_elderlyInfoID"> 
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" id="addPersonalizedNurse_form_operator"  data-options="required:true" />   
	    			 <a href="#"  class="easyui-linkbutton"  onclick="searchTenantUser('addPersonalizedNurse_form_operator')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
			<tr>
				 <th>${message("yly.personalizedNurse.nurseLevel")}:</th>
				 <td>
				    <input class="easyui-textbox" id="addPersonalizedNurse_form_nurseLevel" type="text" name="nurseLevelId"  data-options="required:true"/> 
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
	    		<th>${message("yly.personalizedNurse.sumCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="sumCount" data-options="required:true" />   
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
<div id="editNursePlan"></div>  




