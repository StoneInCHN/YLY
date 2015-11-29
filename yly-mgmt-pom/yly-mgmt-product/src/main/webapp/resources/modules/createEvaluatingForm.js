$(function(){
			reloadItems();//加载题库
			$('#optionList').datagrid({
				title:'编辑选项',
				iconCls:'icon-edit',
				width:880,
				height:220,
				singleSelect:true,
				idField:'optionId',
				columns:[[
					{field:'optionId',title:'ID',width:40},
					{field:'optionScore',title:'得分',width:60,align:'right',editor:'numberspinner'},
					{field:'optionName',title:'选项名称',width:650,editor:'text'},
					{field:'action',title:'操作',width:100,align:'center',
						formatter:function(value,row,index){
							if (row.editing){
								var s = '<a href="#" onclick="saverow(this)">保存</a>&nbsp;&nbsp;';
								var c = '<a href="#" onclick="cancelrow(this)">取消</a>&nbsp;&nbsp;';
								return s+c;
							} else {
								var e = '<a href="#" onclick="editrow(this)">编辑</a>&nbsp;&nbsp; ';
								var d = '<a href="#" onclick="deleterow(this)">删除</a>&nbsp;&nbsp;';
								return e+d;
							}
						}
					}
				]],
				onBeforeEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					updateActions(index);
				}
			});
});
//加载右侧评估表等级
function loadEvaluatingFormLevel(){
	
	
}
//加载左侧的题库
function reloadItems(){
	$.ajax({
		url: '../elderlyEvaluatingRecord/getAllCustomSections.jhtml',
		type: 'GET',			
		dataType: "json",
		success:function(jsonObj, textStatus){
			if(jsonObj.length>0){
						var itemsHtml = '<div id="itemsDisplay" class="easyui-accordion" style="height:720px;">';
						for(var i=0; i< jsonObj.length; i++){			
							var sectionNameShort=jsonObj[i].sectionName;
							if(sectionNameShort.length>6){
								sectionNameShort=sectionNameShort.substring(0,6)+'...';
							}
							//if(!jsonObj[i].systemSection){//标记自定义模块可编辑
								itemsHtml+='<div title="'+sectionNameShort+'"  iconCls="icon-edit" style="overflow:auto;padding:10px;">';
							//}else{//标记系统定义的模块
								//itemsHtml+='<div title="'+sectionNameShort+'" class="section"  iconCls="icon-ok" style="overflow:auto;padding:10px;">';
							//}
							var jsonObj1= jsonObj[i].evaluatingItems;
							itemsHtml+='<div class="section">';
							itemsHtml+='<input type="hidden"  title="hiddenSectionName" value="'+jsonObj[i].sectionName+'"/>';
							itemsHtml+='<input type="hidden" title="sectionId"  value="'+jsonObj[i].id+'"/>';
							//itemsHtml+='<input type="hidden" title="sectionRule"  value="'+jsonObj[i].evaluatingRule+'"/>';
							itemsHtml+='<input type="hidden" title="indexId"  value="'+i+'"/>';
							for(var j=0; j<jsonObj1.length; j++){	
								itemsHtml+='<p  class="item">'+jsonObj1[j].itemName.substring(0,2);
								itemsHtml+='<input type="hidden" title="itemName"  value="'+jsonObj1[j].itemName+'"/>';
								itemsHtml+='<input type="hidden" title="itemId"  value="'+jsonObj1[j].id+'"/>';
								var jsonObj2=jsonObj1[j].evaluatingItemsOptions;
								for(var k=0;k<jsonObj2.length;k++){
									itemsHtml+='<input type="hidden" title="optionScore"  value="'+jsonObj2[k].optionScore+'"/>';
									itemsHtml+='<input type="hidden"  title="optionName" value="'+jsonObj2[k].evaluatingItemOptions.optionName+'"/>';
								}
								itemsHtml+='</p>';
							}
							itemsHtml+='</div>';	
							//if(!jsonObj[i].systemSection){//自定义模块可以继续在该模块下添加题目
							itemsHtml+='<div id="additemDIV'+i+'"><a href="javascript:;" id="additem" class="btn bule-color" onclick="addItem('+jsonObj[i].id +')"><i class="fa fa-plus-square-o fa-2x"/> 添加题目</a></div>';
							//}
							itemsHtml+='</div>';					
						}
						itemsHtml+='</div>';
						$("#accordionDiv").html(itemsHtml);	
						$.parser.parse($('#accordionDiv'));
						//拖拽的源
						$('.left .section').draggable({
							revert:true,
							proxy:'clone'
						});
						//拖拽的目的地
						$('.right .drop').droppable({
							onDragEnter:function(){
								$(this).addClass('over');
							},
							onDragLeave:function(){
								$(this).removeClass('over');
							},
							onDrop:function(e,source){
								$(this).removeClass('over');	
								var sectionName=$(source).find('input[title=hiddenSectionName]').val();		
								if($(this).html().indexOf(sectionName) ==-1){//评估表不能重复加模块
								var indexId=$(source).find('input[title=indexId]').val();
								$("#additemDIV"+indexId).addClass('hiddenAddItem');
								var appendElement='<div><hr><table style="background:#fff;width:95%;"><tr><td colspan="4" style="font-size:130%"><strong>评估模块：<a href="#" >'+sectionName+'</a></strong></td>'+
											'<td style="width:20px"><a href="javascript:void(0);" onclick="removeSection(this,'+indexId+')"><i class="fa fa-times fa-1x"></i></a><p/><p/></td>';
								var sectionId= $(source).find('input[title=sectionId]').val();
								appendElement+='<input type="hidden" name="sectionId"  value="'+sectionId+'"/>';
//								 var sectionRule= $(source).find('input[title=sectionRule]').val();
//								 var sectionRules = sectionRule.split(";");
//								 for(var i=0; i< sectionRules.length; i++){
//									 var levelScore = sectionRules[i].split(":");
//									 if(parseInt(levelScore[0])  == 0){
//										 appendElement+='<tr><td><strong>0</strong> 能力完好:总分';
//										 var scoreFromTo = levelScore[1].split(",");
//										 appendElement+=scoreFromTo[0] + '~' + scoreFromTo[1] + '分</td><td>';
//									 }else if(parseInt(levelScore[0])  == 1){
//										 appendElement+='<strong>1</strong> 轻度受损:总分';
//										 var scoreFromTo = levelScore[1].split(",");
//										 appendElement+=scoreFromTo[0] + '~' + scoreFromTo[1] + '分</td><td>';
//									 }else if(parseInt(levelScore[0])  ==2){
//										 appendElement+='<strong>2</strong> 中度受损:总分';
//										 var scoreFromTo = levelScore[1].split(",");
//										 appendElement+=scoreFromTo[0] + '~' + scoreFromTo[1] + '分</td><td>';
//									 }else if(parseInt(levelScore[0])  ==3){
//										 appendElement+='<strong>3</strong> 中度受损:总分';
//										 var scoreFromTo = levelScore[1].split(",");
//										 appendElement+=scoreFromTo[0] + '~' + scoreFromTo[1] + '分</td><td>&nbsp;</td>';
//									 }
//								 }
								 appendElement+='</table><p/>';
								 $.each( $(source).find('p[class=item]'), function(i,item){      
										var itemName = $(item).find('input[title=itemName]').val();
										var itemId= $(item).find('input[title=itemId]').val();
										appendElement+='<table id="test" class="table table-bordered" fitColumns="true" style="width:95%;height:auto;">'+
															'<tr><td colspan="2">'+itemName+'</td></tr>';
										 $.each( $(item).find('input'), function(i,item){       
										 		if(i>1){ 
												    if(i%2==0){
												    	appendElement+='<tr><td style="width:60px">'+$(item).val() +'分</td>';
												    }else{
												    	appendElement+='<td>'+$(item).val()+'</td></tr>';
												    }
											    }
					   					 });		
										appendElement+='</table>';
			   					 });	

								 appendElement+='</div>';
								$(this).append(appendElement);
							}else{
								$.messager.alert(message("yly.common.prompt"), message(sectionName+" 模块已经添加,不能重复添加!"),'warning');
							}
							}
						});
			}	
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
              //alert("XMLHttpRequest.status:" + XMLHttpRequest.status);
              //alert("XMLHttpRequest.readyState:" + XMLHttpRequest.readyState);
              //alert("textStatus:" + textStatus);
        },
		});
}
function removeSection(obj,indexId){
	$(obj).parent().parent().parent().parent().parent().remove();
	$("#additemDIV"+indexId).removeClass('hiddenAddItem');
}

