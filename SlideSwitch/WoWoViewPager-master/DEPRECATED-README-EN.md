# Deprecated! Check [Wiki](https://github.com/Nightonke/WoWoViewPager/wiki) for the New Document. 

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

Optimized for scrolling app intros or making your CV app. Free and open source.  
WoWoViewPager combines animations and viewpager. 
When you are swiping viewpager, you are also controlling the frames of the animation. 
Just like rewinding time. 

# Guide

[中文文档](https://github.com/Nightonke/WoWoViewPager/blob/master/README-ZH.md)  
[Gradle](https://github.com/Nightonke/WoWoViewPager#gradle)  
[Note](https://github.com/Nightonke/WoWoViewPager#note)  
[Demo](https://github.com/Nightonke/WoWoViewPager#demo)  
[Versions](https://github.com/Nightonke/WoWoViewPager#versions)  
[Todo](https://github.com/Nightonke/WoWoViewPager#todo)  
[License](https://github.com/Nightonke/WoWoViewPager#license)  

### Animations Usage
#### Basic Animations  
1. [Translation Animation](https://github.com/Nightonke/WoWoViewPager#translation-animation)
2. [Scale Animation](https://github.com/Nightonke/WoWoViewPager#scale-animation)
3. [Alpha Animation](https://github.com/Nightonke/WoWoViewPager#alpha-animation)
4. [Rotation Animation](https://github.com/Nightonke/WoWoViewPager#rotation-animation)

#### Font Size Animations
1. [TextView Size Animation](https://github.com/Nightonke/WoWoViewPager#textview-size-animation)

#### Color Animations
1. [Background Color Animation](https://github.com/Nightonke/WoWoViewPager#background-color-animation)
2. [TextView Color Animation](https://github.com/Nightonke/WoWoViewPager#textview-color-animation)
3. [Shape Color Animation](https://github.com/Nightonke/WoWoViewPager#shape-color-animation)
4. [State-List Color Animation](https://github.com/Nightonke/WoWoViewPager#state-list-color-animation)
5. [Layer-List Color Animation](https://github.com/Nightonke/WoWoViewPager#layer-list-color-animation)

#### Path Animations
1. [Path Animation](https://github.com/Nightonke/WoWoViewPager#path-animation)

#### Animation Types
1. [Ease](https://github.com/Nightonke/WoWoViewPager#ease)
2. [RGB or HSV](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)

# Gradle
Just add the "compile 'com.nightonke:wowoviewpager:1.0.2'" in your build.gradle of your module.  
```
dependencies {
    ...
    compile 'com.nightonke:wowoviewpager:1.0.2'
    ...
}
```

# Note
1. Thanks [SCViewPager](https://github.com/sacot41/SCViewPager) for giving me inspiration for codes.
2. Thanks [JazzHands](https://github.com/IFTTT/JazzHands) for giving me inspiration for animations.
3. Thanks [konmik](https://github.com/konmik) for providing a better HSV-animation for version 1.0.2.
4. I'll add more animations for WoWoViewPager.

# Demo
There is a demo for the current animations that WoWoViewPager supports. 
And there are App intros example and CV example(the above gifs) in the demo.  
![WoWo V1.0.2](https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20V1.0.2.png)  
Or by link:  
[WoWo V1.0.2 in Github](https://github.com/Nightonke/WoWoViewPager/blob/master/Apk/WoWo%20V1.0.2.apk?raw=true)  
[WoWo V1.0.2 in Fir](http://fir.im/wowoviewpager)  
![Demo Animation](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/demo_animation.png) 
![Demo Ease Type](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/demo_ease_type.png)  
You can try every animation and ease type that WoWoViewPager supports.   

# Animations Usage  

### Basic Animations  

#### Translation Animation  
Translation animation helps to move a view.  
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
As you can see, the code above helps to make part of effect in the above gif(from page 1 to page 3, 2 translation animations totally). 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTranslationAnimationActivity.java).  

#### Scale Animation  
Scale animation helps to scale a view.  
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
You can combine several animations to create a complex animation. For example, you can create 2 translation animations to make a polyline. Let's see the code above. From offset 0 to offset 0.5 starting from page 3, the width of the view doubles. And then, the height of the view doubles from offset 0.5 to offset 1. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoScaleAnimationActivity.java).  

#### Alpha Animation  
Alpha animation helps to change the opacity of the view, making fading or appearing effects.  
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
That's quite easy to understand. For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoAlphaAnimationActivity.java).  

#### Rotation Animation  
Rotation animation helps to rotate a view.  
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
The code above will cause the view upside down. 
Notice that in the above gif, the pivot of the 2 textview are different. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoRotationAnimationActivity.java).  

### Font Size Animation  
#### TextView Size Animation  
TextView size animation helps to change the font size of textview.  
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
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTextViewTextSizeAnimationActivity.java).  

### Color Animations

#### Background Color Animation
Background color animation helps to change the color of background.  
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
Notice that background color animation can only do its job when the view has the setBackgroundColor() method. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoBackgroundColorAnimationActivity.java).  
[What is colorChangeType?](https://github.com/Nightonke/WoWoViewPager#rgb-&-hsv)  

#### TextView Color Animation
TextView color animation helps to change the font color of textview only.  
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
Notice that textview color animation can only do its job when the view is textview. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoTextViewColorAnimationActivity.java).  
[What is colorChangeType?](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)  

#### Shape Color Animation
Shape color animation helps to change the shape-drawable background color of view.
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
Notice that shape-drawable color animation can only do its job when the background of view is shape-drawable.  
Like this:  
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval"  >
    <solid android:color="@color/red"/>
</shape>
```
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoStateListColorAnimationActivity.java).  
[What is colorChangeType?](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)  

#### State-List Color Animation
State-List color animation helps to change the state-list-drawable background color of view.
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
And the corresponding drawable is:  
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
Notice that state-list-drawable color animation can only do its job when the background of view is state-list-drawable. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoStateListColorAnimationActivity.java).  
[What is colorChangeType?](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)  

#### Layer-List Color Animation
Layer-List color animation helps to change the layer-list-drawable background color of view.
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
And the corresponding drawable is:  
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
Notice that layer-list-drawable color animation can only do its job when the background of view is layer-list-drawable. 
For more codes about the animation, please check the [code](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/java/com/nightonke/wowoviewpagerexample/WoWoLayerListColorAnimationActivity.java).  
[What is colorChangeType?](https://github.com/Nightonke/WoWoViewPager#rgb-or-hsv)  

### Path Animation
#### Path Animation
Path animation helps to draw a path. 
And you can add an image to the head of the path like this:  
![Ease](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/PathAnimation.gif)  
All you need to do is:  
##### Add this in xml:  
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
You can set the attributes in xml:  

Attribute                   | Describe                  | Type             
:------------------------ | :------------------------ | :------------------------ |
app\:pathColor | color of the path | resource of color
app\:pathWidth | width of the path | float
app\:headImageId | image of the head | resource of image
app\:headImageWidth| width of the head | float
app\:headImageHeight| height of the head | float 

##### Create a path:  
To create a path, you must know something about [Path](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html).  
I suggest you use the [cubicTo](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html#cubicTo(float, float, float, float, float, float)) function to draw a curve and [lineTo](http://developer.android.com/intl/zh-cn/reference/android/graphics/Path.html#lineTo(float, float)) function to draw a line.  
You can use these websites to get the 6 parameters of the cubicTo function:  
[Canvature](https://canvature.appspot.com/)  
[BezierTool](http://www.victoriakirst.com/beziertool/)  
As the above gif shows, there are only 3 curves to combine this path:
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

# Ease
To make the animations more lifesome, you can choose different ease type to play the animations.  
![Ease](https://github.com/Nightonke/WoWoViewPager/blob/master/Pictures/ease.png)  
Of course you can use linear function. 
You can find all the ease enums [here](https://github.com/Nightonke/WoWoViewPager/blob/master/wowoviewpager/src/main/java/com/nightonke/wowoviewpager/Eases/EaseType.java).

# RGB or HSV
All the color changing animation has 2 types, RGB and HSV(Hue [0 .. 360), Saturation [0...1] and Value [0...1] If hsv values are out of range, they are pinned). 
The HSV changing type may looks more comfortable in nature. 
For instance, to change red to green, in HSV, it will be red->yellow->green. 
In RGB, it will be red->some color between red and green->green. 
But usually the RGB changing type is the good one. 
Because HSV sometimes looks strange(my opinion). 
You can see the performance of these 2 types in the gif of [Background Color Animation](https://github.com/Nightonke/WoWoViewPager#background-color-animation).  

# Versions
### 1.0.1  
First version for 11 animations.
### 1.0.2  
A better HSV-animation.

# Todo
1. Try to add more animations.
2. Auto-Create path.  

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
