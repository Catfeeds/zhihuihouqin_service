package cn.lc.model.ui.tab3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.utils.CommonUtils;
import cn.lc.model.ui.main.activity.MainActivity;
import mvp.cn.util.StringUtil;

/**
 * Created by Administrator on 2017/11/20.
 */

public class AddBank1Activity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_bankcode)
    EditText ed_bankcode;
    @BindView(R.id.tx_next)
    TextView tx_next;
    private String name;
    private String bankcode;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_addbank1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.tx_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                CommonUtils.hintKbTwo(this);
                finish();
                break;
            case R.id.tx_next:
                name=ed_name.getText().toString();
                bankcode=ed_bankcode.getText().toString();
                CommonUtils.hintKbTwo(this);
                if (StringUtil.isNullOrEmpty(name)) {
                showToast("请输入持卡人姓名");
                return;
            }
                if (StringUtil.isNullOrEmpty(bankcode)) {
                    showToast("请输入银行卡号");
                    return;
                }
                Bundle b=new Bundle();
                b.putString("name",name);
                b.putString("code",bankcode);
                UIManager.turnToAct(this, AddBank2Activity.class,b);
                break;
        }
    }

    @Override
    public void initView() {

    }
}
