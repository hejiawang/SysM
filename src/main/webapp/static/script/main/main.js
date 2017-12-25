;$(function () {
    ajax.get(webUrl + '/codeLog/listAll', null, function (res) {
        tmpl.render('#codeLogList', '#codeLogListTmpl', res.result);
    });
})