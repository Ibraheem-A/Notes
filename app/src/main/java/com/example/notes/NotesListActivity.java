package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        // List view & Adapter
        listView = findViewById(R.id.notesListView);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Yankee");
        arrayList.add("Upper");

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
