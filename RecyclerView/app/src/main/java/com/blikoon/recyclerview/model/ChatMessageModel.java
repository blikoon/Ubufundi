package com.blikoon.recyclerview.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blikoon.recyclerview.persistance.ChatMessageCursorWrapper;
import com.blikoon.recyclerview.persistance.DatabaseBackend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gakwaya on 2017/11/2.
 */

public class ChatMessageModel {
    ////////////////////////

    public interface OnMessageAddListener {
        public void onMessageAdd();
    }

    private static ChatMessageModel sFoodModel;
    //    private List<Food> mFoods;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public OnMessageAddListener getMessageAddListener() {
        return messageAddListener;
    }

    public void setMessageAddListener(OnMessageAddListener messageAddListener) {
        this.messageAddListener = messageAddListener;
    }

    OnMessageAddListener messageAddListener;




    public static ChatMessageModel get(Context context)
    {
        if(sFoodModel == null)
        {
            sFoodModel = new ChatMessageModel(context);
        }
        return  sFoodModel;
    }

    private ChatMessageModel(Context context)
    {
        mContext = context.getApplicationContext();
        //mDatabase = new FoodOpenHelper(mContext).getWritableDatabase();
        mDatabase = DatabaseBackend.getInstance(mContext).getWritableDatabase();
        //populateWithInitialMessages();
    }

    public void addMessage(ChatMessage c)
    {
        ContentValues values = c.getContentValues();
        mDatabase.insert(ChatMessage.TABLE_NAME, null, values);
        messageAddListener.onMessageAdd();
    }
    public void updateMessage(ChatMessage c)
    {



    }

    private void populateWithInitialMessages()
    {
        //Message
        ChatMessage m1 = new ChatMessage("Hello",133345454, ChatMessage.Type.SENT);
        addMessage(m1);


        ChatMessage m2 = new ChatMessage("Hi you!",32334213, ChatMessage.Type.RECEIVED);
        addMessage(m2);

    }

    public List<ChatMessage> getMessages()
    {
        List<ChatMessage> messages = new ArrayList<>();

        ChatMessageCursorWrapper cursor = queryMessages(null,null);
        try
        {
            cursor.moveToFirst();
            while( !cursor.isAfterLast())
            {
                messages.add(cursor.getChatMessage());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }
        return messages;
    }



    private ChatMessageCursorWrapper queryMessages(String whereClause , String [] whereArgs)
    {
        Cursor cursor = mDatabase.query(
                ChatMessage.TABLE_NAME,
                null ,//Columns - null selects all columns
                whereClause,
                whereArgs,
                null ,//groupBy
                null, //having
                null//orderBy
        );
        return new ChatMessageCursorWrapper(cursor);
    }


    ////////////////////////
}
