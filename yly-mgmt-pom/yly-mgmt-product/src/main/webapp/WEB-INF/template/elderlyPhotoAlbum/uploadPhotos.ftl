
	<form id="addElderlyPhotos_form" method="post" >  
		<fieldset>
	    <legend>上传照片到相册</legend> 
	    <table class="table table-striped">
	    	<tr>
	    		<th>相册名称:</th>
	    		<td>
	    			     					
	    		<input class="easyui-combobox" data-options="
	    			valueField: 'label',
					textField: 'value',
						data: [
	    		 [#list elderlyPhotoAlbumList as elderlyPhotoAlbum]
	    		       {
							label: '${elderlyPhotoAlbum.id}',
							value: '${elderlyPhotoAlbum.name}'
						},
	    		 [/#list]
	    		 ],
	    		 prompt:'${message("yly.common.please.select")}'" panelHeight="300"  id="selectAlbumName" name="name" data-options="required:true" style="width:85px;" />
	    		</td>
	    	</tr>	  		    	
	    </table>	
	    </fieldset>
	    	
	</form>
