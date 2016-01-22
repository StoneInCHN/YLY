var notification_manager_tool = {
			add:function(){
				$('#addNotification').dialog({
				    title: message("yly.notification.add"),    
				    width: 700,    
				    height: 600,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addNotification_form').form('validate');
							if(validate){
								$.ajax({
									url:"../notification/add.jhtml",
									type:"post",
									data:$("#addNotification_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addNotification').dialog("close");
											$('#addNotification_form').form('reset');
											$("#notification-table-list").datagrid('reload');
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
							 $('#addNotification').dialog("close");
							 $('#addNotification_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addNotification_form').show();
				    	var editor = KindEditor.create('#add_notification_content', {
							resizeType : 1,
							width : '400px',
							height:'300px',
							allowPreviewEmoticons : false,
							items: [
									"source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste",
									"plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright",
									"justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript",
									"superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/",
									"formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold",
									"italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "multiimage",
									"table", "hr", "emoticons",  "pagebreak",
									"anchor", "link", "unlink"
								],
							allowImageRemote : false,
							showRemote : false,
							allowFileManager: true,
							filePostName: "file",
							uploadJson:  "../../console/file/uploadNotificationPicutre.jhtml",
							urlType:'relative',
							afterBlur:function(){ 
					            this.sync(); 
					        }
						});
				    	editor.sync();
				    }
				});  
				
			},
			edit:function(){
				var _edit_row = $('#notification-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editNotification').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 600,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../notification/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editNotification_form').form('validate');
							if(validate){
								$.ajax({
									url:"../notification/update.jhtml",
									type:"post",
									data:$("#editNotification_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editNotification').dialog("close");
											$('#editNotification_form').form('reset');
											$("#notification-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editNotification').dialog("close");
							 $('#editNotification_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editNotification_form').show();
				    	var editor = KindEditor.create('#edit_notification_content', {
							resizeType : 1,
							width : '400px',
							height:'300px',
							allowPreviewEmoticons : false,
							items: [
									"source", "|", "undo", "redo", "|", "preview", "print", "template", "cut", "copy", "paste",
									"plainpaste", "wordpaste", "|", "justifyleft", "justifycenter", "justifyright",
									"justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript",
									"superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/",
									"formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold",
									"italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "multiimage",
									"table", "hr", "emoticons",  "pagebreak",
									"anchor", "link", "unlink"
								],
							allowImageRemote : false,
							showRemote : false,
							allowFileManager: true,
							filePostName: "file",
							uploadJson:  "../../console/file/uploadNotificationPicutre.jhtml",
							urlType:'relative',
							afterBlur:function(){ 
					            this.sync(); 
					        }
						});	 
				    	
				    	editor.sync();
				    }
				});
				$('#editNotification_form').show();
			},
			remove:function(){
				listRemove('notification-table-list','../notification/delete.jhtml');
			}
	};
$(function(){

	$("#notification-table-list").datagrid({
		title:message("yly.notification.list"),
		fitColumns:true,
		toolbar:"#notification_manager_tool",
		url:'../notification/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#notificationDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 600,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../notification/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#notificationDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.notification.operator"),field:"operator",width:100,sortable:true},
		      {title:message("yly.common.title"),field:"title",width:100,sortable:true},
		      {title:message("yly.notification.publishTime"),field:"publishTime",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	$("#notification-search-btn").click(function(){
	  var _queryParams = $("#notification-search-form").serializeJSON();
	  $('#notification-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#notification-table-list").datagrid('reload');
	})
	
	 
	 
})
