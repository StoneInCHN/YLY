
$(function(){
	
	$("#prescription-table-list").datagrid({
		title:"老人药方",
		fitColumns:true,
		toolbar:"#prescription_manager_tool",
		url:'../prescription/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#prescriptionDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 600, 
			    cache: false,
			    modal: true,
			    href:'../prescription/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#prescriptionDetail').dialog("close");
					}
			    }]
			});   
		},
		onClickRow : function (rowIndex, rowData){
			var recordId =rowData.id;
			$("#prescriptionDrugs-table-list").datagrid({
				fitColumns:true,
				pagination:false,
				loadMsg:message("yly.common.loading"),
				striped:true,
				//url:'../donateDetail/donateDetails.jhtml',
				onBeforeLoad : function(param) {
			        param.id = rowData.id;// 参数
			    },
				columns:[
				   [
				      {field:'ck',checkbox:true},
				      {title:'药品名称',field:"donateAmount",width:10},
				      {title:'药品类型',field:"donateType",width:10}
				   ]
				]

			});
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:'姓名',field:"elderlyInfo",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }else {
			    		  return  value;
			    	  }
						
			      	}},
		      {title:'处方类型',field:"prescriptionType",width:100,sortable:true,
	    	  formatter: function(value,row,index){
		    	  if(value == 'CHINESE_MEDICINE'){
		    		  return  '中药';
		    	  }else if(value == 'WESTEN_MEDICINE'){
		    		  return  '西药';
		    	  }
					
		      	}},
		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      }
		   ]
		]

	});

	prescription_manager_tool = {
			add:function(){
				$('#addPrescription').dialog({
				    title: '添加处方',   
				    width: 900,    
				    height: 500,
				    iconCls:'icon-mini-add',
				    href:"../prescription/add.jhtml",  
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addPrescription_form').form('validate');
							if(validate){
								$.ajax({
									url:"../prescription/save.jhtml",
									type:"post",
									data:$("#addPrescription_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addPrescription').dialog("close");
											$("#prescription-table-list").datagrid('reload');
											$('#addPrescription_form').form('reset');
										}else{
											alertErrorMsg();
										}
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addPrescription').dialog("close");
							 $("#addPrescription_form").form("reset");
						}
				    }],
				    onLoad:function(){
				    	$('#addPrescription_form').show();
				    	$(".hid").hide();
				    	$("#addPrescriptionType").combobox({
				    		onChange: function (n,o) {
				    			if(n == "WESTEN_MEDICINE")
				    				$(".hid").hide();
				    			else{
				    				$(".hid").show();
				    			}
				    		}
				    	});
				    	$("#addPrescriptionUseMethod").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'DRUGSMETHOD';// 参数
						    }
						});
				    }
				
				});  
				 $('#addPrescription_form').show();
			},
			edit:function(){
				var _edit_row = $('#prescription-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editPrescription').dialog({    
					title: message("yly.common.edit"),     
				    width: 900,    
				    height: 500,  
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../prescription/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editPrescription_form').form('validate');
							if(validate){
								$.ajax({
									url:"../prescription/update.jhtml",
									type:"post",
									data:$("#editPrescription_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editPrescription').dialog("close");
										$("#prescription-table-list").datagrid('reload');
										$('#editPrescription_form').form('reset');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editPrescription').dialog("close");
							 $('#editPrescription_form').form('reset');
						}
				    }],
				    onLoad:function(){
				    	var itemSize=$("#drugsItemSize").val();
				    	
				    	$(".edithid").hide();
				    	$("#editPrescriptionType").combobox({
				    		onChange: function (n,o) {
				    			if(n == "WESTEN_MEDICINE")
				    				$(".edithid").hide();
				    			else{
				    				$(".edithid").show();
				    			}
				    		}
				    	});
				    	if(itemSize>0){
				    		$("#editPrescriptionType").combobox("readonly",true);
				    	};
//				    	$("#editPrescriptionUseMethod").combobox({    
//						    valueField:'id',    
//						    textField:'configValue',
//						    cache: true,
//						    url:'../systemConfig/findByConfigKey.jhtml',
//						    onBeforeLoad : function(param) {
//						        param.configKey = 'DRUGSMETHOD';// 参数
//						    }
//						});
				    	$("#editPrescriptionUseMethod").combobox("setValue",$("#editPrescriptionUseMethod").attr('data-value'));
				    	
				    	$("[use-method='editPrescriptionDrugsMethod']").combobox({
				    		valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'DRUGSMETHOD';// 参数
						    }
				    	});
				    	for(var i=0 ;i < itemSize; i++){
				    		$("#editPrescriptionDrugsItems"+i+"_drugUseMethod").combobox("setValue",$("#editPrescriptionDrugsItems"+i+"_drugUseMethod").attr('data-value'));
				    	}
				    },
				    onOpen:function(){
				    	
				    },
				    onClose:function(){
				    	$("#drugsItemSize").val(0);
				    }
				});  
			},
			addDrgus: function(){
				if($("#addPrescriptionType").combobox('getValue') == ""){
		    		alert("请选择处方类型！")
		    		return ;
		    	}
				$('#addPrescriptionDrugs').dialog({
				    title: '选择药品',   
				    width: 650,    
				    height: 300,
				    iconCls:'icon-mini-add',
				    href:"../drugs/drugsSearch.jhtml",  
				    cache: false, 
				    buttons:[{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addPrescriptionDrugs').dialog("close");
						}
				    }],
				    onLoad:function(){
				    	var type=$('#addPrescriptionType').combo('getValue');
					    	$("#drgusAll-table-list").datagrid({
					    		title:message("yly.drugsInfo.list"),
					    		fitColumns:true,
					    		toolbar:"#drugs_manager_tool",
					    		url:'../drugs/drugsSearch.jhtml',  
					    		pagination:true,
					    		loadMsg:message("yly.common.loading"),
					    		striped:true,
					    		onDblClickRow : function (rowIndex, rowData){
					    			$('#prescriptionDrugs').dialog({
									    title: '药品',   
									    width: 440,    
									    height: 230,
									    iconCls:'icon-mini-add',
									    href:"../prescriptionDrugsItem/prescriptionDrugs.jhtml?drugsId="+rowData.id,  
									    cache: false, 
									    buttons:[{
									    	text:message("yly.common.save"),
									    	iconCls:'icon-save',
											handler:function(){
												var validate = $('#addPrescriptionDrugs_form').form('validate');
												if(validate){
													//call add prescription drugs
													prescriptionDrugsItem_manager_tool.add('add');
													$('#prescriptionDrugs').dialog("close");
												};
											}
										},{
											text:message("yly.common.cancel"),
											iconCls:'icon-cancel',
											handler:function(){
												 $('#prescriptionDrugs').dialog("close");
												 $('#addPrescriptionDrugs_form').form("reset")
											}
									    }],
									    onLoad:function(){
									    	$("#prescriptionDrugUseMethod").combobox({    
											    valueField:'id',    
											    textField:'configValue',
											    cache: true,
											    url:'../systemConfig/findByConfigKey.jhtml',
											    onBeforeLoad : function(param) {
											        param.configKey = 'DRUGSMETHOD';// 参数
											    }
											});
									    	$(".hidCHI").hide();
									    	if(type == 'WESTEN_MEDICINE'){
									    		$(".hidCHI").show();
									    	}else if(type == 'WESTEN_MEDICINE'){
									    		$(".hidCHI").hide();
									    	} 
									    }
					    			});
					    			$('#addPrescriptionDrugs').dialog("close");
					    			$('#addPrescriptionDrugs_form').form("reset")
					    		},
					    		columns:[
					    		   [
					    		      {field:'ck',checkbox:true},
					    		      {title:message("yly.drugsInfo.name"),field:"name",width:100,sortable:true},
					    		      {title:message("yly.drugsInfo.alias"),field:"alias",width:100,sortable:true},
					    		      {title:message("yly.drugsInfo.drugCategory"),field:"drugCategory",width:100,sortable:true,
					    	    	  formatter: function(value,row,index){
					    		    	  if(value){
					    		    		  return  value.configValue;
					    		    	  }else{
					    		    		  return  value;
					    		    	  }
					    					
					    		      	}},
					    		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					    					return new Date(value).Format("yyyy-MM-dd");
					    				}
					    		      },
					    		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
					    					return new Date(value).Format("yyyy-MM-dd");
					    				}},
					    		   ]
					    		]
	
					    	});
				    	$("#prescriptionAddDrugs-search-btn").click(function(){
				    	  var _queryParams = {
				    			  allDrugName:$("#allDrugName").val(),
				    			  allDrugsBeginDate:$("#allDrugsBeginDate").val(),
				    			  allDrugsEndDate:$("#allDrugsEndDate").val()
				    	  }
				    	  $('#drgusAll-table-list').datagrid('options').queryParams = _queryParams;  
				    	  $("#drgusAll-table-list").datagrid('reload');
				    	});	
				    },
				
				});  
			},
			editAddDrgus:function(){
				$('#addPrescriptionDrugs').dialog({
				    title: '选择药品',   
				    width: 650,    
				    height: 300,
				    iconCls:'icon-mini-add',
				    href:"../drugs/drugsSearch.jhtml",  
				    cache: false, 
				    buttons:[{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addPrescriptionDrugs').dialog("close");
						}
				    }],
				    onLoad:function(){
				    	var type=$('#editPrescriptionType').combo('getValue');
					    	$("#drgusAll-table-list").datagrid({
					    		title:message("yly.drugsInfo.list"),
					    		fitColumns:true,
					    		toolbar:"#drugs_manager_tool",
					    		url:'../drugs/drugsSearch.jhtml',  
					    		pagination:true,
					    		loadMsg:message("yly.common.loading"),
					    		striped:true,
					    		onDblClickRow : function (rowIndex, rowData){
					    			$('#prescriptionDrugs').dialog({
									    title: '药品',   
									    width: 500,    
									    height: 300,
									    iconCls:'icon-mini-add',
									    href:"../prescriptionDrugsItem/prescriptionDrugs.jhtml?drugsId="+rowData.id,  
									    cache: false, 
									    buttons:[{
									    	text:message("yly.common.save"),
									    	iconCls:'icon-save',
											handler:function(){
												var validate = $('#addPrescriptionDrugs_form').form('validate');
												if(validate){
													//call add prescription drugs
													prescriptionDrugsItem_manager_tool.add('edit');
													$('#prescriptionDrugs').dialog("close");
												};
											}
										},{
											text:message("yly.common.cancel"),
											iconCls:'icon-cancel',
											handler:function(){
												 $('#prescriptionDrugs').dialog("close");
												 $('#addPrescriptionDrugs_form').form('reset');
											}
									    }],
									    onLoad:function(){
									    	$("#prescriptionDrugUseMethod").combobox({    
											    valueField:'id',    
											    textField:'configValue',
											    cache: true,
											    url:'../systemConfig/findByConfigKey.jhtml',
											    onBeforeLoad : function(param) {
											        param.configKey = 'DRUGSMETHOD';// 参数
											    }
											});
									    	$(".hidCHI").hide();
									    	if(type == 'WESTEN_MEDICINE'){
									    		$(".hidCHI").show();
									    	}else if(type == 'WESTEN_MEDICINE'){
									    		$(".hidCHI").hide();
									    	} 
									    }
					    			});
					    			$('#addPrescriptionDrugs').dialog("close");
					    			$('#addPrescriptionDrugs_form').form('reset');
					    		},
					    		columns:[
					    		   [
					    		      {field:'ck',checkbox:true},
					    		      {title:message("yly.drugsInfo.name"),field:"name",width:100,sortable:true},
					    		      {title:message("yly.drugsInfo.alias"),field:"alias",width:100,sortable:true},
					    		      {title:message("yly.drugsInfo.drugCategory"),field:"drugCategory",width:100,sortable:true,
					    	    	  formatter: function(value,row,index){
					    		    	  if(value){
					    		    		  return  value.configValue;
					    		    	  }else{
					    		    		  return  value;
					    		    	  }
					    					
					    		      	}},
					    		      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					    					return new Date(value).Format("yyyy-MM-dd");
					    				}
					    		      },
					    		      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
					    					return new Date(value).Format("yyyy-MM-dd");
					    				}},
					    		   ]
					    		]
	
					    	});
				    	$("#prescriptionAddDrugs-search-btn").click(function(){
				    	  var _queryParams = {
				    			  allDrugName:$("#allDrugName").val(),
				    			  allDrugsBeginDate:$("#allDrugsBeginDate").val(),
				    			  allDrugsEndDate:$("#allDrugsEndDate").val()
				    	  }
				    	  $('#drgusAll-table-list').datagrid('options').queryParams = _queryParams;  
				    	  $("#drgusAll-table-list").datagrid('reload');
				    	});	
				    }
				
				});  
			
			},
			remove:function(){
				listRemove('prescription-table-list','../prescription/delete.jhtml');
			}
	};
	$("#prescription-search-btn").click(function(){
		  var _queryParams = $("#prescription-search-form").serializeJSON();
		  $('#prescription-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#prescription-table-list").datagrid('reload');
		})
})
