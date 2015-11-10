
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
				var _medicationDays=$("#prescriptionDrugMedicationDays").val();
				var _medicineTotal=$("#prescriptionDrugMedicineTotal").val();
				console.log(_medicationDays);
				var drugNameHtml = 
				'<tr>\
					<th>药品名称:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line " type="text" value="'+_drugsName+'" validtype="length[0,15]" style="width:60px;"\/>\
						<input type="hidden" value ="'+_drugsId+'" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].drugsInfo.id" \/>\
						<\/td>';
					var drugWestHtml='\
					<th>单次用量:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line " type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].singleDose" value="'+_singleDose+'" style="width:15px;"\/>\
					<\/td>\
					<th>频度:<\/th>\
						<td>\
		    			<input  class="easyui-combobox input_text_line" name = "prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].doseFrequency" id="prescriptionDrugsItems'+prescriptionDrugsItemIndex+'_doseFrequency" style="width:140px;">\
					  	<\/input>\
						<\/td>\
					<th>药品用法:<\/th>\
					<td>\
						<td>\
					  	<input class="easyui-combobox" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].drugUseMethod.id" id="prescriptionDrugsItems'+prescriptionDrugsItemIndex+'_drugUseMethod" style="width:80px;"/>\
					<\/td>\
					<th>用药天数:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line" type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].medicationDays" value="'+_medicationDays+'" style="width:15px;"\/>\
					<\/td>';
					
					var totalHtml='<th>药总数:<\/th>\
					<td>\
						<input class="easyui-textbox input_text_line" type="text" name="prescriptionDrugsItems['+prescriptionDrugsItemIndex+'].medicineTotal" value="'+_medicineTotal+'" style="width:15px;"\/>\
					<\/td>\
						<\/tr>';
				
				var type=$('#prescriptionType').combo('getValue');
				if(type == 'WESTEN_MEDICINE'){
					$("#prescriptionDrugsAdd-table-list").append(drugNameHtml+drugWestHtml+totalHtml);
					$("#prescriptionDrugsItems"+prescriptionDrugsItemIndex+"_drugUseMethod").combobox({    
					    valueField:'id',    
					    textField:'configValue',
					    cache: true,
					    url:'../systemConfig/findByConfigKey.jhtml',
					    onBeforeLoad : function(param) {
					        param.configKey = 'DRUGSMETHOD';// 参数
					    }
					});
					$("#prescriptionDrugsItems"+prescriptionDrugsItemIndex+"_doseFrequency").combobox({    
					    valueField:'label',    
					    textField:'value',
					    cache: true,
					    data: [{
							label: 'Qd',
							value: message("yly.drugsInfo.doseFrequency.Qd")
						},{
							label: 'Bid',
							value: message("yly.drugsInfo.doseFrequency.Bid")
						},{
							label: 'Tid',
							value: message("yly.drugsInfo.doseFrequency.Tid")
						},{
							label: 'Qid',
							value: message("yly.drugsInfo.doseFrequency.Qid")
						},{
							label: 'Q2h',
							value: message("yly.drugsInfo.doseFrequency.Q2h")
						},{
							label: 'Q4h',
							value: message("yly.drugsInfo.doseFrequency.Q4h")
						}]
					});
					$("#prescriptionDrugsItems"+prescriptionDrugsItemIndex+"_drugUseMethod").combobox("setValue",_drugUseMethod);
					$("#prescriptionDrugsItems"+prescriptionDrugsItemIndex+"_doseFrequency").combobox("setValue",_doseFrequency);
				}else{
					$("#prescriptionDrugsAdd-table-list").append(drugNameHtml+totalHtml);
				}
				
				if( prescriptionDrugsItemIndex > 0){
					$('#prescriptionType').combo('readonly');
				};
				prescriptionDrugsItemIndex++;
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
				if( prescriptionDrugsItemIndex < 0){
					$("#prescriptionType").attr("readonly","readonly");
				};
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
