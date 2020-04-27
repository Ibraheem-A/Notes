package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoteEditActivity extends AppCompatActivity {

    TextView editNoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        editNoteTextView = findViewById(R.id.noteEditText);

        Intent intentFromNoteView = getIntent();
        String title = intentFromNoteView.getStringExtra("title");
        String noteText = intentFromNoteView.getStringExtra("noteText");

        editNoteTextView.setText(noteText);
    }
}
