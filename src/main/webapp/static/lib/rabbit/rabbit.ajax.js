function Ajax() {
	this.ajax = function(url, type, datatype, data, async, callback) {
        if (type != 'GET') data = JSON.stringify(data);
		$.ajax({url: url, type: type, dataType: datatype, async: async, contentType: "application/json", data: data,
            success: function (res) {
                if (datatype == 'json') {
                    if (res.code == 0) {
                        callback && callback(res);
                    } else {
                        // alert("错误提示:" + res.message);
                    }
                } else {
                    callback && callback(res);
                }
            },
            error: function(xhr, errorType, error) {
                // alert("请求超时，请重试！");
            }
        });
	}
}

Ajax.prototype.getHtml = function (url, data, callback) {
    this.ajax(url, 'GET', 'html', data, true, callback);
}

/**
 * 异步请求
 */
Ajax.prototype.get = function(url, data, callback) {
	this.ajax(url, 'GET', 'json', data, true, callback);
}
Ajax.prototype.post = function(url, data, callback) {
	this.ajax(url, 'POST', 'json', data, true, callback);
}

Ajax.prototype.put = function(url, data, callback) {
	this.ajax(url, 'PUT', 'json', data, true, callback);
}

Ajax.prototype.delete = function(url, data, callback) {
	this.ajax(url, 'DELETE', 'json', data, true, callback);
}

Ajax.prototype.jsonp = function(url, data, callback) {
	this.ajax(url, 'GET', 'jsonp', data, true, callback);
}

/**
 * 同步请求
 */
Ajax.prototype.sget = function(url, data, callback) {
	this.ajax(url, 'GET', 'json', data, false, callback);
}
Ajax.prototype.spost = function(url, data, callback) {
	this.ajax(url, 'POST', 'json', data, false, callback);
}

Ajax.prototype.sput = function(url, data, callback) {
	this.ajax(url, 'PUT', 'json', data, false, callback);
}

Ajax.prototype.sdelete = function(url, data, callback) {
	this.ajax(url, 'DELETE', 'json', data, false, callback);
}

Ajax.prototype.sjsonp = function(url, data, callback) {
	this.ajax(url, 'GET', 'jsonp', data, false, callback);
}

Ajax.prototype.constructor = Ajax;

var ajax = new Ajax();