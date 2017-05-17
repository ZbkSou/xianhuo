package com.ihaveu.bc.record;

import com.ihaveu.bc.base.BaseView;
import com.ihaveu.bc.bean.IntegralChangeBean;
import com.ihaveu.bc.bean.IntegralTarnBean;

import java.util.List;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 上午11:18
 */
public interface RecordView extends BaseView{
  public void showIntegralChange(List<IntegralChangeBean> goodsBeenList );
  public void showIntegralTran(List<IntegralTarnBean> goodsBeenList );
}
