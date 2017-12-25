layui.use('element', function(){
    var element = layui.element, $ = layui.jquery;

    var active = {
        tabAdd: function(_t){
            _t.attr("data-menu-type", "tabChange");

            var html = '' +
                '<iframe name="iframe" frameborder="0" class="layui-content-ifram" src="' + webUrl + "/" + _t.data("menu-url") + '">'+
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