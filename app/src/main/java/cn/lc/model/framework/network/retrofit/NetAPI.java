package cn.lc.model.framework.network.retrofit;

import java.util.Map;

import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.MeetingDetailBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;
import cn.lc.model.ui.tab2.bean.OrderMessageBean;
import cn.lc.model.ui.tab3.bean.EvaluateBean;
import cn.lc.model.ui.tab3.bean.IncomeBean;
import cn.lc.model.ui.tab3.bean.IncomeDetailBean;
import cn.lc.model.ui.tab3.bean.PersonInfoBean;
import cn.lc.model.ui.tab3.bean.PhotoBean;
import cn.lc.model.ui.tab3.bean.VersionInfoBean;
import cn.lc.model.ui.tab3.bean.WalletHomeBean;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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

    /**
     *  获取订水服务的订单
     */
    @FormUrlEncoded
    @POST(NetUrl.getorder)
    Observable<OrderWaterBean> getOrderWaterOrder(@FieldMap Map<String,Object> map);

    /**
     *  获取会议室预定的订单列表
     */
    @FormUrlEncoded
    @POST(NetUrl.getorder)
    Observable<MeetingBean> getMeetingOrder(@FieldMap Map<String,Object> map);

    // 获取订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<OrderDetailBean> getorderDetail (@FieldMap Map<String,Object> map);
    // 获取办公用品订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<StationeryDetailBean> getStationeryDetail(@FieldMap Map<String,Object> map);
    // 获取订水服务的订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<OrderWaterDetailBean> getWaterDetial(@FieldMap Map<String,Object> map);
    // 获取会议室预定服务订单详情
    @FormUrlEncoded
    @POST(NetUrl.getOrderDetail)
    Observable<MeetingDetailBean> getMeetingDetail(@FieldMap Map<String,Object> map);

    // 接单
    @FormUrlEncoded
    @POST(NetUrl.receiveOrder)
    Observable<CommonBean> receiveOrder(@FieldMap Map<String,Object> map);
    // 拒绝接单
    @FormUrlEncoded
    @POST(NetUrl.refuseOrder)
    Observable<CommonBean> refuseOrder(@FieldMap Map<String,Object> map);

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

    // 删除已完成订单
    @FormUrlEncoded
    @POST(NetUrl.deleteFinishOrder)
    Observable<CommonBean> deleteFinishOrder(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.updatename)
    Observable<CommonBean> updatename (@FieldMap Map<String,Object> map);

    /*********************************  用户信息 ************************************************/

    @FormUrlEncoded
    @POST(NetUrl.getPersonInfo)
    Observable<PersonInfoBean> getPersonInfo(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.getEvaluateData)
    Observable<EvaluateBean> getEvaluateData(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.getIncomeData)
    Observable<IncomeBean> getIncomeData(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.getIncomeList)
    Observable<IncomeDetailBean> getIncomeList(@FieldMap Map<String,Object> map);

    @FormUrlEncoded
    @POST(NetUrl.changePW)
    Observable<CommonBean> changePW(@FieldMap Map<String,Object> map);
    // 获取版本信息
    @FormUrlEncoded
    @POST(NetUrl.getVersionInfo)
    Observable<VersionInfoBean> getVersionInfo(@FieldMap Map<String,Object> map);
    // 意见反馈
    @FormUrlEncoded
    @POST(NetUrl.advice)
    Observable<CommonBean> advice(@FieldMap Map<String,Object> map);
    // 上传头像
    @POST(NetUrl.upPhoto)
    Observable<PhotoBean> upPhoto(@Body RequestBody Body);

    // 钱包
    @FormUrlEncoded
    @POST(NetUrl.advice)
    Observable<WalletHomeBean> getWalletHome(@FieldMap Map<String,Object> map);

    /*************************************  消息 *************************************************/
    //订单消息
    @FormUrlEncoded
    @POST(NetUrl.getOrderMessage)
    Observable<OrderMessageBean> getOrderMessage(@FieldMap Map<String,Object> map);
}
