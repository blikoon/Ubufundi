/*
    Manage your screen real estate
 */
package com.blikoon.app12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Request this feature so the ActionBar will hide
        //Use with Full screen UI Mode
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        setContentView(R.layout.activity_main);

    }

    public void onToggleClick(View v) {

//        ///Dark Mode---API11
        //Dim the navigation controls to let the user focus on your content.
        //Keyword here: we dim the controls ,not REMOVE them.To remove some , look below.
//        int currentVis = v.getSystemUiVisibility();
//        int newVis;
//        if ((currentVis & View.SYSTEM_UI_FLAG_LOW_PROFILE)
//                == View.SYSTEM_UI_FLAG_LOW_PROFILE) {
//            newVis = View.SYSTEM_UI_FLAG_VISIBLE;
//        } else {
//            newVis = View.SYSTEM_UI_FLAG_LOW_PROFILE;
//        }
//        v.setSystemUiVisibility(newVis);
//        ///-->>Dark Mode
        ///////////////-----------------------------------------------------

//        ///Hiding Navigation Controls---API14
//        //Hide (Temporarily remove the virtual home and back buttons on devices
//        //without hardware buttons.
//
//        v.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//
//        ///When the virtual control buttons are hidden there is more screen real estate.
//        ///The view items laid out relative to the bottom of the screen will experience
//        //screen position changes.Keep this in mind when using this API.
//
//        ////-->>Hiding Navigation Controls.
        ///////////////-----------------------------------------------------

        ///Full Screen UI Mode ---API16
        v.setSystemUiVisibility(
                /* This flag tells Android not to shift
                * our layout when resizing the window to
                * hide/show the system elements .The contents of the screen says intact.In this example,the button stays in the middle.
                 */View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                /* This flag hides the system status bar. If
                * ACTION_BAR_OVERLAY is requested, it will hide
                * the ActionBar as well.
                */| View.SYSTEM_UI_FLAG_FULLSCREEN

                /* This flag hides the onscreen controls
                */| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        ////-->>Full Screen UI Mode

    }

    

}
