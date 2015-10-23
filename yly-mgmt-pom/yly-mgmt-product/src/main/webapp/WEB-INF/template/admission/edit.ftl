<form id="editAdmission_form" method="post">  
		<input type="hidden" name="id" value="${elderlyInfo.id}"/> 
		<input type="hidden" name="elderlyConsigner.id" value="${elderlyInfo.elderlyConsigner.id}"/>
		 <table class="table table-striped table-bordered">
	   	  	<caption><h5>${message("yly.elderlyInfo.baseinfo")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.identifier")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="identifier_input" name="identifier" value="${elderlyInfo.identifier}" validtype="length[0,15]" data-options="required:true,editable:false" style="width:60px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.beHospitalizedDate")}:</th>
	    		<td width="230px">
	    			<input id="beHospitalizedDate" name="beHospitalizedDate" type="text" value="${elderlyInfo.beHospitalizedDate}" class="easyui-datebox"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.personnelCategory")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="personnelCategoryEditId"  name="personnelCategoryEditId" value="${elderlyInfo.personnelCategory.id}" style="width:90px;" data-options="editable:false" >
	    		</td>
	    		
	    		<td colspan="2" rowspan="6">
	    			<div title="头像上传" class="easyui-tooltip">
	    				<div id="admissionUploader-edit" class="single-uploader">
						  	<div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="admissionFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										     <img src="${elderlyInfo.profilePhoto}">
									 </p>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						        <div id="admissionFilePicker-edit2" class="margin-left-40">选择文件</div>
						        <div class="btn btn-info savePhoto margin-left-40" style="display:none">保存头像</div>
						    </div>
						</div>
	    			</div>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderly.name")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="name" value="${elderlyInfo.name}" validtype="length[0,15]" data-options="required:true" style="width:75px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.gender")}:</th>
	    		<td>
				  <select id="gender" class="easyui-combobox" name="gender" style="width:50px;">   
    			  	<option value="MALE" [#if elderlyInfo.gender =="MALE"] selected="selected" [/#if]>${message("yly.gender.male")}</option>
					<option value="FEMALE" [#if elderlyInfo.gender =="FEMALE"] selected="selected" [/#if]>${message("yly.gender.female")}</option>
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
	    			  <input class="easyui-textbox" type="text" name="IDCard"  value="${elderlyInfo.IDCard}" validtype="length[0,25]" style="width:150px;"/> 
	    		</td>
	    	
				<th>${message("yly.common.age")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="age" value="${elderlyInfo.age}" data-options="min:0,max:200" style="width:40px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.birthday")}:</th>
	    		<td>
	    			  <input id="birthday" name="birthday" type="text" value="${elderlyInfo.birthday}" class="easyui-datebox"/>  
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.socialInsuranceNumber")}:</th>
	    		<td>
    			  	<input class="easyui-textbox" type="text" name="socialInsuranceNumber" value="${elderlyInfo.socialInsuranceNumber}" validtype="length[0,25]" style="width:120px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.placeOfOrigin")}:</th>
	    		<td>
    			   <input class="easyui-textbox" type="text" name="placeOfOrigin" value="${elderlyInfo.placeOfOrigin}" validtype="length[0,50]" style="width:150px;"/> 
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.nation")}:</th>
	    		<td>
	    			<input class="easyui-textbox" type="text" name="nation" value="${elderlyInfo.nation}" validtype="length[0,10]" style="width:80px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    	
	    	<th>${message("yly.elderlyInfo.marriageState")}:</th>
	    		<td>
		    		 <input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'MARRIED',
							value: '${message("yly.common.marriageState.married")}'
						},{
							label: 'UNMARRIED',
							value: '${message("yly.common.marriageState.unmarried")}'
						},{
							label: 'WIDOWED',
							value: '${message("yly.common.marriageState.widowed")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
						prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.marriageState}'"  name="marriageState" style="width:80px;"/>
	    		</td>
	    	
	    	  	<th>${message("yly.elderlyInfo.educationLevel")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'BACHELOR',
						value: '${message("yly.common.educationLevel.bachelor")}'
					},{
						label: 'MASTER',
						value: '${message("yly.common.educationLevel.master")}'
					},{
						label: 'DOCTOR',
						value: '${message("yly.common.educationLevel.doctor")}'
					},{
						label: 'HIGHSCHOOL',
						value: '${message("yly.common.educationLevel.highschool")}'
					},{
						label: 'ZHONGZHUAN',
						value: '${message("yly.common.educationLevel.zhongzhuan")}'
					},{
						label: 'JUNIORHIGHSCHOOL',
						value: '${message("yly.common.educationLevel.juniorhighschool")}'
					},{
						label: 'PRIMARYSCHOOL',
						value: '${message("yly.common.educationLevel.primaryschool")}'
					},{
						label: 'OTHER',
						value: '${message("yly.common.other")}'
					}],
					prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.educationLevel}'"  name="educationLevel" style="width:80px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.politicalOutlook")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'ZHONGDANGDANGYUAN',
						value: '${message("yly.common.politicalOutlook.dangyuan")}'
					},{
						label: 'GONGQINGTUANYUAN',
						value: '${message("yly.common.politicalOutlook.tuanyuan")}'
					},{
						label: 'MINZUDANGPAICHENGYUAN',
						value: '${message("yly.common.politicalOutlook.minzhudangpai")}'
					},{
						label: 'QUNZHONG',
						value: '${message("yly.common.politicalOutlook.qunzhong")}'
					},{
						label: 'OTHER',
						value: '${message("yly.common.other")}'
					}],
					prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.politicalOutlook}'"  name="politicalOutlook" style="width:105px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.religion")}:</th>
	    		<td>
	    			   <input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'BUDDHISM',
							value: '${message("yly.common.religion.buddhism")}'
						},{
							label: 'TAOISM',
							value: '${message("yly.common.religion.taoism")}'
						},{
							label: 'CHRISTIANITY',
							value: '${message("yly.common.religion.christianity")}'
						},{
							label: 'ISLAM',
							value: '${message("yly.common.religion.christianity")}'
						},{
							label: 'CATHOLICISM',
							value: '${message("yly.common.religion.catholicism")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
						prompt:'${message("yly.common.please.select")}' ,value:'${elderlyInfo.religion}'"  name="religion" style="width:85px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.originalCompany")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="originalCompany" value="${elderlyInfo.originalCompany}" validtype="length[0,120]" style="width:230px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.position")}:</th>
	    		<td>
	    			   <input class="easyui-textbox" type="text" name="position" value="${elderlyInfo.position}" validtype="length[0,20]"  style="width:80px;"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.registeredResidence")}:</th>
	    		<td colspan=3>
	    			  <input class="easyui-textbox" type="text" name="registeredResidence" value="${elderlyInfo.registeredResidence}" validtype="length[0,150]" style="width:400px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.residentialAddress")}:</th>
	    		<td colspan=3>
	    			  <input class="easyui-textbox" type="text" name="residentialAddress" value="${elderlyInfo.residentialAddress}" validtype="length[0,150]" style="width:400px;"/> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.personalHabits")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="personalHabits" value="${elderlyInfo.personalHabits}" validtype="length[0,150]" data-options="multiline:true,height:90,width:420" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.hobbies")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="hobbies" value="${elderlyInfo.hobbies}" validtype="length[0,150]" data-options="multiline:true,height:90,width:420" /> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.honors")}:</th>
	    		<td colspan=6>
	    			  <input class="easyui-textbox" type="text" name="honors" value="${elderlyInfo.honors}" validtype="length[0,150]" data-options="multiline:true,height:90,width:420" />
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped">
	    	<tr>
	    		<td colspan=4><h5>${message("yly.elderlyInfo.livingState")}</h5></td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.livingState")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'LIVINGALONE',
							value: '${message("yly.elderlyInfo.LivingState.livingalone")}'
						},{
							label: 'LIVINGWITHFAMILY',
							value: '${message("yly.elderlyInfo.LivingState.livingwithfamily")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
						prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.livingState}'"  name="livingState" style="width:120px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.housingInfo")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'PROPERTYRIGHTHOUSE',
							value: '${message("yly.elderlyInfo.housingInfo.propertyrighthouse")}'
						},{
							label: 'RENTALHOUSE',
							value: '${message("yly.elderlyInfo.housingInfo.rentalhouse")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
						prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.housingInfo}'"  name="housingInfo" style="width:85px;"/>
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.elderlyConsigner")}</h5></caption>
	     	<tr>
     			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerName")}:</th>
    			<td>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerName" value="${elderlyInfo.elderlyConsigner.consignerName}" validtype="length[0,15]" data-options="required:true" style="width:75px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber")}:</th>
    			<td>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerPhoneNumber" value="${elderlyInfo.elderlyConsigner.consignerPhoneNumber}" validtype="mobile" data-options="required:true" style="width:110px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.isSameCity")}:</th>
    			<td>
    				<input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
							label: 'true',
							value: '${message("yly.common.yes")}'
						},{
							label: 'false',
							value: '${message("yly.common.no")}'
						}],
						prompt:'${message("yly.common.please.select")}' ,value:'${elderlyInfo.elderlyConsigner.isSameCity}'"  name="elderlyConsigner.isSameCity" style="width:80px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.relation")}:</th>
    			<td>
		 			<input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
						label: 'CHILDREN',
						value: '${message("yly.common.relation.children")}'
					},{
						label: 'MARRIAGE_RELATIONSHIP',
						value: '${message("yly.common.relation.marriage_relationship")}'
					},{
						label: 'GRANDPARENTS_AND_GRANDCHILDREN',
						value: '${message("yly.common.relation.grandparents_and_grandchildren")}'
					},{
						label: 'BROTHERS_OR_SISTERS',
						value: '${message("yly.common.relation.brothers_or_sisters")}'
					},{
						label: 'DAUGHTERINLAW_OR_SONINLAW',
						value: '${message("yly.common.relation.daughterinlaw_or_soninlaw")}'
					},{
						label: 'FRIEND',
						value: '${message("yly.common.relation.friend")}'
					},{
						label: 'OTHER',
						value: '${message("yly.common.other")}'
					}],
					prompt:'${message("yly.common.please.select")}' ,value:'${elderlyInfo.elderlyConsigner.consignerRelation}'"  name="elderlyConsigner.consignerRelation" style="width:100px;"/>
    			</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.elderlyConsigner.companyAddress")}:</th>
    			<td colspan=3>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.companyAddress" value="${elderlyInfo.elderlyConsigner.companyAddress}" validtype="length[0,150]" style="width:400px;"/>
    			</td>
    			
    			<th>${message("yly.elderlyInfo.elderlyConsigner.consignerResidentialAddress")}:</th>
    			<td colspan=4>
    			 <input class="easyui-textbox" type="text" name="elderlyConsigner.consignerResidentialAddress" value="${elderlyInfo.elderlyConsigner.consignerResidentialAddress}" validtype="length[0,150]" style="width:400px;"/> 
    			</td>
	    	</tr>
	    	</table>
	    	<table id="talbe-familyMember-edit" class="table table-striped table-bordered">
		    	<caption><h5>${message("yly.elderlyInfo.familyMember")}</h5></caption>
		    	<tr>
					<td colspan="9">
						<a href="javascript:;" id="addFamilyMember-edit" class="btn green-color"><i class="fa fa-plus-square-o fa-2x"></i></a>
					</td>
				</tr>
				[#list elderlyInfo.elderlyFamilyMembers as familyMember]
		    	<tr>
		    		<input id="familyMemberIndexInitValueId" type="hidden" value="${elderlyInfo.elderlyFamilyMembers?size}"/>
					<th>${message("yly.elderlyInfo.familyMember.memberName")}:</th>
					<td>
						<input class="easyui-textbox" type="text" name="elderlyFamilyMembers[${familyMember_index}].memberName" value="${familyMember.memberName}" validtype="length[0,15]" style="width:75px;"/>
					</td>
					<th>${message("yly.elderlyInfo.familyMember.PhoneNumber")}:</th>
					<td>
						<input class="easyui-textbox" type="text" name="elderlyFamilyMembers[${familyMember_index}].memberPhoneNumber" value="${familyMember.memberPhoneNumber}" validtype="mobile" style="width:110px;"/>
					</td>
					<th>${message("yly.elderlyInfo.familyMember.relation")}:</th>
					<td>
				  		<input class="easyui-combobox" data-options="
							valueField: 'label',
							textField: 'value',
							data: [{
								label: 'CHILDREN',
								value: '${message("yly.common.relation.children")}'
							},{
								label: 'MARRIAGE_RELATIONSHIP',
								value: '${message("yly.common.relation.marriage_relationship")}'
							},{
								label: 'GRANDPARENTS_AND_GRANDCHILDREN',
								value: '${message("yly.common.relation.grandparents_and_grandchildren")}'
							},{
								label: 'BROTHERS_OR_SISTERS',
								value: '${message("yly.common.relation.brothers_or_sisters")}'
							},{
								label: 'DAUGHTERINLAW_OR_SONINLAW',
								value: '${message("yly.common.relation.daughterinlaw_or_soninlaw")}'
							},{
								label: 'FRIEND',
								value: '${message("yly.common.relation.friend")}'
							},{
								label: 'OTHER',
								value: '${message("yly.common.other")}'
							}],
							prompt:'${message("yly.common.please.select")}' ,value:'${familyMember.memberRelation}'"  name="elderlyFamilyMembers[${familyMember_index}].memberRelation" style="width:100px;"/>
				  		
					</td>
					<th>${message("yly.address")}:</th>
					<td>
						<input class="easyui-textbox" type="text" name="elderlyFamilyMembers[${familyMember_index}].memberResidentialAddress" value="${familyMember.memberResidentialAddress}" validtype="length[0,150]" style="width:400px;"/>
					</td>
					<td>
						<a href="javascript:;" class="family-member-remove-edit-original red-color"><i class="fa fa-times fa-2x"></i></a>
					</td>
				</tr>
				[/#list]
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.economicSituation")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.monthlyIncome")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-numberbox" name="monthlyIncome" value="${elderlyInfo.monthlyIncome}" data-options="min:0" style="width:60px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.sourceOfIncome")}:</th>
	    		<td>
	    			<input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
							label: 'PENSION',
							value: '${message("yly.elderlyInfo.sourceOfIncome.pension")}'
						},{
							label: 'CHILDSUPPORT',
							value: '${message("yly.elderlyInfo.sourceOfIncome.childsupport")}'
						},{
							label: 'SOCIALSUPPORT',
							value: '${message("yly.elderlyInfo.sourceOfIncome.socialsupport")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
					prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.sourceOfIncome}'"  name="sourceOfIncome" style="width:110px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.paymentWay")}:</th>
	    		<td>
	    	 		<input class="easyui-combobox" data-options="
					valueField: 'label',
					textField: 'value',
					data: [{
							label: 'CHILDREN_PAY',
							value: '${message("yly.elderlyInfo.paymentway.childpay")}'
						},{
							label: 'OWN_PAYMENT',
							value: '${message("yly.elderlyInfo.paymentway.ownpayment")}'
						},{
							label: 'GOV_PAY',
							value: '${message("yly.elderlyInfo.paymentway.govpay")}'
						},{
							label: 'COMPANY_PAY',
							value: '${message("yly.elderlyInfo.paymentway.companypay")}'
						},{
							label: 'OTHER',
							value: '${message("yly.common.other")}'
						}],
					prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.paymentWay}'"  name="paymentWay" style="width:90px;"/>
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.medicalExpPaymentWay")}:</th>
	    		<td>
		    		  <input class="easyui-combobox" data-options="
						valueField: 'label',
						textField: 'value',
						data: [{
								label: 'SOCIALINSURANCE',
								value: '${message("yly.elderlyInfo.medicalExpPaymentWay.socialinsurance")}'
							},{
								label: 'XINNONGHE',
								value: '${message("yly.elderlyInfo.medicalExpPaymentWay.xinnonghe")}'
							},{
								label: 'COMMERCIALINSURANCE',
								value: '${message("yly.elderlyInfo.medicalExpPaymentWay.commercialinsurance")}'
							},{
								label: 'SELFFINANCED',
								value: '${message("yly.elderlyInfo.medicalExpPaymentWay.selffinanced")}'
							},{
								label: 'PUBLICFINANCED',
								value: '${message("yly.elderlyInfo.medicalExpPaymentWay.publicfinanced")}'
							}],
						prompt:'${message("yly.common.please.select")}',value:'${elderlyInfo.medicalExpPaymentWay}'"  name="medicalExpPaymentWay" style="width:90px;"/>
	    		</td>
	    	</tr>
	    	</table>
	    	<table class="table table-striped table-bordered">
	    	<caption><h5>${message("yly.elderlyInfo.evaluatingResult")}</h5></caption>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.evaluatingResult")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="evaluatingResultEditId"  name="evaluatingResultEditId" value="${elderlyInfo.evaluatingResult.id}" style="width:120px;" data-options="editable:false" > 
	    		</td>
	    		<th>${message("yly.elderlyInfo.evaluatingScore")}:</th>
	    		<td>
	    			  <input type="text" class="easyui-numberbox" name="evaluatingScore" value="${elderlyInfo.evaluatingScore}" data-options="min:0,precision:2" style="width:40px;">
	    		</td>
	    		
	    		<th>${message("yly.elderlyInfo.nursingLevel")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" id="nursingLevelEditId"  name="nursingLevelEditId" value="${elderlyInfo.nursingLevel.id}" style="width:120px;" data-options="editable:false" >   
	    		</td>
	    	</tr>
	    </table>
</form>

<script type="text/javascript">
$().ready(function() {
	var $addFamilyMemberEdit = $("#addFamilyMember-edit");
	var $tableFamilyMember = $("#talbe-familyMember-edit");
	var familyMemberIndex = $("#familyMemberIndexInitValueId").val();
	if(familyMemberIndex == undefined){
		familyMemberIndex=0;
	}
	
	//删除编辑之前已经存在的家庭成员数据	
	$("a.family-member-remove-edit-original").click(function() {
	var $this = $(this);
	$this.parent().parent().remove();
	});
	
	$addFamilyMemberEdit.click(function() {
	[@compress single_line = true]
		var trHtml = 
		'<tr>
			<th>${message("yly.elderlyInfo.familyMember.memberName")}:<\/th>
			<td>
				<input class="easyui-textbox family-member-textbox-edit" type="text" name="elderlyFamilyMembers[' + familyMemberIndex + '].memberName" validtype="length[0,15]" style="width:75px;"\/>
			<\/td>
			<th>${message("yly.elderlyInfo.familyMember.PhoneNumber")}:<\/th>
			<td>
				<input class="easyui-textbox family-member-textbox-edit" type="text" name="elderlyFamilyMembers[' + familyMemberIndex + '].memberPhoneNumber" validtype="mobile" style="width:110px;"\/>
			<\/td>
			<th>${message("yly.elderlyInfo.familyMember.relation")}:<\/th>
			<td>
				<input class="easyui-combobox family-member-combobox-edit" name="elderlyFamilyMembers[' + familyMemberIndex + '].memberRelation" style="width:100px;"/>
			<\/td>
			<th>${message("yly.address")}:<\/th>
			<td>
				<input class="easyui-textbox family-member-textbox-edit" type="text" name="elderlyFamilyMembers[' + familyMemberIndex + '].memberResidentialAddress" validtype="length[0,150]" style="width:400px;"/>
			<\/td>
			<td>
				<a href="javascript:;" class="family-member-remove-edit red-color"><i class="fa fa-times fa-2x"></i><\/a>
			<\/td>
		<\/tr>';
	[/@compress]
	$tableFamilyMember.append(trHtml);
	familyMemberIndex ++;
	
	$('.family-member-textbox-edit').textbox({    
	})

	
	$('.family-member-combobox-edit').combobox({    
	    valueField: 'label',
		textField: 'value',
	    data: [{
				label: 'CHILDREN',
				value: '${message("yly.common.relation.children")}'
				},{
					label: 'MARRIAGE_RELATIONSHIP',
					value: '${message("yly.common.relation.marriage_relationship")}'
				},{
					label: 'GRANDPARENTS_AND_GRANDCHILDREN',
					value: '${message("yly.common.relation.grandparents_and_grandchildren")}'
				},{
					label: 'BROTHERS_OR_SISTERS',
					value: '${message("yly.common.relation.brothers_or_sisters")}'
				},{
					label: 'DAUGHTERINLAW_OR_SONINLAW',
					value: '${message("yly.common.relation.daughterinlaw_or_soninlaw")}'
				},{
					label: 'FRIEND',
					value: '${message("yly.common.relation.friend")}'
				},{
					label: 'OTHER',
					value: '${message("yly.common.other")}'
				}],
				prompt:'${message("yly.common.please.select")}'
	}); 
	
	
	$("a.family-member-remove-edit").click(function() {
	var $this = $(this);
	$this.parent().parent().remove();
	});
	});
	
	
})
</script>

