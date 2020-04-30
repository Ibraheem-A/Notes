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

    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NoteText currentState = getStateOfCurrentNote();
        saveNoteText(currentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        editBodyTextView = findViewById(R.id.bodyEditText);
        editTitleTextView = findViewById(R.id.editTitleText);

        Intent intentFromNoteView = getIntent();
        String title = intentFromNoteView.getStringExtra("title");
        String body = intentFromNoteView.getStringExtra("body");

        editTitleTextView.setText(title);
        editBodyTextView.setText(body);
    }


    private NoteText getStateOfCurrentNote(){
        String title = editTitleTextView.getText().toString();
        String body = editTitleTextView.getText().toString();
        return new NoteText(title, body);
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
