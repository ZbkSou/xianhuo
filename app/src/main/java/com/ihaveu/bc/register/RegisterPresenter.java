package com.ihaveu.bc.register;

import android.content.Context;

import com.google.gson.Gson;
import com.ihaveu.bc.Manager.UserManage;
import com.ihaveu.bc.bean.SeriverResponse;
import com.ihaveu.bc.bean.UserBean;
import com.ihaveu.bc.model.AccountsModel;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.okhttphelp.ImageLoader;
import com.ihaveu.bc.utils.JsonUtil;
import com.ihaveu.bc.utils.LogUtil;
import com.ihaveu.bc.utils.TextUtil;
import com.ihaveu.bc.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/20
 * Time: 上午10:14
 */
public class RegisterPresenter {
  private Context mContext;
  private SessionModel sessionModel;
  private AccountsModel accountsModel;
  private RegisterView mRegisterView;

  public RegisterPresenter(Context context,RegisterView registerView){
    mContext = context;
    sessionModel = new SessionModel(mContext);
    accountsModel = new AccountsModel(mContext);
    mRegisterView = registerView;
  }
  public void goRegister(final HashMap<String, String> params){
    mRegisterView.showHandleLoading();
    register(params) ;
  }
  private void register(Map<String, String> map) {
    mRegisterView.showHandleLoading();
    accountsModel.register(map, new IModelResponse<SeriverResponse>() {

      @Override
      public void onSuccess(SeriverResponse model, ArrayList<SeriverResponse> list) {
        if(!model.getState().equals("200")){
          ToastUtil.showToast("注册失败:"+model.getResult().toString());
          mRegisterView.hideHandleLoading();
        }else {
          LogUtil.d(model.getResult().toString());
//          UserManage.getInstance().setUserBean(new Gson().fromJson(JsonUtil.beanToJSONString(model.getResult()), UserBean.class));
//          ToastUtil.showToast("注册成功"+model.getResult());
          LogUtil.d(UserManage.getInstance().getUserBean().getUsername());
          mRegisterView.hideHandleLoading();
          mRegisterView.register();
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast("检查网络"+msg);
      }
    });
  }
}
