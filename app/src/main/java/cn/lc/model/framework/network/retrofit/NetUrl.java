package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String login="service/user/login?";
    String capture="service/user/getCaptcha?";
    String checkcapture="service/user/checkCaptcha?";
    String register="service/user/register?";

    //订单
    String getorder="CommonOrder/orderList?";                   // 获取订单列表
    String getOrderDetail = "CommonOrder/orderDetail?";         // 获取订单详情
    String receiveOrder = "CommonOrder/instantService?";         // 接收订单.
    String refuseOrder = "CommonOrder/refuseOrder?";            // 拒绝接单.
    String finishOrder = "CommonOrder/finishOrder?";            // 结束订单.
    String deleteOrder = "CommonOrder/deleteOrder?";            // 删除订单.
    String cancelOrder = "CommonOrder/cancelOrder?";            // 取消订单.
    String peiSong = "CommonOrder/immediateDelivery?";          // 立即配送

    // 用户信息
    String getPersonInfo = "Owner/getUserinfo";                 // 获取用户信息.
    String getEvaluateData = "Owner/commentlist";               // 获取我的评价信息.
    // TODO: 2017/12/20 地址错误
    String getIncomeData = "Owner/commentlist";                 // 获取用户收益信息.
    String getIncomeList = "Owner/commentlist";                 // 获取用户收益列表.
    String changePW = "Owner/commentlist";                      // 修改密码.

    String updatename="user/modifyUserinfo?";
}
