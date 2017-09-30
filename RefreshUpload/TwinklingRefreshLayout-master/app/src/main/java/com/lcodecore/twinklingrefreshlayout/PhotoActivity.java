package com.lcodecore.twinklingrefreshlayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;

import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.twinklingrefreshlayout.adapter.PhotoAdapter;
import com.lcodecore.twinklingrefreshlayout.beans.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

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
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        photoAdapter = new PhotoAdapter();
        rv.setAdapter(photoAdapter);

        final TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refresh);
//        ProgressLayout headerView = new ProgressLayout(this);
        BezierLayout headerView = new BezierLayout(this);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setWaveHeight(140);
//        refreshLayout.setFloatRefresh(true);
//        refreshLayout.setPureScrollModeOn(true);
        refreshLayout.setOverScrollBottomShow(false);
//        refreshLayout.setAutoLoadMore(true);

//        addHeader();
        refreshCard();


        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCard();
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreCard();
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });

//        refreshLayout.startRefresh();
    }

    void refreshCard() {
        List<Photo> photos = new ArrayList<>();
//        foods.add(new Food("Preparing Salmon Steak Close Up","BY VIKTOR HANACEK",R.drawable.food1,R.drawable.avatar0));
//        foods.add(new Food("Fresh & Healthy Fitness Broccoli Pie with Basil","BY VIKTOR HANACEK",R.drawable.food2,R.drawable.avatar1));
//        foods.add(new Food("Enjoying a Tasty Burger","BY VIKTOR HANACEK",R.drawable.food3,R.drawable.avatar2));
//        foods.add(new Food("Fresh Strawberries and Blackberries in Little Bowl","BY VIKTOR HANACEK",R.drawable.food4,R.drawable.avatar3));
//        foods.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil","BY VIKTOR HANACEK",R.drawable.food5,R.drawable.avatar4));
        photos.add(new Photo("chest nut", R.drawable.photo1));
        photos.add(new Photo("fish", R.drawable.photo2));
        photos.add(new Photo("cat", R.drawable.photo10));
        photos.add(new Photo("guitar", R.drawable.photo3));
        photos.add(new Photo("common-hazel", R.drawable.photo4));
        photos.add(new Photo("cherry", R.drawable.photo5));
        photos.add(new Photo("flower details", R.drawable.photo6));
        photos.add(new Photo("tree", R.drawable.photo7));
        photos.add(new Photo("blue berries", R.drawable.photo8));
        photos.add(new Photo("snow man", R.drawable.photo9));
        photoAdapter.setDataList(photos);
    }

    void loadMoreCard() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("chest nut", R.drawable.photo1));
        photos.add(new Photo("fish", R.drawable.photo2));
        photos.add(new Photo("cat", R.drawable.photo10));
        photos.add(new Photo("guitar", R.drawable.photo3));
        photos.add(new Photo("common-hazel", R.drawable.photo4));
        photos.add(new Photo("cherry", R.drawable.photo5));
        photos.add(new Photo("flower details", R.drawable.photo6));
        photos.add(new Photo("tree", R.drawable.photo7));
        photos.add(new Photo("blue berries", R.drawable.photo8));
        photos.add(new Photo("snow man", R.drawable.photo9));
        //chest nut   cat and fish  guitar   common-hazel  cherry   flower details   tree
        //blue berries   snow man
//        foods.add(new Food(R.drawable.food3));
//        foods.add(new Food(R.drawable.food2));
//        foods.add(new Food(R.drawable.food1));
        photoAdapter.addItems(photos);
    }
}
