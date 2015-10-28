package gl.com.Util;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by mac on 15-10-26.
 */
public class OkHttpClientUtil {

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance(){
        if (okHttpClient==null){
            synchronized (OkHttpClientUtil.class){
                if (okHttpClient == null){
                    okHttpClient = new OkHttpClient();
                }
            }
        }
        return okHttpClient;
    }
}
