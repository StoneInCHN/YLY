
	$(function(){
		$("#nurseDutyType-table-list").datagrid({
			title:message("yly.nurseDutyType.list"),
			fitColumns:true,
			url:'../nurseDutyType/list.jhtml',  
			pagination:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:'添加',
				iconCls: 'icon-add',
				handler: function(){
					$("#addNurseDutyType").dialog({    
					    title:message("yly.nurseDutyType.add"),   
					    width: 350,    
					    height: 380,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addNurseDutyType_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nurseDutyType/add.jhtml",
										type:"post",
										data:$("#addNurseDutyType_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addNurseDutyType_form').form('reset');
											$('#addNurseDutyType').dialog("close");
											$("#nurseDutyType-table-list").datagrid('reload');
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
								 $('#addNurseDutyType').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addNurseDutyType_form').show();
						},
						onClose:function(){
						    	$('#addNurseDutyType_form').form('reset');
						}
					});
				}
			},'-',{
				text:'修改',
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#nurseDutyType-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
						return false;
					}
					$("#editNurseDutyType").dialog({
						width:350,
						height:350,
						iconCls:'icon-mini-edit',
						title:"班次类型编辑",
						href:'../nurseDutyType/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editNurseDutyType_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nurseDutyType/update.jhtml",
										type:"post",
										data:$("#editNurseDutyType_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editNurseDutyType_form').form('reset');
											$('#editNurseDutyType').dialog("close");
											$("#nurseDutyType-table-list").treegrid('reload');
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
								 $('#editNurseDutyType').dialog("close");
							}
						}],
						onLoad:function(){
				    	
				},
					})
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#nurseDutyType-table-list").treegrid('getSelections');
					if (_rows.length == 0) {
						$.messager.alert(message("yly.common.prompt"), message("yly.common.select.deleteRow"),'warning');
					} else {
						var _ids = [];
						for (var i = 0; i < _rows.length; i++) {
							_ids.push(_rows[i].id);
						}
						if (_ids.length > 0) {
							$.messager.confirm(message("yly.common.confirm"), message("yly.common.delete.confirm"), function(r) {
								if (r) {
									$.ajax({
										url : "../nurseDutyType/delete.jhtml",
										type : "post",
										traditional : true,
										data : {
											"ids" : _ids
										},
										beforeSend : function() {
											$.messager.progress({
												text : message("yly.common.progress")
											});
										},
										success : function(result, response, status) {
											$.messager.progress('close');
											var resultMsg = result.content;
											if (response == "success") {
												showSuccessMsg(resultMsg);
												$("#nurseDutyType-table-list").treegrid('reload');
											} else {
												alertErrorMsg();
											}
										}
									});
								}
							})
						}

					}
				}
			}],
			columns:[
			   [
			      {field:'ck',checkbox:true},
			      {title:message("yly.nurseDutyType.dutyStartTime"),field:"dutyStartTime",width:100,sortable:true},
			      {title:message("yly.nurseDutyType.dutyEndTime"),field:"dutyEndTime",width:100,sortable:true},
			      {title:message("yly.nurseDutyType.dutyName"),field:"dutyName",width:100,sortable:true},
			      {title:message("yly.nurseDutyType.remark"),field:"remark",width:100,sortable:true},
			      {title:message("yly.nurseDutyType.order"),field:"orderIndex",width:100,sortable:true},
			      {title:message("yly.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.common.modifyDate"),field:"modifyDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}}
			   ]
			]
	
		});
})
	