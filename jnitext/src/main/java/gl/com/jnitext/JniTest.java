package gl.com.jnitext;

/**
 * Created by mac on 15-10-31.
 */
import java.lang.System;
public class JniTest {
    static{
        //加载 的so库
        System.loadLibrary("jni-test");
    }

    public static void main(String args[]){
        JniTest jniTest = new JniTest();
        System.out.println(jniTest.get());
        jniTest.set("hello jni");
    }

    public native String get();
    public native void set(String str);
}
