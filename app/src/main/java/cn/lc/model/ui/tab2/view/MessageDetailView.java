package cn.lc.model.ui.tab2.view;

import cn.lc.model.ui.tab2.bean.OrderMessageBean;
import mvp.cn.common.MvpView;

/**
 * Created by Administrator on 2017/12/19.
 */

public interface MessageDetailView extends MvpView {
    // 获取订单消息
    void orderMessageSucc(OrderMessageBean bean);
}
