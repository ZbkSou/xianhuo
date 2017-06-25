package com.ihaveu.bc.model;

import android.content.Context;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.LogUtil;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ZBK on 2017/5/27.
 */

public class ConfigModel extends Model{
  private Context mContext;
  private String configUrl =  AppConfig.getApiHost()+"config/getConfig";
  public ConfigModel(Context context){
    mContext = context;
  }
  /**
   * 配置信息
   * @param modelResponse
   */
  public void getInfo(Map<String,String> params, final IModelResponse<SeriverResponse> modelResponse) {
    post(configUrl, mContext, params,new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }
      @Override
      public void onError(Call call, Response response, Exception e) {
        LogUtil.d(e.getMessage());
        modelResponse.onError(e.getMessage());
      }
    });
  }
}
