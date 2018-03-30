package com.notewriter.sd.notewriter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private EditText titleTextField;
    private EditText contentTextField;
    private NoteDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        helper = new NoteDBHelper(this);
        try {
            int id = getIntent().getExtras().getInt("noteId");
            List<Note> noteId = helper.read(id);
            Toast.makeText(this, noteId.get(0).getContent(), Toast.LENGTH_SHORT).show();

        } catch (NullPointerException e) {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        titleTextField = (EditText) findViewById(R.id.et_title);
        contentTextField = (EditText) findViewById(R.id.et_content);

        switch (item.getItemId()) {
            case R.id.action_save_new_note:
                NoteDBHelper helper = new NoteDBHelper(this);


                if (titleTextField.getText().toString().matches("") && contentTextField.getText().toString().matches("")) {
                    Toast.makeText(this, "No New Note to be Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    helper.save(titleTextField.getText().toString(), contentTextField.getText().toString());
                    Toast.makeText(this, "Your Note Is Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;

            case R.id.action_cancel_new_note:

                if (titleTextField.getText().toString().matches("") && contentTextField.getText().toString().matches("")) {
                    finish();

                } else {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    //yes button clicked
                                    finish();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //no button clicked

                                    break;

                            }

                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
                    builder.setTitle("Really Discarding changes!").setCancelable(false).setMessage("Are you sure you want to discard the changes?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();


                }


                break;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener1 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //yes button clicked
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        //no button clicked

                        break;

                }

            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(NoteActivity.this);
        builder.setTitle("Really go back!").setMessage("Are you sure you want to go back?").setCancelable(false).setPositiveButton("Yes", dialogClickListener1).setNegativeButton("No", dialogClickListener1).show();

    }
}
