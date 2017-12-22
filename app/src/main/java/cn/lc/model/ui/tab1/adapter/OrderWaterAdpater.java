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

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.OrderWaterBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;

/**
 * Tab1适配器
 */
public class OrderWaterAdpater extends RecyclerView.Adapter<OrderWaterAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<OrderWaterBean.ListBean> datas = null;
    public int type;
    private Tab1Presenter presenter;

    public OrderWaterAdpater(List<OrderWaterBean.ListBean> datas, Context con, int type, Tab1Presenter presenter) {
        this.datas = datas;
        this.context = con;
        this.type=type;
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
        final OrderWaterBean.ListBean bean = datas.get(position);

        if (type== Tab1Constants.WATER_UNRECEIVE_ORDER){
            holder.tv_accept_oreder.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_accept_oreder.setText("立即接单");
            holder.tv_cancel_order.setText("拒绝接单");
        }else if (type== Tab1Constants.WATER_RECEIVED_ORDER){
            holder.tv_accept_oreder.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_accept_oreder.setText("取消接单");
            holder.tv_cancel_order.setText("立即配送");
        }else if (type== Tab1Constants.WATER_DELIVERY){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("已送达");
        }else if (type==Tab1Constants.WATER_FINISH){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.GONE);
        }else if (type==Tab1Constants.WATER_CANCEL){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("删除订单");
        }

        holder.tv_ordernum.setText(bean.getOrdercode());
        holder.tv_ordertime.setText(bean.getCreatetime());
        holder.tv_orderaddress.setText(bean.getAddress());
        holder.tv_name.setText(bean.getRealname());
        holder.tv_phone.setText(bean.getMobile());
        holder.tv_sendtime.setText(bean.getEndtime());

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

                UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
            }
        });
        holder.tv_accept_oreder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Tab1Constants.WATER_UNRECEIVE_ORDER){
                    presenter.goService(SharedPrefHelper.getInstance().getServicetype() + "",bean.getId());
                    presenter.getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                }else if (type == Tab1Constants.WATER_RECEIVED_ORDER){      // 取消接单
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid", bean.getId() + "");
                    bundle.putInt("type",type);
                    UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
                }

            }
        });
        holder.tv_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int servicetype = SharedPrefHelper.getInstance().getServicetype();

                if (type == Tab1Constants.WATER_UNRECEIVE_ORDER){       // 拒绝接单
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid", bean.getId() + "");
                    bundle.putInt("type",type);
                    UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
                }else if (type == Tab1Constants.WATER_RECEIVED_ORDER){
                    presenter.peiSongOrder(String.valueOf(servicetype),bean.getId());
                    presenter.getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                }else if(type == Tab1Constants.WATER_DELIVERY){
                    presenter.finishService(String.valueOf(servicetype),bean.getId());
                    presenter.getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                }else if (type == Tab1Constants.WATER_CANCEL){
                    presenter.deleteOrder(String.valueOf(servicetype),bean.getId());
                    presenter.getWaterOrder(SharedPrefHelper.getInstance().getServicetype() + "", 1 + "", 10 + "", type + "");
                }
            }
        });
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView iv_img;
        public ImageView iv_tel;
        public TextView tv_ordernum;
        public TextView tv_ordertime;
        public TextView tv_orderaddress;
        public TextView tv_phone;
        public TextView tv_sendtime;
        public TextView tv_accept_oreder;
        public TextView  tv_cancel_order;
        public RelativeLayout rl_item;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_ordernum=(TextView) view.findViewById(R.id.tv_ordernum);
            tv_ordertime=(TextView) view.findViewById(R.id.tv_ordertime);
            tv_orderaddress=(TextView) view.findViewById(R.id.tv_orderaddress);
            tv_phone=(TextView) view.findViewById(R.id.tv_phone);
            tv_sendtime =(TextView) view.findViewById(R.id.tv_sendtime);
            tv_accept_oreder=(TextView) view.findViewById(R.id.tv_accept_oreder);
            tv_cancel_order=(TextView) view.findViewById(R.id.tv_cancel_order);
            iv_img = (ImageView) view.findViewById(R.id.iv_img);
            iv_tel = (ImageView) view.findViewById(R.id.iv_tel);
           rl_item=(RelativeLayout) view.findViewById(R.id.rl_item);
        }
    }
    public interface MyOnClickListener {
        void myOnClickListener(OrderWaterBean.ListBean bean);
    }
    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener=myOnClickListener;
    }
}
