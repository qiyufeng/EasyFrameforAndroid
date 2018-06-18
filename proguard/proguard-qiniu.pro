## 七牛混淆规则 ##
## http://www.qiniu.com/ ##

#混淆处理 对七牛的SDK不需要做特殊混淆处理，混淆时将七牛相关的包都排除就可以了。
#在Android Studio中，混淆配置在 proguard-rules.pro 里面加上下面几行混淆代码就行：
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings
#注意：-ignorewarnings这个也是必须加的，如果不加这个编译的时候可能是可以通过的，但是release的时候还是会出现错误