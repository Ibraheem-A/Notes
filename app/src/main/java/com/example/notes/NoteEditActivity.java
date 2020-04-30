package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class NoteEditActivity extends AppCompatActivity {

    TextView editBodyTextView;
    TextView editTitleTextView;

    NoteText noteText;

    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveNoteText(noteText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        editBodyTextView = findViewById(R.id.bodyEditText);
        editTitleTextView = findViewById(R.id.editTitleText);

        Intent intentFromNoteView = getIntent();
        noteText = (NoteText) intentFromNoteView.getSerializableExtra("noteText");
        assert noteText != null;
        String title = noteText.getTitle();
        String body = noteText.getBody();

        editTitleTextView.setText(title);
        editBodyTextView.setText(body);
    }

    private void saveNoteText(NoteText noteText){
        if (!NotesListActivity.noteTextArrayList.contains(noteText)){
            NotesListActivity.noteTextArrayList.add(0, noteText);
            NotesListActivity.titleArrayList = NotesListActivity.getTitlesFromNoteText(NotesListActivity.noteTextArrayList);
            NotesListActivity.arrayAdapter.notifyDataSetChanged();
            SharedPreferencesManager.saveDataToSharedPreferences(sharedPreferences, NotesListActivity.noteTextArrayList);
        }
    }
}
