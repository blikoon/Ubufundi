package com.blikoon.app131;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflate the layout and add a button before attaching
        //it to the view
        LinearLayout layout = (LinearLayout)getLayoutInflater()
                .inflate(R.layout.activity_main, null);
        //Add a new button.Here you can also add complex ui structures
        Button reset = new Button(this);
        reset.setText("Reset Form");
        layout.addView(reset,
                new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
        //Attach the view to the window

        setContentView(layout);
        //Continue with your custom stuff
    }
}
