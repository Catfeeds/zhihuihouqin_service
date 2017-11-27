package cn.lc.model.ui.login.view;

import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.ui.login.bean.CaptchaBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep2View extends MvpView{
    void RegisterSucc(CommonBean bean);
}
