package cn.lc.model.ui.tab3.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.ui.tab1.adapter.StationeryAdpater;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab3.presenter.MyBankPresenter;
import cn.lc.model.ui.tab3.view.MyBankView;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MyEvaluateActivity extends MvpSimpleActivity<MyBankView, MyBankPresenter> implements MyBankView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    private List<StationeryBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    private int limit = 10;
    private StationeryAdpater myAdpater;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.evaluete);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //   myAdpater = new StationeryAdpater(list, getActivity());
        //  mRecyclerView.setAdapter(myAdpater);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        mRecyclerView.refreshComplete();
                    }

                }, 2000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                page++;
                new Handler().postDelayed(new Runnable() {

                    public void run() {

                        mRecyclerView.loadMoreComplete();
                    }
                }, 2000);

            }
        });
    }


    @Override
    public void getSucc(CommonBean bean) {
        if (page == 1) {
            list.clear();
        }
        //  list.addAll(bean.getList());
        myAdpater.notifyDataSetChanged();
    }

    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }
}
