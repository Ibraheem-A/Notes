package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteViewActivity extends AppCompatActivity {

    TextView titleTextView;
    TextView bodyTextView;

    String title;
    String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        titleTextView = findViewById(R.id.titleTextView);
        bodyTextView = findViewById(R.id.bodyTextView);

        Intent intentFromNoteList = getIntent();
        title = intentFromNoteList.getStringExtra("title");
        body = intentFromNoteList.getStringExtra("body");

        titleTextView.setText(title);
        bodyTextView.setText(body);

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
        launchNoteEditActivity.putExtra("title", title);
        launchNoteEditActivity.putExtra("body", title);
        startActivity(launchNoteEditActivity);
    }
}
