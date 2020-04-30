package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;
    ImageView addNoteView;

    static ArrayList<String> titleArrayList;
    static ArrayAdapter arrayAdapter;
    static ArrayList<NoteText> noteTextArrayList;

    SharedPreferences sharedPreferences;

    public void onAddClick(View view){
        addNoteView = (ImageView)view;
        Intent launchEmptyEditor = new Intent(getApplicationContext(), NoteEditActivity.class);
        launchEmptyEditor.putExtra("noteText", new NoteText("", ""));
        startActivity(launchEmptyEditor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        listView = findViewById(R.id.notesListView);

        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        // clear ArrayLists to refresh this activity
//        if(!noteTextArrayList.equals(null) && !noteTextArrayList.isEmpty()){ noteTextArrayList.clear();}
//        if(!titleArrayList.isEmpty()){ titleArrayList.clear();}

        noteTextArrayList = SharedPreferencesManager.loadDataFromSharedPreferences(sharedPreferences);

        titleArrayList = getTitlesFromNoteText(noteTextArrayList);

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, titleArrayList);
        listView.setAdapter(arrayAdapter);

        // Intent to launch note view activity
        final Intent launchNoteView = new Intent(getApplicationContext(), NoteViewActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchNoteView.putExtra("noteText", getClickedNoteText(position));
                startActivity(launchNoteView);
            }
        });
    }


    /**
     *
     * @param noteTextArrayList list of NoteText objects gotten from shared preferences
     * @return ArrayList of the titles of each saved notes
     */
    public static ArrayList<String> getTitlesFromNoteText(ArrayList<NoteText> noteTextArrayList){
        Log.i("Starting", "getting titles from notes array");
        Log.i("Size of notes array", Integer.toString(noteTextArrayList.size()));
        ArrayList<String> titlesList = new ArrayList<>();
        if (noteTextArrayList.size() > 0){
            for(int i = 0; i < noteTextArrayList.size(); i++){
                titlesList.add(noteTextArrayList.get(i).getTitle());
            }
        }
        Log.i("Note Titles", titlesList.toString());
        return titlesList;
    }

    public static NoteText getClickedNoteText(int position){
        Log.i("Clicked Note Title", noteTextArrayList.get(position).getTitle());
        Log.i("Clicked Note Body", noteTextArrayList.get(position).getBody());
        return noteTextArrayList.get(position);
    }
}
