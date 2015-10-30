<form id="editAlbumPhoto_form" method="post">   
		<input type="hidden" value="${elderlyPhotoAlbum.id}"  name="id" />
		<input type="hidden" name="elderlyInfoID" value="${elderlyPhotoAlbum.elderlyInfo.id}" id="elderlyNameForAlbumEditID"/>
		<input type="hidden" value="${elderlyPhotoAlbum.albumCover}"  name="albumCover" />
	    <table class="table table-striped">
	    	<tr>
	    		<th  style="width:110px;">${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyPhotoAlbum.elderlyInfo.name}" type="text" id="elderlyNameForAlbumEdit" name="elderlyname" data-options="required:true,width:85"/>
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyNameForAlbumEdit')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    		<td rowspan="3">

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
	    			  <input class="easyui-textbox" type="text" value="${elderlyPhotoAlbum.remark}" name="remark" validtype="length[0,300]" data-options="required:true,multiline:true,height:110,width:320" /> 
	    		</td>
	    		<td></td>
	    	</tr>
	    </table>
	   
</form>
	