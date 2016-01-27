<form id="editPersonalizedNurse_form" method="post">   
	<input value="${personalizedNurse.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.personalizedNurse.elderlyInfo")}:</th>
	    		<td>
	    			<input type="hidden" name="elderlyInfoId" id="editPersonalizedNurse_form_elderlyInfoID" value="${personalizedNurse.elderlyInfo.id}" > 
					<input class="easyui-textbox" prompt="${message("yly.common.please.select")}" value="${personalizedNurse.elderlyInfo.name}" id="editPersonalizedNurse_form_elderlyInfo" data-options="required:true,editable:false" />
	    			 <a href="#"  class="easyui-linkbutton" onclick="searchElderlyInfo('editPersonalizedNurse_form_elderlyInfo')" iconCls="icon-search" plain=true"></a>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.operator")}:</th>
	    		<td>
	    			 <input type="hidden" name="operatorId" id="editPersonalizedNurse_form_operatorID"> 
	    			 <input class="easyui-textbox" prompt="${message("yly.common.please.select")}" name="operator" value="${personalizedNurse.operator}" id="editPersonalizedNurse_form_operator"  data-options="required:true" />   
	    			 <a href="#"  class="easyui-linkbutton"  onclick="searchTenantUser('editPersonalizedNurse_form_operator')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>
	    	<tr>
				 <th>${message("yly.personalizedNurse.personalized")}:</th>
				 <td>
				  	<input type="hidden" id="editPersonalizedNurse_form_personalizedID" value="${personalizedNurse.personalized.id}"> 
				    <input class="easyui-textbox" id="editPersonalizedNurse_form_personalized"  type="text" name="personalizedId"  data-options="required:true"/> 
				 </td>
			</tr>
			<tr>
	    		<th>${message("yly.personalizedNurse.sumCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="sumCount" data-options="required:true" value="${personalizedNurse.sumCount}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.applyTime")}:</th>
	    		<td>
	    			 <input class="easyui-datebox"  name="applyTime" data-options="required:true" value="${personalizedNurse.applyTime}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.usedCount")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="usedCount" data-options="required:true" value="${personalizedNurse.usedCount}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.nurseContent")}:</th>
	    		<td>
	    			 <input class="easyui-textbox"  name="nurseContent" data-options="required:true" value="${personalizedNurse.nurseContent}"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.personalizedNurse.remark")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="remark" data-options="multiline:true,height:100,validType:'maxLength[200]'" value="${personalizedNurse.remark}"/> 
	    		</td>
	    	</tr>
	    </table>
</form>



