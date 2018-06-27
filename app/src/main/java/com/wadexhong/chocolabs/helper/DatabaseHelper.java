package com.wadexhong.chocolabs.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wade8 on 2018/6/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "chocolabs.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "chocolabs";

    private final static String DRAMA_ID = "drama_id";
    private final static String NAME = "name";
    private final static String TOTAL_VIEWS = "total_views";
    private final static String CREATED_AT = "created_at";
    private final static String THUMB = "thumb";
    private final static String RATING = "rating";

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
              + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
              + DRAMA_ID + " TEXT NOT NULL, "
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
}
