package cn.lc.model.framework.network.retrofit;

import java.util.Map;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface NetAPI {

    @FormUrlEncoded
    @POST(NetUrl.login)
    Observable<LoginBean> login (@FieldMap Map<String,Object> map);
    @FormUrlEncoded
    @POST(NetUrl.capture)
    Observable<CaptchaBean> capture (@FieldMap Map<String,Object> map);
    @FormUrlEncoded
    @POST(NetUrl.checkcapture)
    Observable<CommonBean> checkcapture (@FieldMap Map<String,Object> map);
    @FormUrlEncoded
    @POST(NetUrl.register)
    Observable<CommonBean> register (@FieldMap Map<String,Object> map);

    /***************************************  订单 ****************************************************/
    // 获取维修订单列表
    @FormUrlEncoded
    @POST(NetUrl.getorder)
    Observable<StationeryBean> getorder (@FieldMap Map<String,Object> map);

    /**
     *  获取办公订单列表
     */
    @FormUrlEncoded
    @POST(NetUrl.getorder)
    Observable<StationeryNewBean> getStationeryorder(@FieldMap Map<String,Object> map);

    // 获取订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<OrderDetailBean> getorderDetail (@FieldMap Map<String,Object> map);
    // 获取办公用品订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<StationeryDetailBean> getStationeryDetail(@FieldMap Map<String,Object> map);

    // 接单
    @FormUrlEncoded
    @POST(NetUrl.receiveOrder)
    Observable<CommonBean> receiveOrder(@FieldMap Map<String,Object> map);
    // 完成订单
    @FormUrlEncoded
    @POST(NetUrl.finishOrder)
    Observable<CommonBean> finishOrder(@FieldMap Map<String,Object> map);
    // 取消订单
    @FormUrlEncoded
    @POST(NetUrl.cancelOrder)
    Observable<CommonBean> cancelOrder(@FieldMap Map<String,Object> map);
    // 立即配送
    @FormUrlEncoded
    @POST(NetUrl.peiSong)
    Observable<CommonBean> peiSongOrder(@FieldMap Map<String,Object> map);

    // 删除订单
    @FormUrlEncoded
    @POST(NetUrl.deleteOrder)
    Observable<CommonBean> deleteOrder(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.updatename)
    Observable<CommonBean> updatename (@FieldMap Map<String,Object> map);
}
