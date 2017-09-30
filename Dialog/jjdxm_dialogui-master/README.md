
# [jjdxm_dialogui][project] #
### Copyright notice ###

我在网上写的文章、项目都可以转载，但请注明出处，这是我唯一的要求。当然纯我个人原创的成果被转载了，不注明出处也是没有关系的，但是由我转载或者借鉴了别人的成果的请注明他人的出处，算是对前辈们的一种尊重吧！

虽然我支持写禁止转载的作者，这是他们的成果，他们有这个权利，但我不觉得强行扭转用户习惯会有一个很好的结果。纯属个人的观点，没有特别的意思。可能我是一个版权意识很差的人吧，所以以前用了前辈们的文章、项目有很多都没有注明出处，实在是抱歉！有想起或看到的我都会逐一补回去。

从一开始，就没指望从我写的文章、项目上获得什么回报，一方面是为了自己以后能够快速的回忆起曾经做过的事情，避免重复造轮子做无意义的事，另一方面是为了锻炼下写文档、文字组织的能力和经验。如果在方便自己的同时，对你们也有很大帮助，自然是求之不得的事了。要是有人转载或使用了我的东西觉得有帮助想要打赏给我，多少都行哈，心里却很开心，被人认可总归是件令人愉悦的事情。

站在了前辈们的肩膀上，才能走得更远视野更广。前辈们写的文章、项目给我带来了很多知识和帮助，我没有理由不去努力，没有理由不让自己成长的更好。写出好的东西于人于己都是好的，但是由于本人自身视野和能力水平有限，错误或者不好的望多多指点交流。

项目中如有不同程度的参考借鉴前辈们的文章、项目会在下面注明出处的，纯属为了个人以后开发工作或者文档能力的方便。如有侵犯到您的合法权益，对您造成了困惑，请联系协商解决，望多多谅解哈！若您也有共同的兴趣交流技术上的问题加入交流群QQ： 548545202


