package cn.lc.model.ui.tab1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
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
import cn.lc.model.ui.tab1.adapter.OrderWaterAdpater;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public class OrderWaterFragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> implements Tab1View {
    private static final String TAG = "OrderWaterFragment";

    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    @BindView(R.id.tx_null)
     TextView tx_null;
    @BindView(R.id.view_error)
    RelativeLayout view_error;
    private List<OrderWaterBean.ListBean> list = new ArrayList<>();
    private int type;
    private int page = 1;
    private int limit = 10;
    private OrderWaterAdpater myAdpater;

    private boolean isRefresh = false;

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
        getPresenter().getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
        //list.add(new OrderWaterBean.ListBean());

//        getPresenter().getOrder(type + "", page + "", limit + "");
//        StationeryBean.ListBean s1=new StationeryBean.ListBean();
//        s1.setUsername("隔壁老王");
//        s1.setMobile("100086");
//        StationeryBean.ListBean s2=new StationeryBean.ListBean();
//        s2.setUsername("隔壁老张");
//        s1.setMobile("18765903803");
//        StationeryBean.ListBean s3=new StationeryBean.ListBean();
//        s3.setUsername("隔壁老宋");
//        s1.setMobile("100086");
//        StationeryBean.ListBean s4=new StationeryBean.ListBean();
//        s4.setUsername("隔壁老李");
//        s1.setMobile("100086");
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
//        list.add(s4);
        myAdpater = new OrderWaterAdpater(list, getActivity(),type,getPresenter());
        mRecyclerView.setAdapter(myAdpater);
        myAdpater.setMyOnClickListener(new OrderWaterAdpater.MyOnClickListener() {
            @Override
            public void myOnClickListener(final OrderWaterBean.ListBean bean) {
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话"+bean.getMobile())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getMobile()));
                                try {
                                    startActivity(intent);
                                }catch (Exception e){
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
                getPresenter().getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                mRecyclerView.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                mRecyclerView.loadMoreComplete();

            }
        });
    }

    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    @Override
    public void onStart() {
        Log.e(TAG,"onStart");
        super.onStart();
        if (isRefresh) {
            Log.e(TAG,"刷新了1111111111111111111111");
            refreshData();
            isRefresh = false;
        }
    }

    @Override
    public void onStop() {
        Log.e(TAG,"onStop");
        super.onStop();
        isRefresh = true;
    }

    public void refreshData() {
        getPresenter().getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
    }

    @Override
    public void getStationerySucc(StationeryBean bean) {

    }

    @Override
    public void getStationeryNewSucc(StationeryNewBean bean) {

    }

    @Override
    public void getWaterSucc(OrderWaterBean bean) {
        LogUtils.d("错误"+bean.errCode);
        if (bean.errCode!=0){

            tx_null.setText("暂无数据");
            return;
        }
        if (page==1){
            list.clear();
            if (bean.getList().size() != 0) {
                view_error.setVisibility(View.GONE);
            } else {
                view_error.setVisibility(View.VISIBLE);
            }
        }
        list.addAll(bean.getList());

        myAdpater.notifyDataSetChanged();
    }

    @Override
    public void getMeetingSucc(MeetingBean bean) {

    }
}
