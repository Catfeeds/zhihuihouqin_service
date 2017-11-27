package cn.lc.model.ui.main.view;

import cn.lc.model.ui.login.bean.LoginBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface Tab1View extends MvpView{
    void getSucc(StationeryBean bean);
}
