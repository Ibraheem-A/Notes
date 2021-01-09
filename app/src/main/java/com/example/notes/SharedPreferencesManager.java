package com.example.notes;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class SharedPreferencesManager {


    static ArrayList<NoteText> loadDataFromSharedPreferences(SharedPreferences sharedPreferences){
        Type listOfNoteTextObject = new TypeToken<ArrayList<NoteText>>() {}.getType();
        String serializedNotes = sharedPreferences.getString("serializedNotes", "");
        if (!serializedNotes.equals("")) {
            Gson gson = new Gson();
            ArrayList<NoteText> noteTextArrayList = gson.fromJson(serializedNotes, listOfNoteTextObject);
            return noteTextArrayList;
        }
        else {
            return new ArrayList<>();
        }
    }

    static void saveDataToSharedPreferences(SharedPreferences sharedPreferences, ArrayList<NoteText> noteTextsArray){
        Gson gson = new Gson();
        String serializedNotes = gson.toJson(noteTextsArray);
        sharedPreferences.edit().putString("serializedNotes", serializedNotes).apply();
    }
}
