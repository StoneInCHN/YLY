var elderlyEvaluating_manager_tool = {
			chooseFrom:function(){

				$('#chooseEvaluating').dialog({    
				    title: "选择评估表",    
				    width: 500,    
				    height: 350,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#chooseEvaluating').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#chooseEvaluating_form').show();
				    },
				    onClose:function(){
				    	 $('#chooseEvaluating_form').form('reset');
				    }
				});
			
			},
			add:function(){
				$('#addEvaluating').dialog({    
				    title: "添加入院评估",    
				    width: 1250,    
				    height: 850,
				    iconCls:'icon-mini-add',
				    modal:true,
				    href:'../elderlyEvaluatingRecord/addEvaluating.jhtml',
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
			    cache: false,
			    modal: true,
			    href:'../elderlyEvaluatingRecord/view.jhtml?id='+rowData.id,
			    buttons:[{
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
		      {title:"评估人操作人",field:"operator",width:30,align:'center',formatter:function(value,row,index){
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
		    		  		return '<span style="color:green">'+formatLongString(value,8)+'</span>';//绿色  能力完好
						}
						if(value.indexOf("1")!=-1){
							return '<span style="color:orange">'+formatLongString(value,8)+'</span>';//橘黄色  轻度受损
						}
						if(value.indexOf("2")!=-1){
							return '<span style="color:#cc6600">'+formatLongString(value,8)+'</span>';//深黄色  中度受损
						}
						if(value.indexOf("3")!=-1){
							return '<span style="color:red">'+formatLongString(value,8)+'</span>';//红色  重度受损
						}
			    	
		      }},
		      {title:"评估表编号",field:"evaluatingFormName",width:30,align:'center',formatter:function(value,row,index){
		    	  var fullFormName = row.evaluatingForm.formName;//老年人能力评估(MZ/T 039-2013)
		    	  var codeOfFormName = fullFormName.substring(fullFormName.indexOf("(")+1,fullFormName.length-1);//MZ/T 039-2013
		    	  return codeOfFormName;
		      }},
		      {title:"评估基准时间",field:"createDate",width:30,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		]
	});
	$("#elderlyEvaluating_search_btn").click(function(){
		  var _queryParams = $("#elderlyEvaluating_search_form").serializeJSON();
		  $('#elderlyEvaluating-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#elderlyEvaluating-table-list").datagrid('reload');
		})
})
function formatLongString(str,len){
	if(str != null && str!=""&& len > 0){
		if(str.length > len){
			return '<span title="'+str+'">'+str.substring(0,len)+"..."+'<span>'
		}else{
			return str;
		}	
	}
	return "";
}
function createForm(){
	$('#createEvaluatingForm').dialog({    
	    title: "自定义评估表",    
	    width: 800,    
	    height: 900,
	    iconCls:'icon-mini-add',
	    modal:true,
	    href:'../elderlyEvaluatingRecord/createEvaluatingFrom.jhtml',  
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#createEvaluatingForm').dialog("close");
			}
	    }],
	    onOpen:function(){
	    	//$('#chooseEvaluating').dialog("close");
	    },
	    onClose:function(){
	    	 
	    }
	});
}


