package com.ihaveu.bc.utils;

import com.google.gson.Gson;

/**
 * Created by ZBK on 2017/5/14.
 */

public class JsonUtil {
  public static String beanToJSONString(Object bean) {
    return new Gson().toJson(bean);
  }
}
