package com.ihaveu.bc.login;

import android.content.Context;

import com.google.gson.Gson;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
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
    mLoginView.showHandleLoading();
    model.login(params, new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast("登录失败:"+model.getResult().toString());
          mLoginView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());
          UserManage.getInstance().setUserBean(new Gson().fromJson(JsonUtil.beanToJSONString(model.getResult()), UserBean.class));
//          ToastUtil.showToast("登录成功"+model.getResult());
          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mLoginView.hideHandleLoading();
          mLoginView.logIn();
        }
      }
      @Override
      public void onError(String msg) {
        ToastUtil.showToast("登录失败:"+msg);
        mLoginView.hideHandleLoading();
      }
    });
  }
}
