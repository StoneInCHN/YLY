	<style type="text/css">
		.left{
			width:17%;
			float:left;
			border:1px solid #0285b3;
			margin-left:10px;
			margin-top:10px;
			padding-top:10px;
			background: white;
			border: 0 none;
			border-radius: 3px;
			box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.2);
			box-sizing: border-box;
		}
		.left table{
			background:#E0ECFF;
		}
		.left td{
			background:#0285b3;
		}
		.right{
			float:right;
			width:79%;
			border:1px solid #0285b3;
			margin-right:10px;
			margin-top:10px;
			padding-top:10px;
			background: white;
			border: 0 none;
			border-radius: 3px;
			box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.2);
			box-sizing: border-box;
		}
		.right table{
			background:#E0ECFF;
			width:100%;
		}
		.right td{
			background:#fafafa;
			text-align:left;
			padding:2px;
		}
		.right td{
			background:#E0ECFF;
		}
		.right .drop{
			width:100%;
			height:auto;
			margin:20px;
			padding-bottom:40px;
		}
		.right .over{
			background:#D2D2D2;
		}
		.hiddenAddItem{
			display:none
		}
		.item{
			text-align:center;
			border:2px solid #0285b3;
			background:#fafafa;
			width:100px;
			border-radius: 4px;
			box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.2);
			text-align:center;
		}
		.assigned{
			border:1px solid #BC2A4D;
		}
		
	</style>
	<script type="text/javascript">

	</script>
    <script type="text/javascript" src="${base}/resources/modules/createEvaluatingForm.js"></script>

<div style="width:100%;height:100%">
	<div id="leftDiv" class="left">
	<p style="text-align:center;font-size:150%">添加题库</p>			
	<div id="accordionDiv">

	</div>
	<a href="javascript:;" id="additem" class="btn bule-color" onclick="addSection()"><i class="fa fa-plus-square-o fa-2x"/> 添加模块</a>
	</div>
	<div class="right">
		<p style="font-size:150%;margin-left:25px">评估表名：<input type="text" class="easyui-textbox"  data-options="prompt:'请输入评估表名...',required:true,width:450"   id="formName" validtype="length[0,30]" /></p>
		<div id="targetFormDiv" class="drop">
		

		
		</div>
	</div>
</div>
<div id="addEvaluatingItem">
<form id="addEvaluatingItem_form" method="post" class="form-table">   
<div style="margin:10px"><p style="font-size:150%">题目：
		<input type="text" class="easyui-textbox"   validtype="length[0,120]" id="itemName" name="itemName" data-options="prompt:'请输入题目...',required:true,multiline:true,height:40,width:800" /></p>
</div>

	<table id="optionList"></table>
	<div style="margin:10px 0">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="insert()">添加选项</a>
	</div>
	<div id="hiddenDiv"></div>
</form>
</div>
<div id="addEvaluatingSection">
<form id="addEvaluatingSection_form" method="post" class="form-table">   
<div style="margin:10px">
	<p style="font-size:150%">模块名称：
		<input type="text" class="easyui-textbox"   validtype="length[0,50]" id="itemName" name="sectionName" data-options="prompt:'请输入模块名称...',required:true,multiline:true,height:40,width:400" />
	</p>
	<p style="font-size:150%">评分规则：
		<input type="text" class="easyui-textbox"   validtype="length[0,50]" id="itemName" name="sectionName" data-options="prompt:'请输入模块评分规则...',required:false,multiline:true,height:100,width:400" />
	</p>
		
</div>
</form>
</div>
<div id="test" style="display:none"><div  class="easyui-accordion"><div title="sectionName" iconCls="icon-ok" style="overflow:auto;padding:10px;"></div></div></div>

