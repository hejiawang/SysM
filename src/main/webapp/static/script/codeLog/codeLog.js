layui.use(['laypage', 'layer', 'table', 'element'], function(){
    new ActivityEvent(layui).init();
});

var ActivityEvent = function(layui){
    this.table = layui.table;
    this.layer = layui.layer;

    this.listUrl = "/codeLog/list";
};

$.extend(ActivityEvent.prototype, {
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
        layer.msg('查看操作');
        var data = obj.data; //获得当前行数据
    },
    'codeLogEdit' : function (obj) {
        layer.msg('修改操作');
    },
    'codeLogRaise': function () {

    },
    'codeLogDelete' : function (obj) {
        layer.confirm('真的删除行么？', function(index){
            //obj.del(); //删除对应行（tr）的DOM结构
            layer.close(index);
        });
    }
});