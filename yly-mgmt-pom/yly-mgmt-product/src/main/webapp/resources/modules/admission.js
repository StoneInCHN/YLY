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
						if(validate){
							$("#admissionUploader-add .uploadBtn").trigger("upload");
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
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
					             server: '../file/uploadProfilePhoto.jhtml',
					             fileNumLimit: 1,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024    //单个文件上传大小  10 M
			     			},
			     			identifierId:"identifier_input",
			     			warp :"addAdmission_form",
			     			inputId:"addAdmission_form_file_input",
			     			successFun:function(){
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
		      {title:message("yly.elderlyInfo.birthday"),field:"birthday",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }},
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
		      {title:message("yly.elderlyInfo.elderlyPhoneNumber"),field:"elderlyPhoneNumber",width:15,align:'center',sortable:true},
		      {title:message("yly.common.idcard"),field:"idcard",width:40,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.residentialAddress"),field:"residentialAddress",width:40,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(value && value.length >15){
						var abValue =  value.substring(0,10) +"...";
						var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
						return content;
					}else{
						return value
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
	  var _queryParams = {
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#admission-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#admission-table-list").datagrid('reload');
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
	 
})

