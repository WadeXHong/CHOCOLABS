package com.wadexhong.chocolabs.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wade8 on 2018/6/27.
 */

public class Data {

    @SerializedName("data")
    private List<Drama> mDramas;

    public Data(List<Drama> dramas){
        mDramas = dramas;
    }

    public List<Drama> getDramas() {
        return mDramas;
    }

    public void setDramas(List<Drama> dramas) {
        mDramas = dramas;
    }
}
