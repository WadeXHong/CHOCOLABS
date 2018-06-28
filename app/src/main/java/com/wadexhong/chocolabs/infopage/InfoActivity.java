package com.wadexhong.chocolabs.infopage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
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

        Drama drama = (Drama) getIntent().getSerializableExtra("Drama");
        mTextViewName.setText(drama.getName());
        mTextViewTotalViews.setText(Chocolabs.getStringEasy(R.string.total_views, drama.getTotalView()));
        mTextViewCreatedAt.setText(Chocolabs.getStringEasy(R.string.create_at, drama.getCreateAt()));
        mTextViewRating.setText(Chocolabs.getStringEasy(R.string.rating, drama.getRating()));
        mImageView.setTag(drama.getThumb());
        new LruCacheHelper().set(drama.getThumb(),
                  String.valueOf(drama.getId()),
                  mImageView);

//        ChangeBounds bounds = new ChangeBounds();
//        bounds.setDuration(750);
//        getWindow().setSharedElementEnterTransition(bounds);

        mPresenter.start();
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
