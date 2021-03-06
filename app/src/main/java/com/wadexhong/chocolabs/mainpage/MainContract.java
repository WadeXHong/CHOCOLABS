package com.wadexhong.chocolabs.mainpage;

import android.database.Cursor;

import com.wadexhong.chocolabs.BasePresenter;
import com.wadexhong.chocolabs.BaseView;

/**
 * Created by wade8 on 2018/6/27.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void refreshUi(Cursor cursor);
    }

    interface Presenter extends BasePresenter {

        void searchDrama(String newText);
    }
}
