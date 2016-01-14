function showAdjustment(billing,fieldsetId){
	//显示调账信息
	var adjustment_fieldset = $('#'+fieldsetId);
	adjustment_fieldset.html("");
	var adjustmentHtml = "";
	if(billing.billingAdjustment != null && billing.billingAdjustment.length > 0){
		var adjustment = billing.billingAdjustment;
		adjustmentHtml +='<legend>'+message("yly.charge.billing.adjustment")+'</legend>'+
													'<table class="table table-striped">'+
														'<tr>'+
															'<th >'+message("yly.charge.billing.adjustment.cause")+'</th>'+
															'<th>'+message("yly.charge.billing.adjustment.amount")+'</th>'+
															'<th>'+message("yly.charge.record.status")+'</th>'+
															'<th>'+message("yly.charge.record.operator")+'</th>'+
															'<th>'+message("yly.charge.record.oprTime")+'</th>'+
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
	  											'<td align=right>'+adjustment[i].adjustmentCause+'</td>'+
	  											'<td align=right>'+adjustment[i].adjustmentAmount+'</td>'+
	  											'<td align=right>'+statusDisplay+'</td>'+
	  											'<td align=right>'+adjustment[i].operator+'</td>'+
	  											'<td align=right>'+new Date(adjustment[i].createDate).Format("yyyy-MM-dd")+'</td>'+
	  										'</tr>';
			
		}
		adjustmentHtml +='</table>';
	}
	//alert(adjustmentHtml);
	adjustment_fieldset.append(adjustmentHtml);
	adjustment_fieldset.show();
}