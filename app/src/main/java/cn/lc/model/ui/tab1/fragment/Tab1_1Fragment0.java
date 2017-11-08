package cn.lc.model.ui.tab1.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.constant.Tab1Constants;

/**
 * Created by Administrator on 2017/11/6.
 */

public class Tab1_1Fragment0 extends MvpSimpleFragment<Tab1View,Tab1Presenter> {

@BindView(R.id.title)
    TextView title;
    private  int type;
    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.tab1_1_0);
        Bundle argument=getArguments();
        type= argument.getInt("type");
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(getActivity());
        if (type==Tab1Constants.TAB1_0) {
            title.setText("待接单");
        }else  if (type==Tab1Constants.TAB1_1) {
            title.setText("已接单");
        }else  if (type==Tab1Constants.TAB1_2) {
            title.setText("配送中");
        }else  if (type==Tab1Constants.TAB1_3) {
            title.setText("已完成");
        }else  if (type==Tab1Constants.TAB1_4) {
            title.setText("已取消");
        }

    }

    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }
}
