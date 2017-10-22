# JniSample
Android Jni Sample

# NDK下载配置

1、NDK下载：[Google官网](https://developer.android.google.cn/ndk/downloads/index.html)或者
Android Studio SDK Manger -> SDK Tools -> NDK -> Apply

2、配置项目 -> Open Module -> Android NDK Location -> OK -> 编译

3、在根目录gradle.properties添加：android.useDeprecatedNdk=true，允许使用已经过时的NDK版本

4、在module app的build.gradle下添加ndk节点：

    ndk {
        //System.loadLobrary(“本地库名称”)
        moduleName "nativeLib"
  
        //要用到的jni库
        ldLibs "log", "z", "m"
    
        //要生成哪些平台的so文件
        abiFilters "armeabi", "armeabi-v7a", "x86"
    }

# 编写JNI接口

1、创建NativeMethod类

    package cn.autoref.jnisample;
    
    public class NativeMethod {
        public native String getHelloJniText();
    }
    
2、Build -> Make Project

在/JniSample/app/build/intermediates/下会多一个classes文件夹，通过AS的Terminal进入到classes下的debug文件夹，执行：
    
    javah cn.autoref.jnisample.NativeMethod
    
会生成cn_autoref_jnisample_NativeMethod.h文件

3、在main文件夹下新建一个jni文件夹

main -> New -> Folder -> JNI Folder，把刚才生成的.h文件移动到新建的jni文件夹

# 实现.h声明的方法

1、新建cn_autoref_jnisample_NativeMethod.cpp文件，方法命名规则为：Java + 包名 + 类名 + 方法名，实现内容如下：

    #include <cn_autoref_jnisample_NativeMethod.h>

    JNIEXPORT jstring JNICALL Java_cn_autoref_jnisample_NativeMethod_getHelloJniText
        (JNIEnv *env, jobject) {
        return env -> NewStringUTF("Hello Jni !");
    }
    
2、编译加载so库：

    static {
        //名字需要跟build.gradle的ndk节点下的名字一样
        System.loadLibrary("nativeLib");
    }

    TextView textView = (TextView) findViewById(R.id.show_text_view);
    NativeMethod nativeMethod = new NativeMethod();
    textView.setText(nativeMethod.getHelloJniText());
    
编译出的so目录为：JniSample/app/build/intermediates/ndk/debug/lib。
    



