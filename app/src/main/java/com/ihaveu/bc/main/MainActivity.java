package com.ihaveu.bc.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.register.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/27
 * Time: 下午2:52
 */
public class MainActivity extends Activity {
   @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mains);
    ButterKnife.bind(this);
    init();
  }
  private void init(){
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setAdapter(new GoodsRecyclerViewAdapter(this));
  }
}
