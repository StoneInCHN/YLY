// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

var setting = {
	priceScale : "2",
	priceRoundType : "roundHalfUp",
	uploadImageExtension : "jpg,jpeg,bmp,gif,png",
	uploadFlashExtension : "swf,flv",
	uploadMediaExtension : "swf,flv,mp3,wav,avi,rm,rmvb",
	uploadFileExtension : "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};



/**
 * 公共提示信息 response==success
 * 
 * @param msgStr
 */
function showSuccessMsg(msgStr) {
	$.messager.show({
		title : message("yly.common.prompt"),
		msg : msgStr,
		timeout : 3000,
		showType : 'slide'
	});
}

/**
 * 公共提示信息 error
 */
function alertErrorMsg() {
	$.messager.alert(message("yly.common.fail"),
			message("yly.common.unknow.error"), 'error');
}

//添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""),
				(options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}



/**
 * 删除公用方法 id table_list id url 删除是向后台发送的链接
 */
function listRemove(id, url) {
	var _id = id;
	var _url = url
	var _rows = $("#" + _id).datagrid('getSelections');
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
						url : _url,
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
								$("#" + _id).datagrid('reload');
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

/**
 * 老人查询功能
 */
function searchElderlyInfo(id) {
	$('#searchElderlyInfo').dialog({    
	    title: message("yly.visitelderly.search"),    
	    width: 1000,
	    height: 500,
	    modal:true,
	    cache: false,   
	    href:'../elderlyInfo/commonElderlySearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchElderlyInfo').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	/**
	    	 * 此datagrid 用户展示老人数据,并且提供查询功能
	    	 */
	    	$("#common_elderlyInfoSearch-table-list").datagrid({
	    	 title:message("yly.elderlyinfo"),
	    	 fitColumns:true,
	    	 url:'../elderlyInfo/list.jhtml',  
	    	 pagination:true,
	    	 loadMsg:message("yly.common.loading"),
	    	 striped:true,
	    	 onDblClickRow : function (rowIndex, rowData){
	    		 $("#"+id+"ID").val(rowData.id);
	    		 $("#"+id).textbox('setValue',rowData.name);
	    		 if($("#identifier")){
	    			 $("#identifier").val(rowData.identifier);
	    		 }
	    		 $('#searchElderlyInfo').dialog("close");
	    	 },
	    	 columns:[
	    	    [
	    	        {field:'ck',checkbox:true},
		      {title:message("yly.elderlyinfo.identifier"),field:"identifier",width:12,align:'center',sortable:true},
		      {title:message("yly.common.elderlyname"),field:"name",width:20,align:'center',sortable:true},
		      {title:message("yly.common.age"),field:"age",width:10,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyPhoneNumber"),field:"elderlyPhoneNumber",width:20,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.beHospitalizedDate"),field:"beHospitalizedDate",width:25,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value != null){
		    	  		return new Date(value).Format("yyyy-MM-dd");
		      	}
		      }},
		      {title:message("yly.common.gender"),field:"gender",width:10,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "MALE"){
		    	  		return  message("yly.common.male");
		    	  	}
		    	  	if(value == "FEMALE"){
		    	  		return  message("yly.common.female");
		    	  	}
		      	}},
		      {title:message("yly.common.idcard"),field:"idcard",width:40,align:'center',sortable:true},
		      {title:message("yly.common.bedRoom"),field:"bedLocation",width:30,align:'center',sortable:true},
		      {title:message("yly.elderlyInfo.elderlyConsigner.consignerPhoneNumber"),field:"consignerPhoneNumber",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  if(row !=  null && row.elderlyConsigner != null){
		    		  return row.elderlyConsigner.consignerPhoneNumber;  
		    	  }
		      	}},
		      {title:message("yly.elderly.status"),field:"elderlyStatus",width:20,align:'center',sortable:true,formatter: function(value,row,index){
		    	  	if(value == "IN_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.in_nursing_home");
		    	  	}
		    	  	if(value == "OUT_NURSING_HOME"){
		    	  		return  message("yly.elderly.status.out_nursing_home");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKIN"){
		    	  		return  message("yly.elderly.status.in_progress_checkin");
		    	  	}
		    	  	if(value == "IN_PROGRESS_CHECKOUT"){
		    	  		return  message("yly.elderly.status.in_progress_checkout");
		    	  	}
		    	  	if(value == "DEAD"){
		    	  		return  message("yly.elderly.status.dead");
		    	  	}
		      	}}
	    	    ]
	    	 ]

	    	});
	    	
	    	$("#common_elderlyinfo_search_btn").click(function(){
	  		  var _queryParams = $("#common_elderlyinfo_search_form").serializeJSON();
	  		  $('#common_elderlyInfoSearch-table-list').datagrid('options').queryParams = _queryParams;  
	  		  $("#common_elderlyInfoSearch-table-list").datagrid('reload');
	  	})
	    }
	});  
}
//查询用户
function searchTenantUser(id){
	$('#searchTenantUser').dialog({    
	    title: message("yly.tenantUser.search"),    
	    width: 1000,
	    height: 500,
	    modal:true,
	    cache: false,   
	    href:'../tenantUser/commonTenantUserSearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchTenantUser').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	/**
	    	 * 此datagrid 用户展示老人数据,并且提供查询功能
	    	 */
	    	$("#common-tenantUser-table-list").datagrid({
	    	 title:message("yly.elderlyinfo"),
	    	 fitColumns:true,
	    	 url:'../tenantUser/list.jhtml',  
	    	 pagination:true,
	    	 loadMsg:message("yly.common.loading"),
	    	 striped:true,
	    	 onDblClickRow : function (rowIndex, rowData){
	    		 $("#"+id+"ID").val(rowData.id);
	    		 $("#"+id).textbox('setValue',rowData.realName);
	    		 if($("#identifier")){
	    			 $("#identifier").val(rowData.identifier);
	    		 }
	    		 $('#searchTenantUser').dialog("close");
	    	 },
	    	 columns:[
	    	    [
				{title:message("yly.common.age"),field:"age",width:100,sortable:true},
				{title:message("yly.tenantUser.staffID"),field:"staffID",width:100,sortable:true},
				{title:message("yly.tenantUser.staffStatus"),field:"staffStatus",width:100,sortable:true,
					  formatter: function(value,row,index){
				  	  if(value == "INSERVICE"){
				  		  return  message("yly.tenantUser.staffStatus.inService");
				  	  }else if (value = "OUTSERVICE"){
				  		  return  message("yly.tenantUser.staffStatus.outService");
				  	  }
					  }  
				},
				
				{title:message("yly.tenantUser.department"),field:"department",width:100,sortable:true,formatter: function(value,row,index){
					  if(value){
						  return  value.name;
					  }else{
						  return  value;
					  }
				  }},
				{title:message("yly.tenantUser.position"),field:"position",width:100,sortable:true,formatter: function(value,row,index){
					  if(value){
						  return  value.name;
					  }else{
						  return  value;
					  }
				  }},
				{title:message("yly.tenantUser.hireDate"),field:"hireDate",width:100,sortable:true,formatter: function(value,row,index){
						return new Date(value).Format("yyyy-MM-dd");
					}
				},
	    	    ]
	    	 ]

	    	});
	    	
	    	$("#common_elderlyinfo_search_btn").click(function(){
	  		  var _queryParams = $("#common_elderlyinfo_search_form").serializeJSON();
	  		  $('#common_elderlyInfoSearch-table-list').datagrid('options').queryParams = _queryParams;  
	  		  $("#common_elderlyInfoSearch-table-list").datagrid('reload');
	  	})
	    }
	});  

}
function searchRoles(id){
	alert(id);
	$('#searchRoles').dialog({    
	    title: message("yly.role.search"),    
	    width: 1000,
	    height: 500,
	    modal:true,
	    cache: false,   
	    href:'../role/commonRolesSearch.jhtml',
	    buttons:[{
			text:message("yly.common.cancel"),
			iconCls:'icon-cancel',
			handler:function(){
				 $('#searchRoles').dialog("close");
			}
	    }],
	    onLoad:function(){
	    	/**
	    	 * 此datagrid 用户展示老人数据,并且提供查询功能
	    	 */
	    	$("#common-roles-table-list").datagrid({
	    	 title:message("yly.elderlyinfo"),
	    	 fitColumns:true,
	    	 url:'../role/list.jhtml',  
	    	 pagination:true,
	    	 loadMsg:message("yly.common.loading"),
	    	 striped:true,
	    	 onDblClickRow : function (rowIndex, rowData){
	    		 $("#"+id+"ID").val(rowData.id);
	    		 $("#"+id).textbox('setValue',rowData.name);
	    		 if($("#identifier")){
	    			 $("#identifier").val(rowData.identifier);
	    		 }
	    		 $('#searchRoles').dialog("close");
	    	 },
	    	 columns:[
	    	    [
				 {title:message("yly.role.name"),field:"name",width:20,align:'center',formatter:function(value,row,index){
		    	  return row.name;
		      }},
		      {title:message("yly.role.description"),field:"description",width:80,align:'center',formatter:function(value,row,index){
		    	  return row.description;
		      }}
	    	    ]
	    	 ]

	    	});
	    	
	    	$("#common_elderlyinfo_search_btn").click(function(){
	  		  var _queryParams = $("#common_elderlyinfo_search_form").serializeJSON();
	  		  $('#common_elderlyInfoSearch-table-list').datagrid('options').queryParams = _queryParams;  
	  		  $("#common_elderlyInfoSearch-table-list").datagrid('reload');
	  	})
	    }
	});  


}
function formReset(formId,tableId){
	$('#'+formId)[0].reset();
	var _queryParams = {}
	$('#'+tableId).datagrid('options').queryParams = _queryParams;
}
