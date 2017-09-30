# MVP

App based on Material Design + MVP + Rxjava + Retrofit + Okhttp + Glide

The project is based on the idea of simplicity, make development more simple, extract the core library, can be directly introduced to fast development. Project is still in improvement, if you have good suggestions or find any problems welcome[issue](https://github.com/SuperMan42/MVP/issues), email<424346976@qq.com>, If it help you welcome star, fork. This project only do study and communication

Blog (details): [http://www.jianshu.com/p/d98013e0cd03](http://www.jianshu.com/p/d98013e0cd03)

QQ group: 482866708  
![](https://github.com/SuperMan42/MVP/blob/master/share.png)

## [中文版](MVP.md)    [English version](README.md)

## [The Android learning data collection](https://github.com/SuperMan42/MVP/wiki/Android%E5%AD%A6%E4%B9%A0%E8%B5%84%E6%96%99%E6%94%B6%E9%9B%86)

## [Wiki 使用方法](https://github.com/SuperMan42/MVP/wiki)

## Preview
1. The overview (list)  
![](https://github.com/SuperMan42/MVP/blob/master/1.gif)
2. Image selector and expression keyboard (support dynamic expression and emoji expressions, etc.)  
![](https://github.com/SuperMan42/MVP/blob/master/2.gif)
3. Night and day mode switching  
![](https://github.com/SuperMan42/MVP/blob/master/3.gif) 
4. TV  
![](https://github.com/SuperMan42/MVP/blob/master/art/4.gif)  
5. Cache(No network)  
![](https://github.com/SuperMan42/MVP/blob/master/art/5.gif)  

[Download APK](http://pro-app-mt.fir.im/85bcc48436a73ec65be41df573fd81ecbfc7f377.apk?AWSAccessKeyId=e0cada7f00f2465b929656d799937873&Expires=1479991896&Signature=RAHMsJ6bxPgxQxpDStKNj9rC3dE%3D&filename=app-release.apk_1.0.apk)
(Android 5.0 or above)  

![](https://github.com/SuperMan42/MVP/blob/master/download.png)

## Points
* Use Rxjava Retrofit2 okhttp to do network requests and caching
* RxUtil is used to encapsulate thread handling and network request result processing
* Use the RxManager to manage the subscription lifecycle
* Use RxBus to do intercomponent communication
* Use RxPermissions for android6.0 permission application
* Use Material Design controls and animations
* Use the MVP architecture throughout the project, and extract the core as a library, import core can save 50% of the code development
* Use Glide for image processing and loading
* Use Fragmentation to simplify the operation of Fragment and lazy loading
* Day and night mode switching
* Add image selector(High imitation WeChat) and expression keyboard (support dynamic expression and emoji expressions, etc.)
* Encapsulates recyclerview and recyclerviewpager to implement pull-down refresh, pull-up loading and pagerview functionality（Just a few simple code to achieve a variety of lists, no adapter required, no need to design paging load）
* Use x5WebView to do the reading page
* The head of the daily home page can scroll in circles（Using the rxjava round robin and recyclerviewpager）

## Method of use

### Import the core library
```
dependencies {
    compile 'com.hpw.mvpframe:core:1.0.0'
}
```  

## TODO
1. Add aop
2. Continue to optimize
3. There are many to do。。。

## Thanks
[Zhihu Daily API](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[WeChat API](http://www.tianapi.com/#wxnew)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[T-MVP](https://github.com/north2016/T-MVP)
