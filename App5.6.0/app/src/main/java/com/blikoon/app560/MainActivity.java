/*
        Get an instance of the SQLite database on android and write data in

        *The database structure(table and columns) is defined in the onCreate() function
         of your subclass of SQLOpenHelper.See myDbHelper.java

        *You get an instance to the db using SQLiteOpenHelper like this:

           SQLiteDatabase db = SQLiteOpenHelper.getWritableDatabase()

          This function creates the database if not present and if it is there, it
           opens it for writing.

        *Get information about a database table using a qery that returns a Cursor you will use
         for further manipulations.A Cursor is an interface designed to read, write, and traverse
         the results of a query.

                  String[] columns = new String[]{"_id", MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
                  //columns contains the names of all the columns  contained in your table.See MyDbHelper.java
                  Cursor mCursor = mDb.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null, null);
                  //The cursor we get here is capable of manipulating all row entries in the columns specified in the columns String array.

        *Next you figure out a way to wire your database to the ui.In here we used a SimpleCursorAdapter to make the
         ListItems in our ListView reflect what is stored in the database:

               String[] headers = new String[]{MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
                mAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                mCursor, headers, new int[]{android.R.id.text1, android.R.id.text2});
                mList.setAdapter(mAdapter);

       *Add data to the database:
               //Add a new value to the database
                ContentValues cv = new ContentValues(2);
                 cv.put(MyDbHelper.COL_NAME, mText.getText().toString());
                 //Create a formatter for SQL date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cv.put(MyDbHelper.COL_DATE, dateFormat.format(new Date())); //Insert 'now' as the date
                mDb.insert(MyDbHelper.TABLE_NAME, null, cv);
                //Refresh the cursor and the list
                mCursor.requery();
                mAdapter.notifyDataSetChanged();//This lets the list view update with new data

       *Delete data from the database at some position:
                //Delete the item from the database
                mCursor.moveToPosition(position);
                //Get the id value of this row
                String rowId = mCursor.getString(0); //Column 0 of the cursor is the id
                mDb.delete(MyDbHelper.TABLE_NAME, "_id = ?", new String[]{rowId});
                //Refresh the list
                mCursor.requery();
                mAdapter.notifyDataSetChanged();

       *Close database connections when you are done.In this case when the activity pauses:
                //Close all connections
                 mDb.close();
                mCursor.close();

       *More on database manipulation here:
        http://www.blikoon.com/tutorials/android-health-app-design-saving-data-in-sqlite-database

 */

package com.blikoon.app560;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText mText;
    Button mAdd;
    ListView mList;

    MyDbHelper mHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (EditText) findViewById(R.id.name);
        mAdd = (Button) findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        mList = (ListView) findViewById(R.id.list);
        mList.setOnItemClickListener(this);

        mHelper = new MyDbHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Open connections to the database
        mDb = mHelper.getWritableDatabase();
        String[] columns = new String[]{"_id", MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
        mCursor = mDb.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        //Refresh the list
        String[] headers = new String[]{MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                mCursor, headers, new int[]{android.R.id.text1, android.R.id.text2});
        mList.setAdapter(mAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Close all connections
        mDb.close();
        mCursor.close();
    }

    @Override
    public void onClick(View v) {
        //Add a new value to the database
        ContentValues cv = new ContentValues(2);
        cv.put(MyDbHelper.COL_NAME, mText.getText().toString());
        //Create a formatter for SQL date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cv.put(MyDbHelper.COL_DATE, dateFormat.format(new Date())); //Insert 'now' as the date
        mDb.insert(MyDbHelper.TABLE_NAME, null, cv);
        //Refresh the cusrsor list
        mCursor.requery();
        mAdapter.notifyDataSetChanged();
        //Clear the edit field
        mText.setText(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //Delete the item from the database
        mCursor.moveToPosition(position);
        //Get the id value of this row
        String rowId = mCursor.getString(0); //Column 0 of the cursor is the id
        mDb.delete(MyDbHelper.TABLE_NAME, "_id = ?", new String[]{rowId});
        //Refresh the list
        mCursor.requery();
        mAdapter.notifyDataSetChanged();
    }
}
