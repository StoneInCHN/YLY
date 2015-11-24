<form id="chooseEvaluating_form" method="post">   
<p/>
<p style="font-size:140%">选择已经存在的评估表</p>
<ul>
<#list evaluatingFormlist as evaluatingForm>
<li><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="elderlyEvaluating_manager_tool.add('+${evaluatingForm.id}+');">${evaluatingForm.formName}</a></li>
</#list>
</ul>
<hr>
<p style="font-size:140%">自定义评估表</p>
<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="createForm()">开始创建评估表</a>
</form>