package com.ihaveu.bc.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ihaveu.bc.login.LoginActivity;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.record.RecordActivity;
import com.ihaveu.bc.register.RegisterActivity;
import com.ihaveu.bc.setpassword.SetPasswordActivity;
import com.ihaveu.bc.userInfo.UserInfoActivity;
import com.ihaveu.bc.userInfo.UserPresenter;
import com.ihaveu.bc.utils.StringUtil;
import com.ihaveu.bc.utils.TextUtil;
import com.ihaveu.bc.utils.ToastUtil;
import com.ihaveu.bc.widget.DTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static com.ihaveu.bc.record.RecordActivity.INTEGRALCHANGE;
import static com.ihaveu.bc.record.RecordActivity.INTEGRALTRAN;
import static com.ihaveu.bc.record.RecordActivity.KEY_BUNDLE;

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
//  @BindView(R.id.mine_add_money)
//  Button mineAddMoney;
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
//    mineAddMoney.setVisibility(View.GONE);
    presenter = new MinePresenter(this,this);

  }

  @Override
  protected void onResume() {
    super.onResume();
    isLogin();}

  @OnClick({R.id.login_but, R.id.register_but, R.id.mine_get_money,
    R.id.mine_money_layout,R.id.mine_user_info_layout,R.id.mine_trade_layout,
    R.id.mine_set_password_layout,R.id.mine_close})
  public void onViewClicked(View view) {
    Intent intent;
    switch (view.getId()) {
      case R.id.login_but:
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        break;
      case R.id.register_but:
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        break;
      case R.id.mine_add_money:
        break;
//      提现
      case R.id.mine_get_money:
        LayoutInflater factory = LayoutInflater.from(MineActivity.this);//提示框
        final View dialogView = factory.inflate(R.layout.dialog_get_money, null);//这里必须是final的
        final EditText edit=(EditText)dialogView.findViewById(R.id.input_money);//获得输入框对象
        edit.setHint("输入提现金额");//输入框默认值
        edit.setSingleLine(true);
        edit.setInputType(TYPE_CLASS_NUMBER);
        new AlertDialog.Builder(MineActivity.this)
          .setTitle("提现")//提示框标题
          .setView(dialogView)
          .setPositiveButton("确定",//提示框的两个按钮
            new android.content.DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                if(UserManage.getInstance().getUserBean()!=null){
                  if(TextUtil.isValidText(edit.getText().toString())&&
                    TextUtil.isValidText(UserManage.getInstance().getUserBean().getWechat())){
                    HashMap<String,String> params = new HashMap<>();
                    params.put("presentIntegral",edit.getText().toString());
                    params.put("cardNum",UserManage.getInstance().getUserBean().getCardNum());
                    presenter.getMoney(params);
                  }else {
                    ToastUtil.showToast("请完善个人信息");
                  }
                }else {
                  ToastUtil.showToast("请先登录");
                }


              }
            }).setNegativeButton("取消", null).create().show();

        break;
      case R.id.mine_user_info_layout:
        intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
        break;
      case R.id.mine_money_layout:
        intent = new Intent(this, RecordActivity.class);
        Bundle moneyBundle = new Bundle();
        moneyBundle.putInt("key", INTEGRALCHANGE);
        intent.putExtra(KEY_BUNDLE,moneyBundle);
        startActivity(intent);
        break;
      case R.id.mine_trade_layout:
        intent = new Intent(this, RecordActivity.class);
        Bundle tradeBundle = new Bundle();
        tradeBundle.putInt("key", INTEGRALTRAN);
        intent.putExtra(KEY_BUNDLE,tradeBundle);
        startActivity(intent);
        break;
      case R.id.mine_set_password_layout:
        intent = new Intent(this, SetPasswordActivity.class);
        startActivity(intent);
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
    presenter.getUserInfo();
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
