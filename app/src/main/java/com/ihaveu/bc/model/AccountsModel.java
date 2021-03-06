package com.ihaveu.bc.model;

import android.content.Context;
import android.util.Log;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by bc on 16/10/11.
 * Describe
 */
public class AccountsModel extends Model{
  private Context mContext;
  private String registerUrl = AppConfig.getApiHost()+"user/register";
  private String setPasswordUrl = AppConfig.getApiHost()+"user/admin/resetPassword";
  public AccountsModel (Context context){
    mContext = context;
  }
  /**
   * 注册
   * @param params
   * @param modelResponse
   */
  public void register(Map<String,String> params, final IModelResponse<SeriverResponse> modelResponse) {
    post(registerUrl, mContext, params, new JsonCallBack(SeriverResponse.class) {
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

  /**
   * 修改密码
   * @param params
   * @param modelResponse
   */
  public void setPassword(Map<String,String> params, final IModelResponse<SeriverResponse> modelResponse) {
    post(setPasswordUrl, mContext, params, new JsonCallBack(SeriverResponse.class) {
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
