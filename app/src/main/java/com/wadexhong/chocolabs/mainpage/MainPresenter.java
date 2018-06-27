package com.wadexhong.chocolabs.mainpage;

import android.database.Cursor;

import com.wadexhong.chocolabs.helper.DownloadCallback;
import com.wadexhong.chocolabs.helper.InfoDownloadTask;

/**
 * Created by wade8 on 2018/6/27.
 */

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        downloadMainData();
    }

    private void downloadMainData() {
        new InfoDownloadTask(new DownloadCallback() {
            @Override
            public void onSuccess(Cursor cursor) {
                mView.refreshUi(cursor);
            }

            @Override
            public void onFailure() {

            }
        }).execute();
    }
}
