package com.ihaveu.bc.mine;

import android.app.Activity;
import android.os.Bundle;

import com.ihaveu.bc.R;

import butterknife.ButterKnife;

/**
 * Created by ZBK on 2017/5/3.
 */

public class MineActivity extends Activity{
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    ButterKnife.bind(this);
    init();
  }
  private void init(){

  }
}
