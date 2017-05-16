package com.ihaveu.bc.setpassword;

import android.content.Context;

import com.google.gson.Gson;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.model.AccountsModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ZBK on 2017/5/15.
 */
public class SetPasswordPresenter {
  private Context mContext;
  private SetPasswordView mSetPasswordView;
private AccountsModel accountsModel;
  public SetPasswordPresenter(Context context, SetPasswordView setPasswordView){
    mContext = context;
    mSetPasswordView = setPasswordView;
    accountsModel= new AccountsModel(mContext);
  }
  public void setPassword(HashMap<String,String> params){
    accountsModel.setPassword(params, new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast("修改失败:"+model.getResult().toString());
          mSetPasswordView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());

          ToastUtil.showToast(model.getResult().toString());
          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mSetPasswordView.hideHandleLoading();
          mSetPasswordView.setSuccess();
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast("修改失败:"+msg);
        mSetPasswordView.hideHandleLoading();
      }
    });
  }
}
