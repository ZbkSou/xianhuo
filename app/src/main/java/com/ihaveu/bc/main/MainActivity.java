package com.ihaveu.bc.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.register.RegisterPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/27
 * Time: 下午2:52
 */
public class MainActivity extends FragmentActivity {
  @BindView(R.id.tabs)
  TabLayout tabLayout;
  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    ButterKnife.bind(this);
    init();
  }
  private void init(){

  }
  /**
   * 初始化TAB标签
   */
  private void initTab() {
    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    List<String> tabList = new ArrayList<>();
    tabList.add("tab1");
    tabList.add("tab2");
    tabList.add("tab3");
    tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

    List<Fragment> fragmentList = new ArrayList<>();
//
//    fragmentList.add(new TabFragment1());
//    fragmentList.add(new TabFragment2());
//    fragmentList.add(new TabFragment3());

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
    viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
    tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
    tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

  }

}
