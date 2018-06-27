package com.wadexhong.chocolabs.infopage;

/**
 * Created by wade8 on 2018/6/27.
 */

public class InfoPresenter implements InfoContract.Presenter {

    private final InfoContract.View mView;

    public InfoPresenter(InfoContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
