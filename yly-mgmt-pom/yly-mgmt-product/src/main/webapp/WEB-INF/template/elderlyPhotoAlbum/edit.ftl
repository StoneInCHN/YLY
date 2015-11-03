<form id="editAlbumPhoto_form" method="post">   
		<input type="hidden" value="${elderlyPhotoAlbum.id}"  name="id" />
		<input type="hidden" name="elderlyInfoID" value="${elderlyPhotoAlbum.elderlyInfo.id}" id="elderlyNameForAlbumEditID"/>
		<input type="hidden" value="${elderlyPhotoAlbum.albumCover}"  name="albumCover" />
		<input type="hidden" id="deletePhotoIDs"  name="deletePhotoIDs" />
		
	    <table class="table table-striped">
	    	<tr>
	    		<th  style="width:110px;">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyPhotoAlbum.elderlyInfo.name}" type="text" id="elderlyNameForAlbumEdit" name="elderlyname" data-options="required:true,editable:false,width:85"/>
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyNameForAlbumEdit')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<td rowspan="3">

	    		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="setAlbumCover()">
	    		<div style="margin:5px;padding:5px">
	    		<span title="重置封面照片" >
	    		[#if elderlyPhotoAlbum.albumCover == null]
	    		     <img src="${base}/resources/images/album_defaultCover.png" width="150" height="100">
	    		[#else]
	    			 <img src="${elderlyPhotoAlbum.albumCover}" width="150" height="100">
	    		[/#if]
	    		
	    		</span>	
	    		</div>
	    		</a>
	    		<p style="text-align:center"> <h5>该相册目前包含了${elderlyPhotoAlbum.elderlyPhotoes?size}张照片</h5></p>
	    		
	    		</td>
	    	    </tr>
	    	<tr>
	    		<th>相册名称:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${elderlyPhotoAlbum.name}" type="text" name="name" data-options="required:true,width:85" /> 
	    		</td>
	    		<td></td>
	    	</tr>

	    	<tr>
	    		<th>相册描述:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${elderlyPhotoAlbum.remark}" name="remark" validtype="length[0,300]" data-options="required:true,multiline:true,height:60,width:420" /> 
	    		</td>
	    		<td></td>
	    	</tr>
	    </table>
	    <div class="row" style="background:#D2D2D2;margin:10px;padding-top:5px">
	    [#list elderlyPhotoAlbum.elderlyPhotoes as elderlyPhoto]
	    <div class="col-sm-2 col-md-3">
											<div class="thumbnail" >
												<img id="${elderlyPhoto.id}" src="${elderlyPhoto.url}" width="150" height="65">
												<div classheng="caption">
													<p>
													   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="showImg(${elderlyPhoto.id},'${elderlyPhoto.url}')">恢复</a>
													   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:true" onclick="hiddenImg(${elderlyPhoto.id},'${base}/resources/images/deletePhoto.png')";>删除</a>
													</p>
												</div>
											</div>
		</div>
		[/#list]
		<div class="col-sm-2 col-md-3">
		</div>
	    </div>
</form>
<script type="text/javascript">

</script>
	