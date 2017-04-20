package com.ihaveu.bc.register;

import android.content.Context;

import com.ihaveu.bc.model.AccountsModel;
import com.ihaveu.bc.model.SessionModel;
import com.ihaveu.bc.network.IModelResponse;
import com.ihaveu.bc.okhttphelp.ImageLoader;
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
  /**
   * 验证码图片是否已经展示
   */
  private boolean isHasShowCaptcha = false;
  public RegisterPresenter(Context context,RegisterView registerView){
    mContext = context;
    sessionModel = new SessionModel(mContext);
    accountsModel = new AccountsModel(mContext);
    mRegisterView = registerView;
  }
  public void goRegister(final HashMap<String, String> params){
    mRegisterView.showLoading();
    //判断是否需要验证码
    accountsModel.isNeedCaptcha(new IModelResponse<String>() {
                                  @Override
                                  public void onSuccess(String model, ArrayList<String> list) {
                                    try {

                                      if (new JSONObject(model).getString("need_captcha").equals("true") && !isHasShowCaptcha) {
//获取验证码
                                        accountsModel.getCaptchaUrl(new IModelResponse<String>() {
                                          @Override
                                          public void onSuccess(String model, ArrayList<String> list) {
                                            try {
                                              isHasShowCaptcha = true;
                                              mRegisterView.showCaptcha(new JSONObject(model).getString("url"));
                                              mRegisterView.hideLoading();
                                            } catch (JSONException e) {
                                              e.printStackTrace();
                                              mRegisterView.hideLoading();
                                            }
                                          }

                                          @Override
                                          public void onError(String msg) {
                                            mRegisterView.hideLoading();
                                          }
                                        });
                                      }

                                      else {
                                        if (isHasShowCaptcha && !TextUtil.isValidText(params.get("captcha"))) {
                                          ToastUtil.showToast( "请输入验证码");
                                        } else if (TextUtil.isValidText(params.get("captcha"))) {
                                          Map<String, String> map = new HashMap<String, String>();
                                          map.put("captcha", params.get("captcha"));
                                          accountsModel.validateCaptcha(map, new IModelResponse<String>() {

                                            @Override
                                            public void onSuccess(String model, ArrayList<String> list) {
                                              try {
                                                if (new JSONObject(model).getString("is_valid").equals("true")) {
                                                  register(params);
                                                } else {
                                                  ToastUtil.showToast( "请重新输入验证码（验证码输入错误）");
                                                  mRegisterView.hideLoading();
                                                }
                                              } catch (JSONException e) {
                                                mRegisterView.hideLoading();
                                                e.printStackTrace();
                                              }
                                            }

                                            @Override
                                            public void onError(String msg) {
                                              mRegisterView.hideLoading();
                                            }
                                          });
                                        } else {
                                          register(params);
                                        }

                                      }
                                    } catch (JSONException e) {
                                      e.printStackTrace();
                                      mRegisterView.hideLoading();
                                    }
                                  }

                                  @Override
                                  public void onError(String msg) {
                                    mRegisterView.hideLoading();
                                  }
                                }
    );
  }
  private void register(Map<String, String> map) {
    accountsModel.register(map, new IModelResponse<String>() {

      @Override
      public void onSuccess(String model, ArrayList<String> list) {
        try {
          ToastUtil.showToast(new JSONObject(model).getJSONObject("account").getInt("id")+"");
          mRegisterView.hideLoading();
        } catch (JSONException e) {
          e.printStackTrace();
          mRegisterView.hideLoading();
        }
      }

      @Override
      public void onError(String msg) {
        ToastUtil.showToast(msg);
        mRegisterView.hideLoading();
      }
    });
  }
}
