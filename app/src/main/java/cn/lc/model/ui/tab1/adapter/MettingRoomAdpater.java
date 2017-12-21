package cn.lc.model.ui.tab1.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.lc.model.R;
import cn.lc.model.framework.manager.UIManager;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.tab1.activity.OrderDetailActivity;
import cn.lc.model.ui.tab1.bean.MeetingBean;
import cn.lc.model.ui.tab1.bean.StationeryBean;
import cn.lc.model.ui.tab1.constant.Tab1Constants;

/**
 * Tab1适配器
 */
public class MettingRoomAdpater extends RecyclerView.Adapter<MettingRoomAdpater.ViewHolder> {
    private MyOnClickListener myOnClickListener;
    public Context context;
    public List<MeetingBean.ListBean> datas = null;
    private int type;
    private Tab1Presenter presenter;

    public MettingRoomAdpater(List<MeetingBean.ListBean> datas, Context con, int type, Tab1Presenter presenter) {
        this.datas = datas;
        this.context = con;
        this.type = type;
        this.presenter = presenter;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meet_room_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MeetingBean.ListBean bean = datas.get(position);

        if (type == Tab1Constants.MEETINGROOM_UNSERVICE) {
            holder.tv_functionLeft.setVisibility(View.VISIBLE);
            holder.tv_functionRight.setVisibility(View.VISIBLE);
            holder.tv_functionLeft.setText("立即接单");
            holder.tv_functionRight.setText("拒绝接单");
        } else if (type == Tab1Constants.MEETINGROOM_SERVICING) {
            holder.tv_functionLeft.setVisibility(View.VISIBLE);
            holder.tv_functionRight.setVisibility(View.VISIBLE);
            holder.tv_functionLeft.setText("取消订单");
            holder.tv_functionRight.setText("已完成");
        } else if (type == Tab1Constants.MEETINGROOM_FINISH) {
            holder.tv_functionLeft.setVisibility(View.GONE);
            holder.tv_functionRight.setVisibility(View.GONE);
        } else if (type == Tab1Constants.MEETINGROOM_CANCEL) {
            holder.tv_functionLeft.setVisibility(View.GONE);
            holder.tv_functionRight.setVisibility(View.VISIBLE);
            holder.tv_functionRight.setText("删除订单");
        }

        // 设置网络数据
        holder.iv_img.setImageURI(bean.getImgs());
        holder.tv_ordernum.setText(bean.getOrdercode());
        holder.tv_ordertime.setText(bean.getCreatetime());
        holder.tv_meetingName.setText(bean.getConferencename());
        holder.tv_bookTime.setText(bean.getDate());
        holder.tv_meetingPerson.setText(bean.getAttentdleader());
        holder.tv_meetingType.setText(bean.getConferencetype());
        holder.tv_meetingEquipment.setText(bean.getName());
        holder.tv_meeting_personCount.setText(bean.getAttendnum());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                bundle.putString("orderid", bean.getId() + "");
                bundle.putInt("type",type);

                UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
            }
        });

        holder.tv_functionLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Tab1Constants.MEETINGROOM_UNSERVICE) {
                    presenter.goService(SharedPrefHelper.getInstance().getServicetype() + "",bean.getId() + "");
                } else if (type == Tab1Constants.MEETINGROOM_SERVICING) {
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid", bean.getId() + "");
                    bundle.putInt("type",type);
                    UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
                }

            }
        });

        holder.tv_functionRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Tab1Constants.MEETINGROOM_UNSERVICE) {
                    OrderDetailActivity.isReason = true;
                    Bundle bundle = new Bundle();
                    bundle.putString("serviceType", SharedPrefHelper.getInstance().getServicetype() + "");
                    bundle.putString("orderid", bean.getId() + "");
                    bundle.putInt("type",type);
                    UIManager.turnToAct(context, OrderDetailActivity.class,bundle);
                } else if (type == Tab1Constants.MEETINGROOM_SERVICING) {
                    presenter.finishService(SharedPrefHelper.getInstance().getServicetype() + "",bean.getId() + "");
                } else if (type == Tab1Constants.MEETINGROOM_CANCEL) {
                    presenter.deleteOrder(SharedPrefHelper.getInstance().getServicetype() + "",bean.getId() + "");
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
        public SimpleDraweeView iv_img;
        public TextView tv_ordernum;            // 订单号
        public TextView tv_ordertime;           // 下单时间.
        public TextView tv_meetingName;         // 会议名称.
        public TextView tv_bookTime;            // 预约时间
        public TextView tv_meetingPerson;       // 参会领导.
        public TextView tv_meetingType;         // 会议类型
        public TextView tv_meetingEquipment;    // 会议设备.
        public TextView tv_meeting_personCount; // 会议人数.

        public TextView tv_functionLeft;
        public TextView tv_functionRight;
        public RelativeLayout layout_item;

        public ViewHolder(View view) {
            super(view);
            iv_img = (SimpleDraweeView) view.findViewById(R.id.iv_img);
            tv_ordernum = (TextView) view.findViewById(R.id.tv_ordernum);
            tv_ordertime = (TextView) view.findViewById(R.id.tv_ordertime);
            tv_meetingName= (TextView) view.findViewById(R.id.tv_meetingName);
            tv_bookTime = (TextView) view.findViewById(R.id.tv_bookTime);
            tv_meetingType= (TextView) view.findViewById(R.id.tv_meetingType);
            tv_meetingPerson = (TextView) view.findViewById(R.id.tv_meetingPerson);
            tv_meetingEquipment = (TextView) view.findViewById(R.id.tv_meetingEquipment);
            tv_meeting_personCount = (TextView) view.findViewById(R.id.tv_meeting_personCount);
            tv_functionLeft = (TextView) view.findViewById(R.id.tv_accept_oreder);
            tv_functionRight = (TextView) view.findViewById(R.id.tv_cancel_order);
            layout_item = (RelativeLayout) view.findViewById(R.id.rl_item);
        }
    }
    public interface MyOnClickListener {
        void myOnClickListener(StationeryBean.ListBean bean);
    }
    public void setMyOnClickListener(MyOnClickListener myOnClickListener){
        this.myOnClickListener=myOnClickListener;
    }
}
