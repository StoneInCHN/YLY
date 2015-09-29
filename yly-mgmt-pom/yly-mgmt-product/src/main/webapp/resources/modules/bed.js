var bed_manager_tool = {
			add:function(){		
				$('#addBed').dialog({    
				    title: '添加楼宇',    
				    width: 380,    
				    height: 460,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addBed_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bed/save.jhtml",
									type:"post",
									data:$("#addBed_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在添加中......"
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addBed').dialog("close");
											$("#bed_table_list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#addBed').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addBed_form').show();
				    	$("#addBed_form_roomId").combobox({    
						    valueField:'id',    
						    textField:'buildingName',
						    cache: true,
						    url:'../building/findAll.jhtml'
						});
				    	$("#addBed_form_bedType").combobox({    
						    valueField:'id',    
						    textField:'configValue',
						    cache: true,
						    url:'../systemConfig/findByConfigKey.jhtml',
						    onBeforeLoad : function(param) {
						        param.configKey = 'bedTYPE';// 参数
						    }
						});
				    },
				    onClose:function(){
				    	 $('#addBed_form').form('reset');
				    }
				});  
			},
			edit:function(){
				var _edit_row = $('#bed_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行','warning');  
					return false;
				}
				var _dialog = $('#editbBed').dialog({    
				    title: '楼宇编辑',     
				    width: 400,    
				    height: 380,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../bed/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editBed_form').form('validate');
							if(validate){
								$.ajax({
									url:"../bed/update.jhtml",
									type:"post",
									data:$("#editBed_form").serialize(),
									beforeSend:function(){
										$.messager.progress({text:"正在保存中......"});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editBed').dialog("close");
											$("#bed_table_list").datagrid('reload');
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
							};
						}
					},{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editBed').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('bed_table_list','../bed/delete.jhtml');
			}
	}
$(function(){
	$("#bed_table_list").datagrid({
		title:"楼宇列表",
		fitColumns:true,
		toolbar:"#bed_manager_tool",
		url:'../bed/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"床位编号",field:"bedNumber",width:50,sortable:true},
		      {title:"床位状态",field:"bedStatus",width:50,sortable:true,formatter: function(value,row,index){
		    	  	if(value == "ENABLE"){
		    	  		return  "启用";
		    	  	}
		    	  	if(value == "DISABLE"){
		    	  		return  "禁用";
		    	  	}
		      	}
		      },
		      {title:"描述",field:"description",width:200,sortable:true,formatter: function(value,row,index){
					if(value && value.length >22){
						var abValue =  value.substring(0,19) +"...";
						var content = '<span title="' + value + '" class="tips-span">' + abValue + '</span>';
						return content;
					}else{
						return value
					}
		      	}
		      },
		      {title:"创建时间",field:"createDate",width:60,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		      {title:"修改时间",field:"modifyDate",width:60,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}},
		   ]
		],
		onLoadSuccess:function(data){
           $(".tips-span").tooltip({
        	   position: 'top',
               onShow: function(){
                   $(this).tooltip('tip').css({ 
                       width:'300'
                   });
               }
           });
        }

	});
	$("#bed_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#bed_search_form #beginDate").val(),
			  endDate:$("#bed_search_form #endDate").val()
	  }
	  $('#bed_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#bed_table_list").datagrid('reload');
	})
	
	
	$("#bed_search_form_buildingId").combobox({    
	    url:'../building/findAll.jhtml',    
	    valueField:'id',    
	    textField:'buildingName'
	});  
})