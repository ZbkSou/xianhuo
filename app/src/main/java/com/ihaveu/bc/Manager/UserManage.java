package com.ihaveu.bc.Manager;

import com.ihaveu.bc.bean.UserBean;

/**
 * Created by ZBK on 2017/5/14.
 */

public class UserManage {
  public UserBean getUserBean() {
    return userBean;
  }

  public void setUserBean(UserBean userBean) {
    this.userBean = userBean;
  }

  private UserBean userBean;
  private  UserManage(){

  }
  private static UserManage instance;
  public static UserManage  getInstance() {
    if (instance == null)
      instance = new UserManage();
    return instance;
  }

}
