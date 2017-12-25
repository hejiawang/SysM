function Tmpl() {
	this.tmpl = function(/*模板内容*/ content, /*渲染数据*/ data) {
		var template = Handlebars.compile(content);
		return template({"datas": data}); 
	}
}

/*获取模板处理后数据*/
Tmpl.prototype.compile = function (/*模板ID*/ tmplId, /*渲染数据*/ data) {
    return this.tmpl($(tmplId).html(), data);
}

/*覆盖渲染*/
Tmpl.prototype.render = function (/*jquery样式选择元素*/ jqSelecter, /*模板ID*/ tmplId, /*渲染数据*/ data) {
	if (data) {
		$(jqSelecter).html(this.compile(tmplId, data));
	} else {
		$(jqSelecter).html('');
	}
};

/*拼接渲染*/
Tmpl.prototype.append = function (/*jquery样式选择元素*/ jqSelecter, /*模板ID*/ tmplId, /*渲染数据*/ data) {
	if (data) {
		$(jqSelecter).append(this.compile(tmplId, data));
	} else {
		$(jqSelecter).html('');
	}
}

Tmpl.prototype.constructor = Tmpl;

var tmpl = new Tmpl();