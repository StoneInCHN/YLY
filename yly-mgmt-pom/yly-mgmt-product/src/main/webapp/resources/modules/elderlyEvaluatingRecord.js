var elderlyEvaluating_manager_tool = {
			chooseFrom:function(){

				$('#chooseEvaluating').dialog({    
				    title: "选择评估表",    
				    width: 500,    
				    height: 450,
				    iconCls:'icon-mini-add',
				    modal:true,
				    href:'../elderlyEvaluatingRecord/listForm.jhtml',
				    buttons:[{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#chooseEvaluating').dialog("close");
						}
				    }]
				});
			
			},
			addCustom:function(formId){
				$('#addEvaluating').dialog({    
				    title: "添加自定义入院评估",    
				    width: 1250,    
				    height: 850,
				    left:($(window).width()-1250)/2,
				    top:50,
				    resizable:true,
				    left:50,
				    top:50,
				    resizable:true,
				    iconCls:'icon-mini-add',
				    modal:true,
				    href:'../elderlyEvaluatingRecord/addCustomEvaluating.jhtml?formId='+formId,
				    onOpen:function(){
				    	$('#chooseEvaluating').dialog("close");
				    	$('#addAdmission_form').show();
				    	$("#personnelCategoryId").combobox({    
				    		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'PERSONNELCATEGORY';
						    },		    		
						});
				     	$("#evaluatingResultId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'EVALUATINGRESULT';
						    }
						});
				     	$("#nursingLevelId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    }
						});
				     	//头像上传
				     	var options ={
				     			createOption:{
				     				pick: {
						                 id: '#admissionFilePicker-add',
						                 label: '',
						                 multiple :false
						             },
						             dnd: '#admissionUploader-add .queueList',
						             accept: {
						                 title: 'Images',
						                 extensions: 'gif,jpg,jpeg,bmp,png',
						                 mimeTypes: 'image/*'
						             },
						             //缩略图
						             thumb:{
						            	    width: 110,
						            	    height: 110,
						            	    quality: 90,
						            	    allowMagnify: false,
						            	    crop: false,
						            	    type: 'image/jpeg'
						              },
						             // swf文件路径
						             swf: BASE_URL + '/js/Uploader.swf',
						             disableGlobalDnd: true,
						             server: '../file/uploadProfilePhoto.jhtml',
						             fileNumLimit: 1,
						             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
						             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
						             //图片裁剪
						             compress:{
						            	 width: 110,
						            	 height: 110,
						            	 // 图片质量，只有type为`image/jpeg`的时候才有效。
						            	 quality: 90,
						            	 // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
						            	 allowMagnify: false,
						            	 // 是否允许裁剪。
						            	 crop: false,
						            	 // 是否保留头部meta信息。
						            	 preserveHeaders: true,
						            	 // 如果发现压缩后文件大小比原来还大，则使用原来图片
						            	 // 此属性可能会影响图片自动纠正功能
						            	 noCompressIfLarger: false,
						            	 // 单位字节，如果图片大小小于此值，不会采用压缩。
						            	 compressSize: 0
						             }
				     			},
				     			//包裹上传组件的div id
				     			warp :"addEvaluating_form",
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier
				     				 data.identifier =$("#identifier_input").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				//将返回的图片路径放到隐藏的input中，用于表单保存
				     				$("#addAdmission_form_file_input").val(response.content);
				     				$.ajax({
										url:"../admission/add.jhtml",
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
											$('#addEvaluating_form').form('reset');
											$('#addEvaluating').dialog("close");
											$("#elderlyEvaluating-table-list").datagrid('reload');
											
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
				     			}
				     	};
				     	
				     	//singleUpload(options);
				     	
				    },
				    onClose:function(){
				    	$("#admissionUploader-add .uploadBtn").trigger("clearFileQuene");
				    	 $('#addEvaluating_form').form('reset');
				    }
				});
			
			},
			add:function(formId){
				$('#addEvaluating').dialog({    
				    title: "添加入院评估",    
				    width: 1250,    
				    height: 850,
				    left:($(window).width()-1250)/2,
				    top:50,
				    resizable:true,
				    iconCls:'icon-mini-add',
				    modal:true,
				    left:50,
				    top:50,
				    resizable:true,
				    href:'../elderlyEvaluatingRecord/addEvaluating.jhtml?formId='+formId,
				    onOpen:function(){
				    	$('#chooseEvaluating').dialog("close");
				    	$('#addAdmission_form').show();
				    	$("#personnelCategoryId").combobox({    
				    		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'PERSONNELCATEGORY';
						    },		    		
						});
				     	$("#evaluatingResultId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'EVALUATINGRESULT';
						    }
						});
				     	$("#nursingLevelId").combobox({    
				     		prompt:message("yly.common.please.select"),
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'NURSELEVEL';
						    }
						});
				     	//头像上传
				     	var options ={
				     			createOption:{
				     				pick: {
						                 id: '#admissionFilePicker-add',
						                 label: '',
						                 multiple :false
						             },
						             dnd: '#admissionUploader-add .queueList',
						             accept: {
						                 title: 'Images',
						                 extensions: 'gif,jpg,jpeg,bmp,png',
						                 mimeTypes: 'image/*'
						             },
						             //缩略图
						             thumb:{
						            	    width: 110,
						            	    height: 110,
						            	    quality: 90,
						            	    allowMagnify: false,
						            	    crop: false,
						            	    type: 'image/jpeg'
						              },
						             // swf文件路径
						             swf: BASE_URL + '/js/Uploader.swf',
						             disableGlobalDnd: true,
						             server: '../file/uploadProfilePhoto.jhtml',
						             fileNumLimit: 1,
						             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
						             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
						             //图片裁剪
						             compress:{
						            	 width: 110,
						            	 height: 110,
						            	 // 图片质量，只有type为`image/jpeg`的时候才有效。
						            	 quality: 90,
						            	 // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
						            	 allowMagnify: false,
						            	 // 是否允许裁剪。
						            	 crop: false,
						            	 // 是否保留头部meta信息。
						            	 preserveHeaders: true,
						            	 // 如果发现压缩后文件大小比原来还大，则使用原来图片
						            	 // 此属性可能会影响图片自动纠正功能
						            	 noCompressIfLarger: false,
						            	 // 单位字节，如果图片大小小于此值，不会采用压缩。
						            	 compressSize: 0
						             }
				     			},
				     			//包裹上传组件的div id
				     			warp :"addEvaluating_form",
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier
				     				 data.identifier =$("#identifier_input").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				//将返回的图片路径放到隐藏的input中，用于表单保存
				     				$("#addAdmission_form_file_input").val(response.content);
				     				$.ajax({
										url:"../admission/add.jhtml",
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
											$('#addEvaluating_form').form('reset');
											$('#addEvaluating').dialog("close");
											$("#elderlyEvaluating-table-list").datagrid('reload');
											
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
				     			}
				     	};
				     	
				     	//singleUpload(options);
				     	
				    },
				    onClose:function(){
				    	$("#admissionUploader-add .uploadBtn").trigger("clearFileQuene");
				    	 $('#addEvaluating_form').form('reset');
				    }
				});
			},
			remove:function(){
					listRemove('elderlyEvaluating-table-list','../elderlyEvaluatingRecord/delete.jhtml');
			},
			edit:function(){

				var _edit_row = $('#elderlyEvaluating-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#editEvaluating').dialog({    
				    title: "编辑入院评估记录",     
				    width: 1250,    
				    height: 850,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../elderlyEvaluatingRecord/edit.jhtml?id='+_edit_row.id
				});  
			},
			exportData:function(){
				$.ajax({
					url:"../elderlyEvaluatingRecord/count.jhtml",
					type:"post",
					data:$("#elderlyEvaluating_search_form").serialize(),
					success:function(result,response,status){
						if(result.count != null){
							var text = "";
							if(result.count == 0){
								text = "当前条件无可导出的数据。";
								$.messager.alert(message("yly.common.notice"), text,'warning');
							}else if(result.count <= 500){
								text = "确定导出 "+result.count+" 条记录？";
								$.messager.confirm(message("yly.common.confirm"), text, function(r) {
									if(r){
										$("#elderlyEvaluating_search_form").attr("action","../elderlyEvaluatingRecord/exportData.jhtml");
										$("#elderlyEvaluating_search_form").attr("target","_blank");
										$("#elderlyEvaluating_search_form").submit();
									}

								});
							}else{
								text = "导出数据超过500条数据，建议搜索查询条件以缩小查询范围，再导出。";
								$.messager.confirm(message("yly.common.notice"), text, function(r) {
									if (!r) {
										text = "导出共有"+ result.count +"条数据，导出超过500条数据可能需要您耐心等待，仍需操作请确定继续。";
										$.messager.confirm(message("yly.common.confirm"), text, function(yes) {
											if(yes){
												$("#elderlyEvaluating_search_form").attr("action","../elderlyEvaluatingRecord/exportData.jhtml");
												$("#elderlyEvaluating_search_form").attr("target","_blank");
												$("#elderlyEvaluating_search_form").submit();
											}
										});
									}
								})
							}
						}
						$("#elderlyEvaluating-table-list").datagrid('reload');
					},
					error:function (XMLHttpRequest, textStatus, errorThrown) {
						alert("error");
						$.messager.progress('close');
						alertErrorMsg();
					}
				});
			}	
}
$(function(){
	$("#elderlyEvaluating-table-list").datagrid({
		title:"入院评估记录列表",
		fitColumns:true,
		toolbar:"#elderlyEvaluating_manager_tool",
		url:'../elderlyEvaluatingRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#listEvaluating').dialog({    
			    title: message("yly.common.detail"),    
			    width: 800,    
			    height: 800, 
			    left:($(window).width()-800)/2,
			    top:50,
			    resizable:true,
			    cache: false,
			    modal: true,
			    href:'../elderlyEvaluatingRecord/view.jhtml?id='+rowData.id,
			    buttons:[{
					text:"打印",
					iconCls:'icon-print',
					handler:function(){
					    var newWindow=window.open("评估结果详情","_blank");
					    var docStr = $('#listEvaluating').prop('outerHTML');
					   console.info(docStr);
					    newWindow.document.write(docStr);
					    newWindow.document.close();
					    newWindow.print();
					    newWindow.close();
						// $('#listEvaluating').dialog("close");
					}
			    },{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#listEvaluating').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"被评估老人",field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      {title:"评估操作人",field:"operator",width:30,align:'center',formatter:function(value,row,index){
		    	 return formatLongString(value,8);
		      }},
		      {title:"评估原因",field:"evaluatingReason",width:30,align:'center',formatter:function(value,row,index){
					if(value=="CHECKIN_EVALUATING"){
						return "接受服务前初评";
					}
					if(value=="ROUTINE_EVALUATING"){
						return "接受服务后的常规评估";
					}
					if(value=="IMMEDIATE_EVALUATING"){
						return "状况发生变化后的即时评估";
					}
					if(value=="QUESTION_EVALUATING"){
						return "因评估结果有疑问进行的复评";
					}
			  }},
		      {title:"评估结果",field:"evaluatingResult",width:30,align:'center',formatter:function(value,row,index){
		    	  		if(value.indexOf("0")!=-1){
		    		  		return '<span style="color:green">'+value.replace('0','').trim()+'</span>';//绿色  能力完好
						}
						if(value.indexOf("1")!=-1){
							return '<span style="color:orange">'+value.replace('1','').trim()+'</span>';//橘黄色  轻度受损
						}
						if(value.indexOf("2")!=-1){
							return '<span style="color:#cc6600">'+value.replace('2','').trim()+'</span>';//深黄色  中度受损
						}
						if(value.indexOf("3")!=-1){
							return '<span style="color:red">'+value.replace('3','').trim()+'</span>';//红色  重度受损
						}
			    	  return  formatLongString(value,8);
		      }},
		      {title:"评估表名及编号",field:"evaluatingFormName",width:30,align:'center',formatter:function(value,row,index){
		    	  var fullFormName = row.evaluatingForm.formName;//老年人能力评估(MZ/T 039-2013)
		    	  var codeOfFormName = fullFormName.substring(fullFormName.indexOf("(")+1,fullFormName.length-1);//MZ/T 039-2013
		    	  return codeOfFormName;
		      }},
		      {title:"评估基准时间",field:"evaluatingDate",width:30,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		]
	});
	$("#elderlyEvaluating_search_btn").click(function(){
		  var _queryParams = $("#elderlyEvaluating_search_form").serializeJSON();
		  $('#elderlyEvaluating-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#elderlyEvaluating-table-list").datagrid('reload');
		  //隐藏域用于标记上次使用过的查询条件 
		  $("#elderlyNameHidden").val($("#elderlyName").val());
		  $("#beginDateHidden").val($("#beginDate").val());		
		  $("#endDateHidden").val($("#endDate").val());	
		})
})

function createForm(){
	$('#createEvaluatingForm').dialog({    
	    title: "自定义评估表",    
	    width: 800,    
	    height: 900,
	    left:($(window).width()-800)/2,
	    top:50,
	    resizable:true,
	    iconCls:'icon-mini-add',
	    modal:true,
	    href:'../elderlyEvaluatingRecord/createEvaluatingFrom.jhtml',  
	    buttons:[{
	    	text:message("yly.common.save"),
	    	iconCls:'icon-save',
	    	handler:function(){
				var validate = $('#createEvaluating_form').form('validate');
				if(validate){
			    	var _ids=[];
					var sectionIds = $('.right .drop').find('input[name=sectionId]');
					sectionIds.each(function(index, element) {
							_ids.push(parseInt($(this).val()));
					});	
					if(_ids.length>0){
						var formLevelSize=$("#formLevelSize").val();
						if(formLevelSize>0){
							for(var i=0;i<formLevelSize;i++){
								var formLevelName = $("#formLevelName").val();
								var formScoreFrom = parseInt($("#formScore"+i+"From").val());
								var formScoreTo = parseInt($("#formScore"+i+"To").val());
								if(formScoreFrom>formScoreTo){
									$.messager.alert(message("yly.common.prompt"), message("起始分数应该小于等于终止分数!"),'warning');
									return;
								}
								if(i<formLevelSize-1){
									if(parseInt($("#formScore"+i+"To").val())>=parseInt($("#formScore"+(i+1)+"From").val())){
										$.messager.alert(message("yly.common.prompt"), message("相邻等级的终止分数和起始分数输入有误,否者没法根据具体分数明确的划分等级分数区间!"),'warning');
										return;
									}
								}
							}
							
							var evaluatingRuleArray=new Array();
							for(var i=0;i<formLevelSize;i++){
								var formLevelName = $("#formLevel"+i+"Name").val();
								var formScoreFrom = parseInt($("#formScore"+i+"From").val());
								var formScoreTo = parseInt($("#formScore"+i+"To").val());
								var map = {};
								map["LevelName"]=formLevelName;
								map["ScoreScope"]=formScoreFrom+"~"+formScoreTo
								evaluatingRuleArray.push(map);
							}
							var _evaluatingRule = JSON.stringify(evaluatingRuleArray);
							if(_evaluatingRule != ""){
								$.ajax({
									url:"../elderlyEvaluatingRecord/createForm.jhtml",
									type:"post",
									traditional : true,
									data: {
										"ids" : _ids,
										"formName":$("#formName").val(),
										"evaluatingRule":_evaluatingRule
									},
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#createEvaluatingForm').dialog("close");//关闭当前对话框
										$.messager.alert('提示','自定义评估表: '+$("#formName").val()+' 创建成功!','info');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							}

						}else{
							$.messager.alert(message("yly.common.prompt"), message("请先在系统配置的数据字典中配置评估等级!"),'warning');
						}
					}else{
						$.messager.alert(message("yly.common.prompt"), message("请添加模块!"),'warning');
					}
				}else{
					if($("#formName").val()==null || $("#formName").val().trim()==""){	
						$.messager.alert(message("yly.common.prompt"), message("请输入表名称!"),'warning');
					}else{
						$.messager.alert(message("yly.common.prompt"), message("请输入评估等级评分规则!"),'warning');
					}
				}
				

			}
		},{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#createEvaluatingForm').dialog("close");
			}
	    }],
	    onOpen:function(){
	    	$('#chooseEvaluating').dialog("close");
	    },
	    onClose:function(){
	    	 
	    }
	});
	
}


