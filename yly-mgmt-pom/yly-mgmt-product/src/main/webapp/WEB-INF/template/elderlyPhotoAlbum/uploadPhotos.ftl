
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
		            <div id="filePicker2_Photos"></div><div class="uploadBtn"></div>
		        </div>
		    </div>
		</div>	
	</form>
