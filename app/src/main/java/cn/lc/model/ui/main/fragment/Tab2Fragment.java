package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.main.presenter.Tab2Presenter;
import cn.lc.model.ui.main.view.Tab2View;


/**
 * Created by hh on 2016/5/18.
 */
public class Tab2Fragment extends MvpSimpleFragment<Tab2View,Tab2Presenter> implements Tab2View {


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab2);
    }

    @Override
    public void initView(View v) {
        // listView

//        EventBus.getDefault().register(this);
    }

    @Override
    public Tab2Presenter createPresenter() {
        return new Tab2Presenter();
    }




    class Params {
        String keyWords;
        String consultType;
        int startPage = 1;
        int pageSize = 10;
        String orderBy;
        String addrId;
        double lon = 0;
        String sectionId;
        String free;
        double lat = 0;
        String status;
    }

}
