package com.notewriter.sd.notewriter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    EditText titleTextField;
    EditText contentTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_new_note:
                NoteDBHelper helper = new NoteDBHelper(this);

                titleTextField = (EditText) findViewById(R.id.et_title);
                contentTextField = (EditText) findViewById(R.id.et_content);

                helper.save(titleTextField.getText().toString(),contentTextField.getText().toString());

                Toast.makeText(this,"Your Note Is Saved!",Toast.LENGTH_SHORT).show();
                finish();

                break;

        }
        return true;
    }
}
