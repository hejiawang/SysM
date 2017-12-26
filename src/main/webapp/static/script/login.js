layui.use(['form', 'layer'], function(){
    new Login(layui).init();
});

var Login = function(layui){
    this.layer = layui.layer;
    this.form = layui.form;

    this.loginDoUrl = webUrl + "/loginDo";
    this.indexUrl = webUrl + "/index";
}

$.extend(Login.prototype, {
    'init' : function () {
        this.verifyForm();
        this.submitForm();
    },
    'verifyForm' : function () {
        this.form.verify({
            userName: function(value){
                if(value.length < 5 || value.length > 20){
                    return '用户名在5-20之间';
                }
            },
            passWord: [/(.+){6,12}$/, '密码必须6到12位']
        });
    },
    'submitForm' : function () {
        var _t = this;
        _t.form.on('submit(login)', function (data) {
            ajax.post(_t.loginDoUrl, data.field, function (res) {
                if( res.result ) {
                    location.href = _t.indexUrl;
                } else {
                    layMsg.error("登陆失败，请联系管理员");
                }
            });

            return false;
        });
    }
});