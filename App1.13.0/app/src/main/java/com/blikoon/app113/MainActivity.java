/*
    * Animate activity transitions per activity basis
    * Call overridePendingTransition right after you call startActivity() or finish() and the animations you pass in the arguments are applied
    * Animations are defined in resources : res/anim/
    * Animations can also be applied per application.This is done at the theme level.App1.13.1 shows that.
 */
package com.blikoon.app113;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Button b = new Button(this);
        b.setText("Click");
        b.setOnClickListener(this);
        setContentView(b);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
    }

}
