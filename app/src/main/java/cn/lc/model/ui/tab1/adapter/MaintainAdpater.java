package cn.lc.model.ui.tab1.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;

/**
 * Tab1适配器
 */
public class MaintainAdpater extends RecyclerView.Adapter<MaintainAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<StationeryBean.ListBean> datas = null;
    private int type;

    public MaintainAdpater(List<StationeryBean.ListBean> datas, Context con, int type) {
        this.datas = datas;
        this.context = con;
        this.type = type;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.maintain_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        StationeryBean.ListBean bean = datas.get(position);

        if (type == Tab1Constants.MAINTAIN_UNSERVICE) {
            holder.tv_nowservice.setText("立即服务");
        } else if (type == Tab1Constants.MAINTAIN_SERVICING) {
            holder.tv_nowservice.setText("已完成");
        } else if (type == Tab1Constants.MAINTAIN_FINISH) {
            holder.tv_nowservice.setText("删除");
        } else if (type == Tab1Constants.MAINTAIN_CANCEL) {
            holder.tv_nowservice.setText("删除");
        }

        // 设置订单数据:
        holder.tv_inviteTime.setText(bean.getInvitetime());
        holder.iv_header.setImageURI(bean.getMenderphoto());
        holder.tv_name.setText(bean.getMendername());
        holder.tv_address.setText(bean.getServiceplace());
        holder.tv_phone.setText(bean.getMendermobile());
        holder.tv_project.setText(bean.getItemname());
        //holder.tv_dis.setText(bean.);
        holder.tv_ordersn.setText(bean.getOrdercode());

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                bundle.putString("orderid",datas.get(position).getOrderid() + "");

                UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
            }
        });
        holder.iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.call(datas.get(position).getMendermobile());
            }
        });
        holder.tv_nowservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Tab1Constants.MAINTAIN_UNSERVICE) {
                    myOnClickListener.service(datas.get(position));
                } else if (type == Tab1Constants.MAINTAIN_SERVICING) {
                    myOnClickListener.finishOrder(datas.get(position));
                } else if (type == Tab1Constants.MAINTAIN_FINISH) {
                    myOnClickListener.removeOrder(datas.get(position));
                } else if (type == Tab1Constants.MAINTAIN_CANCEL) {
                    myOnClickListener.removeOrder(datas.get(position));
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
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_name;
        public TextView tv_address;
        public TextView tv_phone;
        public TextView tv_project;
        public TextView tv_dis;
        public TextView tv_ordernumtype;
        public TextView tv_nowservice;
        private LinearLayout ll_item;
        public ImageView iv_tel;
        public SimpleDraweeView iv_header;
        public TextView tv_inviteTime;      // 上门时间
        public TextView tv_ordersn;         // 订单号

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_project = (TextView) view.findViewById(R.id.tv_project);
            tv_ordernumtype = (TextView) view.findViewById(R.id.tv_ordernumtype);
            tv_dis = (TextView) view.findViewById(R.id.tv_dis);
            tv_nowservice = (TextView) view.findViewById(R.id.tv_nowservice);
            ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
            iv_tel = (ImageView) view.findViewById(R.id.iv_tel);
            iv_header = (SimpleDraweeView) view.findViewById(R.id.iv_header);      //订单发起人头像
            tv_inviteTime = (TextView) view.findViewById(R.id.tv_time);
            tv_ordersn = (TextView) view.findViewById(R.id.tv_order_id);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface MyOnClickListener {
        void call(String phoneNum);
        // 立即服务;
        void service(StationeryBean.ListBean bean);
        // 已完成:
        void finishOrder(StationeryBean.ListBean bean);
        // 删除:
        void removeOrder(StationeryBean.ListBean bean);
    }

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }
}
