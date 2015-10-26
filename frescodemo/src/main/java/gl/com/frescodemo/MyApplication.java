package gl.com.frescodemo;

import android.app.ActivityManager;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by mac on 15-10-23.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static ImagePipelineConfig config = null;
    public static MyApplication getInstance(){
        if (instance==null){
            synchronized (MyApplication.class){
                if (instance==null){
                    instance=new MyApplication();
                }
            }
        }
        return instance;
    }
    @Override
    public void onCreate() {
        instance=this;
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        initImagePipeline();
    }

    private void initImagePipeline() {
//        config = ImagePipelineConfig.newBuilder(getApplicationContext())
//                .setBitmapMemoryCacheParamsSupplier(new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE)))
//                .setCacheKeyFactory(new CacheKeyFactory() {
//                })
//                .build();
    }

}
