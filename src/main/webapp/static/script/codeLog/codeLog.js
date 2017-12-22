layui.use([ 'layer', 'table'], function(){
    new CodeLog(layui).init();
});

var CodeLog = function(layui){
    this.table = layui.table;
    this.layer = layui.layer;

    this.listUrl = "/codeLog/list";
    this.raisePageUrl = "/codeLog/raisePage";
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
        this.layer.msg('查看操作');
        var data = obj.data; //获得当前行数据
    },
    'codeLogEdit' : function (obj) {
        this.layer.msg('修改操作');
    },
    'codeLogRaise': function () {
        var _t = this,
            tableH = $(".sysm-body").css("height"),  tableH = tableH.substring(0, tableH.length - 2) - 50,
            tableW = $(".sysm-body").css("width"), tableW = tableW.substring(0, tableW.length - 2) - 200;

        _t.layer.open({
            type: 2,
            title : '新增记录',
            maxmin: true,
            area: [ tableW + 'px', tableH + 'px'],
            content: _t.raisePageUrl,
            btn: ['确定', '取消'],
            yes: function(index, layero){
                console.info(layero);
            },
            btn2: function(index, layero){  _t.layer.close(index); }
        });
    },
    'codeLogDelete' : function (obj) {
        this.layer.confirm('真的删除行么？', function(index){
            //obj.del(); //删除对应行（tr）的DOM结构
            layer.close(index);
        });
    }
});