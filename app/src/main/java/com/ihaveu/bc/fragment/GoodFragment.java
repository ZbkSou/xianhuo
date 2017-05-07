package com.ihaveu.bc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.bean.GoodsBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZBK on 2017/5/6.
 */

public class GoodFragment extends Fragment {
  private GoodsBean mGoodsBean;
  @BindView(R.id.buy_text)
   TextView buyText;
  @BindView(R.id.shell_text)
   TextView shellText;
  @BindView(R.id.open_text)
   TextView openText;
  @BindView(R.id.close_text)
   TextView closeText;
  @BindView(R.id.high_text)
   TextView highText;
  @BindView(R.id.low_text)
   TextView lowText;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fragment_goods, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
  public void setData(GoodsBean goodsBean){
    mGoodsBean = goodsBean;

    buyText.setText(goodsBean.getBuy());
    shellText.setText(goodsBean.getSell());
    openText.setText(goodsBean.getOpen());
    closeText.setText(goodsBean.getLastclose());
    highText.setText(goodsBean.getHigh());
    lowText.setText(goodsBean.getLow());
  }



}
