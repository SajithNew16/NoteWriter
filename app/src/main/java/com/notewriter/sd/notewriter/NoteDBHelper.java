package com.notewriter.sd.notewriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<Note> read(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT title,content FROM note_table WHERE id=" + id, null);
        cur.moveToNext();
        int titleIdx = cur.getColumnIndex("title");
        int contentIdx = cur.getColumnIndex("content");
        List<Note> note = new ArrayList<>();
        String title = cur.getString(titleIdx);
        String content = cur.getString(contentIdx);
        note.add(new Note(id, title, content));
        return note;
    }

    public List<Note> readAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM note_table ORDER BY id DESC", null);
        cur.moveToNext();
        int idIdx = cur.getColumnIndex("id");
        int titleIdx = cur.getColumnIndex("title");
        int contentIdx = cur.getColumnIndex("content");
        List<Note> note = new ArrayList<>();
        while (!cur.isAfterLast()) {
            Integer id = cur.getInt(idIdx);
            String title = cur.getString(titleIdx);
            String content = cur.getString(contentIdx);
            note.add(new Note(id, title, content));
            cur.moveToNext();

        }
        return note;

    }
}
