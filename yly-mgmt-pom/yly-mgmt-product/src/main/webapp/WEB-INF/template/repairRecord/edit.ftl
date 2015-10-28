<form id="editBlacklist_form" method="post">   
		<input value="${blackList.id}" type="hidden" name="id" />
		<input value="${blackList.tenantID}" type="hidden" name="tenantID" />
		
		 <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.blacklist.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="name" value="${blackList.name}" validtype="length[0,15]" style="width:85px;"/>   
	    		</td>
	    		<th>${message("yly.blacklist.geracomium")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="geracomium" value="${blackList.geracomium}" validtype="length[0,15]" style="width:85px;"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.gender")}:</th>
	    		<td>
	    		  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if blackList.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if blackList.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    		<th>${message("yly.blacklist.photo")}:</th>
	    		<td>
	    			 <input class="easyui-filebox" name="file" value="${blackList.photo}" style="width:200px;">
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.placeOfOrigin")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="placeOfOrigin" value="${blackList.placeOfOrigin}" validtype="length[0,50]" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.elderlyInfo.nation")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="nation" value="${blackList.nation}" validtype="length[0,10]" style="width:80px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="idCard" value="${blackList.idCard}"  validtype="length[0,25]" style="width:150px;"/> 
	    		</td>
	    		<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" value="${blackList.age}" data-options="min:0,max:200" style="width:40px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.birthday")}:</th>
	    		<td>
	    			  <input type="text" class="Wdate" id="birthday"  name="birthday" value="${blackList.birthday}" readonly="readonly"/>  
	    		</td>
	    		<th>${message("yly.phoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="contactPhone" value="${blackList.contactPhone}" validtype="length[0,30]" style="width:85px;"/>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.registeredResidence")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="registeredResidence" value="${blackList.registeredResidence}" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.residentialAddress")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="residentialAddress" value="${blackList.residentialAddress}" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.blacklist.casue")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="cause" value="${blackList.cause}" validtype="length[0,150]" style="width:100px;"/> 
	    		</td>
	    		<th>${message("yly.remark")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-textbox" name="remark" value="${blackList.remark}" validtype="length[0,150]" data-options="multiline:true,height:90,width:120" />
	    		</td>
	    	</tr>
	    </table>
	    
	    
</form>



