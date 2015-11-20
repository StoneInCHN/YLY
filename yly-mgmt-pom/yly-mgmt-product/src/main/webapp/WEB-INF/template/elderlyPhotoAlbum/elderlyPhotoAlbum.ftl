<script src="${base}/resources/modules/elderlyPhotoAlbum.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	//alert('../elderlyPhotoAlbum/loadAlbum.jhtml?keysOfElderlyName='+$("#keysOfElderlyName").val()+'&keysOfPhotoAlbumName='+$("#keysOfPhotoAlbumName").val());
	loadAlbum();//动态加载相册
});
function loadAlbum(){
			$.ajax({
			url: '../elderlyPhotoAlbum/loadAlbum.jhtml?keysOfElderlyName='+$("#keysOfElderlyName").val()+'&keysOfPhotoAlbumName='+$("#keysOfPhotoAlbumName").val(),
			type: 'GET',			
			dataType: "json",
			success:function(jsonObj, textStatus){
				if(jsonObj.length>0){
					var divAlbumHtml = '<div class="row" style="background:#D2D2D2;margin:10px;padding-top:10px">';
					for(var i=0; i< jsonObj.length; i++){
						var shortName = "《"+jsonObj[i].name+"》";
						if(shortName && jsonObj[i].name.length>5){
							shortName= "《"+jsonObj[i].name.substring(0,5)+"...》";
						}
						var shortRemark= jsonObj[i].remark;
						if(shortRemark && shortRemark.length>9){
							shortRemark= shortRemark.substring(0,9)+"...";
						}	
						
						var albumCoverImg =  jsonObj[i].albumCover;	
						if(albumCoverImg == null || albumCoverImg == ""){
						    albumCoverImg = '${base}/resources/images/album_defaultCover.png';
						}	
						[@compress single_line = true]
						divAlbumHtml += '<div class="col-sm-2 col-md-2"">
											<div class="thumbnail">
												<a href="javascript:void(0);" onclick="photoAlbum_manager_tool.showImage('+jsonObj[i].id+');"><img src="'+albumCoverImg+'" title="相册主人:'+jsonObj[i].elderlyInfo.name+'"  width="155" height="65"></a>
												<div classheng="caption">
													<h6><a href="javascript:void(0);" onclick="photoAlbum_manager_tool.showImage('+jsonObj[i].id+');"><span title="'+jsonObj[i].name+'">'+shortName+'</span></a></h6> 
													<p><span title="'+jsonObj[i].remark+'">'+shortRemark+'</span></p>
													<p>
													   <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="photoAlbum_manager_tool.edit('+jsonObj[i].id+')";>编辑</a> 
													   <a href="javascript:void(0);" class="easyui-linkbutton"  onclick="photoAlbum_manager_tool.remove('+jsonObj[i].id+')";>删除</a>
													</p>
												</div>

											</div>
										 </div>';
						[/@compress]
						
					}
					divAlbumHtml += '</div>';
					$("#photoAlbum_show_form").html(divAlbumHtml);
				}else{
				    $("#photoAlbum_show_form").html("");	
				}			
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                  //alert("XMLHttpRequest.status:" + XMLHttpRequest.status);
                  //alert("XMLHttpRequest.readyState:" + XMLHttpRequest.readyState);
                  //alert("textStatus:" + textStatus);
            },
			});


}
</script>
<div>
	  <fieldset>
	    <legend>相册查询</legend>
	    <form id="photoAlbum_search_form" class="search-form">
	        <div class="search-item">&nbsp;&nbsp;</div>
			<div class="search-item">
			   <label>相册名称:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="keysOfPhotoAlbumName" name="keysOfPhotoAlbumName" validtype="length[0,15]" style="width:110px;"/>
			</div>			        
	    	<div class="search-item">
			   <label>${message("yly.common.elderly.name")}:</label>
			   <input type="text" class="easyui-textbox"  data-options="prompt:'请输入关键字...'"  id="keysOfElderlyName" name="keysOfElderlyName" validtype="length[0,15]" style="width:110px;"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="photoAlbum_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("yly.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="photoAlbum_manager_tool">
	<div class="tool-button">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="photoAlbum_manager_tool.add();">添加相册</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-large-picture',plain:true" onclick="photoAlbum_manager_tool.uploadPhotos();">上传照片</a>
	</div>
	<div class="tool-filter"></div>
