package com.ihaveu.bc.base;

import com.ihaveu.bc.BuildConfig;

/**
 * Created by ZBK on 2017/5/14.
 */

public class AppConfig {
  public static String getApiHost() {
    return getValue("http://119.23.251.26:8080/Game/", "http://119.23.251.26:8080/Game/");
  }
//  public static String getFenShiUrl() {
//    return getValue("http://image.zjwtj.com/goldchart/img/quote/financier/tick/XFAG1_600x400.png", "http://image.zjwtj.com/goldchart/img/quote/financier/tick/XFAG1_600x400.png");
//  }
  private static String getValue(String proEnv, String devEnv) {
    return !BuildConfig.DEBUG ? proEnv : devEnv;//正常情况
  }
}
