
<form id="editPhotoAlbum_form" method="post">   
		<input type="hidden" value="${elderlyPhotoAlbum.id}"  name="id" />
		<input type="hidden" name="elderlyInfoID" value="${elderlyPhotoAlbum.elderlyInfo.id}" id="elderlyNameEditID"/>
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.common.elderly.name")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${elderlyPhotoAlbum.elderlyInfo.name}" type="text" id="elderlyNameEdit" name="elderlyname" data-options="required:true,width:85"/>
	    			 <a href="#" id="elderly_info_search_btn" class="easyui-linkbutton" onclick="searchElderlyInfo('elderlyNameEdit')" iconCls="icon-search" plain=true"></a>    
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" value="${elderlyPhotoAlbum.name}" type="text" name="name" data-options="required:true,width:85" /> 
	    		</td>
	    	</tr>
	    </table>
</form>
	

