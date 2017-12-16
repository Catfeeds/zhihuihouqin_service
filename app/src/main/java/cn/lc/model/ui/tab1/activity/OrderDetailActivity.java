package cn.lc.model.ui.tab1.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.base.CommonBean;
import cn.lc.model.framework.base.MvpSimpleActivity;
import cn.lc.model.ui.tab1.presenter.OrderDetailPresenter;
import cn.lc.model.ui.tab1.view.OrderDetailView;

/**
 * Created by Administrator on 2017/11/20.
 */

public class OrderDetailActivity extends MvpSimpleActivity<OrderDetailView,OrderDetailPresenter>implements OrderDetailView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_call)
    ImageView iv_call;
    @BindView(R.id.tx_personname)
    TextView tx_personname;
    @BindView(R.id.tx_personphone)
    TextView tx_personphone;
    @BindView(R.id.tx_personaddress)
    TextView tx_personaddress;
    @BindView(R.id.tx_ordertime)
    TextView tx_ordertime;
    @BindView(R.id.tx_totime)
    TextView tx_totime;
    @BindView(R.id.tx_content)
    TextView tx_content;
    @BindView(R.id.tx_beizhu)
    TextView tx_beizhu;
    @BindView(R.id.tx_ordernum)
    TextView tx_ordernum;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.order_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }
    @OnClick({R.id.iv_back,R.id.iv_call})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_call:
                new AlertDialog.Builder(getActivity()).setTitle("").setMessage("拨打电话"+"")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +""));
                                try {
                                    startActivity(intent);
                                }catch (Exception e){
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
        }
    }
    @Override
    public void getSucc(CommonBean bean) {

    }
    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }
}