package com.ihaveu.bc.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ZBK on 2017/5/17.
 */

public class CommonUtil {
  private static long mExitTime = 0;
  public static boolean finishActivity(Context context) {
    if ((System.currentTimeMillis() - mExitTime) > 2000) {//
      // 如果两次按键时间间隔大于2000毫秒，则不退出
      Log.d("first", "第一次按下");
      ToastUtil.showToast("再按一次退出程序");
      mExitTime = System.currentTimeMillis();// 更新mExitTime
    } else {
      Log.d("second", "第二次按下");
      System.exit(0);
    }
    return true;
  }
  private static long mCD = 0;
  public static boolean buyCD(Context context) {
    if ((System.currentTimeMillis() - mCD) > 3000) {//
      mCD = System.currentTimeMillis();// 更新mExitTime
      return true;
    } else {
      ToastUtil.showToast("下单操作过快");
      Log.d("second", "第二次按下");
      return false ;
    }

  }
}
