package cn.lc.model.ui.tab1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.adapter.MettingRoomAdpater;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public class MeettingRoomFragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> implements Tab1View {
    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    @BindView(R.id.tx_null)
     TextView tx_null;
    private List<StationeryBean.ListBean> list = new ArrayList<>();
    private int type;
    private int page = 1;
    private int limit = 10;
    private MettingRoomAdpater myAdpater;
    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.tab1_1_0);
        Bundle argument = getArguments();
        type = argument.getInt("type");
        ButterKnife.bind(getActivity());

    }
    @Override
    public void initView(View v) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
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
        myAdpater = new MettingRoomAdpater(list, getActivity(),type);
        mRecyclerView.setAdapter(myAdpater);
        myAdpater.setMyOnClickListener(new MettingRoomAdpater.MyOnClickListener() {

            @Override
            public void myOnClickListener(final StationeryBean.ListBean bean) {
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话"+bean.getMendermobile())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getMendermobile()));
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
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
//                        getPresenter().getOrder(type + "", page + "", limit + "");
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
//                        getPresenter().getOrder(type + "", page + "", limit + "");
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
    public void getSucc(StationeryBean bean) {
        LogUtils.d("错误"+bean.errCode);
        if (bean.errCode!=0){

            tx_null.setText("暂无数据");
            return;
        }
        if (page==1){
            list.clear();
        }
        list.addAll(bean.getList());

        myAdpater.notifyDataSetChanged();

    }

    @Override
    public void getSucc(StationeryNewBean bean) {

    }
}
