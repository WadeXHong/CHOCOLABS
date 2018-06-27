package com.wadexhong.chocolabs.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wade8 on 2018/6/27.
 */

public class Drama {

    @SerializedName("drama_id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("total_views")
    private long mTotalView;
    @SerializedName("created_at")
    private String mCreateTime;
    @SerializedName("thumb")
    private String mThumb;
    @SerializedName("rating")
    private float mRating;

    public Drama(){
        mId = -1;
        mName = "";
        mTotalView = -1;
        mCreateTime = "";
        mThumb = "";
        mRating = -1;
    }

    public Drama(int id, String name, long totalView, String createTime, String thumb, float rating) {
        mId = id;
        mName = name;
        mTotalView = totalView;
        mCreateTime = createTime;
        mThumb = thumb;
        mRating = rating;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getTotalView() {
        return mTotalView;
    }

    public void setTotalView(long totalView) {
        mTotalView = totalView;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String createTime) {
        mCreateTime = createTime;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        mThumb = thumb;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }

}
