var admission_manager_tool = {
		add:function(){		
			$('#addAdmission').dialog({    
			    title: message("yly.admission.add"),    
			    width: 1200,    
			    height: 700,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addAdmission_form').form('validate');
						var $photoLi = $("#admissionUploader-add ul.filelist li");
						if(validate){
							if($photoLi.length >0){
								$("#admissionUploader-add .uploadBtn").trigger("upload");
							}else{
								$.ajax({
									url:"../admission/add.jhtml",
									type:"post",
									data:$("#addAdmission_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addAdmission_form').form('reset');
										$('#addAdmission').dialog("close");
										$("#admission-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							}
							
							
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#bed_ID').val(null);
						 $('#bed_description').textbox("setValue",null);
						 $('#addAdmission').dialog("close");
					}
			    }],
			    onOpen:function(){
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
			     			warp :"addAdmission_form",
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
									data:$("#addAdmission_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addAdmission_form').form('reset');
										$('#addAdmission').dialog("close");
										$("#admission-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
			     			}
			     	};
			     	
			     	singleUpload(options);
			     	
			    },
			    onClose:function(){
			    	$("#admissionUploader-add .uploadBtn").trigger("clearFileQuene");
			    }
			});  
			 $('#addAdmission_form').show();
		},
		edit:function(){
			var _edit_row = $('#admission-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("yly.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editAdmission').dialog({    
			    title: message("yly.common.edit"),     
			    width: 1200,    
			    height: 700,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../admission/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editAdmission_form').form('validate');
						if(validate){
							$.ajax({
								url:"../admission/update.jhtml",
								type:"post",
								data:$("#editAdmission_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#editAdmission').dialog("close");
									$("#admission-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editAdmission').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$('#editAdmission_form').show();
			    	$("#personnelCategoryEditId").combobox({    
			    		prompt:message("yly.common.please.select"),
					    valueField:'id',    
					    textField:'configValue',
					    cache: true,
					    url:'../systemConfig/findByConfigKey.jhtml',
					    onBeforeLoad : function(param) {
					        param.configKey = 'PERSONNELCATEGORY';
					    },
					    value:$("#personnelCategoryEditId").val()
					});
			     	$("#evaluatingResultEditId").combobox({    
			     		prompt:message("yly.common.please.select"),
					    valueField:'id',    
					    textField:'configValue',
					    cache: true,
					    url:'../systemConfig/findByConfigKey.jhtml',
					    onBeforeLoad : function(param) {
					        param.configKey = 'EVALUATINGRESULT';
					    },
					    value:$("#evaluatingResultEditId").val()
					});
			     	$("#nursingLevelEditId").combobox({    
			     		prompt:message("yly.common.please.select"),
					    valueField:'id',    
					    textField:'configValue',
					    cache: true,
					    url:'../systemConfig/findByConfigKey.jhtml',
					    onBeforeLoad : function(param) {
					        param.configKey = 'NURSELEVEL';
					    },
					    value:$("#nursingLevelEditId").val()
					});
			     	
			     	
			     	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#admissionFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#admissionUploader-edit .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             thumb:{
					            	    width: 150,
					            	    height: 150,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					            	},
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../admission/uploadProfilePhoto.jhtml',
					             fileNumLimit: 100,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
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
			     			warp :"editAdmission_form",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#admissionFilePicker-edit2',
			     				innerHTML: '替换头像'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 //在参数中增加一个老人编号字段 identifier
			     				 data.identifier =$("#editAdmission_form").find("input[name='identifier']").val();
			     				 data.elderlyInfoId=$("#editAdmission_form").find("input[name='id']").val();
			     			}
			     	};
			     	
			     	singleUpload(editOptions);
			     	$("#editAdmission_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
			     		    if (res){    
			     		    	$("#admissionUploader-edit .uploadBtn").trigger("upload");   
			     		    }    
			     		}); 
			     		//alert("保存头像");
			     	})
			    }
			});  
		},
		remove:function(){
			listRemove('admission-table-list','../admission/delete.jhtml');
		}
}


$(function(){
	
	$("#admission-table-list").datagrid({
		title:message("yly.elderlyinfo.record"),
		fitColumns:true,
		toolbar:"#admission_manager_tool",
		url:'../admission/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#admissionDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 1200,    
			    height: 700, 
			    cache: false,   
			    href:'../admission/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#admissionDetail').dialog("close");
					}
			    }]
			});   
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
		    	  	if(value == "IN_PROGRESS_CHECKIN_BILL"){
		    	  		return  "入院办理(已出账单未交费)";
		    	  	}
		    	  	if(value == "IN_PROGRESS_EVALUATING"){
		    	  		return  "通过入院评估";
		    	  	}
		      	}}		      
		   ]
		],
		onLoadSuccess:function(data){
	           $(".tips-span").tooltip({
	        	   position: 'top',
	               onShow: function(){
	                   $(this).tooltip('tip').css({ 
	                       width:'300'
	                   });
	               }
	           });
	        }

	});
	
	
	$("#admission_search_btn").click(function(){
	 var _queryParams = $("#admission_search_form").serializeJSON();
	  $('#admission-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#admission-table-list").datagrid('reload');
		//隐藏域用于标记上次使用过的查询条件 
		$("#identifierHidden_admission").val($("#identifier_admission").val());
		$("#nameHidden_admission").val($("#name_admission").val());
		$("#elderlyStatusHidden_admission").val($("#elderlyStatus_admission").combobox('getValue'));
		$("#beHospitalizedBeginDateHiden_admission").val($("#beHospitalizedBeginDate_admission").val());
		$("#beHospitalizedEndDateHidden_admission").val($("#beHospitalizedEndDate_admission").val());	
	})
	
	$("#generateIdentifier_btn").click(function(){
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
	
	
	$('#add_admission_bed_btn').linkbutton({    
	    iconCls: 'icon-search',
	    onClick:function(){
	    	selectRoom({
	    		type:1,
	    		bedInputId:"add_admission_bed"
	    	})
	    }
	}); 
	 
})

