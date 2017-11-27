package cn.lc.model.framework.network.retrofit;

import java.util.Map;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
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
    @FormUrlEncoded
    @POST(NetUrl.getorder)
    Observable<StationeryBean> getorder (@FieldMap Map<String,Object> map);
    @FormUrlEncoded
    @POST(NetUrl.updatename)
    Observable<CommonBean> updatename (@FieldMap Map<String,Object> map);
}
