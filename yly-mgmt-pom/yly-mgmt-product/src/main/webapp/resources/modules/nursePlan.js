
	$(function(){
		$("#nursePlan-table-list").datagrid({
			title:message("yly.nursePlan.list"),
			fitColumns:true,
			url:'../nursePlan/list.jhtml',  
			pagination:true,
			loadMsg:message("yly.common.loading"),
			striped:true,
			toolbar: [{
				text:message("yly.common.add"),
				iconCls: 'icon-add',
				handler: function(){
					$("#addNursePlan").dialog({    
					    title:message("yly.nursePlan.add"),   
					    width: 350,    
					    height: 450,    
					    closed: false,    
					    cache: false,
					    iconCls:'icon-mini-add',
					    modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#addNursePlan_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nursePlan/add.jhtml",
										type:"post",
										data:$("#addNursePlan_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#addNursePlan_form').form('reset');
											$('#addNursePlan').dialog("close");
											$("#nursePlan-table-list").datagrid('reload');
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
								 $('#addNursePlan').dialog("close");
							}
						}],
						onOpen:function(){
						    	$('#addNursePlan_form').show();
						},
						onClose:function(){
						    	$('#addNursePlan_form').form('reset');
						}
					});
				}
			},'-',{
				text:message("yly.common.edit"),
				iconCls: 'icon-edit',
				handler: function(){
					var _edit_row = $('#nursePlan-table-list').datagrid('getSelected');
					if( _edit_row == null ){
						$.messager.alert(message("yly.common.prompt"),message("yly.common.select.editRow"),'warning');    
						return false;
					}
					$("#editNursePlan").dialog({
						width:350,
						height:350,
						iconCls:'icon-mini-edit',
						title:message("yly.nursePlan.edit"),
						href:'../nursePlan/edit.jhtml?id='+_edit_row.id,
						modal: true,
					    buttons:[{
					    	text:message("yly.common.save"),
					    	iconCls:'icon-save',
							handler:function(){
								var validate = $('#editNursePlan_form').form('validate');
								if(validate){
									$.ajax({
										url:"../nursePlan/update.jhtml",
										type:"post",
										data:$("#editNursePlan_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("yly.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editNursePlan_form').form('reset');
											$('#editNursePlan').dialog("close");
											$("#nursePlan-table-list").treegrid('reload');
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
								 $('#editNursePlan').dialog("close");
							}
						}],
						onLoad:function(){
				    	
				},
					})
				}
			},'-',{
				text:message("yly.common.remove"),
				iconCls: 'icon-remove',
				handler: function(){
					var _rows = $("#nursePlan-table-list").treegrid('getSelections');
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
										url : "../nursePlan/delete.jhtml",
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
												$("#nursePlan-table-list").treegrid('reload');
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
			      {title:message("yly.nursePlan.planStartDate"),field:"planStartDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.nursePlan.planEndDate"),field:"planEndDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}},
			      {title:message("yly.nursePlan.planName"),field:"planName",width:100,sortable:true},
			      {title:message("yly.nursePlan.planContent"),field:"planContent",width:100,sortable:true},
			      {title:message("yly.nursePlan.remark"),field:"remark",width:100,sortable:true},
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
	