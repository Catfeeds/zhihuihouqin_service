package cn.lc.model.ui.tab3.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.lc.model.R;

/**
 * Created by Administrator on 2017/12/20.
 */

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {
    //private List<IncomeBean.ListBean> list;
    private Activity activity;

    /*public IncomeAdapter(Activity activity, List<IncomeBean.ListBean> list) {
        this.activity = activity;
        //this.list = list;
    }*/

    @Override
    public IncomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View itemView = inflater.inflate(R.layout.item_income,parent,false);
        return new IncomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return list.size();
        return 0;
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {

        public IncomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
