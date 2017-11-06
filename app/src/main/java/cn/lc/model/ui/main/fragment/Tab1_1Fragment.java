package cn.lc.model.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.tab1.adapter.FragmentAdapter;
import cn.lc.model.ui.tab1.bean.Mytab1Title;
import cn.lc.model.ui.tab1.constant.Tab1Constants;
import cn.lc.model.ui.tab1.fragment.Tab1_1Fragment0;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab1_1Fragment extends BaseFragment<Tab1View,Tab1Presenter> {
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
        titleList.add(new Mytab1Title("待接单", Tab1Constants.TAB1_0));
        titleList.add(new Mytab1Title("已接单", Tab1Constants.TAB1_1));
        titleList.add(new Mytab1Title("配送中", Tab1Constants.TAB1_2));
        titleList.add(new Mytab1Title("已完成",Tab1Constants.TAB1_3));
        titleList.add(new Mytab1Title("已取消",Tab1Constants.TAB1_4));
    }
    private void initFragments() {
        for (Mytab1Title title : titleList) {
            Fragment f;
            f=new Tab1_1Fragment0();
            Bundle b = new Bundle();
            b.putInt("type", title.type);
            f.setArguments(b);
            fragmentList.add(f);
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
}
