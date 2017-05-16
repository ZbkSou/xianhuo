package com.ihaveu.bc.model;

import android.content.Context;
import android.util.Log;

import com.ihaveu.bc.base.AppConfig;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.callback.JsonCallBack;
import com.ihaveu.bc.network.IModelResponse;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ZBK on 2017/5/16.
 */

public class GoodsModel extends Model{
  private Context mContext;
  private String goodsUrl = AppConfig.getApiHost()+"cashGoods/getCashGoodsItem";
  private String buyGoodsUrl = AppConfig.getApiHost()+"integralTran/purchaseOrder";
  public GoodsModel (Context context){
    mContext =  context;
  }
  /**
   * 现货数据
   * @param modelResponse
   */
  public void getGoods( final IModelResponse<SeriverResponse> modelResponse) {
    get(goodsUrl, mContext, new JsonCallBack(SeriverResponse.class) {
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
   * 现货数据
   * @param modelResponse
   */
  public void buyGoods(Map<String,String> params, final IModelResponse<SeriverResponse> modelResponse) {
    post(buyGoodsUrl, mContext,params, new JsonCallBack(SeriverResponse.class) {
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
