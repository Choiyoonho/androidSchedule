package com.my.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.my.schedule.uitl.Days;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textDate;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private List<String> dayList;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 5행 7열 GridView 생성*/
        System.out.println(Days.class.getEnumConstants());
        gridView  = (GridView) findViewById(R.id.gridview);
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        /* 현재 날짜를 구한다. */
        final Date nowDate = new Date(System.currentTimeMillis());
        Log.d("nowDate", nowDate+"");
        /* 연, 월, 일 구분 */
        final SimpleDateFormat currentYear = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat currentMonth = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat currentDay = new SimpleDateFormat("dd", Locale.KOREA);

        /* 연, 월 표현*/
        textDate = (TextView) findViewById(R.id.date);
        textDate.setText(currentYear.format(nowDate) + ". " + currentMonth.format(nowDate));

        /* Calendar 생성*/
        myCalendar = Calendar.getInstance();

        /* 이번달 1일이 무슨 요일인지 */
        myCalendar.set(Integer.parseInt(currentYear.format(nowDate)), Integer.parseInt(currentMonth.format(nowDate)) - 1, 1);   //이거 분석
        int dayNum = myCalendar.get(Calendar.DAY_OF_WEEK);
        Log.i("1Day == ", dayNum+"");

        /* 이번달 1일을 알맞은 요일로 매칭 시키기 위해서 공백 추가 */
        for(int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(myCalendar.get(Calendar.MONTH) + 1);
        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
    }

    /* 이거 분석 */
    private void setCalendarDate(int month) {
        myCalendar.set(Calendar.MONTH, month - 1);
        for (int i = 0; i < myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }
    }
}
