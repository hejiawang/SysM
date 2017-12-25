layui.use([ 'layer', 'table', 'laydate', 'layedit'], function(){
    new CodeLog(layui).init();
});

var CodeLog = function(layui){
    {
        this.table = layui.table;
        this.layer = layui.layer;
        this.laydate = layui.laydate;
        this.layedit = layui.layedit;
    }

    {
        this.listUrl = webUrl + "/codeLog/list";
        this.raiseUrl = webUrl +  "/codeLog/raise";
        this.deleteUrl = webUrl + "/codeLog/delete";
        this.modifyUrl = webUrl + "/codeLog/modify";
    }

    {
        var sysmBodyH = $(".sysm-body").css("height"), sysmBodyW = $(".sysm-body").css("width");
        this.tableH = sysmBodyH.substring(0, sysmBodyH.length - 2);
        this.tableW = sysmBodyW.substring(0, sysmBodyW.length - 2);
        this.popupH =  (this.tableH - 50) + 'px';
        this.popupW =  (this.tableW - 200) + 'px';
    }

    this.layeditIndex = null;
};

$.extend(CodeLog.prototype, {
    'init' : function () {
        this.initTableList();
        this.bindTableEvent();
        this.bingSearchEvent();
    },
    'initTableList' : function () {
        var _t = this;
        _t.table.render({
            id:'codeLogListTable',
            elem: '#codeLog_table',
            height: _t.tableH,
            url: _t.listUrl,
            page: true,
            cols: [[
                {field: 'id', minWidth:'0%', width:'0%', type:'space', style:'display:none'},
                {field: 'date', title: '时间', sort: true},
                {field: 'content', title: '内容', sort: true},
                {fixed: 'right', width: 180, align:'center', toolbar: '#codeLog_bar'}
            ]]
        });
    },
    'bindTableEvent' :function () {
        var _t = this;
        _t.table.on('tool(codeLog_layFilter)', function(obj){
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
    'initBuild' : function () {
        this.laydate.render({ elem: '#codeLog_date', value: new Date() });
        this.layeditIndex = this.layedit.build('codeLog_content', {
            tool: [
                'strong' ,'italic'  ,'underline' ,'del' ,'|'  ,'left' ,'center' ,'right' ,'link' ,'unlink' ,'face'
            ]
        });
    },
    'codeLogSearch' : function () {
        this.table.reload('codeLogListTable',{
            page:{ curr: 1 },
            where: { content: $('#s_content').val() }
        });
    },
    'codeLogReload' : function () {
        $('#s_content').val("");
        this.codeLogSearch();
    },
    'codeLogDetail' : function (obj) {
        var _t = this, data = obj.data;

        _t.layer.open({
            type: 1,
            title : '查看日志',
            maxmin: true,
            area: [ _t.popupW, _t.popupH],
            content: $("#codeLogBody"),
            btn: [common.buttonNo],
            btn1: function(index, layero){  _t.layer.close(index); },
        });

        $("#codeLog_content").val(data.content);
        _t.initBuild();
        $("#codeLog_date").val(data.date);
    },
    'codeLogEdit' : function (obj) {
        var _t = this, data = obj.data;

        _t.layer.open({
            type: 1,
            title : '修改日志',
            maxmin: true,
            area: [ _t.popupW, _t.popupH ],
            content: $("#codeLogBody"),
            btn: [common.buttonYes, common.buttonNo],
            yes: function(index, layero){  _t.codeLogModifyCall(index, layero, obj); },
            btn2: function(index, layero){ _t.layer.close(index); }
        });

        $("#codeLog_content").val(data.content);
        _t.initBuild();
        $("#codeLog_date").val(data.date);
    },
    'codeLogModifyCall' : function (index, layero, obj) {
        var _t = this;
        if( !_t.checkCodeLogForm() ) return;

        var param = {
            'codeLog.id' : obj.data.id,
            'codeLog.date': $("#codeLog_date").val(),
            'codeLog.content' : _t.layedit.getContent(_t.layeditIndex)
        };
        ajax.get(_t.modifyUrl, param, function (res) {
            layMsg.modifyMsg(res.result);
            _t.layer.close(index);
            _t.codeLogReload();
        });
    },
    'codeLogRaise': function () {
        var _t = this;
        $("#codeLog_content").val("");

        _t.layer.open({
            type: 1,
            title : '新增日志',
            maxmin: true,
            area: [ _t.popupW, _t.popupH ],
            content: $("#codeLogBody"),
            btn: [common.buttonYes, common.buttonNo],
            yes: function(index, layero){  _t.codeLogRaiseCall(index, layero); },
            btn2: function(index, layero){ _t.layer.close(index); }
        });

        _t.initBuild();
    },
    'codeLogRaiseCall': function (index, layero) {
        var _t = this;
        if( !_t.checkCodeLogForm() ) return;

        var param = {
            'codeLog.date': $("#codeLog_date").val(),
            'codeLog.content' : _t.layedit.getContent(_t.layeditIndex)
        };
        ajax.get(_t.raiseUrl, param, function (res) {
            layMsg.raiseMsg(res.result);
            _t.layer.close(index);
            _t.codeLogReload();
        });
    },
    'codeLogDelete' : function (obj) {
        var _t = this;

        _t.layer.confirm( common.deleteConfirmTest , function(index){
            var id = obj.data.id;
            ajax.get(_t.deleteUrl, {'codeLog.id':id}, function (res) {
                layMsg.deleteMsg(res.result);
                _t.layer.close(index);
                _t.codeLogReload();
            });
        });
    },
    'checkCodeLogForm' : function () {
        var _t = this;

        var date = $("#codeLog_date").val();
        if( common.isBlank(date) ) {
            layMsg.msgError("请选择时间");
            return false;
        }

        var content =  _t.layedit.getContent(_t.layeditIndex);
        if( common.isBlank(content) ){
            layMsg.msgError("请填写内容");
            return false;
        }

        return true;
    }
});