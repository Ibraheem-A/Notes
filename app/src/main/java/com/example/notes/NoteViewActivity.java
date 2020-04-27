package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class NoteViewActivity extends AppCompatActivity {

    TextView noteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        noteTextView = findViewById(R.id.noteTextView);

        Intent intentFromNoteList = getIntent();
        String currentNote = intentFromNoteList.getStringExtra("title");

        noteTextView.setText(currentNote);
    }
}
