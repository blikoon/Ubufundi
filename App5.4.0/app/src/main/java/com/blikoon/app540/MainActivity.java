/*
    Android provides functions to get the root of the internal storage for a
    particular app either for writing or reading:

        .FileOutputStream mOutput = Context.openFileOutput(FILENAME, Activity.MODE_PRIVATE);
        .FileInputStream mInput = Context.openFileInput(FILENAME);
        .The second parameter can either be MODE_PRIVATE : to override the file if it already is there
         or MODE_APPEND : to append the new content to the file if it already exists.

    These functions then return an InputStream/OutputStream that you user to write/read your data
    The rest is normal Java I/O stuff

 */


package com.blikoon.app540;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity  extends Activity {

    private static final String FILENAME = "data.txt";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);

        //Create a new file and write some data
        try {
            FileOutputStream mOutput = openFileOutput(FILENAME, Activity.MODE_PRIVATE);
            String data = "THIS DATA WRITTEN TO A FILE";
            mOutput.write(data.getBytes());
            mOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read the created file and display to the screen
        try {
            FileInputStream mInput = openFileInput(FILENAME);
            byte[] data = new byte[128];
            mInput.read(data);
            mInput.close();

            String display = new String(data);
            tv.setText(display.trim());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Delete the created file
        //deleteFile(FILENAME);
    }
}
