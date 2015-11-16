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
	liIndex++;
	$("#li"+liIndex).addClass("active");
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
	liIndex--;
	$("#li"+liIndex).addClass("active");
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
	liIndex = selectedIndex;
	$("#li"+selectedIndex).addClass("active");
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
function populateLevel(answer_begin_index,sectionSize,section_index,section_name){
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
			var answerScoreList = new Array();
			for(var i=0;  i<sectionSize; i++){
				var nextItemName=$("#itemNameOf"+(parseInt(answer_begin_index)+i)).val();
				var nextScore=$("#scoreOf"+(parseInt(answer_begin_index)+i)).textbox('getValue');
				if(nextScore!=null  && nextScore!=""){
					answerScoreList.push(nextItemName+"::::"+nextScore);
				}
			}			
			$.ajax({
				url:"../elderlyEvaluatingRecord/getSectionLevel.jhtml?sectionName="+section_name+"&answerScores="+answerScoreList.join(";;;;"),
				type:"get",
				success:function(result,response,status){
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
					var nextSectionLevel=$("#sectionLevel"+i).textbox('getValue');
					var nextSectionName=$("#sectionNameOf"+i).val();
					if(nextSectionLevel!=null  && nextSectionLevel!=""){
						sectionLevelList.push(nextSectionName+"::::"+nextSectionLevel);
					}
				}	
				
				$.ajax({
					url:"../elderlyEvaluatingRecord/getFormLevel.jhtml?sectionLevels="+sectionLevelList.join(";;;;"),
					type:"get",
					success:function(result,response,status){
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
				});
			}
	}

