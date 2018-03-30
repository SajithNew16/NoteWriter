package com.notewriter.sd.notewriter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    public  NoteDBHelper helper;

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
                MainActivity.this.startActivity(newNoteIntent);
                break;
        }

        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Note> noteData = helper.readAll();

        ListView noteList = findViewById(R.id.note_listView);
        noteList.setAdapter(new NoteListAdapter(getApplicationContext(),noteData));

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent NoteSelectIntent = new Intent(MainActivity.this,NoteActivity.class);
                NoteSelectIntent.putExtra("noteData",noteData.get(position).getId());
               // NoteSelectIntent.putExtra("content",noteData.get(position).getId());
                MainActivity.this.startActivity(NoteSelectIntent);
            }
        });
    }
}

