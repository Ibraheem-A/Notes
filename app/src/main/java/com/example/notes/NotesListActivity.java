package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    ListView listView;

    static NoteText clickedNoteText;
    static ArrayList<String> titleArrayList;
    static ArrayAdapter arrayAdapter;
    static ArrayList<NoteText> noteTextArrayList;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        listView = findViewById(R.id.notesListView);

        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        noteTextArrayList = SharedPreferencesManager.loadDataFromSharedPreferences(sharedPreferences);


        titleArrayList = getTitlesFromNoteText(noteTextArrayList);
        Log.i("title on Create", titleArrayList.toString());

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_selectable_list_item, titleArrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent launchNoteView = new Intent(getApplicationContext(), NoteEditActivity.class);
                launchNoteView.putExtra("noteText", clickedNoteText = getClickedNoteText(position));
                startActivity(launchNoteView);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(NotesListActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Note!")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                noteTextArrayList.remove(position);
                                titleArrayList.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note){
            Intent launchEmptyEditor = new Intent(getApplicationContext(), NoteEditActivity.class);
            launchEmptyEditor.putExtra("noteText", new NoteText("", ""));
            startActivity(launchEmptyEditor);

            return true;
        }

        return false;
    }

    public static void updateNoteTextAndTitleArray(NoteText editedNoteText) {
        if (!noteTextArrayList.contains(editedNoteText)) {
            noteTextArrayList.add(0, editedNoteText);
        } else {
            noteTextArrayList.remove(editedNoteText);
            noteTextArrayList.add(0, editedNoteText);
        }

        titleArrayList = getTitlesFromNoteText(noteTextArrayList);
    }

    /**
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

    public NoteText getClickedNoteText(int position){
        Log.i("Clicked Note Title", noteTextArrayList.get(position).getTitle());
        Log.i("Clicked Note Body", noteTextArrayList.get(position).getBody());
        return noteTextArrayList.get(position);
    }
}
