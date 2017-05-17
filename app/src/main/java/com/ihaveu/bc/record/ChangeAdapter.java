package com.ihaveu.bc.record;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihaveu.bc.R;
import com.ihaveu.bc.bean.IntegralChangeBean;
import com.ihaveu.bc.widget.DTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/5/17
 * Time: 下午2:22
 */
public class ChangeAdapter extends RecyclerView.Adapter<ChangeAdapter.NormalTextViewHolder> {
  private final LayoutInflater mLayoutInflater;
  private final Context mContext;

  private List<IntegralChangeBean> changeBeenList;

  public ChangeAdapter(Context context, List<IntegralChangeBean> List) {
    changeBeenList = List;
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_integralchange, parent, false));
  }

  @Override
  public void onBindViewHolder(NormalTextViewHolder holder, int position) {
    if(changeBeenList.get(position).getChangeType().equals("-1")){
      holder.changeItemType.setText("减少");
    } else {
      holder.changeItemType.setText("增加");
    }
    holder.changeItemIntegral.setText(changeBeenList.get(position).getChangeIntegral()+"");
    holder.changeItemRemark.setText(changeBeenList.get(position).getRemarks());
    holder.changeItemTime.setText(changeBeenList.get(position).getCreateDate());
    holder.changeItemMoney.setText("剩余"+changeBeenList.get(position).getChangeAfterIntegral()+"");
  }

  @Override
  public int getItemCount() {
    return changeBeenList==null? 0:changeBeenList.size();
  }

  public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.change_item_type)
    DTextView changeItemType;
    @BindView(R.id.change_item_integral)
    DTextView changeItemIntegral;
    @BindView(R.id.change_item_remark)
    DTextView changeItemRemark;
    @BindView(R.id.change_item_time)
    DTextView changeItemTime;

    @BindView(R.id.change_item_money)
    DTextView changeItemMoney;
    NormalTextViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

}
