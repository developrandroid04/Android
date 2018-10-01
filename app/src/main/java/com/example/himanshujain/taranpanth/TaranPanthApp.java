package com.example.himanshujain.taranpanth;

import android.app.Application;
import android.content.res.Resources;
import android.os.StrictMode;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class TaranPanthApp extends Application {

    private static final String TAG = "QBApp";


    private static TaranPanthApp sInstance;


    public void onCreate() {
        super.onCreate();
        //only for debug mode

        sInstance = this;


//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        mRefWatcher = LeakCanary.install(this);
//        Bugfender.init(this, "x6FMI0cJqzK0XKQM0i12utfIfJNBcCgM", BuildConfig.DEBUG);
//        Bugfender.enableLogcatLogging();
//        Bugfender.enableUIEventLogging(this);
//        Bugfender.setMaximumLocalStorageSize(10);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//         fetchCountries();
        // System.loadLibrary("eposprint");

    }

    public static synchronized TaranPanthApp getInstance() {
        return sInstance;
    }

    public static synchronized Resources getAppResources() {
        return sInstance.getResources();
    }




    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }





//    static {
//             System.loadLibrary("a01jni");
//            System.loadLibrary("eposprint");
//            System.loadLibrary("intelligentLib");
//            System.loadLibrary("ndkapi");
//            System.loadLibrary("ndkapism");
//            System.loadLibrary("serial_port");
//            System.loadLibrary("serialport");
//            System.loadLibrary("emvjni");
//
//
//    }
}

