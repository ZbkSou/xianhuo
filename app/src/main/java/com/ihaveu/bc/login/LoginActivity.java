package com.ihaveu.bc.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.main.MainActivity;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.register.RegisterActivity;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.SharedpreferenceUtil;
import com.ihaveu.bc.utils.StringUtil;
import com.ihaveu.bc.utils.TextUtil;
import com.ihaveu.bc.utils.ToastUtil;
import com.ihaveu.bc.widget.DEditText;
import com.ihaveu.bc.widget.DTextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bc on 16/10/8.
 * Describe
 */
public class LoginActivity extends BaseActivity implements LoginView {
  @BindView(R.id.username)
  DEditText usernameEdit;
  @BindView(R.id.password)
  DEditText passwordEdit;
  @BindView(R.id.login)
  Button login;
  @BindView(R.id.logup)
  DTextView logupBut;
private LoginPresenter mLoginPresenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    mLoginPresenter = new LoginPresenter(this,this);

  }
  @Override
  protected void onResume() {
    super.onResume();
    String username = SharedpreferenceUtil.getData("username","")+"";
    String password = SharedpreferenceUtil.getData("password","")+"";
    if(TextUtil.isValidText(username)&&TextUtil.isValidText(password)) {
      passwordEdit.setText(password);
      usernameEdit.setText(username);
    }
  }
  @OnClick({R.id.login,R.id.logup})
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.login:
        HashMap<String,String> params = new HashMap<>();
        if(StringUtil.isValidText(usernameEdit.getText().toString())&&
          StringUtil.isValidText(passwordEdit.getText().toString())){
          params.put("username",usernameEdit.getText().toString());
          params.put("password",passwordEdit.getText().toString());
          mLoginPresenter.logIn(params);
        }else {
          ToastUtil.showToast("请输入完整的登录信息");
        }

        break;
      case R.id.logup:
        ToastUtil.showToast("注册");
        Intent intent;
        intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
        break;
    }


  }

  @Override
  public void logIn() {
    SharedpreferenceUtil.saveData("username",usernameEdit.getText().toString());
    SharedpreferenceUtil.saveData("password",passwordEdit.getText().toString());
//    Intent intent;
//    intent = new Intent(this, MainActivity.class);
//    startActivity(intent);
    finish();
  }
}
