package cn.lc.model.ui.tab1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.StationeryBean;

/**
 * Tab1适配器
 */
public class MettingRoomAdpater extends RecyclerView.Adapter<MettingRoomAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<StationeryBean.ListBean> datas = null;
    private int type;

    public MettingRoomAdpater(List<StationeryBean.ListBean> datas, Context con, int type) {
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
        if (type == 1) {
        holder.tv_nowservice.setText("立即服务");
        } else if (type == 2) {
            holder.tv_nowservice.setText("已完成");
        } else if (type == 3) {
            holder.tv_nowservice.setText("删除");
        } else if (type == 4) {
            holder.tv_nowservice.setText("删除");
        }
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.turnToAct(context, OrderDetailActivity.class);
            }
        });
        holder.iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.myOnClickListener(datas.get(position));
            }
        });
        holder.tv_nowservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        public TextView tv_address;
        public TextView tv_phone;
        public TextView tv_project;
        public TextView tv_dis;
        public TextView tv_ordernumtype;
        public TextView tv_nowservice;
        private LinearLayout ll_item;
        public ImageView iv_tel;
        public ImageView iv_header;
        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_phone= (TextView) view.findViewById(R.id.tv_phone);
            tv_project = (TextView) view.findViewById(R.id.tv_project);
            tv_ordernumtype= (TextView) view.findViewById(R.id.tv_ordernumtype);
            tv_dis = (TextView) view.findViewById(R.id.tv_dis);
            tv_nowservice = (TextView) view.findViewById(R.id.tv_nowservice);
            ll_item=(LinearLayout)view.findViewById(R.id.ll_item) ;
            iv_tel= (ImageView) view.findViewById(R.id.iv_tel);
            iv_header = (ImageView) view.findViewById(R.id.iv_header);
        }
    }
    public interface MyOnClickListener {
        void myOnClickListener(StationeryBean.ListBean bean);
    }
    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener=myOnClickListener;
    }
}
