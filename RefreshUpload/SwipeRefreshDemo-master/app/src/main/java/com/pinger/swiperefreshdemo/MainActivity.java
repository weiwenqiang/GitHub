package com.pinger.swiperefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinger.swiperefreshdemo.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 使用谷歌提供的SwipeRefreshLayout下拉控件进行下拉刷新
 */
public class MainActivity extends AppCompatActivity {
    private List<String> mList;
    private int mCount;
    private StringAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeRefreshView swipeRefreshView = (SwipeRefreshView) findViewById(R.id.srl);
        ListView listView = (ListView) findViewById(R.id.lv);

        // 设置适配器数据
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("我是天才" + i + "号");
            mCount++;
        }
        mAdapter = new StringAdapter();
        listView.setAdapter(mAdapter);


        // 不能在onCreate中设置，这个表示当前是刷新状态，如果一进来就是刷新状态，SwipeRefreshLayout会屏蔽掉下拉事件
        //swipeRefreshLayout.setRefreshing(true);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshView.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshView.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        swipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                final Random random = new Random();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.add(0, "我是天才" + random.nextInt(100) + "号");
                        mAdapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "刷新了一条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        swipeRefreshView.setRefreshing(false);
                    }
                }, 1200);

                // System.out.println(Thread.currentThread().getName());

                // 这个不能写在外边，不然会直接收起来
                //swipeRefreshLayout.setRefreshing(false);
            }
        });


        // 设置下拉加载更多
        swipeRefreshView.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // 添加数据
                        for (int i = 30; i < 35; i++) {
                            mList.add("我是天才" + i+ "号");
                            // 这里要放在里面刷新，放在外面会导致刷新的进度条卡住
                            mAdapter.notifyDataSetChanged();
                        }

                        Toast.makeText(MainActivity.this, "加载了" + 5 + "条数据", Toast.LENGTH_SHORT).show();

                        // 加载完数据设置为不加载状态，将加载进度收起来
                        swipeRefreshView.setLoading(false);
                    }
                }, 1200);
            }
        });


    }


    /**
     * 适配器
     */
    private class StringAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }

            TextView tv = (TextView) convertView;
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(0, 20, 0, 20);
            tv.setText(mList.get(position));

            return convertView;
        }
    }
}
