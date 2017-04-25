package com.ihaveu.bc.deal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ihaveu.bc.R;
import com.ihaveu.bc.view.ChartConstant;
import com.ihaveu.bc.view.ChartView;

/**
 * Created by Arvin on 2016/10/26.
 * 分时frag和k线frag的父布局
 */
public abstract class LineBaseFragment extends Fragment implements ChartConstant, TabLayout.OnTabSelectedListener, ChartView.OnDoubleTapListener, View.OnClickListener {
    protected String cid;
    protected boolean isShow;
    //K线类型：取值为ChartConstant的TYPE_RIK等,日k月k周k等,默认为0分时图
    protected int type;

    protected TabLayout indexTab;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        indexTab = (TabLayout)getView().findViewById(R.id.cfb_index_tab);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isShow = isVisibleToUser;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDoubleTap() {

    }
}
