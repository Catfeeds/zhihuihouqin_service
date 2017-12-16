package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String login="service/user/login?";
    String capture="service/user/getCaptcha?";
    String checkcapture="service/user/checkCaptcha?";
    String register="service/user/register?";
    //办公用品订单列表
    String getorder="CommonOrder/orderList?";
    String updatename="user/modifyUserinfo?";
}
