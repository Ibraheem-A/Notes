package com.example.notes;

public class NoteText {
    private String title;
    private String text;

    public NoteText(String title, String text){
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
