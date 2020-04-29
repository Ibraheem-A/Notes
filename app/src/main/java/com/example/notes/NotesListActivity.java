package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;
    ImageView addNoteView;

    static ArrayList<String> arrayList;
    static ArrayAdapter arrayAdapter;

    SharedPreferences sharedPreferences;

    public void onAddClick(View view){
        addNoteView = (ImageView)view;
        Intent launchEmptyEditor = new Intent(getApplicationContext(), NoteEditActivity.class);
        startActivity(launchEmptyEditor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        sharedPreferences  = getSharedPreferences("Notes", MODE_PRIVATE);


        // List view & Adapter
        listView = findViewById(R.id.notesListView);

        arrayList = new ArrayList<>();
        arrayList.add("Yankee");
        arrayList.add("Upper");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, arrayList);
        listView.setAdapter(arrayAdapter);

        final Intent launchNoteView = new Intent(getApplicationContext(), NoteViewActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchNoteView.putExtra("title", arrayList.get(position));
                launchNoteView.putExtra("body", arrayList.get(position)); // TODO get noteText from SharedPreferences
                startActivity(launchNoteView);
            }
        });
    }
}
