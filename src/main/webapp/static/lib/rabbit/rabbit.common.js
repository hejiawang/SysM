var Common = function () {
    this.raiseSuccessText = "新增成功！";
    this.modifySuccessText = "修改成功！";
    this.deleteSuccessText = "删除成功！";
    this.viewSuccessText = "查看成功！";
    this.successText = "处理成功！";

    this.raiseFailText = "新增失败！";
    this.modifyFailText = "修改失败！";
    this.deleteFailText = "删除失败！";
    this.viewFailText = "查看失败！";
    this.failText = "处理失败！";

    this.RaiseConfirmTest = "确认新增？";
    this.modifyConfirmTest = "确认修改？";
    this.deleteConfirmTest = "确认删除？";
    this.viewConfirmTest = "查看删除？";

    this.buttonYes = "确定";
    this.buttonNo = "取消";
}

$.extend(Common.prototype, {
    "isBlank" : function (text) {
        if( text == null || text == "" || text.length <= 0 ){
            return true;
        } else {
            return false;
        }
    },
    'isNotBlank' : function (text) {
        return !this.isBlank(text);
    }
});

var common = new Common();
