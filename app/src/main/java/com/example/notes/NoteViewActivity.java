package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NoteViewActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView bodyTextView;

    NoteText noteText;

    String title;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        titleTextView = findViewById(R.id.titleTextView);
        bodyTextView = findViewById(R.id.bodyTextView);

        Intent intentFromNoteList = getIntent();
        noteText = (NoteText) intentFromNoteList.getSerializableExtra("noteText");

        assert noteText != null;
        title = noteText.getTitle();
        body = noteText.getBody();

        Log.i("Clicked Note Title", title);
        Log.i("Clicked Note Body", body);

        titleTextView.setText(title);
        bodyTextView.setText(body);

        // set text view to scroll
        bodyTextView.setMovementMethod(new ScrollingMovementMethod());

        // new intent for note edit
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNoteEditing();
            }
        });

        bodyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNoteEditing();
            }
        });
    }

    private void launchNoteEditing(){
        Intent launchNoteEditActivity = new Intent(getApplicationContext(), NoteEditActivity.class);
        launchNoteEditActivity.putExtra("noteText", noteText);
        startActivity(launchNoteEditActivity);
    }
}
