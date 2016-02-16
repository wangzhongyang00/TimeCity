package com.example.timetest;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.timetest.adapter.ArrayWheelAdapter;
import com.example.timetest.util.OnWheelChangedListener;
import com.example.timetest.util.WheelView;


/**
 * 修改地址
 */
public class UpdateaddsActivity extends BasecityActivity implements OnClickListener, OnWheelChangedListener {

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button btn_top_back;
    private Button btn_top_save;
    private TextView tx_c_adds;

    private Context context;

    private String info;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_adds);
        context = UpdateaddsActivity.this;

        setUpViews();
        setUpListener();
        setUpData();
    }

    private void setUpViews() {
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        btn_top_back = (Button) findViewById(R.id.btn_top_back);
        btn_top_save = (Button) findViewById(R.id.btn_top_save);
        tx_c_adds = (TextView) findViewById(R.id.tx_c_adds);
        tx_c_adds.setText(info);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        btn_top_back.setOnClickListener(this);
        btn_top_save.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(UpdateaddsActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
            tx_c_adds.setText(mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);

        if (flag) {
            tx_c_adds.setText(mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName);
        } else {
            flag = true;
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top_back:
                finish();//返回
                break;
            case R.id.btn_top_save:
                String name = mCurrentProviceName + " " + mCurrentCityName + " " + mCurrentDistrictName;
                break;
            default:
                break;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}