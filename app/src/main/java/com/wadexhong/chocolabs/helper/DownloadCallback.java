package com.wadexhong.chocolabs.helper;

import android.database.Cursor;

import com.wadexhong.chocolabs.objects.Data;

/**
 * Created by wade8 on 2018/6/27.
 */

public interface DownloadCallback {
    void onSuccess(Cursor cursor);
    void onFailure();
}
