package cn.lc.model.framework.network.retrofit;

import android.util.Log;

import com.google.gson.Gson;

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
import cn.lc.model.framework.utils.LogUtils;
import mvp.cn.util.DateUtil;
import mvp.cn.util.Md5Util;
import okhttp3.OkHttpClient;
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
     *
     *
     *
     * @return
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
     *
     *
     *
     * @return
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
    /**
     * 订单列表
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
     * 真实姓名。电话
     * @return
     */
    public static Observable updatename(String name,String mobile)  {
        Map<String, Object> paramsMap = new HashMap<>();
        try {
            Map<String, String> tempMap = new HashMap<String, String>();
            tempMap.put("realname", name);
            tempMap.put("mobile",mobile);

            addParam(paramsMap,tempMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.updatename(paramsMap);
    }
}
