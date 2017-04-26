package com.ihaveu.bc.bean;

import java.util.List;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/26
 * Time: 下午6:05
 */
public class DataResponse {
  private String error_code;

  public String getError_code() {
    return error_code;
  }

  public void setError_code(String error_code) {
    this.error_code = error_code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  private String msg;
  private String param;
}
