function downloadTemplate(fileName){
	window.open("../importData/downloadTemplate.jhtml?fileName="+fileName);
}
function importData(){
//	var formData = new FormData($( "#importData_form" )[0]);  
//	$.ajax({
//		url:"../importData/insertData.jhtml",
//		type:"post",
//        async: false,  
//        cache: false,  
//        contentType: false,  
//        processData: false, 
//		data:formData,
//		beforeSend:function(){
//			$.messager.progress({
//				text:"正在上传中..."
//			});
//		},
//		success:function(result,response,status){
//			console.info(response);
//			$.messager.progress('close');
//			showSuccessMsg(result.content);
//		},
//		error:function (XMLHttpRequest, textStatus, errorThrown) {
//			alert("error");
//			$.messager.progress('close');
//			alertErrorMsg();
//		}
//	});
	if($("#excelFile").val() == null || $("#excelFile").val() == ""){
		$.messager.alert(message("yly.common.prompt"),"请先选择一个要上传的excel文件",'warning'); 
	}else{
		$("#importData_form").attr("action","../importData/insertData.jhtml");
		$("#importData_form").attr("target","_blank");
		$("#importData_form").submit();
		$.messager.confirm(message("yly.common.confirm"),"请下载并查看系统反馈的excel，导入状态 标记了是否导入成功", function(r) {
			if(r){
				$("#elderlyInfo-table-list").datagrid('reload');
			}else{
				$("#elderlyInfo-table-list").datagrid('reload');
			}
		});
	}

}
$(function(){	
	$("#elderlyInfo-table-list").datagrid({
		title:message("yly.elderlyinfo.record"),
		fitColumns:true,
		url:'../elderlyInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("yly.common.loading"),
		striped:true,
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
		    	  	if(value == "IN_PROGRESS_CHECKIN_BILL"){
		    	  		return  "入院办理(已出账单未交费)";
		    	  	}
		    	  	if(value == "IN_PROGRESS_EVALUATING"){
		    	  		return  "通过入院评估";
		    	  	}
		      	}}		      
		   ]
		]
	});		 
})