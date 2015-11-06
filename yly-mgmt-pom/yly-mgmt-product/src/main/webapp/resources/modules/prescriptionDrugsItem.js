
$(function(){
	var prescriptionDrugsItemIndex = 0;
	prescriptionDrugsItem_manager_tool = {
			add:function(){
				
				var _drugsName=$("#name").val();
				var _drugsId=$("#drugsInfoID").val();
				var _singleDose=$("#singleDose").val();
				var _doseFrequency=$("#doseFrequency").val();
				var _doseFrequencyTxt=$("#doseFrequency").find("option:selected").text();
				var _drugUseMethod=$("#prescriptionDrugUseMethod").combobox("getValue");
				var _drugUseMethodTxt=$("#prescriptionDrugUseMethod").combobox("getText");
				console.log(_drugUseMethodTxt);
				var _medicationDays=$("#medicationDays").val();
				var _medicineTotal=$("#medicineTotal").val();
				var trHtml = 
				'<tr>\
					<th>药品名称:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line " type="text" value="'+_drugsName+'" validtype="length[0,15]" style="width:75px;"\/>\
						<input type="hidden" value ="'+_drugsId+'" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].drugsInfo.id" \/>\
						<\/td>\
					<th>单次用量:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line " type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].singleDose" value="'+_singleDose+'" style="width:10px;"\/>\
					<\/td>\
					<th>频度:<\/th>\
						<td>\
		    			<select id="doseFrequency" class="easyui-combobox input_text_line" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].doseFrequency" style="width:140px;">\
							<option value="'+_doseFrequency+'"  selected = "selected">'+_doseFrequencyTxt+'<\/option>\
					  	<\/select>\
						<\/td>\
					<th>药品用法:<\/th>\
					<td>\
						<td>\
		    			<select id="doseFrequency" class="easyui-combobox input_text_line" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].drugUseMethod.id" style="width:140px;">\
							<option value="'+_drugUseMethod+'"  selected = "selected">'+_drugUseMethodTxt+'<\/option>\
					  	<\/select>\
					<\/td>\
					<th>用药天数:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line" type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].medicationDays" value="'+_medicationDays+'" style="width:10px;"\/>\
					<\/td>\
					<th>药总数:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line" type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].medicineTotal" value="'+_medicineTotal+'" style="width:10px;"\/>\
					<\/td>\
				<\/tr>';
				prescriptionDrugsItemIndex++;
				
				$("#prescriptionDrugsAdd-table-list").append(trHtml);
			},
			edit:function(){
				var _edit_row = $('#drugs-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editDrugs').dialog({    
					title: message("yly.common.edit"),     
				    width: 700,    
				    height: 680,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../drugs/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editDrugs_form').form('validate');
							if(validate){
								$.ajax({
									url:"../drugs/update.jhtml",
									type:"post",
									data:$("#editDrugs_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editDrugs').dialog("close");
										$("#drugs-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editDrugs').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('drugs-table-list','../drugs/delete.jhtml');
			}
	};
	$("#search-btn").click(function(){
	  var _queryParams = {
			  drugName:$("#drugName").val(),
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#drugs-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#drugs-table-list").datagrid('reload');
	})
	
	 
	 
})
