package cn.lc.model.easeui;

import android.content.Intent;

import com.hyphenate.util.EasyUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import cn.lc.model.R;
import cn.lc.model.easeui.ui.EaseChatFragment;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.login.presenter.RegistStep1Presenter;
import cn.lc.model.ui.login.view.RegistStep1View;
import cn.lc.model.ui.main.activity.MainActivity;

/**
 * Created by xyixiuw on 2017/2/20.
 * 备注：聊天界面
 */

public class ChatActivity extends MvpSimpleActivity<RegistStep1View, RegistStep1Presenter> {
    @ViewInject(R.id.titlebar)
    private TitleBar titlebar;
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    public String usetpath, username , realName;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.em_activity_chat);
        ViewUtils.inject(this);
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    @Override
    public void initView() {
        usetpath = getIntent().getStringExtra("usetpath");
        username = getIntent().getStringExtra("username");
        realName = getIntent().getStringExtra("realName");
        titlebar.setBack(true);
        titlebar.setTitle(realName);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    public RegistStep1Presenter createPresenter() {
        return new RegistStep1Presenter();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
