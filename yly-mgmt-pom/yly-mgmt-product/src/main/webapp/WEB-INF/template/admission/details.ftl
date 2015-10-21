<form id="admissionDetail_form" method="post">   
		  <table class="table table-striped table-bordered">
	   	  	<caption><h5>${message("yly.elderlyInfo.baseinfo")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.identifier")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="identifier_input" name="identifier" value="${elderlyInfo.identifier}" validtype="length[0,15]" data-options="required:true,editable:false" style="width:60px;"/>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.beHospitalizedDate")}:</th>
	    		<td width="230px">
	    			<input id="beHospitalizedDate" name="beHospitalizedDate" type="text" value="${elderlyInfo.beHospitalizedDate}" class="easyui-datebox" disabled=true/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.personnelCategory")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="personnelCategoryId" type="text"  value="${elderlyInfo.personnelCategory.configValue}" readonly=true style="width:90px;"/>
	    		</td>
	    		
	    		<td  rowspan="6">
	    				<p class="imgWrap img-thumbnail">
						     <img src="${elderlyInfo.profilePhoto}">
						</p>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="name" value="${elderlyInfo.name}" readonly=true  validtype="length[0,15]" data-options="required:true" style="width:75px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.gender")}:</th>
	    		<td>
				  <select id="gender" class="easyui-combobox" name="gender" disabled="disabled" style="width:50px;">   
    			  	<option value="MALE" [#if consultation.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if consultation.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
				  </select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.elderlyPhoneNumber")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="elderlyPhoneNumber" value="${elderlyInfo.elderlyPhoneNumber}" readonly=true validtype="mobile" data-options="required:true"  style="width:110px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.idcard")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="IDCard"  value="${elderlyInfo.IDCard}" readonly=true validtype="length[0,25]" style="width:150px;"/> 
	    		</td>
	    	
				<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" value="${elderlyInfo.age}" readonly=true data-options="min:0,max:200" style="width:40px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.birthday")}:</th>
	    		<td>
	    			  <input id="birthday" name="birthday" type="text" value="${elderlyInfo.birthday}" class="easyui-datebox" disabled=true/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.socialInsuranceNumber")}:</th>
	    		<td>
    			  	<input class="easyui-textbox" type="text" name="socialInsuranceNumber" value="${elderlyInfo.socialInsuranceNumber}" readonly=true validtype="length[0,25]" style="width:120px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.placeOfOrigin")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="placeOfOrigin" value="${elderlyInfo.placeOfOrigin}" readonly=true validtype="length[0,50]" style="width:150px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.nation")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="nation" value="${elderlyInfo.nation}" readonly=true validtype="length[0,10]" style="width:80px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		
	    		<th>${message("yly.elderlyInfo.marriageState")}:</th>
	    		<td>
		    		<select id="marriageState" class="easyui-combobox" name="marriageState" disabled="disabled" style="width:70px;">   
	    			  	<option value="MARRIED" [#if elderlyInfo.marriageState =="MARRIED"] selected="selected" [/#if]>${message("yly.common.marriageState.married")}</option>
	    			  	<option value="UNMARRIED" [#if elderlyInfo.marriageState =="UNMARRIED"] selected="selected" [/#if]>${message("yly.common.marriageState.unmarried")}</option>
	    			  	<option value="WIDOWED" [#if elderlyInfo.marriageState =="WIDOWED"] selected="selected" [/#if]>${message("yly.common.marriageState.widowed")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.marriageState =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    	  	<th>${message("yly.elderlyInfo.educationLevel")}:</th>
	    		<td>
	    			 <select id="educationLevel" class="easyui-combobox" name="educationLevel" disabled="disabled" style="width:60px;">   
	    			  	<option value="BACHELOR" [#if elderlyInfo.educationLevel =="BACHELOR"] selected="selected" [/#if]>${message("yly.common.educationLevel.bachelor")}</option>
	    			  	<option value="MASTER" [#if elderlyInfo.educationLevel =="MASTER"] selected="selected" [/#if]>${message("yly.common.educationLevel.master")}</option>
	    			  	<option value="DOCTOR" [#if elderlyInfo.educationLevel =="DOCTOR"] selected="selected" [/#if]>${message("yly.common.educationLevel.doctor")}</option>
	    			  	<option value="HIGHSCHOOL" [#if elderlyInfo.educationLevel =="HIGHSCHOOL"] selected="selected" [/#if]>${message("yly.common.educationLevel.highschool")}</option>
	    			  	<option value="ZHONGZHUAN" [#if elderlyInfo.educationLevel =="ZHONGZHUAN"] selected="selected" [/#if]>${message("yly.common.educationLevel.zhongzhuan")}</option>
	    			  	<option value="JUNIORHIGHSCHOOL" [#if elderlyInfo.educationLevel =="JUNIORHIGHSCHOOL"] selected="selected" [/#if]>${message("yly.common.educationLevel.juniorhighschool")}</option>
	    			  	<option value="PRIMARYSCHOOL" [#if elderlyInfo.educationLevel =="PRIMARYSCHOOL"] selected="selected" [/#if]>${message("yly.common.educationLevel.primaryschool")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.educationLevel =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.politicalOutlook")}:</th>
	    		<td>
	    			<select id="politicalOutlook" class="easyui-combobox" name="politicalOutlook" disabled="disabled" style="width:110px;">   
	    			  	<option value="ZHONGDANGDANGYUAN" [#if elderlyInfo.politicalOutlook =="ZHONGDANGDANGYUAN"] selected="selected" [/#if]>${message("yly.common.politicalOutlook.dangyuan")}</option>
	    			  	<option value="GONGQINGTUANYUAN" [#if elderlyInfo.politicalOutlook =="GONGQINGTUANYUAN"] selected="selected" [/#if]>${message("yly.common.politicalOutlook.tuanyuan")}</option>
	    			  	<option value="MINZUDANGPAICHENGYUAN" [#if elderlyInfo.politicalOutlook =="MINZUDANGPAICHENGYUAN"] selected="selected" [/#if]>${message("yly.common.politicalOutlook.minzhudangpai")}</option>
	    			  	<option value="QUNZHONG" [#if elderlyInfo.politicalOutlook =="QUNZHONG"] selected="selected" [/#if]>${message("yly.common.politicalOutlook.qunzhong")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.politicalOutlook =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.religion")}:</th>
	    		<td>
	    			<select id="religion" class="easyui-combobox" name="religion" disabled="disabled" style="width:100px;">   
	    			  	<option value="BUDDHISM" [#if elderlyInfo.religion =="BUDDHISM"] selected="selected" [/#if]>${message("yly.common.religion.buddhism")}</option>
	    			  	<option value="TAOISM" [#if elderlyInfo.religion =="TAOISM"] selected="selected" [/#if]>${message("yly.common.religion.taoism")}</option>
	    			  	<option value="CHRISTIANITY" [#if elderlyInfo.religion =="CHRISTIANITY"] selected="selected" [/#if]>${message("yly.common.religion.christianity")}</option>
	    			  	<option value="ISLAM" [#if elderlyInfo.religion =="ISLAM"] selected="selected" [/#if]>${message("yly.common.religion.islam")}</option>
	    			  	<option value="CATHOLICISM" [#if elderlyInfo.religion =="CATHOLICISM"] selected="selected" [/#if]>${message("yly.common.religion.catholicism")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.religion =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.originalCompany")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="originalCompany" value="${elderlyInfo.originalCompany}" readonly=true validtype="length[0,120]" style="width:230px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.position")}:</th>
	    		<td>
	    			   <input class="easyui-textbox" type="text" name="position" value="${elderlyInfo.position}" readonly=true validtype="length[0,20]"  style="width:80px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.registeredResidence")}:</th>
	    		<td colspan=3>
	    			  <input class="easyui-textbox" type="text" name="registeredResidence" value="${elderlyInfo.registeredResidence}" readonly=true validtype="length[0,150]" style="width:400px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.residentialAddress")}:</th>
	    		<td colspan=3>
	    			  <input class="easyui-textbox" type="text" name="residentialAddress" value="${elderlyInfo.residentialAddress}" readonly=true validtype="length[0,150]" style="width:400px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.personalHabits")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="personalHabits" value="${elderlyInfo.personalHabits}" readonly=true validtype="length[0,150]" data-options="multiline:true,height:90,width:420" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.hobbies")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="hobbies" value="${elderlyInfo.hobbies}" readonly=true validtype="length[0,150]" data-options="multiline:true,height:90,width:420" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.honors")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="honors" value="${elderlyInfo.honors}" readonly=true validtype="length[0,150]" data-options="multiline:true,height:90,width:420" /> 
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<tr>
	    		<td colspan=4><h5>${message("yly.elderlyInfo.livingState")}</h5></td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.livingState")}:</th>
	    		<td>
	    			<select id="livingState" class="easyui-combobox" name="livingState" disabled="disabled" style="width:110px;">   
	    			  	<option value="LIVINGALONE" [#if elderlyInfo.livingState =="LIVINGALONE"] selected="selected" [/#if]>${message("yly.elderlyInfo.LivingState.livingalone")}</option>
	    			  	<option value="LIVINGWITHFAMILY" [#if elderlyInfo.livingState =="LIVINGWITHFAMILY"] selected="selected" [/#if]>${message("yly.elderlyInfo.LivingState.livingwithfamily")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.livingState =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.housingInfo")}:</th>
	    		<td>
	    			<select id="housingInfo" class="easyui-combobox" name="housingInfo" disabled="disabled" style="width:110px;">   
	    			  	<option value="PROPERTYRIGHTHOUSE" [#if elderlyInfo.housingInfo =="PROPERTYRIGHTHOUSE"] selected="selected" [/#if]>${message("yly.elderlyInfo.housingInfo.propertyrighthouse")}</option>
	    			  	<option value="RENTALHOUSE" [#if elderlyInfo.housingInfo =="RENTALHOUSE"] selected="selected" [/#if]>${message("yly.elderlyInfo.housingInfo.rentalhouse")}</option>
	    			  	<option value="OTHER" [#if elderlyInfo.housingInfo =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select> 
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.elderlyConsigner")}</h5></caption>
	     	<tr>
     			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerName")}:</th>
    			<td>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerName" value="${elderlyInfo.elderlyConsigner.consignerName}" readonly=true validtype="length[0,15]" data-options="required:true" style="width:75px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber")}:</th>
    			<td>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerPhoneNumber"  value="${elderlyInfo.elderlyConsigner.consignerPhoneNumber}" readonly=true validtype="mobile" data-options="required:true" style="width:110px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.isSameCity")}:</th>
    			<td>
    				<select id="elderlyConsigner.isSameCity" class="easyui-combobox" name="elderlyConsigner.isSameCity" disabled="disabled" style="width:80px;">   
	    			  	<option value="true" [#if elderlyInfo.elderlyConsigner.isSameCity ==true] selected="selected" [/#if]>${message("yly.common.yes")}</option>
	    			  	<option value="false" [#if elderlyInfo.elderlyConsigner.isSameCity ==true] selected="selected" [/#if]>${message("yly.common.no")}</option>
				  	</select> 
				  	
    			<th>${message("yly.elderlyInfo.elderlyConsigner.relation")}:</th>
    			<td>
		 			<select id="relation" class="easyui-combobox" name="relation" disabled="disabled" style="width:100px;">   
						<option value="CHILDREN" [#if elderlyInfo.elderlyConsigner.consignerRelation =="CHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.children")}</option> 
						<option value="MARRIAGE_RELATIONSHIP" [#if elderlyInfo.elderlyConsigner.consignerRelation =="MARRIAGE_RELATIONSHIP"] selected="selected" [/#if]>${message("yly.common.relation.marriage_relationship")}</option>
						<option value="GRANDPARENTS_AND_GRANDCHILDREN" [#if elderlyInfo.elderlyConsigner.consignerRelation =="GRANDPARENTS_AND_GRANDCHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.grandparents_and_grandchildren")}</option>
						<option value="BROTHERS_OR_SISTERS" [#if elderlyInfo.elderlyConsigner.consignerRelation =="BROTHERS_OR_SISTERS"] selected="selected" [/#if]>${message("yly.common.relation.brothers_or_sisters")}</option>
						<option value="DAUGHTERINLAW_OR_SONINLAW" [#if elderlyInfo.elderlyConsigner.consignerRelation =="DAUGHTERINLAW_OR_SONINLAW"] selected="selected" [/#if]>${message("yly.common.relation.daughterinlaw_or_soninlaw")}</option>
						<option value="FRIEND" [#if elderlyInfo.elderlyConsigner.consignerRelation =="FRIEND"] selected="selected" [/#if]>${message("yly.common.relation.friend")}</option>
						<option value="OTHER" [#if elderlyInfo.elderlyConsigner.consignerRelation =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  
    			</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.elderlyConsigner.companyAddress")}:</th>
    			<td colspan=3>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.companyAddress" value="${elderlyInfo.elderlyConsigner.companyAddress}" readonly=true validtype="length[0,150]" style="width:400px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerResidentialAddress")}:</th>
    			<td colspan=4>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerResidentialAddress" value="${elderlyInfo.elderlyConsigner.consignerResidentialAddress}" readonly=true validtype="length[0,150]" style="width:400px;"/> 
    			</td>
	    	</tr>
	    	</table>
	    	<table id="talbe-familyMember" class="table table-striped table-bordered">
		    	<caption><h5>${message("yly.elderlyInfo.familyMember")}</h5></caption>
		    	[#list elderlyInfo.elderlyFamilyMembers as familyMember]
		    	<tr>
					<th>${message("yly.elderlyInfo.familyMember.memberName")}:</th>
					<td>
						<input class="easyui-textbox family-member-textbox" type="text" name="memberName" value="${familyMember.memberName}" readonly=true validtype="length[0,15]" style="width:75px;"/>
					</td>
					<th>${message("yly.elderlyInfo.familyMember.PhoneNumber")}:</th>
					<td>
						<input class="easyui-textbox family-member-textbox" type="text" name="memberPhoneNumber" value="${familyMember.memberPhoneNumber}" readonly=true validtype="mobile" style="width:110px;"/>
					</td>
					<th>${message("yly.elderlyInfo.familyMember.relation")}:</th>
					<td>
						<select id="relation" class="easyui-combobox" name="memberRelation" disabled="disabled" style="width:100px;">   
							<option value="CHILDREN" [#if familyMember.memberRelation =="CHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.children")}</option> 
							<option value="MARRIAGE_RELATIONSHIP" [#if familyMember.memberRelation =="MARRIAGE_RELATIONSHIP"] selected="selected" [/#if]>${message("yly.common.relation.marriage_relationship")}</option>
							<option value="GRANDPARENTS_AND_GRANDCHILDREN" [#if familyMember.memberRelation =="GRANDPARENTS_AND_GRANDCHILDREN"] selected="selected" [/#if]>${message("yly.common.relation.grandparents_and_grandchildren")}</option>
							<option value="BROTHERS_OR_SISTERS" [#if familyMember.memberRelation =="BROTHERS_OR_SISTERS"] selected="selected" [/#if]>${message("yly.common.relation.brothers_or_sisters")}</option>
							<option value="DAUGHTERINLAW_OR_SONINLAW" [#if familyMember.memberRelation =="DAUGHTERINLAW_OR_SONINLAW"] selected="selected" [/#if]>${message("yly.common.relation.daughterinlaw_or_soninlaw")}</option>
							<option value="FRIEND" [#if familyMember.memberRelation =="FRIEND"] selected="selected" [/#if]>${message("yly.common.relation.friend")}</option>
							<option value="OTHER" [#if familyMember.memberRelation =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  		</select>  
					</td>
					<th>${message("yly.address")}:</th>
					<td>
						<input class="easyui-textbox family-member-textbox" type="text" name="memberResidentialAddress" value="${familyMember.memberResidentialAddress}" readonly=true validtype="length[0,150]" style="width:400px;"/>
					</td>
				</tr>
				[/#list]
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.economicSituation")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.monthlyIncome")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-numberbox" name="monthlyIncome" value="${elderlyInfo.monthlyIncome}" readonly=true data-options="min:0" style="width:60px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.sourceOfIncome")}:</th>
	    		<td>
	    		
	    			<select id="sourceOfIncome" class="easyui-combobox" name="sourceOfIncome" disabled="disabled" style="width:110px;">   
	    				<option value="PENSION" [#if elderlyInfo.sourceOfIncome =="PENSION"] selected="selected" [/#if]>${message("yly.elderlyInfo.sourceOfIncome.pension")}</option>
	    				<option value="CHILDSUPPORT" [#if elderlyInfo.sourceOfIncome =="CHILDSUPPORT"] selected="selected" [/#if]>${message("yly.elderlyInfo.sourceOfIncome.childsupport")}</option>
	    				<option value="SOCIALSUPPORT" [#if elderlyInfo.sourceOfIncome =="SOCIALSUPPORT"] selected="selected" [/#if]>${message("yly.elderlyInfo.sourceOfIncome.socialsupport")}</option>
	    				<option value="OTHER" [#if elderlyInfo.sourceOfIncome =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>  		
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.paymentWay")}:</th>
	    		<td>
	    			<select id="paymentWay" class="easyui-combobox" name="paymentWay" disabled="disabled" style="width:110px;">   
	    				<option value="CHILDREN_PAY" [#if elderlyInfo.paymentWay =="CHILDREN_PAY"] selected="selected" [/#if]>${message("yly.elderlyInfo.paymentway.childpay")}</option>
	    				<option value="OWN_PAYMENT" [#if elderlyInfo.paymentWay =="OWN_PAYMENT"] selected="selected" [/#if]>${message("yly.elderlyInfo.paymentway.ownpayment")}</option>
	    				<option value="GOV_PAY" [#if elderlyInfo.paymentWay =="GOV_PAY"] selected="selected" [/#if]>${message("yly.elderlyInfo.paymentway.govpay")}</option>
	    				<option value="COMPANY_PAY" [#if elderlyInfo.paymentWay =="COMPANY_PAY"] selected="selected" [/#if]>${message("yly.elderlyInfo.paymentway.companypay")}</option>
	    				<option value="OTHER" [#if elderlyInfo.paymentWay =="OTHER"] selected="selected" [/#if]>${message("yly.common.other")}</option>
				  	</select>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.medicalExpPaymentWay")}:</th>
	    		<td>
	    		
	    			<select id="medicalExpPaymentWay" class="easyui-combobox" name="medicalExpPaymentWay" disabled="disabled" style="width:110px;">   
	    				<option value="SOCIALINSURANCE" [#if elderlyInfo.medicalExpPaymentWay =="SOCIALINSURANCE"] selected="selected" [/#if]>${message("yly.elderlyInfo.medicalExpPaymentWay.socialinsurance")}</option>
	    				<option value="XINNONGHE" [#if elderlyInfo.medicalExpPaymentWay =="XINNONGHE"] selected="selected" [/#if]>${message("yly.elderlyInfo.medicalExpPaymentWay.xinnonghe")}</option>
	    				<option value="COMMERCIALINSURANCE" [#if elderlyInfo.medicalExpPaymentWay =="COMMERCIALINSURANCE"] selected="selected" [/#if]>${message("yly.elderlyInfo.medicalExpPaymentWay.commercialinsurance")}</option>
	    				<option value="SELFFINANCED" [#if elderlyInfo.medicalExpPaymentWay =="SELFFINANCED"] selected="selected" [/#if]>${message("yly.elderlyInfo.medicalExpPaymentWay.selffinanced")}</option>
	    				<option value="PUBLICFINANCED" [#if elderlyInfo.medicalExpPaymentWay =="PUBLICFINANCED"] selected="selected" [/#if]>${message("yly.elderlyInfo.medicalExpPaymentWay.publicfinanced")}</option>
				  	</select>
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.evaluatingResult")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.evaluatingResult")}:</th>
	    		<td>
	    			   <input class="easyui-textbox"  name="evaluatingResultId" type="text"  value="${elderlyInfo.evaluatingResult.configValue}" readonly=true style="width:120px;"/> 
	    		</td>
	    		<th>${message("yly.elderlyInfo.evaluatingScore")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="evaluatingScore" value=${elderlyInfo.evaluatingScore} readonly=true data-options="min:0,precision:2" style="width:40px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.nursingLevel")}:</th>
	    		<td>
	    			  <input class="easyui-textbox"  name="nursingLevelId" type="text"  value="${elderlyInfo.nursingLevel.configValue}" readonly=true style="width:120px;"/>
	    		</td>
	    	</tr>
	    </table>
</form>
