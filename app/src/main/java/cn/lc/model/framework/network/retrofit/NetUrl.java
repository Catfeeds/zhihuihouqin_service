package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String login="service/user/login?";
    String capture="service/user/getCaptcha?";
    String checkcapture="service/user/checkCaptcha?";
    String register="service/user/register?";

    //订单列表
    String getorder="CommonOrder/orderList?";                   // 获取订单列表
    String getOrderDetail = "CommonOrder/orderDetail?";         // 获取订单详情
    String receiveOrder = "CommonOrder/instantService?";         // 接收订单.
    String finishOrder = "CommonOrder/finishOrder?";            // 结束订单.
    String deleteOrder = "CommonOrder/deleteOrder?";            // 删除订单.


    String updatename="user/modifyUserinfo?";
}
