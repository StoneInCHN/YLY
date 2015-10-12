<form id="editBed_form" method="post" >   
		<input value="${bed.id}" type="hidden" name="id" />
	    <table class="table table-striped">
	    	<tr>
	    		<th>${message("yly.bed.bedNumber")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" type="text" name="bedNumber" value="${bed.bedNumber}" validtype="length[0,20]" data-options="required:true" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.room")}:</th>
	    		<td>
					  <input class="easyui-combotree" type="text" id="editBed_form_roomId" name="roomId" data-value="${bed.room.id}" data-options="required:true,editable:false" />   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.status")}:</th>
	    		<td>
	    			 <input type="radio" name="status" value="ENABLE" [#if bed.status == "ENABLE"]checked = "checked"[/#if]><span>${message("yly.common.enable")}</span>
       				 <input type="radio" name="status" value="DISABLE" [#if bed.status == "DISABLE"]checked = "checked"[/#if]><span>${message("yly.common.disable")}</span>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.bed.description")}:</th>
	    		<td>
	    			  <input class="easyui-textbox" type="text" value="${bed.description}" name="description" validtype="length[0,100]" data-options="required:true,multiline:true,height:100,width:200" /> 
	    		</td>
	    	</tr>
	    </table>
	</form>
<script>
	$(function(){
			$("#editBed_form_roomId").combotree({    
				animate:true,
				lines:true,
				url:'../room/findAll.jhtml',
				onLoadSuccess:function(){
					$('#editBed_form_roomId').combotree("setValue",$("#editBed_form_roomId").attr("data-value"));
				},
				queryParams:{
					roomId:$("#editBed_form_roomId").attr("data-value")
				},
				//选择树节点触发事件  
				onBeforeSelect : function(node) {  
				//返回树对象  
				var tree = $(this).tree;  
				//选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
				var isLeaf = tree('isLeaf', node.target);  
				if (!isLeaf) {  
					$.messager.alert("警告","请选择子节点","warning");
					// 返回false表示取消本次选择操作
					return false;
			   }  
		   } 
		})
	
	})
</script>