//添加模块
function addSection(){
	$('#addEvaluatingSection').dialog({    
	    title: "自定义模块",    
	    width: 600,    
	    height: 300,
	    iconCls:'icon-mini-add',
	    modal:true,
	    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
			    	handler:function(){
						var validate = $('#addEvaluatingSection_form').form('validate');
						if(validate){								
								$.ajax({
									url:"../elderlyEvaluatingRecord/addSection.jhtml",
									type:"post",
									data:$("#addEvaluatingSection_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addEvaluatingSection').dialog("close");//关闭当前对话框
										reloadItems();//重新加载左边的题库
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
						}else{
							$.messager.alert(message("yly.common.prompt"), message("还有未填字段!"),'warning');
						}
					}
				},
			{text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#addEvaluatingSection').dialog("close");
			}
	    }],
	    onOpen:function(){
	    	$('#addEvaluatingSection_form').show();	    		    	
	    },
	    onClose:function(){
	    	 
	    }
	});
}
//添加题目
function addItem(evaluatingSectionId){
	$('#addEvaluatingItem').dialog({    
	    title: "自定义题目",    
	    width: 900,    
	    height: 400,
	    iconCls:'icon-mini-add',
	    modal:true,
	    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
			    	handler:function(){
						var validate = $('#addEvaluatingItem_form').form('validate');
						if(validate){	
							
							 var itemSectionIDs = $("#itemSectionIDs").val();
							 var itemID = $("#itemNameID").val();
							 var itemName = $("#itemName").val();
								$.ajax({
									url:"../elderlyEvaluatingRecord/sectionContainItem.jhtml?sectionId="+evaluatingSectionId+"&itemId="+itemID,
									type:"get",
									success:function(result,response,status){
										if(result.sectionContainItem+""=="true"){											
											 $.messager.alert(message("yly.common.prompt"), message("该模块已经包含此题目，请另外添加其他题目!"),'warning');
										}else{
											$('#hiddenDiv').append('<input type="hidden"  name="evaluatingSectionId" value="'+evaluatingSectionId+'"/>');
											var rows =  $('#optionList').datagrid('getRows');
											for(var i=0; i<rows.length; i++){
												var optionScoreHtml='<input type="hidden"  name="evaluatingItemsOptions['+i+'].optionScore" value="'+rows[i]['optionScore']+'"/>';
												var optionNameHtml='<input type="hidden"  name="evaluatingItemsOptions['+i+'].evaluatingItemOptions.optionName" value="'+rows[i]['optionName']+'"/>';
												$('#hiddenDiv').append(optionScoreHtml);
												$('#hiddenDiv').append(optionNameHtml);
											}
											$.ajax({
												url:"../elderlyEvaluatingRecord/addItem.jhtml",
												type:"post",
												data:$("#addEvaluatingItem_form").serialize(),
												beforeSend:function(){
													$.messager.progress({
														text:message("yly.common.saving")
													});
												},
												success:function(result,response,status){
													$('#itemNameID').val(null);
													$.messager.progress('close');
													showSuccessMsg(result.content);
													$('#addEvaluatingItem').dialog("close");//关闭当前对话框
													empty();//清空optionList
													reloadItems();//重新加载左边的题库
												},
												error:function (XMLHttpRequest, textStatus, errorThrown) {
													$.messager.progress('close');
													alertErrorMsg();
												}
											});
										}
									}
								});		
						}else{
							$.messager.alert(message("yly.common.prompt"), message("还有未填字段!"),'warning');
						}
					}
				},
			{text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#addEvaluatingItem').dialog("close");
			}
	    }],
	    onOpen:function(){
	    	$('#addEvaluatingItem_form').show();	    		    	
	    },
	    onClose:function(){
	    	 
	    }
	});
}
$.extend($.fn.datagrid.defaults.editors, {
	numberspinner: {
		init: function(container, options){
			var input = $('<input type="text">').appendTo(container);
			return input.numberspinner(options);
		},
		destroy: function(target){
			$(target).numberspinner('destroy');
		},
		getValue: function(target){
			return $(target).numberspinner('getValue');
		},
		setValue: function(target, value){
			$(target).numberspinner('setValue',value);
		},
		resize: function(target, width){
			$(target).numberspinner('resize',width);
		}
	}
});
var optionId=0;//选项的ID

