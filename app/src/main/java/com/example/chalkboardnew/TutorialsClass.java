package com.example.chalkboardnew;

public class TutorialsClass {
    String link;

    public TutorialsClass() {
    }

    public TutorialsClass(String link){

        this.link=link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return link;
    }

}
