package com.example.notes;

class NoteText {
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
