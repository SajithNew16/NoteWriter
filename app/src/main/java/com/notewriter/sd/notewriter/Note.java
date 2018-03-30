package com.notewriter.sd.notewriter;

/**
 * Created by Dasuni Anupama on 3/15/2018.
 */

public class Note {

    public int id;
    private String title;
    private String content;


    public Note(int id, String title, String content) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
