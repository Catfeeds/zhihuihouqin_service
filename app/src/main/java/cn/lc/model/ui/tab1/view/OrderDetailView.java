package cn.lc.model.ui.tab1.view;

import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface OrderDetailView extends MvpView{
    void getSucc(OrderDetailBean bean);
}
