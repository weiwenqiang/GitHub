# SwipeRefreshLayout详解和自定义加载更多
[个人主页](http://www.jianshu.com/users/64f479a1cef7/latest_articles)
[演示Demo下载](https://github.com/PingerWan/SwipeRefreshDemo)
 
---

> 本文重点介绍了SwipeRefreshLayout的使用和自定View继承SwipeRefreshLayout添加上拉加载更多的功能。


* 介绍之前，先来看一下SwipeRefreshLayout实现的下拉刷新效果图。从图中可以看到，下拉到了一定的高度才会进行刷新，高度不够就会回收上去，正在刷新过程中，继续下拉没反应，说明刷新时屏蔽掉了下拉事件。
![上拉刷新效果图](http://upload-images.jianshu.io/upload_images/2786991-c76615c24918e04d.gif?imageMogr2/auto-orient/strip)

###  一、SwipeRefreshLayout简单介绍

* 先看以下官方文档，已有了很详细的描述了。
![官方文档说明](http://upload-images.jianshu.io/upload_images/2786991-a718fcc3c94bd13e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 这里我再大概解释一下：
	* 在竖直滑动时想要刷新页面可以用SwipeRefreshLayout来实现。它通过设置OnRefreshListener来监听界面的滑动从而实现刷新。也可以通过一些方法来设置SwipeRefreshLayout是否可以刷新。如：setRefreshing(true)，展开刷新动画。
setRefreshing(false)，取消刷新动画。setEnable(true)下拉刷新将不可用。

	* 使用这个布局要想达到刷新的目的，需要在这个布局里包裹可以滑动的子控件，如ListView等，并且只能有一个子控件。

* 介绍总结：使用SwipeRefreshLayout可以实现下拉刷新，前提是布局里需要包裹一个可以滑动的子控件，然后在代码里设置OnRefreshListener设置监听，最后在监听里设置刷新时的数据获取就可以了。由于是新出来的东西，所以要想使用，先把support library的版本升级到19.1或更新。


###  二、SwipeRefreshLayout主要方法介绍
> 翻看官方的文档，可以看到方法有很多，这里只介绍五个经常用到的方法。

* isRefreshing()
	* 判断当前的状态是否是刷新状态。

* setColorSchemeResources(int... colorResIds)
	* 设置下拉进度条的颜色主题，参数为可变参数，并且是资源id，最多可以设置四种不同的颜色，每转一圈就显示一种颜色。

* setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener)
	* 设置监听，需要重写onRefresh()方法，顶部下拉时会调用这个方法，在里面实现请求数据的逻辑，设置下拉进度条消失等等。

* setProgressBackgroundColorSchemeResource(int colorRes)
	* 设置下拉进度条的背景颜色，默认白色。

* setRefreshing(boolean refreshing)
	* 设置刷新状态，true表示正在刷新，false表示取消刷新。

###  三、SwipeRefreshLayout的基本使用

* 介绍了SwipeRefreshLayout，主要的方法也讲了，接下来就是实战，其实使用起来非常的简单。

#### 3.1 设置布局
* 官方文档已经说明，SwipeRefreshLayout只能有一个孩子，当然我们不般也不会往里面放其他的布局。我们只需要在容器里包裹一个ListView就好了。



   	 	<!--使用谷歌官方的下拉刷新组件,只有下拉刷新功能-->
		<!--
		    <android.support.v4.widget.SwipeRefreshLayout
		        android:id="@+id/srl"
		        android:layout_width="match_parent"
		        android:layout_height="match_parent">
		
		        <ListView
		            android:id="@+id/lv"
		            android:layout_width="match_parent"
		            android:layout_height="match_parent"/>
		
		    </android.support.v4.widget.SwipeRefreshLayout>
		-->

#### 3.2 在代码中使用
* 在该布局文件对应的Activity或其他类中获取布局id，先设置ListView显示的适配器，然后再设置SwipeRefreshLayout。



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

* 经过以上两步简单的设置就能使用SwipeRefreshLayout了。

### 四、自定义View继承SwipeRefreshLayout，添加上拉加载更多功能

> 由于谷歌并没有提供上拉加载更多的布局，所以我们只能自己去定义布局实现这个功能。

> 这里通过自定义View继承SwipeRefreshLayout容器，然后添加上拉加载更多的功能。

* 先来看一下上拉加载更多的效果图
![上拉加载更多效果图](http://upload-images.jianshu.io/upload_images/2786991-9e4901396efc72b1.gif?imageMogr2/auto-orient/strip)

#### 4.1 定义View继承SwipeRefreshLayout，添加上拉加载功能

> 代码中的注释比较详细，这里就不一一解释了，说一下大概的实现思路，主要分为四步。

#### 4.1.1 获取子控件ListView
* 在布局使用中，这里和SwipeRefreshLayout一样，ListView是SwipeRefreshView的子控件，所以需要在onLayout()方法中获取子控件ListView。



		// 获取ListView,设置ListView的布局位置
        if (mListView == null) {
            // 判断容器有多少个孩子
            if (getChildCount() > 0) {
                // 判断第一个孩子是不是ListView
                if (getChildAt(0) instanceof ListView) {
                    // 创建ListView对象
                    mListView = (ListView) getChildAt(0);

                    // 设置ListView的滑动监听
                    setListViewOnScroll();
                }
            }
        }

#### 4.1.2 对ListView设置滑动监听
* 监听ListView的滑动事件，当滑动到底部，并且当前可见页的最后一个条目等于adapter的getCount数目-1，就满足加载数据的条件。


	
	
		   /**
		     * 设置ListView的滑动监听
		     */
		    private void setListViewOnScroll() {
		
		        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
		            @Override
		            public void onScrollStateChanged(AbsListView view, int scrollState) {
		                // 移动过程中判断时候能下拉加载更多
		                if (canLoadMore()) {
		                    // 加载数据
		                    loadData();
		                }
		            }
		
		            @Override
		            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		
		            }
		        });
		    }



#### 4.1.3 处理SwipeRefreshView容器的分发事件
* 由于ListView是SwipeRefreshView的子控件，所以这里要进行事件的分发处理，判断用户的滑动距离是否满足条件。



		 /**
	     * 在分发事件的时候处理子控件的触摸事件
	     *
	     * @param ev
	     * @return
	     */
	    private float mDownY, mUpY;
	    @Override
	    public boolean dispatchTouchEvent(MotionEvent ev) {
	
	        switch (ev.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                // 移动的起点
	                mDownY = ev.getY();
	                break;
	            case MotionEvent.ACTION_MOVE:
	                // 移动过程中判断时候能下拉加载更多
	                if (canLoadMore()) {
	                    // 加载数据
	                    loadData();
	                }
	
	                break;
	            case MotionEvent.ACTION_UP:
	                // 移动的终点
	                mUpY = getY();
	                break;
	        }
	        return super.dispatchTouchEvent(ev);
	    }



#### 4.1.4 判断条件，满足就用回调去加载数据
* 当满足了需要判断的所有的条件之后，就可以去调用加载数据的方法，这里提供一个设置上拉布局显示和隐藏的方法，通过传入当前的状态，是true就显示加载，是false就隐藏。	 


	    /**
	     * 判断是否满足加载更多条件
	     *
	     * @return
	     */
	    private boolean canLoadMore() {
	        // 1. 是上拉状态
	        boolean condition1 = (mDownY - mUpY) >= mScaledTouchSlop;
	        if (condition1) {
	            System.out.println("是上拉状态");
	        }
	
	        // 2. 当前页面可见的item是最后一个条目
	        boolean condition2 = false;
	        if (mListView != null && mListView.getAdapter() != null) {
	            condition2 = mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
	        }
	
	        if (condition2) {
	            System.out.println("是最后一个条目");
	        }
	        // 3. 正在加载状态
	        boolean condition3 = !isLoading;
	        if (condition3) {
	            System.out.println("不是正在加载状态");
	        }
	        return condition1 && condition2 && condition3;
	    }
	
	    /**
	     * 处理加载数据的逻辑
	     */
	    private void loadData() {
	        System.out.println("加载数据...");
	        if (mOnLoadListener != null) {
	            // 设置加载状态，让布局显示出来
	            setLoading(true);
	            mOnLoadListener.onLoad();
	        }
	
	    }
	
	    /**
	     * 设置加载状态，是否加载传入boolean值进行判断
	     *
	     * @param loading
	     */
	    public void setLoading(boolean loading) {
	        // 修改当前的状态
	        isLoading = loading;
	        if (isLoading) {
	            // 显示布局
	            mListView.addFooterView(mFooterView);
	        } else {
	            // 隐藏布局
	            mListView.removeFooterView(mFooterView);
	
	            // 重置滑动的坐标
	            mDownY = 0;
	            mUpY = 0;
	        }
	    }
	

### 4.2 使用自定义View
#### 4.2.1. 书写布局
* 因为是继承自SwipeRefreshLayout，所以SwipeRefreshView也只能有一个孩子


	    <!--自定义View实现SwipeRefreshLayout，添加上拉加载更多的功能-->
	    <com.pinger.swiperefreshdemo.view.SwipeRefreshView
	        android:id="@+id/srl"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent">
	
	        <ListView
	            android:id="@+id/lv"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	
	    </com.pinger.swiperefreshdemo.view.SwipeRefreshView>


#### 4.2.2. 在代码中使用

* 在代码中使用更加的简单，只需要设置监听重写onLoad()方法，在里面加载数据，加载完数据然后设置为不加载状态就可以了。



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



---
[个人主页](http://www.jianshu.com/users/64f479a1cef7/latest_articles)
[演示Demo下载](https://github.com/PingerWan/SwipeRefreshDemo)
####  以上纯属于个人平时工作和学习的一些总结分享，如果有什么错误欢迎随时指出，大家可以讨论一起进步。