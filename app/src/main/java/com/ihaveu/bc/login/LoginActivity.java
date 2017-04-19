package com.ihaveu.bc.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ihaveu.bc.R;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.StringUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bc on 16/10/8.
 * Describe
 */
public class LoginActivity extends Activity implements LoginView {
  @BindView(R.id.username)
  EditText username;
  @BindView(R.id.password)
  EditText password;
  @BindView(R.id.login)
  Button login;
  @BindView(R.id.logup)
  Button logupBut;
private LoginPresenter mLoginPresenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    mLoginPresenter = new LoginPresenter(this,this);
  }

  @OnClick({R.id.login,R.id.logup})
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.login:
        HashMap<String,String> params = new HashMap<>();
        if(StringUtil.isValidText(username.getText().toString())&&
          StringUtil.isValidText(password.getText().toString())){
          params.put("account[login]",username.getText().toString());
          params.put("account[password]",password.getText().toString());
          mLoginPresenter.logIn(params);
        }else {
          ToastUtil.showToast("请输入完整的登录信息");
        }

        break;
      case R.id.logup:
        ToastUtil.showToast("注册");

        break;
    }


  }

  @Override
  public void showLoading() {
    ToastUtil.showToast("showLoading");
  }

  @Override
  public void hideLoading() {
    ToastUtil.showToast("hideLoading");
  }
}
