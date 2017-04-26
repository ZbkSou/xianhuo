package com.ihaveu.bc.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 蜡烛图  开 高 低 收 成交量
 */
public class StickData implements Parcelable {

    //时间
    private long time;
    //开盘
    private double open;
    //收盘
    private double close;
    //最高
    private double high;
    //最低
    private double low;
    //量
    private long count;

    //计算均线的零时保存的值
    private double maValue;

    //计算K时需要
    private double rsv;

    public StickData() {
    }

    public StickData(double maValue) {
        this.maValue = maValue;
    }

    /**
     * 显示全部时间
     *
     * @return
     */
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(new Date(time * 1000));
        } catch (Exception e) {
            return "--:--";
        }
    }

    public long getTimeLone() {
        return time;
    }
    /**
     * 只显示到月
     *
     * @return
     */
    public String getTimeNoDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            return sdf.format(new Date(time * 1000));
        } catch (Exception e) {
            return "--:--";
        }
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }



    public double getMaValue() {
        return maValue;
    }

    public void setMaValue(double maValue) {
        this.maValue = maValue;
    }



    public double getRsv() {
        return rsv;
    }

    public void setRsv(double rsv) {
        this.rsv = rsv;
    }

    /**
     * 是否上涨
     * @return
     */
    public boolean isRise() {
        return open <= close;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.time);
        dest.writeDouble(this.open);
        dest.writeDouble(this.close);
        dest.writeDouble(this.high);
        dest.writeDouble(this.low);
        dest.writeLong(this.count);
        dest.writeDouble(this.maValue);
        dest.writeDouble(this.rsv);
    }

    protected StickData(Parcel in) {
        this.time = in.readLong();
        this.open = in.readDouble();
        this.close = in.readDouble();
        this.high = in.readDouble();
        this.low = in.readDouble();
        this.count = in.readLong();
        this.maValue = in.readDouble();
        this.rsv = in.readDouble();
    }

    public static final Creator<StickData> CREATOR = new Creator<StickData>() {
        @Override
        public StickData createFromParcel(Parcel source) {
            return new StickData(source);
        }

        @Override
        public StickData[] newArray(int size) {
            return new StickData[size];
        }
    };
}
