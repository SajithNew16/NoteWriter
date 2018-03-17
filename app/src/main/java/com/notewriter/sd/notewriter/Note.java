package com.notewriter.sd.notewriter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;

import java.util.TimeZone;

/**
 * Created by Dasuni Anupama on 3/15/2018.
 */

public class Note {
    private long nDateTime;
    private String Title;
    private String Context;

    public Note(long nDateTime, String Title, String Context) {
        this.setnDateTime(nDateTime);
        this.setTitle(Title);
        this.setContext(Context);
    }

    public long getnDateTime() {
        return nDateTime;
    }

    public void setnDateTime(long nDateTime) {
        this.nDateTime = nDateTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

   /* public String DateTimeFormat(Context context){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYY HH:mm:ss"
        , context.getResources().getConfiguration().locale);
        dateFormat.setTimeZone(TimeZone.getDefault());
    }
    */
}
