package com.notewriter.sd.notewriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SajithChamara on 3/17/2018.
 */

public class NoteDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notedatabase.db";

    public NoteDBHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE note_table(id integer PRIMARY KEY,title text, content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(String title, String content) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("content", content);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("note_table", null, cv);
        db.close();
    }
}
