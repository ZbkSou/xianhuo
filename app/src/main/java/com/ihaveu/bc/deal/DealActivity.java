package com.ihaveu.bc.deal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import com.ihaveu.bc.R;
import com.ihaveu.bc.adapter.FragmentAdapter;
import com.ihaveu.bc.view.NoScrollViewPager;

import java.util.ArrayList;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/25
 * Time: 下午4:46
 */
public class DealActivity extends FragmentActivity {
  public static final String[] TITLES = {"分时线", "日K"};
  private TabLayout tabLayout;
  private NoScrollViewPager viewPager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_deal);
    tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
    ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
    fragments.add(new FenshiFragment());
    fragments.add(new KLineFragment());
    FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, TITLES);
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);
  }
}
