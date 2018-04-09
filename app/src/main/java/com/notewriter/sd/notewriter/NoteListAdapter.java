package com.notewriter.sd.notewriter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dasuni Anupama on 3/19/2018.
 */

public class NoteListAdapter extends BaseAdapter {

    List<Note> noteList;
    Context mContext;
    private NoteDBHelper helper;

    boolean onClickEnabled = true;

    public NoteListAdapter(Context context, List<Note> noteList, NoteDBHelper helper) {
        this.noteList = noteList;
        this.mContext = context;
        this.helper = helper;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        }
        Note note = noteList.get(position);
        TextView text_title = listItem.findViewById(R.id.text_title);
        TextView text_note = listItem.findViewById(R.id.text_note);
        text_title.setText(note.getTitle());
        text_note.setText(note.getContent());

        final View finalListItem = listItem;
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickEnabled) {
                    Intent noteSelectIntent = new Intent(finalListItem.getContext(), NoteActivity.class);
                    noteSelectIntent.putExtra("noteId", noteList.get(position).getId());
                    noteSelectIntent.putExtra("mode", "Update");
                    finalListItem.getContext().startActivity(noteSelectIntent);
                } else {
                    ImageButton imageButton = finalListItem.findViewById(R.id.check_note);
                    imageButton.setVisibility(View.GONE);
                    onClickEnabled = true;
                }
            }
        });

        listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final ImageButton imageButton = finalListItem.findViewById(R.id.check_note);
                imageButton.setVisibility(View.VISIBLE);
                onClickEnabled = false;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("DASU", "Item Long Delete");
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Delete Item!").setCancelable(false).setMessage("Do you want to Delete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Note note = noteList.get(position);
                                noteList.remove(note);
                                helper.delete(note.getId());
                                notifyDataSetChanged();
                                imageButton.setVisibility(View.GONE);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                imageButton.setVisibility(View.GONE);
                            }
                        });
                        builder.show();
                    }
                });
                return true;
            }
        });

        return listItem;
    }


}
