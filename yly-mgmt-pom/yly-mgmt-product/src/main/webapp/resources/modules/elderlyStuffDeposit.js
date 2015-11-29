var elderlystuff_manager_tool = {
			add:function(){
				$('#putinDateTR').hide();
				$('#takeAlwayDateTR').hide();
				$('#addElderlyStuffDeposit').dialog({    
				    title: message("yly.elderly.stuffDeposit.add"),    
				    width: 500,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    modal:true,
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
				    	handler:function(){
							var validate = $('#addelderlyStuff_form').form('validate');
							if(validate){								
								$.ajax({
									url:"../elderlyStuffDeposit/add.jhtml",
									type:"post",
									data:$("#addelderlyStuff_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addElderlyStuffDeposit').dialog('close');
										$("#stuffDeposit-table-list").datagrid('reload');
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
							 $('#addElderlyStuffDeposit').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addelderlyStuff_form').show();
				    },
				    onClose:function(){
				    	 $('#addelderlyStuff_form').form('reset');
				    }
				});
			},
			remove:function(){
				listRemove('stuffDeposit-table-list','../elderlyStuffDeposit/delete.jhtml');
			},
			edit:function(){
				var _edit_row = $('#stuffDeposit-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');  
					return false;
				}
				var _dialog = $('#editStuffDeposit').dialog({    
				    title: message("yly.elderly.stuffDeposit.edit"),     
				    width: 500,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../elderlyStuffDeposit/detail.jhtml?id='+_edit_row.id+'&handle=edit',
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editStuffDeposit_form').form('validate');
							if(validate){
								$.ajax({
									url:"../elderlyStuffDeposit/update.jhtml",
									type:"post",
									data:$("#editStuffDeposit_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editStuffDeposit').dialog("close");
										$("#stuffDeposit-table-list").datagrid('reload');
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
							 $('#editStuffDeposit').dialog("close");
						}
				    }]
				});  
			}
	}
	
$(function(){
	$("#stuffDeposit-table-list").datagrid({
		title:message("yly.elderlyInfo.stuffDeposit.list"),
		fitColumns:true,
		toolbar:"#elderlystuff_manager_tool",
		url:'../elderlyStuffDeposit/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#editStuffDeposit').dialog({    
			    title: message("yly.common.detail"),    
			    width: 400,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../elderlyStuffDeposit/detail.jhtml?id='+rowData.id+'&handle=view',
			    buttons:[{
					text:message("yly.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editStuffDeposit').dialog("close");
					}
			    }]
			});   
		},
		columns:[    
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.elderlyInfo.stuffDeposit.name"),field:"name",width:30,align:'center',formatter:function(value,row,index){
			    	 return formatLongString(value,8);
			  }},
			  {title:message("yly.elderlyInfo.stuffDeposit.count"),field:"count",width:15,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.stuffDeposit.stuffNumer"),field:"stuffNumber",width:30,align:'center',formatter:function(value,row,index){
			    	 return formatLongString(value,8);
			  }},
		      {title:message("yly.elderlyInfo.stuffDeposit.status"),field:"stuffDepositStatus",width:20,align:'center',formatter: function(value,row,index){
					if(value=="PUT_IN"){
						 return message("yly.elderlyInfo.stuffDeposit.putin");
					}
					if(value=="TAKE_ALWAY"){
						return message("yly.elderlyInfo.stuffDeposit.takeaway");
					}
			  }},
		      {title:message("yly.elderlyInfo.stuffDeposit.inputDate"),field:"putinDate",width:20,align:'center',sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
			  }},
		      {title:message("yly.elderlyInfo.stuffDeposit.takeAwayDate"),field:"takeAlwayDate",width:20,align:'center',formatter:function(value,row,index){
		    	  if(value!=null){
		    		  return  new Date(value).Format("yyyy-MM-dd");
		    	  }					
			  }},
			  {title:message("yly.elderlyInfo.common.operator"),field:"operator",width:30,align:'center',formatter:function(value,row,index){
			    	 return formatLongString(value,8);
			  }},
		      {title:message("yly.elderlyInfo.stuffDeposit.elderlyName"),field:"elderlyInfoName",width:30,align:'center',formatter:function(value,row,index){
		    	  return formatLongString(row.elderlyInfo.name,8);
		      }},
		      {title:message("yly.elderlyInfo.stuffDeposit.remark"),field:"remark",width:50,align:'center',formatter:function(value,row,index){
			    	 return formatLongString(value,15);
			  }},
		   ]
		]

	});
	$("#elderlyStuff_search_btn").click(function(){
		  var _queryParams = $("#elderlyStuff_search_form").serializeJSON();
		  $('#stuffDeposit-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#stuffDeposit-table-list").datagrid('reload');
		})
})
//function formatLongString(str,len){
//	if(str.length > len){
//		return '<span title="'+str+'">'+str.substring(0,len)+"..."+'<span>'
//	}else{
//		return str;
//	}
//}
$(document).ready(function () {
	$('#stuffDepositStatus').combobox({  
		 onSelect: function(rec){   
			 var status = $('#stuffDepositStatus').combobox('getValue');  
			 if(status == 'PUT_IN'){
				 $('#putinDateTR').show();
				 $('#takeAlwayDateTR').hide();
			 }else if(status == 'TAKE_ALWAY'){
				 $('#takeAlwayDateTR').show();				 
				 $('#putinDateTR').hide();
			 }			 
	        }
	})
	}
)

