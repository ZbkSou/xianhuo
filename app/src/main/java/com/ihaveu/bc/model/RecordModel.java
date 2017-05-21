package com.ihaveu.bc.model;

import android.content.Context;
import android.util.Log;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.network.IModelResponse;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 上午11:24
 */
public class RecordModel extends Model {
  private Context mContext;
  private String integralChangeUrl = AppConfig.getApiHost()+"integralChange/admin/getIntegralChangeList";
  private String integralTranUrl = AppConfig.getApiHost()+"integralTran/admin/getIntegralTranList";
  public RecordModel (Context context){
    mContext =  context;
  }
  /**
   * 资金数据
   * @param modelResponse
   */
  public void getIntegralChange( final IModelResponse<SeriverResponse> modelResponse) {
    get(integralChangeUrl, mContext, new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }

      @Override
      public void onError(Call call, Response response, Exception e) {
        Log.d("RecordModel",e.getMessage());
      }
    });
  }
  /**
   * 订单数据
   * @param modelResponse
   */
  public void getIntegralTran(final IModelResponse<SeriverResponse> modelResponse) {
    get(integralTranUrl, mContext, new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }

      @Override
      public void onError(Call call, Response response, Exception e) {
        Log.d("RecordModel",e.getMessage());
      }
    });
  }
  /**
   * 持有订单数据
   * @param modelResponse
   */
  public void getIntegralHaveTrade(final IModelResponse<SeriverResponse> modelResponse) {
    get(integralTranUrl, mContext, new JsonCallBack(SeriverResponse.class) {
      @Override
      public void onSuccess(Object o, Call call, Response response) {
        modelResponse.onSuccess((SeriverResponse) o,null);
      }

      @Override
      public void onError(Call call, Response response, Exception e) {
        Log.d("RecordModel",e.getMessage());
      }
    });
  }
}
