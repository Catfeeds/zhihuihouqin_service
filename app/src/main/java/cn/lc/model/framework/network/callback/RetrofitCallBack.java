package cn.lc.model.framework.network.callback;


import cn.lc.model.framework.application.SoftApplication;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.contant.Constants;
import mvp.cn.util.LogUtil;
import mvp.cn.util.ToastUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/8/28.
 */

public abstract class RetrofitCallBack<T extends BaseResponse> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        LogUtil.log("onCompleted");
        onComplete();
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.logError("error:" + e.getMessage());
        onComplete();
        onPostFail(e);
    }

    @Override
    public void onNext(T t) {
        if (t == null) {
            onPostFail(new Throwable("解析出问题,可能您需要检查bean"));
            return;
        }
       // if (t.errCode == Constants.ERROR_CODE_OK) {
            onSuccess(t);
      //  } else {
            onCodeError(t);
      //  }
    }

    public abstract void onPostFail(Throwable e);

    public void onCodeError(T t) {
        ToastUtil.showToast(SoftApplication.softApplication, t.msg);
    }

    public abstract void onSuccess(T t);

    public abstract void onComplete();
}
