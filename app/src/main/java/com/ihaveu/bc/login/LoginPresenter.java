package com.ihaveu.bc.login;

import android.content.Context;

import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/19
 * Time: 下午4:01
 */
public class LoginPresenter {
  private Context mContext;
  private SessionModel model;
  private LoginView mLoginView;
  public LoginPresenter(Context context,LoginView loginView){
    mContext = context;
    model = new SessionModel(mContext);
    mLoginView = loginView;
  }
  public void logIn(HashMap<String,String> params){
    mLoginView.showLoading();
    HashMap<String,String> loginParams = params;

    model.login(params, new IModelResponse<String>() {
      @Override
      public void onSuccess(String model, ArrayList<String> list) {
        LogUtil.d(model);
        ToastUtil.showToast("登录成功"+model);
        mLoginView.hideLoading();
      }
      @Override
      public void onError(String msg) {
        ToastUtil.showToast("登录失败:"+msg);
        mLoginView.hideLoading();
      }
    });
  }
}
