
$(function(){
	
	prescriptionDrugsItemIndex = 0;
	prescriptionDrugsItem_manager_tool = {
			
			remove:function(id){
				if(prescriptionDrugsItemIndex>0){
					$("#drugsItem"+id).remove();
					prescriptionDrugsItemIndex--;
					if( prescriptionDrugsItemIndex <= 0){
						$("#prescriptionType").combobox("readonly",false);
					};
				}
			},
			
	};
	$("#search-btn").click(function(){
	  var _queryParams = {
			  drugName:$("#drugName").val(),
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val()
	  }
	  $('#drugs-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#drugs-table-list").datagrid('reload');
	})
	
	 
	 
})
