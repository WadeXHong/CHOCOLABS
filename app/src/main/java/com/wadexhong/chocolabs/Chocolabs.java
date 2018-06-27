package com.wadexhong.chocolabs;

import android.app.Application;
import android.content.Context;

import com.wadexhong.chocolabs.helper.DatabaseHelper;

/**
 * Created by wade8 on 2018/6/27.
 */

public class Chocolabs extends Application {

    private static Context mContext;
    private static DatabaseHelper mDatabaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static DatabaseHelper getDatabaseHelper(){
        if (mDatabaseHelper == null) mDatabaseHelper = new DatabaseHelper(getAppContext());
        return mDatabaseHelper;
    }
}
