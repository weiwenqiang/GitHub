package com.lcodecore.twinklingrefreshlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.twinklingrefreshlayout.adapter.FoodAdapter;
import com.lcodecore.twinklingrefreshlayout.beans.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        setupRecyclerView((RecyclerView) findViewById(R.id.recyclerview));

        findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setupRecyclerView(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        foodAdapter = new FoodAdapter();
        rv.setAdapter(foodAdapter);

        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
//        ProgressLayout headerView = new ProgressLayout(getContext());
        BezierLayout headerView = new BezierLayout(this);
        refreshLayout.setHeaderView(headerView);
//        refreshLayout.setFloatRefresh(false);
        refreshLayout.setPureScrollModeOn(true);
//        refreshLayout.setEnableOverlayRefreshView(false);
//        refreshLayout.setAutoLoadMore(true);

        refreshCard();

    }

    void refreshCard() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Preparing Salmon Steak Close Up", "BY VIKTOR HANACEK", R.drawable.food1, R.drawable.avatar0));
        foods.add(new Food("Fresh & Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food2, R.drawable.avatar1));
        foods.add(new Food("Enjoying a Tasty Burger", "BY VIKTOR HANACEK", R.drawable.food3, R.drawable.avatar2));
        foods.add(new Food("Fresh Strawberries and Blackberries in Little Bowl", "BY VIKTOR HANACEK", R.drawable.food4, R.drawable.avatar3));
        foods.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food5, R.drawable.avatar4));
        foodAdapter.setDataList(foods);
    }

    void loadMoreCard() {
        List<Food> foods = new ArrayList<>();
//        foods.add(new Food(R.drawable.food3));
//        foods.add(new Food(R.drawable.food2));
//        foods.add(new Food(R.drawable.food1));
        foodAdapter.addItems(foods);
    }
}
