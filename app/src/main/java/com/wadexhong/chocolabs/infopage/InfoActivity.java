package com.wadexhong.chocolabs.infopage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wadexhong.chocolabs.R;

public class InfoActivity extends AppCompatActivity implements InfoContract.View {

    private InfoContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_activity);

        mPresenter = new InfoPresenter(this);
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
