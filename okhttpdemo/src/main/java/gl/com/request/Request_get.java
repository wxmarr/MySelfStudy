package gl.com.request;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

import gl.com.Util.OkHttpClientUtil;

/**
 * Created by mac on 15-10-26.
 */
public class Request_get {

    /**
     * 发送get请求
     */
    public static void get(){
        OkHttpClient client = OkHttpClientUtil.getInstance();
        Request request = new Request.Builder()
                .url("https://github.com/guolei1130")
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //这里并不是UI线程，如果想操作控件，还需要Handler
                    response.body().toString();
                    response.body().bytes();
                    response.body().byteStream();

            }
        });
    }

    /**
     * 请求参数为键值对
     */
    public static void postkv(){
        OkHttpClient client = OkHttpClientUtil.getInstance();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //添加键值对
        builder.add("username","guolei");
        Request request = new Request.Builder()
                .url("")
                .post(builder.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });

    }

    public static void postJson() throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client  = OkHttpClientUtil.getInstance();
        JSONObject jo = new JSONObject();
        RequestBody body = RequestBody.create(JSON,jo.toString());

        Request request = new Request.Builder()
                .url("")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            //成功
        }else{
            //失败
        }

    }
}
