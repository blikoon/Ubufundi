package com.blikoon.recyclerview.model;

import android.content.ContentValues;
import android.text.format.DateFormat;

import java.util.concurrent.TimeUnit;

/**
 * Created by gakwaya on 2017/11/1.
 */

public class ChatMessage {

    private String message;
    private long timestamp;
    private Type type;


    public static final String TABLE_NAME = "chatMessages";

    public static final class Cols
    {
        public static final String message = "message";
        public static final String timestamp = "timestamp";
        public static final String messageType = "messageType";
    }




    public ChatMessage(String message, long timestamp, Type type){
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getFormattedTime(){

        long oneDayInMillis = TimeUnit.DAYS.toMillis(1); // 24 * 60 * 60 * 1000;

        long timeDifference = System.currentTimeMillis() - timestamp;

        return timeDifference < oneDayInMillis
                ? DateFormat.format("hh:mm a", timestamp).toString()
                : DateFormat.format("dd MMM - hh:mm a", timestamp).toString();
    }

    public ContentValues getContentValues()
    {
        ContentValues values = new ContentValues();
        values.put("message", message);
        values.put("timestamp", Long.toString(timestamp));
        values.put("messageType",getTypeStringValue(type));

        return values;

    }

    public String getTypeStringValue(Type type)
    {
        if(type==Type.SENT)
            return "SENT";
        else
            return "RECEIVED";
    }

    public enum Type {
        SENT, RECEIVED
    }
}
