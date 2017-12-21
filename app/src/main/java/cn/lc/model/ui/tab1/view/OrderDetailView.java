package cn.lc.model.ui.tab1.view;

import cn.lc.model.ui.tab1.bean.MeetingDetailBean;
import cn.lc.model.ui.tab1.bean.OrderDetailBean;
import cn.lc.model.ui.tab1.bean.OrderWaterDetailBean;
import cn.lc.model.ui.tab1.bean.StationeryDetailBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface OrderDetailView extends MvpView{
    void getSucc(OrderDetailBean bean);
    void getSucc(StationeryDetailBean bean);
    void getSucc(OrderWaterDetailBean bean);
    void getSucc(MeetingDetailBean bean);
}
