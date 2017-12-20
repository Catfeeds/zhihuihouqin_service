package cn.lc.model.ui.main.view;

import cn.lc.model.ui.tab3.bean.PersonInfoBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface Tab3View extends MvpView{
    void getSuccess(PersonInfoBean personInfoBean);
}
