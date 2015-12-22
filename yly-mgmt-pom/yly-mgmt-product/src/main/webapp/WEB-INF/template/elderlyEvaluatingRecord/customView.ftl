
<form id="viewEvaluating_form" method="post">  

<div style="margin:15px;padding:15px">
<p style="text-align:center;font-size:180%">老年人能力评估基本信息表 </p>
<p style="font-size:140%">A.1　评估基本信息表</p>
<table class="table table-bordered" border="1px" style="border-collapse:collapse">
<tr><td style="width:230px">A.1.1 评估编号</td><td>
${elderlyEvaluatingRecord.evaluatingForm.formName}
</td></tr>
<tr><td>A.1.2 姓名</td><td>${elderlyEvaluatingRecord.elderlyInfo.name}</td></tr>
<tr><td>A.1.3 身份证号码</td><td>${elderlyEvaluatingRecord.elderlyInfo.IDCard}</td></tr>
<tr><td>A.1.4 性别</td>
	<td>
		<#if elderlyEvaluatingRecord.elderlyInfo.gender=="MALE">男
		<#elseif elderlyEvaluatingRecord.elderlyInfo.gender=="FEMALE">女
		</#if>
	</td>
</tr>
<tr><td>A.1.5 评估日期</td>
	<td>
		${(elderlyEvaluatingRecord.evaluatingDate?string("yyyy年MM月dd日"))!''} 
	</td>
</tr>
<tr><td>A.1.6 评估原因</td>
	<td>
		<#if elderlyEvaluatingRecord.evaluatingReason=="CHECKIN_EVALUATING">接受服务前初评
		<#elseif elderlyEvaluatingRecord.evaluatingReason=="ROUTINE_EVALUATING">接受服务后的常规评估
		<#elseif elderlyEvaluatingRecord.evaluatingReason=="IMMEDIATE_EVALUATING">状况发生变化后的即时评估
		<#elseif elderlyEvaluatingRecord.evaluatingReason=="QUESTION_EVALUATING">因评估结果有疑问进行的复评
		</#if>
	</td>
</tr>
</table>


<p style="text-align:center;font-size:180%">
${elderlyEvaluatingRecord.evaluatingForm.formName}
</p>

<#list elderlyEvaluatingRecord.evaluatingForm.evaluatingSections as evaluatingSection>
<p style="font-size:140%">B.${evaluatingSection_index + 1} ${evaluatingSection.sectionName}</p>

<table class="table table-bordered" border="1px" style="border-collapse:collapse">
<#list evaluatingSection.evaluatingItems as evaluatingItem>
<tr>
	<td text-align="center" style="width:150px" rowspan="${evaluatingItem.evaluatingItemsOptions?size}">
		B.${evaluatingSection_index + 1}.${evaluatingItem_index+1}  ${evaluatingItem.itemName}
	</td>
	<td text-align="center"style="width:80px" rowspan="${evaluatingItem.evaluatingItemsOptions?size}">
	   <#list elderlyEvaluatingRecord.evaluatingItemsAnswers as evaluatingItemsAnswer>
			<#if evaluatingItemsAnswer.evaluatingItemsOptions.evaluatingItems.itemName == evaluatingItem.itemName>
				<u>${evaluatingItemsAnswer.evaluatingItemsOptions.optionScore}分</u>
			</#if>	
	   </#list> 
	</td>
	<td><#list evaluatingItem.evaluatingItemsOptions as evaluatingItemsOption>	
	       <#if evaluatingItemsOption_index == 0>
               &nbsp;${evaluatingItemsOption.optionScore}&nbsp;分,&nbsp;&nbsp;${evaluatingItemsOption.evaluatingItemOptions.optionName}
           </#if>	
	    </#list> 
	</td>
</tr>
<#list evaluatingItem.evaluatingItemsOptions as evaluatingItemsOption>	
	<#if evaluatingItemsOption_index != 0>	
		  <tr>
		  	<td> 
		  		&nbsp;&nbsp;${evaluatingItemsOption.optionScore}</font>&nbsp;分,&nbsp;&nbsp;${evaluatingItemsOption.evaluatingItemOptions.optionName}
		  	</td>
		  </tr>
	</#if>
