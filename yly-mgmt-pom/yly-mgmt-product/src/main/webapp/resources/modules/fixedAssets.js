
$(function(){
	
	$("#fixedAssets-table-list").datagrid({
		title:"固定资产列表",
		fitColumns:true,
		toolbar:"#fixedAssets_manager_tool",
		url:'../fixedAssets/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#fixedAssetsDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 660,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../fixedAssets/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#fixedAssetsDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"资产编号",field:"assetNo",width:100,sortable:true},
		      {title:"资产名称",field:"assetName",width:100,sortable:true},
		      {title:"存放部门",field:"department",width:100,sortable:true,
	    	  formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.name;
		    	  }else{
		    		  return  value;
		    	  }
	      	  }},
		      {title:"资产数量",field:"assetCount",width:100,sortable:true},
		      
		      {title:"录入时间",field:"assetTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	fixedAssets_manager_tool = {
			add:function(){
				$('#addFixedAssets').dialog({
				    title: message("yly.drugsInfo.add"),    
				    width: 700,    
				    height: 550,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addFixedAssets_form').form('validate');
							if(validate){
								$.ajax({
									url:"../fixedAssets/add.jhtml",
									type:"post",
									data:$("#addFixedAssets_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addFixedAssets').dialog("close").form("reset");
											$("#fixedAssets_table-list").datagrid('reload');
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
							 $('#addFixedAssets').dialog("close").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#addFixedAssets_form').show();
				    	$("#department").combobox({    
						    valueField:'id',    
						    textField:'name',
						    cache: true,
						    url:'../department/findAllDepartments.jhtml'
						});
				    },
				
				});  
				 $('#addFixedAssets_form').show();
			},
			edit:function(){
				var _edit_row = $('#fixedAssets-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editFixedAssets').dialog({    
				    title: '固定资产编辑',     
				    width: 700,    
				    height: 550,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../fixedAssets/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editFixedAssets_form').form('validate');
							if(validate){
								$.ajax({
									url:"../fixedAssets/update.jhtml",
									type:"post",
									data:$("#editFixedAssets_form").serialize(),
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
											$('#editFixedAssets').dialog("close");
											$("#fixedAssets_table-list").datagrid('reload');
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
							 $('#editFixedAssets').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				var _rows = $('#fixedAssets-table-list').datagrid('getSelections');
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
									url:"../fixedAssets/delete.jhtml",
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
											$("#fixedAssets-table-list").datagrid('reload');
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
	  var _queryParams = {
			  drugName:$("#drugName").val(),
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#fixedAssets-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#fixedAssets-table-list").datagrid('reload');
	})
	
	 
	 
})
