package com.wadexhong.chocolabs.helper;

import android.database.Cursor;
import android.os.AsyncTask;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.objects.Data;

/**
 * Created by wade8 on 2018/6/27.
 */

public class DownloadTask extends AsyncTask<Void, Void, Cursor> {

    private DownloadCallback mDownloadCallback;

    public DownloadTask(DownloadCallback downloadCallback) {
        mDownloadCallback = downloadCallback;
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        Data data = new JsonHelper().fetchJson();
        if (data != null){
            Chocolabs.getDatabaseHelper().inputDramas(data.getDramas());
            Chocolabs.getDatabaseHelper().queryDramas(null);
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
