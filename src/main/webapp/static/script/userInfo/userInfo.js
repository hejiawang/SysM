layui.use([ 'layer', 'table', 'laydate', 'form'], function(){
    new UserInfo(layui).init();
});

var UserInfo = function(layui){
    {
        this.layer = layui.layer;
        this.table = layui.table;
        this.laydate = layui.laydate;
        this.form = layui.form;
    }
    
    {
        this.listUrl = webUrl + "/userInfo/list";
        this.raiseUrl = webUrl +  "/userInfo/raise";
        this.deleteUrl = webUrl + "/userInfo/delete";
        this.modifyUrl = webUrl + "/userInfo/modify";
    }

    {
        var sysmBodyH = $(".sysm-body").css("height"), sysmBodyW = $(".sysm-body").css("width");
        this.tableH = sysmBodyH.substring(0, sysmBodyH.length - 2);
        this.tableW = sysmBodyW.substring(0, sysmBodyW.length - 2);
        this.popupH =  (this.tableH - 50) + 'px';
        this.popupW =  (this.tableW - 200) + 'px';
    }

    this.educationalContent = ["无", "小学", "初中" , "高中", "大专", "本科", "研究生", "博士生"];
};

$.extend(UserInfo.prototype, {
    'init' : function () {
        this.initTableList();
        this.bindTableEvent();
        this.bingSearchEvent();
        this.verifyForm();
        this.submitForm();
        this.initBuild();
    },
    'initTableList' : function () {
        var _t = this;
        _t.table.render({
            id:'userInfoListTable',
            elem: '#userInfo_table',
            height: _t.tableH,
            url: _t.listUrl,
            page: true,
            cols: [[
                {field: 'id', minWidth:'0%', width:'0%', type:'space', style:'display:none'},
                {field: 'userName', title: '用户名', sort: true},
                {field: 'realName', title: '真实姓名', sort: true},
                {field: 'email', title: '邮箱', sort: false},
                {field: 'telephone', title: '电话', sort: false},
                {field: 'birtoday', title: '出生日期', sort: false},
                {field: 'educational', title: '教育背景', sort: false, templet: function(d){ return _t.educationalContent[d.educational]; }},
                {field: 'remark', title: '备注', sort: false},
                {fixed: 'right', width: 180, align:'center', toolbar: '#userInfo_bar'}
            ]]
        });
    },
    'verifyForm' : function () {
        this.form.verify({
            userName: [/(.+){5,20}$/, '登录名必须5到20位'],
            passWord: [/(.+){6,12}$/, '密码必须6到12位'],
            realName: [/(.+){1,5}$/, '真实姓名必须1到5位'],
        });
    },
    'submitForm' : function () {
        var _t = this;
        _t.form.on('submit(formFilter)', function (data) {
            ajax.post($("#userInfo_form").attr("action"), data.field, function (res) {
                layMsg.msg(res.result);
                if( res.result ) {
                    _t.userInfoReload();
                    _t.layer.closeAll();
                }
            });
            return false;
        });
    },
    'initBuild' : function () {
        this.laydate.render({ elem: '#birtoday', value: "" });
    },
    'bindTableEvent' : function () {
        var _t = this;
        _t.table.on('tool(userInfo_layFilter)', function(obj){
            var layEvent = obj.event;
            layEvent && _t[layEvent] && _t[layEvent](obj);
        });
    },
    'bingSearchEvent' : function () {
        var _t = this;
        $('.e_search .layui-btn').on('click', function(){
            var type = $(this).data('type');
            type && _t[type] && _t[type]();
        });
    },
    'userInfoSearch' : function () {
        this.table.reload('userInfoListTable',{
            page:{ curr: 1 },
            where: { name: $('#s_name').val() }
        });
    },
    'userInfoReload' : function () {
        $('#s_name').val("");
        this.userInfoSearch();
    },
    'userInfoRaise' : function () {
        var _t = this;

        _t.clearFormContent();
        $("#userInfo_form").attr("action", _t.raiseUrl);
        _t.layer.open({
            type: 1,
            title : '新增用户',
            maxmin: true,
            area: [ _t.popupW, _t.popupH ],
            content: $("#userInfoBody"),
            btn: [common.buttonYes, common.buttonNo],
            yes: function(index, layero){ $("#submitForm").trigger("click");  },
            btn2: function(index, layero){ _t.layer.close(index); }
        });
    },
    'clearFormContent' : function () {
        $("#userInfo_form input").val("");
        $("#userInfo_form textarea").val("");
        $("#educational").siblings("div .layui-form-select").find("dd[lay-value='0']").click();
    },
    'userInfoDetail' : function (obj) {
        var _t = this;

        _t.showUserInfo(obj.data);
        _t.layer.open({
            type: 1,
            title : '查看用户',
            maxmin: true,
            area: [ _t.popupW, _t.popupH ],
            content: $("#userInfoBody"),
            btn: [common.buttonNo],
            yes: function(index, layero){ _t.layer.close(index); },
        });
    },
    'showUserInfo' : function (data) {
        $("#id").val(data.id);
        $("#userName").val(data.userName);
        $("#passWord").val("123456");
        $("#realName").val(data.realName);
        $("#birtoday").val(data.birtoday);
        $("#telephone").val(data.telephone);
        $("#email").val(data.email);
        $("#educational").siblings("div .layui-form-select").find("dd[lay-value='"+data.educational+"']").click();
        $("#remark").val(data.remark);
    },
    'userInfoEdit' : function (obj) {
        var _t = this;

        _t.showUserInfo(obj.data);
        $("#userInfo_form").attr("action", _t.modifyUrl);
        _t.layer.open({
            type: 1,
            title : '编辑用户',
            maxmin: true,
            area: [ _t.popupW, _t.popupH ],
            content: $("#userInfoBody"),
            btn: [common.buttonYes, common.buttonNo],
            yes: function(index, layero){ $("#submitForm").trigger("click"); },
            btn2: function(index, layero){ _t.layer.close(index); }
        });
    },
    'userInfoDelete' : function (obj) {
        var _t = this;

        _t.layer.confirm( common.deleteConfirmTest , function(index){
            var id = obj.data.id;
            ajax.get(_t.deleteUrl, {'userInfo.id':id}, function (res) {
                layMsg.deleteMsg(res.result);
                _t.layer.close(index);
                _t.userInfoReload();
            });
        });
    }
});