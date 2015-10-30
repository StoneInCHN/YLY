<form id="blacklistDetail_form" method="post">   
		  <table class="table table-striped">
		  	<table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" value="${blackList.elderlyInfo.name}" disabled="true" style="width:80px;"/>   
	    		</td>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    		  <select id="gender" class="easyui-combobox" name="gender" disabled="true" style="width:50px;">   
    			  	<option value="MALE" [#if blackList.elderlyInfo.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if blackList.elderlyInfo.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.blacklist.geracomium")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="geracomium" value="${tenantInfo.tenantName}" disabled="true" style="width:120px;"/>   
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" value="${blackList.elderlyInfo.age}" disabled="true" data-options="min:0,max:200" style="width:40px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="contactPhone" value="${blackList.elderlyInfo.elderlyPhoneNumber}" disabled="true" style="width:120px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.blacklist.photo")}:</th>
	    		<td rowspan="6">
	    			<p class="imgWrap img-thumbnail">
						<img src="${blackList.elderlyInfo.profilePhoto}">
					</p>
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.casue")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="cause" value="${blackList.cause}" disabled="true" data-options="multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" value="${blackList.remark}" disabled="true" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    	</table>
	    </table>
</form>



