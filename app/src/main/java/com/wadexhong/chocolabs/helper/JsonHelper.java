package com.wadexhong.chocolabs.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.wadexhong.chocolabs.objects.Data;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wade8 on 2018/6/27.
 */

public class JsonHelper {

    public final String url = "http://www.mocky.io/v2/5a97c59c30000047005c1ed2";

    public Data fetchJson() {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            Gson gson = new Gson();
            return gson.fromJson(response.body().string(), Data.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
