
$(function(){
	$("#donateRecord-table-list").datagrid({
		title:message("yly.donateRecord.list"),
		fitColumns:true,
		toolbar:"#donateRecord_manager_tool",
		url:'../donateRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#donateRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../donateRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#donateRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.donateRecord.donatorName"),field:"donatorName",width:100,sortable:true},
		      {title:message("yly.donateRecord.donatorPhone"),field:"donatorPhone",width:100,sortable:true},
		      {title:message("yly.donateRecord.donateTime"),field:"donateTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		],
		onClickRow : function (rowIndex, rowData){
			recordId =rowData.id;
			$("#donateDetail_manager_tool").show();
			$("#donateDetail-table-list").datagrid({
				fitColumns:true,
				pagination:true,
				loadMsg:message("yly.common.loading"),
				striped:true,
				url:'../donateDetail/donateDetails.jhtml',
				onBeforeLoad : function(param) {
			        param.id = rowData.id;// 参数
			    },
				columns:[
				   [
				      {field:'ck',checkbox:true},
				      {title:message("yly.donateDetail.donateAmount"),field:"donateAmount",width:10},
				      {title:message("yly.donateDetail.donateType"),field:"donateType",width:10,formatter: function(value,row,index){
				    	  if(value == "MONEY"){
				    	  		return message("yly.donateDetail.donateType.money");
				    	  	}
				    	  if(value == "ITEM"){
				    	  		return message("yly.donateDetail.donateType.item");
				    	  	}
				      	}},
			      	 {title:message("yly.donateDetail.donateItemType"),field:"donateItemType",width:10,formatter: function(value,row,index){
			      		if(value){
				    		  return  value.itemName;
				    	  }else{
				    		  return  value;
				    	  }
				    	 
				      	}},
				   ]
				]

			});
		},
	});
	
	donateRecord_manager_tool = {
			add:function(){
				$('#addDonateRecord').dialog({
				    title: message("yly.donateRecord.add"),    
				    width: 700,    
				    height: 400,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addDonateRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../donateRecord/add.jhtml",
									type:"post",
									data:$("#addDonateRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addDonateRecord').dialog("close").form("reset");
											$("#donateRecord-table-list").datagrid('reload');
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
							 $('#addDonateRecord').dialog("close").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#addDonateRecord_form').show();
				    },
				
				});  
				 $('#addDonateRecord_form').show();
			},
			edit:function(){
				var _edit_row = $('#donateRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editDonateRecord').dialog({    
				    title: message("yly.common.edit"),     
				    width: 700,    
				    height: 400,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../donateRecord/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editDonateRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../donateRecord/update.jhtml",
									type:"post",
									data:$("#editDonateRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editDonateRecord').dialog("close");
											$("#donateRecord-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editDonateRecord').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('donateRecord-table-list','../donateRecord/delete.jhtml');
			}
	};
	$("#search-btn").click(function(){
	  var _queryParams = $("#donateRecord-search-form").serializeJSON();
	  $('#donateRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#donateRecord-table-list").datagrid('reload');
	})
	
	 
	 
})
