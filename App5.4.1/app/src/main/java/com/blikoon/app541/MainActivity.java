/*
    Reading and Writing to external directories on android

    * You get a path to the external storage and create a folder there like this:
    *      File rootPath = new File(Environment.getExternalStorageDirectory(), "directory_name");
        if (!rootPath.exists()) {

            if(rootPath.mkdirs())
            {
                Log.d("ANDROID_TEST","Directory create success :"+ rootPath.getAbsolutePath());
            }else
            {
                Log.d("ANDROID_TEST","FAILED TO CREATE DIRECTORY :"+ rootPath.getAbsolutePath());

            }
        }

    *You then create files and work with them as you do in standard Java I/O like this:

           File dataFile = new File(rootPath, FILENAME);

    *Notable external storage directory APIs you should care about:
    *
        *Environment.getExternalStoragePublicDirectory(String type)
            • API Level 8
            • Returns a common directory where all applications store media files. The
                contents of these directories are visible to users and other applications. In
                particular, the media placed here will likely be scanned and inserted into
                the device’s MediaStore for applications such as the Gallery.
            • Valid type values include DIRECTORY_PICTURES, DIRECTORY_MUSIC,
                DIRECTORY_MOVIES, and DIRECTORY_RINGTONES.

        *Context.getExternalFilesDir(String type)
            • API Level 8
            • Returns a directory on external storage for media files that are specific to the
                application. Media placed here will not be considered public, however, and
                won’t show up in MediaStore.
            • This is external storage, however, so it is still possible for users and other
                applications to see and edit the files directly: there is no security enforced.
            • Files placed here will be removed when the application is uninstalled, so it can
                be a good location in which to place large content files the application needs
                that one may not want on internal storage.
            • Valid type values include DIRECTORY_PICTURES, DIRECTORY_MUSIC, DIRECTORY_
                MOVIES, and DIRECTORY_RINGTONES.

        *Context.getExternalCacheDir()
            • API Level 8
            • Returns a directory on internal storage for app-specific temporary files. The
                contents of this directory are visible to users and other applications.
            • Files placed here will be removed when the application is uninstalled, so it can
                be a good location in which to place large content files the application needs
                that one may not want on internal storage.

        *Also remember proper permissions for external storage:
            <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

 */

package com.blikoon.app541;

import android.app.Activity;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    private static final String FILENAME = "data.txt";
    private static final String DNAME = "myfiles";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);

        //Create a new directory on external storage
        File rootPath = new File(Environment.getExternalStorageDirectory(), DNAME);
        if (!rootPath.exists()) {

            if(rootPath.mkdirs())
            {
                Log.d("ANDROID_TEST","Directory create success :"+ rootPath.getAbsolutePath());
            }else
            {
                Log.d("ANDROID_TEST","FAILED TO CREATE DIRECTORY :"+ rootPath.getAbsolutePath());

            }
        }
        //Create the file reference
        File dataFile = new File(rootPath, FILENAME);

        //Check if external storage is usable
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Cannot use storage.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }else
        {
            Log.d("ANDROID_TEST"," Found external dir :" + dataFile.getAbsolutePath());
        }

        //Create a new file and write some data
        try {
            FileOutputStream mOutput = new FileOutputStream(dataFile, true);
            String data = "THIS DATA WRITTEN TO A FILE";
            mOutput.write(data.getBytes());
            //mOutput.close();

            //Clear the stream buffers
            mOutput.flush();
            //Sync all data to the filesystem
            mOutput.getFD().sync();
            //Close the stream
            mOutput.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read the created file and display to the screen
        try {
            FileInputStream mInput = new FileInputStream(dataFile);
            byte[] data = new byte[128];
            mInput.read(data);
            mInput.close();

            String display = new String(data);
            tv.setText(display.trim());
            Log.d("ANDROID_TEST"," The string we read in is :"+ display);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //YOU CAN DELETE THE CREATED FILE BUT WE ARE KEEPING IT JUST TO SEE IT ON THE DEVICE.
        //dataFile.delete();
    }
}
