package cn.lc.model.ui.tab3.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.ui.tab3.bean.IncomeDetailBean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeDetailAdapter extends RecyclerView.Adapter<IncomeDetailAdapter.IncomeDetialViewHolder> {
    private List<IncomeDetailBean.DataBean> list;
    private Activity activity;

    public IncomeDetailAdapter(Activity activity, List<IncomeDetailBean.DataBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public IncomeDetialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View itemView = inflater.inflate(R.layout.item_income, parent, false);

        return new IncomeDetialViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncomeDetialViewHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getContent());
        holder.tv_time.setText(list.get(position).getCreatTime());
        holder.tv_money.setText(list.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class IncomeDetialViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_time;
        TextView tv_money;

        public IncomeDetialViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_project_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_project_time);
            tv_money = (TextView) itemView.findViewById(R.id.tv_project_money);
        }
    }
}
