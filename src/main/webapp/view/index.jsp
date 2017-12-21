<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="../../static/lib/layui/css/layui.css">
    <link href="../../static/lib/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style type="text/css">
        .layui-tab-item {
            display: none;
            height: 100%
        }
        .hvr-icon-pulse {
            display: inline-block;
            vertical-align: middle;
            -webkit-transform: translateZ(0);
            transform: translateZ(0);
            box-shadow: 0 0 1px rgba(0, 0, 0, 0);
            -webkit-backface-visibility: hidden;
            backface-visibility: hidden;
            -moz-osx-font-smoothing: grayscale;
            position: relative;
        }
        .layui-side .layui-nav-tree .layui-nav-item a i{
            display: block;
            text-align: center;
            width: 50px;
            line-height: 45px;
            float: left;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
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
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree e_menu_ul"  lay-filter="lay_menu">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;"><i class="fa fa-coffee fa-fw hvr-icon-pulse"> </i><span> 父级菜单</span></a>
                    <dl class="layui-nav-child">
                        <dd><a class="e_menu" data-menu-url="test/pageIndex" data-menu-title="名称一" data-menu-type="tabAdd" href="javascript:;">列表一</a></dd>
                        <dd><a class="e_menu" data-menu-url="test/pageIndex2" data-menu-title="名称二" data-menu-type="tabAdd" href="javascript:;">列表二</a></dd>
                        <!--<dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
                <!--<li class="layui-nav-item"><a href="">云市场</a></li>-->
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab layui-tab-brief" lay-filter="lay_menu_tab" lay-allowclose="true" style="position: absolute; bottom: 51px; top: 0px; left: 0px; right: 0px; margin-top: 0px;">
            <ul class="layui-tab-title">
                <!--
                <li class="layui-this" lay-id="test/pageIndex">网站设置</li>
                <li lay-id="test/pageIndex2">用户管理</li>
                -->
            </ul>
            <div class="layui-tab-content" style="height: 100%">
                <!--
                <div class="layui-tab-item layui-show" >
                    <iframe name="iframe" id="iframe" frameborder="0" scrolling="yes" style="width: 100%; height: 100%; padding-bottom: 0px; box-sizing: border-box; " src="http://localhost:8080/test/pageIndex">

                    </iframe>
                </div>
                <div class="layui-tab-item" >
                    <iframe name="iframe" id="iframe2" frameborder="0" scrolling="yes" style="width: 100%; height: 100%; padding-bottom: 0px; box-sizing: border-box; " src="http://localhost:8080/test/pageIndex2">

                    </iframe>
                </div>
                -->
            </div>
        </div>

        <div class="layui-footer">
            © layui.com - 底部固定区域
        </div>

    </div>


</div>
<script src="../../static/lib/layui/layui.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element, $ = layui.jquery;

        var active = {
            tabAdd: function(_t){
                _t.attr("data-menu-type", "tabChange");

                var html = '' +
                    '<iframe name="iframe" frameborder="0" scrolling="yes" style="width: 100%; height: 100%; padding-bottom: 0px; box-sizing: border-box; " src="http://localhost:8080/' + _t.data("menu-url") + '">'+
                    '</iframe>';

                element.tabAdd('lay_menu_tab', {
                    title: _t.data("menu-title"),
                    content: html,
                    id: _t.data("menu-url")
                });

                $(".layui-tab-title").children().attr("class", "");
                $(".layui-tab-title").children().last().attr("class", "layui-this");

                $(".layui-tab-content").children().attr("class", "layui-tab-item");
                $(".layui-tab-content").children().last().attr("class", "layui-tab-item layui-show");
            },
            tabChange: function(_t){
                element.tabChange('lay_menu_tab', _t.data("menu-url"));
            }
        };

        $('.e_menu').on('click', function(){
            var _t = $(this), menuType = _t.attr("data-menu-type");
            active[menuType] ? active[menuType].call(this, _t) : '';
        });

        element.on('tabDelete(lay_menu_tab)', function(data){
            $('[data-menu-url="'+$(this).parent().attr("lay-id")+'"]').attr("data-menu-type", "tabAdd");
        });

        element.on('tab(lay_menu_tab)', function(data){
            $(".e_menu_ul dd").attr("class", "");
            $('[data-menu-url="'+$(this).attr("lay-id")+'"]').parent().attr("class", "layui-this");
        });
    });
</script>
</body>
</html>