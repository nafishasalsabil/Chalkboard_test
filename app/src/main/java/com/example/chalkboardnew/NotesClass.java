package com.example.chalkboardnew;

public class NotesClass {
    String noteTitle,subtitle,date,mynote;

    public NotesClass() {
    }

    public NotesClass(String noteTitle, String subtitle, String date, String mynote) {
        this.noteTitle = noteTitle;
        this.subtitle = subtitle;
        this.date = date;
        this.mynote = mynote;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMynote() {
        return mynote;
    }

    public void setMynote(String mynote) {
        this.mynote = mynote;
    }
}
