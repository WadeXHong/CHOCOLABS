package com.wadexhong.chocolabs.helper;

import android.os.AsyncTask;

import com.wadexhong.chocolabs.objects.Data;

/**
 * Created by wade8 on 2018/6/27.
 */

public class DownloadTask extends AsyncTask<Void, Void, Data> {

    private DownloadCallback mDownloadCallback;

    public DownloadTask(DownloadCallback downloadCallback) {
        mDownloadCallback = downloadCallback;
    }

    @Override
    protected Data doInBackground(Void... voids) {
        return new JsonHelper().fetchJson();
    }

    @Override
    protected void onPostExecute(Data data) {
        super.onPostExecute(data);

        if (data != null) {
            mDownloadCallback.onSuccess(data);
        } else {
            mDownloadCallback.onFailure();
        }
    }
}
