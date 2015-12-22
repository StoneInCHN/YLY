$(function(){
	
	$("#bookingRegistration-table-list").datagrid({
		title:message("yly.bookingRegistration.record"),
		fitColumns:true,
		toolbar:"#bookingRegistration_manager_tool",
		url:'../bookingRegistration/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.bookingRegistration.peopleWhoBooked"),field:"peopleWhoBooked",width:40,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"elderlyName",width:40,align:'center',sortable:true},
		      {title:message("yly.common.phonenumber"),field:"phoneNumber",width:50,align:'center',sortable:true},
		      {title:message("yly.bookingRegistration.bookingCheckInDate"),field:"bookingCheckInDate",width:60,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		    	  	}
		      	}},
		      {title:message("yly.common.idcard"),field:"idcard",width:110,align:'center',sortable:true},
		      {title:message("yly.bookingRegistration.intentRoomType"),field:"intentRoomType",width:80,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.configValue;
		    	  }else{
		    		  return  value;
		    	  }
					
		      	}},
		      {title:message("yly.common.gender"),field:"gender",width:30,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		    	{title:message("yly.common.remark"),field:"remark",width:100,align:'center',sortable:true,formatter: function(value,row,index){
					if(value && value.length >15){
						var abValue =  value.substring(0,10) +"...";
						var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
						return content;
					}else{
						return value
					}
		      	}}
		   ]
		]

	});
	
	bookingRegistration_manager_tool = {
			add:function(){		
				$('#addBookingRegistration').dialog({    
				    title: message("yly.bookingRegistration.add"),    
				    width: 800,    
				    height: 400,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addBookingRegistration_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bookingRegistration/add.jhtml",
									type:"post",
									data:$("#addBookingRegistration_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addBookingRegistration_form').form('reset');
										$('#addBookingRegistration').dialog("close");
										$("#bookingRegistration-table-list").datagrid('reload');
										
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
							 $('#addBookingRegistration').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addBookingRegistration_form').show();
				    	$("#bookingRegistrationAddRoomType").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'ROOMTYPE';
						    }
						});
				    },
				});  
				 $('#addBookingRegistration_form').show();
			},
			edit:function(){
				var _edit_row = $('#bookingRegistration-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editBookingRegistration').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 400,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../bookingRegistration/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editBookingRegistration_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bookingRegistration/update.jhtml",
									type:"post",
									data:$("#editBookingRegistration_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editBookingRegistration').dialog("close");
										$("#bookingRegistration-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editBookingRegistration').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('bookingRegistration-table-list','../bookingRegistration/delete.jhtml');
			}
	}
	$("#bookingRegistration_search_btn").click(function(){
	  var _queryParams = $("#bookingRegistration_search_form").serializeJSON();
	  $('#bookingRegistration-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#bookingRegistration-table-list").datagrid('reload');
		//隐藏域用于标记上次使用过的查询条件 
	  $("#peopleWhoBookedHidden").val($("#peopleWhoBooked").val());
	  $("#elderlyNameHidden").val($("#elderlyName").val());
	  $("#searchRoomTypeValueHidden").val($("#bookingRegistrationSearchRoomType").combobox('getText'));
	  $("#bookingCheckInBeginDateHidden").val($("#bookingCheckInDateBeginDate").val());
	  $("#bookingCheckInEndDateHidden").val($("#bookingCheckInDateDateEndDate").val());
	})
	/**
	 * 加载查询项里面的数据
	 */
  	$("#bookingRegistrationSearchRoomType").combobox({    
	    valueField:'id',    
	    textField:'configValue',
	    cache: true,
	    url:'../systemConfig/findByConfigKey.jhtml',
	    onBeforeLoad : function(param) {
	        param.configKey = 'ROOMTYPE';
	    }
	});
})