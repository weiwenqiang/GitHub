# 文档过时! 最新的请查看[Wiki](https://github.com/Nightonke/WoWoViewPager/wiki)

[![WoWoViewPager](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/WoWoViewPager)
[![BoomMenu](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BoomMenu)
[![CoCoin](https://github.com/Nightonke/CoCoin/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/CoCoin)
[![BlurLockView](https://github.com/Nightonke/BlurLockView/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BlurLockView)
[![LeeCo](https://github.com/Nightonke/LeeCo/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/LeeCo)
[![GithubWidget](https://github.com/Nightonke/GithubWidget/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/GithubWidget)
[![JellyToggleButton](https://github.com/Nightonke/JellyToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/JellyToggleButton)
[![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/FaceOffToggleButton)

![WoWoViewPager App Intros Example](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/AppIntroExample.gif) 
![WoWoViewPager CV Example](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/CVExample.gif)

WoWo可以优化你的App介绍/引导页面，制作你的App简历。  
WoWo将动画和viewpager结合起来。  
当你滑动viewpager的时候，你也在控制动画的当前帧。  
就好像控制动画时间一般。  
比如上面的两个Gif，是先向前滑动然后向后滑动。

# 目录

[English README](https://github.com/Nightonke/WoWoViewPager)  
[Gradle](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#gradle)  
[备注](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#备注)  
[Demo](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#demo)  
[版本](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#版本)  
[Todo](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#todo)  
[License](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#license)  

### 动画用法
#### 基本动画  
1. [位移动画](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#位移动画)
2. [缩放动画](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#缩放动画)
3. [渐现、渐逝动画](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#渐现、渐逝动画)
4. [旋转动画](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#旋转动画)

#### 文字大小动画
1. [TextView Size Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#textview-size-animation)

#### 变色动画
1. [Background Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#background-color-animation)
2. [TextView Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#textView-color-animation)
3. [Shape Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#shape-color-animation)
4. [State-List Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#state-list-color-animation)
5. [Layer-List Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#layer-list-color-animation)

#### 路径动画
1. [路径动画](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#路径动画)

#### 动画效果
1. [缓动函数](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#缓动函数)
2. [RGB or HSV](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)

# Gradle
直接在你的module的build.gradle的dependencies中加入“compile 'com.nightonke:wowoviewpager:1.0.2'”这行即可。  
```
dependencies {
    ...
    compile 'com.nightonke:wowoviewpager:1.0.2'
    ...
}
```

# 备注
1. 感谢 [SCViewPager](https://github.com/sacot41/SCViewPager) 带给我代码上的灵感。
2. 感谢 [JazzHands](https://github.com/IFTTT/JazzHands) 带给我动画上的灵感。  
3. 感谢 [konmik](https://github.com/konmik) 提供了一个更好的HSV动画变换。
4. 更多的动画将会陆续加入。  

# Demo
你可以通过这个demo来查看所有WoWo支持的动画，并以不同的缓动效果和变色效果来查看动画效果。  
你也可以在demo中找到App引导页例子和App简历例子。  
![WoWo V1.0.2](https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20V1.0.2.png)  
或者下载链接：  
[WoWo V1.0.2 in Github](https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20V1.0.2.apk?raw=true)  
[WoWo V1.0.2 in Fir](http://fir.im/wowoviewpager)  
![Demo Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/demo_animation.png) 
![Demo Ease Type](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/demo_ease_type.png)  

# 动画用法  

### 基本动画  

#### 位移动画 
位移动画可以让view动起来。  
![Translation animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/TranslationAnimation.gif)  
```java
/**
 *
 * @param page                The translation animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The translation animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The translation animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromX               The starting horizontal position of this view 
                              relative to its left position, in pixels
                              
 * @param fromY               The starting vertical position of this view 
                              relative to its top position, in pixels
                              
 * @param targetX             The ending horizontal position of this view 
                              relative to its left position, in pixels
                              
 * @param targetY             The ending vertical position of this view 
                              relative to its top position, in pixels
                              
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoTranslationAnimation(
        0, 
        0f, 
        1f,
        findViewById(R.id.test).getTranslationX(),
        findViewById(R.id.test).getTranslationY(),
        -screenW / 2 + WoWoUtil.dp2px(40, this),
        -screenH / 2 + WoWoUtil.dp2px(40, this),
        easeType,
        useSameEaseTypeBack));
animation.addPageAnimaition(new WoWoTranslationAnimation(
        1, 
        0f, 
        1f,
        -screenW / 2 + WoWoUtil.dp2px(40, this),
        -screenH / 2 + WoWoUtil.dp2px(40, this),
        screenW - WoWoUtil.dp2px(80, this),
        screenH - WoWoUtil.dp2px(80, this),
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
如你所见，上述代码实现了gif中一部分的效果，也就是从viewpager的第一页滑动到第三页的效果，共涉及两个位移动画。   
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTranslationAnimationActivity.java)。    

#### 缩放动画
缩放动画实现view的缩小或放大。  
![Scale animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/ScaleAnimation.gif)  
```java
/**
 *
 * @param page                The scale animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The scale animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The scale animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param targetScaleX        Target scale x = target x / original y
 
 * @param targetScaleY        Target scale y = target y / original y
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoScaleAnimation(
        3, 0f, 0.5f,
        2f,
        1f,
        easeType,
        useSameEaseTypeBack));
animation.addPageAnimaition(new WoWoScaleAnimation(
        3, 0.5f, 1f,
        1f,
        2f,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
你可以将多种动画结合起来来实现复制的动画。比如，你可以用两个位移动画来创造一个折线动画。
正如上述代码，从viewpager第3页（注意这里从0数起）的0偏移量到0.5偏移量（也就是滑动的前一半路程），view的宽度翻倍。
然后，在0.5偏移量到1偏移量，也就是滑动的后一半路程，view的高度翻倍。  
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoScaleAnimationActivity.java)。  

#### 渐现、渐逝动画
渐现、渐逝动画改变view的可见度，用来产生渐现、渐逝效果。  
![Alpha animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/AlphaAnimation.gif)  
```java
/**
 *
 * @param page                The alpha animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The alpha animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The alpha animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromAlpha           Original alpha
     
 * @param targetAlpha         Target alpha
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoAlphaAnimation(
        3, 0f, 0.5f,
        1,
        0.3f,
        easeType,
        useSameEaseTypeBack));
animation.addPageAnimaition(new WoWoAlphaAnimation(
        3, 0.5f, 1f,
        0.3f,
        1f,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
相信不用做太多解释。  
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoAlphaAnimationActivity.java)。  

#### 旋转动画
旋转动画，顾名思义。
![Rotation animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/RotationAnimation.gif)  
```java
/**
 *
 * @param page                The alpha animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The alpha animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The alpha animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
 *        _ _ _ _ _ _ _
 *      /|    x
 *     / |
 *    /  |y
 *   /   |
 *  /z   |
 * /     |
 *
 * @param pivotX              The x value of the pivot of this rotation animation
 
 * @param pivotY              The y value of the pivot of this rotation animation
 
 * @param targetX             The target degree on x axis
 
 * @param targetY             The target degree on y axis
 
 * @param targetZ             The target degree on z axis
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoRotationAnimation(
        0, 0f, 1f,
        pivotX, pivotY,
        0,
        0,
        180,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
上述的代码将导致view上下颠倒，注意在gif中，两个textview的轴心是不一样的。  
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoRotationAnimationActivity.java)。  

### 文字大小动画
#### TextView Size Animation  
TextView文字大小动画帮助改变TextView内的文字大小。   
```java
/**
 *
 * @param page                The textview size animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The textview size animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The textview size animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromSize            Original text size in sp
 
 * @param targetSize          Target text size in sp
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoTextViewSizeAnimation(
        0, 0f, 1f,
        50,
        20,
        easeType,
        useSameEaseTypeBack));
animation.addPageAnimaition(new WoWoTextViewSizeAnimation(
        1, 0f, 1f,
        20,
        60,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTextViewTextSizeAnimationActivity.java)。  

### 变色动画

#### Background Color Animation
背景变色动画令view的背景色改变。  
![Background Color Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/BackgroundColorAnimation.gif)  
```java
/**
 *
 * @param page                The background color animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The background color animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The background color animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromColor           Original color
 
 * @param targetColor         Target color
 
 * @param colorChangeType     How to change the color. For more information, 
                              please check the ColorChangeType.class
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoBackgroundColorAnimation(
        0, 0f, 1f,
        Color.parseColor("#ff0000"),
        Color.parseColor("#00ff00"),
        colorChangeType,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
注意背景变色动画只能改变有setBackgroundColor()函数的view。   
更多示例，请查看[代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoBackgroundColorAnimationActivity.java)。  
[什么是colorChangeType？](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)  

#### TextView Color Animation
TextView变色动画帮助改变字体颜色。  
```java
/**
 *
 * @param page                The textview color animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The textview color animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The textview color animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromColor           Original color
 
 * @param targetColor         Target color
 
 * @param colorChangeType     How to change the color. For more information, 
                              please check the ColorChangeType.class
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoTextViewColorAnimation(
        0, 0f, 1f,
        Color.parseColor("#ff0000"),
        Color.parseColor("#00ff00"),
        colorChangeType,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
注意该动画只能用于textview。  
更多示例，请查看[代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTextViewColorAnimationActivity.java)。  
[什么是colorChangeType？](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)  

#### Shape Color Animation
Shape变色动画帮助改变以shape-drawable为背景的view的颜色。  
```java
/**
 *
 * @param page                The shape-drawable color animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The shape-drawable color animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The shape-drawable color animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromColor           Original color
 
 * @param targetColor         Target color
 
 * @param colorChangeType     How to change the color. For more information, 
                              please check the ColorChangeType.class
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoShapeColorAnimation(
        0, 0f, 1f,
        Color.parseColor("#ff0000"),
        Color.parseColor("#00ff00"),
        colorChangeType,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
注意这个动画只能改变背景是shape-drawable的view的颜色。  
也就是这样：  
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval"  >
    <solid android:color="@color/red"/>
</shape>
```
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoStateListColorAnimationActivity.java)。  
[什么是colorChangeType？](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)  

#### State-List Color Animation
State-List变色动画帮助改变以state-list-drawable为背景的view的颜色。  
```java
/**
 *
 * @param page                The state-list-drawable color animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The state-list-drawable color animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The state-list-drawable color animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromColor           Original colors, corresponding to the items in xml
 
 * @param targetColor         Target colors, corresponding to the items in xml
 
 * @param colorChangeType     How to change the color. For more information, 
                              please check the ColorChangeType.class
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoStateListColorAnimation(
        0, 0f, 1f,
        new int[]{Color.parseColor("#ff0000"), Color.parseColor("#ff0000"), Color.parseColor("#ff0000")},
        new int[]{Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), Color.parseColor("#00ff00")},
        colorChangeType,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
对应的drawable是：  
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" >
    <item android:state_pressed="true" >
        <shape android:shape="rectangle"  >
            <corners android:radius="10dp" />
            <solid android:color="@color/red" />
        </shape>
    </item>
    <item android:state_focused="true">
        <shape android:shape="rectangle"  >
            <corners android:radius="2dp" />
            <solid android:color="@color/red"/>
        </shape>
    </item>
    <item >
        <shape android:shape="rectangle"  >
            <corners android:radius="2dp" />
            <solid android:color="@color/red"/>
        </shape>
    </item>
</selector>
```
注意这个动画只能改变以state-list-drawable为背景的view的颜色。  
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoStateListColorAnimationActivity.java)。  
[什么是colorChangeType？](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)  

#### Layer-List Color Animation
Layer-List变色动画帮助改变以state-list-drawable为背景的view的颜色。  
```java
/**
 *
 * @param page                The layer-list-drawable color animation will start from 
                              page(0, 1, ..., adapter.getCount() - 1)
                              
 * @param startOffset         The layer-list-drawable color animation will start from this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param endOffset           The layer-list-drawable color animation will end with this offset([0, 1]) 
                              when swiping from page to page + 1
                              
 * @param fromColor           Original colors, corresponding to the items in xml
 
 * @param targetColor         Target colors, corresponding to the items in xml
 
 * @param colorChangeType     How to change the color. For more information, 
                              please check the ColorChangeType.class
 
 * @param easeType            Ease type, please check the ease type section
 
 * @param useSameEaseTypeBack Whether use the same ease type to back
 */
ViewAnimation animation = new ViewAnimation(findViewById(R.id.test));
animation.addPageAnimaition(new WoWoLayerListColorAnimation(
        0, 0f, 1f,
        new int[]{
          Color.parseColor("#000000"), 
          Color.parseColor("#ff0000"), 
          Color.parseColor("#00ff00"), 
          Color.parseColor("#00ff00"), 
          Color.parseColor("#ff0000")},
        new int[]{
          Color.parseColor("#0000ff"), 
          Color.parseColor("#00ff00"), 
          Color.parseColor("#ff0000"), 
          Color.parseColor("#ff0000"), 
          Color.parseColor("#00ff00")},
        colorChangeType,
        easeType,
        useSameEaseTypeBack));
wowoViewPager.addAnimation(animation);
```
对应的drawable是：  
```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/item1"
        android:top="0dp" android:left="0dp" android:bottom="0dp" android:right="0dp">
        <shape android:shape="rectangle">
            <size android:width="200dp"
                android:height="200dp"/>
            <solid android:color="@color/black"/>
        </shape>
    </item>

    <item
        android:id="@+id/item2"
        android:top="20dp" android:left="20dp" android:bottom="100dp" android:right="100dp">
        <shape android:shape="rectangle">
            <size android:width="80dp"
                android:height="80dp"/>
            <solid android:color="@color/red"/>
        </shape>
    </item>
    
    <item
        android:id="@+id/item3"
        android:top="20dp" android:left="100dp" android:bottom="100dp" android:right="20dp">
        <shape android:shape="rectangle">
            <size android:width="80dp"
                android:height="80dp"/>
            <solid android:color="@color/green"/>
        </shape>
    </item>

    <item
        android:id="@+id/item4"
        android:top="100dp" android:left="20dp" android:bottom="20dp" android:right="100dp">
        <shape android:shape="rectangle">
            <size android:width="80dp"
                android:height="80dp"/>
            <solid android:color="@color/green"/>
        </shape>
    </item>
    
    <item
        android:id="@+id/item5"
        android:top="100dp" android:left="100dp" android:bottom="20dp" android:right="20dp">
        <shape android:shape="rectangle">
            <size android:width="80dp"
                android:height="80dp"/>
            <solid android:color="@color/red"/>
        </shape>
    </item>
</layer-list>
```
注意该动画只能改变以state-list-drawable为背景的view的颜色。  
更多示例，请查看 [代码](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoLayerListColorAnimationActivity.java)。  
[什么是colorChangeType？](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#rgb-or-hsv)  

### 路径动画
#### 路径动画
路径动画可以帮助画一条路。  
而且你还可以为这条路加一个头：   
![Ease](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/PathAnimation.gif)  
你需要做的是：  
##### 在xml中加入这个：    
```xml
<com.nightonke.wowoviewpager.WoWoPathView
    android:id="@+id/pathview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/transparent"
    app:pathColor="@color/white"
    app:pathWidth="5"
    app:headImageId="@drawable/airplane"
    app:headImageWidth="240"
    />
```
改变对应的属性：   

属性                      | 描述                      | 类型             
:------------------------ | :------------------------ | :------------------------ |
app\:pathColor | 路径颜色 | resource of color
app\:pathWidth | 路径宽度 | float
app\:headImageId | 路径头图像 | resource of image
app\:headImageWidth| 路径头宽度 | float
app\:headImageHeight| 路径头高度 | float 

##### 创造一条路径：  
你必须先简单了解 [Path](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html)。  
我建议你使用 [cubicTo](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html#cubicTo(float, float, float, float, float, float)) 函数来画曲线，用 [lineTo](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html#lineTo(float, float)) 函数来画直线。    
你可以通过以下网站来获得cubicTo函数的6个参数：  
[Canvature](https://canvature.appspot.com/)  
[BezierTool](http://www.victoriakirst.com/beziertool/)  
正如上面的gif，这条路径其实由3条曲线组成：  
```java
WoWoPathView pathView = (WoWoPathView)findViewById(R.id.pathview);
ViewGroup.LayoutParams layoutParams = pathView.getLayoutParams();
layoutParams.height = screenH;
// set the pathView a little wider,
// then the airplane can hide
layoutParams.width = screenW + 200;
pathView.setLayoutParams(layoutParams);

// use this to adjust the path
int xoff = -300;
int yoff = screenH - 616 - 300;
float xScale = 1.5f;
float yScale = 1;

Path path = new Path();
path.moveTo(xScale * (565 + xoff), screenH + yoff);
path.cubicTo(
        xScale * (509 + xoff), yScale * (385 + yoff),
        xScale * (144 + xoff), yScale * (272 + yoff),
        xScale * (394 + xoff), yScale * (144 + yoff));
path.cubicTo(
        xScale * (477 + xoff), yScale * (99 + yoff),
        xScale * (596 + xoff), yScale * (91 + yoff),
        xScale * (697 + xoff), yScale * (128 + yoff));
path.cubicTo(
        xScale * (850 + xoff), yScale * (189 + yoff),
        xScale * (803 + xoff), yScale * (324 + yoff),
        xScale * (66 + xoff), yScale * (307 + yoff));

// set the path to pathView
pathView.setPath(path);
ViewAnimation animation = new ViewAnimation(pathView);
animation.addPageAnimaition(new WoWoPathAnimation(
        0, 0f, 1f,
        easeType,
        useSameEaseTypeBack));
wowo.addAnimation(animation);
```

# 缓动函数
缓动函数可以让以上动画效果看起来更加真实，你可以在构建动画的时候指定缓动函数类型。  
![Ease](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/ease.png)  
当然你也可以不使用缓动函数，你可以在[这里](https://github.com/Nightonke/WoWoViewPager/blob/master/wowoviewpager/src/main/java/com/nightonke/wowoviewpager/Eases/EaseType.java).找到所有的缓动函数类型。  

# RGB or HSV
所有的变色动画都有两种变色效果，RGB和HSV。
HSV是改变亮度等来实现变色，在自然界中看起来更加正常。
比如，从红色变为绿色，在HSV中是红->黄->绿。
在RGB中，是红->一种介于红和绿的颜色->绿。 
但是通常情况下，用RGB要好点。
因为HSV看起来有点奇怪。
你可以再背景变色动画的[gif](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md#background-color-animation)中看到这两种变色的差异。  

# 版本
### 1.0.1  
第一个版本，11种动画。  
### 1.0.2
一个更好的HSV动画变换。

# Todo
1. 增加更多动画。  
2. 智能生成路径。  

# License

    Copyright 2016 Nightonke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
