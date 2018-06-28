package com.wadexhong.chocolabs.helper;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.objects.Data;

/**
 * Created by wade8 on 2018/6/27.
 */

public class InfoDownloadTask extends AsyncTask<Void, Void, Cursor> {

    private final static String TAG = InfoDownloadTask.class.getSimpleName();
    private DownloadCallback mDownloadCallback;

    public InfoDownloadTask(DownloadCallback downloadCallback) {
        mDownloadCallback = downloadCallback;
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        Data data = new JsonHelper().fetchJson();
        if (data != null){
            Chocolabs.getDatabaseHelper().inputDramas(data.getDramas());
        } else {
            Log.w(TAG, "fetchJson get null !");
        }

        return Chocolabs.getDatabaseHelper().queryDramas(null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

        if (cursor != null) {
            mDownloadCallback.onSuccess(cursor);
        } else {
            mDownloadCallback.onFailure();
        }
    }
}
