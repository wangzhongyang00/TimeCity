package com.example.timetest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;

import com.example.timetest.adapter.NumericWheelAdapter;
import com.example.timetest.util.OnWheelScrollListener;
import com.example.timetest.util.WheelView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ChangetimeActivity extends Activity {


    private Context context;
    private int type;
    private String info = "18";
    private String xingzuo, age_cha;//计算年龄和星座用
    private int age_year, age_month, age_date;
    private long age_time;//时间戳
    private long abc;

    TextView tx_c_age;
    TextView tx_c_xingzuo;

    WheelView hourView;
    WheelView minuteView;
    private String startTime;
    private int curYear, curMonth, curday;
    private int[] timeInt;


    private SimpleDateFormat sf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_per_time);
        context = this;
        tx_c_age = (TextView) findViewById(R.id.tx_c_age);
        tx_c_xingzuo = (TextView) findViewById(R.id.tx_c_xingzuo);
        hourView = (WheelView) findViewById(R.id.hour);
        minuteView = (WheelView) findViewById(R.id.minute);
        setView();
    }


    private void setView() {
        tx_c_age.setText(info);
        tx_c_xingzuo.setText(xingzuo);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        startTime = df.format(date);
        setStartTime();
        initWindow();
    }


    private void setStartTime() {
        // TODO Auto-generated method stub
        timeInt = new int[6];
        timeInt[0] = Integer.valueOf(startTime.substring(0, 4));
        timeInt[1] = Integer.valueOf(startTime.substring(4, 6));
        timeInt[2] = Integer.valueOf(startTime.substring(6, 8));
        timeInt[3] = Integer.valueOf(startTime.substring(8, 10));
        timeInt[4] = Integer.valueOf(startTime.substring(10, 12));
        timeInt[5] = Integer.valueOf(startTime.substring(12, 14));
    }

    private void initWindow() {
        initWheel();
    }

    private void initWheel() {
        // TODO Auto-generated method stub
        Calendar calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;
        curday = calendar.get(Calendar.DAY_OF_MONTH);

//        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(context, curYear - 80, curYear);
//        numericWheelAdapter1.setLabel("年");
//        yearView.setViewAdapter(numericWheelAdapter1);
//        yearView.setCyclic(true);
//        yearView.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(context, 0, 23, "%02d");
        numericWheelAdapter2.setLabel("时");
        hourView.setViewAdapter(numericWheelAdapter2);
        hourView.setCyclic(true);
        hourView.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(context, 0, 59, "%02d");
        numericWheelAdapter3.setLabel("分");
        minuteView.setViewAdapter(numericWheelAdapter3);
        minuteView.setCyclic(true);
        minuteView.addScrollingListener(scrollListener);

//        if (age_year == 0) {
//            yearView.setCurrentItem(timeInt[0] - (curYear - 80));
//            monthView.setCurrentItem(timeInt[1] - 1);
//            dayView.setCurrentItem(timeInt[2] - 1);
//        } else {
//            yearView.setCurrentItem(age_year - (curYear - 80));
//            monthView.setCurrentItem(age_month - 1);
//            dayView.setCurrentItem(age_date - 1);
//        }
//
//        yearView.setVisibleItems(7);//设置显示行数
//        monthView.setVisibleItems(7);
//        dayView.setVisibleItems(7);

    }

    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {

//            int n_year = yearView.getCurrentItem() + curYear;//年
//            int n_month = monthView.getCurrentItem() + 1;//月

            int nowhour = hourView.getCurrentItem();
            int nowminute = minuteView.getCurrentItem();

            Log.e("datas", nowhour + "时 ：" + nowminute);

//            initDay(n_year, n_month);
//
//            int year = yearView.getCurrentItem() + (curYear - 80);
//            int month = Integer.parseInt(((monthView.getCurrentItem() + 1) < 10 ? "0" + (monthView.getCurrentItem() + 1) : (monthView.getCurrentItem() + 1)).toString());
//            int day = Integer.parseInt((((dayView.getCurrentItem() + 1) < 10) ? "0" + (dayView.getCurrentItem() + 1) : (dayView.getCurrentItem() + 1)).toString());
//
//
//            if (curYear < year) {
//                yearView.setCurrentItem(timeInt[0] - (curYear - 80));
//                monthView.setCurrentItem(timeInt[1] - 1);
//                dayView.setCurrentItem(timeInt[2] - 1);
//                year = yearView.getCurrentItem() + (curYear - 80);
//                month = Integer.parseInt(((monthView.getCurrentItem() + 1) < 10 ? "0" + (monthView.getCurrentItem() + 1) : (monthView.getCurrentItem() + 1)).toString());
//                day = Integer.parseInt((((dayView.getCurrentItem() + 1) < 10) ? "0" + (dayView.getCurrentItem() + 1) : (dayView.getCurrentItem() + 1)).toString());
//
//            } else if (curYear == year && curMonth < month) {
//                yearView.setCurrentItem(timeInt[0] - (curYear - 80));
//                monthView.setCurrentItem(timeInt[1] - 1);
//                dayView.setCurrentItem(timeInt[2] - 1);
//                year = yearView.getCurrentItem() + (curYear - 80);
//                month = Integer.parseInt(((monthView.getCurrentItem() + 1) < 10 ? "0" + (monthView.getCurrentItem() + 1) : (monthView.getCurrentItem() + 1)).toString());
//                day = Integer.parseInt((((dayView.getCurrentItem() + 1) < 10) ? "0" + (dayView.getCurrentItem() + 1) : (dayView.getCurrentItem() + 1)).toString());
//
//            } else if (curYear == year && curMonth == month && curday < day) {
//                yearView.setCurrentItem(timeInt[0] - (curYear - 80));
//                monthView.setCurrentItem(timeInt[1] - 1);
//                dayView.setCurrentItem(timeInt[2] - 1);
//                year = yearView.getCurrentItem() + (curYear - 80);
//                month = Integer.parseInt(((monthView.getCurrentItem() + 1) < 10 ? "0" + (monthView.getCurrentItem() + 1) : (monthView.getCurrentItem() + 1)).toString());
//                day = Integer.parseInt((((dayView.getCurrentItem() + 1) < 10) ? "0" + (dayView.getCurrentItem() + 1) : (dayView.getCurrentItem() + 1)).toString());
//
//            }
//
//            String birthday = new StringBuilder()
//                    .append((yearView.getCurrentItem() + (curYear - 80)))
//                    .append("-")
//                    .append((monthView.getCurrentItem() + 1) < 10 ? "0" + (monthView.getCurrentItem() + 1) : (monthView.getCurrentItem() + 1))
//                    .append("-")
//                    .append(((dayView.getCurrentItem() + 1) < 10) ? "0" + (dayView.getCurrentItem() + 1) : (dayView.getCurrentItem() + 1)).toString();
//
//            info = year + "年" + month + "月" + day + "日";
//            Log.e("abc", "year  = " + year + "   month  = " + month + "  day  = " + day);
//            Log.e("abc", "info  = " + info);
//            showage(year, month, day);
        }
    };

    private void initDay(int arg1, int arg2) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, 1, getDay(arg1, arg2), "%02d");
        numericWheelAdapter.setLabel("日");
//        dayView.setViewAdapter(numericWheelAdapter);
    }

    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }


    /**
     * 展示age
     */
    private void showage(int arg1, int arg2, int arg3) {


        Time t_now = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t_now.setToNow(); // 取得系统时间。
        int year_now = t_now.year;
        int month_now = t_now.month + 1;
        int date_now = t_now.monthDay;

        int year = arg1;
        int month = arg2;
        int date = arg3;
        Log.e("abc", "1 = " + year);
        Log.e("abc", "2 = " + month);
        Log.e("abc", "3 = " + date);

        //  xingzuo = Utils.getStarSeat(month, date);
        if (year_now > year) {
            if (month_now > month || (month_now == month && !(date_now < date))) {
                age_cha = Integer.toString(year_now - year);

            } else if (month_now < month || (month_now == month && date_now < date)) {
                age_cha = Integer.toString(year_now - year - 1);
            }
        }

    }

    /**
     * 将字符串转为时间戳
     */
    public long getStringToDate(String time) {
        Log.e("abc", "执行-----");
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.e("abc", "date.getTime() = " + date.getTime());
        return date.getTime();
    }

}
