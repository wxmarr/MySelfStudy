package gl.com.universalimageloaderdemo;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedVignetteBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by mac on 15-10-25.
 */
public class MyApplication extends Application {

    //正常状态的配置
    public static DisplayImageOptions default_options;


    public static DisplayImageOptions normal_options;
    //圆形头像的设置
    public static DisplayImageOptions head_options;
    @Override
    public void onCreate() {
        super.onCreate();
        //ImageLoader.getInstance();
        getNormalImageloader();
        initOptions();
    }

    private void initOptions() {

        default_options = new DisplayImageOptions.Builder()
                /**
                 * 这只Uri为空、加载失败、加载时候的图片资源，可以接受Drawable 和 资源ID
                 */
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                //是否设置在加载之前重置view
                .resetViewBeforeLoading(false)

                .delayBeforeLoading(1000)
                //是否缓存在内存中
                .cacheInMemory(false)
                //是否缓存在文件中
                .cacheOnDisk(false)
//                .preProcessor(...)
//                .postProcessor(...)
//                .extraForDownloader(...)
                .considerExifParams(false)
                //Image的缩放类型
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                //bitmap 的config
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                //decode参数
//                .decodingOptions()
                //设置显示，可以设置为圆角
                .displayer(new RoundedVignetteBitmapDisplayer(20, 30))
                .handler(new Handler())
                .build();

        normal_options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)

                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();

        head_options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                //设置圆角显示
                .displayer(new RoundedVignetteBitmapDisplayer(50,20))
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
    }


    /**
     * 配置默认的config
     */
    public void getNormalImageloader(){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
        /**
         * 初始化
         */
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 自定义配置 config
     */

    public void getMyImageloader(){
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
//                .taskExecutor(...)
//                .taskExecutorForCachedImages(...)
//                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .defaultDisplayImageOptions(normal_options)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

}
