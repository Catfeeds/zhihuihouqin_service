package cn.lc.model.ui.tab1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.activity.MainActivity;
import cn.lc.model.ui.main.fragment.Tab1Fragment;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.adapter.MaintainAdpater;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public class MaintainFragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> implements Tab1View {
    private static final String TAG = "MaintainFragment";

    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    @BindView(R.id.tx_null)
    TextView tx_null;
    @BindView(R.id.view_error)
    RelativeLayout layout_error;

    private List<StationeryBean.ListBean> list = new ArrayList<>();
    private int type;
    private int page = 1;
    private int limit = 10;
    private MaintainAdpater myAdpater;
    private ViewStub viewStub;

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

        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
        //isRefresh = false;
        //list.add(new StationeryBean.ListBean());

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
        myAdpater = new MaintainAdpater(list, getActivity(), type);
        mRecyclerView.setAdapter(myAdpater);
        myAdpater.setMyOnClickListener(new MaintainAdpater.MyOnClickListener() {
            // 打电话
            @Override
            public void call(final String phoneNum) {
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话" + phoneNum)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL , Uri.parse("tel:" + phoneNum));
                                try {
                                    startActivity(intent);
                                } catch (Exception e) {
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
            // 待服务去立即服务
            @Override
            public void service(StationeryBean.ListBean bean) {
                Log.e("TAG",bean.getId() + "");
                page = 1;
                getPresenter().goService(SharedPrefHelper.getInstance().getServicetype() + "",String.valueOf(bean.getId()));
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                //callTab1Refresh();
            }
            //拒绝接单
            @Override
            public void refuseOrder(StationeryBean.ListBean bean) {


                OrderDetailActivity.isReason = true;
                Bundle bundle = new Bundle();
                bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                bundle.putString("orderid",bean.getId() + "");
                bundle.putInt("type",type);
                UIManager.turnToAct(getActivity(), OrderDetailActivity.class,bundle);

                //getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");

            }
            // 取消接单.
            @Override
            public void cancelOrder(StationeryBean.ListBean bean) {


                OrderDetailActivity.isReason = true;
                Bundle bundle = new Bundle();
                bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                bundle.putString("orderid",bean.getId() + "");
                bundle.putInt("type",type);

                UIManager.turnToAct(getActivity(),OrderDetailActivity.class,bundle);
                page = 1;
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
            }

            // 服务中点击完成
            @Override
            public void finishOrder(StationeryBean.ListBean bean) {
                page = 1;
                getPresenter().finishService(SharedPrefHelper.getInstance().getServicetype() + "",String.valueOf(bean.getId()));
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                //callTab1Refresh();
            }

            // 删除
            @Override
            public void removeOrder(StationeryBean.ListBean bean) {
                page = 1;
                getPresenter().deleteOrder(SharedPrefHelper.getInstance().getServicetype() + "",String.valueOf(bean.getId()));
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                //callTab1Refresh();
            }

        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                mRecyclerView.refreshComplete();
                /*new Handler().postDelayed(new Runnable() {
                    public void run() {
//                        getPresenter().getOrder(type + "", page + "", limit + "");


                    }

                }, 2000); */           //refresh data here
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
                mRecyclerView.loadMoreComplete();
                /*new Handler().postDelayed(new Runnable() {

                    public void run() {
//                        getPresenter().getOrder(type + "", page + "", limit + "");


                    }
                }, 2000);*/

            }
        });
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            String reason = data.getStringExtra("reason");

        }
    }*/

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

    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    public void refreshData() {
        getPresenter().getOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
    }

    public void callTab1Refresh(){
        Log.e(TAG,"刷新其他页面");
        MainActivity activity = (MainActivity) getActivity();
        Tab1Fragment tab1Fragment = activity.getTab1Fragment();
        tab1Fragment.Refresh(this,3);
    }

    @Override
    public void getStationerySucc(StationeryBean bean) {
        LogUtils.d("错误" + bean.errCode);
        if (bean.errCode != 0) {

            tx_null.setText("暂无数据");
            return;
        }
        if (page == 1) {
            list.clear();
            if (bean.getList().size() != 0) {
                layout_error.setVisibility(View.GONE);
            } else {
                layout_error.setVisibility(View.VISIBLE);
            }

        } else {

        }

        list.addAll(bean.getList());

        myAdpater.notifyDataSetChanged();
    }

    @Override
    public void getStationeryNewSucc(StationeryNewBean bean) {

    }

    @Override
    public void getWaterSucc(OrderWaterBean bean) {

    }

    @Override
    public void getMeetingSucc(MeetingBean bean) {

    }
}
