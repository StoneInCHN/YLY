$(function(){
	
	$("#bedNurseChargeRecord-table-list").datagrid({
		title:message("yly.bedNurse.charge.record"),
		fitColumns:true,
		//toolbar:"#consultation_manager_tool",
		url:'../bedNurseChargeRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#bedNurseChargeRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 700,    
			    height: 500, 
			    cache: false,   
			    href:'../bedNurseChargeRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#bedNurseChargeRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      //老人姓名
		      {title:message("yly.common.elderlyname"),field:"elderlyInfo",width:30,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.name;
		    	  }
		      }},
		      //老人编号
		      {title:message("yly.elderlyinfo.identifier"),field:"elderlyInfo",width:70,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.identifier;
		    	  }
		      }},
		      //房间
		      {title:message("yly.common.bedRoom"),field:"elderlyInfo",width:120,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return value.bedLocation;
		    	  }
		      }},
		      //护理等级
		      {title:message("yly.common.nurseLevel"),field:"elderlyInfo",width:40,align:'center',sortable:true,formatter:function(value,row,index){
		    	  if(value!=null && value.nursingLevel!=null){
		    		  return value.nursingLevel.configValue;
		    	  }
		      }},
		      //床位费
		      {title:message("yly.charge.record.bed"),field:"bedAmount",width:20,align:'center',sortable:true},
		      //护理费
		      {title:message("yly.charge.record.nurse"),field:"nurseAmount",width:20,align:'center',sortable:true},
		      //总金额
		      {title:message("yly.charge.record.totalAmount"),field:"vistor",width:120,align:'center'},
		      //收款人
		      {title:message("yly.charge.record.operator"),field:"vistor",width:120,align:'center',sortable:true},
		      //收款时间段
		      {title:message("yly.charge.record.period"),field:"returnVisitDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM");
		    	  	}
				}},
		      //状态
		      {title:message("yly.charge.record.status"),field:"vistor",width:120,align:'center',sortable:true},
		      
		      {title:message("yly.common.gender"),field:"gender",width:30,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		      {title:message("yly.consultation.checkinintention"),field:"checkinIntention",width:80,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "CONFIRMED"){
		    	  		return  message("yly.consultation.checkinIntention.confirmed");
		    	  	}
		    	  	if(value == "WILL_TO_CHECKIN_STRONGLY"){
		    	  		return  message("yly.consultation.checkinIntention.will_to_checkin_strongly");
		    	  	}
		    		if(value == "WILL_TO_CHECKIN_NOT_STRONGLY"){
		    	  		return  message("yly.consultation.checkinIntention.will_to_checkin_not_strongly");
		    	  	}
		    		if(value == "WILL_NOT_CHECKIN"){
		    	  		return  message("yly.consultation.checkinIntention.will_not_checkin");
		    	  	}
		      	}}
		      
		   ]
		]

	});
	
	consultation_manager_tool = {
			add:function(){		
				$('#addConsultation').dialog({    
				    title: message("yly.consultation.add"),    
				    width: 700,    
				    height: 500,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addConsultation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../consultation/add.jhtml",
									type:"post",
									data:$("#addConsultation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addConsultation_form').form('reset');
										$('#addConsultation').dialog("close");
										$("#consultation-table-list").datagrid('reload');
										
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
							 $('#addConsultation').dialog("close");
						}
				    }]
				});  
				 $('#addConsultation_form').show();
			},
			edit:function(){
				var _edit_row = $('#consultation-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editConsultation').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 500,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../consultation/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editConsultation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../consultation/update.jhtml",
									type:"post",
									data:$("#editConsultation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editConsultation').dialog("close");
										$("#consultation-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editConsultation').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('consultation-table-list','../consultation/delete.jhtml');
			}
	}
	$("#consultation_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#consultation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#consultation-table-list").datagrid('reload');
	})
	
	 
	 
})