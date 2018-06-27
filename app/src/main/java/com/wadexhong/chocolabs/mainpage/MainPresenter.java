package com.wadexhong.chocolabs.mainpage;

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

    }
}
