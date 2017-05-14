package com.ihaveu.bc.main;

import android.content.Context;

import com.google.gson.Gson;
import com.ihaveu.bc.Manager.UserManage;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.model.UserModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ZBK on 2017/5/14.
 */

public class MainPresenter {
  private Context mContext;
  private MainView mMainView;
  private UserModel userModel;

  public MainPresenter(Context context,MainView mainView){
    mContext = context;
    mMainView = mainView;
    userModel = new UserModel(mContext);
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
}
