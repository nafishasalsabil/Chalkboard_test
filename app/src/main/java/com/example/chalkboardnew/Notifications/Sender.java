package com.example.chalkboardnew.Notifications;

import com.example.chalkboardnew.Notifications.Data;
public class Sender {

    public Data notification;
    public String to;

    public Sender(Data notification, String to) {
        this.notification = notification;
        this.to = to;
    }
}