## Introduction ##
[English documentation](https://github.com/jjdxmashl/jjdxm_dialogui/blob/master/README_EN.md)

这是一个基于AlertDialog和Dialog这两个类封装的多种弹出框样式，其中提供各种简单样式的弹出框使用说明。同时也可自定义弹出框。

## Features ##

1.使用链式开发代码简洁明了
2.所有的弹出框样式都在DialogUIUtils这个类中完成，方便查阅方法
3.可以自定义弹出框字体样式
4.简单的类似加载框的样式可以支持两种主题更改默认白色和灰色

## Screenshots ##

<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon01.gif" width="300">
 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon02.gif" width="600">


<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon01.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon02.png" width="300"> 
 <img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon03.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon04.png" width="300">
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon05.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon06.png" width="300"> 
 <img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon07.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon08.png" width="300">
 <img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon09.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon10.png" width="300">
## Download ##

[demo apk下载][downapk]

Download or grab via Maven:

	<dependency>
	  <groupId>com.dou361.dialogui</groupId>
	  <artifactId>jjdxm-dialogui</artifactId>
	  <version>x.x.x</version>
	</dependency>

or Gradle:

	compile 'com.dou361.dialogui:jjdxm-dialogui:x.x.x'


历史版本：

    compile 'com.dou361.dialogui:jjdxm-dialogui:1.0.3'
    compile 'com.dou361.dialogui:jjdxm-dialogui:1.0.2'
	compile 'com.dou361.dialogui:jjdxm-dialogui:1.0.1'
	compile 'com.dou361.dialogui:jjdxm-dialogui:1.0.0'

jjdxm-dialogui requires at minimum Java 9 or Android 2.3.


[架包的打包引用以及冲突解决][jaraar]

## Proguard ##

类库中使用consumerProguardFiles属性，它指定了编译时，库自动引入的混淆规则。也就是说应用打包时候会自动的寻找库里的混淆文件，不需要手工配置了。


[AndroidStudio代码混淆注意的问题][minify]

## Get Started ##

### step1 ###

    compile ('com.dou361.dialogui:jjdxm-dialogui:1.0.3'){
    // exclude group: 'com.android.support', module: 'support-v7'
    // exclude group: 'com.android.support', module: 'design'
    }


如果需要使用toast类，采用单例模式的，多次调用toast后只会显示一个，需要初始化DialogUIUtils类，否则会抛异常，使用前初始化即可，代码如下

	DialogUIUtils.init(appContext);

### step2 ###
如果不需要使用toast类，可以不操作step1，直接使用相对于的弹出框即可。以下分别是部分弹出框的调用代码案例。

#### 自定义弹出框 ####

    /**
     * 自定义弹出框 默认居中可取消可点击
     *
     * @param context     上下问
     * @param contentView 自定义view
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView)

    /**
     * 自定义弹出框 默认可取消可点击
     *
     * @param context     上下文
     * @param contentView 自定义view
     * @param gravity     显示window的位置例如Gravity.center
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView, int gravity)

    /***
     * 自定义弹出框
     *
     * @param context          上下文
     * @param contentView      自定义view
     * @param gravity          显示window的位置例如Gravity.center
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @return
     */
    public static BuildBean showCustomAlert(Context context, View contentView, int gravity, boolean cancleable, boolean outsideTouchable)

具体使用

	View rootView = View.inflate(activity, R.layout.custom_dialog_layout, null);
	DialogUIUtils.showCustomAlert(this, rootView).show();

#### 弹出toast ####

	/**
     * 弹出toast 默认白色背景可取消可点击
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    public static BuildBean showToastTie(Context context, CharSequence msg) 

    /**
     * 弹出toast 默认可取消可点击
     *
     * @param context   上下文
     * @param msg       提示文本
     * @param isWhiteBg true为白色背景false为灰色背景
     */
    public static BuildBean showToastTie(Context context, CharSequence msg, boolean isWhiteBg) 

    /**
     * 弹出toast
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showToastTie(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg)

具体使用

	DialogUIUtils.showToastTie(this, "加载中...").show();

#### 横向加载框 ####

    /**
     * 横向加载框 默认白色背景可取消可点击
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    public static BuildBean showLoadingHorizontal(Context context, CharSequence msg)

    /**
     * 横向加载框 默认可取消可点击
     *
     * @param context   上下文
     * @param msg       提示文本
     * @param isWhiteBg true为白色背景false为灰色背景
     */
    public static BuildBean showLoadingHorizontal(Context context, CharSequence msg, boolean isWhiteBg)

    /**
     * 横向加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showLoadingHorizontal(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg)

具体使用

	DialogUIUtils.showLoadingHorizontal(this, "加载中...").show();

#### md风格横向加载框 ####

    /**
     * md风格横向加载框 默认白色背景可取消可点击
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    public static BuildBean showMdLoadingHorizontal(Context context, CharSequence msg)

    /**
     * md风格横向加载框 默认可取消可点击
     *
     * @param context   上下文
     * @param msg       提示文本
     * @param isWhiteBg true为白色背景false为灰色背景
     */
    public static BuildBean showMdLoadingHorizontal(Context context, CharSequence msg, boolean isWhiteBg) 

    /**
     * md风格横向加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showMdLoadingHorizontal(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg) 

具体使用

	DialogUIUtils.showMdLoadingHorizontal(this, "加载中...").show();

#### 竖向加载框 ####

    /**
     * 竖向加载框  默认白色背景可取消可点击
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    public static BuildBean showLoadingVertical(Context context, CharSequence msg) 

    /**
     * 竖向加载框 默认可取消可点击
     *
     * @param context   上下文
     * @param msg       提示文本
     * @param isWhiteBg true为白色背景false为灰色背景
     */
    public static BuildBean showLoadingVertical(Context context, CharSequence msg, boolean isWhiteBg) 

    /**
     * 竖向加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showLoadingVertical(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg) 

具体使用

	DialogUIUtils.showLoadingVertical(this, "加载中...").show();

#### md风格竖向加载框 ####

    /**
     * md风格竖向加载框  默认白色背景可取消可点击
     *
     * @param context 上下文
     * @param msg     提示文本
     */
    public static BuildBean showMdLoadingVertical(Context context, CharSequence msg)

    /**
     * md风格竖向加载框 默认可取消可点击
     *
     * @param context   上下文
     * @param msg       提示文本
     * @param isWhiteBg true为白色背景false为灰色背景
     */
    public static BuildBean showMdLoadingVertical(Context context, CharSequence msg, boolean isWhiteBg)

    /**
     * md风格竖向加载框
     *
     * @param context          上下文
     * @param msg              提示文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param isWhiteBg        true为白色背景false为灰色背景
     */
    public static BuildBean showMdLoadingVertical(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg)


具体使用

	DialogUIUtils.showMdLoadingVertical(this, "加载中...").show();

#### md风格弹出框 ####

    /***
     * md风格弹出框 默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param msg      消息
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, DialogUIListener listener)

    /***
     * md风格弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param msg              消息
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	DialogUIUtils.showMdAlert(activity, "标题", "文本内容", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        
                    }

                    @Override
                    public void onNegative() {
                        
                    }

                }).show();

#### md风格多选框 ####

    /**
     * md风格多选框  默认可取消可点击
     *
     * @param activity     所在activity
     * @param title        标题 不传则无标题
     * @param words        消息数组
     * @param checkedItems 默认选中项
     * @param listener     事件监听
     */
    public static BuildBean showMdMultiChoose(Activity activity, CharSequence title, CharSequence[] words, boolean[] checkedItems, DialogUIListener listener)

    /***
     * md风格多选框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param words            消息数组
     * @param checkedItems     默认选中项
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdMultiChoose(Activity activity, CharSequence title, CharSequence[] words, boolean[] checkedItems, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	String[] words = new String[]{"1", "2", "3"};
                boolean[] choseDefault = new boolean[]{false, false, false};
                DialogUIUtils.showMdMultiChoose(activity, "标题", words, choseDefault, new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }
                }).show();

#### 单选框 ####

    /**
     * 单选框  默认可取消可点击
     *
     * @param activity      所在activity
     * @param title         标题 不传则无标题
     * @param defaultChosen 默认选中项
     * @param words         消息数组
     * @param listener      事件监听
     */
    public static BuildBean showSingleChoose(Activity activity, CharSequence title, int defaultChosen, CharSequence[] words, DialogUIItemListener listener) 

    /**
     * 单选框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param defaultChosen    默认选中项
     * @param words            消息数组
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showSingleChoose(Activity activity, CharSequence title, int defaultChosen, CharSequence[] words, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) 

具体使用

	String[] words2 = new String[]{"1", "2", "3"};
	                DialogUIUtils.showSingleChoose(activity, "单选", 0, words2, new DialogUIItemListener() {
	                    @Override
	                    public void onItemClick(CharSequence text, int position) {
	                        showToast(text + "--" + position);
	                    }
	                }).show();

#### 提示弹出框 ####

    /**
     * 提示弹出框 默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param listener 事件监听
     */
    public static BuildBean showAlert(Activity activity, CharSequence title, CharSequence msg, DialogUIListener listener)

    /**
     * 提示弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showAlert(Activity activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	DialogUIUtils.showAlert(activity, "标题", "文本内容", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        
                    }

                    @Override
                    public void onNegative() {
                        
                    }

                }).show();

#### 横向弹出框 ####

    /**
     * 横向弹出框  默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param msg      消息
     * @param listener 事件监听
     */
    public static BuildBean showAlertHorizontal(Context activity, CharSequence title, CharSequence msg, DialogUIListener listener) 

    /**
     * 横向弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param msg              消息
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showAlertHorizontal(Context activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	DialogUIUtils.showAlertHorizontal(activity, "标题", "文本内容", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        
                    }

                    @Override
                    public void onNegative() {
                        
                    }

                }).show();

#### 竖向弹出框 ####

    /**
     * 竖向弹出框  默认可取消可点击
     *
     * @param activity 所在activity
     * @param title    标题 不传则无标题
     * @param msg      消息
     * @param listener 事件监听
     */
    public static BuildBean showAlertVertical(Context activity, CharSequence title, CharSequence msg, DialogUIListener listener)

    /**
     * 竖向弹出框
     *
     * @param activity         所在activity
     * @param title            标题 不传则无标题
     * @param msg              消息
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     */
    public static BuildBean showAlertVertical(Context activity, CharSequence title, CharSequence msg, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	DialogUIUtils.showAlertVertical(activity, "标题", "文本内容", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        
                    }

                    @Override
                    public void onNegative() {
                        
                    }

                }).show();

