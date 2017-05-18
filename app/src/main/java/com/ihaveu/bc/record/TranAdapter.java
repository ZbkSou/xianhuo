package com.ihaveu.bc.record;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihaveu.bc.R;
import com.ihaveu.bc.bean.IntegralTarnBean;
import com.ihaveu.bc.widget.DTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 下午3:23
 */
public class TranAdapter extends RecyclerView.Adapter<TranAdapter.TranAdapterViewHolder> {
  private final LayoutInflater mLayoutInflater;
  private final Context mContext;

  private List<IntegralTarnBean> integralTarnBeanList;

  public TranAdapter(Context context, List<IntegralTarnBean> List) {
    integralTarnBeanList = List;
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  public TranAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TranAdapterViewHolder(mLayoutInflater.inflate(R.layout.item_tran, parent, false));
  }

  @Override
  public void onBindViewHolder(TranAdapterViewHolder holder, int position) {
    if(integralTarnBeanList.get(position).getFlag().equals("-1")){
      holder.tranItemFlag.setText("输");
    } else if(integralTarnBeanList.get(position).getFlag().equals("1")) {
      holder.tranItemFlag.setText("赢");
    } else {
      holder.tranItemFlag.setText("未完成");
    }
    holder.tranItemIntegral.setText(integralTarnBeanList.get(position).getTranIntegral()+"积分");
    holder.tranItemCode.setText(integralTarnBeanList.get(position).getGoodsCode());
    if(integralTarnBeanList.get(position).getTranType().equals("1")){
      holder.tranItemType.setText("看涨");
    } else {
      holder.tranItemType.setText("看跌");
    }
    holder.tranItemPercent.setText(integralTarnBeanList.get(position).getPercent()+"");
    holder.changeItemTime.setText(integralTarnBeanList.get(position).getCreateDate());
    holder.tranItemPice.setText(integralTarnBeanList.get(position).getGoodsPrice()+"");
  }

  @Override
  public int getItemCount() {
    return integralTarnBeanList == null ? 0 : integralTarnBeanList.size();
  }

  public class TranAdapterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tran_item_flag)
    DTextView tranItemFlag;
    @BindView(R.id.tran_item_integral)
    DTextView tranItemIntegral;
    @BindView(R.id.tran_item_code)
    DTextView tranItemCode;
    @BindView(R.id.tran_item_type)
    DTextView tranItemType;
    @BindView(R.id.tran_item_percent)
    DTextView tranItemPercent;
    @BindView(R.id.change_item_time)
    DTextView changeItemTime;
    @BindView(R.id.tran_item_pice)
    DTextView tranItemPice;
    TranAdapterViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
