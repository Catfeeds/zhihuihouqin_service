package cn.lc.model.ui.login.presenter;

import cn.lc.model.ui.login.view.LoginView;
import mvp.cn.rx.MvpRxSimplePresenter;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginPresenter extends MvpRxSimplePresenter<LoginView> {


    public void getData() {

    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
