package com.ihaveu.bc.setpassword;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.utils.StringUtil;
import com.ihaveu.bc.utils.ToastUtil;
import com.ihaveu.bc.widget.DEditText;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBK on 2017/5/15.
 */

public class SetPasswordActivity extends BaseActivity implements SetPasswordView {
  @BindView(R.id.password)
  DEditText password;
  @BindView(R.id.set_button)
  Button setButton;
  @BindView(R.id.repeat_password)
  DEditText repeatPassword;
  private SetPasswordPresenter presenter;
  private Context mContext;
  private SetPasswordView mSetPasswordView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activty_set_password);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    presenter = new SetPasswordPresenter(this, this);

  }

  @Override
  public void setSuccess() {

    finish();
  }

  @OnClick(R.id.set_button)
  public void onViewClicked() {
    HashMap<String, String> params = new HashMap<>();
    if (StringUtil.isValidText(repeatPassword.getText().toString()) &&
      StringUtil.isValidText(password.getText().toString())&&repeatPassword.getText().toString().length()>5) {
      if (repeatPassword.getText().toString().equals(password.getText().toString())) {
        params.put("password", password.getText().toString());
        presenter.setPassword(params);
      } else {
        ToastUtil.showToast("请确认密码");
      }
    } else {
      ToastUtil.showToast("请输入完整的信息");
    }

  }
}