#### 中间弹出列表 ####

    /**
     * 中间弹出列表 默认可取消可点击
     *
     * @param context  上下文
     * @param words    素组集合
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showCenterSheet(Context context, List<? extends CharSequence> words, DialogUIItemListener listener)

    /***
     * 中间弹出列表
     *
     * @param context          上下文
     * @param words            素组集合
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showCenterSheet(Context context, List<? extends CharSequence> words, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener)

具体使用

	List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
                DialogUIUtils.showCenterSheet(activity, strings, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                    }

                    @Override
                    public void onBottomBtnClick() {
                    }
                }).show();

#### 带取消的底部弹出列表 ####

    /**
     * 带取消的底部弹出列表 默认可取消可点击
     *
     * @param context   上下文
     * @param words     素组集合
     * @param bottomTxt 底部按钮文本
     * @param listener  事件监听
     * @return
     */
    public static BuildBean showBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, DialogUIItemListener listener)

    /***
     * 带取消的底部弹出列表
     *
     * @param context          上下文
     * @param words            素组集合
     * @param bottomTxt        底部按钮文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showBottomSheetAndCancel(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) 

具体使用

	List<String> strings = new ArrayList<>();
                strings.add("1");
                strings.add("2");
                strings.add("3");
                DialogUIUtils.showBottomSheetAndCancel(activity, strings, "取消", new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                    }

                    @Override
                    public void onBottomBtnClick() {
                    }
                }).show();

#### 底部弹出列表 ####

    /**
     * 底部弹出列表 默认可取消可点击
     *
     * @param context  上下文
     * @param datas    集合需要BottomSheetBean对象
     * @param listener 事件监听
     * @return
     */
    public static BuildBean showBottomSheet(Activity context, List datas, DialogUIItemListener listener)

    /***
     * 底部弹出列表
     *
     * @param context          上下文
     * @param datas            集合需要BottomSheetBean对象
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showBottomSheet(Activity context, List datas, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener)

具体使用

	List<BottomSheetBean> datass = new ArrayList<>();
                datass.add(new BottomSheetBean(0, "1"));
                datass.add(new BottomSheetBean(0, "2"));
                datass.add(new BottomSheetBean(0, "3"));
                DialogUIUtils.showBottomSheet(this, datass, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {

                    }
                }).show();

#### 输入框 ####

    /**
     * 输入框 默认可取消可点击
     *
     * @param context   上下文
     * @param title     标题
     * @param hint1     第一个文本框提示语
     * @param hint2     第二个文本框提示语
     * @param firstTxt  第一个按钮文本
     * @param secondTxt 第二个按钮文本
     * @param listener  事件监听
     * @return
     */
    public static BuildBean showAlertInput(Context context, CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, DialogUIListener listener)

    /***
     * 输入框
     *
     * @param context          上下文
     * @param title            标题
     * @param hint1            第一个文本框提示语
     * @param hint2            第二个文本框提示语
     * @param firstTxt         第一个按钮文本
     * @param secondTxt        第二个按钮文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showAlertInput(Context context, CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, boolean cancleable, boolean outsideTouchable, DialogUIListener listener)

具体使用

	DialogUIUtils.showAlertInput(activity, "登录", "请输入用户名", "请输入密码", "登录", "取消", new DialogUIListener() {
                    @Override
                    public void onPositive() {

                    }

                    @Override
                    public void onNegative() {

                    }

                    @Override
                    public void onGetInput(CharSequence input1, CharSequence input2) {
                    }
                }).show();

#### md风格竖向底部弹出列表 ####

    /**
     * md风格竖向底部弹出列表 默认可取消可点击
     *
     * @param context   上下文
     * @param title     标题
     * @param datas     集合需要BottomSheetBean对象
     * @param bottomTxt 底部item文本
     * @param listener  事件监听
     * @return
     */
    public static BuildBean showMdBottomSheetVertical(Context context, CharSequence title, List datas, CharSequence bottomTxt, DialogUIItemListener listener)

    /***
     * md风格竖向底部弹出列表
     *
     * @param context          上下文
     * @param title            标题
     * @param datas            集合需要BottomSheetBean对象
     * @param bottomTxt        底部item文本
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdBottomSheetVertical(Context context, CharSequence title, List datas, CharSequence bottomTxt, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener) 

具体使用

	List<BottomSheetBean> datass = new ArrayList<>();
                datass.add(new BottomSheetBean(0, "1"));
                datass.add(new BottomSheetBean(0, "2"));
                datass.add(new BottomSheetBean(0, "3"));
                DialogUIUtils.showMdBottomSheetVertical(this, datass, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {

                    }
                }).show();

#### md风格横向底部弹出列表 ####

    /**
     * md风格横向底部弹出列表 默认可取消可点击
     *
     * @param context          上下文
     * @param title            标题
     * @param datas            集合需要BottomSheetBean对象
     * @param bottomTxt        底部item文本
     * @param columnsNum       列数量
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdBottomSheetHorizontal(Context context, CharSequence title, List datas, CharSequence bottomTxt, int columnsNum, DialogUIItemListener listener) 

    /***
     * md风格横向底部弹出列表
     *
     * @param context          上下文
     * @param title            标题
     * @param datas            集合需要BottomSheetBean对象
     * @param bottomTxt        底部item文本
     * @param columnsNum       列数量
     * @param cancleable       true为可以取消false为不可取消
     * @param outsideTouchable true为可以点击空白区域false为不可点击
     * @param listener         事件监听
     * @return
     */
    public static BuildBean showMdBottomSheetHorizontal(Context context, CharSequence title, List datas, CharSequence bottomTxt, int columnsNum, boolean cancleable, boolean outsideTouchable, DialogUIItemListener listener)

