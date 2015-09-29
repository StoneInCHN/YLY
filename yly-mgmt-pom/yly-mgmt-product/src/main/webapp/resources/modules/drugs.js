var drugs_manager_tool = {
			add:function(){		
				$('#addDrugs').dialog({    
<<<<<<< HEAD
				    title: message("yly.drugsInfo.add"),    
				    width: 380,    
				    height: 270,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
=======
				    title: '添加药品',    
				    width: 380,    
				    height: 270,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    href:'../drugs/add.jhtml',
				    buttons:[{
				    	text:'保存',
>>>>>>> branch 'master' of https://github.com/StoneInCHN/YLY.git
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addDrugs_form').form('validate');
							if(validate){
								$.ajax({
									url:"../drugs/save.jhtml",
									type:"post",
									data:$("#addDrugs_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在添加中......"
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
											$('#addDrugs').dialog("close").form("reset");
											$("#drug_table-list").datagrid('reload');
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
							 $('#addDrugs').dialog("close").form("reset");
						}
				    }]
				});  
				 $('#addDrugs').show();
			},
			edit:function(){
				var _edit_row = $('#drugs-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行');  
					return false;
				}
				var _dialog = $('#editDrugs').dialog({    
				    title: '药品编辑',     
				    width: 400,    
				    height: 270,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../drugs/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
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
											$('#editDrugs').dialog("close");
											$("#drugs_table-list").datagrid('reload');
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
							 $('#editDrugs').dialog("close").form("reset");
						}
				    }]
				});  
			},
			remove:function(){
				var _rows = $('#drugs-table-list').datagrid('getSelections');
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
									url:"../drugs/delete.jhtml",
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
											$("#drugs-table-list").datagrid('reload');
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
	}


$(function(){
	
	$("#drugs-table-list").datagrid({
		title:"药品列表",
		fitColumns:true,
		toolbar:"#drugs_manager_tool",
		url:'../drugs/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"药品名称",field:"name",width:100,sortable:true},
		      {title:"别名",field:"alias",width:100,sortable:true},
		      {title:"药品分类",field:"drugCategory",width:100,sortable:true},
		      {title:"创建时间",field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:"修改时间",field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		]

	});

	
	$("#search-btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#beginDate").val(),
			  endDate:$("#endDate").val(),
	  }
	  $('#drugs-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#drugs-table-list").datagrid('reload');
	})
	
	 
	 
})
