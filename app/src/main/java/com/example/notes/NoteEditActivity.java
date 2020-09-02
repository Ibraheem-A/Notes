package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NoteEditActivity extends AppCompatActivity {

    TextView editBodyTextView;
    TextView editTitleTextView;

    NoteText noteText;

    SharedPreferences sharedPreferences;

    @Override
    public void onBackPressed() {
        Log.i("Action", "Back Pressed in Note Edit");
        saveNoteAndGoToPreviousActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        editTitleTextView = findViewById(R.id.editTitleText);
        editBodyTextView = findViewById(R.id.editBodyText);

        Intent intentFromNoteView = getIntent();
        noteText = (NoteText) intentFromNoteView.getSerializableExtra("noteText");
        assert noteText != null;
        String title = noteText.getTitle();
        String body = noteText.getBody();

        Log.i("Clicked Note Title", title);
        Log.i("Clicked Note Body", body);

        editTitleTextView.setText(title);
        editBodyTextView.setText(body);
    }

    /**
     * saves the NoteText object when the save button is pressed 
     * @param view the ImageView of the save button
     */
    public void onSaveClick(View view){
        Log.i("Action", "Save button pressed in Note Edit");
        saveNoteAndGoToPreviousActivity();
    }

    /**
     * saves note and returns to Note List screen
     */
    private void saveNoteAndGoToPreviousActivity() {
        super.onBackPressed();
        commitCurrentNote();
        Log.i("Saving", noteText.getTitle() + " with body size " + noteText.getBody().length() + " chars");
        saveNoteText(noteText);
    }

    /**
     * updates the note text with the edited title and body
     */
    private void commitCurrentNote(){
        String title = editTitleTextView.getText().toString();
        String body = editBodyTextView.getText().toString();
        noteText.setTitle(title);
        noteText.setBody(body);
    }

    /**
     * saves the NoteText object in shared preferences
     * @param noteText the note text object
     */
    private void saveNoteText(NoteText noteText){
        NotesListActivity.noteTextArrayList.add(0, noteText);
        NotesListActivity.titleArrayList = NotesListActivity.getTitlesFromNoteText(NotesListActivity.noteTextArrayList);
        NotesListActivity.arrayAdapter.notifyDataSetChanged();
        SharedPreferencesManager.saveDataToSharedPreferences(sharedPreferences, NotesListActivity.noteTextArrayList);
    }
}
