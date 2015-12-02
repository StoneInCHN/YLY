<form id="editEvaluatingSection_form" method="post">   
<div style="margin:10px">
	<input type="hidden" value="${evaluatingSection.id}"  name="id" />
	<p style="font-size:130%">模块名称：
		<input type="text" class="easyui-textbox"   validtype="length[0,500]" id="sectionName" name="sectionName" value="${evaluatingSection.sectionName}" data-options="prompt:'请输入模块名称...',required:true,multiline:true,height:40,width:460" />
	</p>
	<p style="font-size:130%">模块描述：
		<input type="text" class="easyui-textbox"   validtype="length[0,500]" id="sectionDescription" name="sectionDescription" value="${evaluatingSection.sectionDescription}" data-options="prompt:'请输入模块的描述...',required:true,multiline:true,height:140,width:460" />
	</p>
</div>
</form>