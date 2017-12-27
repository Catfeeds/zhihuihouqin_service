package cn.lc.model.ui.tab2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.ui.tab2.bean.OrderMessageBean;

/**
 * Created by Administrator on 2017/12/26.
 */

public class OrderMessageAdapter extends RecyclerView.Adapter<OrderMessageAdapter.OrderMessageViewHolder> {
    private List<OrderMessageBean.DataBean> list;

    public  OrderMessageAdapter(List<OrderMessageBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public OrderMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message, parent,false);

        return new OrderMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderMessageViewHolder holder, int position) {
        OrderMessageBean.DataBean bean = list.get(position);
        holder.tv_title.setText(bean.getMessageTitle());
        holder.tv_content.setText(bean.getMessageContent());
        holder.tv_time.setText(bean.getCreateTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderMessageViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_content;
        TextView tv_time;

        public OrderMessageViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_message_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_message_content);
            tv_time = (TextView) itemView.findViewById(R.id.tv_message_time);
        }
    }
}
