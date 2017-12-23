var LayMsg = function () {
    this.layer = null;

    var _t = this;
    layui.use([ 'layer'], function(){ _t.layer = layui.layer; });
}

$.extend(LayMsg.prototype, {
    'msg' : function ( info ) {
        this.layer.msg(info);
    },
    'raiseSuccessMsg' : function () {
        this.msg(common.raiseSuccessText);
    },
    'modifySuccessMsg' : function () {
        this.msg(common.modifySuccessText);
    },
    'deleteSuccessMsg' : function () {
        this.msg(common.deleteSuccessText);
    }
});

var layMsg = new LayMsg();