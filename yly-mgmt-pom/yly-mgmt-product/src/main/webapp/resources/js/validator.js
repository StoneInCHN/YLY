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