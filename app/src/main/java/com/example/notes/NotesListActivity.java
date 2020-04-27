package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        // List view & Adapter
        listView = findViewById(R.id.notesListView);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Yankee");
        arrayList.add("Upper");

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, arrayList);
        listView.setAdapter(arrayAdapter);

        final Intent launchNoteView = new Intent(getApplicationContext(), NoteViewActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchNoteView.putExtra("title", arrayList.get(position));
                launchNoteView.putExtra("noteText", arrayList.get(position)); // TODO get noteText from SharedPreferences
                startActivity(launchNoteView);
            }
        });
    }
}
