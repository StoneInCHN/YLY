<script src="${base}/resources/modules/admission.js"></script>
<div>
	  <fieldset>
	    <legend>${message("yly.elderlyInfo.search")}</legend>
	    <form id="elderlyInfo_search_form" class="search-form">
			<div class="search-item">
			    <label> ${message("yly.elderlyInfo.checkinDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("yly.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="elderlyInfo_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<table id="admission-table-list"></table>
<div id="admission_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="admission_manager_tool.add();">${message("yly.button.add")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="admission_manager_tool.edit();"">${message("yly.button.update")}</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="admission_manager_tool.remove();">${message("yly.button.delete")}</a>
	</div>
	<div class="tool-filter"></div>
</div> 
<div id="addAdmission">
	<form id="addAdmission_form" method="post" class="form-table">   
	   	  <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.elderlyInfo.identifier")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="identifier" validtype="length[0,15] data-options="required:true,editable:false" style="width:85px;"/>  
	    			 <a href="#" class="easyui-linkbutton" plain="true">${message("yly.common.generate.identifier")}</a>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.beHospitalizedDate")}:</th>
	    		<td>
	    			<input type="text" class="Wdate" id="beginDate" name="beHospitalizedDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="name" validtype="length[0,15] data-options="required:true" style="width:85px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.photo")}:</th>
	    		<td>
	    			  <input class="easyui-filebox" name="photo" data-options="prompt:'${message("yly.elderlyInfo.choose.photo")}...'" style="width:200px;">
	    		</td>
	    	</tr>
	    	<tr>
	    	
	    		<th>${message("yly.gender")}:</th>
	    		<td>
    			  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE">${message("yly.gender.male")}</option>
					<option value="FEMALE">${message("yly.gender.female")}</option>  
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard"  validtype="length[0,25] style="width:120px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.socialInsuranceNumber")}:</th>
	    		<td>
    			  	<input class="easyui-textbox" type="text" name="socialInsuranceNumber" validtype="length[0,25] style="width:120px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.birthday")}:</th>
	    		<td>
	    			  <input class="easyui-datetimebox" name="birthday" data-options="required:true,showSeconds:false,editable:false" style="width:140px">   
	    		</td>
	    	</tr>
	    	<tr>
				<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="age" style="width:60px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.elderlyPhoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyPhoneNumber" validtype="mobile" data-options="required:true"  style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.placeOfOrigin")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="placeOfOrigin" validtype="length[0,50] style="width:120px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.personnelCategory")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="personnelCategory"  name="personnelCategory" style="width:90px;" data-options="editable:false" > 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.nation")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="nation" validtype="length[0,10] style="width:120px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.religion")}:</th>
	    		<td>
	    			<select id="religion" class="easyui-combobox" name="religion" style="width:110px;">   
						<option value="BUDDHISM">${message("yly.common.religion.buddhism")}</option>
						<option value="TAOISM">${message("yly.common.religion.taoism")}</option> 
						<option value="CHRISTIANITY">${message("yly.common.religion.christianity")}</option>
						<option value="ISLAM">${message("yly.common.religion.islam")}</option>
						<option value="CATHOLICISM">${message("yly.common.religion.catholicism")}</option>
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>     
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.politicalOutlook")}:</th>
	    		<td>
	    			<select id="politicalOutlook" class="easyui-combobox" name="politicalOutlook" style="width:110px;">   
						<option value="ZHONGDANGDANGYUAN">${message("yly.common.politicalOutlook.dangyuan")}</option>
						<option value="GONGQINGTUANYUAN">${message("yly.common.politicalOutlook.tuanyuan")}</option> 
						<option value="MINZUDANGPAICHENGYUAN">${message("yly.common.politicalOutlook.minzhudangpai")}</option>
						<option value="QUNZHONG">${message("yly.common.politicalOutlook.qunzhong")}</option>
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.marriageState")}:</th>
	    		<td>
	    			<select id="marriageState" class="easyui-combobox" name="marriageState" style="width:110px;">   
						<option value="MARRIED">${message("yly.common.marriageState.married")}</option>
						<option value="UNMARRIED">${message("yly.common.marriageState.unmarried")}</option> 
						<option value="WIDOWED">${message("yly.common.marriageState.widowed")}</option>
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.originalCompany")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="originalCompany" validtype="length[0,120]" style="width:100px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.position")}:</th>
	    		<td>
	    			   <input class="easyui-textbox" type="text" name="position" validtype="length[0,20]"  style="width:50px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.educationLevel")}:</th>
	    		<td>
	    			<select id="educationLevel" class="easyui-combobox" name="educationLevel" style="width:110px;">   
						<option value="BACHELOR">${message("yly.common.educationLevel.bachelor")}</option>
						<option value="MASTER">${message("yly.common.educationLevel.master")}</option> 
						<option value="DOCTOR">${message("yly.common.educationLevel.doctor")}</option>
						<option value="HIGHSCHOOL">${message("yly.common.educationLevel.highschool")}</option>
						<option value="ZHONGZHUAN">${message("yly.common.educationLevel.zhongzhuan")}</option>
						<option value="JUNIORHIGHSCHOOL">${message("yly.common.educationLevel.juniorhighschool")}</option>
						<option value="PRIMARYSCHOOL">${message("yly.common.educationLevel.primaryschool")}</option>
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>     
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.personalHabits")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="personalHabits" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		
	    		<th>${message("yly.elderlyInfo.honors")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="honors" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.hobbies")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="hobbies" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.registeredResidence")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="registeredResidence" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.residentialAddress")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="residentialAddress" validtype="length[0,150]" data-options="multiline:true,height:90,width:320" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.livingState")}:</th>
	    		<td>
	    			<select id="livingState" class="easyui-combobox" name="livingState" style="width:110px;">   
						<option value="LIVINGALONE">${message("yly.elderlyInfo.LivingState.livingalone")}</option>
						<option value="LIVINGWITHFAMILY">${message("yly.elderlyInfo.LivingState.livingwithfamily")}</option> 
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.housingInfo")}:</th>
	    		<td>
	    			<select id="housingInfo" class="easyui-combobox" name="housingInfo" style="width:110px;">   
						<option value="PROPERTYRIGHTHOUSE">${message("yly.elderlyInfo.housingInfo.propertyrighthouse")}</option>
						<option value="RENTALHOUSE">${message("yly.elderlyInfo.housingInfo.rentalhouse")}</option> 
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    	</tr>
	     	<tr>
	    		<th>${message("yly.elderlyInfo.elderlyConsigner")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyConsigner" style="width:50px;"/
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.elderlyFamilyMembers")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="elderlyFamilyMembers" style="width:50px;"/
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.monthlyIncome")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="monthlyIncome" style="width:50px;"/
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.sourceOfIncome")}:</th>
	    		<td>
	    			<select id="sourceOfIncome" class="easyui-combobox" name="sourceOfIncome" style="width:110px;">   
						<option value="PENSION">${message("yly.elderlyInfo.sourceOfIncome.pension")}</option>
						<option value="CHILDSUPPORT">${message("yly.elderlyInfo.sourceOfIncome.childsupport")}</option> 
						<option value="SOCIALSUPPORT">${message("yly.elderlyInfo.sourceOfIncome.socialsupport")}</option>
						<option value="OTHER">${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.paymentWay")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="paymentWay"  name="paymentWay" style="width:90px;" data-options="editable:false" >  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.medicalExpPaymentWay")}:</th>
	    		<td>
	    			<select id="medicalExpPaymentWay" class="easyui-combobox" name="medicalExpPaymentWay" style="width:110px;">   
						<option value="SOCIALINSURANCE">${message("yly.elderlyInfo.medicalExpPaymentWay.socialinsurance")}</option>
						<option value="XINNONGHE">${message("yly.elderlyInfo.medicalExpPaymentWay.xinnonghe")}</option> 
						<option value="COMMERCIALINSURANCE">${message("yly.elderlyInfo.medicalExpPaymentWay.commercialinsurance")}</option>
						<option value="SELFFINANCED">${message("yly.elderlyInfo.medicalExpPaymentWay.selffinanced")}</option>
						<option value="PUBLICFINANCED">${message("yly.elderlyInfo.medicalExpPaymentWay.publicfinanced")}</option>
				  	</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.evaluatingResult")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="evaluatingResult"  name="evaluatingResult" style="width:90px;" data-options="editable:false" > 
	    		</td>
	    		<th>${message("yly.elderlyInfo.evaluatingScore")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="evaluatingScore"  style="width:40px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.nursingLevel")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="nursingLevel"  name="nursingLevel" style="width:90px;" data-options="editable:false" >   
	    		</td>
	    	</tr>
	    </table>
	</form>
	</div>
<div id="editAdmission"></div> 
<div id="admissionDetail"></div>
