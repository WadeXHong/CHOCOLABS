package com.wadexhong.chocolabs.infopage;

import android.database.Cursor;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.helper.DatabaseHelper;
import com.wadexhong.chocolabs.objects.Drama;

/**
 * Created by wade8 on 2018/6/27.
 */

public class InfoPresenter implements InfoContract.Presenter {

    private final InfoContract.View mView;

    public InfoPresenter(InfoContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void query(String dramaId) {
        Cursor cursor = Chocolabs.getDatabaseHelper().querySpecificDrama(dramaId);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME));
            String totalView = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TOTAL_VIEWS));
            String createAt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CREATED_AT));
            String thumb = cursor.getString(cursor.getColumnIndex(DatabaseHelper.THUMB));
            String rating = cursor.getString(cursor.getColumnIndex(DatabaseHelper.RATING));
            mView.refreshUi(new Drama(dramaId, name, totalView, createAt, thumb, rating));
        } else {
            mView.showErrorView();
        }
    }

    @Override
    public void start() {

    }
}
