package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab3.adapter.IncomeAdapter;
import cn.lc.model.ui.tab3.bean.IncomeBean;
import cn.lc.model.ui.tab3.presenter.MyBankPresenter;
import cn.lc.model.ui.tab3.view.MyBankView;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeFrament extends MvpSimpleFragment<MyBankView, MyBankPresenter> implements MyBankView  {
    private static final String TAG = "IncomeFrament";

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.recyclerView_wallet)
    XRecyclerView recyclerView;
    @BindView(R.id.iv_wallet_rule)
    ImageView iv_rule;

    IncomeAdapter adapter;
    //List<IncomeBean.ListBean> list = new ArrayList<>();


    private int page = 1;
    private int limit = 10;

    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_income);
    }

    @OnClick({R.id.iv_back,R.id.tv_incomeDetail,R.id.iv_wallet_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.tv_incomeDetail:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.income_content,new IncomeDetailFragment())
                        .addToBackStack("")
                        .commit();
                break;
            case R.id.iv_wallet_rule:
                Log.e(TAG,"2222222222222222");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.income_content,new IncomeRuleFragment())
                        .addToBackStack("")
                        .commit();
                break;
        }
    }

    @Override
    public void initView(View v) {
       /* recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new IncomeAdapter(getActivity(),list);*/

        getPresenter().getIncomeData(SharedPrefHelper.getInstance().getServicetype() + "");
        //list.add(new IncomeBean.ListBean());

        /*recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                recyclerView.loadMoreComplete();
            }
        });*/
    }

    @Override
    public void getSucc(BaseResponse bean) {
        IncomeBean data = null;

        if (bean instanceof IncomeBean) {
            data = (IncomeBean) bean;
        } else {
            return;
        }

        tv_money.setText(data.getData());

        /*if (page == 1) {
            list.clear();
        }

        list.addAll(data.getList());
        adapter.notifyDataSetChanged();*/
    }

}
