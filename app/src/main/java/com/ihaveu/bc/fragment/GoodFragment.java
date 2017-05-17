package com.ihaveu.bc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ihaveu.bc.R;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.manager.GoodManage;
import com.ihaveu.bc.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZBK on 2017/5/6.
 */

public class GoodFragment extends Fragment {
  @BindView(R.id.new_pice_text)
  TextView newPiceText;
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
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_goods, container, false);
    ButterKnife.bind(this, view);
    Bundle data = getArguments();//获得从activity中传递过来的值
    setData(data.getInt("code"));
    return view;
  }

  public void setData(int i) {

    try {
      mGoodsBean = GoodManage.getInstance().getGoodsBeanList().get(i);
      newPiceText.setText(String.format("%.2f", mGoodsBean.getPrice()));
      buyText.setText(mGoodsBean.getBuy() + "");
      shellText.setText(mGoodsBean.getSell() + "");
      openText.setText(mGoodsBean.getOpenPrice() + "");
      closeText.setText(mGoodsBean.getYesterdayClosePrice() + "");
      highText.setText(mGoodsBean.getHightPrice() + "");
      lowText.setText(mGoodsBean.getLowPrice() + "");
    }catch (Exception e){
      LogUtil.d(e.getMessage());
    }
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }
}
