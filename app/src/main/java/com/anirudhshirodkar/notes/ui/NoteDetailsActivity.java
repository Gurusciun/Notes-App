package com.anirudhshirodkar.notes.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.anirudhshirodkar.notes.R;
import com.anirudhshirodkar.notes.data.Note;
import com.anirudhshirodkar.notes.data.NoteDataSource;

public class NoteDetailsActivity extends AppCompatActivity {

    public static final String NOTE_EXTRA = "NOTE_EXTRA";

    private NoteDataSource noteDataSource;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Note note = getIntent().getParcelableExtra(NOTE_EXTRA);

        noteId = note.getId();

        TextView titleTextView = (TextView) findViewById(R.id.activity_note_details_title);
        TextView descriptionTextView = (TextView) findViewById(R.id.activity_note_details_description);

        titleTextView.setText(note.getTitle());
        descriptionTextView.setText(note.getDescription());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_note_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_note_details_delete:
                // show delete dialog
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void showDeleteDialog(){

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.note_delete_dialog_title)
                .setMessage(R.string.note_delete_dialog_message)
                .setPositiveButton(R.string.note_delete_dialog_positive_button, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteDataSource.deleteNote(noteId);
                        setResult(RESULT_OK);
                        finish();

                    }
                })
                .setNegativeButton(R.string.note_delete_dialog_negative_button, null)
                .create();
        alertDialog.show();

    }

}