function updateActions(index){
	$('#optionList').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
function getRowIndex(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
	$('#optionList').datagrid('beginEdit', getRowIndex(target));
}
function deleterow(target){
	$.messager.confirm('确认','确定删除此选项吗?',function(r){
		if (r){
			$('#optionList').datagrid('deleteRow', getRowIndex(target));
		}
	});
}
//清空自定义题目对话框里面的数据
function empty(){
	optionId=0;
	$("#itemName").textbox('setValue',null);
	$('#optionList').datagrid('loadData',{total:0,rows:[]});
}
function saverow(target){
	$('#optionList').datagrid('endEdit', getRowIndex(target));
}
function cancelrow(target){
	$('#optionList').datagrid('cancelEdit', getRowIndex(target));
}
//插入一行新选项
function insert(){
	var row = $('#optionList').datagrid('getSelected');
	if (row){
		var index = $('#optionList').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#optionList').datagrid('insertRow', {
		index: index,
		row:{
			optionId:++optionId
		}
	});
	//$('#optionList').datagrid('selectRow',index);
	$('#optionList').datagrid('beginEdit',index);
}
/**
 * 题库查询功能
 */
function searchAllItems(id){
	$('#searchAllItems').dialog({
	    title: "选择已有题目",    
	    width: 600,
	    height: 450,
	    modal:true,
	    cache: false,   
	    href:'../elderlyEvaluatingRecord/searchAllItems.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchAllItems').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	$("#searchAllItems-table-list").datagrid({
	    		title:'题库查询',
		    	 fitColumns:true,
		    	 url:'../elderlyEvaluatingRecord/getAllItems.jhtml',  
		    	 pagination:true,
		    	 loadMsg:message("yly.common.loading"),
		    	 striped:true,
		    	 onDblClickRow : function (rowIndex, rowData){
//		    		 var sections = rowData.evaluatingSections;
//		    		 var sectionIdStrList = "";
//		    		 for(var i=0; i<sections.length; i++){
//		    			 sectionIdStrList+=sections[i].id;
//		    			 if((i+1)<sections.length){
//		    				 sectionIdStrList+=',';
//		    			 }
//		    		 }
//		    		 $("#itemSectionIDs").val(sectionIdStrList);
		    		 $("#"+id+"ID").val(rowData.id);
		    		 $("#"+id).textbox('setValue',rowData.itemName);
		    		 var options = rowData.evaluatingItemsOptions;
		    		 var loadData='{"total":'+options.length+',"rows":[';
		    		 for(var i=0; i<options.length; i++){
		    			 loadData+='{"optionId":'+(i+1)+',"optionScore":"'+options[i].optionScore+'","optionName":"'+options[i].evaluatingItemOptions.optionName+'"}';
		    			 if((i+1)<options.length){
		    				 loadData+=',';
		    			 }
		    		 }
		    		 loadData+=']}';
		    		 var jsonData = $.parseJSON(loadData);
		    		 $('#optionList').datagrid('loadData',jsonData);

		    		 $('#searchAllItems').dialog("close");
		    	 },
		    	 columns:[
		  	    	    [
		  	    	        {field:'ck',checkbox:true,width:8,align:'center'},
		  	    	        {title:'题目名称',field:"itemName",width:10,align:'left',formatter:function(value,row,index){
			 			    	 return formatLongString(value,10);
		  	    	        }}
		  		      ]]
	    	});
	    }
	});
	
}