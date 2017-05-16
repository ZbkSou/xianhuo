package com.ihaveu.bc.userInfo;

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
 * Created by ZBK on 2017/5/14.
 */

public class UserInfoActivity extends BaseActivity implements UserView {
  @BindView(R.id.weichat_edit)
  DEditText weichatEdit;
  @BindView(R.id.alipay_edit)
  DEditText alipayEdit;
  @BindView(R.id.brank_edit)
  DEditText brankEdit;
  @BindView(R.id.queren_button)
  Button querenButton;
  private UserPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_info);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    presenter = new UserPresenter(this, this);
  }

  @Override
  public void toActivity() {
    this.finish();
  }

  @OnClick(R.id.queren_button)
  public void onViewClicked() {
    HashMap<String, String> params = new HashMap<>();
    if (StringUtil.isValidText(weichatEdit.getText().toString()) ||
      StringUtil.isValidText(alipayEdit.getText().toString()) ||
      StringUtil.isValidText(brankEdit.getText().toString())
      ) {
      params.put("wechat", weichatEdit.getText().toString());
      params.put("cardNum", brankEdit.getText().toString());
      params.put("alipay", alipayEdit.getText().toString());
      presenter.completeInfo(params);

    } else {
      ToastUtil.showToast("请输入完整的信息");
    }

  }
}
