var room_manager_tool = {
			add:function(){		
				$('#addRoom').dialog({    
				    title: '添加楼宇',    
				    width: 350,    
				    height: 380,
				    modal: true,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addRoom_form').form('validate');
							if(validate){
								$.ajax({
									url:"../room/save.jhtml",
									type:"post",
									data:$("#addRoom_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:"正在添加中......"
										});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addRoom').dialog("close");
											$("#room_table_list").datagrid('reload');
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
							 $('#addRoom').dialog("close");
						}
				    }],
				    onOpen:function(){
				    	$('#addRoom_form').show();
				    	$("#addRoom_form_buildingId").combobox({    
						    valueField:'id',    
						    textField:'buildingName',
						    cache: true,
						    url:'../building/findAll.jhtml'
						});
				    },
				    onClose:function(){
				    	 $('#addRoom_form').form('reset');
				    }
				});  
			},
			edit:function(){
				var _edit_row = $('#room_table_list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert('警告','请选择要编辑的行','warning');  
					return false;
				}
				var _dialog = $('#editRoom').dialog({    
				    title: '楼宇编辑',     
				    width: 400,    
				    height: 380,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../room/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:'保存',
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editRoom_form').form('validate');
							if(validate){
								$.ajax({
									url:"../room/update.jhtml",
									type:"post",
									data:$("#editRoom_form").serialize(),
									beforeSend:function(){
										$.messager.progress({text:"正在保存中......"});
									},
									success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editRoom').dialog("close");
											$("#room_table_list").datagrid('reload');
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
							 $('#editRoom').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				listRemove('room_table_list','../room/delete.jhtml');
			}
	}
$(function(){
	$("#room_table_list").datagrid({
		title:"楼宇列表",
		fitColumns:true,
		toolbar:"#room_manager_tool",
		url:'../room/list.jhtml',  
		pagination:true,
		loadMsg:"加载中......",
		striped:true,
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:"房间名称",field:"roomName",width:100,sortable:true},
		      {title:"房间编号",field:"roomNumber",width:50,sortable:true},
		      {title:"所在楼层",field:"floor",width:50,sortable:true},
		      {title:"房间类型",field:"roomType",width:100,sortable:true},
		      {title:"房间状态",field:"roomStatus",width:50,sortable:true,formatter: function(value,row,index){
		    	  	if(value == "ENABLE"){
		    	  		return  "启用";
		    	  	}
		    	  	if(value == "DISABLE"){
		    	  		return  "禁用";
		    	  	}
		      	}
		      },
		      {title:"所属楼宇",field:"building",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value){
		    		  return  value.buildingName;
		    	  }else{
		    		  return  value;
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
	$("#room_search_btn").click(function(){
	  var _queryParams = {
			  beginDate:$("#room_search_form #beginDate").val(),
			  endDate:$("#room_search_form #endDate").val()
	  }
	  $('#room_table_list').datagrid('options').queryParams = _queryParams;  
	  $("#room_table_list").datagrid('reload');
	})
	
	
	$("#room_search_form_buildingId").combobox({    
	    url:'../building/findAll.jhtml',    
	    valueField:'id',    
	    textField:'buildingName'
	});  
})