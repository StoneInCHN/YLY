var industryInformation_manager_tool = {
			add:function(){
				$('#addIndustryInformation').dialog({
				    title: message("yly.industryInformation.add"),    
				    width: 700,    
				    height: 600,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#addIndustryInformation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../industryInformation/add.jhtml",
									type:"post",
									data:$("#addIndustryInformation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addIndustryInformation').dialog("close");
											$('#addIndustryInformation_form').form('reset');
											$("#industryInformation-table-list").datagrid('reload');
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
							 $('#addIndustryInformation').dialog("close");
							 $('#addIndustryInformation_form').form('reset');
						}
				    }],
				    onOpen:function(){
				    	$('#addIndustryInformation_form').show();
				    	var editor = KindEditor.create('#add_industry_information_content', {
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
				var _edit_row = $('#industryInformation-table-list').datagrid('getSelected');
				if( _edit_row == null ){
					$.messager.alert(message("yly.common.select.editRow"));
					return false;
				}
				var _dialog = $('#editIndustryInformation').dialog({    
					title: message("yly.common.edit"),   
				    width: 700,    
				    height: 600,    
				    modal: true,
				    iconCls:'icon-mini-edit',
				    href:'../industryInformation/edit.jhtml?id='+_edit_row.id,
				    buttons:[{
				    	text:message("yly.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							var validate = $('#editIndustryInformation_form').form('validate');
							if(validate){
								$.ajax({
									url:"../industryInformation/update.jhtml",
									type:"post",
									data:$("#editIndustryInformation_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editIndustryInformation').dialog("close");
											$('#editIndustryInformation_form').form('reset');
											$("#industryInformation-table-list").datagrid('reload');
									}
								});
							};
						}
					},{
						text:message("yly.common.cancel"),
						iconCls:'icon-cancel',
						handler:function(){
							 $('#editIndustryInformation').dialog("close");
							 $('#editIndustryInformation_form').form('reset');
						}
				    }],onLoad:function(){
				    	$('#editIndustryInformation_form').show();
				    	var editor = KindEditor.create('#edit_industryInformation_content', {
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
				$('#editIndustryInformation_form').show();
			},
			remove:function(){
				listRemove('industryInformation-table-list','../industryInformation/delete.jhtml');
			}
	};
$(function(){

	$("#industryInformation-table-list").datagrid({
		title:message("yly.industryInformation.list"),
		fitColumns:true,
		toolbar:"#industryInformation_manager_tool",
		url:'../industryInformation/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#industryInformationDetail').dialog({    
			    title: message("yly.common.detail"),    
			    width: 600,    
			    height: 500, 
			    cache: false,
			    modal: true,
			    href:'../industryInformation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("yly.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#industryInformationDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("yly.notification.operator"),field:"operator",width:50,sortable:true},
		      {title:message("yly.common.title"),field:"title",width:50,sortable:true},
		      {title:message("yly.notification.publishTime"),field:"publishTime",width:50,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd");
				}
		      },
		   ]
		]

	});

	$("#industryInformation-search-btn").click(function(){
	  var _queryParams = $("#industryInformation-search-form").serializeJSON();
	  $('#industryInformation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#industryInformation-table-list").datagrid('reload');
	})
	
	 
	 
})
