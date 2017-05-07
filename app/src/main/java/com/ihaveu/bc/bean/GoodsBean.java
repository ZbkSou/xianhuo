package com.ihaveu.bc.bean;

/**
 * Created by ZBK on 2017/5/7.
 */

public class GoodsBean {

  /**
   * code : XFAG1,XFAG2,XFAG3,XFAG4,XFCU1,XFCU2,XFCU3,XFOIL1,XFOIL2,XFOIL3,XFZN1T
   * name : 长江银100克,长江银15千克,长江银60千克,长江银120千克,长江铜1吨,长江铜5吨,长江铜10吨,长江油100桶,长江油500桶,长江油1000桶,长江锌1吨
   * time : 1494014438,1494016197,1494016175,1494016197,1494007199,1494007200,1494007200,1494016197,1494016202,1494016202,1494007187
   * newprice : 3.6100,3609.0000,3609.0000,3609.0000,38369.0000,38369.0000,38369.0000,319.3700,319.3700,319.3700,17814.0000
   * lastclose : 3.6200,3626.0000,3626.0000,3626.0000,38057.0000,38057.0000,38057.0000,312.7000,312.7000,312.7000,17680.0000
   * open : 3.6100,3608.0000,3608.0000,3608.0000,38098.0000,38098.0000,38098.0000,313.0400,313.0400,313.0400,17680.0000
   * high : 3.6500,3650.0000,3650.0000,3650.0000,38410.0000,38410.0000,38410.0000,321.1600,321.1600,321.1600,17831.0000
   * low : 3.5800,3584.0000,3584.0000,3584.0000,37950.0000,37950.0000,37950.0000,301.0700,301.0700,301.0700,17491.0000
   * buy : 3.6200,3615.0000,3615.0000,3614.0000,38429.0000,38429.0000,38429.0000,319.9700,319.9700,319.9700,17829.0000
   * sell : 3.6100,3609.0000,3609.0000,3609.0000,38369.0000,38369.0000,38369.0000,319.3700,319.3700,319.3700,17814.0000
   */

  private String code;
  private String name;
  private String time;
  private String newprice;
  private String lastclose;
  private String open;
  private String high;
  private String low;
  private String buy;
  private String sell;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getNewprice() {
    return newprice;
  }

  public void setNewprice(String newprice) {
    this.newprice = newprice;
  }

  public String getLastclose() {
    return lastclose;
  }

  public void setLastclose(String lastclose) {
    this.lastclose = lastclose;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLow() {
    return low;
  }

  public void setLow(String low) {
    this.low = low;
  }

  public String getBuy() {
    return buy;
  }

  public void setBuy(String buy) {
    this.buy = buy;
  }

  public String getSell() {
    return sell;
  }

  public void setSell(String sell) {
    this.sell = sell;
  }
}
