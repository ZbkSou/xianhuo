package com.ihaveu.bc.mine;

import android.content.Context;

import com.google.gson.Gson;
import com.ihaveu.bc.Manager.UserManage;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.model.AccountsModel;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by ZBK on 2017/5/14.
 */

public class MinePresenter {
  private Context mContext;
  private SessionModel sessionModel;
  private MineView mineView;
  public MinePresenter(Context context,MineView registerView){
    mContext = context;
    sessionModel = new SessionModel(mContext);
    mineView = registerView;
  }
  public void exit(){
    sessionModel.exit(new IModelResponse<SeriverResponse>() {
      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast(model.getResult().toString());
        }else {
          LogUtil.d(model.getResult().toString());
          UserManage.getInstance().setUserBean(null);
          mineView.exit();
        }
      }

      @Override
      public void onError(String msg) {

      }
    });

  }
}
