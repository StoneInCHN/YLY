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
				    	text:"编辑相册",
				    	iconCls:'icon-save',
						handler:function(){
							$('#showPhotoAlbum').dialog("close");
							photoAlbum_manager_tool.edit();
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
				$.messager.confirm(message("yly.common.confirm"),"确定删除相册以及相册下的所有照片么？", function(r) {
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
								$("#albumUploader-add .uploadBtn").trigger("upload");//封面
								$("#uploader .uploadBtn").trigger("upload");//照片集
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
				    	//照片上传
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
				     				 data.identifier = $("#identifier").val();
				     				 data.albumName = $("#albumName").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				//将返回的图片路径放到隐藏的input中，用于表单保存
				     				$("#addAlbum_form_file_input").val(response.content);
				     				//albumCover
				     				alert($("#addAlbum_form_file_input").val());
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
											$('#addElderlyPhotoAlbum').dialog("close");
											
										},
										error:function (XMLHttpRequest, textStatus, errorThrown) {
											$.messager.progress('close');
											alertErrorMsg();
										}
									});
				     			}
				     	};
				     	
				     	singleUpload(options);
				     	
				    	var options1 = {	
				    			createOption:{
				    			
				    		        pick: {
				    		            id: '#filePicker',
				    		            label: '点击选择图片'
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
				     			uploadBeforeSend:function(object, data, headers){
				     				 //在参数中增加一个老人编号字段 identifier
				     				 data.identifier = $("#identifier").val();
				     				 data.albumName = $("#albumName").val();
				     			},
				     			uploadSuccess:function(file, response){
				     				photoUrlList.push(response.content);				     				
				     			}};
				    	multipleUpload(options1);
				    },
				    onClose:function(){
				    	$("#uploader .uploadBtn").trigger("clearFiles");						
				    	loadAlbum();//重新加载相册
				    }
				});
				$('#addElderlyPhotoAlbum_form').show();
		    },
		    edit:function(photoAlbumID){
				var _dialog = $('#editElderlyPhotoAlbum').dialog({  
				    title: "编辑相册相片",    
				    width: 700,    
				    height: 700,
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
						}
				    }]
				});
		    
		    }		    
	}
	
$(function(){
	$("#photoAlbum_search_btn").click(function(){
		loadAlbum();//重新加载相册
	});
})
function formatLongString(str,len){
	if(str.length > len){
		return '<span title="'+str+'">'+str.substring(0,len-1)+"..."+'<span>'
	}else{
		return str;
	}	
}
function deepColor(){
	$(this).css({
		padding_top:'10px',
		background:'#AAAAAA'
	});
	
}

