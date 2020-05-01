package com.example.notes;

import java.io.Serializable;

class NoteText implements Serializable {
    private String title;
    private String body;

    NoteText(String title, String body){
        this.title = title;
        this.body = body;
    }

    String getTitle() {
        return title;
    }

    String getBody() {
        return body;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setBody(String body) {
        this.body = body;
    }
}
