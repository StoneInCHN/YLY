function showAdjustment(billing,elementId){
	//显示调账信息
	var adjustment_element = $('#'+elementId);
	adjustment_element.html("");
	var adjustmentHtml = "";
	if(billing.billingAdjustment != null && billing.billingAdjustment.length > 0){
		var adjustment = billing.billingAdjustment;
		adjustmentHtml +='<table>'+
														'<tr>'+
															'<th style="padding:4px 32px 4px 8px">'+message("yly.charge.billing.adjustment.cause")+'</th>'+
															'<th style="padding:4px 32px 4px 8px">'+message("yly.charge.billing.adjustment.amount")+'</th>'+
															'<th style="padding:4px 32px 4px 8px">'+message("yly.charge.record.status")+'</th>'+
															'<th style="padding:4px 32px 4px 8px">'+message("yly.charge.record.operator")+'</th>'+
															'<th style="padding:4px 32px 4px 8px">'+message("yly.charge.record.oprTime")+'</th>'+
														'</tr>';
		for(var i = 0; i < adjustment.length; i++){
			var status = adjustment[i].chargeStatus;
			var statusDisplay = "";
    	  	if(status == "PAID"){
    	  		statusDisplay =  "<font color=#7CCD7C>"+message("yly.charge.status.paid")+"</font>";
    	  	}
    	  	if(status == "UNPAID"){
    	  		statusDisplay =  "<font color=#FF0000>"+message("yly.charge.status.unpaid")+"</font>";
    	  	}
    	  	if(status == "UNPAID_ADJUSTMENT"){
    	  		statusDisplay =  "<font color=#FF0000>"+message("yly.charge.status.unpaid_adjustment")+"</font>";
    	  	}
			adjustmentHtml +='<tr>'+
	  											'<td style="padding:4px 32px 4px 8px" align=right>'+adjustment[i].adjustmentCause+'</td>'+
	  											'<td style="padding:4px 32px 4px 8px" align=right>'+adjustment[i].adjustmentAmount+'</td>'+
	  											'<td style="padding:4px 32px 4px 8px" align=right>'+statusDisplay+'</td>'+
	  											'<td style="padding:4px 32px 4px 8px" align=right>'+adjustment[i].operator+'</td>'+
	  											'<td style="padding:4px 32px 4px 8px" align=right>'+new Date(adjustment[i].createDate).Format("yyyy-MM-dd")+'</td>'+
	  										'</tr>';
			
		}
		adjustmentHtml +='</table>';
	}
	//alert(adjustmentHtml);
	adjustment_element.append(adjustmentHtml);
}
function showElderlyInfo(billing,elementId){
	//显示老人基本信息
	var elderlyInfo_element = $('#'+elementId);
	elderlyInfo_element.html("");
	var elderlyInfoHtml = "";
	if(billing.elderlyInfo != null){
			elderlyInfoHtml +='<table>'+
												'<tr>'+
													'<td style="padding:4px 32px 4px 8px">'+message("yly.common.elderlyname")+": "+billing.elderlyInfo.name+'</td>'+
													'<td style="padding:4px 32px 4px 8px">'+message("yly.elderlyInfo.identifier")+": "+billing.elderlyInfo.identifier+'</td>'+
												'</tr>'+
												'<tr>'+
	  												'<td style="padding:4px 32px 4px 8px">'+message("yly.common.bedRoom")+": "+billing.elderlyInfo.bedLocation+'</td>'+
	  												'<td style="padding:4px 32px 4px 8px">'+message("yly.common.nurseLevel")+": "+billing.elderlyInfo.nursingLevel.configValue+'</td>'+
	  											'</tr>';
		}
		elderlyInfoHtml +='</table>';
		//alert(elderlyInfoHtml);
		elderlyInfo_element.append(elderlyInfoHtml);
	
}
