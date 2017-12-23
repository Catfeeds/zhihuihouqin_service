package cn.lc.model.ui.tab3.presenter;

import android.util.Log;

import cn.lc.model.framework.network.callback.RetrofitCallBack;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.tab3.bean.PhotoBean;
import cn.lc.model.ui.tab3.view.PersonInfoView;
import mvp.cn.rx.MvpRxSimplePresenter;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PersonInfoPresenter extends MvpRxSimplePresenter<PersonInfoView> {


    public void getData() {
//        LogUtil.log("PersonInfo发出请求");
//        Observable login = RetrofitUtils.getInstance().login("", "");
//        getNetWork(login, new RetrofitCallBack<BaseResponse>() {
//
//            @Override
//            public void onPostFail(Throwable e) {
//
//            }
//
//            @Override
//            public void onSuccess(BaseResponse baseResponse) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    public void upPhoto(String photo) {
        Observable observable = RetrofitUtils.getInstance().updataPhoto(photo);
        getNetWork(observable, new RetrofitCallBack<PhotoBean>() {
            @Override
            public void onPostFail(Throwable e) {
                Log.e("netError:",e.toString());
            }

            @Override
            public void onSuccess(PhotoBean photoBean) {
                getView().upPhotoSuccess(photoBean);
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
