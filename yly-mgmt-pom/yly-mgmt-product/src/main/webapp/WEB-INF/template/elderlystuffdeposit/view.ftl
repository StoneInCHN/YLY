
<form id="viewEvent_form" method="post">   
	    <table class="table table-striped">
	    	<tr>
	    		<th style="width:110px;">${message("yly.elderlyInfo.stuffDeposit.name")}:</th>
	    		<td>
	    			 ${elderlyStuffDeposit.name}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.count")}:</th>
	    		<td>
	    			 ${elderlyStuffDeposit.count}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.stuffNumer")}:</th>
	    		<td>
	    			${elderlyStuffDeposit.stuffNumber}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.status")}:</th>
	    		<td>
	    			${message("yly.elderlyInfo.stuffDeposit_${elderlyStuffDeposit.stuffDepositStatus}")}	
	    		</td>
	    	</tr>	    	

	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.inputDate")}:</th>
	    		<td>
	    			[#if elderlyStuffDeposit.putinDate != null]
	    			  		${elderlyStuffDeposit.putinDate?string("yyyy-MM-dd")}
	    			[/#if]
	    			
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.takeAwayDate")}:</th>
	    		<td>
	    		      [#if elderlyStuffDeposit.takeAlwayDate != null]
	    			  		${elderlyStuffDeposit.takeAlwayDate?string("yyyy-MM-dd")} 
	    			  [/#if]
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.common.elderly.operator")}:</th>
	    		<td>
	    			  ${elderlyStuffDeposit.operator} 
	    		</td>
	    	</tr>	    		    	
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.elderlyName")}:</th>
	    		<td>
	    			 ${elderlyStuffDeposit.elderlyInfo.name}
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("yly.elderlyInfo.stuffDeposit.remark")}:</th>
	    		<td>
	    			 ${elderlyStuffDeposit.remark}
	    		</td>
	    	</tr>
	    </table>
</form>
	

