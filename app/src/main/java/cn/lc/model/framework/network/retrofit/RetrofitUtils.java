package cn.lc.model.framework.network.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.framework.network.AppConstants;
import cn.lc.model.framework.network.ParameterKeys;
import cn.lc.model.framework.network.ServerConstants;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import mvp.cn.util.DateUtil;
import mvp.cn.util.Md5Util;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RetrofitUtils implements AppConstants, ServerConstants {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static NetAPI api;
    private static RetrofitUtils instance;

    private static RetrofitUtils createApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(NetAPI.class);
        return new RetrofitUtils();
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.d("retrofit", "retrofitBack = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    connectTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS).
                    addInterceptor(loggingInterceptor).build();
        }
        return okHttpClient;
    }
    private static String getSign(String biz, String timestamp) throws UnsupportedEncodingException {
        return Md5Util.getMD5(URLEncoder.encode(biz, "UTF-8") + timestamp + Constants.KEY_SECRET);
    }
    private static void addParam(Map<String, Object> paramsMap, Map<String, String> tempMap) {
        Gson gson = new Gson();
        String biz = gson.toJson(tempMap);
        paramsMap.put(ParameterKeys.BIZ, biz);
        LogUtils.d("biz请求参数：" + biz);
        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
        paramsMap.put(ParameterKeys.TIMESTAMP, timestamp);
        LogUtils.d("时间戳请求参数：" + timestamp);
        SoftApplication sf = new SoftApplication();
        sf.getToken();
        LogUtils.d("token::" + sf.getToken());
        if (sf.getToken() != null && !sf.getToken().equals("")) {
            paramsMap.put(ParameterKeys.TOKEN, sf.getToken());
        } else {
            paramsMap.put(ParameterKeys.TOKEN, "");
        }
            try {
                paramsMap.put(ParameterKeys.SIGN, getSign(biz, timestamp));
                //   LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            createApi();
        }
        return instance;
    }

    //-------------------------------------------请求内容----------------------------------------------

    /**
     * 登陆
     *
     *
     * @param pwd
     * @return
     */
    public static Observable login(String username, String pwd,String service) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("username", username);
            tempMap.put("password", pwd);
            tempMap.put("servicetypeid", service);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.login(paramsMap);
    }

    /**
     * 获取验证码
     */
    public static Observable capture(String mobile) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("mobile", mobile);

            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.capture(paramsMap);
    }
    /**
     * 校验验证码
     */
    public static Observable checkcapture(String mobile,String serviceid,String capture ,String uid)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("mobile", mobile);
            tempMap.put("servicetypeid", serviceid);
            tempMap.put("captcha", capture);
            tempMap.put("uid", uid);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.checkcapture(paramsMap);
    }
    /**
     * 注册
     * @return
     */
    public static Observable register(String mobile,String password,String servicetypeid ,String captcha)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("username", mobile);
            tempMap.put("password", password);
            tempMap.put("servicetypeid", servicetypeid);
            tempMap.put("captcha",captcha);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.register(paramsMap);
    }
    /********************************* 用户信息 ***********************************************/
    /**
     *  获取用户信息
     */
    public static Observable getPersonInfo(String serviceType) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getPersonInfo(paramsMap);
    }

    /**
     *  获取用户评价信息
     */
    public static Observable getEvaluateData(String serviceType,String scoretype,String page) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("servicetype",serviceType);
            tempMap.put("scoretype",scoretype);
            tempMap.put("page",page);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getEvaluateData(paramsMap);
    }

    /**
     *  获取用户的收益首页
     */
    public static Observable getIncomeData(String serviceType){
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceTypeId",serviceType);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getIncomeData(paramsMap);
    }

    /**
     *  获取用户的收益列表.
     */
    public static Observable getIncomeList(String serviceTypeId,String page,String limit){
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceTypeId",serviceTypeId);
            tempMap.put("page",page);
            tempMap.put("limit",limit);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getIncomeList(paramsMap);
    }

    /**
     *  修改密码
     */
    public static Observable changePW(String pw) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("password", pw);
            tempMap.put("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.updatename(paramsMap);
    }

    /**
     *  获取版本信息
     */
    public static Observable getVersionInfo() {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("type","0");
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.getVersionInfo(paramsMap);
    }

    /**
     *  意见反馈
     */
    public static Observable adviceFeedBack(String content,String phone,String serviceType){
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("content",content);
            tempMap.put("mobile",phone);
            tempMap.put("type",serviceType);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.advice(paramsMap);
    }

    /*************************************  订单 ************************************************/
    /**
     * 维修订单列表
     * @return
     */
    public static Observable getOrder(String serviceType, String page, String limit, String orderstatus)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("serviceType", serviceType);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            tempMap.put("orderstatus", orderstatus);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.getorder(paramsMap);
    }

    /**
     *  办公订单列表
     */
    public static Observable getStationeryOrder(String serviceType, String page, String limit, String orderstatus) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("serviceType", serviceType);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            tempMap.put("orderstatus", orderstatus);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getStationeryorder(paramsMap);
    }

    /**
     *  获取订水服务的订单列表
     */
    public static Observable getWaterOrder(String serviceType, String page, String limit, String orderstatus) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("serviceType", serviceType);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            tempMap.put("orderstatus", orderstatus);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getOrderWaterOrder(paramsMap);
    }

    /**
     *  获取会议室预定的订单列表
     */
    public static Observable getMeetingOrder(String serviceType, String page, String limit, String orderstatus) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("serviceType", serviceType);
            tempMap.put("page", page);
            tempMap.put("limit", limit);
            tempMap.put("orderstatus", orderstatus);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.getMeetingOrder(paramsMap);
    }

    /**
     *  获取订单详情
     */
    public static Observable getOrderDetail(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderid",orderId);
            addParam(paramsMap,tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.getorderDetail(paramsMap);
    }

    /**
     *  获取办公用品的订单详情
     */
    public static Observable getStationeryDetail(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderid",orderId);
            addParam(paramsMap,tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.getStationeryDetail(paramsMap);
    }

    /**
     *  获取订水服务的点单详情
     */
    public static Observable getWaterDetail(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderid",orderId);
            addParam(paramsMap,tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.getWaterDetial(paramsMap);
    }
    /**
     *  获取会议室预定服务的订单详情
     */
    public static Observable getMeetingDetial(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderid",orderId);
            addParam(paramsMap,tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.getMeetingDetail(paramsMap);
    }

    /**
     *  维修保修人员去接单
     */
    public static Observable receiveOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("id",orderId);
            addParam(paramsMap, tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.receiveOrder(paramsMap);
    }

    /**
     *  拒绝接单
     */
    public static Observable refuseOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderId",orderId);
            addParam(paramsMap, tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return api.refuseOrder(paramsMap);
    }

    /**
     *  立即配送
     */
    public static Observable peiSongOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("id",orderId);
            addParam(paramsMap, tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.peiSongOrder(paramsMap);
    }

    /**
     *  取消订单
     */
    public static Observable cancelOrder(String serviceType,String orderId,String reason) {
        Map<String,Object> paramsMap = new HashMap<>();
        try {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("id",orderId);
            tempMap.put("cancelreason",reason);
            addParam(paramsMap, tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return api.cancelOrder(paramsMap);
    }

    /**
     *  维修保修人员结束订单.
     */
    public static Observable finishOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try {
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("id",orderId);
            addParam(paramsMap, tempMap);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return api.finishOrder(paramsMap);
    }

    /**
     *  删除订单
     */
    public static Observable deleteOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("id",orderId);
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.toString();
        }

        return api.deleteOrder(paramsMap);
    }

    /**
     *  删除已完成订单
     */
    public static Observable deleteFinishOrder(String serviceType,String orderId) {
        Map<String,Object> paramsMap = new HashMap<>();
        try{
            Map<String,String> tempMap = new HashMap<>();
            tempMap.put("serviceType",serviceType);
            tempMap.put("orderId",orderId);
            addParam(paramsMap, tempMap);
        } catch (Exception e) {
            e.toString();
        }

        return api.deleteFinishOrder(paramsMap);
    }

    /**
     * 真实姓名。电话
     * @return
     */
    public static Observable updatename(String name)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("realname", name);
            tempMap.put("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.updatename(paramsMap);
    }

    /**
     * 真实电话
     * @return
     */
    public static Observable updatePhone(String phone)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("mobile", phone);
            tempMap.put("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.updatename(paramsMap);
    }

    /**
     *  修改头像
     */
    public static Observable updataPhoto(String photo) {
        Map<String, Object> paramsMap = new HashMap<>();
        RequestBody requestBody = null;
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("servicetypeid",SharedPrefHelper.getInstance().getServicetype() + "");

            Gson gson = new Gson();
            String biz = gson.toJson(tempMap);


            LogUtils.d("biz请求参数：" + biz);
            String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();


            LogUtils.d("时间戳请求参数：" + timestamp);
            SoftApplication sf = new SoftApplication();
            String token = "";
            sf.getToken();
            LogUtils.d("token::" + sf.getToken());
            if (sf.getToken() != null && !sf.getToken().equals("")) {
                token = sf.getToken();
            }
            LogUtils.d("sign" + getSign(biz,timestamp));

            File file = new File(photo);
            LogUtils.d("photo" + file.length());
            requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart(ParameterKeys.BIZ, biz)
                    .addFormDataPart(ParameterKeys.TIMESTAMP,timestamp)
                    .addFormDataPart(ParameterKeys.TOKEN,token)
                    .addFormDataPart(ParameterKeys.SIGN,getSign(biz,timestamp))
                    .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("\"image/*\""), file))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return api.upPhoto(requestBody);
    }

    /**
     *  获取订单消息
     */
    public static Observable getOrderMessage(String serviceTypeId,String page,String limit) {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("serviceTypeId", serviceTypeId);
            tempMap.put("page", page);
            tempMap.put(limit,limit);
            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.getOrderMessage(paramsMap);
    }
}
