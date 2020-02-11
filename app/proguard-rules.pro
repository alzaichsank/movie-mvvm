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
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/adt-bundle-mac-x86_64-20140702/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#-keep class * {
#    public private *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Setting
-optimizationpasses 5
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dontusemixedcaseclassnames
-dontoptimize
-ignorewarnings

-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn com.squareup.okhttp.internal.**
-dontwarn com.squareup.picasso.**
-dontwarn org.apache.commons.logging.**
-dontwarn org.kobjects.**
-dontwarn org.ksoap2.**
-dontwarn org.kxml2.**
-dontwarn org.xmlpull.v1.**
-dontwarn java.**
-dontwarn javax.**
-dontwarn javax.annotation.*
-dontwarn com.github.jaiimageio.**
-dontwarn com.google.zxing.**
-dontwarn android.databinding.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-dontwarn com.google.android.gms.**
-dontwarn retrofit2.**
-dontwarn com.squareup.okhttp.internal.**
-dontwarn com.squareup.okhttp3.**
-dontwarn android.databinding.**
-dontwarn org.kobjects.**
-dontwarn org.ksoap2.**
-dontwarn org.kxml2.**
-dontwarn org.xmlpull.v1.**

-keep class org.kobjects.** { *; }
-keep class org.ksoap2.** { *; }
-keep class org.kxml2.** { *; }
-keep class org.xmlpull.** { *; }
-keep class android.databinding.** { *; }
-keep class org.kobjects.** { *; }
-keep class org.ksoap2.** { *; }
-keep class org.kxml2.** { *; }
-keep class org.xmlpull.** { *; }
-keep class android.databinding.** { *; }
-keep class java.** {*;}
-keep class javax.** {*;}
-keep class com.github.jaiimageio.** {*;}
-keep class com.google.zxing.** {*;}
-keep class retrofit2.** { *; }
-keep class com.squareup.okhttp.* { *;}
-keep class okhttp3.* { *;}
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.* { *; }
#-keep public class com.google.android.gms.* { public *; }
#-keep class * extends java.util.ListResourceBundle {
#    protected Object[][] getContents();
#}
#-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
#    public static final *** NULL;
#}
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.http.* <methods>;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
#-keepnames @com.google.android.gms.common.annotation.KeepName class *
#-keepclassmembernames class * {
#    @com.google.android.gms.common.annotation.KeepName *;
#}

# --------------------------------------------------------------------
# Square OkHttp
# --------------------------------------------------------------------
# https://github.com/levelup/Android-HttpClient/blob/master/HttpClient-OkHttp/proguard-project.txt
-keepnames class com.levelup.http.okhttp.** { *; }
-keepnames interface com.levelup.http.okhttp.** { *; }
-keepnames class com.squareup.okhttp.** { *; }
-keepnames interface com.squareup.okhttp.** { *; }

-dontwarn com.squareup.okhttp.internal.http.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.squareup.okhttp.internal.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.* { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.**

# --------------------------------------------------------------------
# Square Retrofit (uses GSON)
# --------------------------------------------------------------------
# https://github.com/square/retrofit/issues/372
-keepattributes Signature
-keepattributes *Annotation*

-dontwarn rx.**
-keep class com.google.gson.** { *; }
-keep class com.google.inject.* { *; }
-keep class org.apache.http.* { *; }
-keep class org.apache.james.mime4j.* { *; }
-keep class javax.inject.* { *; }
-keep class retrofit.* { *; }
-keep class sun.misc.Unsafe { *; }

-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-dontwarn retrofit.appengine.**

# --------------------------------------------------------------------
# IMPORTANT: If you're using GSON or Retrofit you must edit this part
# to prevent your model classes being removed or obfuscated
# --------------------------------------------------------------------

-keep class com.crashlytics.** { public private *; }
-keep class com.google.firebase.** { public private  *; }

-keep @interface com.airbnb.deeplinkdispatch.DeepLink
-keepclasseswithmembers class * {
    @com.airbnb.deeplinkdispatch.DeepLink <methods>;
}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keep class * extends com.raizlabs.android.dbflow.config.DatabaseHolder { *; }
-keep class net.sqlcipher.** { *; }

-keep class com.beehapps.waroengonline.data.** { *; }
-keep class com.beehapps.waroengonline.presentation.** { *; }
-keep class com.beehapps.waroengonline.domain.** { *; }
-keep public class com.beehapps.waroengonline.data.**
-keep public class com.beehapps.waroengonline.presentation.**
-keep public class com.beehapps.waroengonline.domain.**
-dontwarn net.sqlcipher.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
 **[] $VALUES;
 public *;
 }

-printmapping mapping.txt
-printconfiguration full-r8-config.txt