</div> 

<form id="photoAlbum_show_form">

</form>


<div id="addElderlyPhotoAlbum">
	<form id="addElderlyPhotoAlbum_form" method="post" class="form-table">   
		<input type="hidden" name="identifier" id="identifier">
		<input type="hidden" id="addAlbum_form_file_input" name="albumCover">
		<input type="hidden" name="elderlyInfoID" id="elderlynameForAlbumID">
		<input type="hidden" id="addAlbum_photos" name="photoList">
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" id="elderlynameForAlbum" name="elderlyname" data-options="required:true,editable:false,width:85" />   
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlynameForAlbum')" iconCls="icon-search" plain=true"></a> 
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>相册名称:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" id="albumName" name="name" validtype="length[0,15]" data-options="required:true,width:85"/> 
	    		</td>
	    	</tr>	  	
	    	<tr>
	    		<th>相册描述:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" name="remark" validtype="length[0,30]" data-options="required:true,width:320,height:60,multiline:true"/> 
	    		</td>
	    	</tr>
	   </div>		    	
	    </table>
	    
	    
		<div id="uploader" class="wu-example">
		    <div class="queueList">
		        <div id="dndArea" class="placeholder" style="background:#F0F0F0">
		            <div id="filePicker"></div>
		            <p>或将照片拖到这里，单次最多可选300张</p>
		        </div>
		    </div>
		    <div class="statusBar" style="display:none;">
		        <div class="progress">
		            <span class="text">0%</span>
		            <span class="percentage"></span>
		        </div><div class="info"></div>
		        <div class="btns">
		            <div id="filePicker2"></div><div class="uploadBtnDisable"></div>
		        </div>
		    </div>
		</div>


	</form>
</div>

<div id="addElderlyPhotos">
	<form id="addElderlyPhotos_form" method="post" class="form-table">  
	    <input type="hidden" name="selectAlbumID" id="selectAlbumID">
	    <input type="hidden" name="selectIdentifier" id="selectIdentifier">
	    <input type="hidden" id="selectAlbum_photos" name="photoList">
		<fieldset>
	    <legend>上传照片到相册</legend> 
	    <table class="table table-striped">
	    	<tr>
	    		<th>相册名称:</th>
	    		<td>
	    		<input class="easyui-textbox" type="text" id="selectAlbum" name="selectAlbumName" data-options="required:true,editable:false,width:85" />   
	    			 <a href="#" id="album_search_btn" class="easyui-linkbutton" onclick="searchAlbum('selectAlbum','${base}/resources/images/album_defaultCover.png')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    	</tr>	  		    	
	    </table>	
	    </fieldset>
	    		<div id="uploader_Photos" class="wu-example">
		    <div class="queueList">
		        <div id="dndArea" class="placeholder" style="background:#F0F0F0">
		            <div id="filePicker_Photos"></div>
		            <p>或将照片拖到这里，单次最多可选300张</p>
		        </div>
		    </div>
		    <div class="statusBar" style="display:none;">
		        <div class="progress">
		            <span class="text">0%</span>
		            <span class="percentage"></span>
		        </div><div class="info"></div>
		        <div class="btns">
		            <div id="filePicker2_Photos"></div><div class="uploadBtnDisable"></div>
		        </div>
		    </div>
		</div>	
	</form>
</div>
<div id="editElderlyPhotoAlbum"></div>  
<div id="showPhotoAlbum"></div> 
<div id="setAlbumCover">
<form id="setAlbumCover_form" method="post" class="form-table">
<input type="hidden" name="coverUrl" id="coverUrl">
<input type="hidden" name="coverID" id="coverID">
	    		<td rowspan="3">
	    		    <div title="设置相册封面" class="easyui-tooltip headWarp">
	    				<div id="albumUploader-add" class="single-uploader">
						    <div class="queueList">
						        <div class="placeholder">
						        	<div id="albumFilePicker-add"></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
				    </div>
	    		</td>
</form>   
</div>




