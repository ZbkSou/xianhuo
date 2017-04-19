package com.ihaveu.bc.utils;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/19
 * Time: 下午4:59
 */
public class StringUtil {
  /**
   * 判断一个字符串是否有效
   *
   * @param text
   * @return
   */
  public static boolean isValidText(String text) {
    if (text == null || text.trim().equals("null") || text.trim().isEmpty()) {
      return false;
    }
    return true;
  }

}
