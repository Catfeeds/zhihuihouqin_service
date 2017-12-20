package cn.lc.model.ui.tab3.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.ui.tab3.bean.EvaluateBean;

/**
 * Created by Administrator on 2017/12/20.
 */

public class MyEvaluateAdapter extends RecyclerView.Adapter<MyEvaluateAdapter.EvaluateViewHolder> {
    private List<EvaluateBean.ListBean> list;
    private Activity activity;

    public MyEvaluateAdapter(List<EvaluateBean.ListBean> list, Activity activity){
        this.list = list;
        this.activity = activity;
    }

    @Override
    public EvaluateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EvaluateViewHolder(inflater.inflate(R.layout.item_evaluate,parent,false));
    }

    @Override
    public void onBindViewHolder(EvaluateViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EvaluateViewHolder extends RecyclerView.ViewHolder {

        public EvaluateViewHolder(View itemView) {
            super(itemView);
        }
    }
}
