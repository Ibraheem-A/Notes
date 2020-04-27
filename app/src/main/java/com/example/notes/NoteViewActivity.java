package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class NoteViewActivity extends AppCompatActivity {

    TextView noteTextView;

    String currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        noteTextView = findViewById(R.id.noteTextView);

        Intent intentFromNoteList = getIntent();
        currentNote = intentFromNoteList.getStringExtra("title");

        noteTextView.setText(currentNote);

        // new intent for note edit
        noteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchNoteEditActivity = new Intent(getApplicationContext(), NoteEditActivity.class);
                launchNoteEditActivity.putExtra("title", currentNote);
                launchNoteEditActivity.putExtra("noteText", currentNote);
                startActivity(launchNoteEditActivity);
            }
        });
    }
}
