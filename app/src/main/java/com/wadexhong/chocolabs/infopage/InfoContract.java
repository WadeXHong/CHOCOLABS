package com.wadexhong.chocolabs.infopage;

import com.wadexhong.chocolabs.BasePresenter;
import com.wadexhong.chocolabs.BaseView;
import com.wadexhong.chocolabs.objects.Drama;

/**
 * Created by wade8 on 2018/6/27.
 */

public interface InfoContract {

    interface View extends BaseView<Presenter> {

        void refreshUi(Drama drama);

        void showErrorView();
    }

    interface Presenter extends BasePresenter {

        void query(String dramaId);
    }
}
