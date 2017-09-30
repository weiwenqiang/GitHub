package com.study.android.kehan.calendartest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kehan on 16-7-26.
 */
public class MainActivity extends AppCompatActivity {

    private Calendar c = Calendar.getInstance();
    private Calendar d = Calendar.getInstance();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Log.i("MainActivityFilter", "-------------------------------");
        //  用于获得当前时间是上午还是下午，上午为0，下午为1
        //  建议用常量来判断，不用去记Calendar.AM = 0， Calendar.PM = 1
//        if (c.get(Calendar.AM_PM) == Calendar.AM) {
//            Log.i("MainActivityFilter", "现在是上午");
//        } else {
//            Log.i("MainActivityFilter", "现在是下午");
//        }
        //  获取今天是当月份的几号，以下两句同义
//        Log.i("MainActivityFilter", "DATE:" + c.get(Calendar.DATE));
//        Log.i("MainActivityFilter", "DAY_OF_MONTH:" + c.get(Calendar.DAY_OF_MONTH));

//        printDayOfWeek();
        //  这个常量比较神奇，应该是本月的第几个周几，例如今天是周二，并且今天是本月的第四个周二，那么返回值就是4
        //  但是有一点比较奇怪的是如果这一周不完整，比如说第一周或者最后一周不完整，它好像有一些特别的处理机制，所以要做更多的测试，先放着
        //  这一天在这个月中的第几周
//        Log.i("MainActivityFilter", "今天在本月的第" + c.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "周");

        //  获得今天是全年的第几天
//        Log.i("MainActivityFilter", "今天是今年的第" + c.get(Calendar.DAY_OF_YEAR) + "天");

        //  这个不是很能理解，文档也看不懂，多方查找，这个是以毫秒为单位指示夏令时的偏移量，暂时用不上
//        Log.i("MainActivityFilter", "DST_OFFSET:" + c.get(Calendar.DST_OFFSET));

        //  以毫秒来表示的时区，先转换成用小时来表示，正数代表的是东几区，负数代表的是西几区
//        Log.i("MainActivityFilter", "ZONE_OFFSET:" + (c.get(Calendar.ZONE_OFFSET) / 1000 / 60 / 60));

        //  获得当前年份是公元前还是公元后
        //  c.get(Calendar.ERA) == Calendar.BC 为true的话就是公元前
        //  c.get(Calendar.ERA) == Calendar.AD 为true的话就是公元后
//        Log.i("MainActivityFilter", "ERA:" + c.get(Calendar.ERA));

        //  Calendar这个类有多少个成员变量
//        Log.i("MainActivityFilter", "FIELD_COUNT:" + Calendar.FIELD_COUNT);

        //  Calendar.LONG，Calendar.SHORT用于在getDisplayName和getDisplayNames方法中指定返回的名称是全称还是缩写
        //  getDisplayName的用法
        //  三个参数分别是field（目前觉得只有Calendar.MONTH有效，其它都会得到空字符串，而且这个只能得到当月份的），style（Long 或者 Short），locale（指定地区）
        //  这个方法在locale为空的时候会抛出NullPointerException，在field或者style无效的时候会抛出IllegalArgumentException
        //  其实整个方法感觉用处不大
//        String temp = c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
//        if (temp != null) {
//            Log.i("MainActivityFilter", temp);
//        } else {
//            Log.i("MainActivityFilter", "获得的字符串为空");
//        }
        //  用法相同返回值不同的还有一个方法是getDisplayNames


        //  获得当前的年份
//        Log.i("MainActivityFilter", "当前的年份为：" + c.get(Calendar.YEAR));

        //  获得当前的月份
//        Log.i("MainActivityFilter", "当前的月份为：" + (c.get(Calendar.MONTH) + 1));

        //  获得现在是几点，12小时制
//        Log.i("MainActivityFilter", "现在是" + c.get(Calendar.HOUR) + "点");

        //  获得现在是几点，24小时制
//        Log.i("MainActivityFilter", "现在是" + c.get(Calendar.HOUR_OF_DAY) + "点");

        //  获得现在是几分
//        Log.i("MainActivityFilter", "现在是" + c.get(Calendar.MINUTE) + "分");

        //  获得现在是几秒
//        Log.i("MainActivityFilter", "现在是" + c.get(Calendar.SECOND) + "秒");

        //  获得现在是几毫秒
//        Log.i("MainActivityFilter", "现在是" + c.get(Calendar.MILLISECOND) + "毫秒");

//        Log.i("MainActivityFilter", c.toString());

//        DateFormat dateBeforeFormat = DateFormat.getDateInstance();
//        DateFormat dateFormat = DateFormat.getDateTimeInstance();
//        DateFormat dateAfterFormat = DateFormat.getTimeInstance();
//        Log.i("MainActivityFilter", "日期的前半段：" + dateBeforeFormat.format(c.getTime()));
//        Log.i("MainActivityFilter", "日期：" + dateFormat.format(c.getTime()));
//        Log.i("MainActivityFilter", "日期的后半段：" + dateAfterFormat.format(c.getTime()));

//        d.add(Calendar.DAY_OF_MONTH, 1);
//        Log.i("MainActivityFilter", "c在d前面：" + c.before(d));


        d = (Calendar) c.clone();

        Log.i("MainActivityFilter", "c：" + sdf.format(c.getTime()));

        Log.i("MainActivityFilter", "本周第一天：" + c.getFirstDayOfWeek());

//        c.add(Calendar.YEAR, 1);
//
//        Log.i("MainActivityFilter", "比较的结果：" + c.compareTo(d));
//
//        d.add(Calendar.YEAR, 2);
//
//        Log.i("MainActivityFilter", "比较的结果：" + c.compareTo(d));

    }

    /**
     * 获得今天是周几
     */
    private void printDayOfWeek() {
        //  获取今天是周几，和常量SUNDAY，MONDAY，TUESDAY，WEDNESDAY，THURSDAY，FRIDAY，SATURDAY搭配使用
        //  不要用获得的返回值来直接判断周几，例如，返回值为3不代表今天是周三，返回值为3其实是周二
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                Log.i("MainActivityFilter", "今天是周日");
                break;
            case Calendar.MONDAY:
                Log.i("MainActivityFilter", "今天是周一");
                break;
            case Calendar.TUESDAY:
                Log.i("MainActivityFilter", "今天是周二");
                break;
            case Calendar.WEDNESDAY:
                Log.i("MainActivityFilter", "今天是周三");
                break;
            case Calendar.THURSDAY:
                Log.i("MainActivityFilter", "今天是周四");
                break;
            case Calendar.FRIDAY:
                Log.i("MainActivityFilter", "今天是周五");
                break;
            case Calendar.SATURDAY:
                Log.i("MainActivityFilter", "今天是周六");
                break;
            default:
                break;
        }
    }
}
