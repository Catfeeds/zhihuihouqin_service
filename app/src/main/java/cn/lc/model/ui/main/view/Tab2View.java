package cn.lc.model.ui.main.view;

import cn.lc.model.ui.tab2.bean.OrderMessageBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface Tab2View extends MvpView{
    // 获取订单消息
    void orderMessageSucc(OrderMessageBean bean);
    // 获取系统消息
    //void sysMessageSucc()
}
