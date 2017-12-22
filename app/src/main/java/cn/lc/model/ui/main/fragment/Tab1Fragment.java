package cn.lc.model.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.MvpSimpleFragment;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.adapter.FragmentAdapter;
import cn.lc.model.ui.tab1.bean.Mytab1Title;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.fragment.MaintainFragment;
import cn.lc.model.ui.tab1.fragment.MeettingRoomFragment;
import cn.lc.model.ui.tab1.fragment.OrderWaterFragment;
import cn.lc.model.ui.tab1.fragment.StationeryFragment;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab1Fragment extends MvpSimpleFragment<Tab1View, Tab1Presenter> {
    @BindView(R.id.mIndicator)
    MagicIndicator mIndicator;
    @BindView(R.id.score_pager)
    ViewPager viewPager;
    private List<Mytab1Title> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private CommonNavigator navigator;
    private FragmentAdapter fragmentAdapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab1_1);

    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        initTitles();
        initFragments();
        initViewPager();
    }

    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    private void initTitles() {
        if (SharedPrefHelper.getInstance().getServicetype() == 1) {                         // 维修报修
            titleList.add(new Mytab1Title("待服务", Tab1Constants.MAINTAIN_UNSERVICE));
            titleList.add(new Mytab1Title("服务中", Tab1Constants.MAINTAIN_SERVICING));
            titleList.add(new Mytab1Title("已完成", Tab1Constants.MAINTAIN_FINISH));
            titleList.add(new Mytab1Title("已取消", Tab1Constants.MAINTAIN_CANCEL));
        } else if (SharedPrefHelper.getInstance().getServicetype() == 8) {                  // 办公用品
            titleList.add(new Mytab1Title("待接单", Tab1Constants.WORK_UNRECEIVE_ORDER));
            titleList.add(new Mytab1Title("已接单", Tab1Constants.WORK_RECEIVED_ORDER));
            titleList.add(new Mytab1Title("配送中", Tab1Constants.WORK_DELIVERY));
            titleList.add(new Mytab1Title("已完成", Tab1Constants.WORK_FINISH));
            titleList.add(new Mytab1Title("已取消", Tab1Constants.WORK_CANCEL));
        } else if (SharedPrefHelper.getInstance().getServicetype() == 18) {                 // 订水服务
            titleList.add(new Mytab1Title("待接单", Tab1Constants.WATER_UNRECEIVE_ORDER));
            titleList.add(new Mytab1Title("已接单", Tab1Constants.WATER_RECEIVED_ORDER));
            titleList.add(new Mytab1Title("配送中", Tab1Constants.WATER_DELIVERY));
            titleList.add(new Mytab1Title("已完成", Tab1Constants.WATER_FINISH));
            titleList.add(new Mytab1Title("已取消", Tab1Constants.WATER_CANCEL));
        } else if (SharedPrefHelper.getInstance().getServicetype() == 7) {                  // 会议室
            titleList.add(new Mytab1Title("待服务", Tab1Constants.MEETINGROOM_UNSERVICE));
            titleList.add(new Mytab1Title("服务中", Tab1Constants.MEETINGROOM_SERVICING));
            titleList.add(new Mytab1Title("已完成", Tab1Constants.MEETINGROOM_FINISH));
            titleList.add(new Mytab1Title("已取消", Tab1Constants.MEETINGROOM_CANCEL));
        }

    }

    private void initFragments() {
        if (SharedPrefHelper.getInstance().getServicetype() == 1) {         // 维修.
            for (Mytab1Title title : titleList) {
                Fragment f;
                f = new MaintainFragment();
                Bundle b = new Bundle();
                b.putInt("type", title.type);
                f.setArguments(b);
                fragmentList.add(f);
            }
        } else if (SharedPrefHelper.getInstance().getServicetype() == 8) {     //办公用品
            for (Mytab1Title title : titleList) {
                Fragment f;
                f = new StationeryFragment();
                Bundle b = new Bundle();
                b.putInt("type", title.type);
                f.setArguments(b);
                fragmentList.add(f);
            }
        } else if (SharedPrefHelper.getInstance().getServicetype() == 18) {     // 订水
            for (Mytab1Title title : titleList) {
                Fragment f;
                f = new OrderWaterFragment();
                Bundle b = new Bundle();
                b.putInt("type", title.type);
                f.setArguments(b);
                fragmentList.add(f);
            }
        } else if (SharedPrefHelper.getInstance().getServicetype() == 7) {          // 会议.
            for (Mytab1Title title : titleList) {
                Fragment f;
                f = new MeettingRoomFragment();
                Bundle b = new Bundle();
                b.putInt("type", title.type);
                f.setArguments(b);
                fragmentList.add(f);
            }
        }
    }

    private void initViewPager() {
        fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        navigator = new CommonNavigator(getActivity());
        navigator.setAdjustMode(true);
        navigator.setIndicatorOnTop(true);
        navigator.setSkimOver(true);
        navigator.setScrollPivotX(0.5f);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = fragmentList.get(position);
                if (fragment instanceof MaintainFragment) {
                    ((MaintainFragment) fragment).refreshData();
                } else if (fragment instanceof StationeryFragment){
                    ((StationeryFragment) fragment).refreshData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        CommonNavigatorAdapter navigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleList == null ? 0 : titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {

                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(titleList.get(index).title);
                clipPagerTitleView.setBackgroundResource(R.color.white);
                clipPagerTitleView.setTextSize(getResources().getDimensionPixelOffset(R.dimen.sp_common));
                clipPagerTitleView.setTextColor(ContextCompat.getColor(context, R.color.tv_black));
                clipPagerTitleView.setClipColor(ContextCompat.getColor(context, R.color.bg_color));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 15));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setYOffset(UIUtil.dip2px(context, 0.5));
                indicator.setColors(ContextCompat.getColor(context, R.color.bg_color));
                return indicator;
            }
        };
        navigator.setAdapter(navigatorAdapter);
        mIndicator.setNavigator(navigator);
        ViewPagerHelper.bind(mIndicator, viewPager);
    }



    public void Refresh(Fragment object,int max){
        int itemPosition = fragmentAdapter.getItemPosition(object);
        Log.e("viewpage","当前的position == " + itemPosition);
        /*MaintainFragment fragment1 = (MaintainFragment) object;
        fragment1.refreshData();*/


        /*if (itemPosition-1 >= 0) {
            Fragment item = fragmentAdapter.getItem(itemPosition - 1);
            if (item instanceof MaintainFragment) {
                MaintainFragment fragment = (MaintainFragment) item;
                fragment.refreshData();
            }
        }
        if (itemPosition+1 <= max) {
            Fragment item = fragmentAdapter.getItem(itemPosition + 1);
            if (item instanceof MaintainFragment) {
                MaintainFragment fragment = (MaintainFragment) item;
                fragment.refreshData();
            }
        }*/

    }
}
