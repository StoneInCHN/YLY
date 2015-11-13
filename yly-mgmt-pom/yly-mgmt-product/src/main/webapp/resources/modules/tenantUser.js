var tenantUser_manager_tool = {
		add:function(){
			$('#addTenantUser').dialog({
			    title: message("yly.tenantUser.add"),    
			    width: 700,    
			    height: 550,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addTenantUser_form').form('validate');
						var $photoLi = $("#tenantUserUploader-add ul.filelist li");
						
						if(validate){
							if($photoLi.length >0){
								$("#tenantUserUploader-add .uploadBtn").trigger("upload");
							}else{
								$.ajax({
									url:"../tenantUser/add.jhtml",
									type:"post",
									data:$("#addTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addTenantUser').dialog("close").form("reset");
											$("#tenantUser-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							}
						};
					}
				},{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addTenantUser').dialog("close").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addTenantUser_form').show();
			    	$("#tenantUserDepartment-add").combotree({    
					    url: '../department/list.jhtml',    
					    method:"get",
					    animate:true,
					    lines:true,
					    required:true,
					    prompt:message("yly.common.please.select"),
					    formatter:function(node){
					    	node.text = node.name;
							return node.name;
						},
					    onSelect: function(rec){    
				            var url = '../position/findPositions.jhtml?id='+rec.id;    
				            $('#tenantUserPosition-add').combobox('clear');
				            $('#tenantUserPosition-add').combobox('reload', url);    
				        }
					});
			    	$("#tenantUserPosition-add").combobox({    
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("yly.common.please.select"),
					});
			    	//头像上传
			     	var options ={
			     			createOption:{
			     				pick: {
					                 id: '#tenantUserFilePicker-add',
					                 label: '',
					                 multiple :false
					             },
					             dnd: '#tenantUserUploader-add .queueList',
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
			     			warp :"addTenantUser_form",
			     			uploadBeforeSend:function(object, data, headers){
			     				 //在参数中增加一个员工编号字段 staffID
			     				 data.staffID =$("#staffID").val();
			     			},
			     			uploadSuccess:function(file, response){
			     				//将返回的图片路径放到隐藏的input中，用于表单保存
			     				$("#addTenantUser_form_file_input").val(response.content);
			     				$.ajax({
									url:"../tenantUser/add.jhtml",
									type:"post",
									data:$("#addTenantUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addTenantUser_form').form('reset');
										$('#addTenantUser').dialog("close");
										$("#tenantUser-table-list").datagrid('reload');
										
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
			    	$("#tenantUserUploader-add .uploadBtn").trigger("clearFileQuene");
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#tenantUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("yly.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editTenantUser').dialog({    
				title: message("yly.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../tenantUser/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("yly.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editTenantUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../tenantUser/update.jhtml",
								type:"post",
								data:$("#editTenantUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editTenantUser').dialog("close");
										$("#tenantUser-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editTenantUser').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#tenantUserDepartment-edit").combobox({    
					    valueField:'id',    
					    textField:'name',
					    cache: true,
					    url:'../department/findDepartments.jhtml',
					    	onLoadSuccess:function(){
						    	$("#tenantUserDepartment-edit").combobox("setValue",$("#tenantUserDepartment-edit").attr("data-value"))
						    }
					});
			    	$("#tenantUserPosition-edit").combobox({    
					    valueField:'id',    
					    textField:'name',
					    cache: true,
					    editable : false,
					    url:'../position/findAllPositions.jhtml',
					    onLoadSuccess:function(){
					    	$("#tenantUserPosition-edit").combobox("setValue",$("#tenantUserPosition-edit").attr("data-value"))
					    }
					});
			    	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#tenantUserFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#tenantUserUploader-edit .queueList',
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
					             server: '../tenantUser/uploadPhoto.jhtml',
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
			     			warp :"editTenantUser_form",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#tenantUserFilePicker-edit2',
			     				innerHTML: '替换头像'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 //
			     				 data.staffID =$("#editTenantUser_form").find("input[name='staffID']").val();
			     				 data.tenantUserId=$("#editTenantUser_form").find("input[name='id']").val();;
			     			}
			     	};
			     	
			     	singleUpload(editOptions);
			     	$("#editTenantUser_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
			     		    if (res){
			     		    	$("#tenantUserUploader-edit .uploadBtn").trigger("upload");
			     		    }    
			     		}); 
			     		//alert("保存头像");
			     	})
			    }
			});  
		},
		remove:function(){
			listRemove('tenantUser-table-list','../tenantUser/delete.jhtml');
		}
};

$(function(){	
	$("#tenantUserDepartment-search").combotree({    
		 url: '../department/list.jhtml',    
		 method:"get",
		 animate:true,
		 lines:true,
		 prompt:message("yly.common.please.select"),
	     formatter:function(node){
	    	node.text = node.name;
			return node.name;
		 },
		 onSelect: function(rec){    
	        var url = '../position/findPositions.jhtml?id='+rec.id;    
	        $('#tenantUserPosition-search').combobox('clear');
	        $('#tenantUserPosition-search').combobox('reload', url);    
	     }
		
	});
	
	$("#tenantUserPosition-search").combobox({    
	    valueField:'id',    
	    textField:'name',
	    cache: true,
	    prompt:message("yly.common.please.select")
	});
	$("#tenantUser-table-list").datagrid({
		title:message("yly.tenantUser.list"),
		fitColumns:true,
		toolbar:"#tenantUser_manager_tool",
		url:'../tenantUser/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#tenantUserDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../tenantUser/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#tenantUserDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.tenantUser.realName"),field:"realName",width:100,sortable:true},
		      {title:message("yly.common.gender"),field:"gender",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "MALE"){
			    		  return  message("yly.common.male");
			    	  }else if (value = "FEMALE"){
			    		  return  message("yly.common.female");
			    	  }
		      	  }  },
		      {title:message("yly.common.age"),field:"age",width:100,sortable:true},
		      {title:message("yly.tenantUser.staffID"),field:"staffID",width:100,sortable:true},
		      {title:message("yly.tenantUser.staffStatus"),field:"staffStatus",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value == "INSERVICE"){
			    		  return  message("yly.tenantUser.staffStatus.inService");
			    	  }else if (value = "OUTSERVICE"){
			    		  return  message("yly.tenantUser.staffStatus.outService");
			    	  }
		      	  }  
		      },
	    	  
		      {title:message("yly.tenantUser.department"),field:"department",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:message("yly.tenantUser.position"),field:"position",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:message("yly.tenantUser.hireDate"),field:"hireDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	
	$("#tenantUser-search-btn").click(function(){
	  var _queryParams = $("#tenantUser-search-form").serializeJSON();
	  $('#tenantUser-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#tenantUser-table-list").datagrid('reload');
	})
	
	 
	 
})
