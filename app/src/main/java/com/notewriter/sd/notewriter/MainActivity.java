package com.notewriter.sd.notewriter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    public NoteDBHelper helper;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNotes = findViewById(R.id.note_listView);
        helper = new NoteDBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_note:
                Intent newNoteIntent = new Intent(MainActivity.this, NoteActivity.class);
                newNoteIntent.putExtra("mode", "Save");
                MainActivity.this.startActivity(newNoteIntent);
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        final List<Note> noteData = helper.readAll();
        final ListView noteList = findViewById(R.id.note_listView);
        noteList.setAdapter(new NoteListAdapter(context, noteData, helper));
    }

}

