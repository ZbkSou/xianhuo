package com.ihaveu.bc.manager;

import com.ihaveu.bc.bean.GoodsBean;

import java.util.List;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 下午6:04
 */
public class GoodManage {
  public List<GoodsBean> getGoodsBeanList() {
    return goodsBeenList;
  }

  public void setGoodsBean(List<GoodsBean> userBean) {
    this.goodsBeenList = userBean;
  }

  private List<GoodsBean> goodsBeenList ;
  private  GoodManage(){

  }
  private static GoodManage instance;
  public static GoodManage  getInstance() {
    if (instance == null)
      instance = new GoodManage();
    return instance;
  }
}
