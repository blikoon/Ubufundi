/*
    *Animate Activity transitions for the entire application
    * Animations are defined in res/anim
    * Add the item:
    * <item name="android:windowAnimationStyle">
            @style/ActivityAnimation</item>
       to your theme
    * The ActivityAnimation style contains the animations we defined
    * With the theme applied in the manifest ,now every activity transition should be animated.
 */

package com.blikoon.app1131;

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
