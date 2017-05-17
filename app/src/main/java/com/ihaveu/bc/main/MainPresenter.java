package com.ihaveu.bc.main;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.manager.GoodManage;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.model.GoodsModel;
import com.ihaveu.bc.model.UserModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZBK on 2017/5/14.
 */

public class MainPresenter {
  private Context mContext;
  private MainView mMainView;
  private UserModel userModel;
  private GoodsModel goodsModel;
  public MainPresenter(Context context,MainView mainView){
    mContext = context;
    mMainView = mainView;
    userModel = new UserModel(mContext);
    goodsModel = new GoodsModel(mContext);
  }
  public void getUserInfo(){
    userModel.getUserInfo(new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getResult().toString());
        }else {
          LogUtil.d(model.getResult().toString());
          UserManage.getInstance().setUserBean(new Gson().fromJson(JsonUtil.beanToJSONString(model.getResult()), UserBean.class));
          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mMainView.showUser();
        }
      }
      @Override
      public void onError(String msg) {

      }
    });
  }
  public void getGoodData(){
    goodsModel.getGoods(new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getResult().toString());
        }else {
          LogUtil.d(model.getResult().toString());
          List<GoodsBean> goodsBeenList = new Gson().fromJson(JsonUtil.beanToJSONString(model.getResult()),new TypeToken<List<GoodsBean>>(){}.getType());
          GoodManage.getInstance().setGoodsBean(goodsBeenList);
          mMainView.showData(goodsBeenList);
        }
      }
      @Override
      public void onError(String msg) {

      }
    });
  }
  public void getRefreshGoodData(){
    goodsModel.getGoods(new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getResult().toString());
        }else {
          LogUtil.d(model.getResult().toString());
          List<GoodsBean> goodsBeenList = new Gson().fromJson(JsonUtil.beanToJSONString(model.getResult()),new TypeToken<List<GoodsBean>>(){}.getType());
          GoodManage.getInstance().setGoodsBean(goodsBeenList);
          mMainView.showRefresh();
        }
      }
      @Override
      public void onError(String msg) {

      }
    });
  }
  public void buyGoods(Map<String,String> params){
    goodsModel.buyGoods(params,new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(model.getState().equals("200")){
          ToastUtil.showToast(model.getResult().toString());
          mMainView.dismissPopu();
          getUserInfo();
        }else {
          LogUtil.d(model.getResult().toString());
        }
      }
      @Override
      public void onError(String msg) {

      }
    });
  }

}
