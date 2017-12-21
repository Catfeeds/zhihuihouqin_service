package cn.lc.model.ui.tab1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.adapter.StationeryAdpater;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public class StationeryFragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> implements Tab1View {
    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    @BindView(R.id.tx_null)
    TextView tx_null;
    @BindView(R.id.view_error)
    RelativeLayout layout_error;

    private List<StationeryNewBean.ListBean> list = new ArrayList<>();
    private int type;
    private int page = 1;
    private int limit = 10;
    private StationeryAdpater myAdpater;
    private ViewStub viewStub;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.tab1_1_0);
        Bundle argument = getArguments();
        type = argument.getInt("type");
        //ButterKnife.bind(getActivity());

    }

    @Override
    public void initView(View v) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //getPresenter().getStationeryOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");

        list.add(new StationeryNewBean.ListBean());
        myAdpater = new StationeryAdpater(list, getActivity(), type,getPresenter());
        mRecyclerView.setAdapter(myAdpater);
        myAdpater.setMyOnClickListener(new StationeryAdpater.MyOnClickListener() {
            @Override
            public void myOnClickListener(final StationeryNewBean.ListBean bean) {
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话" + bean.getMobile())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getMobile()));
                                try {
                                    startActivity(intent);
                                } catch (Exception e) {
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                        mRecyclerView.refreshComplete();
                    }

                }, 2000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                page++;
                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                        mRecyclerView.loadMoreComplete();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    @Override
    public void getSucc(StationeryBean bean) {}

    @Override
    public void getSucc(StationeryNewBean bean) {
        LogUtils.d("错误" + bean.errCode);
        if (bean.errCode != 0) {

            tx_null.setText("暂无数据");
            return;
        }
        if (page == 1) {
            list.clear();
            if (bean.getList().size() == 0) {
                layout_error.setVisibility(View.VISIBLE);
            } else {
                layout_error.setVisibility(View.GONE);
            }
        }
        list.addAll(bean.getList());

        myAdpater.notifyDataSetChanged();
    }

    @Override
    public void getSucc(OrderWaterBean bean) {

    }
}
