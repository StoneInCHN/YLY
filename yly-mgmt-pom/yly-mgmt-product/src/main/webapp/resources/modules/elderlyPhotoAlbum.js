var photoAlbum_manager_tool = {
		    showImage:function(photoAlbumID){
		    	$('#showPhotoAlbum').dialog({ 
				    title: message("yly.elderly.Photo.Album.view"),    
				    width: 800,    
				    height: 750,
				    background:'#F0F0F0',
				    iconCls:'icon-mini-edit',
				    modal:true,
				    href:'../elderlyPhotoAlbum/viewPhotos.jhtml?photoAlbumID='+photoAlbumID,
				    cache: false, 	
				    buttons:[{
				    	text:message("yly.elderly.Photo.Album.edit"),
				    	iconCls:'icon-save',
						handler:function(){
							$('#showPhotoAlbum').dialog("close");
							photoAlbum_manager_tool.edit(photoAlbumID);
						}
					},{
						text:message("yly.common.close"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#showPhotoAlbum').dialog("close");
						}
				    }]
				});
		    },
		    remove:function(photoAlbumID){
				$.messager.confirm(message("yly.common.confirm"),message("yly.elderly.Photo.Album.confirm_delete_album_and_allPhotos"), function(r) {
					if(r){
						$.ajax({
							url : '../elderlyPhotoAlbum/delete.jhtml',
							type : "post",
							traditional : true,
							data : {
								"id" : photoAlbumID
							},
							beforeSend : function() {
								$.messager.progress({
									text : message("yly.common.progress")
								});
							},
							success : function(result, response, status) {
								$.messager.progress('close');
								var resultMsg = result.content;
								if (response == "success") {
									showSuccessMsg(resultMsg);
									loadAlbum();//重新加载相册
								} else {
									alertErrorMsg();
								}
							}
						});
				    }
				});
		    },
		    add:function(){
				$('#addElderlyPhotoAlbum').dialog({  
				    title: message("yly.elderly.Photo.Album.add"),    
				    width: 700,    
				    height: 700,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addElderlyPhotoAlbum_form').form('validate');
							if(validate){	
								$("#uploader .uploadBtnDisable").trigger("upload");//照片集
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addElderlyPhotoAlbum').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addElderlyPhotoAlbum_form').show();
				    	var options1 = {	
				    			createOption:{
				    			
				    		        pick: {
				    		            id: '#filePicker',
				    		            label: message("yly.elderly.Photo.Album.click_choose_photo")
				    		        },
				    		        dnd: '#uploader .queueList',
				    		        paste: document.body,

				    		        accept: {
				    		            title: 'Images',
				    		            extensions: 'gif,jpg,jpeg,bmp,png',
				    		            mimeTypes: 'image/*'
				    		        },

				    		        // swf文件路径
				    		        swf: BASE_URL + '/js/Uploader.swf',

				    		        disableGlobalDnd: true,
				    		        chunked: true,
				    		        server: '../elderlyPhotoAlbum/uploadAlbum.jhtml',
				    		        fileNumLimit: 300,
				    		        fileSizeLimit: 5 * 1024 * 1024,    // 200 M
				    		        fileSingleSizeLimit: 1 * 1024 * 1024,    // 50 M
						             //图片裁剪
						             compress:{
						            	 width: 1000,
						            	 height: 1000,
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
				    		    warp :"uploader",
				    		    filePicker2 :"filePicker2",
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier
				     				 data.identifier = $("#identifier").val();
				     				 data.albumName = $("#albumName").val();
				     				 //data.elderlyInfoID = $("#elderlynameForAlbumID").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				photoUrlList.push(response.content);				     				
				     			}};
				    	multipleUpload(options1);
				    },
				    onClose:function(){
				    	$("#uploader .uploadBtnDisable").trigger("clearFiles");//清空上次所选图片文件
	     				$("#identifier").val("");//清空隐藏字段 identifier
	     				$("#albumName").val("");//清空隐藏字段 albumName
	     				//$("#elderlynameForAlbumID").val("");
				    	loadAlbum();//重新加载相册
				    }
				});
				$('#addElderlyPhotoAlbum_form').show();
		    },
		    edit:function(photoAlbumID){
				var _dialog = $('#editElderlyPhotoAlbum').dialog({  
				    title:message("yly.elderly.Photo.Album.edit"),    
				    width: 750,    
				    height: 750,
				    iconCls:'icon-mini-edit',
				    modal:true,
				    href:'../elderlyPhotoAlbum/detail.jhtml?id='+photoAlbumID+'&handle=edit',
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editAlbumPhoto_form').form('validate');
							if(validate){
								$.ajax({
									url:"../elderlyPhotoAlbum/update.jhtml",
									type:"post",
									data:$("#editAlbumPhoto_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editElderlyPhotoAlbum').dialog("close");
										loadAlbum();//重新加载相册
										$.messager.alert(message("yly.common.prompt"),message("yly.elderly.Photo.Album.edit_success"),'info');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
							
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editElderlyPhotoAlbum').dialog("close");
							 loadAlbum();//重新加载相册
						}
				    }],
				    onOpen:function(){

				    	$('#coverID').val(photoAlbumID);

				    	//封面上传
				     	var options ={
				     			createOption:{
				     				pick: {
						                 id: '#albumFilePicker-add',
						                 label: '',
						                 multiple :false
						             },
						             dnd: '#albumUploader-add .queueList',
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
						             server: '../elderlyPhotoAlbum/uploadAlbum.jhtml',
						             fileNumLimit: 1,
						             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
						             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
						             //图片裁剪
						             compress:{
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
				     			//warp :"addElderlyPhotoAlbum_form",
				     			warp :"albumUploader-add",
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier

				     			},
				     			uploadSuccess:function(file, response){}
				     	};
				     	//singleUpload(options);
				    }
				});
				//$('#editAlbumPhoto_form').show();
		    
		    },
		    uploadPhotos:function(){
		    	var _dialog = $('#addElderlyPhotos').dialog({  
				    title: message("yly.elderly.Photo.Album.upload"),    
				    width: 700,    
				    height: 700,
				    iconCls:'icon-mini-add',
				    modal:true,
				    //href:'../elderlyPhotoAlbum/uploadPhotos.jhtml',
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addElderlyPhotos_form').form('validate');
							if(validate){	
								$("#uploader_Photos .uploadBtnDisable").trigger("upload");//照片集
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addElderlyPhotos').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addElderlyPhotos_form').show();
				    	
				    	var options2 = {	
				    			createOption:{
				    			
				    		        pick: {
				    		            id: '#filePicker_Photos',
				    		            label: message("yly.elderly.Photo.Album.click_choose_photo")
				    		        },
				    		        dnd: '#uploader .queueList',
				    		        paste: document.body,

				    		        accept: {
				    		            title: 'Images',
				    		            extensions: 'gif,jpg,jpeg,bmp,png',
				    		            mimeTypes: 'image/*'
				    		        },

				    		        // swf文件路径
				    		        swf: BASE_URL + '/js/Uploader.swf',

				    		        disableGlobalDnd: true,
				    		        chunked: true,
				    		        server: '../elderlyPhotoAlbum/uploadAlbum.jhtml',
				    		        fileNumLimit: 300,
				    		        fileSizeLimit: 5 * 1024 * 1024,    // 200 M
				    		        fileSingleSizeLimit: 1 * 1024 * 1024,    // 50 M
						             //图片裁剪
						             compress:{
						            	 width: 1000,
						            	 height: 1000,
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
				    		    warp :"uploader_Photos",
				    		    filePicker2 :"filePicker2_Photos",
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier
				     				 data.selectIdentifier = $("#selectIdentifier").val();
				     				 data.selectAlbumName = $("#selectAlbum").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				photoUrlList.push(response.content);				     				
				     			}};
				    	multipleUpload(options2);
				    },
				    onClose:function(){
				    	$("#uploader_Photos .uploadBtnDisable").trigger("clearFiles");//清空上次所选图片文件
				    	$("#selectIdentifier").val("");//清空隐藏字段 selectIdentifier
				    	$("#selectAlbumName").val("");//清空隐藏字段 selectAlbumName
				    	loadAlbum();//重新加载相册
				    }
				});
		    	$('#addElderlyPhotos_form').show();
		    }
	}
	
$(function(){
	$("#photoAlbum_search_btn").click(function(){
		loadAlbum();//重新加载相册
	});
})
//function formatLongString(str,len){
//	if(str.length > len){
//		return '<span title="'+str+'">'+str.substring(0,len-1)+"..."+'<span>'
//	}else{
//		return str;
//	}	
//}
function deepColor(){
	$(this).css({
		padding_top:'10px',
		background:'#AAAAAA'
	});
	
}

function hiddenImg(id,url){
	if($("#"+id)){
		//$("#"+id).css("opacity": "0.5");
		$("#"+id).attr("src",url);
		if($("#deletePhotoIDs").val().indexOf(id+",") == -1){
			$("#deletePhotoIDs").val($("#deletePhotoIDs").val() + id+",");
		}
	}
}
function showImg(id,url){
		if($("#"+id)){
		//$("#"+id).css("opacity": "1");
		$("#"+id).attr("src",url);
		$("#deletePhotoIDs").val($("#deletePhotoIDs").val().replace(id+",",""));
	}
}
function setAlbumCover(){
	$('#editElderlyPhotoAlbum').dialog("close");
	$('#setAlbumCover').dialog({  
		 title: message("yly.elderly.Photo.Album.setAlbum"),    
		    width: 300,    
		    height: 300,
		    iconCls:'icon-mini-edit',
		    modal:true,		    
		    buttons:[{
		    	text:message("yly.common.save"),
		    	iconCls:'icon-save',
				handler:function(){
					var validate = $('#setAlbumCover_form').form('validate');
					if(validate){	
						$("#albumUploader-add .uploadBtn").trigger("upload");//封面
					};
				}
			},{
				text:message("yly.common.cancel"),
				iconCls:'icon-cancel',
				handler:function(){
					 $('#setAlbumCover').dialog("close");
					 photoAlbum_manager_tool.edit($('#coverID').val());
				}
		    }],
		    onOpen:function(){
		    	$('#setAlbumCover_form').show();
		    	var options ={
		     			createOption:{
		     				pick: {
				                 id: '#albumFilePicker-add',
				                 label: '',
				                 multiple :false
				             },
				             dnd: '#albumUploader-add .queueList',
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
				             server: '../elderlyPhotoAlbum/setAlbumCover.jhtml',
				             fileNumLimit: 1,
				             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
				             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
				             //图片裁剪
				             compress:{
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
		     			warp :"albumUploader-add",
		     			uploadBeforeSend:function(object, data, headers){
		     				 //在参数中增加一个老人编号字段 identifier
		     				 data.coverID = $('#coverID').val();
		     			},
		     			uploadSuccess:function(file, response){
		     				//将返回的图片路径放到隐藏的input中，用于表单保存
		     				$("#coverUrl").val(response.content);
		     				$.ajax({
								url:"../elderlyPhotoAlbum/saveAlbumCover.jhtml",
								type:"post",
								data:$("#setAlbumCover_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("yly.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									showSuccessMsg(result.content);
									$('#setAlbumCover_form').form('reset');
									$('#setAlbumCover').dialog("close");

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
		    	photoAlbum_manager_tool.edit($('#coverID').val());
		    	$("#albumUploader-add").find(".webuploader-container").show();
		    	$("#albumUploader-add .uploadBtn").removeClass('disabled');
		    	$("#albumUploader-add .uploadBtn").trigger("clearFileQuene");//清空上次所选图片文件
		    }
		
	});
	
}
/**
 * 相册查询功能
 */
function searchAlbum(id,defaultImg){
	$('#searchAlbum').dialog({
	    title: message("yly.elderly.Photo.Album.chooseAlbum"),    
	    width: 850,
	    height: 600,
	    modal:true,
	    cache: false,   
	    href:'../elderlyPhotoAlbum/albumSearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchAlbum').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	$("#albumSearch-table-list").datagrid({
	    		title:message("yly.elderly.Photo.Album.search"),
		    	 fitColumns:true,
		    	 url:'../elderlyPhotoAlbum/list.jhtml',  
		    	 pagination:true,
		    	 loadMsg:message("yly.common.loading"),
		    	 striped:true,
		    	 onDblClickRow : function (rowIndex, rowData){
		    		 $("#"+id+"ID").val(rowData.id);
		    		 $("#"+id).textbox('setValue',rowData.name);
		    		 if($("#selectIdentifier")){
		    			 $("#selectIdentifier").val(rowData.elderlyInfo.identifier);
		    		 }
		    		 $('#searchAlbum').dialog("close");
		    	 },
		    	 columns:[
		  	    	    [
		  	    	        {field:'ck',checkbox:true,width:8,align:'center'},
		  		      {title:message("yly.elderly.Photo.Album.albumCover"),field:"albumCover",width:10,align:'center',formatter: function(value,row,index){
							if(value==null || value==""){
								return  '<div style="margin:3px;padding:3px">'+
					    		        '<span title="默认封面" >'+
					    		        '<img src="'+defaultImg+'" width="50" height="40"></img></span></div>';;
							}else{
								return  '<div style="margin:3px;padding:3px">'+
			    		        		'<span title="自定义封面" >'+'<img src="'+value+'" width="50" height="40"></img></span></div>';
							}
					  }},
		  		    {title:message("yly.elderly.Photo.Album.albumName"),field:"name",width:10,align:'center',sortable:true},
		  		  {title:message("yly.elderlyInfo.stuffDeposit.elderlyName"),field:"elderlyInfoName",width:10,align:'center',formatter: function(value,row,index){
		  			 return row.elderlyInfo.name;
				  }},
		  		{title:message("yly.common.remark"),field:"remark",width:20,align:'center',sortable:true},
		  		      ]]
	    	});
	    }
	});
	
}
function saveAlbumAndPhotos(){

	    if($("#selectAlbumID").val() == null || $("#selectAlbumID").val() == ""){
	    	$("#addAlbum_photos").val(photoUrlList.join(","));
			$.ajax({
				url:"../elderlyPhotoAlbum/add.jhtml",
				type:"post",
				data:$("#addElderlyPhotoAlbum_form").serialize(),
				beforeSend:function(){
					$.messager.progress({
						text:message("yly.common.saving")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
					showSuccessMsg(result.content);
					$('#addElderlyPhotoAlbum_form').form('reset');
					showSuccessMsg(message("yly.common.success"));  
					$.messager.alert(message("yly.common.prompt"),message("yly.elderly.Photo.Album.upload_success"),'info');
					$('#addElderlyPhotoAlbum').dialog("close");
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
	    }else{
	    	$("#selectAlbum_photos").val(photoUrlList.join(","));
	    	$.ajax({
				url:"../elderlyPhotoAlbum/add.jhtml",
				type:"post",
				data:$("#addElderlyPhotos_form").serialize(),
				beforeSend:function(){
					$.messager.progress({
						text:message("yly.common.saving")
					});
				},
				success:function(result,response,status){
					$.messager.progress('close');
					showSuccessMsg(result.content);
					$('#addElderlyPhotos_form').form('reset');
					showSuccessMsg(message("yly.common.success"));  
					$.messager.alert(message("yly.common.prompt"),message("yly.elderly.Photo.Album.upload_success"),'info');
					$('#addElderlyPhotos').dialog("close");
				},
				error:function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
	    	$("#selectAlbumID").val("");
	    }

		
}
