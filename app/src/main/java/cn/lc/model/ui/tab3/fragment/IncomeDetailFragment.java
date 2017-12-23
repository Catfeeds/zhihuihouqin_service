package cn.lc.model.ui.tab3.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab3.adapter.IncomeDetailAdapter;
import cn.lc.model.ui.tab3.bean.IncomeDetailBean;
import cn.lc.model.ui.tab3.presenter.MyBankPresenter;
import cn.lc.model.ui.tab3.view.MyBankView;

/**
 * Created by zhy on 2017/12/20.
 */

public class IncomeDetailFragment extends MvpSimpleFragment<MyBankView, MyBankPresenter> implements MyBankView {
    @BindView(R.id.recyclerView_income)
    XRecyclerView recyclerView;
    @BindView(R.id.view_error)
    RelativeLayout view_error;

    IncomeDetailAdapter adapter;
    List<IncomeDetailBean.DataBean> list = new ArrayList<>();

    private int page = 1;
    private int limit = 10;

    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_income_detail);
    }

    @Override
    public void initView(View v) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //list.add(new IncomeDetailBean.ListBean());
        getPresenter().getIncomeList(SharedPrefHelper.getInstance().getServicetype() + "",page + "",limit + "");

        adapter = new IncomeDetailAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getIncomeList(SharedPrefHelper.getInstance().getServicetype() + "",page + "",limit + "");
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getIncomeList(SharedPrefHelper.getInstance().getServicetype() + "",page + "",limit + "");
                recyclerView.refreshComplete();
            }
        });
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }
    }

    @Override
    public void getSucc(BaseResponse bean) {
        IncomeDetailBean data;

        if (bean instanceof IncomeDetailBean) {
            data = (IncomeDetailBean) bean;
        } else {
            return;
        }

        if (page == 1) {
            list.clear();
            recyclerView.refreshComplete();
            if (data.getData().size() == 0) {
                view_error.setVisibility(View.VISIBLE);
            } else {
                view_error.setVisibility(View.GONE);
            }
        } else {
            recyclerView.loadMoreComplete();
        }

        list.addAll(data.getData());
        adapter.notifyDataSetChanged();
    }

}
