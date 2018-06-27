package com.wadexhong.chocolabs;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.wadexhong.chocolabs.helper.DatabaseHelper;

/**
 * Created by wade8 on 2018/6/27.
 */

public class Chocolabs extends Application {

    private static Context mContext;
    private static DatabaseHelper mDatabaseHelper;
    private static LruCache<String, Bitmap> mLruCache;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        initLruCache();
    }

    private void initLruCache() {

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 2;

        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }


    public static Context getAppContext() {
        return mContext;
    }

    public static DatabaseHelper getDatabaseHelper() {
        if (mDatabaseHelper == null) mDatabaseHelper = new DatabaseHelper(getAppContext());
        return mDatabaseHelper;
    }

    public static String getStringEasy(int stringId, Object... formatArgs) {
        return formatArgs == null ? getAppContext().getResources().getString(stringId) : getAppContext().getResources().getString(stringId, formatArgs);
    }

    public static LruCache<String, Bitmap> getLruCache(){
        return mLruCache;
    }
}
