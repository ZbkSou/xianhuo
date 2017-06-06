package com.ihaveu.bc.register;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.utils.StringUtil;
import com.ihaveu.bc.utils.ToastUtil;
import com.ihaveu.bc.widget.DEditText;
import com.ihaveu.bc.widget.DTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/19
 * Time: 下午6:45
 */
public class RegisterActivity extends BaseActivity implements RegisterView {
  @BindView(R.id.email)
  DEditText email;
  @BindView(R.id.name)
  DEditText name;
  @BindView(R.id.password)
  DEditText password;
  @BindView(R.id.repeat_password)
  DEditText repeat_password;
  @BindView(R.id.register_button)
  Button registerButton;
  @BindView(R.id.go_login)
  DTextView goLogin;
  @BindView(R.id.remark)
  DEditText remark;
  @BindView(R.id.login_register)
  RelativeLayout loginRegister;


  private Context mContext;

  private RegisterPresenter registerPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);
    mContext = this;
    registerPresenter = new RegisterPresenter(this, this);
  }

  @OnClick({R.id.register_button, R.id.go_login})
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.register_button:

        //登录
        final HashMap<String, String> params = new HashMap<>();
        if (StringUtil.isValidText(email.getText().toString()) &&
          StringUtil.isValidText(password.getText().toString()) &&
          StringUtil.isValidText(repeat_password.getText().toString()) &&
          StringUtil.isValidText(remark.getText().toString()) &&
        StringUtil.isValidText(name.getText().toString()) && password.getText().toString().length() > 5){
        if (repeat_password.getText().toString().equals(password.getText().toString())) {
          params.put("phoneNumber", email.getText().toString());
          params.put("remarks", remark.getText().toString());
          params.put("username", name.getText().toString());
          params.put("password", password.getText().toString());
          registerPresenter.goRegister(params);
        } else {
          ToastUtil.showToast("请确认密码");
        }
      } else{
        ToastUtil.showToast("请输入完整的注册信息");
      }
      break;
      case R.id.go_login:
        finish();
        break;
    }


  }


  @Override
  public void register() {
//    Intent intent;
//    intent = new Intent(this, MainActivity.class);
//    startActivity(intent);
    finish();
  }
}
