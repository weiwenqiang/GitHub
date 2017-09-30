# MVP

App based on Material Design + MVP + Rxjava + Retrofit + Okhttp + Glide

本项目本着简洁的思想，让开发更加简单，抽取出了core做为库，可以直接引入进行快捷开发，项目仍在改进中，如果有好的建议或者发现什么问题欢迎[issue](https://github.com/SuperMan42/MVP/issues),email<424346976@qq.com>，如果感觉对你有帮助也欢迎点个star，fork，本项目仅做学习交流使用

简书(详情):[http://www.jianshu.com/p/d98013e0cd03](http://www.jianshu.com/p/d98013e0cd03)

QQ群:482866708  
![](https://github.com/SuperMan42/MVP/blob/master/share.png)  

## [中文版](MVP.md)   [英文版](README.md)

## [Andorid学习资料收集](https://github.com/SuperMan42/MVP/wiki/Android%E5%AD%A6%E4%B9%A0%E8%B5%84%E6%96%99%E6%94%B6%E9%9B%86)

## [Wiki 使用方法](https://github.com/SuperMan42/MVP/wiki)
   
## Preview
1. 总览(列表)  
![](https://github.com/SuperMan42/MVP/blob/master/1.gif)  
2. 图片选择器和表情键盘(支持动态表情和emoji表情等)  
![](https://github.com/SuperMan42/MVP/blob/master/2.gif)  
3. 夜间和日间模式切换  
![](https://github.com/SuperMan42/MVP/blob/master/3.gif)  
4. 全民直播(如何优雅的看视频)  
![](https://github.com/SuperMan42/MVP/blob/master/art/4.gif)  
5. 缓存(断网情况下任然可以愉快的玩)  
![](https://github.com/SuperMan42/MVP/blob/master/art/5.gif)  

[Download APK](http://pro-app-mt.fir.im/85bcc48436a73ec65be41df573fd81ecbfc7f377.apk?AWSAccessKeyId=e0cada7f00f2465b929656d799937873&Expires=1479991896&Signature=RAHMsJ6bxPgxQxpDStKNj9rC3dE%3D&filename=app-release.apk_1.0.apk)
(Android 5.0 or above)  

![](https://github.com/SuperMan42/MVP/blob/master/download.png)

## Points
* 使用Rxjava配合Retrofit2+okhttp做网络请求和缓存
* 使用RxUtil对线程操作和网络请求结果处理做了封装
* 使用RxManager对订阅生命周期做了统一管理
* 使用RxBus做了组件间通信
* 使用RxPermissions对android6.0进行权限申请
* 使用Material Design控件和动画
* 使用MVP架构整个项目，并且抽取出core做为库，导入core即可省去50%的代码开发哦
* 使用Glide做图片处理和加载
* 使用Fragmentation简化Fragment的操作和懒加载
* 日间和夜间模式切换
* 添加了图片选择器(高仿微信)和表情键盘(支持动态表情)
* 自己封装了recyclerview和recyclerviewpager实现下拉刷新，上拉加载更多和pagerview功能（只需简单几句代码即可实现各种列表，无需adapter，无需自己设计分页加载）
* 使用x5WebView做阅览页
* 日报首页的头部可以循环滚动（使用了rxjava轮循和recyclerviewpager）

## 使用  

### 导入core库
```
dependencies {
    compile 'com.hpw.mvpframe:core:1.0.0'
}
```  

## TODO
1. 添加aop
2. 继续优化  
3. 还有很多。。。

## Thanks
[知乎日报API](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[微信精选API](http://www.tianapi.com/#wxnew)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[T-MVP](https://github.com/north2016/T-MVP)
