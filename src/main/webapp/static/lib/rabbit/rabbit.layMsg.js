var LayMsg = function () {
    this.layer = null;

    var _t = this;
    layui.use([ 'layer'], function(){ _t.layer = layui.layer; });
}

$.extend(LayMsg.prototype, {
    'msgInfo' : function ( info ) {
        this.layer.msg(info, {icon: 6});
        //this.layer.msg(info);
    },
    'msgError' : function (info) {
        this.layer.msg(info, {icon: 5});
    },
    'msg' : function (isSuccess) {
        if( isSuccess ){
            this.successMsg();
        } else {
            this.failMsg();
        }
    },
    'successMsg' : function () {
        this.msgInfo(common.successText);
    },
    'failMsg' : function () {
        this.msgError(common.failText);
    },
    'raiseMsg' : function ( isSuccess ) {
        if( isSuccess ){
            this.raiseSuccessMsg();
        } else {
            this.raiseFailMsg();
        }
    },
    'raiseSuccessMsg' : function () {
        this.msgInfo(common.raiseSuccessText);
    },
    'raiseFailMsg' : function () {
        this.msgError(common.raiseFailText);
    },
    'modifyMsg' :  function (isSuccess) {
        if( isSuccess ){
            this.modifySuccessMsg();
        } else {
            this.modifyFailMsg();
        }
    },
    'modifySuccessMsg' : function () {
        this.msgInfo(common.modifySuccessText);
    },
    'modifyFailMsg' : function () {
        this.msgError(common.modifyFailText);
    },
    'deleteMsg' : function (isSuccess) {
        if( isSuccess ){
            this.deleteSuccessMsg();
        } else {
            this.deleteFailMsg();
        }
    },
    'deleteSuccessMsg' : function () {
        this.msgInfo(common.deleteSuccessText);
    },
    'deleteFailMsg' : function () {
        this.msgError(common.deleteFailText);
    }
});

var layMsg = new LayMsg();