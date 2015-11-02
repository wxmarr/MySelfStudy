#include "gl_com_jnitext_JniTest.h"
#include <stdio.h>
JNIEXPORT jstring JNICALL Java_gl_com_jnitext_JniTest_get(JNIEnv *env, jobject thiz) {
    printf("invoke get in c++\n");
    return env->NewStringUTF("Hello from JNI !");
}

JNIEXPORT void JNICALL Java_gl_com_jnitext_JniTest_set(JNIEnv *env, jobject thiz, jstring string) {
    printf("invoke set from C++\n");
    char* str = (char*)env->GetStringUTFChars(string,NULL);
    printf("%s\n", str);
    env->ReleaseStringUTFChars(string, str);
}