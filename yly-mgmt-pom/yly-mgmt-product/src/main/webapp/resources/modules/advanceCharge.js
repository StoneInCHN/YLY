var advanceCharge_manager_tool = {
			add:function(){		
				$('#addAdvanceCharge').dialog({    
				    title: message("yly.charge.advance.add"),    
				    width: 450,    
				    height: 400,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addAdvanceCharge_form').form('validate');
							if(validate){
								$.ajax({
									url:"../advanceCharge/add.jhtml",
									type:"post",
									data:$("#addAdvanceCharge_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addAdvanceCharge').dialog("close");
											$("#advanceCharge_table_list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addAdvanceCharge').dialog("close");
							 $('#addAdvanceCharge_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addAdvanceCharge_form').show();
				    },
				    onClose:function(){
				    	 //$('#addAdvanceCharge_form').form('reset');
				    }
				});  
			}
	}

$(function(){
	
	$("#advanceCharge_table_list").datagrid({
		//title:message("yly.advance.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../advanceCharge/chargeAccounts.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$("#advanceChargeDetails_search_form")[0].reset();
			var nameText = $("#advanceChargeDetails_search_form #realName");
			nameText.textbox('setValue',rowData.name);
			var _elderlyInfoParams={realName:rowData.name};
			$('#advanceChargeDetails_table_list').datagrid('options').queryParams = _elderlyInfoParams;  
			$("#advanceChargeDetails_table_list").datagrid({url:'../advanceCharge/chargeList.jhtml'});
			//$("#advanceChargeDetails_table_list").datagrid('reload');
			
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"name",width:30,align:'center',sortable:true},
		      //老人编号
		      {title:message("yly.elderlyinfo.identifier"),field:"identifier",width:40,align:'center',sortable:true},
		      //房间
		      {title:message("yly.common.bedRoom"),field:"bedLocation",width:50,align:'center'},
		      //护理等级
		      {title:message("yly.common.nurseLevel"),field:"nursingLevel",width:30,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.configValue;
		    	  }else{
		    		  return null;
		    	  }
		      }},
		      //预存款总额(元)
		      {title:message("yly.charge.record.advanceChargeAmount"),field:"advanceChargeAmount",width:50,align:'center',sortable:true}
		   ]
		]

	});
	
	$("#advanceCharge_search_btn").click(function(){
	  var _queryParams = $("#advanceCharge_search_form").serializeJSON();
	  $('#advanceCharge_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#advanceCharge_table_list").datagrid('reload');
	});
	
	
	$("#advanceChargeDetails_table_list").datagrid({
		//title:message("yly.advance.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		//url:'../advanceCharge/chargeList.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#advanceChargeDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../advanceCharge/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#advanceChargeDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return row.elderlyInfo.name;
		      }},
		      //收支类型
		      {title:message("yly.charge.record.budgetType"),field:"budgetType",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	    if(value == "INCOME"){
		    	  		return "<font color=#7CCD7C>"+message("yly.charge.record.budgetType.income")+"</font>";
		    	  	}
		    	  	if(value == "COST"){
		    	  		return "<font color=#FF0000>"+message("yly.charge.record.budgetType.cost")+"</font>";
		    	  	}
				}},
		      //金额
		      {title:message("yly.charge.record.amount"),field:"advanceAmount",width:25,align:'center',sortable:true},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"operator",width:30,align:'center',sortable:true},
		      //缴费时间
		      {title:message("yly.charge.record.payTime"),field:"payTime",width:35,align:'center',sortable:true,formatter: function(value,row,index){
		    	    if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd hh:mm");
		    	  	}
				}},
		   ]
		]

	});
	
	$("#advanceChargeDetails_search_btn").click(function(){
	 
	  var _queryParams = $("#advanceChargeDetails_search_form").serializeJSON();
	  $('#advanceChargeDetails_table_list').datagrid('options').queryParams = _queryParams; 
	  $("#advanceChargeDetails_table_list").datagrid({url:'../advanceCharge/chargeList.jhtml'});
	  //$("#advanceChargeDetails_table_list").datagrid('reload');
	})
	
})