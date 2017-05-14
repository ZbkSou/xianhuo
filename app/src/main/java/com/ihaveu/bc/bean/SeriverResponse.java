package com.ihaveu.bc.bean;

/**
 * Created by ZBK on 2017/5/14.
 */

public class SeriverResponse<T> {

  /**
   * state : 999
   * message : 操作失败
   * result : 用户名或密码不正确
   */

  private String state;
  private String message;
  private T result;

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}
