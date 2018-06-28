package com.wadexhong.chocolabs.mainpage;

import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wadexhong.chocolabs.Chocolabs;
import com.wadexhong.chocolabs.R;
import com.wadexhong.chocolabs.helper.DatabaseHelper;
import com.wadexhong.chocolabs.helper.LruCacheHelper;
import com.wadexhong.chocolabs.objects.Drama;

/**
 * Created by wade8 on 2018/6/27.
 */

public class MainAdapter extends RecyclerView.Adapter {

    private Cursor mCursor;

    public void setCursor(Cursor cursor) {
        if (mCursor != null) mCursor.close();
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MainViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    private class MainViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mConstraintLayout;
        private ImageView mImageView;
        private TextView mTextViewName;
        private TextView mTextViewRating;
        private TextView mTextViewCreatedAt;

        private MainViewHolder(View itemView) {
            super(itemView);

            mConstraintLayout = itemView.findViewById(R.id.item_main_layout);
            mImageView = itemView.findViewById(R.id.item_main_imageview);
            mTextViewName = itemView.findViewById(R.id.item_main_info_name);
            mTextViewRating = itemView.findViewById(R.id.item_main_info_rating);
            mTextViewCreatedAt = itemView.findViewById(R.id.item_main_info_createat);
        }

        private void bind(int position) {
            mCursor.moveToPosition(position);

            final String id = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.DRAMA_ID));
            final String name = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.NAME));
            final String totalView = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.TOTAL_VIEWS));
            final String createAt = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.CREATED_AT));
            final String thumb = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.THUMB));
            final String rating = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.RATING));

            mTextViewName.setText(name);
            mTextViewRating.setText(Chocolabs.getStringEasy(R.string.rating, rating));
            mTextViewCreatedAt.setText(Chocolabs.getStringEasy(R.string.create_at, createAt));

            mImageView.setTag(thumb);
            new LruCacheHelper().set(thumb,
                      id,
                      mImageView);

            mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)itemView.getContext())
                              .transToInfo(new Drama(id, name, totalView, createAt, thumb, rating));
                }
            });
        }
    }
}
