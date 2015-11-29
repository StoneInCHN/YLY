<form id="chooseEvaluating_form" method="post">   
<p/>

<p style="font-size:140%">自定义评估表</p>
<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="createForm()">开始创建评估表</a>
<hr>
<p style="font-size:140%">选择已经存在的评估表</p>
<#list evaluatingFormlist as evaluatingForm>
<#if evaluatingForm.formName == "老年人能力评估(MZ/T 039-2013)">
<ul class="easyui-tree">
        <li>
            <span>默认评估表（系统定义）</span>
            <ul>
                        <li><a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" onclick="elderlyEvaluating_manager_tool.add('+${evaluatingForm.id}+');">${evaluatingForm.formName}</a></li>
            </ul>
        </li>
      </ul>
</#if>

</#list>

      <ul class="easyui-tree">
        <li>
           <span>自定义评估表</span>
            <ul>
<#list evaluatingFormlist as evaluatingForm>
<#if evaluatingForm.formName != "老年人能力评估(MZ/T 039-2013)">
<li><a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" onclick="elderlyEvaluating_manager_tool.addCustom('+${evaluatingForm.id}+');">${evaluatingForm.formName}</a></li>
</#if>

</#list>	
 </ul>
        </li>
	<ul>


</form>