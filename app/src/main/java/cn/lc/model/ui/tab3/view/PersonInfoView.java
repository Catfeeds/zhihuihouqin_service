package cn.lc.model.ui.tab3.view;

import cn.lc.model.ui.tab3.bean.PhotoBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface PersonInfoView extends MvpView{
    void upPhotoSuccess(PhotoBean bean);
}
