package com.blikoon.app570;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gakwaya on 4/14/2017.
 */

public class PeopleOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "foodDb.db";


    public PeopleOpenHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + PeopleSchema.PeopleTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                PeopleSchema.PeopleTable.Cols.UUID + "," +
                PeopleSchema.PeopleTable.Cols.NAME + "," +
                PeopleSchema.PeopleTable.Cols.AGE + "," +
                PeopleSchema.PeopleTable.Cols.LOCATION + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + PeopleSchema.PeopleTable.NAME);
        onCreate(db);

    }
}
