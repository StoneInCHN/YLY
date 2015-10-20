$.extend($.fn.validatebox.defaults.rules, {  
        //移动手机号码验证  
        mobile: {
            validator: function (value) {  
                var reg = /^1[3|4|5|7|8][0-9]\d{8}$/;  
                return reg.test(value);  
            },  
            message: '号码格式错误'  
        }     
})  

$.extend($.fn.validatebox.defaults.rules, {
	   idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
	    }
})