package cn.lc.model.ui.tab3.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab3.adapter.MyEvaluateAdapter;
import cn.lc.model.ui.tab3.bean.EvaluateBean;
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
    @BindView(R.id.view_error)
    RelativeLayout layout_error;
    private List<EvaluateBean.PageBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    private int limit = 10;

    private MyEvaluateAdapter myAdpater;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.evaluete);
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

        getPresenter().getEvaluateData(SharedPrefHelper.getInstance().getServicetype()+"","0",page +"");
        //list.add(new EvaluateBean.ListBean());
        myAdpater = new MyEvaluateAdapter(list, getActivity());
        mRecyclerView.setAdapter(myAdpater);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getEvaluateData(SharedPrefHelper.getInstance().getServicetype()+"","0",page +"");
                mRecyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getEvaluateData(SharedPrefHelper.getInstance().getServicetype()+"","0",page +"");
                mRecyclerView.loadMoreComplete();

            }
        });
    }


    @Override
    public void getSucc(BaseResponse bean) {
        EvaluateBean data = null;

        if (bean instanceof EvaluateBean) {
            data = (EvaluateBean) bean;
        } else {
            return;
        }

        if (page == 1) {
            list.clear();
            if (data.getPage().getList().size() == 0) {
                layout_error.setVisibility(View.VISIBLE);
            } else {
                layout_error.setVisibility(View.GONE);
            }
        }
        list.addAll(data.getPage().getList());
        myAdpater.notifyDataSetChanged();
    }

    @Override
    public MyBankPresenter createPresenter() {
        return new MyBankPresenter();
    }
}
