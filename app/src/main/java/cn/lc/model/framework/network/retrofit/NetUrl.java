package cn.lc.model.framework.network.retrofit;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetUrl {

    String login="user/login?";
    String capture="user/getCaptcha?";
    String checkcapture="user/checkCaptcha?";
    String register="user/register?";
    //办公用品订单列表
    String getorder="CommonOrder/orderList?";
    String updatename="user/modifyUserinfo?";
}