</#list>
</#list>
<tr>
	<td>B.${evaluatingSection_index+1}.${evaluatingSection.evaluatingItems?size + 1}  ${evaluatingSection.sectionName}总分</td>
	<td>
		<#if sectionScoreMap??&&sectionScoreMap?size gt 0>
		   <#list sectionScoreMap.keySet() as key>
		   		<#if key==evaluatingSection.id>
					&nbsp;&nbsp;<u>${sectionScoreMap.get(key)}分</u>&nbsp;&nbsp;&nbsp;
				</#if>
	      </#list>
		</#if>
	</td>
	<td>上述${evaluatingSection.evaluatingItems?size}个项目得分总和</td>
</tr>

</table>
</#list>

<p style="text-align:center;font-size:180%">老年人能力评估报告</p>
<table class="table table-bordered" border="1px" style="border-collapse:collapse">
<tr>
	<td style="width:150px" rowspan="${elderlyEvaluatingRecord.evaluatingForm.evaluatingSections?size}">C.1&nbsp;&nbsp;一级指标分级&nbsp;&nbsp;</td>
	<td>	
		<#list elderlyEvaluatingRecord.evaluatingForm.evaluatingSections as evaluatingSection>	
	       <#if evaluatingSection_index == 0>
               C.1.${evaluatingSection_index+1}&nbsp;&nbsp;${evaluatingSection.sectionName}:&nbsp;&nbsp;
				<#if sectionScoreMap??&&sectionScoreMap?size gt 0>
		   			<#list sectionScoreMap.keySet() as key>
		   				<#if key==evaluatingSection.id>		   		    
							&nbsp;&nbsp;<u>${sectionScoreMap.get(key)}分</u>&nbsp;&nbsp;&nbsp;
						</#if>
	      			</#list>
				</#if>
           </#if>	
	    </#list> 
	</td>
</tr>

		<#list elderlyEvaluatingRecord.evaluatingForm.evaluatingSections as evaluatingSection>	
	       <#if evaluatingSection_index != 0>
               <tr>
               	<td>C.1.${evaluatingSection_index+1}&nbsp;&nbsp;${evaluatingSection.sectionName}:&nbsp;&nbsp;
					<#if sectionScoreMap??&&sectionScoreMap?size gt 0>
		   				<#list sectionScoreMap.keySet() as key>
		   					<#if key==evaluatingSection.id>		   		    
								&nbsp;&nbsp;<u>${sectionScoreMap.get(key)}分</u>&nbsp;&nbsp;&nbsp;
							</#if>
	      				</#list>
					</#if>
				</td>
			</tr>
           </#if>	
	    </#list> 
<tr>
	<td text-align="center">C.2&nbsp;&nbsp;老年人能力初步等级&nbsp;&nbsp;</td>
	<td>
		${formPrimaryLevel}
	</td>
	
</tr>	
<tr>
	<td text-align="center">C.3&nbsp;&nbsp;等级变更条款&nbsp;&nbsp;</td>
	<td>1有认知障碍/痴呆，在原有能力级别上提高一个等级；<p/>
		2近30天内发生过2次及以上跌倒、噎食、自杀、走失者，在原有能力级别上提高一个等级；<p/>
		3处于昏迷状态者，直接评定为重度失能；<p/>
		4 若初步等级确定为“3重度失能”，则不考虑上述1-3中各情况对最终等级的影响，等级不再提高                                               
	</td>
</tr>
<tr>
	<td text-align="center">C.2&nbsp;&nbsp;老年人能力最终等级&nbsp;&nbsp;</td>
	<td>暂时不做这个等级评定</td>
</tr>
<tr>
	<td colspan="2">评估员签名<u>  ${elderlyEvaluatingRecord.operator} </u> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期 ${(elderlyEvaluatingRecord.evaluatingDate?string("yyyy年MM月dd日"))!''}<p/>
					信息提供者签名________ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期_____年___月___日
	</td>
</tr>
<tr>
	<td colspan="2">
${formScoreRule}
	</td>
</tr>
</table>
</div>
</form>
	

