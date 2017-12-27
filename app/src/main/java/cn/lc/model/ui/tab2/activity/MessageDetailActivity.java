package cn.lc.model.ui.tab2.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab2.adapter.OrderMessageAdapter;
import cn.lc.model.ui.tab2.bean.OrderMessageBean;
import cn.lc.model.ui.tab2.presenter.MessageDetailPresenter;
import cn.lc.model.ui.tab2.view.MessageDetailView;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MessageDetailActivity extends MvpSimpleActivity<MessageDetailView,MessageDetailPresenter>implements MessageDetailView {
    @BindView(R.id.xRecyclerview_message)
    XRecyclerView recyclerView;

    private List<OrderMessageBean.DataBean> list = new ArrayList<>();
    private int page = 1;
    private int limit = 10;
    OrderMessageAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_message_detail);
    }

    @Override
    public void initView() {
        getPresenter().getOrderMessage(SharedPrefHelper.getInstance().getServicetype()+"",page + "",limit + "");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderMessageAdapter(list);
        recyclerView.setAdapter(adapter);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getOrderMessage(SharedPrefHelper.getInstance().getServicetype()+"",page + "",limit + "");
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getOrderMessage(SharedPrefHelper.getInstance().getServicetype()+"",page + "",limit + "");
                recyclerView.loadMoreComplete();
            }
        });
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public MessageDetailPresenter createPresenter() {
        return new MessageDetailPresenter();
    }

    @Override
    public void orderMessageSucc(OrderMessageBean bean) {
        if (bean == null) {
            return;
        }

        if (page == 0) {
            list.clear();
        }
        list.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }
}
