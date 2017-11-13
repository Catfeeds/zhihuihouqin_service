package cn.lc.model.framework.network.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lc.model.framework.network.AppConstants;
import cn.lc.model.framework.network.ParameterKeys;
import cn.lc.model.framework.network.ServerConstants;
import cn.lc.model.framework.utils.LogUtils;
import mvp.cn.util.DateUtil;
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
    private static void addParam(Map<String, Object> paramsMap, Map<String, String> tempMap) {
        Gson gson = new Gson();

        String biz = gson.toJson(tempMap);
        paramsMap.put("biz", biz);
        LogUtils.d("biz请求参数：" + biz);

//        String replaceBiz = biz.replaceAll(" ", "");
//        paramsMap.put(ParameterKeys.REPLACE_BIZ, replaceBiz);
//
//        String device = gson.toJson(getdevice());
//        paramsMap.put(ParameterKeys.DEVICE, device);
//        LogUtils.d("device请求参数：" + device);
//
//        String replaceDevice = device.replaceAll(" ", "");
//        paramsMap.put(ParameterKeys.REPLACE_DEVICE, replaceDevice);
//
//        String timestamp = DateUtil.getCurrentDateTimeyyyyMMddHHmmss();
//        paramsMap.put(ParameterKeys.TIMESTAMP, timestamp);
//        LogUtils.d("时间戳请求参数：" + timestamp);
//        SoftApplication sf = new SoftApplication();
//        sf.getToken();
//        LogUtils.d("token::" + sf.getToken());
//        if (sf.getToken() != null && !sf.getToken().equals("")) {
//            paramsMap.put(TOKEN, sf.getToken());
//        } else {
//            paramsMap.put(TOKEN, "");
//        }
//        if (SharedPrefHelper.getInstance().getToken() != null && !SharedPrefHelper.getInstance().getToken().equals("")) {
//            LogUtils.d("getToken请求参数：" + SharedPrefHelper.getInstance().getToken());
//            paramsMap.put(TOKEN, SharedPrefHelper.getInstance().getToken());
//
//        } else {
//            LogUtil.log(SharedPrefHelper.getInstance().getyouke() + "");
//            if (SharedPrefHelper.getInstance().getyouke() == true) {
//                paramsMap.put(TOKEN, "0");
//            } else {
//            }
//        try {
//            paramsMap.put(ParameterKeys.SIGN, getSign(replaceDevice, replaceBiz, timestamp));
//            //   LogUtils.d("sign戳请求参数：" + getSign(device,biz, timestamp));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
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
            tempMap.put("userName", username);
            tempMap.put("password", pwd);
            tempMap.put("servicetypeid", service);
            addParam(paramsMap,tempMap);
//            Gson gson = new Gson();
//            String info = gson.toJson(tempMap);
//            paramsMap.put(ParameterKeys.KEY_INFO, info);
//            paramsMap.put(ParameterKeys.KEY_SIGN, "");
//            paramsMap.put(ParameterKeys.KEY_TOKEN, "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.login(paramsMap);
    }


}
