var current_fs, next_fs, previous_fs; //当前的fieldsets，下一个fieldsets，上一个fieldsets
//var left, opacity, scale; //fieldset 的动画属性，向左，不透明度，大小规模
//var animating;

var liIndex=0;

$(".next").click(function(){//单击 下一步按钮
//	if(animating) return false;
//	animating = true;
	
	current_fs = $(this).parent().parent();
	next_fs = $(this).parent().parent().next();
	
	$("#li"+liIndex).removeClass("active");
	$("#li"+liIndex).addClass("negative");
	liIndex++;
	$("#li"+liIndex).addClass("active");
	$("#li"+liIndex).removeClass("negative");
	//显示下一个 fieldset
	next_fs.show(); 
	//隐藏当前的 fieldset
// 	current_fs.animate({opacity: 0}, {
//		step: function(now, mx) {
//			scale = 1 - (1 - now) * 0.01;
//			left = (now * 1)+"%";
//			opacity = 1 - now;
//			current_fs.css({'transform': 'scale('+scale+')'});
//			next_fs.css({'left': left, 'opacity': opacity});
//		}, 
//		duration: 800, 
//		complete: function(){
//			current_fs.hide();
//			animating = false;
//		}, 
//		easing: 'easeInOutBack'
//	});
	current_fs.hide();
});

$(".previous").click(function(){//单击上一步按钮
//	if(animating) return false;
//	animating = true;
	
	current_fs = $(this).parent().parent();
	previous_fs = $(this).parent().parent().prev();
	
	$("#li"+liIndex).removeClass("active");
	$("#li"+liIndex).addClass("negative");
	liIndex--;
	$("#li"+liIndex).addClass("active");
	$("#li"+liIndex).removeClass("negative");
	
	//显示上一个 fieldset
	previous_fs.show(); 
	//隐藏当前的 fieldset
//	current_fs.animate({opacity: 0}, {
//		step: function(now, mx) {
//			scale = 0.99 + (1 - now) * 0.01;
//			left = ((1-now) * 1)+"%";
//			opacity = 1 - now;
//			current_fs.css({'left': left});
//			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
//		}, 
//		duration: 800, 
//		complete: function(){
//			current_fs.hide();
//			animating = false;
//		}, 
//		easing: 'easeInOutBack'
//	});
	current_fs.hide();
});

$(".saveEvaluating").click(function(){//单击提交按钮

	var validate = $('#addEvaluating_form').form('validate');
	if(validate){								
		$.ajax({
			url:"../elderlyEvaluatingRecord/save.jhtml",
			type:"post",
			data:$("#addEvaluating_form").serialize(),
			beforeSend:function(){
				$.messager.progress({
					text:message("yly.common.saving")
				});
			},
			success:function(result,response,status){
				$.messager.progress('close');
				showSuccessMsg(result.content);
				$('#addEvaluating').dialog("close");
				$("#elderlyEvaluating-table-list").datagrid('reload');
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
				$.messager.progress('close');
				alertErrorMsg();
			}
		});
	}else{
		$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.info_no_enough"),'warning');
	}
	return false;
})
$(".updateEvaluating").click(function(){//单击提交按钮

	var validate = $('#editEvaluating_form').form('validate');
	if(validate){								
		$.ajax({
			url:"../elderlyEvaluatingRecord/update.jhtml",
			type:"post",
			data:$("#editEvaluating_form").serialize(),
			beforeSend:function(){
				$.messager.progress({
					text:message("yly.common.saving")
				});
			},
			success:function(result,response,status){
				$.messager.progress('close');
				showSuccessMsg(result.content);
				$('#editEvaluating').dialog("close");
				$("#elderlyEvaluating-table-list").datagrid('reload');
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
				$.messager.progress('close');
				alertErrorMsg();
			}
		});
	}else{
		$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.info_no_enough"),'warning');
	}
	return false;
})
$("#generateIdentifier_btn").click(function(){//生成老人编号
	$.ajax({
		url:"../identifier/generateId.jhtml",
		type:"get",
		data:{
		"idType":"ELDERLYINFO_IDENTIFIER"
		},
		success:function(result,response,status){
			$("#identifier_input").textbox('setValue',result.id);
		}
	});
})
function skipTo(selectedIndex){//直接选择每个模块，（非上一步下一步）
	current_fs = $("#fieldset"+liIndex);
	selected_fs = $("#fieldset"+selectedIndex);

	$("#li"+liIndex).removeClass("active");
	$("#li"+liIndex).addClass("negative");
	liIndex = selectedIndex;
	$("#li"+selectedIndex).addClass("active");
	$("#li"+liIndex).removeClass("negative");
	
	//显示选中的 fieldset
	selected_fs.show(); 
	//隐藏当前的fieldset
	current_fs.hide();
}

