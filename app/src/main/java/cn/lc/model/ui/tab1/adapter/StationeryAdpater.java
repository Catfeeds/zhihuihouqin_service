package cn.lc.model.ui.tab1.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.StationeryNewBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;

import static cn.lc.model.ui.tab1.constant.Tab1Constants.WORK_CANCEL;
import static cn.lc.model.ui.tab1.constant.Tab1Constants.WORK_DELIVERY;
import static cn.lc.model.ui.tab1.constant.Tab1Constants.WORK_UNRECEIVE_ORDER;

/**
 * Tab1适配器
 */
public class StationeryAdpater extends RecyclerView.Adapter<StationeryAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<StationeryNewBean.ListBean> datas = null;
    public int type;
    public Tab1Presenter presenter;

    public StationeryAdpater(List<StationeryNewBean.ListBean> datas, Context con, int type,Tab1Presenter presenter) {
        this.datas = datas;
        this.context = con;
        this.type = type;
        this.presenter = presenter;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stationery_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final StationeryNewBean.ListBean bean = datas.get(position);

        if (type == Tab1Constants.WORK_UNRECEIVE_ORDER) {
            holder.tv_accept_oreder.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_accept_oreder.setText("立即接单");
            holder.tv_cancel_order.setText("拒绝接单");
        } else if (type == Tab1Constants.WORK_RECEIVED_ORDER) {
            holder.tv_accept_oreder.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_accept_oreder.setText("取消订单");
            holder.tv_cancel_order.setText("立即配送");
        } else if (type == WORK_DELIVERY) {
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("已送达");
        } else if (type == Tab1Constants.WORK_FINISH) {
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.GONE);
        } else if (type == WORK_CANCEL) {
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("删除订单");
        }

        holder.iv_img.setImageURI(bean.getImg());
        holder.tv_ordernum.setText(bean.getCode());
        holder.tv_ordertime.setText(bean.getCreatetime());
        holder.tv_orderaddress.setText(bean.getAddress());
        holder.tv_name.setText(bean.getUsername());
        holder.tv_phone.setText(bean.getMobile());
        holder.tv_sendtime.setText(bean.getExpertarrivaltme());


        holder.iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.myOnClickListener(datas.get(position));
            }
        });
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                bundle.putString("orderid", bean.getId() + "");
                bundle.putInt("type",type);
                UIManager.turnToAct(context, OrderDetailActivity.class, bundle);
            }
        });
        holder.tv_accept_oreder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == WORK_UNRECEIVE_ORDER) {                             // 未接单中的立即接单.
                    presenter.goService(SharedPrefHelper.getInstance().getServicetype() + "" + "",bean.getId());
                    presenter.getStationeryOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                } else if (type == Tab1Constants.WORK_RECEIVED_ORDER) {         // 已接单的取消订单
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid",bean.getId() + "");
                    bundle.putInt("type",type);

                    UIManager.turnToAct(context,OrderDetailActivity.class,bundle);
                }


            }
        });
        holder.tv_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == WORK_UNRECEIVE_ORDER) {                             // 未接单的拒绝接单
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid", bean.getId() + "");
                    bundle.putInt("type",type);
                    UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
                } else if (type == Tab1Constants.WORK_RECEIVED_ORDER) {         // 已接单的立即配送
                    presenter.peiSongOrder(SharedPrefHelper.getInstance().getServicetype() + "" + "",bean.getId());
                    presenter.getStationeryOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                } else if (type == WORK_DELIVERY) {                             //配送中的已送达
                    presenter.finishService(SharedPrefHelper.getInstance().getServicetype() + "" + "",bean.getId());
                    presenter.getStationeryOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                } else if (type == WORK_CANCEL) {                               // 已取消的删除订单.
                    presenter.deleteOrder(SharedPrefHelper.getInstance().getServicetype() + "" + "",bean.getId());
                    presenter.getStationeryOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                }
            }
        });
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
        //return 1;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public SimpleDraweeView iv_img;
        public ImageView iv_tel;
        public TextView tv_ordernum;
        public TextView tv_ordertime;
        public TextView tv_orderaddress;
        public TextView tv_phone;
        public TextView tv_sendtime;
        public TextView tv_accept_oreder;
        public TextView tv_cancel_order;
        public RelativeLayout rl_item;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_ordernum = (TextView) view.findViewById(R.id.tv_ordernum);
            tv_ordertime = (TextView) view.findViewById(R.id.tv_ordertime);
            tv_orderaddress = (TextView) view.findViewById(R.id.tv_orderaddress);
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_sendtime = (TextView) view.findViewById(R.id.tv_sendtime);
            tv_accept_oreder = (TextView) view.findViewById(R.id.tv_accept_oreder);
            tv_cancel_order = (TextView) view.findViewById(R.id.tv_cancel_order);
            iv_img = (SimpleDraweeView) view.findViewById(R.id.iv_img);
            iv_tel = (ImageView) view.findViewById(R.id.iv_tel);
            rl_item = (RelativeLayout) view.findViewById(R.id.rl_item);
        }
    }

    public interface MyOnClickListener {
        void myOnClickListener(StationeryNewBean.ListBean bean);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }
}
