package cn.lc.model.ui.tab1.fragment;

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
import cn.lc.model.ui.tab1.adapter.MettingRoomAdpater;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;

/**
 * Created by Administrator on 2017/11/6.
 */

public class MeettingRoomFragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> implements Tab1View {
    private static final String TAG = "MeettingRoomFragment";

    @BindView(R.id.mRecyclerview)
    XRecyclerView mRecyclerView;
    @BindView(R.id.tx_null)
    TextView tx_null;
    @BindView(R.id.view_error)
    RelativeLayout layout_error;
    private List<MeetingBean.ListBean> list = new ArrayList<>();
    private int type;
    private int page = 1;
    private int limit = 10;
    private MettingRoomAdpater myAdpater;

    private boolean isRefresh = false;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.tab1_1_0);
        Bundle argument = getArguments();
        type = argument.getInt("type");

    }

    @Override
    public void initView(View v) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        getPresenter().getMeetingOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
        //list.add(new MeetingBean.ListBean());

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

        myAdpater = new MettingRoomAdpater(list, getActivity(), type, getPresenter());
        mRecyclerView.setAdapter(myAdpater);

//        myAdpater.setMyOnClickListener(new MettingRoomAdpater.MyOnClickListener() {
//
//            @Override
//            public void myOnClickListener(final StationeryBean.ListBean bean) {
//                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话"+bean.getMendermobile())
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getMendermobile()));
//                                try {
//                                    startActivity(intent);
//                                }catch (Exception e){
//                                }
//                            }
//                        })
//                        .setNegativeButton("取消", null)
//                        .show();
//            }
//        });

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getMeetingOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
//                        getPresenter().getOrder(type + "", page + "", limit + "");
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getMeetingOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
//                        getPresenter().getOrder(type + "", page + "", limit + "");
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
        getPresenter().getMeetingOrder(SharedPrefHelper.getInstance().getServicetype() + "", page + "", limit + "", type + "");
    }

    @Override
    public void getSucc(StationeryBean bean) {

    }

    @Override
    public void getSucc(StationeryNewBean bean) {

    }

    @Override
    public void getSucc(OrderWaterBean bean) {

    }

    @Override
    public void getSucc(MeetingBean bean) {
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
        }
        list.addAll(bean.getList());

        myAdpater.notifyDataSetChanged();

    }
}
