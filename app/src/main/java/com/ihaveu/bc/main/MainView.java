package com.ihaveu.bc.main;

import com.ihaveu.bc.base.BaseView;
import com.ihaveu.bc.bean.GoodsBean;

import java.util.List;

/**
 * Created by ZBK on 2017/5/14.
 */

public interface MainView extends BaseView {

  public void showUser();
  public void showData(List<GoodsBean> goodsBeanList);
  public void dismissPopu();
}
