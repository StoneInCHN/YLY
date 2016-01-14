	<form id="checkoutCharge_form" method="post" > 
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 出院日期: <input class="easyui-textbox"  value="${(billing.elderlyInfo.outHospitalizedDate?string("yyyy年MM月dd日"))!''}" data-options="width:150" readonly="readonly""  />
	    		</td>
	    		<td>
	    			
	    		</td>
	    	</tr>
	    </table>
	    
	  <fieldset> 
	   <legend>老人基本信息:</legend>
	    <table class="table table-striped">
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly")}: <input class="easyui-textbox"  value="${billing.elderlyInfo.name}" style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 老人${message("yly.common.elderly.identifier")}: <input class="easyui-textbox"  value="${billing.elderlyInfo.identifier}" style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    			 ${message("yly.common.elderly.bedRoom")}: <input class="easyui-textbox"   value="${billing.elderlyInfo.bedLocation}" style="width:180px;" readonly="readonly" />
	    		</td>
	    		<td>
	    			 ${message("yly.common.elderly.nurseLevel")}: <input class="easyui-textbox"   value="${billing.elderlyInfo.nursingLevel.configValue}" style="width:180px;" readonly="readonly" />
	    		</td>
	    	</tr>
		</table>
		</fieldset>		  	
	   <fieldset> 
	    			  	<legend>${message("yly.charge.bedNurse.record")}</legend>
	    			  	<p><input class="easyui-textbox"  value="${billing.bedAmount + billing.nurseAmount}"  style="width:550px;" readonly="readonly" /></p>
	    			  	<p><input class="easyui-textbox"   value="${billing.bedNurseCharge.remark?replace('     ', '\n' )}"  data-options="multiline:true,height:150,width:550" readonly="readonly" /></p>
	   </fieldset>
	    <fieldset id="monthlyMeal" style="display:blank"> 
	    			  	<legend>${message("yly.charge.meal.reocrd")}</legend>
	    			  	<p><input class="easyui-textbox"  value="${billing.mealAmount}" style="width:550px;" readonly="readonly"/></p>
	    			  	<p><input class="easyui-textbox" id="mealCharge_remark"   data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	    <fieldset id="waterElectricity" style="display:blank"> 
	    			  	<legend>${message("yly.charge.water.electricity.reocrd")}</legend>
	    			  	<input type="hidden" id="addCheckoutCharge_waterCharge" name="waterElectricityCharge.waterAmount"/>
	    			  	<input type="hidden" id="addCheckoutCharge_electricityCharge" name="waterElectricityCharge.electricityAmount"/>	 	    			  	
	    			  	<p><input class="easyui-textbox"  value="${billing.waterAmount + billing.electricityAmount}" style="width:550px;" readonly="readonly" /></p>
	    			  	<p><input class="easyui-textbox" value="${billing.waterElectricityCharge.remark}" data-options="multiline:true,height:150,width:550" readonly="readonly" /></p>
	    </fieldset>
	    	    <fieldset id="personalizedService" style="display:blank"> 
	    			  	<legend>${message("yly.charge.personalized.service.record")}</legend>   			
	    			  	<p><input class="easyui-textbox"  value="${billing.personalizedAmount}" style="width:550px;" readonly="readonly" /></p>
	    			  	<p><input class="easyui-textbox" value="${billing.personalizedCharge.remark}"  data-options="multiline:true,height:150,width:550" readonly="readonly"/></p>
	    </fieldset>
	     <table class="table table-striped">
	    	<tr>
	    		<td>
	    			
	    			 使用了预存款: ￥ ${billing.advanceChargeAmount} 付款 
	    			 
	    		</td>
	    		<td>
	    			总金额(已扣除押金)：<input class="easyui-numberbox"  value="${billing.totalAmount}" style="width:120px;" readonly="readonly" />
	    		</td>
	    	</tr>
	    </table>
	    <br>
	</form>

