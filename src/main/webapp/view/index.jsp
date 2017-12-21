<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理系统</title>
    <link href="../../static/lib/layui/css/layui.css" rel="stylesheet">
    <link href="../../static/lib/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../static/css/app.css" rel="stylesheet">
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">后台管理系统</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="../../static/image/headPortrait.png" class="layui-nav-img">
                        HeJiawang
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">基本资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                        <dd><a href="">退出系统</a></dd>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree e_menu_ul"  lay-filter="lay_menu">
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;"><i class="fa fa-coffee fa-fw hvr-icon-pulse"> </i><span> 父级菜单</span></a>
                        <dl class="layui-nav-child">
                            <dd><a class="e_menu" data-menu-url="test/pageIndex" data-menu-title="名称一" data-menu-type="tabAdd" href="javascript:;">列表一</a></dd>
                            <dd><a class="e_menu" data-menu-url="test/pageIndex2" data-menu-title="名称二" data-menu-type="tabAdd" href="javascript:;">列表二</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <div class="layui-body">
            <div class="layui-tab layui-tab-brief" lay-filter="lay_menu_tab" lay-allowclose="true" >
                <ul class="layui-tab-title">
                    <li class="layui-this" lay-id="main-page">首页</li>
                </ul>
                <div class="layui-tab-content" style="height: 100%">
                    <div class="layui-tab-item layui-show" >
                        <iframe name="iframe" id="iframe" frameborder="0" class="layui-content-ifram" src="/main/index">
                        </iframe>
                    </div>
                </div>
            </div>

            <div class="layui-footer footer">
                底部固定区域
            </div>
        </div>
    </div>

    <script src="../../static/lib/layui/layui.js"></script>
    <script src="../../static/script/index.js"></script>
</body>
</html>