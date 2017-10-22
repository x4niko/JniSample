#include <cn_autoref_jnisample_NativeMethod.h>

/*
 * Class:     cn_autoref_jnisample_NativeMethod
 * Method:    getHelloJniText
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_cn_autoref_jnisample_NativeMethod_getHelloJniText
  (JNIEnv *env, jobject) {
    return env -> NewStringUTF("Hello Jni !");
}
