/*
        Share contents of your app database with other apps using ContentProviders.
        * Make sure you have the database in place.See ShareDbHelper
        * Create your ContentProvider subclass:
        *   # Decide on the uri of your content provider. The following :
        *     "content://com.blikoon.app590.friendprovider/friends"
        *     is usen in FriendProvider. It is this uri or small variations of it
        *     that other apps will use to perform queries on your database.
        *
        *   #In your ContentProvider override the necessary methods:
        *       .onCreate()
        *       .getType()
        *       .query()
        *       .insert()
        *       .update()
        *       .delete()
        *     See FriendProvider.java for details.These methods are called through your
        *     ContentProvider CONTENT_URI.
        *
        *   #The ContentProvider must be declared in the manifest file
        *       <provider
                    android:name=".FriendProvider"
                    android:authorities="com.blikoon.app590.friendprovider">
                </provider>

              Nothe that "com.blikoon.app590.friendprovider" matches what we have in the CONTENT_URI

            #Use LoaderManager and SimpleCursorAdapter to perform queries and match the returned data
             to the list view displayed in the UI.(SHOULD DIG MORE ON THIS).When the activity starts
             we query the entire database and only display the First name of the entry.When an entry
             is clicked, we then show firstName,LastName and Phone.
 */

package com.blikoon.app590;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    private static final int LOADER_LIST = 100;
    SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(LOADER_LIST, null, this);

        /*
        // Creates a new SimpleCursorAdapter
            mCursorAdapter = new SimpleCursorAdapter(
            getApplicationContext(),               // The application's Context object
            R.layout.wordlistrow,                  // A layout in XML for one row in the ListView
            mCursor,                               // The result from the query
            mWordListColumns,                      // A string array of column names in the cursor
            mWordListItems,                        // An integer array of view IDs in the row layout
            0);                                    // Flags (usually none are needed)

         */

        mAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{FriendProvider.Columns.FIRST},
                new int[]{android.R.id.text1},
                0);

        ListView list = new ListView(this);
        list.setOnItemClickListener(this);
        list.setAdapter(mAdapter);

        setContentView(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Cursor c = mAdapter.getCursor();
        c.moveToPosition(position);

        Uri uri = Uri.withAppendedPath(FriendProvider.CONTENT_URI, c.getString(0));
        String[] projection = new String[]{FriendProvider.Columns.FIRST,
                FriendProvider.Columns.LAST,
                FriendProvider.Columns.PHONE};
        //Get the full record
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();

        String message = String.format("%s %s, %s", cursor.getString(0), cursor.getString(1), cursor.getString(2));
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        cursor.close();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{FriendProvider.Columns._ID,
                FriendProvider.Columns.FIRST};
        return new CursorLoader(this, FriendProvider.CONTENT_URI,
                projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
