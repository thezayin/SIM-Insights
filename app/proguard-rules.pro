# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn com.thezayin.ads.GoogleManager
-dontwarn com.thezayin.analytics.analytics.Analytics
-dontwarn com.thezayin.di.AppModuleKt
-dontwarn com.thezayin.framework.extension.ads.ShowAppOpenAdKt
-dontwarn com.thezayin.framework.remote.AdConfigs
-dontwarn com.thezayin.framework.remote.RemoteConfig
-dontwarn com.thezayin.home.HomeScreenKt
-dontwarn com.thezayin.premium.PremiumScreenKt
-dontwarn com.thezayin.presentation.ResultScreenKt
-dontwarn com.thezayin.presentation.ServerScreenKt
-dontwarn com.thezayin.presentation.SplashScreenKt
-dontwarn com.thezayin.setting.SettingScreenKt
-dontwarn com.thezayin.web.WebScreenKt

-keepclassmembers class * {
    public <init>(...);
}

-keep class com.thezayin.di.** { *; }