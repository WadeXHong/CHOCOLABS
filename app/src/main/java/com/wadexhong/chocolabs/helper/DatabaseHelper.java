package com.wadexhong.chocolabs.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wadexhong.chocolabs.objects.Drama;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wade8 on 2018/6/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String TAG = DatabaseHelper.class.getSimpleName();

    private final static String DATABASE_NAME = "chocolabs.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "chocolabs";

    public final static String DRAMA_ID = "drama_id";
    public final static String NAME = "name";
    public final static String TOTAL_VIEWS = "total_views";
    public final static String CREATED_AT = "created_at";
    public final static String THUMB = "thumb";
    public final static String RATING = "rating";

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
              + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
              + DRAMA_ID + " TEXT NOT NULL UNIQUE, "
              + NAME + " TEXT NOT NULL, "
              + TOTAL_VIEWS + " TEXT NOT NULL, "
              + CREATED_AT + " TEXT NOT NULL, "
              + THUMB + " TEXT NOT NULL, "
              + RATING + " TEXT NOT NULL) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public Cursor queryDramas(@Nullable String string) {

        SQLiteDatabase db = getReadableDatabase();
        if (string != null) {
            return db.query(TABLE_NAME, null, NAME + " LIKE ?", new String[]{"%" + string + "%"}, null, null, null);
        } else {
            return db.query(TABLE_NAME, null, null, null, null, null, null);
        }
    }

    public Cursor querySpecificDrama(@NonNull String dramaId) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, DRAMA_ID + " =?", new String[]{dramaId}, null, null, null);
    }

    public void inputDramas(List<Drama> dramas) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Drama> dramaArrayList = new ArrayList<>(dramas);
        int size = dramaArrayList.size();

        for (int i = 0; i < size; i++) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(DRAMA_ID, dramaArrayList.get(i).getId());
            contentValues.put(NAME, dramaArrayList.get(i).getName());
            contentValues.put(TOTAL_VIEWS, dramaArrayList.get(i).getTotalView());
            contentValues.put(CREATED_AT, dramaArrayList.get(i).getCreateAt());
            contentValues.put(THUMB, dramaArrayList.get(i).getThumb());
            contentValues.put(RATING, dramaArrayList.get(i).getRating());

            // 檢查更新
            long id = db.insertWithOnConflict(TABLE_NAME,
                      null,
                      contentValues,
                      SQLiteDatabase.CONFLICT_IGNORE);

            if (id == -1) {
                db.update(TABLE_NAME,
                          contentValues,
                          DRAMA_ID + " =?",
                          new String[]{dramaArrayList.get(i).getId() + ""});
                Log.d(TAG, "DbUpdate at DRAMA_ID = " + dramaArrayList.get(i).getId());
            }
        }

        db.close();

//        callback.onComplete();
    }
}
