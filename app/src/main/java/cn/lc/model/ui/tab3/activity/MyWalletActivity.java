package cn.lc.model.ui.tab3.activity;

import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.ui.tab3.fragment.IncomeFrament;
import cn.lc.model.ui.tab3.presenter.MyBankPresenter;
import cn.lc.model.ui.tab3.view.MyBankView;

/**
 *  Created by 张海洋 on 2017/11/9.
 *
 */

public class MyWalletActivity extends MvpSimpleActivity<MyBankView, MyBankPresenter> {

    @Override
    public void setContentLayout() {
        setContentView(R.layout.mywallet);
    }

    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.income_content,new IncomeFrament())
                .commit();
    }

    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }
}
