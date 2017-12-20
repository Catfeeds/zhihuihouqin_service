package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab3.presenter.MyBankPresenter;
import cn.lc.model.ui.tab3.view.MyBankView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeRuleFragment extends MvpSimpleFragment<MyBankView, MyBankPresenter> {


    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_income_ruler);
    }

    @Override
    public void initView(View v) {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }
    }
}
