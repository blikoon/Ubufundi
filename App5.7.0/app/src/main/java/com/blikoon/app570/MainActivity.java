/*
        Read and write to the database

        *Write data to the database using ContentValues:
                ContentValues values = getContentValues(p);
                dbWrite.insert(PeopleSchema.PeopleTable.NAME,null,values);


        *Read the data back using CursorWrapers:
               public List<Person> getPeople()
                {
                List <Person> foods = new ArrayList<>();

                PersonCursorWraper cursor = queryPeople(null,null);
                try
                {
                    cursor.moveToFirst();
                 while( !cursor.isAfterLast())
                 {
                      foods.add(cursor.getPerson());
                     cursor.moveToNext();
                 }

                }finally {
                    cursor.close();
                }
                return foods;

            }

            *More on this example :
             here :http://www.blikoon.com/tutorials/android-health-app-design-saving-data-in-sqlite-database
 */

package com.blikoon.app570;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BackupTask.CompletionListener {

    public static final String LOGTAG = "DATABASE_HACK";

    PeopleOpenHelper pplHelper;
    Cursor cursor;
    SQLiteDatabase dbWrite;
    SQLiteDatabase dbRead;

    Button addButton;
    Button querryButton;

    Button backupButton;
    Button restoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //populateDatabase();
                    //Log.d(LOGTAG,"DONE POPULATING THE DATABASE");

            }
        });
        querryButton = (Button) findViewById(R.id.querryButton);
        querryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performQuerry();
                Log.d(LOGTAG,"Querrying done");
            }
        });
        backupButton = (Button) findViewById(R.id.backupButton);
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Backup the database
                Log.d(LOGTAG,"Backing up database");
                if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
                    BackupTask task = new BackupTask(MainActivity.this);
                    task.setCompletionListener(MainActivity.this);
                    task.execute(BackupTask.COMMAND_BACKUP);
                    Log.d(LOGTAG,"Started the backup process.");
                }else
                {
                    Log.d(LOGTAG,"Could not find a mountable external storage.");
                }

            }
        });
        restoreButton = (Button) findViewById(R.id.restoreButton);
        restoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Restore the database
                Log.d(LOGTAG,"Restoring the  database");
                if( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
                    BackupTask task = new BackupTask(MainActivity.this);
                    task.setCompletionListener(MainActivity.this);
                    task.execute(BackupTask.COMMAND_RESTORE);
                    Log.d(LOGTAG,"Started backup restore process.");
                }else
                {
                    Log.d(LOGTAG,"Could not find a mountable external storage.");
                }
            }
        });
        pplHelper = new PeopleOpenHelper(this);

    }



    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();

    }



    private static ContentValues getContentValues(Person person)
    {
        ContentValues values = new ContentValues();
        values.put(PeopleSchema.PeopleTable.Cols.UUID,person.getUUID().toString());
        values.put(PeopleSchema.PeopleTable.Cols.NAME,person.getNAME().toString());
        values.put(PeopleSchema.PeopleTable.Cols.AGE, person.getAGE().toString());
        values.put(PeopleSchema.PeopleTable.Cols.LOCATION,person.getLOCATION());

        return values;
    }

    void populateDatabase()
    {
        dbWrite = pplHelper.getWritableDatabase();
        if (dbWrite!=null)
        {
            for ( int i  =0 ; i < 20 ; i ++)
            {
                String ext = Integer.toString(i);
                Person person = new Person("name_" +ext,"age_"+ext,"location_"+ext);
                addPerson(person);
            }

        }else
        {
            Log.d(LOGTAG,"populateDatabase: got a null db object");
        }




    }

    void performQuerry()
    {
        dbRead = pplHelper.getReadableDatabase();
        if (dbRead != null)
        {
            Log.d(LOGTAG,"We have a valid db object, proceed");

            List<Person> personList = getPeople();
            for (Person person :personList)
            {
                Log.d(LOGTAG,person.toString());
            }


        }else
        {
            Log.d(LOGTAG,"Halt, Invalid db object.");
        }
    }

    void addPerson( Person p)
    {
        ContentValues values = getContentValues(p);
        dbWrite.insert(PeopleSchema.PeopleTable.NAME,null,values);

    }


    private PersonCursorWraper queryPeople(String whereClause ,String [] whereArgs)
    {
        Cursor cursor = dbRead.query(
                PeopleSchema.PeopleTable.NAME,
                null ,//Columns - null selects all columns
                whereClause,
                whereArgs,
                null ,//groupBy
                null, //having
                null//orderBy
        );
        return new PersonCursorWraper(cursor);
    }

    public List<Person> getPeople()
    {
        List <Person> foods = new ArrayList<>();

        PersonCursorWraper cursor = queryPeople(null,null);
        try
        {
            cursor.moveToFirst();
            while( !cursor.isAfterLast())
            {
                foods.add(cursor.getPerson());
                cursor.moveToNext();
            }

        }finally {
            cursor.close();
        }
        return foods;

    }

    @Override
    public void onBackupComplete() {
        Toast.makeText(this, "Backup Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestoreComplete() {
        Toast.makeText(this, "Restore Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int errorCode) {
        if(errorCode == BackupTask.RESTORE_NOFILEERROR) {
            Toast.makeText(this, "No Backup Found to Restore",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error During Operation: "+errorCode,
                    Toast.LENGTH_SHORT).show();
        }

    }
}
