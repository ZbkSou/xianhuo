package com.ihaveu.bc.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.fragment.GoodFragment;
import com.ihaveu.bc.manager.GoodManage;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.mine.MineActivity;
import com.ihaveu.bc.widget.DTextView;

import java.util.ArrayList;
import java.util.HashMap;
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
public class MainActivity extends BaseActivity implements MainView {
  @BindView(R.id.tabs)
  TabLayout tabLayout;
  @BindView(R.id.viewpager)
  ViewPager viewPager;
  @BindView(R.id.up_but)
  Button upButton;
  @BindView(R.id.down_but)
  Button downButton;
  @BindView(R.id.user_text)
  DTextView userText;
  @BindView(R.id.money_text)
  DTextView moneyText;
  @BindView(R.id.layout_main_mine)
  RelativeLayout layoutMainMine;

  private GoodsBean goodsBean;
  private MainPresenter mainPresenter;
  private GoodFragment goodFragment1;
  private GoodFragment goodFragment2;
  private GoodFragment goodFragment3;
  private List<GoodsBean> mGoodsBeanList;
  private String Point = "3";
  private String Money = "10";
  PopupWindow window;
  String[] point_XFAG1;
  String[] point_XFOIL1;
  String[] point_XFCU1;
  public static int XFAG = 0;
  public static int XFOIL = 1;
  public static int XFCU= 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    ButterKnife.bind(this);
    init();

  }

  @Override
  protected void onResume() {
    super.onResume();
    getUserInfo();
  }

  private void init() {
    Resources res = getResources();
    point_XFAG1 = res.getStringArray(R.array.point_XFAG1);
    point_XFOIL1 = res.getStringArray(R.array.point_XFOIL1);
    point_XFCU1 = res.getStringArray(R.array.point_XFCU1);

    mainPresenter = new MainPresenter(this, this);
    mainPresenter.getGoodData();
  }

  @OnClick({R.id.up_but, R.id.down_but, R.id.money_text, R.id.layout_main_mine})
  void onViewClick(View button) {
    switch (button.getId()) {
      case R.id.up_but:
        goodsBean = GoodManage.getInstance().getGoodsBeanList().get(viewPager.getCurrentItem());
        if(viewPager.getCurrentItem()==0){
          showPopwindow(goodsBean, true,point_XFAG1);
        }else if(viewPager.getCurrentItem()==1){
          showPopwindow(goodsBean, true,point_XFCU1 );
        }else {
          showPopwindow(goodsBean, true,point_XFOIL1);
        }
        break;
      case R.id.down_but:
        goodsBean =  GoodManage.getInstance().getGoodsBeanList().get(viewPager.getCurrentItem());
        if(viewPager.getCurrentItem()==0){
          showPopwindow(goodsBean, false,point_XFAG1);
        }else if(viewPager.getCurrentItem()==1){
          showPopwindow(goodsBean, false,point_XFCU1 );
        }else {
          showPopwindow(goodsBean, false,point_XFOIL1);
        }
        break;
      case R.id.layout_main_mine:
        Intent intent;
        intent = new Intent(this, MineActivity.class);
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
    tabList.add("长江银");
    tabList.add("长江铜");
    tabList.add("长江油");
    tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(0)));//添加tab选项卡
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(1)));
    tabLayout.addTab(tabLayout.newTab().setText(tabList.get(2)));

    List<Fragment> fragmentList = new ArrayList<>();

    goodFragment1 = new GoodFragment();
    goodFragment2 = new GoodFragment();
    goodFragment3 = new GoodFragment();

    Bundle data1 = new Bundle();
    data1.putInt("code", XFAG);
    goodFragment1.setArguments(data1);
    Bundle data2 = new Bundle();
    data2.putInt("code", XFCU);
    goodFragment2.setArguments(data2);
    Bundle data3 = new Bundle();
    data3.putInt("code", XFOIL);
    goodFragment3.setArguments(data3);

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
  private void showPopwindow(final GoodsBean goodsBean, final boolean status,String[] point) {
    // 利用layoutInflater获得View
    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View view = inflater.inflate(R.layout.popwindow_buy, null);

    // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

    window = new PopupWindow(view,
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


    RadioButton buyPoint3 = (RadioButton) view.findViewById(R.id.buy_point_3);
    buyPoint3.setText(point[0]);
    RadioButton buyPoint4 = (RadioButton) view.findViewById(R.id.buy_point_4);
    buyPoint4.setText(point[1]);
    RadioButton buyPoint6= (RadioButton) view.findViewById(R.id.buy_point_6);
    buyPoint6.setText(point[2]);

    // 这里检验popWindow里的button是否可以点击
    TextView statusText = (TextView) view.findViewById(R.id.status);
    TextView goodsNameText = (TextView) view.findViewById(R.id.goods_name);
    goodsNameText.setText(goodsBean.getName());
    TextView newPiceText = (TextView) view.findViewById(R.id.new_pice);
    newPiceText.setText(goodsBean.getPrice()+"");

    if (status) {
      statusText.setText("看涨");
    } else {
      statusText.setText("看跌");
    }


    goodsNameText.setText(goodsBean.getName());
    newPiceText.setText(goodsBean.getPrice()+"");
    Button buyButton = (Button) view.findViewById(R.id.buy_but);
    RadioGroup radioGroupPoint = (RadioGroup) view.findViewById(R.id.buy_point);

    radioGroupPoint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButtonPoint = (RadioButton) view.findViewById(checkedId);
        Point = radioButtonPoint.getText().toString();
      }
    });

    RadioGroup radioGroupMoney = (RadioGroup) view.findViewById(R.id.bug_money);
    radioGroupMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButtonPoint = (RadioButton) view.findViewById(checkedId);
        Money = radioButtonPoint.getText().toString();
      }
    });

    buyButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        HashMap<String, String> params = new HashMap<>();
        params.put("tranType", status ? "1" : "-1");
        params.put("tranIntegral", Money);
        params.put("goodsCode", goodsBean.getCode());
        params.put("goodsPrice", goodsBean.getPrice()+"");
        params.put("percent", Point);
        System.out.println("第一个按钮被点击了" + Point + Money);
        mainPresenter.buyGoods(params);
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

  private void getUserInfo() {
    mainPresenter.getUserInfo();

  }

  @Override
  public void showUser() {
//    if(TextUtil.isValidText(UserManage.getInstance().getUserBean().getName())){
//      userText.setText(UserManage.getInstance().getUserBean().getName());
//    }else {
    userText.setText(UserManage.getInstance().getUserBean().getUsername());
//    }
    moneyText.setText("可用资金" + UserManage.getInstance().getUserBean().getIntegral());

  }

  @Override
  public void showData(List<GoodsBean> goodsBeanList) {
    mGoodsBeanList = goodsBeanList;
    initTab();
  }

  @Override
  public void dismissPopu() {
    window.dismiss();
  }
}
