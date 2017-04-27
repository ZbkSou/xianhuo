package com.ihaveu.bc.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihaveu.bc.R;
import com.ihaveu.bc.widget.DTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: bkzhou
 * Date: 2017/4/27
 * Time: 下午3:48
 */
public class GoodsRecyclerViewAdapter extends RecyclerView.Adapter<GoodsRecyclerViewAdapter.GoodsTextViewHolder> {
  private  LayoutInflater mLayoutInflater;
  private  Context mContext;
  private String[] mTitles;
  public GoodsRecyclerViewAdapter(Context context) {
    mTitles = context.getResources().getStringArray(R.array.goods);
    mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
  }
  @Override
  public GoodsRecyclerViewAdapter.GoodsTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new GoodsTextViewHolder(mLayoutInflater.inflate(R.layout.item_goods, parent, false));
  }

  @Override
  public void onBindViewHolder(GoodsRecyclerViewAdapter.GoodsTextViewHolder holder, int position) {
    holder.goodsName.setText(mTitles[position]);
  }

  @Override
  public int getItemCount() {
    return mTitles == null ? 0 : mTitles.length;
  }
  public static class GoodsTextViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.goods_name)
    DTextView goodsName;

    GoodsTextViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
        }
      });
    }
  }
}
