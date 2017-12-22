package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab1Presenter extends MvpRxSimplePresenter<Tab1View> {

    public void getOrder(String serviceType, String page, String limit, String orderstatus) {
        LogUtils.d("tab1_1发出请求");
        Observable login = RetrofitUtils.getInstance().getOrder(serviceType , page , limit , orderstatus);
        getNetWork(login, new RetrofitCallBack<StationeryBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(StationeryBean baseResponse) {
                Log.e("TAG","11111111111111111111111");
                getView().getSucc(baseResponse);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 获取办公用品订单列表:
    public void getStationeryOrder(String serviceType, String page, String limit, String orderstatus) {
        Observable stationeryOrder = RetrofitUtils.getInstance().getStationeryOrder(serviceType, page, limit, orderstatus);
        getNetWork(stationeryOrder,new RetrofitCallBack<StationeryNewBean>() {

            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(StationeryNewBean stationeryNewBean) {
                getView().getSucc(stationeryNewBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     *  获取订水服务订单列表
     */
    public void getWaterOrder(String serviceType, String page, String limit, String orderstatus) {
        Observable waterOder = RetrofitUtils.getInstance().getWaterOrder(serviceType,page,limit,orderstatus);
        getNetWork(waterOder, new RetrofitCallBack<OrderWaterBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(OrderWaterBean orderWaterBean) {
                getView().getSucc(orderWaterBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取会议室预定的订单列表
     */
    public void getMeetingOrder(String serviceType, String page, String limit, String orderstatus) {
        Observable meetingOrder = RetrofitUtils.getInstance().getMeetingOrder(serviceType, page, limit, orderstatus);
        getNetWork(meetingOrder, new RetrofitCallBack<MeetingBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(MeetingBean bean) {
                getView().getSucc(bean);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    //立即配送
    public void peiSongOrder(String serviceType,String orderId) {
        LogUtils.d("配送商品");
        Observable observable = RetrofitUtils.getInstance().peiSongOrder(serviceType, orderId);
        getNetWork(observable, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
                getView().showToast(e.toString());
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("开始配送");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 维修人员立即服务/接单
    public void goService(String serviceType,String orderId){
        LogUtils.d("立即服务的请求发出");
        Observable receiveOrder = RetrofitUtils.getInstance().receiveOrder(serviceType, orderId);
        getNetWork(receiveOrder, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
                getView().showToast("下单失败");
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("下单成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //取消订单
    public void cancelOrder(String serviceType,String orderId,String reason) {
        LogUtils.d("取消订单");
        Observable observable = RetrofitUtils.getInstance().cancelOrder(serviceType, orderId,reason);
        getNetWork(observable, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
                getView().showToast("订单取消失败");
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("订单取消成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 维修人员结束订单/订单完成
    public void finishService(String serviceType,String orderId) {
        LogUtils.d("结束订单");
        Observable finishOrder = RetrofitUtils.getInstance().finishOrder(serviceType, orderId);
        getNetWork(finishOrder,new RetrofitCallBack<CommonBean>(){

            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
                getView().showToast("网络异常请重试");
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("订单已结束");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 删除已完成/已取消订单.
    public void deleteOrder(String serviceType,String orderId) {
        LogUtils.d("删除订单");
        Observable deleteOrder = RetrofitUtils.getInstance().deleteOrder(serviceType, orderId);
        getNetWork(deleteOrder,new RetrofitCallBack<CommonBean>(){

            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("订单已删除");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
