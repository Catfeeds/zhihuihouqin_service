package cn.lc.model.ui.tab3.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseResponse;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.ui.tab3.presenter.SystemSettingPresent;
import cn.lc.model.ui.tab3.view.SystemSettingView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class AboutUsFragment extends MvpSimpleFragment<SystemSettingView,SystemSettingPresent> implements SystemSettingView,View.OnClickListener  {
    @BindView(R.id.call)
    TextView tv_call;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call:
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话" + "")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL , Uri.parse("tel:" + ""));
                                try {
                                    startActivity(intent);
                                } catch (Exception e) {
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }

    }

    @Override
    public void getSucc(BaseResponse bean) {

    }

    @Override
    public SystemSettingPresent createPresenter() {
        return new SystemSettingPresent();
    }

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_about);
    }

    @Override
    public void initView(View v) {
        tv_call.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }
}
