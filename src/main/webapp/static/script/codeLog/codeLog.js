layui.use([ 'layer', 'table', 'laydate', 'layedit'], function(){
    new CodeLog(layui).init();
});

var CodeLog = function(layui){
    this.table = layui.table;
    this.layer = layui.layer;
    this.laydate = layui.laydate;
    this.layedit = layui.layedit;

    this.layeditIndex = null;

    this.listUrl = "/codeLog/list";
    this.raiseUrl = "/codeLog/raise";
};

$.extend(CodeLog.prototype, {
    'init' : function () {
        this.initTableList();
        this.bindTableEvent();
        this.bingSearchEvent();
    },
    'initTableList' : function () {
        var _t = this, tableH = $(".sysm-body").css("height"), tableH = tableH.substring(0, tableH.length - 2);
        _t.table.render({
            id:'codeLogListTable',
            elem: '#codeLog_table',
            height: tableH,
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
        layMsg.modifySuccessMsg();
        //var data = obj.data; //获得当前行数据
    },
    'codeLogEdit' : function (obj) {
        layMsg.deleteSuccessMsg();
    },
    'codeLogRaise': function () {
        var _t = this,
            tableH = $(".sysm-body").css("height"),  tableH = tableH.substring(0, tableH.length - 2) - 50,
            tableW = $(".sysm-body").css("width"), tableW = tableW.substring(0, tableW.length - 2) - 200;

        _t.layer.open({
            type: 1,
            title : '新增日志',
            maxmin: true,
            area: [ tableW + 'px', tableH + 'px'],
            content: $("#codeLogRaise"),
            btn: [common.buttonYes, common.buttonNo],
            yes: function(index, layero){  _t.codeLogRaiseCall(index, layero); },
            btn2: function(index, layero){ _t.layer.close(index); }
        });

        _t.initBuild();
    },
    'codeLogRaiseCall': function (index, layero) {
        var _t = this,
            param = {
            'codeLog.date': $("#codeLog_date").val(),
            'codeLog.content' : _t.layedit.getContent(_t.layeditIndex)
        };
        ajax.get(_t.raiseUrl, param, function (res) {
            _t.layer.close(index);
            layMsg.raiseSuccessMsg();
            _t.codeLogReload();
        });
    },
    'codeLogDelete' : function (obj) {
        this.layer.confirm( common.deleteConfirmTest , function(index){
            layer.close(index);
        });
    }
});