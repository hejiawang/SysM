<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>首页</title>
    <link href="../../static/lib/layui/css/layui.css" rel="stylesheet">
    <link href="../../static/css/app.css" rel="stylesheet">
</head>
<body class="layui-layout-body">
    <blockquote class="layui-elem-quote news_search">
        <div class="e_search">
            搜索内容：
            <div class="layui-inline">
                <input class="layui-input" name="s_content" id="s_content" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="codeLogSearch" >搜索</button>
            <button class="layui-btn" data-type="codeLogReload" >重置</button>
            <button class="layui-btn" data-type="codeLogRaise" >新增</button>
        </div>
    </blockquote>

    <div class="sysm-body">
        <table class="layui-hide" id="codeLog_table" lay-filter="codeLog_layFilter"></table>
        <script type="text/html" id="codeLog_bar">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="codeLogDetail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="codeLogEdit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="codeLogDelete">删除</a>
        </script>
    </div>

    <script src="../../static/lib/layui/layui.js"></script>
    <script src="../../static/lib/jquery/3.2.1/jquery.js"></script>
    <script src="../../static/script/codeLog/codeLog.js"></script>

</body>
</html>