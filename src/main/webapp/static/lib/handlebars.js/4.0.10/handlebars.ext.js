/**
 * handlebars 扩展函数
 */
Handlebars.registerHelper("equals", function(v1, v2, options) {
    return v1 == v2 ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper("isNotEmpty", function(v, options) {
    return v != null && v.length > 0 ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper("add", function(v1, v2, options) {
    return v1 + v2;
});

Handlebars.registerHelper("convert", function(type, v, options) {
	var types = {
		'caseType': {'Water': '水污染', 'Air': '大气污染', 'Noise': '噪声污染', 'SolidWaste': '固废污染', 'Radiation': '辐射污染', 'Other': '其它'},
		'sex': {'Male': '男', 'Female': '女', 'Unknown': '未知', 'Secrecy': '保密'}
	}
	return types[type] ? types[type][v] : v;
});

Handlebars.registerHelper({  
	'randomNum': function (num) {
		var res = 0;
		for (var i = 0; i < num; i++) {
			res += Math.ceil(Math.random() * 10);
		}
		return res;
	},
    'prettifyDate' : function(timestamp) {//格式化时间  
        var format = 'YYYY-MM-DD';  
        if(arguments.length > 2){  
            format = arguments[1];  
        }  
        if(timestamp){  
            return moment(new Date(timestamp)).format(format);  
        } else　{  
            return '';  
        }  
    },//格式化数字默认空为''  
    'prettifyNumber': function(number) {  
        var format = '0.00', zero = true;  
        if(arguments.length > 2){  
            format = arguments[1];  
        }  
        if(arguments.length > 3){  
            zero = arguments[2];  
        }  
          
        return (zero ? number!=null : !!number) ? numeral(number).format(format):'';  
          
    },//字符去空格  
    'prettifyStr': function(s) {  
        return s && s.replace(/\s/g, " " ); ;  
          
    },//格式化数字默认空为0  
    'prettifyPositive': function(number) {  
        var format = '0.00', zero = true;  
        if(arguments.length > 2){  
            format = arguments[1];  
        }  
        if(arguments.length > 3){  
            zero = arguments[2];  
        }  
          
        return (zero ? number!=null&&number>0 : !!number) ? numeral(number).format(format) : '0';  
          
    },//减  
    'subtract': function(number1, number2) {  
        var format = '0.00';  
        if(arguments.length > 3){  
            format = arguments[2];  
        }  
        var number = number1 - number2;  
        return number ? numeral(number).format(format) : '';  
    },//加  
    'computeAdd': function() {  
        var big = 0;  
        try{  
            var len = arguments.length - 1;  
            for(var i = 0; i < len; i++){  
                if(arguments[i]){  
                    big = eval(big +"+"+ arguments[i]);  
                }  
            }  
        }catch(e){  
            throw new Error('Handlerbars Helper "computeAdd" can not deal with wrong expression:'+arguments);  
        }  
        return big;  
    }  
      
});  