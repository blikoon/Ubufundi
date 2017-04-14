package com.blikoon.app570;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

/**
 * Created by gakwaya on 4/14/2017.
 */

public class PersonCursorWraper extends CursorWrapper {

    public PersonCursorWraper(Cursor cursor)
    {
        super(cursor);
    }

    public Person getPerson()
    {
        String uuidString = getString(getColumnIndex(PeopleSchema.PeopleTable.Cols.UUID));
        String name = getString(getColumnIndex(PeopleSchema.PeopleTable.Cols.NAME));

        String age = getString(getColumnIndex(PeopleSchema.PeopleTable.Cols.AGE));
        String location = getString(getColumnIndex(PeopleSchema.PeopleTable.Cols.LOCATION));


        Person p = new Person(name,age,location,UUID.fromString(uuidString));


        return  p;

    }
}
