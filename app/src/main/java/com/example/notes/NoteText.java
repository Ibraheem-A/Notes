package com.example.notes;

import java.io.Serializable;

class NoteText implements Serializable {
    private String title;
    private String text;

    NoteText(String title, String text){
        this.title = title;
        this.text = text;
    }

    String getTitle() {
        return title;
    }

    String getBody() {
        return text;
    }
}
