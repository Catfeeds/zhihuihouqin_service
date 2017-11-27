package cn.lc.model.ui.tab3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab3.activity.AddBank1Activity;

/**
 *
 */
public class MyBankAdapter extends RecyclerView.Adapter<MyBankAdapter.ViewHolder> {
    public Context context;
    public List<StationeryBean.ListBean> datas = null;

    public MyBankAdapter(List<StationeryBean.ListBean> datas, Context con) {
        this.datas = datas;
        this.context = con;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mybank_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position==datas.size()){
            holder.llitemAdd.setVisibility(View.VISIBLE);
            holder.llItem.setVisibility(View.GONE);
        }else if (datas.size()!=0){
            holder.llItem.setVisibility(View.VISIBLE);
            holder.llitemAdd.setVisibility(View.GONE);
            holder.itemBankName.setText(datas.get(position).getUsername());
            holder.itemCardType.setText(datas.get(position).getUsername());
            holder.itemCardNo.setText(datas.get(position).getUsername());
        }

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.llitemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.turnToAct(context, AddBank1Activity.class);
            }
        });

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size()+1;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemBankName;
        public TextView itemCardType;
        public TextView itemCardNo;
        public LinearLayout llItem;
        public LinearLayout llitemAdd;

        public ViewHolder(View view) {
            super(view);
            itemBankName = (TextView) view.findViewById(R.id.item_bankName);
            itemCardType = (TextView) view.findViewById(R.id.item_cardType);
            itemCardNo = (TextView) view.findViewById(R.id.item_cardNo);
            llItem = (LinearLayout) view.findViewById(R.id.ll_item);
            llitemAdd = (LinearLayout) view.findViewById(R.id.ll_item_add);
        }
    }
}
