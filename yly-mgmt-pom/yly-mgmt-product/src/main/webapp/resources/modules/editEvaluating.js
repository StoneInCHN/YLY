var current_fs, next_fs, previous_fs; //当前的fieldsets，下一个fieldsets，上一个fieldsets


var editliIndex=0;

$(".next").click(function(){//单击 下一步按钮	
	current_fs = $(this).parent().parent();
	next_fs = $(this).parent().parent().next();
	
	$("#editli"+editliIndex).removeClass("active");
	editliIndex++;
	$("#editli"+editliIndex).addClass("active");
	//显示下一个 fieldset
	next_fs.show(); 
	//隐藏当前的 fieldset
	current_fs.hide();
});

$(".previous").click(function(){//单击上一步按钮
	current_fs = $(this).parent().parent();
	previous_fs = $(this).parent().parent().prev();
	
	$("#editli"+editliIndex).removeClass("active");
	editliIndex--;
	$("#editli"+editliIndex).addClass("active");
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
		$.messager.alert('提示','信息提供有误或者不够完善！请核查...','warning');
	}
	return false;
})

function skipTo(selectedIndex){//直接选择每个模块，（非上一步下一步）
	current_fs = $("#editfieldset"+editliIndex);
	selected_fs = $("#editfieldset"+selectedIndex);

	$("#editli"+editliIndex).removeClass("active");
	editliIndex = selectedIndex;
	$("#editli"+selectedIndex).addClass("active");
	//显示选中的 fieldset
	selected_fs.show(); 
	//隐藏当前的fieldset
	current_fs.hide();
}
//自动填充每道题的分数、每个模块总分以及每个模块的等级
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
	
	}
//填充每个模块的等级
function populateLevel(answer_begin_index,sectionSize,section_index,section_name){
		var levelFlag=true;//标记是否可以开始为整个模块评等级
		for(var i=0;  i<sectionSize; i++){
			var nextScore=$("#editscoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
			if(nextScore!=null  && nextScore!=""){
				levelFlag =levelFlag&& true;
			}else{
				levelFlag =levelFlag&& false;
			}
		}	
		if(levelFlag){//当且仅当整个模块的所有题都做了，才能评等级
			var answerScoreList = new Array();
			for(var i=0;  i<sectionSize; i++){
				var nextItemName=$("#edititemNameOf"+(parseInt(answer_begin_index)+i)).val();
				var nextScore=$("#editscoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
				if(nextScore!=null  && nextScore!=""){
					answerScoreList.push(nextItemName+"::::"+nextScore);
				}
			}			
			$.ajax({
				url:"../elderlyEvaluatingRecord/getSectionLevel.jhtml?sectionName="+section_name+"&answerScores="+answerScoreList.join(";;;;"),
				type:"get",
				success:function(result,response,status){
					$("#editsectionLevelOf"+section_index).textbox('setValue',result.level);	
					$("#editsectionLevel"+section_index).textbox('setValue',result.level);	
					$("#editformLevel").textbox('setValue',null);		//因为每个模块的等级发生改变，需要清空评估表之前的等级
 				  if(parseInt(result.level)==0){
						$("#editsectionLevelOf"+section_index).parent().css("color","green");//绿色  能力完好
						//$("#editsectionLevel"+section_index).parent().css("color","green");//绿色  能力完好
					}
					if(parseInt(result.level)==1){
						$("#editsectionLevelOf"+section_index).parent().css("color","orange");//橘黄色  轻度受损
						//$("#editsectionLevel"+section_index).parent().css("color","orange");//橘黄色  轻度受损
					}
					if(parseInt(result.level)==2){
						$("#editsectionLevelOf"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
						//$("#editsectionLevel"+section_index).parent().css("color","#cc6600");//深黄色  中度受损
					}
					if(parseInt(result.level)==3){
						$("#editsectionLevelOf"+section_index).parent().css("color","red");//红色  重度受损
						//$("#editsectionLevel"+section_index).parent().css("color","red");//红色  重度受损
					}
				}
			});			
	}
}
//填充评估表的等级
function populateFormLevel(form_sectionSize){//模块等级发生改变时候

			var editformLevelFlag=true;//标记是否可以开始为整个评估表的评等级
			for(var i=0;  i<form_sectionSize; i++){
				var nextSectionScore=$("#editsectionLevel"+i).textbox('getValue');
				if(nextSectionScore!=null  && nextSectionScore!=""){
					editformLevelFlag =editformLevelFlag&& true;
				}else{
					editformLevelFlag =editformLevelFlag&& false;
				}
			}	
			if(editformLevelFlag){//当且仅当每个模块的等级都出来了，才能对整个评估表评等级
				
				var editsectionLevelList = new Array();
				for(var i=0;  i<form_sectionSize; i++){
					var nextSectionLevel=$("#editsectionLevel"+i).textbox('getValue');
					var nextSectionName=$("#editsectionNameOf"+i).val();
					if(nextSectionLevel!=null  && nextSectionLevel!=""){
						editsectionLevelList.push(nextSectionName+"::::"+nextSectionLevel);
					}
				}	
				alert(editsectionLevelList);
				$.ajax({
					url:"../elderlyEvaluatingRecord/getFormLevel.jhtml?sectionLevels="+editsectionLevelList.join(";;;;"),
					type:"get",
					success:function(result,response,status){
						$("#editformLevel").textbox('setValue',result.level);	
	 				  if(parseInt(result.level)==0){
	 					 $("#editformLevel").parent().css("color","green");//绿色  能力完好
						}
						if(parseInt(result.level)==1){
							$("#editformLevel").parent().css("color","orange");//橘黄色  轻度受损
						}
						if(parseInt(result.level)==2){
							$("#editformLevel").parent().css("color","#cc6600");//深黄色  中度受损
						}
						if(parseInt(result.level)==3){
							$("#editformLevel").parent().css("color","red");//红色  重度受损
						}
					}
				});
			}
	}

