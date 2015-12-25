var current_fs, next_fs, previous_fs; //当前的fieldsets，下一个fieldsets，上一个fieldsets


var editliIndex=0;

$(".next").click(function(){//单击 下一步按钮	
	current_fs = $(this).parent().parent();
	next_fs = $(this).parent().parent().next();
	
	$("#editli"+editliIndex).removeClass("active");
	$("#editli"+editliIndex).addClass("negative");
	editliIndex++;
	$("#editli"+editliIndex).addClass("active");
	$("#editli"+editliIndex).removeClass("negative");
	//显示下一个 fieldset
	next_fs.show(); 
	//隐藏当前的 fieldset
	current_fs.hide();
});

$(".previous").click(function(){//单击上一步按钮
	current_fs = $(this).parent().parent();
	previous_fs = $(this).parent().parent().prev();
	
	$("#editli"+editliIndex).removeClass("active");
	$("#editli"+editliIndex).addClass("negative");
	editliIndex--;
	$("#editli"+editliIndex).addClass("active");
	$("#editli"+editliIndex).removeClass("negative");
	//显示上一个 fieldset
	previous_fs.show(); 
	//隐藏当前的 fieldset
	current_fs.hide();
});

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

function skipTo(selectedIndex){//直接选择每个模块，（非上一步下一步）
	current_fs = $("#editfieldset"+editliIndex);
	selected_fs = $("#editfieldset"+selectedIndex);

	$("#editli"+editliIndex).removeClass("active");
	$("#editli"+editliIndex).addClass("negative");
	editliIndex = selectedIndex;
	$("#editli"+selectedIndex).addClass("active");
	$("#editli"+editliIndex).removeClass("negative");
	//显示选中的 fieldset
	selected_fs.show(); 
	//隐藏当前的fieldset
	current_fs.hide();
}
//自动填充每道题的分数、每个模块总分
function populateScore(answer_index,optionScore,answer_begin_index,sectionSize,section_index){
	//填充每道题的分数
	$("#editscoreOf"+answer_index).textbox('setValue',optionScore);
	//填充每个模块总分
	var optionScore = 0;
	for(var i=0;  i<sectionSize; i++){
		var nextScore=$("#editscoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
		if(nextScore!=null &&nextScore!=""){
			optionScore = optionScore +parseInt(nextScore);
		}
	}	
	$("#editsectionScoreOf"+section_index).textbox('setValue',optionScore);		
	var levelFlag=true;//标记整个模块的题都做完了
	for(var i=0;  i<sectionSize; i++){
		var nextScore=$("#editscoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
		if(nextScore!=null  && nextScore!=""){
			levelFlag =levelFlag&& true;
		}else{
			levelFlag =levelFlag&& false;
		}
	}	
	if(levelFlag){//当且仅当整个模块的所有题都做了，才将模块总分写入最后的评估报表单
		$("#editsectionScore"+section_index).textbox('setValue',optionScore);	//填充评估报表中每个模块的总分
	}	
	$("#editformLevel").textbox('setValue',null);	
	}
//填充评估表的等级
function populateFormLevel(form_sectionSize){//模块等级发生改变时候
			
			var editformLevelFlag=true;//标记是否可以开始为整个评估表的评等级
			for(var i=0;  i<form_sectionSize; i++){
				var nextSectionScore=$("#editsectionScore"+i).textbox('getValue');
				if(nextSectionScore!=null  && nextSectionScore!=""){
					editformLevelFlag =editformLevelFlag&& true;
				}else{
					editformLevelFlag =editformLevelFlag&& false;
				}
			}	
			
			if(editformLevelFlag){//当且仅当每个模块的等级都出来了，才能对整个评估表评等级
				var form_Id = $("#evaluatintFormID").val() ;
				if(form_Id == null || form_Id == ""){
					$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.form_level_error"),'warning');
					return false;
				}
				var dataMap ={};
				dataMap.formId = form_Id;
				var sectionLevelList = new Array();
				for(var i=0;  i<form_sectionSize; i++){
					var nextSectionScore=$("#editsectionScore"+i).textbox('getValue');
					var nextSectionId=$("#editsectionIdOf"+i).val();
					if(nextSectionScore!=null  && nextSectionId!=""){
							var map = {}; 
							map["id"] = nextSectionId; 
							map["score"] = nextSectionScore; 
							sectionLevelList.push(map);
					}
				}	
				if(sectionLevelList.length == 0){
					$.messager.alert(message("yly.common.prompt"),message("yly.elderly.evaluating.form_level_error"),'warning');
					return false;
				}
				dataMap.sections=sectionLevelList;
				var dataMapStr = JSON.stringify(dataMap);//{"formId":"12","sections":[{"id":"9","score":"38"},{"id":"10","score":"8"}]}
				$("#sectionsResult").val(JSON.stringify(sectionLevelList));//[{"id":"9","score":"38"},{"id":"10","score":"8"}]				
				console.log($("#sectionsResult").val());
				$.ajax({
					url:"../elderlyEvaluatingRecord/getCustomFormLevel.jhtml",
					type:"post",
					data:{"sectionsScoreJSON":dataMapStr},
					success:function(result,response,status){
						$("#editformLevel").textbox('setValue',result.level);	
					}
				});
			}
	}

