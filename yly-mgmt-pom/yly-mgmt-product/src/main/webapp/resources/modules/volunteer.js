$(function(){
	
	$("#volunteer-table-list").datagrid({
		title:message("yly.volunteer.record"),
		fitColumns:true,
		toolbar:"#volunteer_manager_tool",
		url:'../volunteer/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#volunteerDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 600,    
			    height: 550, 
			    cache: false,   
			    href:'../volunteer/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#volunteerDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.volunteer.name"),field:"volunteerName",width:100,sortable:true},
		      {title:message("yly.volunteer.type"),field:"volunteerType",width:100,sortable:true, formatter: function(value,row,index){
		    	  	if(value == "PERSONAL"){
		    	  		return  message("yly.volunteer.personal");
		    	  	}
		    	  	if(value == "ORGANIZATION"){
		    	  		return  message("yly.volunteer.organization");
		    	  	}
		      	}},
		      {title:message("yly.volunteer.idCard"),field:"idcard",width:100,sortable:true},
		      {title:message("yly.volunteer.email"),field:"email",width:100,sortable:true},
		      {title:message("yly.volunteer.address"),field:"address",width:100,sortable:true},
		      {title:message("yly.volunteer.mobile"),field:"mobile",width:100,sortable:true},
		      {title:message("yly.volunteer.activityTime"),field:"activityTime",width:100,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }}
		   ]
		]

	});
	 
	 

	volunteer_manager_tool = {
			add:function(){		
				$('#addvolunteer').dialog({    
				    title: message("yly.volunteer.add"),    
				    width: 600,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addvolunteer_form').form('validate');
							if(validate){
								$.ajax({
									url:"../volunteer/add.jhtml",
									type:"post",
									data:$("#addvolunteer_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addvolunteer_form').form('reset');
										$('#addvolunteer').dialog("close");
										$("#volunteer-table-list").datagrid('reload');
								
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							}
							else{
								alert("validate fail");
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addvolunteer').dialog("close");
						}
				    }]
				});  
				 $('#addvolunteer_form').show();
			},
			edit:function(){
				var _edit_row = $('#volunteer-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editvolunteer').dialog({    
				    title: message("yly.common.edit"),     
				    width: 600,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../volunteer/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editvolunteer_form').form('validate');
							if(validate){
								$.ajax({
									url:"../volunteer/update.jhtml",
									type:"post",
									data:$("#editvolunteer_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editvolunteer').dialog("close");
										$("#volunteer-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editvolunteer').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				var _edit_row = $('#volunteer-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.deleteRow"));  
					return false;
				}
				listRemove('volunteer-table-list','../volunteer/delete.jhtml');
			}
	}
	$("#volunteer_search_btn").click(function(){
	  var _queryParams = $("#volunteer_search_form").serializeJSON();
	  $('#volunteer-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#volunteer-table-list").datagrid('reload');
	})
	
})