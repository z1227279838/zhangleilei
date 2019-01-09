package com.skr.myproject.myapp;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        //初始化友盟SDK
        UMShareAPI.get(this);//初始化sd
        //开启debug模式，方便定位错误，具体错误检查方式可以查看
        //http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        //微信
       // PlatformConfig.setWeixin("wxdc1e388c1111c80b", "3baf1193c85774b3fd9d11117d76cab0");
        //新浪微博(第三个参数为回调地址)
        //PlatformConfig.setSinaWeibo("3111100954", "04b48b094faeb16683c32111124ebdad",
               // "http://sns.whalecloud.com/sina2/callback");
        //应用宝开放平台  APP ID  APP KEY
        PlatformConfig.setQQZone("1108026309", "Wp1SW5ybY5ufaEFQ");
    }
}
