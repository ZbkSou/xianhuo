package com.ihaveu.bc.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.model.AccountsModel;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.okhttphelp.ImageLoader;
import com.ihaveu.bc.utils.TextUtil;
import com.ihaveu.bc.utils.ToastUtil;
import com.ihaveu.bc.widget.DEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/19
 * Time: 下午6:45
 */
public class RegisterActivity extends Activity implements RegisterView  {
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
  @BindView(R.id.captcha)
  DEditText captchaEdit;
  @BindView(R.id.captcha_image)
  ImageView captcha;
  /**
   * 验证码图片是否已经展示
   */
  private boolean isHasShowCaptcha = false;
  private Context mContext;

  private RegisterPresenter registerPresenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    ButterKnife.bind(this);

    mContext = this;
    registerPresenter = new RegisterPresenter(this,this);
  }

  @OnClick(R.id.register_button)
  public void onClick() {
    final HashMap<String, String> params = new HashMap<>();
    params.put("account[phone]", email.getText().toString());
    params.put("account[password]", password.getText().toString());
    params.put("account[password_confirmation]", repeat_password.getText().toString());
    if (TextUtil.isValidText(captchaEdit.getText().toString())) {
      params.put("captcha", captchaEdit.getText().toString());
    }
    params.put("account[client]", "android");
    registerPresenter.goRegister(params);
  }





  @Override
  public void showLoading() {

  }

  @Override
  public void hideLoading() {

  }

  @Override
  public void showCaptcha(String url) {
    ImageLoader.displayCookiesImage(url, captcha);
  }
}
