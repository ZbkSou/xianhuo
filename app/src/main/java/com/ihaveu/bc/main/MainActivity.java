package com.ihaveu.bc.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.fragment.GoodFragment;
import com.ihaveu.bc.register.RegisterPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
  @BindView(R.id.up_but)
  Button upButton;
  @BindView(R.id.down_but)
  Button downButton;
  private GoodsBean goodsBean;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    goodsBean = new GoodsBean();
    goodsBean.setBuy("3.16");
    goodsBean.setCode("XFAG1");
    goodsBean.setName("长江银100克");
    goodsBean.setSell("3.18");
    goodsBean.setNewprice("3.15");
    goodsBean.setHigh("3.17");
    goodsBean.setLow("3.18");
    goodsBean.setOpen("3.18");
    goodsBean.setLastclose("3.18");
    goodsBean.setBuy("3.19");
    initTab();
  }

  @OnClick({ R.id.up_but, R.id.down_but,R.id.money_text})
   void onViewClick(Button button){
    switch (button.getId()){
      case R.id.up_but :
        showPopwindow(goodsBean,true);
        break;
      case R.id.down_but:
        showPopwindow(goodsBean,false);
        break;
      case R.id.layout_main_mine:
        Intent intent;
        intent = new Intent(this, com.ihaveu.bc.mine.MineActivity.class);
        startActivity(intent);
        break;
    }


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

    GoodFragment goodFragment1 = new GoodFragment();
    GoodFragment goodFragment2 = new GoodFragment();
    GoodFragment goodFragment3 = new GoodFragment();

    fragmentList.add(goodFragment1);
    fragmentList.add(goodFragment2);
    fragmentList.add(goodFragment3);

    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
    TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
    viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
    tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
    tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

  }
  /**
   * 显示popupWindow
   */
  private void showPopwindow(GoodsBean goodsBean,boolean status) {
    // 利用layoutInflater获得View
    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.popwindow_buy, null);

    // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

    PopupWindow window = new PopupWindow(view,
      WindowManager.LayoutParams.MATCH_PARENT,
      WindowManager.LayoutParams.WRAP_CONTENT);

    // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
    window.setFocusable(true);



    // 实例化一个ColorDrawable颜色为半透明
    ColorDrawable dw = new ColorDrawable(0xb0000000);
    window.setBackgroundDrawable(dw);


    // 设置popWindow的显示和消失动画
    window.setAnimationStyle(R.style.mypopwindow_anim_style);
    // 在底部显示
    window.showAtLocation(MainActivity.this.findViewById(R.id.up_but),
      Gravity.BOTTOM, 0, 0);
    // 这里检验popWindow里的button是否可以点击
    TextView statusText = (TextView) view.findViewById(R.id.status);
    TextView goodsNameText = (TextView) view.findViewById(R.id.goods_name);
    goodsNameText.setText(goodsBean.getName());
    TextView newPiceText = (TextView) view.findViewById(R.id.new_pice);
    newPiceText.setText(goodsBean.getNewprice());
    if(status){
      statusText.setText("看涨");
    }else {
      statusText.setText("看跌");
    }


    goodsNameText.setText(goodsBean.getName());
    newPiceText.setText(goodsBean.getNewprice());
    Button buyButton = (Button) view.findViewById(R.id.buy_but);
    buyButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        System.out.println("第一个按钮被点击了");
      }
    });

    //popWindow消失监听方法
    window.setOnDismissListener(new PopupWindow.OnDismissListener() {

      @Override
      public void onDismiss() {
        System.out.println("popWindow消失");
      }
    });

  }
}
