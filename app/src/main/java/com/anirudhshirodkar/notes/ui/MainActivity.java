package com.anirudhshirodkar.notes.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.SurfaceHolder.Callback;


import com.anirudhshirodkar.notes.R;
import com.anirudhshirodkar.notes.data.Note;
import com.anirudhshirodkar.notes.data.NoteDataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NOTE_ADD_ACTIVITY_REQUEST_CODE = 1;
    private static final int NOTE_DETAILS_ACTIVITY_REQUEST_CODE = 2;

    private RecyclerView recyclerView;

    private NotesListAdapter notesListAdapter;
    private NoteDataSource noteDataSource;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_main_add_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startActivityIntent = new Intent(getApplicationContext(), NoteAddActivity.class);
                startActivityForResult(startActivityIntent, NOTE_ADD_ACTIVITY_REQUEST_CODE);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        noteDataSource = new NoteDataSource(this);
        noteList = noteDataSource.getNotes();
        notesListAdapter = new NotesListAdapter(noteList, new OnNoteSelectedListener() {
            @Override
            public void OnNoteSelected(Note note) {

                // start details activity here

                Intent intent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
                intent.putExtra(NoteDetailsActivity.NOTE_EXTRA, note);
                startActivityForResult(intent, NOTE_DETAILS_ACTIVITY_REQUEST_CODE);
            }
        });
        recyclerView.setAdapter(notesListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode){
            if (NOTE_ADD_ACTIVITY_REQUEST_CODE == requestCode || NOTE_DETAILS_ACTIVITY_REQUEST_CODE == requestCode){
                noteList = noteDataSource.getNotes();
                notesListAdapter.setNoteList(noteList);
                recyclerView.smoothScrollToPosition(0);
            }
        }
    }
}
