package com.ihaveu.bc.base;

import com.ihaveu.bc.BuildConfig;

/**
 * Created by ZBK on 2017/5/14.
 */

public class AppConfig {
  public static String getApiHost() {
    return getValue("http://119.23.251.26:8080/Game/", "http://119.23.251.26:8080/Game/");
  }
  private static String getValue(String proEnv, String devEnv) {
    return !BuildConfig.DEBUG ? proEnv : devEnv;//正常情况
  }
}
