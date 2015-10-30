
$(function(){
	$("#donateRecord-table-list").datagrid({
		title:"捐赠记录",
		fitColumns:true,
		toolbar:"#donateRecord_manager_tool",
		url:'../donateRecord/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
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
		      {title:"捐赠人姓名",field:"donatorName",width:100,sortable:true},
		      {title:"捐赠人电话",field:"donatorPhone",width:100,sortable:true},
		      {title:"捐赠时间",field:"donateTime",width:100,sortable:true,formatter: function(value,row,index){
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
				      {title:"捐赠数",field:"donateAmount",width:10},
				      {title:"捐赠类型",field:"donateType",width:10,formatter: function(value,row,index){
				    	  if(value == "MONEY"){
				    	  		return "钱";
				    	  	}
				    	  if(value == "ITEM"){
				    	  		return "物";
				    	  	}
				      	}},
			      	 {title:"捐赠物品类型",field:"donateItemType",width:10,formatter: function(value,row,index){
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
				    title: message("yly.drugsInfo.add"),    
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
						text:'取消',
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
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editDonateRecord').dialog({    
				    title: '药品编辑',     
				    width: 700,    
				    height: 400,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../donateRecord/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
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
											text:"正在保存中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$.messager.show({
												title:'提示',
												msg:'保存成功',
												timeout:3000,
												showType:'slide'
											});
											$('#editDonateRecord').dialog("close");
											$("#donateRecord-table-list").datagrid('reload');
										}else{
											$.messager.alert('保存失败','未知错误','warning');
										}
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editDonateRecord').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				var _rows = $('#donateRecord-table-list').datagrid('getSelections');
				if(_rows == null){
					$.messager.alert('警告','请选择要删除的内容');  
				}else{
					var _ids =[];
					for(var i=0; i<_rows.length; i++){
							_ids.push(_rows[i].id);
						}
					if(_ids.length >0){
						$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
							if(r){
								$.ajax({
									url:"../donateRecord/delete.jhtml",
									type:"post",
									traditional: true,
									data:{"ids":_ids},
									beforeSend:function(){
										$.messager.progress({
											text:"正在删除中......"
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											$.messager.show({
												title:'提示',
												msg:'操作成功',
												timeout:3000,
												showType:'slide'
											});
											$("#donateRecord-table-list").datagrid('reload');
										}else{
											$.messager.alert('保存失败','未知错误','warning');
										}
									}
								});
							}
						})
					}
					
				}
			}
	};
	$("#search-btn").click(function(){
	  var _queryParams = $("#donateRecord-search-form").serializeJSON();
	  $('#donateRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#donateRecord-table-list").datagrid('reload');
	})
	
	 
	 
})
