package com.notewriter.sd.notewriter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewNotes = findViewById(R.id.note_listView);

        NoteDBHelper helper = new NoteDBHelper(this);

        List<Note> rawData = helper.readAll();
        List<String> noteData = new ArrayList<>();
        for(Note d: rawData){
            noteData.add(d.getTitle());
            //noteData.add(d.getContent());
        }

//        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noteData);

        ListView lv = findViewById(R.id.note_listView);
        lv.setAdapter(new NoteListAdapter(getApplicationContext(),rawData));

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
}
