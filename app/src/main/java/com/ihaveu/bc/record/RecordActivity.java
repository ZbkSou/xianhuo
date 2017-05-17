package com.ihaveu.bc.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ihaveu.bc.R;
import com.ihaveu.bc.base.BaseActivity;
import com.ihaveu.bc.bean.IntegralChangeBean;
import com.ihaveu.bc.bean.IntegralTarnBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 上午11:17
 */
public class RecordActivity extends BaseActivity implements RecordView {
  public static int INTEGRALCHANGE = 0;
  public static int INTEGRALTRAN = 1;
  public static final String KEY_BUNDLE = "KEY_BUNDLE";
  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;
  private RecordPresenter presenter;
  private Bundle bundle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activty_record);
    ButterKnife.bind(this);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    bundle = getIntent().getBundleExtra(KEY_BUNDLE);
    init();
  }

  private void init() {
    presenter = new RecordPresenter(this, this);
    if ((int) bundle.get("key") == INTEGRALCHANGE) {
      presenter.getIntegralChange();
    } else {
      presenter.getIntegralTran();
    }
  }

  @Override
  public void showIntegralChange(List<IntegralChangeBean> goodsBeenList) {
    ChangeAdapter changeAdapter = new ChangeAdapter(this, goodsBeenList);
    recyclerView.setAdapter(changeAdapter);
  }

  @Override
  public void showIntegralTran(List<IntegralTarnBean> goodsBeenList) {
    TranAdapter changeAdapter = new TranAdapter(this, goodsBeenList);
    recyclerView.setAdapter(changeAdapter);
  }
}
