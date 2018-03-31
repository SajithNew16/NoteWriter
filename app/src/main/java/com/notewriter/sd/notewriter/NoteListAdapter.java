package com.notewriter.sd.notewriter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dasuni Anupama on 3/19/2018.
 */

public class NoteListAdapter extends BaseAdapter {

    List<Note> noteList;
    Context mContext;

    public NoteListAdapter(Context context, List<Note> noteList) {
        this.noteList = noteList;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }
        Note note = noteList.get(position);
        TextView text_title = listItem.findViewById(R.id.text_title);
        TextView text_note = listItem.findViewById(R.id.text_note);
        text_title.setText(note.getTitle());
        text_note.setText(note.getContent());

        return listItem;
    }
}