具体使用

	List<BottomSheetBean> datass = new ArrayList<>();
                datass.add(new BottomSheetBean(0, "1"));
                datass.add(new BottomSheetBean(0, "2"));
                datass.add(new BottomSheetBean(0, "3"));
                DialogUIUtils.showMdBottomSheetHorizontal(this, datass, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {

                    }
                }).show();


## More Actions ##

## ChangeLog ##

## About Author ##

#### 个人网站:[http://www.dou361.com][web] ####
#### GitHub:[jjdxmashl][github] ####
#### QQ:316988670 ####
#### 交流QQ群:548545202 ####


## License ##

    Copyright (C) dou361, The Framework Open Source Project
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
     	http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## (Frequently Asked Questions)FAQ ##
## Bugs Report and Help ##

If you find any bug when using project, please report [here][issues]. Thanks for helping us building a better one.




[web]:http://www.dou361.com
[github]:https://github.com/jjdxmashl/
[project]:https://github.com/jjdxmashl/jjdxm_dialogui/
[issues]:https://github.com/jjdxmashl/jjdxm_dialogui/issues/new
[downapk]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/apk/app-debug.apk
[lastaar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/release/jjdxm-dialogui-1.0.0.aar
[lastjar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/release/jjdxm-dialogui-1.0.0.jar
[icon01]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon01.png
[icon02]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_dialogui/master/screenshots/icon02.png
[jaraar]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/架包的打包引用以及冲突解决.md
[minify]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/AndroidStudio代码混淆注意的问题.md
[author]:http://www.jianshu.com/users/ec59bd61433a/latest_articles
[url]:http://www.jianshu.com/p/03fdcfd3ae9c?utm_campaign=maleskine&utm_content=note&utm_medium=writer_share