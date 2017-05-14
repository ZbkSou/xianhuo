package com.ihaveu.bc.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ihaveu.bc.Manager.UserManage;
import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.widget.DTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBK on 2017/5/3.
 */

public class MineActivity extends BaseActivity implements MineView {
  @BindView(R.id.login_but)
  DTextView loginBut;
  @BindView(R.id.register_but)
  DTextView registerBut;
  @BindView(R.id.login_register)
  LinearLayout loginRegister;
  @BindView(R.id.mine_user_name)
  DTextView mineUserName;
  @BindView(R.id.mine_money_text)
  DTextView mineMoneyText;
  @BindView(R.id.mine_add_money)
  Button mineAddMoney;
  @BindView(R.id.mine_get_money)
  Button mineGetMoney;
  @BindView(R.id.mine_money_layout)
  RelativeLayout mineMoneyLayout;
  @BindView(R.id.mine_user_info_layout)
  RelativeLayout mineUserInfoLayout;
  @BindView(R.id.mine_trade_layout)
  RelativeLayout mineTradeLayout;
  @BindView(R.id.mine_set_password_layout)
  RelativeLayout mineSetPasswordLayout;
  @BindView(R.id.mine_close)
  Button mineClose;
  @BindView(R.id.mine_user_layout)
  LinearLayout mineUserLayout;

  private MinePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mine);
    ButterKnife.bind(this);
    init();
  }

  private void init() {

    presenter = new MinePresenter(this,this);

  }

  @Override
  protected void onResume() {
    super.onResume();
    isLogin();}

  @OnClick({R.id.login_but, R.id.register_but, R.id.mine_add_money, R.id.mine_get_money,
    R.id.mine_money_layout,R.id.mine_user_info_layout,R.id.mine_trade_layout,
    R.id.mine_set_password_layout,R.id.mine_close})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.login_but:
        break;
      case R.id.register_but:
        break;
      case R.id.mine_add_money:
        break;
      case R.id.mine_get_money:
        break;
      case R.id.mine_user_info_layout:
        break;
      case R.id.mine_money_layout:
        break;
      case R.id.mine_trade_layout:
        break;
      case R.id.mine_set_password_layout:
        break;
      case R.id.mine_close:
        presenter.exit();
        break;
    }
  }

  @Override
  public void exit() {
    isLogin();
  }
  private boolean isLogin(){
    if (UserManage.getInstance().getUserBean() != null) {
      loginRegister.setVisibility(View.GONE);
      mineUserLayout.setVisibility(View.VISIBLE);
      mineUserName.setText(UserManage.getInstance().getUserBean().getUsername());
      mineMoneyText.setText("可用资金:"+UserManage.getInstance().getUserBean().getIntegral());
      return true;
    } else {
      loginRegister.setVisibility(View.VISIBLE);
      mineUserLayout.setVisibility(View.GONE);
      return false;
    }
  }
}
