package cn.lc.model.ui.tab3.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.ui.tab3.bean.EvaluateBean;

/**
 * Created by zhy on 2017/12/20.
 */

public class MyEvaluateAdapter extends RecyclerView.Adapter<MyEvaluateAdapter.EvaluateViewHolder> {
    private List<EvaluateBean.PageBean.ListBean> list;
    private Activity activity;


    public MyEvaluateAdapter(List<EvaluateBean.PageBean.ListBean> list, Activity activity){
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
        EvaluateBean.PageBean.ListBean listBean = list.get(position);

        holder.iv_head.setImageURI(listBean.getReplyphoto());
        holder.tv_name.setText(listBean.getReplyname());
        holder.tv_content.setText(listBean.getContent());
        //holder.iv_img1.setImageURI(listBean.getImgs());
        String imgs = listBean.getImgs();
        String[] split = imgs.split(",");
        for (int i = 0;i < split.length;i++) {
            switch (i) {
                case 0:
                    holder.iv_img1.setImageURI(split[i]);
                    break;
                case 1:
                    holder.iv_img2.setVisibility(View.VISIBLE);
                    holder.iv_img2.setImageURI(split[i]);
                    break;
                case 2:
                    holder.iv_img3.setVisibility(View.VISIBLE);
                    holder.iv_img3.setImageURI(split[i]);
                    break;
            }
        }

        switch (listBean.getScore()) {
            case 1:
                holder.iv_star1.setVisibility(View.VISIBLE);
                holder.iv_star2.setVisibility(View.GONE);
                holder.iv_star3.setVisibility(View.GONE);
                holder.iv_star4.setVisibility(View.GONE);
                holder.iv_star5.setVisibility(View.GONE);
                break;
            case 2:
                holder.iv_star1.setVisibility(View.VISIBLE);
                holder.iv_star2.setVisibility(View.VISIBLE);
                holder.iv_star3.setVisibility(View.GONE);
                holder.iv_star4.setVisibility(View.GONE);
                holder.iv_star5.setVisibility(View.GONE);
                break;
            case 3:
                holder.iv_star1.setVisibility(View.VISIBLE);
                holder.iv_star2.setVisibility(View.VISIBLE);
                holder.iv_star3.setVisibility(View.VISIBLE);
                holder.iv_star4.setVisibility(View.GONE);
                holder.iv_star5.setVisibility(View.GONE);
                break;
            case 4:
                holder.iv_star1.setVisibility(View.VISIBLE);
                holder.iv_star2.setVisibility(View.VISIBLE);
                holder.iv_star3.setVisibility(View.VISIBLE);
                holder.iv_star4.setVisibility(View.VISIBLE);
                holder.iv_star5.setVisibility(View.GONE);
                break;
            case 5:
                holder.iv_star1.setVisibility(View.VISIBLE);
                holder.iv_star2.setVisibility(View.VISIBLE);
                holder.iv_star3.setVisibility(View.VISIBLE);
                holder.iv_star4.setVisibility(View.VISIBLE);
                holder.iv_star5.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EvaluateViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView iv_head;
        TextView tv_name;
        TextView tv_content;
        ImageView iv_star1;
        ImageView iv_star2;
        ImageView iv_star3;
        ImageView iv_star4;
        ImageView iv_star5;
        SimpleDraweeView iv_img1;
        SimpleDraweeView iv_img2;
        SimpleDraweeView iv_img3;

        public EvaluateViewHolder(View itemView) {
            super(itemView);
            iv_head = (SimpleDraweeView) itemView.findViewById(R.id.iv_evaluate_head);
            tv_name = (TextView) itemView.findViewById(R.id.tv_evaluate_name);
            tv_content = (TextView) itemView.findViewById(R.id.tv_evaluate_content);
            iv_star1 = (ImageView) itemView.findViewById(R.id.tv_evaluate_star1);
            iv_star2 = (ImageView) itemView.findViewById(R.id.tv_evaluate_star2);
            iv_star3 = (ImageView) itemView.findViewById(R.id.tv_evaluate_star3);
            iv_star4 = (ImageView) itemView.findViewById(R.id.tv_evaluate_star4);
            iv_star5 = (ImageView) itemView.findViewById(R.id.tv_evaluate_star5);

            iv_img1 = (SimpleDraweeView) itemView.findViewById(R.id.iv_evaluate_img1);
            iv_img2 = (SimpleDraweeView) itemView.findViewById(R.id.iv_evaluate_img2);
            iv_img3 = (SimpleDraweeView) itemView.findViewById(R.id.iv_evaluate_img3);

        }
    }
}
