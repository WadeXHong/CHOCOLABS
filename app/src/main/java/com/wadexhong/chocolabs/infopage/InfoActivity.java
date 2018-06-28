package com.wadexhong.chocolabs.infopage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.R;
import com.wadexhong.chocolabs.helper.LruCacheHelper;
import com.wadexhong.chocolabs.objects.Drama;

public class InfoActivity extends AppCompatActivity implements InfoContract.View {

    private InfoContract.Presenter mPresenter;

    private ImageView mImageView;
    private TextView mTextViewName;
    private TextView mTextViewTotalViews;
    private TextView mTextViewCreatedAt;
    private TextView mTextViewRating;
    private TextView mTextViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mPresenter = new InfoPresenter(this);

        mImageView = findViewById(R.id.activity_info_imageview);
        mTextViewName = findViewById(R.id.activity_info_name);
        mTextViewTotalViews = findViewById(R.id.activity_info_totalviews);
        mTextViewCreatedAt = findViewById(R.id.activity_info_createat);
        mTextViewRating = findViewById(R.id.activity_info_rating);
        mTextViewError = findViewById(R.id.activity_info_error);

        // 動畫只從第一格..
//        ChangeBounds bounds = new ChangeBounds();
//        bounds.setDuration(750);
//        getWindow().setSharedElementEnterTransition(bounds);

        mPresenter.start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String dramaId = getIntent().getStringExtra("drama_id");
        mPresenter.query(dramaId);
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshUi(Drama drama) {
        mTextViewError.setVisibility(View.INVISIBLE);
        mTextViewName.setText(drama.getName());
        mTextViewTotalViews.setText(Chocolabs.getStringEasy(R.string.total_views, drama.getTotalView()));
        mTextViewCreatedAt.setText(Chocolabs.getStringEasy(R.string.create_at, drama.getCreateAt()));
        mTextViewRating.setText(Chocolabs.getStringEasy(R.string.rating, drama.getRating()));
        mImageView.setTag(drama.getThumb());
        new LruCacheHelper().set(drama.getThumb(),
                  String.valueOf(drama.getId()),
                  mImageView);
    }

    @Override
    public void showErrorView() {
        mTextViewError.setVisibility(View.VISIBLE);
        mTextViewName.setVisibility(View.INVISIBLE);
        mTextViewTotalViews.setVisibility(View.INVISIBLE);
        mTextViewCreatedAt.setVisibility(View.INVISIBLE);
        mTextViewRating.setVisibility(View.INVISIBLE);
        mImageView.setVisibility(View.INVISIBLE);
    }
}
