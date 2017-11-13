package cn.lc.model.ui.login.view;

import cn.lc.model.ui.login.bean.LoginBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface LoginView extends MvpView{
    void loginSucc(LoginBean loginBean);
    void showToast();
}
