/*
    Reading and writing to files stored in the assets folder of your android project

        *Make sure you have an "assets" folder alongside java and res in your project
         I usually go to file explorer and create it myself and android studio picks it up.

        *Put your assets in there.In this example data.csv is used
        * Use AssetManager to get an InputStream you use to read from the asset file like this:

        *      //Access application assets
            AssetManager manager = getAssets();
            //Open our data file
            InputStream in = manager.open("data.csv");

        *The rest is standard Java reading and parsing.Look at the parse method .
 */

package com.blikoon.app550;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity  extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);

        try {
            //Access application assets
            AssetManager manager = getAssets();
            //Open our data file
            InputStream in = manager.open("data.csv");

            //Parse the CSV data and display
            ArrayList<Person> cooked = parse(in);
            StringBuilder builder = new StringBuilder();
            for (Person piece : cooked) {
                builder.append(String.format("%s is %s years old, and likes the color %s",
                        piece.name, piece.age, piece.color));
                builder.append('\n');
            }
            tv.setText(builder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* Simple CSV Parser */
    private static final int COL_NAME = 0;
    private static final int COL_AGE = 1;
    private static final int COL_COLOR = 2;

    private ArrayList<Person> parse(InputStream in) throws IOException {
        ArrayList<Person> results = new ArrayList<Person>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String nextLine = null;
        while ((nextLine = reader.readLine()) != null) {
            String[] tokens = nextLine.split(",");
            if (tokens.length != 3) {
                Log.w("CSVParser", "Skipping Bad CSV Row");
                continue;
            }
            //Add new parsed result
            Person current = new Person();
            current.name = tokens[COL_NAME];
            current.color = tokens[COL_COLOR];
            current.age = tokens[COL_AGE];

            results.add(current);
        }

        in.close();

        return results;
    }

    private class Person {
        public String name;
        public String age;
        public String color;

        public Person() {
        }
    }
}
