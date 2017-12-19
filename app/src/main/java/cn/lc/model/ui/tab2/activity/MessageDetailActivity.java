package cn.lc.model.ui.tab2.activity;

import android.view.View;

import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.ui.tab2.presenter.MessageDetailPresenter;
import cn.lc.model.ui.tab2.view.MessageDetailView;

/**
 * Created by Administrator on 2017/12/19.
 */

public class MessageDetailActivity extends MvpSimpleActivity<MessageDetailView,MessageDetailPresenter>implements MessageDetailView {


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_message_detail);
    }

    @Override
    public void initView() {

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
}
