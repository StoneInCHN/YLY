
$(function(){
	
	$("#operationLog-table-list").datagrid({
		title:"操作日志列表",
		fitColumns:true,
		toolbar:"#operationLog_manager_tool",
		url:'../operationLog/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		
		columns:[
		   [
		      {title:"操作员",field:"operator",width:100,sortable:true},
		      {title:"操作",field:"operation",width:100,sortable:true},
		      {title:"操作内容",field:"content",width:100,sortable:true},
		      {title:"操作IP",field:"ip",width:100,sortable:true},
	    	  {title:"操作时间",field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		     
		   ]
		]

	});

	
	$("#search-btn").click(function(){
	  var _queryParams = {
			  drugName:$("#drugName").val(),
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#fixedAssets-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#fixedAssets-table-list").datagrid('reload');
	})
	
	 
	 
})
