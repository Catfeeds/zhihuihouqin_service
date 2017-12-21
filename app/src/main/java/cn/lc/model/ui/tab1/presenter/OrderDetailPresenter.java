package cn.lc.model.ui.tab1.presenter;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.view.OrderDetailView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class OrderDetailPresenter extends MvpRxSimplePresenter<OrderDetailView> {


    public void getData(String serviceType,String orderid) {
        LogUtils.d("OrderDetailPresenter发出请求");
        Observable orderDetail = RetrofitUtils.getInstance().getOrderDetail(serviceType,orderid);
        getNetWork(orderDetail, new RetrofitCallBack<OrderDetailBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }
            @Override
            public void onSuccess(OrderDetailBean baseResponse) {
                getView().getSucc(baseResponse);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    // 获取办公用品得订单详情.
    public void getStationeryDetialData(String serviceType,String orderid){
        Observable observable = RetrofitUtils.getInstance().getStationeryDetail(serviceType, orderid);
        getNetWork(observable, new RetrofitCallBack<StationeryDetailBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...."+e);
            }

            @Override
            public void onSuccess(StationeryDetailBean stationeryDetailBean) {
                getView().getSucc(stationeryDetailBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    // 获取订水服务的订单详情
    public void getOrderWaterDetail(String serviceType,String orderid) {
        Observable waterDetail = RetrofitUtils.getInstance().getWaterDetail(serviceType,orderid);
        getNetWork(waterDetail, new RetrofitCallBack<OrderWaterDetailBean>() {

            @Override
            public void onPostFail(Throwable e) {

            }

            @Override
            public void onSuccess(OrderWaterDetailBean orderWaterDetailBean) {
                getView().getSucc(orderWaterDetailBean);
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
        LogUtils.d("即服务的请求发出");
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

    //拒绝接单
    public void refuseOrder(String serviceType,String orderId) {
        Observable observable = RetrofitUtils.getInstance().refuseOrder(serviceType, orderId);
        getNetWork(observable, new RetrofitCallBack<CommonBean>() {
            @Override
            public void onPostFail(Throwable e) {
                LogUtils.d("erre...." + e);
            }

            @Override
            public void onSuccess(CommonBean commonBean) {
                getView().showToast("拒绝成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    //取消订单
    public void cancelOrder(String serviceType,String orderId,String reason) {
        LogUtils.d("取消订单");
        Observable observable = RetrofitUtils.getInstance().cancelOrder(serviceType, orderId, reason);
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
