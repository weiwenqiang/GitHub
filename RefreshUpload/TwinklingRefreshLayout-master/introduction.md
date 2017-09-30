---
date: 2016-10-11 10:00
status: public
title: '想要亲手实现一个刷新控件，你只需要掌握这些知识'
---

十一期间，大家都去玩耍了，笔者以前写的一个开源库收到了star，笔者非常高兴，心血来潮之下，决定重新搞一搞，耗费了三天的假期。笔者期望，这个刷新控件能像Google的SwipeRefreshLayout一样，支持大多数列表控件，另外还得有加载更多功能，最好要很方便的支持个性化吧。开源库在这，[TwinklingRefreshLayout](https://github.com/lcodecorex/TwinklingRefreshLayout)，如果喜欢请star，笔者的文章也是围绕着这个控件的实现来说的。

为了方便，笔者将TwinklingRefreshLayout直接继承自FrameLayout而不是ViewGroup，可以省去onMeasure、onLayout等一些麻烦，Header和Footer则是通过LayoutParams来设置View的Gravity属性来做的。

## 1. View的onAttachedToWindow()方法
首先View没有明显的生命周期，我们又不能再构造函数里面addView()给控件添加头部和底部，因此这个操作比较合适的时机就是在onDraw()之前——onAttachedToWindow()方法中。

此时View被添加到了窗体上,View有了一个用于显示的Surface,将开始绘制。因此其保证了在onDraw()之前调用,但可能在调用 onDraw(Canvas) 之前的任何时刻，包括调用 onMeasure(int, int) 之前或之后。
比较适合去执行一些初始化操作。(此外在屏蔽Home键的时候也会回调这个方法)

- onDetachedFromWindow()与onAttachedToWindow()方法相对应。
- ViewGroup先是调用自己的onAttachedToWindow()方法，再调用其每个child的onAttachedToWindow()方法，这样此方法就在整个view树中遍布开了，而visibility并不会对这个方法产生影响。

- onAttachedToWindow方法是在Activity resume的时候被调用的，也就是act对应的window被添加的时候，且每个view只会被调用一次，父view的调用在前，不论view的visibility状态都会被调用，适合做些view特定的初始化操作；
- onDetachedFromWindow方法是在Activity destroy的时候被调用的，也就是act对应的window被删除的时候，且每个view只会被调用一次，父view的调用在后，也不论view的visibility状态都会被调用，适合做最后的清理操作；

就TwinklingRefreshLayout来说,Header和Footer需要及时显示出来,View又没有明显的生命周期,因此在onAttachedToWindow()中进行设置可以保证在onDraw()之前添加了刷新控件。
```java
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //添加头部
        FrameLayout headViewLayout = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.gravity = Gravity.TOP;
        headViewLayout.setLayoutParams(layoutParams);

        mHeadLayout = headViewLayout;
        this.addView(mHeadLayout);//addView(view,-1)添加到-1的位置

        //添加底部
        FrameLayout bottomViewLayout = new FrameLayout(getContext());
        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams2.gravity = Gravity.BOTTOM;
        bottomViewLayout.setLayoutParams(layoutParams2);

        mBottomLayout = bottomViewLayout;
        this.addView(mBottomLayout);
        //...其它步骤
    }
```

但是当TwinklingRefreshLayout应用在Activity或Fragment中时,可能会因为执行onResume重新触发了onAttachedToWindow()方法而导致重复创建Header和Footer挡住原先添加的View,因此需要加上判断:
```java
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("onAttachedToWindow绑定窗口");

        //添加头部
        if (mHeadLayout == null) {
            FrameLayout headViewLayout = new FrameLayout(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            layoutParams.gravity = Gravity.TOP;
            headViewLayout.setLayoutParams(layoutParams);

            mHeadLayout = headViewLayout;

            this.addView(mHeadLayout);//addView(view,-1)添加到-1的位置

            if (mHeadView == null) setHeaderView(new RoundDotView(getContext()));
        }
        //...
    }
```

## 2. View的事件分发机制
事件的分发过程由dispatchTouchEvent、onInterceptTouchEvent和onTouchEvent三个方法来共同完成的。由于事件的传递是自顶向下的，对于ViewGroup，笔者觉得最重要的就是onInterceptTouchEvent方法了，它关系到事件是否能够继续向下传递。看如下伪代码：
```java
public boolean dispatchTouchEvent(MotionEvenet ev){
    boolean consume = false;
    if (onInterceptTouchEvent(ev)) {
        consume = onTouchEvent(ev);
    }else{
        consume = child.dispatchTouchEvent(ev);
    }
    return consume;
}
```

如代码所示，如果ViewGroup拦截了(onInterceptTouchEvent返回true)事件，则事件会在ViewGroup的onTouchEvent方法中消费，而不会传到子View；否则事件将交给子View去分发。

我们需要做的就是在子View滚动到顶部或者底部时及时的拦截事件，让ViewGroup的onTouchEvent来交接处理滑动事件。

## 3. 判断子View滚动达到边界
在什么时候对事件进行拦截呢？对于Header，当手指向下滑动也就是 dy>0 且子View已经滚动到顶部(不能再向上滚动)时拦截；对于bottom则是 dy<0 且子View已经滚动到底部(不能再向下滚动)时拦截：
```java
@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getY() - mTouchY;

                if (dy > 0 && !canChildScrollUp()) {
                    state = PULL_DOWN_REFRESH;
                    return true;
                } else if (dy < 0 && !canChildScrollDown() && enableLoadmore) {
                    state = PULL_UP_LOAD;
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
```

判断View能不能继续向上滚动，对于sdk14以上版本，v4包里提供了方法：
```java
public boolean canChildScrollUp() {
    return ViewCompat.canScrollVertically(mChildView, -1);
}
```

其它情况，直接交给子View了，ViewGroup这里也管不着。

## 4. ViewGroup 的 onTouchEvent 方法
走到这一步，子View的滚动已经交给子View自己去搞了，ViewGroup需要处理的事件只有两个临界状态，也就是用户在下拉可能想要刷新的状态和用户在上拉可能想要加载更多的状态。也就是上面state记录的状态。接下来的事情就简单咯，监听一下ACTION_MOVE和ACTION_UP就好了。

首先在ACTION_DOWN时需要记录下最原先的手指按下的位置 mTouchY，然后在一系列ACTION_MOVE过程中，获取当前位移(ev.getY()-mTouchY)，然后通过 **某种计算方式** 不断计算当前的子View应该位移的距离offsetY，调用`mChildView.setTranslationY(offsetY)`来不断设置子View的位移，同时需要给HeadLayout申请布局高度来完成顶部控件的显示。这其中笔者使用的计算方式就是插值器(Interpolator)。

在ACTION_UP时，需要判断子View的位移有没有达到进入刷新或者是加载更多状态的要求，即`mChildView.getTranslationY() >= mHeadHeight - mTouchSlop`，mTouchSlop是为了防止发生抖动而存在。判断进入了刷新状态时，当前子View的位移在HeadHeight和maxHeadHeight之间，所以需要让子View的位移回到HeadHeight处，否则就直接回到0处。

## 5. Interpolator插值器
Interpolator用于动画中的时间插值，其作用就是把0到1的浮点值变化映射到另一个浮点值变化。上面提到的计算方式如下：
```java
float offsetY = decelerateInterpolator.getInterpolation(dy / mWaveHeight / 2) * dy / 2;
```

其中(dy / mWaveHeight / 2)是一个0~1之间的浮点值，随着下拉高度的增加，这个值越来越大，通过decelerateInterpolator获取到的插值也越来越大，只不过这些值的变化量是越来越小(decelerate效果)。Interpolator继承自TimeInterpolator接口，源码如下：
```java
public interface TimeInterpolator {
    /**
     * Maps a value representing the elapsed fraction of an animation to a value that represents
     * the interpolated fraction. This interpolated value is then multiplied by the change in
     * value of an animation to derive the animated value at the current elapsed animation time.
     *
     * @param input A value between 0 and 1.0 indicating our current point
     *        in the animation where 0 represents the start and 1.0 represents
     *        the end
     * @return The interpolation value. This value can be more than 1.0 for
     *         interpolators which overshoot their targets, or less than 0 for
     *         interpolators that undershoot their targets.
     */
    float getInterpolation(float input);
}
```

getInterpolation接收一个0.0~1.0之间的float参数，0.0代表动画的开始，1.0代表动画的结束。返回值则可以超过1.0，也可以小于0.0，比如OvershotInterpolator。所以getInterpolation()是用来实现输入0~1返回0~1左右的函数值的一个函数。
![](http://www.mincoder.com/images/201502/dr9PkIhoI1mCIasR.jpg)

## 6. 属性动画
上面说到了手指抬起的时候，mChildView的位移要么回到mHeadHeight处，要么回到0处。直接setTranslationY()不免太不友好，所以我们这里使用属性动画来做。本来是直接可以用mChildView.animate()方法来完成属性动画的，因为需要兼容低版本并回调一些参数，所以这里使用ObjectAnimator:
```java
private void animChildView(float endValue, long duration) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(mChildView, "translationY", mChildView.getTranslationY(), endValue);
        oa.setDuration(duration);
        oa.setInterpolator(new DecelerateInterpolator());//设置速率为递减
        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) mChildView.getTranslationY();//获得mChildView当前y的位置
                height = Math.abs(height);

                mHeadLayout.getLayoutParams().height = height;
                mHeadLayout.requestLayout();
            }
        });
    oa.start();
}
```

传统的补间动画只能够实现移动、缩放、旋转和淡入淡出这四种动画操作，而且它只是改变了View的显示效果，改变了画布绘制出来的样子，而不会真正去改变View的属性。比如用补间动画对一个按钮进行了移动，只有在原位置点击按钮才会发生响应，而属性动画则可以真正的移动按钮。属性动画最简单的一种使用方式就是使用ValueAnimator:
```java
ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);  
anim.start();
```

它可以传入多个参数，如`ValueAnimator.ofFloat(0f, 5f, 3f, 10f)`，他会根据设置的插值器依次计算，比如想做一个心跳的效果，用ValueAnimator来控制心的当前缩放值大小就是个不错的选择。除此之外，还可以调用setStartDelay()方法来设置动画延迟播放的时间，调用setRepeatCount()和setRepeatMode()方法来设置动画循环播放的次数以及循环播放的模式等。

如果想要实现View的位移，ValueAnimator显然是比较麻烦的，我们可以使用ValueAnimator的子类ObjectAnimator，如下：
```java
ObjectAnimator animator = ObjectAnimator.ofFloat(textview, "alpha", 1f, 0f, 1f);  
animator.setDuration(5000);  
animator.start();  
```

传入的第一个值是Object，不局限于View，传入的第二个参数为Object的一个属性，比如传入`"abc"`，ObjectAnimator会去Object里面找有没有 **getAbc()** 和 **setAbc(...)** 这两个方法，如果没有，动画就没有效果，它内部应该是处理了相应的异常。另外还可以用AnimatorSet来实现多个属性动画同时播放，也可以在xml中写属性动画。

## 7. 个性化Header和Footer的接口
要实现个性化的Header和Footer，最最重要的当然是把滑动过程中系数都回调出来啦。在ACTION_MOVE的时候，在ACTION_UP的时候，还有在mChildView在执行属性动画的时候，而且mChildView当前所处的状态都是很明确的，写个接口就好了。
```
public interface IHeaderView {
    View getView();

    void onPullingDown(float fraction,float maxHeadHeight,float headHeight);

    void onPullReleasing(float fraction,float maxHeadHeight,float headHeight);

    void startAnim(float maxHeadHeight,float headHeight);

    void onFinish();
}
```

getView()方法保证在TwinklingRefreshLayout中可以取到在外部设置的View，onPullingDown()是下拉过程中ACTION_MOVE时的回调方法，onPullReleasing()是下拉状态中ACTION_UP时的回调方法，startAnim()则是正在刷新时回调的方法。其中 **fraction=mChildView.getTranslationY()/mHeadHeight**，fraction=1 时，mChildView的位移恰好是HeadLayout的高度，fraction>1 时则超过了HeadLayout的高度，其最大高度可以到达 **mWaveHeight/mHeadHeight**。这样我们只需要写一个View来实现这个接口就可以实现个性化了，该有的参数都有了！

## 8. 实现越界回弹
不能在手指快速滚动到顶部时对越界做出反馈，这是一个继承及ViewGroup的刷新控件的通病。没有继承自具体的列表控件，它没办法获取到列表控件的Scroller，不能获取到列表控件的当前滚动速度，更是不能预知列表控件什么时候能滚动到顶部；同时ViewGroup除了达到临界状态的事件被拦截了，其它事件全都交给了子View去处理。我们能获取到的有关于子View的操作，只有简简单单的手指的触摸事件。so, let's do it!
```java
mChildView.setOnTouchListener(new OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
});
```

我们把在mChildView上的触摸事件交给了一个工具类GestureDetector去处理，它可以辅助检测用户的单击、滑动、长按、双击、快速滑动等行为。我们这里只需要重写onFling()方法并获取到手指在Y方向上的速度velocityY，要是再能及时的发现mChildView滚动到了顶部就可以解决问题了。
```java
GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            mVelocityY = velocityY;
        }
    });
```

此外获取速度还可以用VelocityTracker，比较麻烦一些：
```java
VelocityTracker tracker = VelocityTracker.obtain();
tracker.addMovement(ev);
//然后在恰当的位置使用如下方法获取速度
tracker.computeCurrentVelocity(1000);
mVelocityY = (int)tracker.getYVelocity();
```

继续来实现越界回弹。对于RecyclerView、AbsListView，它们提供有OnScrollListener可以获取一下滚动状态：
```java
if (mChildView instanceof RecyclerView) {
            ((RecyclerView) mChildView).addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    if (!isRefreshing && !isLoadingmore && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (mVelocityY >= 5000 && ScrollingUtil.isRecyclerViewToTop((RecyclerView) mChildView)) {
                            animOverScrollTop();
                        }
                        if (mVelocityY <= -5000 && ScrollingUtil.isRecyclerViewToBottom((RecyclerView) mChildView)) {
                            animOverScrollBottom();
                        }
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
        }
```

笔者选取了一个滚动速度的临界值，Y方向的滚动速度大于5000时才允许越界回弹，RecyclerView的OnScrollListener可以让我们获取到滚动状态的改变，滚动到顶部时滚动完成，状态变为SCROLL_STATE_IDLE，执行越界回弹动画。这样的策略也还有一些缺陷，不能获取到mChildView滚动到顶部时的滚动速度，也就不能根据不同的滚动速度来实现更加友好的越界效果。现在的越界高度是固定的，还需要后面进行优化，比如采用加速度来计算，是否可行还待验证。

## 9. 滚动的延时计算策略
上面的方法对于RecyclerView和AbsListView都好用，对于ScrollView、WebView就头疼了，只能使用延时计算一段时间看有没有到达顶部的方式来判断的策略。延时策略的思想就是通过发送一系列的延时消息从而达到一种渐进式计算的效果，具体来说可以使用Handler或View的postDelayed方法，也可以使用线程的sleep方法。另外提一点，需要不断循环计算一个数值，比如自定义View需要实现根据某个数值变化的动效，最好不要使用Thread + while 循环的方式计算，使用ValueAnimator会是更好的选择。这里笔者选择了Handler的方式。
```java
@Override
public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    mVelocityY = velocityY;
    if (!(mChildView instanceof AbsListView || mChildView instanceof RecyclerView)) {
        //既不是AbsListView也不是RecyclerView,由于这些没有实现OnScrollListener接口,无法回调状态,只能采用延时策略
        if (Math.abs(mVelocityY) >= 5000) {
            mHandler.sendEmptyMessage(MSG_START_COMPUTE_SCROLL);
        } else {
            cur_delay_times = ALL_DELAY_TIMES;
        }
    }
    return false;
}
```

在滚动速度大于5000的时候发送一个重新计算的消息，Handler收到消息后，延时一段时间继续给自己发送消息，直到时间用完或者mChildView滚动到顶部或者用户又进行了一次Fling动作。
```java
private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_START_COMPUTE_SCROLL:
                cur_delay_times = -1; //这里没有break,写作-1方便计数
            case MSG_CONTINUE_COMPUTE_SCROLL:
                cur_delay_times++;

                if (!isRefreshing && !isLoadingmore && mVelocityY >= 5000 && childScrollToTop()) {
                    animOverScrollTop();
                    cur_delay_times = ALL_DELAY_TIMES;
                }

                if (!isRefreshing && !isLoadingmore && mVelocityY <= -5000 && childScrollToBottom()) {
                    animOverScrollBottom();
                    cur_delay_times = ALL_DELAY_TIMES;
                }

                if (cur_delay_times < ALL_DELAY_TIMES)
                    mHandler.sendEmptyMessageDelayed(MSG_CONTINUE_COMPUTE_SCROLL, 10);
                break;
            case MSG_STOP_COMPUTE_SCROLL:
                cur_delay_times = ALL_DELAY_TIMES;
                break;
        }
    }
};
```

ALL_DELAY_TIMES是最多可以计算的次数，当Handler接收到MSG_START_COMPUTE_SCROLL消息时，如果mChildView没有滚动到边界处，则会在10ms之后向自己发送一条MSG_CONTINUE_COMPUTE_SCROLL的消息，然后继续进行判断。然后在合适的时候越界回弹就好了。

## 10. 实现个性化Header
这里笔者来演示一下，怎么轻轻松松的做一个个性化的Header，比如新浪微博样式的刷新Header(如下面第1图)。

1. 创建 SinaRefreshView 继承自 FrameLayout 并实现 IHeaderView 接口

2. getView()方法中返回this

3. 在onAttachedToWindow()方法中获取一下需要用到的布局(笔者写到了xml中，也可以直接在代码里面写)

```java
@Override
protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    if (rootView == null) {
        rootView = View.inflate(getContext(), R.layout.view_sinaheader, null);
        refreshArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
        refreshTextView = (TextView) rootView.findViewById(R.id.tv);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_loading);
        addView(rootView);
    }
}
```

4.实现其它方法
```java
@Override
public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
    if (fraction < 1f) refreshTextView.setText(pullDownStr);
    if (fraction > 1f) refreshTextView.setText(releaseRefreshStr);
    refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180);
}

@Override
public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
    if (fraction < 1f) {
        refreshTextView.setText(pullDownStr);
        refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180);
        if (refreshArrow.getVisibility() == GONE) {
            refreshArrow.setVisibility(VISIBLE);
            loadingView.setVisibility(GONE);
        }
    }
}

@Override
public void startAnim(float maxHeadHeight, float headHeight) {
    refreshTextView.setText(refreshingStr);
    refreshArrow.setVisibility(GONE);
    loadingView.setVisibility(VISIBLE);
}
```

5.布局文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">
    <ImageView
        android:id="@+id/iv_arrow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_arrow"/>

    <ImageView
        android:id="@+id/iv_loading"
        android:visibility="gone"
        android:layout_width="34dp"
        android:layout_height="34dp"
        android:src="@drawable/anim_loading_view"/>

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:textSize="16sp"
        android:text="下拉刷新"/>
</LinearLayout>
```

注意fraction的使用,比如上面的代码 **refreshArrow.setRotation(fraction * headHeight / maxHeadHeight * 180)**，`fraction * headHeight`表示当前头部滑动的距离，然后算出它和最大高度的比例，然后乘以180，可以使得在滑动到最大距离时Arrow恰好能旋转180度。startAnim()方法是在onRefresh之后会自动调用的方法。

要想实现如图2所示效果，可以具体查看笔者的开源库[TwinklingRefreshLayout](https://github.com/lcodecorex/TwinklingRefreshLayout)。

![](https://github.com/lcodecorex/TwinklingRefreshLayout/raw/master/art/gif_gridview.gif) ![](https://github.com/lcodecorex/TwinklingRefreshLayout/raw/master/art/gif_recyclerview.gif)

## 总结
至此，笔者实现这个刷新控件的所有核心思想都讲完了，其中并没有用到多么高深的技术，只是需要我们多一点耐心，多去调试，不要逃避bug，多挑战一下自己。
