package com.ihaveu.bc.record;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ihaveu.bc.bean.GoodsBean;
import com.ihaveu.bc.bean.IntegralChangeBean;
import com.ihaveu.bc.bean.IntegralTarnBean;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.model.RecordModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 上午11:18
 */
public class RecordPresenter {
  private Context mContext;
  private RecordView mRecordView;
  private RecordModel mRecordModel;
  public RecordPresenter(Context context ,RecordView recordView){
    mContext = context;
    mRecordView =recordView;
    mRecordModel = new RecordModel(context);
  }
  public void getIntegralChange(){
    mRecordView.showHandleLoading();
    mRecordModel.getIntegralChange( new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getMessage().toString());
          mRecordView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());
          List<IntegralChangeBean> integralChangeBean = new Gson().fromJson(
            JsonUtil.beanToJSONString(model.getResult()),new TypeToken<List<IntegralChangeBean>>(){}.getType());

          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mRecordView.hideHandleLoading();
          mRecordView.showIntegralChange(integralChangeBean);
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast(msg);
        mRecordView.hideHandleLoading();
      }
    });
  }
  public void getIntegralTran(){
    mRecordView.showHandleLoading();
    mRecordModel.getIntegralTran( new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getMessage().toString());
          mRecordView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());
          List<IntegralTarnBean> integralTarnBeanList = new Gson().fromJson(
            JsonUtil.beanToJSONString(model.getResult()),new TypeToken<List<IntegralTarnBean>>(){}.getType());

          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mRecordView.hideHandleLoading();
          mRecordView.showIntegralTran(integralTarnBeanList);
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast(msg);
        mRecordView.hideHandleLoading();
      }
    });
  }

  public void getIntegralHaveTrade(){
    mRecordView.showHandleLoading();
    mRecordModel.getIntegralHaveTrade( new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getMessage().toString());
          mRecordView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());
          List<IntegralTarnBean> integralTarnBeanList = new Gson().fromJson(
            JsonUtil.beanToJSONString(model.getResult()),new TypeToken<List<IntegralTarnBean>>(){}.getType());

          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mRecordView.hideHandleLoading();
          mRecordView.showIntegralTran(integralTarnBeanList);
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast(msg);
        mRecordView.hideHandleLoading();
      }
    });
  }
}
