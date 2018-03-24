package com.notewriter.sd.notewriter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;

import java.util.TimeZone;

/**
 * Created by Dasuni Anupama on 3/15/2018.
 */

public class Note {

    public int id;
    //private long nDateTime;
    private String Title;
    private String content;


    public Note(int id, String Title, String content) {
        this.setId(id);
        //this.setnDateTime(nDateTime);
        this.setTitle(Title);
        this.setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public long getnDateTime() {
        return nDateTime;
    }

    public void setnDateTime(long nDateTime) {
        this.nDateTime = nDateTime;
    }
*/
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   /* public String DateTimeFormat(Context context){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYY HH:mm:ss"
        , context.getResources().getConfiguration().locale);
        dateFormat.setTimeZone(TimeZone.getDefault());
    }
    */
}