//自动填充每道题的分数、每个模块总分以及每个模块的等级
function populateScore(answer_index,optionScore,answer_begin_index,sectionSize,section_index){
	//填充每道题的分数
	$("#scoreOf"+answer_index).textbox('setValue',optionScore);
	//填充每个模块总分
	var optionScore = 0;
	for(var i=0;  i<sectionSize; i++){
		var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
		if(nextScore!=null &&nextScore!=""){
			optionScore = optionScore +parseInt(nextScore);
		}
	}	
	$("#sectionScoreOf"+section_index).textbox('setValue',optionScore);		
	
	}
//填充每个模块的等级
function populateLevel(answer_begin_index,sectionSize,section_index,section_id){
		var levelFlag=true;//标记是否可以开始为整个模块评等级
		for(var i=0;  i<sectionSize; i++){
			var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
			if(nextScore!=null  && nextScore!=""){
				levelFlag =levelFlag&& true;
			}else{
				levelFlag =levelFlag&& false;
			}
		}	
		if(levelFlag){//当且仅当整个模块的所有题都做了，才能评等级
			if(section_id == null || section_id == ""){
				$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.section_level_error"),'warning');
				return false;
			}
			var dataMap ={};
			dataMap.sectionId = section_id;
			var answerScoreList = new Array();
			for(var i=0;  i<sectionSize; i++){
				var nextItemId=$("#itemIdOf"+(parseInt(answer_begin_index)+i)).val();
				var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
				if(nextScore!=null  && nextScore!=""&& nextItemId!=null  && nextItemId!=""){
					var map = {}; 
					map["id"] = nextItemId; 
					map["score"] = nextScore; 
					answerScoreList.push(map);
				}
			}		
			if(answerScoreList.length == 0){
				$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.section_level_error"),'warning');
				return false;
			}
			dataMap.items=answerScoreList;
			
			var dataMapString = JSON.stringify(dataMap);
			$.ajax({
				url:"../elderlyEvaluatingRecord/getSectionLevel.jhtml",
				type:"post",
				data:{"itemsScoreJSON":dataMapString},
				success:function(result,response,status){
					if(result.level==-1){
						$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.section_level_error"),'warning');
					}else{
							$("#sectionLevelOf"+section_index).textbox('setValue',result.level);	
							$("#sectionLevel"+section_index).textbox('setValue',result.level);	
							$("#formLevel").textbox('setValue',null);		//因为每个模块的等级发生改变，需要清空评估表之前的等级
		 				  if(parseInt(result.level)==0){
								$("#sectionLevelOf"+section_index).parent().css("color","green");//绿色  能力完好
								//$("#sectionLevel"+section_index).parent().css("color","green");//绿色  能力完好
							}
							if(parseInt(result.level)==1){
								$("#sectionLevelOf"+section_index).parent().css("color","orange");//橘黄色  轻度受损
								//$("#sectionLevel"+section_index).parent().css("color","orange");//橘黄色  轻度受损
							}
							if(parseInt(result.level)==2){
								$("#sectionLevelOf"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
								//$("#sectionLevel"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
							}
							if(parseInt(result.level)==3){
								$("#sectionLevelOf"+section_index).parent().css("color","red");//红色  重度受损
								//$("#sectionLevel"+section_index).parent().css("color","red");//红色  重度受损
							}
					}
				}
			});			
	}
}
//填充评估表的等级
function populateFormLevel(form_sectionSize){//模块等级发生改变时候

			var formLevelFlag=true;//标记是否可以开始为整个评估表的评等级
			for(var i=0;  i<form_sectionSize; i++){
				var nextSectionScore=$("#sectionLevel"+i).textbox('getValue');
				if(nextSectionScore!=null  && nextSectionScore!=""){
					formLevelFlag =formLevelFlag&& true;
				}else{
					formLevelFlag =formLevelFlag&& false;
				}
			}	
			if(formLevelFlag){//当且仅当每个模块的等级都出来了，才能对整个评估表评等级
				var sectionLevelList = new Array();
				for(var i=0;  i<form_sectionSize; i++){
					var nextSectionId=$("#sectionIdOf"+i).val();
					var nextSectionScore=$("#sectionScoreOf"+i).textbox('getValue');
					var nextSectionLevel=$("#sectionLevel"+i).textbox('getValue');
					if(nextSectionLevel!=null  && nextSectionLevel!=""){
						var map = {}; 
						map["id"] = nextSectionId; 
						map["score"] = nextSectionScore; 
						map["level"] = nextSectionLevel; 
						sectionLevelList.push(map);
					}
				}	
				if(sectionLevelList.length == 0){
					$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.form_level_error"),'warning');
					return false;
				}
				var dataMapString = JSON.stringify(sectionLevelList);
				$("#sectionsResult").val(JSON.stringify(sectionLevelList));
				$.ajax({
					url:"../elderlyEvaluatingRecord/getFormLevel.jhtml",
					type:"post",
					data:{"sectionsLevelJSON":dataMapString},
					success:function(result,response,status){
						if(result.level ==""){
							$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.form_level_error"),'warning');
							return false;
						}else{
							$("#formLevel").textbox('setValue',result.level);	
			 				  if(parseInt(result.level)==0){
			 					 $("#formLevel").parent().css("color","green");//绿色  能力完好
								}
								if(parseInt(result.level)==1){
									$("#formLevel").parent().css("color","orange");//橘黄色  轻度受损
								}
								if(parseInt(result.level)==2){
									$("#formLevel").parent().css("color","#cc6600");//深黄色  中度受损
								}
								if(parseInt(result.level)==3){
									$("#formLevel").parent().css("color","red");//红色  重度受损
								}
						}
						
					}
				});
			}
	}
/**
 * 老人查询功能
 */
function searchAllElderlyInfo() {
	$('#searchElderlyInfo').dialog({    
	    title: message("yly.visitelderly.search"),    
	    width: 1000,
	    height: 500,
	    modal:true,
	    cache: false,   
	    href:'../elderlyInfo/commonElderlySearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchElderlyInfo').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	/**
	    	 * 此datagrid 用户展示老人数据,并且提供查询功能
	    	 */
	    	$("#common_elderlyInfoSearch-table-list").datagrid({
	    	 title:message("yly.elderlyinfo"),
	    	 fitColumns:true,
	    	 url:'../elderlyInfo/list.jhtml',  
	    	 pagination:true,
	    	 loadMsg:message("yly.common.loading"),
	    	 striped:true,
	    	 onDblClickRow : function (rowIndex, rowData){
	    		 if(rowData){
	    			 $("#generateIdentifier_btn") .hide();//隐藏生成编号超链接
	    		 }else{
	    			 return false;
	    		 }
	    		 //挨个填充赋值
	    		 $("#elderlyInfoID").val(rowData.id);
	    		 $("#identifier_input").textbox('setValue',rowData.identifier);
	    		 $('#identifier_input').textbox('readonly');//只读
	    		 $("#beHospitalizedDate").datebox('setValue',new Date(rowData.beHospitalizedDate).Format("yyyy-MM-dd"));
	    		 $('#beHospitalizedDate').datebox('readonly');//只读
	    		 $("#name").textbox('setValue',rowData.name);
	    		 $('#name').textbox('readonly');//只读
	    		 $("#personnelCategoryId").textbox('setValue',rowData.personnelCategoryId);//???
	    		 $('#personnelCategoryId').textbox('readonly');//只读
	    		 $("#gender").combobox('setValue',rowData.gender);
	    		 $('#gender').combobox('readonly');//只读
	    		 $("#elderlyPhoneNumber").textbox('setValue',rowData.elderlyPhoneNumber);
	    		 $('#elderlyPhoneNumber').textbox('readonly');//只读
	    		 $("#IDCard").textbox('setValue',rowData.IDCard);
	    		 $('#IDCard').textbox('readonly');//只读
	    		 $("#age").numberbox('setValue',rowData.age);
	    		 $('#age').numberbox('readonly');//只读
	    		 $("#birthday").datebox('setValue',new Date(rowData.birthday).Format("yyyy-MM-dd"));
	    		 $('#birthday').datebox('readonly');//只读
	    		 $("#socialInsuranceNumber").textbox('setValue',rowData.socialInsuranceNumber);
	    		 $('#socialInsuranceNumber').textbox('readonly');//只读
	    		 $("#placeOfOrigin").textbox('setValue',rowData.placeOfOrigin);
	    		 $('#placeOfOrigin').textbox('readonly');//只读
	    		 $("#nation").textbox('setValue',rowData.nation);
	    		 $('#nation').textbox('readonly');//只读
	    		 $("#marriageState").combobox('setValue',rowData.marriageState);
	    		 $('#marriageState').combobox('readonly');//只读
	    		 $("#educationLevel").combobox('setValue',rowData.educationLevel);
	    		 $('#educationLevel').combobox('readonly');//只读
	    		 $("#politicalOutlook").combobox('setValue',rowData.politicalOutlook);
	    		 $('#politicalOutlook').combobox('readonly');//只读
	    		 $("#religion").combobox('setValue',rowData.religion);
	    		 $('#religion').combobox('readonly');//只读
	    		 $("#originalCompany").textbox('setValue',rowData.originalCompany);
	    		 $('#originalCompany').textbox('readonly');//只读
	    		 $("#position").textbox('setValue',rowData.position);
	    		 $('#position').textbox('readonly');//只读
	    		 $("#registeredResidence").textbox('setValue',rowData.registeredResidence);
	    		 $('#registeredResidence').textbox('readonly');//只读
	    		 $("#residentialAddress").textbox('setValue',rowData.residentialAddress);
	    		 $('#residentialAddress').textbox('readonly');//只读
	    		 $("#personalHabits").textbox('setValue',rowData.personalHabits);
	    		 $('#personalHabits').textbox('readonly');//只读
	    		 $("#hobbies").textbox('setValue',rowData.hobbies);
	    		 $('#hobbies').textbox('readonly');//只读
	    		 $("#honors").textbox('setValue',rowData.honors);
	    		 $('#honors').textbox('readonly');//只读
	    		 $("#livingState").combobox('setValue',rowData.livingState);
	    		 $('#livingState').combobox('readonly');//只读
	    		 $("#housingInfo").combobox('setValue',rowData.housingInfo);
	    		 $('#housingInfo').combobox('readonly');//只读
	    		 $("#elderlyConsigner_id").val(rowData.elderlyConsigner.id);
	    		 $("#elderlyConsigner_consignerName").textbox('setValue',rowData.elderlyConsigner.consignerName);
	    		 $('#elderlyConsigner_consignerName').textbox('readonly');//只读
	    		 $("#elderlyConsigner_consignerPhoneNumber").textbox('setValue',rowData.elderlyConsigner.consignerPhoneNumber);
	    		 $('#elderlyConsigner_consignerPhoneNumber').textbox('readonly');//只读
	    		 $("#elderlyConsigner_isSameCity").combobox('setValue',rowData.elderlyConsigner.isSameCity);
	    		 $('#elderlyConsigner_isSameCity').combobox('readonly');//只读
	    		 $("#elderlyConsigner_consignerRelation").combobox('setValue',rowData.elderlyConsigner.consignerRelation);
	    		 $('#elderlyConsigner_consignerRelation').combobox('readonly');//只读
	    		 $("#elderlyConsigner_companyAddress").textbox('setValue',rowData.elderlyConsigner.companyAddress);
	    		 $('#elderlyConsigner_companyAddress').textbox('readonly');//只读
	    		 $("#elderlyConsigner_consignerResidentialAddress").textbox('setValue',rowData.elderlyConsigner.consignerResidentialAddress);
	    		 $('#elderlyConsigner_consignerResidentialAddress').textbox('readonly');//只读
	    		 $("#monthlyIncome").numberbox('setValue',rowData.monthlyIncome);
	    		 $('#monthlyIncome').numberbox('readonly');//只读
	    		 $("#sourceOfIncome").combobox('setValue',rowData.sourceOfIncome);
	    		 $('#sourceOfIncome').combobox('readonly');//只读
	    		 $("#paymentWay").combobox('setValue',rowData.paymentWay);
	    		 $('#paymentWay').combobox('readonly');//只读
	    		 $("#medicalExpPaymentWay").combobox('setValue',rowData.medicalExpPaymentWay);
	    		 $('#medicalExpPaymentWay').combobox('readonly');//只读
	    		 $("#evaluatingResultId").combobox('setValue',rowData.evaluatingResultId);//????
	    		 $('#evaluatingResultId').combobox('readonly');//只读
	    		 $("#evaluatingScore").numberbox('setValue',rowData.evaluatingScore);
	    		 $('#evaluatingScore').numberbox('readonly');//只读
	    		 $("#nursingLevelId").combobox('setValue',rowData.nursingLevelId);//????
	    		 $('#nursingLevelId').combobox('readonly');//只读
	    		 $("#evaluatingReason").combobox('setValue',rowData.evaluatingReason);//???
	    		 //$('#evaluatingReason').combobox('disable');//只读
	    		 
	    		 //填充familymember
	    		 $('#talbe-familyMember').html('<caption><h5>'+message("yly.elderlyInfo.elderlyFamilyMembers")+'</h5></caption>');
	    		 var familymenbersHtml="";
	    		 var familymembers = rowData.elderlyFamilyMembers;
	    		 if(familymembers != null && familymembers.length > 0){
	    			 var size = parseInt(familymembers.length);
	    			 for(var i = 0; i<size; i++){
	    				 var familymember = familymembers[i];
	    				 familymenbersHtml+='<tr>';
	    				 familymenbersHtml+='<th>'+message("yly.elderlyInfo.familyMember.memberName")+':</th>';
	    				 familymenbersHtml+='<td><input class="easyui-textbox" type="text" name="elderlyFamilyMembers['+i+'].memberName" value="'+familymember.memberName+'" readonly=true  style="width:75px;"/></td>';
	    				 familymenbersHtml+='<th>'+message("yly.elderlyInfo.familyMember.PhoneNumber")+':</th>';
	    				 familymenbersHtml+='<td><input class="easyui-textbox" type="text" name="elderlyFamilyMembers['+i+'].memberPhoneNumber" value="'+familymember.memberPhoneNumber+'" readonly=true  style="width:110px;"/></td>';
	    				 familymenbersHtml+='<th>'+message("yly.common.relation")+':</th>';
	    				 familymenbersHtml+='<input  type="hidden" name="elderlyFamilyMembers['+i+'].memberRelation" value="'+familymember.memberRelation+'"/>';
	    				 var memberRelation="";
	    				 if(familymember.memberRelation == "SELF"){
	    					 memberRelation=message("yly.common.relation.self");
	    				 }
		    			 if(familymember.memberRelation == "CHILDREN"){
		    					 memberRelation=message("yly.common.relation.children");
		    			 }
		    			 if(familymember.memberRelation == "MARRIAGE_RELATIONSHIP"){
	    					 memberRelation=message("yly.common.relation.marriage_relationship");
		    			 }
		    			 if(familymember.memberRelation == "GRANDPARENTS_AND_GRANDCHILDREN"){
	    					 memberRelation=message("yly.common.relation.grandparents_and_grandchildren");
		    			 }
		    			 if(familymember.memberRelation == "BROTHERS_OR_SISTERS"){
	    					 memberRelation=message("yly.common.relation.brothers_or_sisters");
		    			 }
		    			 if(familymember.memberRelation == "DAUGHTERINLAW_OR_SONINLAW"){
	    					 memberRelation=message("yly.common.relation.daughterinlaw_or_soninlaw");
		    			 }
		    			 if(familymember.memberRelation == "FRIEND"){
	    					 memberRelation=message("yly.common.relation.friend");
		    			 }
		    			 if(familymember.memberRelation == "OTHER"){
	    					 memberRelation=message("yly.common.other");
		    			 }
	    				 familymenbersHtml+='<td><input class="easyui-textbox" type="text"  value="'+memberRelation+'" readonly=true  style="width:110px;"/></td>';
						familymenbersHtml+='<th>'+message("yly.common.address")+'</th>';
						var memberResidentialAddress="";
						if(familymember.memberResidentialAddress != null){
							memberResidentialAddress=familymember.memberResidentialAddress;
						}
						familymenbersHtml+='<td><input class="easyui-textbox" type="text" name="elderlyFamilyMembers['+i+'].memberResidentialAddress" value="'+memberResidentialAddress+'" readonly=true  style="width:400px;"/></td>';
						familymenbersHtml+='</tr>';
	    			 }
	    		 } 
	    		 
	    		 $('#talbe-familyMember').append(familymenbersHtml);
	    		 //关闭当前窗口
	    		 $('#searchElderlyInfo').dialog("close");
	    		 
	    		 $.parser.parse($('#talbe-familyMember'));

	    	 },
	    	 columns:[
	    	    [
	    	        {field:'ck',checkbox:true},
		      {title:message("yly.elderlyinfo.identifier"),field:"identifier",width:12,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"name",width:20,align:'center',sortable:true},
		      {title:message("yly.common.age"),field:"age",width:10,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyPhoneNumber"),field:"elderlyPhoneNumber",width:20,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.beHospitalizedDate"),field:"beHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }},
		      {title:message("yly.common.gender"),field:"gender",width:10,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		      {title:message("yly.common.idcard"),field:"idcard",width:40,align:'center',sortable:true},
		      {title:message("yly.common.bedRoom"),field:"bedLocation",width:30,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber"),field:"consignerPhoneNumber",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(row !=  null && row.elderlyConsigner != null){
		    		  return row.elderlyConsigner.consignerPhoneNumber;  
		    	  }
		      	}},
		      {title:message("yly.elderly.status"),field:"elderlyStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "IN_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.in_nursing_home");
		    	  	}
		    	  	if(value == "OUT_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.out_nursing_home");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKIN"){
		    	  		return  message("yly.elderly.status.in_progress_checkin");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKOUT"){
		    	  		return  message("yly.elderly.status.in_progress_checkout");
		    	  	}
		    	  	if(value == "DEAD"){
		    	  		return  message("yly.elderly.status.dead");
		    	  	}
		      	}}
	    	    ]
	    	 ]

	    	});
	    	
	    	$("#common_elderlyinfo_search_btn").click(function(){
	  		  var _queryParams = $("#common_elderlyinfo_search_form").serializeJSON();
	  		  $('#common_elderlyInfoSearch-table-list').datagrid('options').queryParams = _queryParams;  
	  		  $("#common_elderlyInfoSearch-table-list").datagrid('reload');
	  	})
	    }
	});  
}


