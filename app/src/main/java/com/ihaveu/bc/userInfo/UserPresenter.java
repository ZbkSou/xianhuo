package com.ihaveu.bc.userInfo;

import android.content.Context;

import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.manager.UserManage;
import com.ihaveu.bc.model.UserModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZBK on 2017/5/15.
 */

public class UserPresenter {
  private Context mContext;
  private UserView userView;
  private UserModel userModel;

  public UserPresenter(Context context, UserView userView){
    mContext = context;
    this.userView = userView;
    userModel = new UserModel(mContext);
  }
  public void completeInfo( Map<String, String> params){
    userModel.completeInfo(params, new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast("修改失败:"+model.getResult().toString());
          userView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());

          ToastUtil.showToast(model.getResult().toString());
          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          userView.hideHandleLoading();
          userView.toActivity();
        }
      }

      @Override
      public void onError(String msg) {

      }
    });
  }

}
