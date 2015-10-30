$(function(){
	
	$("#waterElectricityRecord-table-list").datagrid({
		title:message("yly.waterElectricityRecord.record"),
		fitColumns:true,
		toolbar:"#waterElectricityRecord_manager_tool",
		url:'../waterElectricityRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#waterElectricityRecordDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 500,    
			    height: 750, 
			    cache: false,   
			    href:'../waterElectricityRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#waterElectricityRecordDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.room.roomNumber"),field:"roomNumber",width:20,sortable:true,formatter: function(value,row,index){
		    	  return row.room.roomNumber;
		      }},
		      {title:message("yly.waterElectricityRecord.waterActual"),field:"waterActual",width:20,align:'center',sortable:true},
		      {title:message("yly.waterElectricityRecord.electricityActual"),field:"electricityActual",width:20,align:'center',sortable:true},
		      {title:message("yly.waterElectricityRecord.operator"),field:"operator",width:40,align:'center',sortable:true},
		      {title:message("yly.common.insertDate"),field:"createDate",width:40,sortable:true, formatter: function(value,row,index){
		    	  return new Date(value).Format("yyyy-MM-dd");
		      }}
		   ]
		]

	});
	 
	 

	waterElectricityRecord_manager_tool = {
			add:function(){		
				$('#addWaterElectricityRecord').dialog({    
				    title: message("yly.waterElectricityRecord.add"),    
				    width: 500,    
				    height: 700,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addWaterElectricityRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../waterElectricityRecord/add.jhtml",
									type:"post",
									data:$("#addWaterElectricityRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addWaterElectricityRecord_form').form('reset');
										$('#addWaterElectricityRecord').dialog("close");
										$("#waterElectricityRecord-table-list").datagrid('reload');
										
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
							 $('#addWaterElectricityRecord').dialog("close");
						}
				    }],
				onOpen:function(){
			    	$('#addBed_form').show();
			    	$("#addBed_form_roomId").combotree({    
			    	    animate:true,
			    	    lines:true,
			    	    queryParams:{
							isSelect:true
						},
			    	    url:'../room/findAll.jhtml',
			    	    prompt:message("yly.common.please.select"),
			    	    //选择树节点触发事件  
			    	    onBeforeSelect : function(node) {  
			    	        //返回树对象  
			    	        var tree = $(this).tree;  
			    	        //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
			    	        var isLeaf = tree('isLeaf', node.target);  
			    	        if (!isLeaf) {  
			    	        	$.messager.alert("警告","请选择子节点","warning");
			    				// 返回false表示取消本次选择操作
			    				return false;
			    	        }  
			    	    } 
			    	})
			    }
				});  
				 $('#addWaterElectricityRecord_form').show();
				 
			},
			edit:function(){
				var _edit_row = $('#waterElectricityRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.editRow"));  
					return false;
				}
				var _dialog = $('#editwaterElectricityRecord').dialog({    
				    title: message("yly.common.edit"),     
				    width: 500,    
				    height: 700,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../waterElectricityRecord/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editwaterElectricityRecord_form').form('validate');
							if(validate){
								$.ajax({
									url:"../waterElectricityRecord/update.jhtml",
									type:"post",
									data:$("#editwaterElectricityRecord_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editwaterElectricityRecord').dialog("close");
										$("#waterElectricityRecord-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editwaterElectricityRecord').dialog("close");
						}
				    }]
				});  
			},
			remove:function(){
				var _edit_row = $('#waterElectricityRecord-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.notice"),message("yly.common.select.deleteRow"));  
					return false;
				}
				listRemove('waterElectricityRecord-table-list','../waterElectricityRecord/delete.jhtml');
			}
	}
	$("#waterElectricityRecord_search_btn").click(function(){
	  var _queryParams = $("#waterElectricityRecord_search_form").serializeJSON();
	  $('#waterElectricityRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#waterElectricityRecord-table-list").datagrid('reload');
	})
	
	$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
	})
	 
})

