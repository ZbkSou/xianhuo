package com.ihaveu.bc.model;

import android.content.Context;
import android.util.Log;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.LogUtil;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ZBK on 2017/5/14.
 */

public class UserModel extends Model{
  private Context mContext;
  private String useUrl = AppConfig.getApiHost()+"user/getUserInfo";

  public UserModel(Context context){
    mContext = context;
  }
  /**
   * 用户信息
   * @param modelResponse
   */
  public void getUserInfo( final IModelResponse<SeriverResponse> modelResponse) {
    get(useUrl, mContext, new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }
      @Override
      public void onError(Call call, Response response, Exception e) {
        LogUtil.d(e.getMessage());
      }
    });
  }
}
