package com.anirudhshirodkar.notes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.anirudhshirodkar.notes.R;
import com.anirudhshirodkar.notes.data.Note;
import com.anirudhshirodkar.notes.data.NoteDataSource;

public class NoteAddActivity extends AppCompatActivity {

    private NoteDataSource noteDataSource;

    private EditText titleEditText;
    private EditText descriptionEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        noteDataSource = new NoteDataSource(this);

        titleEditText = (EditText) findViewById(R.id.activity_note_add_title);
        descriptionEditText = (EditText) findViewById(R.id.activity_note_add_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_note_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_note_add_done:
                saveNote();
                setResult(RESULT_OK);
                finish();
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void    saveNote() {

        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        Note note = new Note(title, description);
        noteDataSource.saveNote(note);
    }
}
