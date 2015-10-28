<form id="blacklistDetail_form" method="post">   
		  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" value="${blackList.elderlyInfo.name}" validtype="length[0,15]" style="width:80px;"/>   
	    		</td>
	    		<th>${message("yly.blacklist.geracomium")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="geracomium" value="${tenantInfo.tenantName}" validtype="length[0,15]" style="width:120px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    		  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if blackList.elderlyInfo.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if blackList.elderlyInfo.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    		<th>${message("yly.blacklist.photo")}:</th>
	    		<td rowspan="6">
	    			<p class="imgWrap img-thumbnail">
						<img src="${blackList.elderlyInfo.profilePhoto}">
					</p>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="contactPhone" value="${blackList.elderlyInfo.elderlyPhoneNumber}" validtype="length[0,30]" style="width:120px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" value="${blackList.elderlyInfo.age}" data-options="min:0,max:200" style="width:40px;"/>
	    		</td>
	    	</tr>
	    	<table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.casue")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="cause" value="${blackList.cause}" validtype="length[0,150]" data-options="multiline:true,height:90,width:260"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" value="${blackList.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />
	    		</td>
	    	</tr>
	    	</table>
	    </table>
</form>



