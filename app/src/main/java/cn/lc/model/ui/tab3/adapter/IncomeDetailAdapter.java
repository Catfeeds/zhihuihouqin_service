package cn.lc.model.ui.tab3.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.ui.tab3.bean.IncomeDetailBean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeDetailAdapter extends RecyclerView.Adapter<IncomeDetailAdapter.IncomeDetialViewHolder> {
    private List<IncomeDetailBean.ListBean> list;
    private Activity activity;

    public IncomeDetailAdapter(Activity activity, List<IncomeDetailBean.ListBean> list) {
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class IncomeDetialViewHolder extends RecyclerView.ViewHolder {

        public IncomeDetialViewHolder(View itemView) {
            super(itemView);
        }
    }
}
