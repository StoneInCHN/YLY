var current_fs, next_fs, previous_fs; //当前的fieldsets，下一个fieldsets，上一个fieldsets

var liIndex=0;

$(".next").click(function(){//单击 下一步按钮
	
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
	current_fs.hide();
});

$(".previous").click(function(){//单击上一步按钮
	
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
		$.messager.alert('提示','信息提供有误或者不够完善！请核查...','warning');
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
		$.messager.alert('提示','信息提供有误或者不够完善！请核查...','warning');
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

//自动填充每道题的分数、每个模块总分
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
	var levelFlag=true;//标记整个模块的题都做完了
	for(var i=0;  i<sectionSize; i++){
		var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
		if(nextScore!=null  && nextScore!=""){
			levelFlag =levelFlag&& true;
		}else{
			levelFlag =levelFlag&& false;
		}
	}	
	if(levelFlag){//当且仅当整个模块的所有题都做了，才将模块总分写入最后的评估报表单
		$("#sectionScore"+section_index).textbox('setValue',optionScore);	//填充评估报表中每个模块的总分
	}	
}
//填充每个模块的等级
//function populateLevel(answer_begin_index,sectionSize,section_index,section_name){
//		var levelFlag=true;//标记是否可以开始为整个模块评等级
//		for(var i=0;  i<sectionSize; i++){
//			var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
//			if(nextScore!=null  && nextScore!=""){
//				levelFlag =levelFlag&& true;
//			}else{
//				levelFlag =levelFlag&& false;
//			}
//		}	
//		if(levelFlag){//当且仅当整个模块的所有题都做了，才能评等级
//			var answerScoreList = new Array();
//			for(var i=0;  i<sectionSize; i++){
//				var nextItemName=$("#itemNameOf"+(parseInt(answer_begin_index)+i)).val();
//				var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
//				if(nextScore!=null  && nextScore!=""){
//					answerScoreList.push(nextItemName+"::::"+nextScore);
//				}
//			}			
//			$.ajax({
//				url:"../elderlyEvaluatingRecord/getSectionLevel.jhtml?sectionName="+section_name+"&answerScores="+answerScoreList.join(";;;;"),
//				type:"get",
//				success:function(result,response,status){
//					$("#sectionLevelOf"+section_index).textbox('setValue',result.level);	
//					$("#sectionLevel"+section_index).textbox('setValue',result.level);	
//					$("#formLevel").textbox('setValue',null);		//因为每个模块的等级发生改变，需要清空评估表之前的等级
// 				  if(parseInt(result.level)==0){
//						$("#sectionLevelOf"+section_index).parent().css("color","green");//绿色  能力完好
//						//$("#sectionLevel"+section_index).parent().css("color","green");//绿色  能力完好
//					}
//					if(parseInt(result.level)==1){
//						$("#sectionLevelOf"+section_index).parent().css("color","orange");//橘黄色  轻度受损
//						//$("#sectionLevel"+section_index).parent().css("color","orange");//橘黄色  轻度受损
//					}
//					if(parseInt(result.level)==2){
//						$("#sectionLevelOf"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
//						//$("#sectionLevel"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
//					}
//					if(parseInt(result.level)==3){
//						$("#sectionLevelOf"+section_index).parent().css("color","red");//红色  重度受损
//						//$("#sectionLevel"+section_index).parent().css("color","red");//红色  重度受损
//					}
//				}
//			});			
//	}
//}
//填充评估表的等级
function populateFormLevel(form_sectionSize){//模块等级发生改变时候

			var formLevelFlag=true;//标记是否可以开始为整个评估表的评等级
			for(var i=0;  i<form_sectionSize; i++){
				var nextSectionScore=$("#sectionScore"+i).textbox('getValue');
				if(nextSectionScore!=null  && nextSectionScore!=""){
					formLevelFlag =formLevelFlag&& true;
				}else{
					formLevelFlag =formLevelFlag&& false;
				}
			}	
			if(formLevelFlag){//当且仅当每个模块的总分出来了，才能对整个评估表评等级
				
				var sectionLevelList = new Array();
				for(var i=0;  i<form_sectionSize; i++){
					var nextSectionLevel=$("#sectionScore"+i).textbox('getValue');
					var nextSectionName=$("#sectionNameOf"+i).val();
					if(nextSectionLevel!=null  && nextSectionLevel!=""){
						sectionLevelList.push(nextSectionName+"::::"+nextSectionLevel);
					}
				}	
				$("#sectionsResult").val(sectionLevelList.join(";;;;"));	
				$.ajax({
					url:"../elderlyEvaluatingRecord/getCustomFormLevel.jhtml?formId="+$("#evaluatintFormID").val() +"&sectionLevels="+sectionLevelList.join(";;;;"),
					type:"get",
					success:function(result,response,status){
						$("#formLevel").textbox('setValue',result.level);	
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
	    		 //挨个填充赋值
	    		 $("#id").val(rowData.id);
	    		 $("#identifier_input").textbox('setValue',rowData.identifier);
	    		 $("#beHospitalizedDate").datebox('setValue',new Date(rowData.beHospitalizedDate).Format("yyyy-MM-dd"));
	    		 $("#name").textbox('setValue',rowData.name);
	    		 $("#personnelCategoryId").textbox('setValue',rowData.personnelCategoryId);//???
	    		 $("#gender").combobox('setValue',rowData.gender);
	    		 $("#elderlyPhoneNumber").textbox('setValue',rowData.elderlyPhoneNumber);
	    		 $("#IDCard").textbox('setValue',rowData.IDCard);
	    		 $("#age").numberbox('setValue',rowData.age);
	    		 $("#birthday").datebox('setValue',new Date(rowData.birthday).Format("yyyy-MM-dd"));
	    		 $("#socialInsuranceNumber").textbox('setValue',rowData.socialInsuranceNumber);
	    		 $("#placeOfOrigin").textbox('setValue',rowData.placeOfOrigin);
	    		 $("#nation").textbox('setValue',rowData.nation);
	    		 $("#marriageState").combobox('setValue',rowData.marriageState);
	    		 $("#educationLevel").combobox('setValue',rowData.educationLevel);
	    		 $("#politicalOutlook").combobox('setValue',rowData.politicalOutlook);
	    		 $("#religion").combobox('setValue',rowData.religion);
	    		 $("#originalCompany").textbox('setValue',rowData.originalCompany);
	    		 $("#position").textbox('setValue',rowData.position);
	    		 $("#registeredResidence").textbox('setValue',rowData.registeredResidence);
	    		 $("#residentialAddress").textbox('setValue',rowData.residentialAddress);
	    		 $("#personalHabits").textbox('setValue',rowData.personalHabits);
	    		 $("#hobbies").textbox('setValue',rowData.hobbies);
	    		 $("#honors").textbox('setValue',rowData.honors);
	    		 $("#livingState").combobox('setValue',rowData.livingState);
	    		 $("#housingInfo").combobox('setValue',rowData.housingInfo);
	    		 $("#elderlyConsigner_consignerName").textbox('setValue',rowData.elderlyConsigner.consignerName);
	    		 $("#elderlyConsigner_consignerPhoneNumber").textbox('setValue',rowData.elderlyConsigner.consignerPhoneNumber);
	    		 $("#elderlyConsigner_isSameCity").combobox('setValue',rowData.elderlyConsigner.isSameCity);
	    		 $("#elderlyConsigner_consignerRelation").combobox('setValue',rowData.elderlyConsigner.consignerRelation);
	    		 $("#elderlyConsigner_companyAddress").textbox('setValue',rowData.elderlyConsigner.companyAddress);
	    		 $("#elderlyConsigner_consignerResidentialAddress").textbox('setValue',rowData.elderlyConsigner.consignerResidentialAddress);
	    		 $("#monthlyIncome").numberbox('setValue',rowData.monthlyIncome);
	    		 $("#sourceOfIncome").combobox('setValue',rowData.sourceOfIncome);
	    		 $("#paymentWay").combobox('setValue',rowData.paymentWay);
	    		 $("#medicalExpPaymentWay").combobox('setValue',rowData.medicalExpPaymentWay);
	    		 $("#evaluatingResultId").combobox('setValue',rowData.evaluatingResultId);//????
	    		 $("#evaluatingScore").numberbox('setValue',rowData.evaluatingScore);
	    		 $("#nursingLevelId").combobox('setValue',rowData.nursingLevelId);//????
	    		 $("#evaluatingReason").combobox('setValue',rowData.evaluatingReason);//???
	    		 //关闭当前窗口
	    		 $('#searchElderlyInfo').dialog("close");
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