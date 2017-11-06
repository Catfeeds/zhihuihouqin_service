package cn.lc.model.ui.tab1.bean;


import cn.lc.model.framework.base.BaseResponse;

/**
 * Created by hh on 2016/5/9.
 */
public class Mytab1Title extends BaseResponse {
    public String title;
    public int type;

    public Mytab1Title(String title, int type) {
        this.title = title;
        this.type = type;
    }
}
