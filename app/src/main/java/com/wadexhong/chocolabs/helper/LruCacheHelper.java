package com.wadexhong.chocolabs.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.wadexhong.chocolabs.Chocolabs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * Created by wade8 on 2018/6/27.
 */

public class LruCacheHelper {

    public void set(String url, ImageView imageView) {

        Bitmap bitmap = Chocolabs.getLruCache().get(url);
        if (bitmap == null) {
            new ImageDownloadTask(url, imageView).executeOnExecutor(Executors.newCachedThreadPool());
        } else {
            if (imageView.getTag() == url) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }




    public Bitmap getSmallBitmap(String url) {
        Bitmap bitmap = null;
        try {
            InputStream input = new URL(url).openStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, options);
            int height = options.outHeight;
            int width = options.outWidth;

            int inSampleSize = 1;

            if (height > 150 || width > 150) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                while ((halfHeight / inSampleSize) > 150
                          && (halfWidth / inSampleSize) > 150) {
                    inSampleSize += 1;
                }
            }
            input = new URL(url).openStream();
            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            bitmap = BitmapFactory.decodeStream(input, null, options);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    public class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {

        private String mUrl;
        private ImageView mImageView;

        public ImageDownloadTask(String url, ImageView imageView) {
            mUrl = url;
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return getSmallBitmap(mUrl);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {
                Chocolabs.getLruCache().put(mUrl, bitmap);
                if (mImageView.getTag().equals(mUrl)) {
                    mImageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
