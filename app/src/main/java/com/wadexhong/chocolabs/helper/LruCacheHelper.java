package com.wadexhong.chocolabs.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.wadexhong.chocolabs.Chocolabs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * Created by wade8 on 2018/6/27.
 */

public class LruCacheHelper {

    public void set(String url, String id, ImageView imageView) {

        Bitmap bitmap = Chocolabs.getLruCache().get(url);
        if (bitmap == null) {
            new ImageDownloadTask(url, id, imageView).executeOnExecutor(Executors.newCachedThreadPool());
        } else {
            if (imageView.getTag().equals(url)) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }


    public Bitmap getBitmap(String url, String id) {

        Bitmap bitmap = null;
        Log.e("tem[", Chocolabs.getAppContext().getFileStreamPath(id+".jpg").length()+"");
        if (Chocolabs.getAppContext().getFileStreamPath(id+".jpg").length() == 0) {
            try {
                bitmap = cutBitmap(url, bitmap);
                saveAsJPEG(id, bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            bitmap = BitmapFactory.decodeFile(Chocolabs.getAppContext().getFilesDir()+"/"+id+".jpg");
        }

        return bitmap;
    }

    private Bitmap cutBitmap(String url, Bitmap bitmap) throws IOException {
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
        return bitmap;
    }

    private void saveAsJPEG(String id, Bitmap bitmap) throws FileNotFoundException {
        File file = new File(Chocolabs.getAppContext().getFilesDir(), id + ".jpg");
        FileOutputStream out = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
    }


    public class ImageDownloadTask extends AsyncTask<Void, Void, Bitmap> {

        private String mUrl;
        private String mId;
        private ImageView mImageView;

        public ImageDownloadTask(String url, String id, ImageView imageView) {
            mUrl = url;
            mId = id;
            mImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return getBitmap(mUrl, mId);
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
