package cn.lc.model.ui.tab1.adapter;

import android.content.Context;
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
import cn.lc.model.ui.main.activity.MainActivity;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.StationeryBean;

/**
 * Tab1适配器
 */
public class StationeryAdpater extends RecyclerView.Adapter<StationeryAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<StationeryBean.ListBean> datas = null;
    public int type;
    public StationeryAdpater(List<StationeryBean.ListBean> datas, Context con,int type) {
        this.datas = datas;
        this.context = con;
        this.type=type;
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
        if (type==1){
            holder.tv_accept_oreder.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
        }else if (type==2){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("立即配送");
        }else if (type==3){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("已送达");
        }else if (type==4){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.GONE);
        }else if (type==5){
            holder.tv_accept_oreder.setVisibility(View.GONE);
            holder.tv_cancel_order.setVisibility(View.VISIBLE);
            holder.tv_cancel_order.setText("删除订单");
        }

//        holder.tv_ordernum.setText(datas.get(position).getOrdercode()+"");
//        holder.tv_ordertime.setText(datas.get(position).getCreatetime()+"");
//        holder.tv_orderaddress.setText(datas.get(position).getAddress()+"");
//        holder.tv_name.setText(datas.get(position).getUsername()+"");
//        holder.tv_phone.setText(datas.get(position).getMobile()+"");
//        holder.tv_sendtime.setText(datas.get(position).getSendtime()+"");
        holder.iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.myOnClickListener(datas.get(position));
            }
        });
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.turnToAct(context, OrderDetailActivity.class);
            }
        });
        holder.tv_accept_oreder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tv_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type==1){

                }else if (type==2){

                }else if(type==3){

                }else if (type==5){

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
        void myOnClickListener(StationeryBean.ListBean bean);
    }
    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener=myOnClickListener;
    }
}
