package com.ihaveu.bc.model;

import android.content.Context;
import android.util.Log;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.bean.Session;
import com.ihaveu.bc.network.IModelResponse;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by bc on 16/10/8.
 * Describe
 */
public class SessionModel extends Model{
  private Context mContext;
  private String Url = AppConfig.getApiHost()+"user/login";
  private String exitUrl = AppConfig.getApiHost()+"user/exitSystem";


  public SessionModel (Context context){
    mContext = context;
  }
  public void login(Map<String,String> params, final IModelResponse<SeriverResponse> modelResponse){
    post(Url, mContext, params, new JsonCallBack(SeriverResponse.class) {

      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }

      @Override
      public void onError(Call call, Response response, Exception e) {
        Log.d("Login",e.getMessage());
      }
    });
  }
  public void isLogin(final IModelResponse<Session> modelResponse){
    get(Url, mContext, new JsonCallBack(Session.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((Session) o,null);
      }
    });
  }
  public void exit( final IModelResponse<SeriverResponse> modelResponse){
    get(exitUrl, mContext, new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }
      @Override
      public void onError(Call call, Response response, Exception e) {
        Log.d("Login",e.getMessage());
      }
    });
  }




}
